package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.ArrayList;
import java.util.List;

public class ExamEnrollmentLazyFactory {
    private static ExamEnrollmentLazyFactory instance;
    private final List<ExamEnrollment> factoryObjects;

    private ExamEnrollmentLazyFactory(){
        factoryObjects = new ArrayList<ExamEnrollment>();
    }

    public static synchronized ExamEnrollmentLazyFactory getInstance(){
        if (instance == null){
            instance = new ExamEnrollmentLazyFactory();
        }
        return instance;
    }

    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam) throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        List<ExamEnrollment> list = new ArrayList<>();
        for(ExamEnrollment e : factoryObjects){
            if(e.getExam().equals(exam)){
                list.add(e);
            }
        }
        try {
            List<ExamEnrollment> daoList = DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().getExamEnrollmentsByExam(exam,list);
            factoryObjects.addAll(daoList);
            list.addAll(daoList);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return list;
    }

    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student) throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        List<ExamEnrollment> list = new ArrayList<>();
        for(ExamEnrollment e : factoryObjects){
            if(e.getStudent().equals(student)){
                list.add(e);
            }
        }
        try {
            list.addAll(DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().getExamEnrollmentsByStudent(student, list));
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return list;
    }

    public ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student) throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        for(ExamEnrollment e : factoryObjects){
            if(e.getExam().equals(exam) && e.getStudent().equals(student)){
                return e;
            }
        }
        try {
            return DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().getExamEnrollmentByExamAndStudent(exam, student);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public void saveExamResult(ExamEnrollment enrollment, ExamResult result) throws DAOException, MissingAuthorizationException {
        enrollment.setExamResult(result);
        try {
            DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().update(enrollment);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public void update (ExamEnrollment exam) throws DAOException, MissingAuthorizationException {
        try {
            DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().update(exam);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
}