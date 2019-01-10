package model.entity;

import java.io.Serializable;

/**
 * Class {@code Answer} represents the Answer model. This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Alex Naumenko
 */
public class Answer implements Serializable {

    /**
     * Id of Answer.
     */
    private int id;

    /**
     * Test id of Answer.
     */
    private int testId;

    /**
     * Question of Answer.
     */
    private String question;

    /**
     * Correct answer of Answer.
     */
    private String correctAnswer;

    /**
     * User answer of Answer.
     */
    private String userAnswer;

    /**
     * Status of Answer.
     */
    private AnswerStatus status;

    /**
     * Default constructor.
     */
    public Answer() {
    }

    /**
     * Constructs answer with answer builder parameter.
     *
     * @param builder
     * @see model.entity.Answer.Builder
     */

    public Answer(Builder builder) {
        id = builder.id;
        testId = builder.testId;
        question = builder.question;
        correctAnswer = builder.correctAnswer;
        userAnswer = builder.userAnswer;
        status = builder.status;
    }

    /**
     * Returns id of Answer.
     *
     * @return id of Answer.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns test id of Answer.
     *
     * @return id of Answer.
     */
    public int getTestId() {
        return testId;
    }

    /**
     * Returns question of Answer.
     *
     * @return id of Answer.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns id of Answer.
     *
     * @return id of Answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns id of Answer.
     *
     * @return id of Answer.
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * Returns id of Answer.
     *
     * @return id of Answer.
     */
    public AnswerStatus getStatus() {
        return status;
    }

    /**
     * Indicates whether some other answer is "equal to" this one.
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

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (testId != answer.testId) return false;
        if (question != null ? !question.equals(answer.question) : answer.question != null) return false;
        if (correctAnswer != null ? !correctAnswer.equals(answer.correctAnswer) : answer.correctAnswer != null)
            return false;
        if (userAnswer != null ? !userAnswer.equals(answer.userAnswer) : answer.userAnswer != null) return false;
        return status == answer.status;
    }

    /**
     * Returns a hash code value for the answer.
     *
     * @return a hash code value for this answer.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (correctAnswer != null ? correctAnswer.hashCode() : 0);
        result = 31 * result + (userAnswer != null ? userAnswer.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of the answer.
     *
     * @return a string representation of the answer.
     */
    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", testId=" + testId +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", userAnswer='" + userAnswer + '\'' +
                ", status=" + status +
                '}';
    }

    /**
     * Class {@code Builder} is used to separate the answer's construction from its representation.
     */
    public static class Builder {

        /**
         * Id of answer builder.
         */
        private int id;

        /**
         * Test id of answer builder.
         */
        private int testId;

        /**
         * Question of answer builder.
         */
        private String question;

        /**
         * Correct answer of answer builder.
         */
        private String correctAnswer;

        /**
         * User answer of answer builder.
         */
        private String userAnswer;

        /**
         * Status of answer builder.
         */
        private AnswerStatus status;

        /**
         * Set new id to answer builder.
         *
         * @param id of answer builder.
         * @return answer builder.
         */
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Set new test id to answer builder.
         *
         * @param testId of answer builder.
         * @return answer builder.
         */
        public Builder setTestId(int testId) {
            this.testId = testId;
            return this;
        }

        /**
         * Set new question to answer builder.
         *
         * @param question of answer builder.
         * @return answer builder.
         */
        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        /**
         * Set new correct answer to answer builder.
         *
         * @param correctAnswer of answer builder.
         * @return answer builder.
         */
        public Builder setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        /**
         * Set new user answer to answer builder.
         *
         * @param userAnswer of answer builder.
         * @return answer builder.
         */
        public Builder setUserAnswer(String userAnswer) {
            this.userAnswer = userAnswer;
            return this;
        }

        /**
         * Set new status to answer builder.
         *
         * @param status of answer builder.
         * @return answer builder.
         */
        public Builder setStatus(AnswerStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Returns the answer builder.
         *
         * @return the answer builder
         */
        public Answer build() {
            return new Answer(this);
        }
    }

    /**
     * Class {@code AnswerStatus} defines status of the answer such as correct and incorrect.
     */
    public enum AnswerStatus {
        CORRECT,
        INCORRECT
    }
}
