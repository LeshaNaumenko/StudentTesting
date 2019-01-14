package model.entity;

/**
 * Class {@code TestInfo} represents the TestInfo model. This class contains a portion of the fields that belong
 * to the {@code Test) and the {@code Themes} classes and is used to transfer data to the client.
 * This model class can be used throughout all layers, the data layer, the controller layer and the view layer.
 *
 * @author Alex Naumenko
 */
public class TestInfo {

    /**
     * Id of TestInfo.
     */
    private int testId;

    /**
     * Course name of TestInfo.
     */
    private String courseName;

    /**
     * Theme name of TestInfo.
     */
    private String themeName;

    /**
     * Date of TestInfo.
     */
    private String date;

    /**
     * Start time of TestInfo.
     */
    private String startTime;

    /**
     * End time of TestInfo.
     */
    private String endTime;

    /**
     * Theme time of TestInfo.
     */
    private Integer themeTime;

    /**
     * User time of TestInfo.
     */
    private String userTime;

    /**
     * Passing grade of TestInfo.
     */
    private Integer passingGrade;

    /**
     * Grade of TestInfo.
     */
    private Integer grade;

    /**
     * Status of TestInfo.
     */
    private Test.Status status;

    /**
     * Default constructor.
     */
    public TestInfo() {
    }

    /**
     * Constructs test data transfer object with test dto builder parameter.
     *
     * @param testDtoBuilder
     * @see TestInfo.TestDTOBuilder
     */
    public TestInfo(TestDTOBuilder testDtoBuilder) {
        this.testId = testDtoBuilder.testId;
        this.courseName = testDtoBuilder.courseName;
        this.themeName = testDtoBuilder.themeName;
        this.date = testDtoBuilder.date;
        this.startTime = testDtoBuilder.startTime;
        this.endTime = testDtoBuilder.endTime;
        this.themeTime = testDtoBuilder.themeTime;
        this.userTime = testDtoBuilder.userTime;
        this.passingGrade = testDtoBuilder.passingGrade;
        this.grade = testDtoBuilder.grade;
        this.status = testDtoBuilder.status;
    }

    /**
     * Returns test id of TestInfo.
     *
     * @return test id of TestInfo.
     */
    public int getTestId() {
        return testId;
    }

    /**
     * Returns course name of TestInfo.
     *
     * @return course name of TestInfo.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Returns theme name of TestInfo.
     *
     * @return theme name of TestInfo.
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * Returns date of TestInfo.
     *
     * @return date of TestInfo.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns start time of TestInfo.
     *
     * @return start time of TestInfo.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Returns end time of TestInfo.
     *
     * @return end time of TestInfo.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Returns time of TestInfo.
     *
     * @return time of TestInfo.
     */
    public Integer getThemeTime() {
        return themeTime;
    }

    /**
     * Returns user time of TestInfo.
     *
     * @return user time of TestInfo.
     */
    public String getUserTime() {
        return userTime;
    }

    /**
     * Returns passing grade of TestInfo.
     *
     * @return passing grade of TestInfo.
     */
    public Integer getPassingGrade() {
        return passingGrade;
    }

    /**
     * Returns grade of TestInfo.
     *
     * @return grade of TestInfo.
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Returns status of TestInfo.
     *
     * @return status of TestInfo.
     */
    public Test.Status getStatus() {
        return status;
    }

    /**
     * Class {@code TestDTOBuilder} is used to separate the testDTO's construction from its representation.
     */
    public static class TestDTOBuilder {

        /**
         * Test id of testDTO builder.
         */
        private int testId;

        /**
         * Course name id of testDTO builder.
         */
        private String courseName;

        /**
         * Theme name id of testDTO builder.
         */
        private String themeName;

        /**
         * Date id of testDTO builder.
         */
        private String date;

        /**
         * Start time id of testDTO builder.
         */
        private String startTime;

        /**
         * End time id of testDTO builder.
         */
        private String endTime;

        /**
         * Theme time id of testDTO builder.
         */
        private Integer themeTime;

        /**
         * User time id of testDTO builder.
         */
        private String userTime;

        /**
         * Passing grade id of testDTO builder.
         */
        private Integer passingGrade;

        /**
         * Grade id of testDTO builder.
         */
        private Integer grade;

        /**
         * Status id of testDTO builder.
         */
        private Test.Status status;

        /**
         * Set new test id to testDTO builder.
         *
         * @param testId of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setTestId(int testId) {
            this.testId = testId;
            return this;
        }

        /**
         * Set new course name to testDTO builder.
         *
         * @param courseName of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        /**
         * Set new theme name to testDTO builder.
         *
         * @param themeName of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setThemeName(String themeName) {
            this.themeName = themeName;
            return this;
        }

        /**
         * Set new date to testDTO builder.
         *
         * @param date of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        /**
         * Set new start time to testDTO builder.
         *
         * @param startTime of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * Set new end time to testDTO builder.
         *
         * @param endTime of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * Set new theme time to testDTO builder.
         *
         * @param themeTime of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setThemeTime(Integer themeTime) {
            this.themeTime = themeTime;
            return this;
        }

        /**
         * Set new user time to testDTO builder.
         *
         * @param userTime of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setUserTime(String userTime) {
            this.userTime = userTime;
            return this;
        }

        /**
         * Set new passing grade to testDTO builder.
         *
         * @param passingGrade of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setPassingGrade(Integer passingGrade) {
            this.passingGrade = passingGrade;
            return this;
        }

        /**
         * Set new grade to testDTO builder.
         *
         * @param grade of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setGrade(Integer grade) {
            this.grade = grade;
            return this;
        }

        /**
         * Set new status to testDTO builder.
         *
         * @param status of testDTO builder.
         * @return test builder.
         */
        public TestDTOBuilder setStatus(Test.Status status) {
            this.status = status;
            return this;
        }

        /**
         * Returns the testDTO builder.
         *
         * @return the testDTO builder
         */
        public TestInfo build() {
            return new TestInfo(this);
        }
    }


}
