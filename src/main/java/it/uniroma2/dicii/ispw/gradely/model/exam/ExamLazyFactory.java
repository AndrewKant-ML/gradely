package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamLazyFactory {
    private static ExamLazyFactory instance;
    private final List<Exam> exams;

    private ExamLazyFactory(){
        exams = new ArrayList<>();
    }

    public static synchronized ExamLazyFactory getInstance(){
        if (instance == null){
            instance = new ExamLazyFactory();
        }
        return instance;
    }

    public Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) throws DAOException {
        for(Exam e : exams){
            if(e.getAppello().equals(appello) && e.getSubjectCourse().equals(course) && e.getSession().equals(session)){
                return e;
            }
        }
        try {
            return DAOFactoryAbstract.getInstance().getExamDAO().getExamByAppelloAndSubjectCourseAndSession(appello, course, session);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public List<Exam> getGradableExams(Professor professor) throws DAOException, UserNotFoundException, ObjectNotFoundException {
        List<Exam> list = new ArrayList<>();
        for(SubjectCourse c : SubjectCourseAssignmentLazyFactory.getInstance().getAssignedSubjectCoursesByProfessor(professor)){
            for (Exam e : c.getExams()){
                if (Boolean.TRUE.equals(e.getGradable())){
                    list.add(e);
                }
            }
        }
        return list;
    }

    public void update (Exam exam) throws DAOException {
        try {
            DAOFactoryAbstract.getInstance().getExamDAO().update(exam);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
}
