package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.*;
import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.WrongTimerTypeException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.ExamConfirmationTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerObserver;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsertStudentsGradesControl implements TimerObserver {

    protected InsertStudentsGradesControl() {

    }

    /**
     * Checks if user has Professor role by checking
     * user token and throws exception if not,
     * then gets a list of all his gradable exams from
     * the ExamLazyFactory and returns it,
     * visibility is protected because it should only be usable by another
     * class in this class' package, e.g. the right facade
     *
     * @param token
     * @return gradable exam list
     * @throws MissingAuthorizationException
     */
    protected ExamListBean getGradableExams(Token token) throws MissingAuthorizationException, DAOException {
        Professor professor = SessionManager.getInstance().getSessionUserByToken(token).getRole().getProfessorRole();
        return new ExamListBean(createExamBeanList(ExamLazyFactory.getInstance().getGradableExams(professor)));
    }

    /**
     * Creates an ExamBeanList and populates it with the values
     * of the exams in the input list,
     * visibility is private because it should only be usable by another
     * method in this class
     *
     * @param inList
     * @return
     */
    private List<ExamBean> createExamBeanList(List<Exam> inList){
        List<ExamBean> outList = new ArrayList<>();
        for (Exam e : inList){
            outList.add(new ExamBean(new SubjectCourseBean(e.getSubjectCourse().getCode(),e.getSubjectCourse().getName(),e.getSubjectCourse().getAcademicYear()),e.getAppello(),e.getSession()));
        }
        return outList;
    }

    /**
     * Checks if requesting user has Professor role by checking user token
     * and throws MissingAuthorizationException if not,
     * then checks if requesting professor teaches the course
     * the exam is for and throws exception if not,
     * visibility is protected because it should only be usable by another
     * class in this class' package, e.g. the right facade
     * 
     * @param token
     * @param bean
     * @return
     */
    protected ExamEnrollmentListBean getExamEnrollments(Token token, ExamBean bean) throws MissingAuthorizationException, DAOException{
        Professor professor = SessionManager.getInstance().getSessionUserByToken(token).getRole().getProfessorRole();
        Exam exam = getExamByBean(bean);
        checkExamProfessor(exam, professor);
        List<ExamEnrollmentBean> list = new ArrayList<>();
        for (ExamEnrollment e : ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByExam(exam)){
            list.add(new ExamEnrollmentBean(e.getStudent(),e.getExam()));
        }
        return new ExamEnrollmentListBean(list);
    }

    /**
     * Checks if a professor is teaching the course a specific exam is correlated to
     * and throws MissingAuthorizationException otherwise,
     * visibility is private because it should only be usable by another
     * method in this class
     * 
     * @param exam
     * @param professor
     * @throws MissingAuthorizationException
     */
    private void checkExamProfessor(Exam exam, Professor professor) throws MissingAuthorizationException{
        Boolean check = false;
        for (CourseAssignment c : exam.getSubjectCourse().getCourseAssignments()){
            if (c.getProfessor().equals(professor)) {
                check = true;
                break;
            }
        }
        if (!check) throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
    }

    /**
     * Gets the model instance correlated to a specific exam bean
     * from the ExamLazyFactory,
     * visibility is private because it should only be usable
     * by another method in this class
     *
     * @param bean
     * @return exam
     */
    private Exam getExamByBean(ExamBean bean) throws DAOException {
        return ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(bean.getAppello(), getSubjectCourseByBean(bean.getCourse()),bean.getSessione());
    }

    /**
     * Gets the model instance correlated to a specific subject course bean
     * from the ExamLazyFactory,
     * visibility is private because it should only be usable
     * by another method in this class
     *
     * @param bean
     * @return subjectCourse
     */
    private SubjectCourse getSubjectCourseByBean(SubjectCourseBean bean){
        return SubjectCourseLazyFactory.getInstance().getSubjectCourseByName(bean.getName());
    }

    /**
     * Checks if requesting user has Professor role by checking user token
     * and throws MissingAuthorizationException if not,
     * also checks if requesting user is teaching the course the provided exam is correlated to
     * and throws MissingAuthorizationException otherwise,
     * then calls saveExamResult for each result and creates a pending event for correspondent enrolled student
     * in order to ask them to accept or reject the result,
     * finally creates an exam confirmation timer for the confirmation expiration time,
     * visibility is protected because it should only be usable by another
     * class in this class' package, e.g. the right facade
     *
     * @param token
     * @param list
     * @throws MissingAuthorizationException
     */
    protected void saveExamResults(Token token, StudentGradeListBean list) throws MissingAuthorizationException, DAOException{
        Professor professor = SessionManager.getInstance().getSessionUserByToken(token).getRole().getProfessorRole();
        Exam exam = list.getExam();
        checkExamProfessor(exam, professor);
        for (StudentGradeBean g : list.getGrades()){
            saveExamResult(g);
            PendingEventLazyFactory.getInstance().createNewPendingEventSingle(g.getEnrollmentBean().getStudent().getUser(), PendingEventTypeEnum.EVENT_1, g.getEnrollmentBean().getExam());
        }
        TimerLazyFactory.getInstance().newExamConfirmationTimer(LocalDate.now().plusDays(7L), list.getExam());
    }

    /**
     * Asks the lazy factory to save provided exam result,
     * visibility is private because it should only be usable
     * by another method in this class
     *
     * @param bean
     */
    private void saveExamResult(StudentGradeBean bean) throws DAOException {
        ExamEnrollmentLazyFactory.getInstance().saveExamResult(ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentByExamAndStudent(bean.getEnrollmentBean().getExam(), bean.getEnrollmentBean().getStudent()), new ExamResult(bean.getExamResultBean().getGrade(),bean.getExamResultBean().getResult(), ExamResultConfirmationEnum.NULL));
    }

    /**
     * Checks if requesting user has Student role by checking user token
     * and throws MissingAuthorizationException if not,
     * also checks if requesting user is owner of the enrollment they want to confirm the result
     * and throws MissingAuthorizationException otherwise,
     * then changes result confirmation status to the one provided by the student,
     * visibility is protected because it should only be usable by another
     * class in this class' package, e.g. the right facade
     *
     * @param token
     * @param enrollment
     * @param decision
     * @throws MissingAuthorizationException
     */
    protected void acceptOrRejectExamGrade(Token token, ExamEnrollment enrollment, ExamResultConfirmationEnum decision) throws MissingAuthorizationException{
        Student student = SessionManager.getInstance().getSessionUserByToken(token).getRole().getStudentRole();
        if (enrollment.getStudent().equals(student)){
            enrollment.getExamResult().setConfirmed(decision);
        } else throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
    }

    /**
     * Checks if requesting user has Secretary role by checking user token
     * and throws MissingAuthorizationException if not,
     * also checks if requesting user is owner of the enrollment they want to confirm the result
     * and throws MissingAuthorizationException otherwise,
     * then changes result confirmation status to the one provided by the student,
     * visibility is protected because it should only be usable by another
     * class in this class' package, e.g. the right facade
     *
     * @param token
     * @param bean
     * @throws MissingAuthorizationException
     */
    protected void confirmExamVerbaleProtocolization(Token token, ProtocolBean bean) throws MissingAuthorizationException, DAOException{
        Secretary secretary = SessionManager.getInstance().getSessionUserByToken(token).getRole().getSecretaryRole();
        Exam e = ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(bean.getExamBean().getAppello(), SubjectCourseLazyFactory.getInstance().getSubjectCourseByName(bean.getExamBean().getCourse().getName()), bean.getExamBean().getSessione());
        checkExamSecretary(e, secretary);
        e.setVerbalizable(false);
        e.setVerbaleDate(bean.getVerbaleDate());
        e.setVerbaleNumber(bean.getVerbaleNumber());
        ExamLazyFactory.getInstance().update(e);
        notifyExamProtocolization(e);
    }

    /**
     * Checks if a secretary is assigned to a dipartimento offering the course a specific exam is correlated to
     * and throws MissingAuthorizationException otherwise,
     * visibility is private because it should only be usable
     * by another method in this class
     *
     * @param exam
     * @param secretary
     * @throws MissingAuthorizationException
     */
    private void checkExamSecretary(Exam exam, Secretary secretary) throws MissingAuthorizationException{
        Boolean check = false;
        for (DegreeCourse d : exam.getSubjectCourse().getDegreeCourses()){
            if (d.getDipartimento().equals(secretary.getDipartimento())) {
                check = true;
                break;
            }
        }
        if (!check) throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
    }

    /**
     * Notifies all the students enrolled to, and all professor teaching, an exam
     * that the exam verbale has been protocolized by a secretary,
     * visibility is private because it should only be usable
     * by another method in this class
     *
     * @param exam
     */
    private void notifyExamProtocolization(Exam exam){
        List<User> users = new ArrayList<>();
        for (ExamEnrollment e : exam.getEnrollments()){
            users.add(e.getStudent().getUser());
        }
        for (CourseAssignment c : exam.getSubjectCourse().getCourseAssignments()){
            users.add(c.getProfessor().getUser());
        }
        PendingEventLazyFactory.getInstance().createNewPendingEventGroup(users,PendingEventTypeEnum.EVENT_4,exam);
    }

    /**
     * Implements TimerObserver timeIsUp operation,
     * checks the timer is an exam confirmation timer and throws WrongTimerException otherwise,
     * then marks as accepted all non-accepted nor rejected results and notifies corresponding students,
     * notifies all the secretary assigned to any dipertimento correlated with the course that
     * the verbale needs to be protocolized
     *
     * @param timerArgument
     * @throws WrongTimerTypeException
     */
    @Override
    public void timeIsUp(AbstractTimer timerArgument) throws WrongTimerTypeException {
        ExamConfirmationTimer timer = timerArgument.castToExamConfirmationTimer();
        for (ExamEnrollment e : timer.getObject().getEnrollments()){
            if (e.getExamResult().getConfirmed()==ExamResultConfirmationEnum.NULL){
                e.getExamResult().setConfirmed(ExamResultConfirmationEnum.ACCEPTED);
                PendingEventLazyFactory.getInstance().createNewPendingEventSingle(e.getStudent().getUser(), PendingEventTypeEnum.EVENT_2, e);
            }
        }
        List<User> list = new ArrayList<>();
        for (DegreeCourse d : timer.getObject().getSubjectCourse().getDegreeCourses()){
            for (Secretary s : DAOFactoryAbstract.getDAOFactory().getSecretaryDAO().getSecretariesByDipartimento(d.getDipartimento())){
                list.add(s.getUser());
            }
        }
        PendingEventLazyFactory.getInstance().createNewPendingEventGroup(list,PendingEventTypeEnum.EVENT_3, timer.getObject());
    }
}

