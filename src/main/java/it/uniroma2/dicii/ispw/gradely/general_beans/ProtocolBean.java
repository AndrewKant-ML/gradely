package it.uniroma2.dicii.ispw.gradely.general_beans;

public class ProtocolBean {
    private ExamBean examBean;
    private Integer verbaleNumber;

    public ProtocolBean(ExamBean examBean, Integer verbaleNumber) {
        this.examBean = examBean;
        this.verbaleNumber = verbaleNumber;
    }

    public ExamBean getExamBean() {
        return examBean;
    }

    public void setExamBean(ExamBean examBean) {
        this.examBean = examBean;
    }

    public Integer getVerbaleNumber() {
        return verbaleNumber;
    }

    public void setVerbaleNumber(Integer verbaleNumber) {
        this.verbaleNumber = verbaleNumber;
    }
}
