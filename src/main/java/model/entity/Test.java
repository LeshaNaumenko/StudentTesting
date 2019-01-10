package model.entity;

import java.io.Serializable;

/**
 * Class {@code Test} represents the Test model. This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Alex Naumenko
 */
public class Test implements Serializable {

    /**
     * Id of Test.
     */
    private Integer id;

    /**
     * User id of Test.
     */
    private Integer userId;

    /**
     * Theme id of Test.
     */
    private Integer themeId;

    /**
     * Status of Test.
     */
    private Status status;

    /**
     * Grade of Test.
     */
    private Integer grade;

    /**
     * Start time of Test.
     */
    private String startTime;

    /**
     * End time of Test.
     */
    private String endTime;

    /**
     * User time of Test.
     */
    private String userTime;

    /**
     * Date of Test.
     */
    private String date;

    /**
     * Default constructor.
     */
    public Test() {
    }

    /**
     * Constructs test with test builder parameter.
     *
     * @param builder
     * @see model.entity.Test.Builder
     */
    public Test(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        themeId = builder.themeId;
        status = builder.status;
        grade = builder.grade;
        startTime = builder.startTime;
        endTime = builder.endTime;
        userTime = builder.testTime;
        date = builder.date;
    }

    /**
     * Returns id of Test.
     *
     * @return id of Test.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns user id of Test.
     *
     * @return user id of Test.
     */
    public Integer getUserId() {
        return userId;
    }


    /**
     * Returns theme id of Test.
     *
     * @return theme id of Test.
     */
    public Integer getThemeId() {
        return themeId;
    }


    /**
     * Returns status of Test.
     *
     * @return status of Test.
     */
    public Status getStatus() {
        return status;
    }


    /**
     * Returns grade of Test.
     *
     * @return grade of Test.
     */
    public Integer getGrade() {
        return grade;
    }


    /**
     * Returns start time of Test.
     *
     * @return start time of Test.
     */
    public String getStartTime() {
        return startTime;
    }


    /**
     * Returns end time of Test.
     *
     * @return end time of Test.
     */
    public String getEndTime() {
        return endTime;
    }


    /**
     * Returns user time of Test.
     *
     * @return user time of Test.
     */
    public String getUserTime() {
        return userTime;
    }


    /**
     * Returns date of Test.
     *
     * @return date of Test.
     */
    public String getDate() {
        return date;
    }

    /**
     * Indicates whether some other test is "equal to" this one.
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

        Test test = (Test) o;

        if (id != null ? !id.equals(test.id) : test.id != null) return false;
        if (userId != null ? !userId.equals(test.userId) : test.userId != null) return false;
        if (themeId != null ? !themeId.equals(test.themeId) : test.themeId != null) return false;
        if (status != test.status) return false;
        if (grade != null ? !grade.equals(test.grade) : test.grade != null) return false;
        if (startTime != null ? !startTime.equals(test.startTime) : test.startTime != null) return false;
        if (endTime != null ? !endTime.equals(test.endTime) : test.endTime != null) return false;
        if (userTime != null ? !userTime.equals(test.userTime) : test.userTime != null) return false;
        return date != null ? date.equals(test.date) : test.date == null;
    }

    /**
     * Returns a hash code value for the test.
     *
     * @return a hash code value for this test.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (themeId != null ? themeId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (userTime != null ? userTime.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of the test.
     *
     * @return a string representation of the test.
     */
    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", userId=" + userId +
                ", themeId=" + themeId +
                ", status=" + status +
                ", grade=" + grade +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", userTime='" + userTime + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    /**
     * Class {@code Builder} is used to separate the test's construction from its representation.
     */
    public static class Builder {
        /**
         * Id of test builder.
         */
        private Integer id;

        /**
         * User id of test builder.
         */
        private Integer userId;

        /**
         * Theme id of test builder.
         */
        private Integer themeId;

        /**
         * Status of test builder.
         */
        private Status status;

        /**
         * Grade of test builder.
         */
        private Integer grade;

        /**
         * Start time of test builder.
         */
        private String startTime;

        /**
         * End time of test builder.
         */
        private String endTime;

        /**
         * Test time of test builder.
         */
        private String testTime;

        /**
         * Date of test builder.
         */
        private String date;

        /**
         * Set new id to test builder.
         *
         * @param id of test builder.
         * @return test builder.
         */
        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

         /**
         * Set new user id to test builder.
         *
         * @param userId of test builder.
         * @return test builder.
         */
        public Builder setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

         /**
         * Set new theme id to test builder.
         *
         * @param themeId of test builder.
         * @return test builder.
         */
        public Builder setThemeId(Integer themeId) {
            this.themeId = themeId;
            return this;
        }

         /**
         * Set new status to test builder.
         *
         * @param status of test builder.
         * @return test builder.
         */
        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

         /**
         * Set new grade to test builder.
         *
         * @param grade of test builder.
         * @return test builder.
         */
        public Builder setGrade(Integer grade) {
            this.grade = grade;
            return this;
        }

         /**
         * Set new start time to test builder.
         *
         * @param startTime of test builder.
         * @return test builder.
         */
        public Builder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

         /**
         * Set new end time to test builder.
         *
         * @param endTime of test builder.
         * @return test builder.
         */
        public Builder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

         /**
         * Set new test time to test builder.
         *
         * @param testTime of test builder.
         * @return test builder.
         */
        public Builder setUserTime(String testTime) {
            this.testTime = testTime;
            return this;
        }

         /**
         * Set new date to test builder.
         *
         * @param date of test builder.
         * @return test builder.
         */
        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        /**
         * Returns the test builder.
         *
         * @return the test builder
         */
        public Test build() {
            return new Test(this);
        }
    }

    /**
     * Class {@code Status} defines status of the test such as passed and failed.
     */
    public enum Status {
        PASSED,
        FAILED
    }
}


