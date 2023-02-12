package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.*;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.WrongDegreeCourseCodeException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;

import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAOFS implements DegreeCourseDAOInterface {

    private static final String degreeCourseFileName = "degree_course";
    private static final String prerequisiteFileName = "degree_course_prerequisite";

    private static DegreeCourseDAOFS instance;

    private DegreeCourseDAOFS() {

    }

    public static synchronized DegreeCourseDAOInterface getInstance() {
        if (instance == null) {
            instance = new DegreeCourseDAOFS();
        }
        return instance;
    }

    private DegreeCourse parseLine(List<String> line) throws DAOException, ResourceNotFoundException, WrongDegreeCourseCodeException {
        DegreeCourse degreeCourse = new DegreeCourse(
                DegreeCourseCodeEnum.getDegreeCourseCodeByValue(Integer.parseInt(line.get(0))),
                line.get(1),
                FacoltaEnum.getFacoltaByValue(Integer.parseInt(line.get(6))),
                DipartimentoEnum.getDipartimentoByValue(Integer.parseInt(line.get(5))),
                DegreeCourseTypeEnum.getDegreeCourseTypeByValue(Integer.parseInt(line.get(2))),
                TestTypeEnum.getTestTypeByValue(Integer.parseInt(line.get(4)))
        );
        degreeCourse.setPrerequisites(DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(getPrerequisitesCodesByDegreeCourseCode(line.get(0))));
        return degreeCourse;
    }

    @Override
    public DegreeCourse getDegreeCourseByName(String name) throws DAOException, ResourceNotFoundException, ObjectNotFoundException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(degreeCourseFileName);
            for (List<String> line : lines)
                if (line.get(1).equals(name))
                    return (parseLine(line));
            throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
        } catch (CsvException | WrongDegreeCourseCodeException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public List<DegreeCourse> getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException, ResourceNotFoundException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(degreeCourseFileName);
            List<DegreeCourse> newDegreeCourses = new ArrayList<>();
            for (List<String> line : lines)
                if (checkListPresenceByCode(Integer.parseInt(line.get(0)), degreeCourses))
                    newDegreeCourses.add(parseLine(line));
            return newDegreeCourses;
        } catch (CsvException | WrongDegreeCourseCodeException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Checks if a DegreeCourse is contained in a List by checking each list's element by code
     *
     * @param code          the code to be checked
     * @param degreeCourses the List of DegreeCourse to check on
     * @return true if the DegreeCourse is contained in the list, false otherwise
     */
    private boolean checkListPresenceByCode(int code, List<DegreeCourse> degreeCourses) {
        for (DegreeCourse degreeCourse : degreeCourses)
            if (degreeCourse.getCode().value == code)
                return true;
        return false;
    }

    @Override
    public DegreeCourse getDegreeCourseByCoordinatore(Professor professor) throws DAOException, ObjectNotFoundException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(degreeCourseFileName);
            for (List<String> line : lines)
                if (line.get(3).equals(professor.getCodiceFiscale()))
                    return (parseLine(line));
            throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
        } catch (CsvException | ResourceNotFoundException | DAOException | WrongDegreeCourseCodeException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public List<AbstractDegreeCourse> getDegreeCoursesByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException, ResourceNotFoundException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(degreeCourseFileName);
            List<AbstractDegreeCourse> newDegreeCourses = new ArrayList<>();
            for (List<String> line : lines)
                if (codes.contains(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(Integer.parseInt(line.get(0)))))
                    newDegreeCourses.add(parseLine(line));
            return newDegreeCourses;
        } catch (CsvException | WrongDegreeCourseCodeException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    private List<DegreeCourseCodeEnum> getPrerequisitesCodesByDegreeCourseCode(String code) throws DAOException, ResourceNotFoundException, WrongDegreeCourseCodeException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(prerequisiteFileName);
            List<DegreeCourseCodeEnum> codes = new ArrayList<>();
            for (List<String> line : lines)
                if (line.get(0).equals(code))
                    codes.add(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(Integer.parseInt(line.get(2))));
            return codes;
        } catch (CsvException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
}
