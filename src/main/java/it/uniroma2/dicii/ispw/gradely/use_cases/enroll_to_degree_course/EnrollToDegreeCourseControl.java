package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.beans.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.Student;

import java.util.List;

public class EnrollToDegreeCourseControl {


    public List<DegreeCourseBean> getDegreeCourses(Student student) {
        List<DegreeCourse> degreeCourses;
        DegreeCourseLazyFactory lazyFactory = DegreeCourseLazyFactory.getInstance();

        return null;
    }
}
