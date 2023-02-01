package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamResultLazyFactory {
    private static ExamResultLazyFactory instance;
    private List<Exam> exams;

    private ExamResultLazyFactory(){
        exams = new ArrayList<Exam>();
    }

    public static ExamResultLazyFactory getInstance(){
        if (instance == null) {
            instance = new ExamResultLazyFactory();
        }
        return instance;
    }


    public void update (ExamResult examResult){
        DAOFactoryAbstract.getDAOFactory().getExamResultDAO().update(examResult);
    }
}
