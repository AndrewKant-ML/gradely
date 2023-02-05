module it.uniroma2.dicii.ispw.gradely {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens it.uniroma2.dicii.ispw.gradely to javafx.fxml;
    exports it.uniroma2.dicii.ispw.gradely;
    exports it.uniroma2.dicii.ispw.gradely.session_manager;
    exports it.uniroma2.dicii.ispw.gradely.beans_general;
    exports it.uniroma2.dicii.ispw.gradely.exceptions;
    exports it.uniroma2.dicii.ispw.gradely.enums;
    exports it.uniroma2.dicii.ispw.gradely.dao_manager;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.login;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;
    opens it.uniroma2.dicii.ispw.gradely.session_manager to javafx.fxml;
    opens it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course to javafx.fxml;
    opens it.uniroma2.dicii.ispw.gradely.use_cases.login to javafx.fxml;

    exports it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries;
    opens it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries to javafx.fxml;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;
    opens it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers to javafx.fxml;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers;
    exports it.uniroma2.dicii.ispw.gradely.model.timer;
    exports it.uniroma2.dicii.ispw.gradely.model.user;
    exports it.uniroma2.dicii.ispw.gradely.model.test;
    exports it.uniroma2.dicii.ispw.gradely.model.subject_course;
    exports it.uniroma2.dicii.ispw.gradely.model.role.student;
    exports it.uniroma2.dicii.ispw.gradely.model.role.secretary;
    exports it.uniroma2.dicii.ispw.gradely.model.role.professor;
    exports it.uniroma2.dicii.ispw.gradely.model.exam;
    exports it.uniroma2.dicii.ispw.gradely.model.degree_course;
    exports it.uniroma2.dicii.ispw.gradely.model.test_result;
    exports it.uniroma2.dicii.ispw.gradely.model.role;
    exports it.uniroma2.dicii.ispw.gradely.model.exam_result;
    exports it.uniroma2.dicii.ispw.gradely.model.title;
    exports it.uniroma2.dicii.ispw.gradely.model.pending_events;

}