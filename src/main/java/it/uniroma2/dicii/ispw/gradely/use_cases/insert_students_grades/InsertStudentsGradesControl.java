package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.*;
import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResultLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.ExamConfirmationTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerObserver;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.ExamResultConfirmationBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertStudentsGradesControl extends TimerObserver {

    private static final Logger logger = Logger.getLogger(InsertStudentsGradesControl.class.getName());

    InsertStudentsGradesControl() {
        super();
    }

    /**
     * Checks if user has Professor role by checking
     * user token and throws exception if not,
     * then gets a list of all his gradable exams from
     * the ExamLazyFactory and returns it,
     * visibility is protected because it should only be usable by another
     * class in this class' package, e.g. the right facade
     *
     * @param tokenKey
     * @return gradable exam list
     * @throws MissingAuthorizationException
     */
    ExamListBean getGradableExams(String tokenKey) throws MissingAuthorizationException, DAOException, UserNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        Professor professor = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getProfessorRole();
        try {
            return new ExamListBean(createExamBeanList(ExamLazyFactory.getInstance().getGradableExams(professor)));
        } catch (ObjectNotFoundException e) {
            // This can only happen if DB is corrupted, so the application must stop
            logger.log(Level.SEVERE, String.format("Error: professor with codice_fiscale %s does not exists", professor.getCodiceFiscale()));
            System.exit(1);
        }
        return null;
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
            outList.add(new ExamBean(new SubjectCourseBean(e.getSubjectCourse().getCode(),e.getSubjectCourse().getName(),e.getSubjectCourse().getAcademicYear()),e.getAppello(),e.getSession(),e.getExaminationDate()));
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
     * @param tokenKey
     * @param bean
     * @return
     */
    ExamEnrollmentListBean getExamEnrollments(String tokenKey, ExamBean bean) throws MissingAuthorizationException, DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UserNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        Professor professor = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getProfessorRole();
        Exam exam = getExamByBean(bean);
        checkExamProfessor(exam, professor);
        List<ExamEnrollmentBean> list = new ArrayList<>();
        for (ExamEnrollment e : ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByExam(exam)) {
            list.add(new ExamEnrollmentBean(new StudentBean(e.getStudent().getCodiceFiscale()), new ExamBean(new SubjectCourseBean(e.getExam().getSubjectCourse().getCode(),e.getExam().getSubjectCourse().getName(),e.getExam().getSubjectCourse().getAcademicYear()),e.getExam().getAppello(),e.getExam().getSession(),e.getExam().getExaminationDate())));
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
        for (SubjectCourseAssignment c : exam.getSubjectCourse().getCourseAssignments()){
            if (c.getProfessor().equals(professor)){
                check = true;
                break;
            }
        }
        if (Boolean.FALSE.equals(check)) throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
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
    private Exam getExamByBean(ExamBean bean) throws DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UserNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(bean.getAppello(), getSubjectCourseByBean(bean.getCourse()), bean.getSession());
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
    private SubjectCourse getSubjectCourseByBean(SubjectCourseBean bean) throws DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UserNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(bean.getCode(), bean.getName(), bean.getCfu(), bean.getAcademicYear());
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
     * @param tokenKey
     * @param list
     * @throws MissingAuthorizationException
     */
    void saveExamResults(String tokenKey, StudentGradeListBean list) throws MissingAuthorizationException, DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException, WrongTimerTypeException, PropertyException, ResourceNotFoundException {
        Professor professor = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getProfessorRole();
        ExamBean examBean = list.getExam();
        Exam exam = ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(
                examBean.getAppello(),
                SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(
                        examBean.getCourse().getCode(),
                        examBean.getCourse().getName(),
                        examBean.getCourse().getCfu(),
                        examBean.getCourse().getAcademicYear()
                ),
                examBean.getSession()
        );
        checkExamProfessor(exam, professor);
        for (StudentGradeBean g : list.getGrades()) {
            saveExamResult(g);
            PendingEventLazyFactory.getInstance().createNewPendingEvent(List.of(g.getEnrollmentBean().getStudent().getCodiceFiscale()), PendingEventTypeEnum.GRADE_CONFIRMATION_PENDING, g.getEnrollmentBean().getExam());
        }
        TimerLazyFactory.getInstance().newExamConfirmationTimer(LocalDate.now().plusDays(7L), exam);
    }

    /**
     * Asks the lazy factory to save provided exam result,
     * visibility is private because it should only be usable
     * by another method in this class
     *
     * @param bean
     */
    private void saveExamResult(StudentGradeBean bean) throws DAOException, MissingAuthorizationException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException, PropertyException, ResourceNotFoundException {
        ExamEnrollmentLazyFactory.getInstance().saveExamResult(
                ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentByExamAndStudent(
                                ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(
                                        bean.getEnrollmentBean().getExam().getAppello(),
                                        SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(
                                                bean.getEnrollmentBean().getExam().getCourse().getCode(),
                                                bean.getEnrollmentBean().getExam().getCourse().getName(),
                                                bean.getEnrollmentBean().getExam().getCourse().getCfu(),
                                                bean.getEnrollmentBean().getExam().getCourse().getAcademicYear()),
                                        bean.getEnrollmentBean().getExam().getSession()),
                        StudentLazyFactory.getInstance().getStudentByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale(bean.getEnrollmentBean().getStudent().getCodiceFiscale()))),
                ExamResultLazyFactory.getInstance().newExamResult(bean.getExamResultBean().getGrade(), bean.getExamResultBean().getResult(), ExamResultConfirmationEnum.NULL));
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
     * @param tokenKey
     * @param enrollment
     * @param decision
     * @throws MissingAuthorizationException
     */
    void acceptOrRejectExamGrade(String tokenKey, ExamEnrollmentBean enrollment, ExamResultConfirmationBean decision) throws MissingAuthorizationException, DAOException, UserNotFoundException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException {
        Student student = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getStudentRole();
        if (enrollment.getStudent().getCodiceFiscale().equals(student.getCodiceFiscale())) {
            ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentByExamAndStudent(
                    ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(
                            enrollment.getExam().getAppello(),
                            SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(enrollment.getExam().getCourse().getCode(),enrollment.getExam().getCourse().getName(),enrollment.getExam().getCourse().getCfu(),enrollment.getExam().getCourse().getAcademicYear()),
                            enrollment.getExam().getSession()
                    ),
                    StudentLazyFactory.getInstance().getStudentByUser(
                            UserLazyFactory.getInstance().getUserByCodiceFiscale(student.getCodiceFiscale())
                    )
            ).getExamResult().setConfirmed(decision.getDecision());
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
     * @param tokenKey
     * @param bean
     * @throws MissingAuthorizationException
     */
    void confirmExamVerbaleProtocolization(String tokenKey, ProtocolBean bean) throws MissingAuthorizationException, DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UserNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        Secretary secretary = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey).getRole().getSecretaryRole();
        Exam e = ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(bean.getExamBean().getAppello(), SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(bean.getExamBean().getCourse().getCode(), bean.getExamBean().getCourse().getName(), bean.getExamBean().getCourse().getCfu(), bean.getExamBean().getCourse().getAcademicYear()), bean.getExamBean().getSession());
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
            if (d.getDipartimento().equals(secretary.getDipartimento())){
                check = true;
                break;
            }
        }
        if (Boolean.FALSE.equals(check)) throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
    }

    /**
     * Notifies all the students enrolled to, and all professor teaching, an exam
     * that the exam verbale has been protocolized by a secretary,
     * visibility is private because it should only be usable
     * by another method in this class
     *
     * @param exam
     */
    private void notifyExamProtocolization(Exam exam) throws DAOException, MissingAuthorizationException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException {
        for (ExamEnrollment e : exam.getEnrollments()){
            PendingEventLazyFactory.getInstance().createNewPendingEvent(List.of(e.getStudent().getCodiceFiscale()),PendingEventTypeEnum.EXAM_VERBALIZED, exam);
        }
        for (SubjectCourseAssignment c : exam.getSubjectCourse().getCourseAssignments()){
            PendingEventLazyFactory.getInstance().createNewPendingEvent(List.of(c.getProfessor().getCodiceFiscale()),PendingEventTypeEnum.EXAM_VERBALIZED, exam);
        }
    }

    /**
     * Implements TimerObserver timeIsUp operation,
     * checks the timer is an exam confirmation timer and throws WrongTimerException otherwise,
     * then marks as accepted all non-accepted nor rejected results and notifies corresponding students,
     * notifies all the secretary assigned to any dipertimento correlated with the course that
     * the verbale needs to be protocolized
     *
     * @param timer
     * @throws WrongTimerTypeException
     */
    @Override
    public void timeIsUp(AbstractTimer timer) throws WrongTimerTypeException, DAOException, UserNotFoundException, MissingAuthorizationException, ObjectNotFoundException, UnrecognizedRoleException, WrongListQueryIdentifierValue, WrongDegreeCourseCodeException {
        ExamConfirmationTimer concreteTimer = timer.castToExamConfirmationTimer();
        for (ExamEnrollment e : concreteTimer.getObject().getEnrollments()) {
            if (e.getExamResult().getConfirmed() == ExamResultConfirmationEnum.NULL) {
                e.getExamResult().setConfirmed(ExamResultConfirmationEnum.ACCEPTED);
                PendingEventLazyFactory.getInstance().createNewPendingEvent(List.of(e.getStudent().getCodiceFiscale()), PendingEventTypeEnum.GRADE_AUTO_ACCEPTED, e);
            }
        }
        List<String> list = new ArrayList<>();
        for (DegreeCourse d : concreteTimer.getObject().getSubjectCourse().getDegreeCourses()) {
            for (Secretary s : SecretaryLazyFactory.getInstance().getSecretariesByDipartimento(d.getDipartimento())) {
                list.add(s.getCodiceFiscale());
            }
        }
        PendingEventLazyFactory.getInstance().createNewPendingEvent(list, PendingEventTypeEnum.EXAM_VERBALIZATION_PENDING, concreteTimer.getObject());
    }
}

