package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SubjectCourse extends AbstractSubjectCourse {
    private String name;
    private Year academicYear;
    private Integer cfu;
    private List<AbstractSubjectCourse> prerequisites;
    private List<DegreeCourse> degreeCourses;
    private List<SubjectCourseAssignment> subjectCourseAssignments;
    private List<Exam> exams;
    private List<SubjectCourseEnrollment> enrollments;

    protected SubjectCourse(SubjectCourseCodeEnum code, String name, Year academicYear, Integer cfu) {
        super(code);
        this.name = name;
        this.academicYear = academicYear;
        this.cfu = cfu;
        this.prerequisites = new ArrayList<>();
        this.degreeCourses = new ArrayList<>();
        this.subjectCourseAssignments = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.enrollments = new ArrayList<>();
    }

    protected SubjectCourse(SubjectCourseCodeEnum code, String name, Year academicYear, Integer cfu, List<AbstractSubjectCourse> prerequisites, List<DegreeCourse> degreeCourses, List<SubjectCourseAssignment> subjectCourseAssignments) {
        super(code);
        this.name = name;
        this.academicYear = academicYear;
        this.cfu = cfu;
        this.prerequisites = prerequisites;
        this.degreeCourses = degreeCourses;
        this.subjectCourseAssignments = subjectCourseAssignments;
        this.exams = new ArrayList<>();
        this.enrollments = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Year getAcademicYear(){
        return academicYear;
    }

    public void setAcademicYear(Year academicYear){
        this.academicYear = academicYear;
    }

    public Integer getCfu(){
        return cfu;
    }

    public void setCfu(Integer cfu){
        this.cfu = cfu;
    }

    public List<AbstractSubjectCourse> getPrerequisites(){
        return prerequisites;
    }

    public void setPrerequisites(List<AbstractSubjectCourse> prerequisites){
        this.prerequisites = prerequisites;
    }
    public void addPrerequisites(List<AbstractSubjectCourse> prerequisites){
        this.prerequisites.addAll(prerequisites);
    }

    public List<DegreeCourse> getDegreeCourses(){
        return degreeCourses;
    }

    public void setDegreeCourses(List<DegreeCourse> degreeCourses){
        this.degreeCourses = degreeCourses;
    }

    public void addDegreeCourses(List<DegreeCourse> degreeCourse){
        this.degreeCourses.addAll(degreeCourse);
    }
    public List<SubjectCourseAssignment> getSubjeectCourseAssignments(){
        return subjectCourseAssignments;
    }

    public void setSubjectCourseAssignments(List<SubjectCourseAssignment> subjectCourseAssignments){
        this.subjectCourseAssignments = subjectCourseAssignments;
    }
    public void addSubjectCourseAssignment(List<SubjectCourseAssignment> subjectCourseAssignments){
        this.subjectCourseAssignments.addAll(subjectCourseAssignments);
    }
    public List<Exam> getExams(){
        return exams;
    }

    public void setExams(List<Exam> exams){
        this.exams = exams;
    }

    public void addExam(Exam exam){
        this.exams.add(exam);
    }

    public List<SubjectCourseEnrollment> getEnrollments(){
        return enrollments;
    }

    public void setEnrollments(List<SubjectCourseEnrollment> enrollments){
        this.enrollments = enrollments;
    }
    public void addEnrollment(List<SubjectCourseEnrollment> subjectCourseEnrollment){
        this.enrollments.addAll(subjectCourseEnrollment);
    }
}
