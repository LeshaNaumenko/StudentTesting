package model.entity;

import java.io.Serializable;

/**
 * Class {@code Question} represents the Question model. This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Alex Naumenko
 */
public class Question implements Serializable {

    /**
     * Id of Question.
     */
    private Integer id;

    /**
     * Theme id of Question.
     */
    private Integer themeId;

    /**
     * Description of question.
     */
    private String descriptionOfQuestion;

    /**
     * First option of Question.
     */
    private String option1;

    /**
     * Second Option of Question.
     */
    private String option2;

    /**
     * Third option of Question.
     */
    private String option3;

    /**
     * Fourth option of Question.
     */
    private String option4;

    /**
     * Correct option of Question.
     */
    private String correctOption;

    /**
     * Default constructor.
     */
    public Question() {
    }

    /**
     * Constructor of the question with parameters.
     *
     * @param id
     * @param themeId
     * @param descriptionOfQuestion
     * @param option1
     * @param option2
     * @param option3
     * @param option4
     * @param correctOption
     */
    public Question(Integer id, Integer themeId, String descriptionOfQuestion, String option1, String option2, String option3, String option4, String correctOption) {
        this.id = id;
        this.themeId = themeId;
        this.descriptionOfQuestion = descriptionOfQuestion;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
    }

    /**
     * Returns id of Question.
     *
     * @return id of Question.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new id to Question
     *
     * @param id of Question
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns theme id of Question.
     *
     * @return theme id of Question.
     */
    public Integer getThemeId() {
        return themeId;
    }

    /**
     * Set new theme id to Question
     *
     * @param themeId of Question
     */
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    /**
     * Returns description of question of class {@code Question}.
     *
     * @return description of question of class {@code Question}.
     */
    public String getDescriptionOfQuestion() {
        return descriptionOfQuestion;
    }

    /**
     * Set new description of question to class {@code Question}
     *
     * @param descriptionOfQuestion of Question
     */
    public void setDescriptionOfQuestion(String descriptionOfQuestion) {
        this.descriptionOfQuestion = descriptionOfQuestion;
    }

    /**
     * Returns first option of Question.
     *
     * @return first option of Question.
     */
    public String getOption1() {
        return option1;
    }

    /**
     * Set new first option to Question
     *
     * @param option1 of Question
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * Returns second option of Question.
     *
     * @return second option of Question.
     */
    public String getOption2() {
        return option2;
    }

    /**
     * Set new second option to Question
     *
     * @param option2 of Question
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * Returns third option of Question.
     *
     * @return third option of Question.
     */
    public String getOption3() {
        return option3;
    }

    /**
     * Set new third option to Question
     *
     * @param option3 of Question
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * Returns fourth option of Question.
     *
     * @return fourth option of Question.
     */
    public String getOption4() {
        return option4;
    }

    /**
     * Set new fourth option to Question
     *
     * @param option4 of Question
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * Returns correct option of Question.
     *
     * @return correct option of Question.
     */
    public String getCorrectOption() {
        return correctOption;
    }

    /**
     * Set new correct option to Question
     *
     * @param correctOption of Question
     */
    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    /**
     * Indicates whether some other question is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same
     * as the o argument; {@code false} otherwise.
     *
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (themeId != null ? !themeId.equals(question.themeId) : question.themeId != null) return false;
        if (descriptionOfQuestion != null ? !descriptionOfQuestion.equals(question.descriptionOfQuestion) : question.descriptionOfQuestion != null)
            return false;
        if (option1 != null ? !option1.equals(question.option1) : question.option1 != null) return false;
        if (option2 != null ? !option2.equals(question.option2) : question.option2 != null) return false;
        if (option3 != null ? !option3.equals(question.option3) : question.option3 != null) return false;
        if (option4 != null ? !option4.equals(question.option4) : question.option4 != null) return false;
        return correctOption != null ? correctOption.equals(question.correctOption) : question.correctOption == null;
    }

    /**
     * Returns a hash code value for the question.
     *
     * @return a hash code value for this question.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (themeId != null ? themeId.hashCode() : 0);
        result = 31 * result + (descriptionOfQuestion != null ? descriptionOfQuestion.hashCode() : 0);
        result = 31 * result + (option1 != null ? option1.hashCode() : 0);
        result = 31 * result + (option2 != null ? option2.hashCode() : 0);
        result = 31 * result + (option3 != null ? option3.hashCode() : 0);
        result = 31 * result + (option4 != null ? option4.hashCode() : 0);
        result = 31 * result + (correctOption != null ? correctOption.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of the question.
     *
     * @return a string representation of the question.
     */
    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", themeId=" + themeId +
                ", descriptionOfQuestion='" + descriptionOfQuestion + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", correctOption='" + correctOption + '\'' +
                '}';
    }
}
