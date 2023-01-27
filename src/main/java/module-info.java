module it.uniroma2.dicii.ispw.gradely {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.uniroma2.dicii.ispw.gradely to javafx.fxml;
    exports it.uniroma2.dicii.ispw.gradely;
    exports it.uniroma2.dicii.ispw.gradely.session_manager;
    exports it.uniroma2.dicii.ispw.gradely.model;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.login;
    exports it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;
    opens it.uniroma2.dicii.ispw.gradely.session_manager to javafx.fxml;
    opens it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course to javafx.fxml;
}