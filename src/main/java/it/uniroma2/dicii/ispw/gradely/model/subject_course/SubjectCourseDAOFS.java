package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.time.Year;
import java.util.List;


public class SubjectCourseDAOFS extends SubjectCourseDAOInterface {

    private final String subjectCourseFileName = "subject_course";
    private final String prerequisiteFileName = "subject_course_prerequisite";

    private SubjectCourseDAOFS() {
        super();
    }

    public static synchronized SubjectCourseDAOInterface getInstance() {
        if (instance == null) {
            instance = new SubjectCourseDAOFS();
        }
        return instance;
    }

    @Override
    public SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws DAOException, ObjectNotFoundException, ResourceNotFoundException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(subjectCourseFileName);
            SubjectCourseCodeEnum scCode;
            String scName;
            Integer scCfu;
            Year scAy;
            for (List<String> line : lines) {
                scCode = SubjectCourseCodeEnum.getSubjectCourseCodeByValue(Integer.parseInt(line.get(0)));
                if (scCode == null)
                    throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
                scName = line.get(1);
                scCfu = Integer.parseInt(line.get(2));
                scAy = Year.of(Integer.parseInt(line.get(3)));
                if (scCode.equals(code) && scName.equals(name) && scCfu.equals(cfu) && scAy.compareTo(academicYear) == 0)
                    return new SubjectCourse(scCode, scName, scAy, scCfu);
            }
            throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
        } catch (CsvException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(SubjectCourse subjectCourse){

    }

    @Override
    public void cancel(SubjectCourse subjectCourse){

    }

    @Override
    public void update(SubjectCourse subjectCourse){

    }



}
