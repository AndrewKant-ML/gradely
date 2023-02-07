package it.uniroma2.dicii.ispw.gradely.beans_general;


public class SubjectCourseAssignmentBean {
    private SubjectCourseBean subjectCourse;
    private ProfessorBean professor;

    public SubjectCourseAssignmentBean(SubjectCourseBean subjectCourse, ProfessorBean professor) {
        this.subjectCourse = subjectCourse;
        this.professor = professor;
    }

    public SubjectCourseBean getSubjectCourse() {
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourseBean subjectCourse) {
        this.subjectCourse = subjectCourse;
    }

    public ProfessorBean getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorBean professor) {
        this.professor = professor;
    }
}
