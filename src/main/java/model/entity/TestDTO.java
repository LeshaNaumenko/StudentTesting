package model.entity;

/**
 * Class {@code TestDTO} represents the TestDTO model. This class contains a portion of the fields that belong
 * to the {@code Test) and the {@code Themes} classes and is used to transfer data to the client.
 * This model class can be used throughout all layers, the data layer, the controller layer and the view layer.
 *
 * @author Alex Naumenko
 */
public class TestDTO {

    /**
     * Id of TestDTO.
     */
    private int testId;

    /**
     * Course name of TestDTO.
     */
    private String courseName;

    /**
     * Theme name of TestDTO.
     */
    private String themeName;

    /**
     * Date of TestDTO.
     */
    private String date;

    /**
     * Start time of TestDTO.
     */
    private String startTime;

    /**
     * End time of TestDTO.
     */
    private String endTime;

    /**
     * Theme time of TestDTO.
     */
    private Integer themeTime;

    /**
     * User time of TestDTO.
     */
    private String userTime;

    /**
     * Passing grade of TestDTO.
     */
    private Integer passingGrade;

    /**
     * Grade of TestDTO.
     */
    private Integer grade;

    /**
     * Status of TestDTO.
     */
    private Test.Status status;

    /**
     * Default constructor.
     */
    public TestDTO() {
    }

    /**
     * Constructs test data transfer object with test dto builder parameter.
     *
     * @param testDtoBuilder
     * @see model.entity.TestDTO.TestDTOBuilder
     */
    public TestDTO(TestDTOBuilder testDtoBuilder) {
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
     * Returns test id of TestDTO.
     *
     * @return test id of TestDTO.
     */
    public int getTestId() {
        return testId;
    }

    /**
     * Returns course name of TestDTO.
     *
     * @return course name of TestDTO.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Returns theme name of TestDTO.
     *
     * @return theme name of TestDTO.
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * Returns date of TestDTO.
     *
     * @return date of TestDTO.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns start time of TestDTO.
     *
     * @return start time of TestDTO.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Returns end time of TestDTO.
     *
     * @return end time of TestDTO.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Returns time of TestDTO.
     *
     * @return time of TestDTO.
     */
    public Integer getThemeTime() {
        return themeTime;
    }

    /**
     * Returns user time of TestDTO.
     *
     * @return user time of TestDTO.
     */
    public String getUserTime() {
        return userTime;
    }

    /**
     * Returns passing grade of TestDTO.
     *
     * @return passing grade of TestDTO.
     */
    public Integer getPassingGrade() {
        return passingGrade;
    }

    /**
     * Returns grade of TestDTO.
     *
     * @return grade of TestDTO.
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Returns status of TestDTO.
     *
     * @return status of TestDTO.
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
        public TestDTO build() {
            return new TestDTO(this);
        }
    }


}
