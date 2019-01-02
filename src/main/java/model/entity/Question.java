package model.entity;

import java.io.Serializable;

public class Question implements Serializable {

    private Integer id;
    private Integer theme_id;
    private String descriptionOfQuestion;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct_option;

    public Question() {
    }

    public Question(Integer id, Integer theme_id, String descriptionOfQuestion, String option1, String option2, String option3, String option4, String correct_option) {
        this.id = id;
        this.theme_id = theme_id;
        this.descriptionOfQuestion = descriptionOfQuestion;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct_option = correct_option;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    public String getDescriptionOfQuestion() {
        return descriptionOfQuestion;
    }

    public void setDescriptionOfQuestion(String descriptionOfQuestion) {
        this.descriptionOfQuestion = descriptionOfQuestion;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(String correct_option) {
        this.correct_option = correct_option;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", theme_id=" + theme_id +
                ", descriptionOfQuestion='" + descriptionOfQuestion + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", correct_option='" + correct_option + '\'' +
                '}';
    }
}
