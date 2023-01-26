package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;

import java.util.List;

public class DegreeCourseLazyFactory {

    private static class DegreeCourseLazyFactoryHolder {
        public final static DegreeCourseLazyFactory instance = new DegreeCourseLazyFactory();
    }

    private DegreeCourseLazyFactory() {}

    public static DegreeCourseLazyFactory getInstance() {
        return DegreeCourseLazyFactoryHolder.instance;
    }

    public List<DegreeCourse> getDegreeCourses() {
        return null;
    }
}
