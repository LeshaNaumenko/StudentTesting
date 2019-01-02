package model.entity;

public class TestDTO {
    private int testId;
    private String courseName;
    private String themeName;
    private String date;
    private String startTime;
    private String endTime;
    private Integer themeTime;
    private String userTime;
    private Integer passingGrade;
    private Integer grade;
    private Test.Status status;

    public int getTestId() {
        return testId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getThemeTime() {
        return themeTime;
    }

    public String getUserTime() {
        return userTime;
    }

    public Integer getPassingGrade() {
        return passingGrade;
    }

    public Integer getGrade() {
        return grade;
    }

    public Test.Status getStatus() {
        return status;
    }

    public TestDTO(TestBuilder testBuilder) {
        this.testId = testBuilder.testId;
        this.courseName = testBuilder.courseName;
        this.themeName = testBuilder.themeName;
        this.date = testBuilder.date;
        this.startTime = testBuilder.startTime;
        this.endTime = testBuilder.endTime;
        this.themeTime = testBuilder.themeTime;
        this.userTime = testBuilder.userTime;
        this.passingGrade = testBuilder.passingGrade;
        this.grade = testBuilder.grade;
        this.status = testBuilder.status;
    }

    public static class TestBuilder{
        private int testId;
        private String courseName;
        private String themeName;
        private String date;
        private String startTime;
        private String endTime;
        private Integer themeTime;
        private String userTime;
        private Integer passingGrade;
        private Integer grade;
        private Test.Status status;

        public TestBuilder setTestId(int testId) {
            this.testId = testId;
            return this;
        }

        public TestBuilder setCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public TestBuilder setThemeName(String themeName) {
            this.themeName = themeName;
            return this;
        }

        public TestBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public TestBuilder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public TestBuilder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public TestBuilder setThemeTime(Integer themeTime) {
            this.themeTime = themeTime;
            return this;
        }

        public TestBuilder setUserTime(String userTime) {
            this.userTime = userTime;
            return this;
        }

        public TestBuilder setPassingGrade(Integer passingGrade) {
            this.passingGrade = passingGrade;
            return this;
        }

        public TestBuilder setGrade(Integer grade) {
            this.grade = grade;
            return this;
        }

        public TestBuilder setStatus(Test.Status status) {
            this.status = status;
            return this;
        }
        public TestDTO build(){
            return new TestDTO(this);
        }
    }



}
