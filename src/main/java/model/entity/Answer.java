package model.entity;

import java.io.Serializable;

public class Answer implements Serializable {
    private int id;
    private int testId;
    private String question;
    private String correctAnswer;
    private String userAnswer;
    private AnswerStatus status;

    public Answer() {
    }

    public Answer(Builder builder) {
          id  = builder.id;
          testId = builder.testId;
          question = builder.question;
          correctAnswer = builder.correctAnswer;
          userAnswer = builder.userAnswer;
          status = builder.status;
    }

    public int getId() {
        return id;
    }

    public int getTestId() {
        return testId;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public AnswerStatus getStatus() {
        return status;
    }

    public enum AnswerStatus{
        CORRECT,
        INCORRECT
    }

    public static class Builder{
        private int id;
        private int testId;
        private String question;
        private String correctAnswer;
        private String userAnswer;
        private AnswerStatus status;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTestId(int testId) {
            this.testId = testId;
            return this;
        }

        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Builder setUserAnswer(String userAnswer) {
            this.userAnswer = userAnswer;
            return this;
        }

        public Builder setStatus(AnswerStatus status) {
            this.status = status;
            return this;
        }
        public Answer build(){
            return new Answer(this);
        }
    }


}
