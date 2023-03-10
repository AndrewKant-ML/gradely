package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.RoomEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Exam {
    private UUID id;
    private LocalDate enrollmentStartDate;
    private LocalDate enrollmentEndDate;
    private LocalDate examinationDate;
    private RoomEnum room;
    private AppelloEnum appello;
    private SessionEnum session;
    private SubjectCourse subjectCourse;
    private Boolean gradable;
    private Boolean verbalizable;
    private LocalDate verbaleDate;
    private Integer verbaleNumber;
    private List<ExamEnrollment> enrollments;

    public Exam(LocalDate enrollmentStartDate, LocalDate enrollmentEndDate, LocalDate examinationDate, RoomEnum room, AppelloEnum appello, SessionEnum session, SubjectCourse subjectCourse){
        this.enrollmentStartDate = enrollmentStartDate;
        this.enrollmentEndDate = enrollmentEndDate;
        this.examinationDate = examinationDate;
        this.room = room;
        this.appello = appello;
        this.session = session;
        this.subjectCourse = subjectCourse;
        this.verbalizable = true;
    }

    public Exam(UUID id, LocalDate enrollmentStartDate, LocalDate enrollmentEndDate, LocalDate examinationDate, RoomEnum room, AppelloEnum appello, SessionEnum session, SubjectCourse subjectCourse){
        this.id = id;
        this.enrollmentStartDate = enrollmentStartDate;
        this.enrollmentEndDate = enrollmentEndDate;
        this.examinationDate = examinationDate;
        this.room = room;
        this.appello = appello;
        this.session = session;
        this.subjectCourse = subjectCourse;
    }
    public Exam(UUID id, LocalDate enrollmentStartDate, LocalDate enrollmentEndDate, LocalDate examinationDate, RoomEnum room, AppelloEnum appello, SessionEnum session, SubjectCourse subjectCourse, Boolean gradable, Boolean verbalizable, LocalDate verbaleDate, Integer verbaleNumber){
        this.id = id;
        this.enrollmentStartDate = enrollmentStartDate;
        this.enrollmentEndDate = enrollmentEndDate;
        this.examinationDate = examinationDate;
        this.room = room;
        this.appello = appello;
        this.session = session;
        this.subjectCourse = subjectCourse;
        this.gradable = gradable;
        this.verbalizable = verbalizable;
        this.verbaleDate = verbaleDate;
        this.verbaleNumber = verbaleNumber;
    }
    public Exam(UUID id, LocalDate enrollmentStartDate, LocalDate enrollmentEndDate, LocalDate examinationDate, RoomEnum room, AppelloEnum appello, SessionEnum session, SubjectCourse subjectCourse, Boolean gradable, Boolean verbalizable, LocalDate verbaleDate, Integer verbaleNumber, List<ExamEnrollment> enrollments){
        this.id = id;
        this.enrollmentStartDate = enrollmentStartDate;
        this.enrollmentEndDate = enrollmentEndDate;
        this.examinationDate = examinationDate;
        this.room = room;
        this.appello = appello;
        this.session = session;
        this.subjectCourse = subjectCourse;
        this.gradable = gradable;
        this.verbalizable = verbalizable;
        this.verbaleDate = verbaleDate;
        this.verbaleNumber = verbaleNumber;
        this.enrollments = enrollments;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getEnrollmentStartDate(){
        return enrollmentStartDate;
    }

    public void setEnrollmentStartDate(LocalDate enrollmentStartDate){
        this.enrollmentStartDate = enrollmentStartDate;
    }

    public LocalDate getEnrollmentEndDate(){
        return enrollmentEndDate;
    }

    public void setEnrollmentEndDate(LocalDate enrollmentEndDate){
        this.enrollmentEndDate = enrollmentEndDate;
    }

    public LocalDate getExaminationDate(){
        return examinationDate;
    }

    public void setExaminationDate(LocalDate examinationDate){
        this.examinationDate = examinationDate;
    }

    public RoomEnum getRoom(){
        return room;
    }

    public void setRoom(RoomEnum room){
        this.room = room;
    }

    public AppelloEnum getAppello(){
        return appello;
    }

    public void setAppello(AppelloEnum appello){
        this.appello = appello;
    }

    public SessionEnum getSession(){
        return session;
    }

    public void setSession(SessionEnum session){
        this.session = session;
    }

    public SubjectCourse getSubjectCourse(){
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourse subjectCourse){
        this.subjectCourse = subjectCourse;
    }

    public Boolean getGradable(){
        return gradable;
    }

    public void setGradable(Boolean gradable){
        this.gradable = gradable;
    }

    public Boolean getVerbalizable(){
        return verbalizable;
    }

    public void setVerbalizable(Boolean verbalizable){
        this.verbalizable = verbalizable;
    }

    public LocalDate getVerbaleDate(){
        return verbaleDate;
    }

    public void setVerbaleDate(LocalDate verbaleDate){
        this.verbaleDate = verbaleDate;
    }

    public Integer getVerbaleNumber(){
        return verbaleNumber;
    }

    public void setVerbaleNumber(Integer verbaleNumber){
        this.verbaleNumber = verbaleNumber;
    }

    public List<ExamEnrollment> getEnrollments(){
        return enrollments;
    }

    public void setEnrollments(List<ExamEnrollment> enrollments){
        this.enrollments = enrollments;
    }
}
