package it.uniroma2.dicii.ispw.gradely.model.exam_result.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;

public abstract class AbstractExamResultDAO implements DAOInterface<ExamResult> {
    protected static AbstractExamResultDAO instance;

    protected AbstractExamResultDAO(){
    }


}
