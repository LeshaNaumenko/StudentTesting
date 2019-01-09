package model.entity;
import java.io.Serializable;

public class Test implements Serializable {

    private Integer id;
    private Integer user_id;
    private Integer theme_id;
    private Status status;
    private Integer grade;
    private String start_time;
    private String end_time;
    private String test_time;
    private String date;

    public Test() {
    }

    public Test(Integer id, Integer user_id, Integer theme_id, Status status, Integer grade, String start_time, String end_time, String test_time, String date) {
        this.id = id;
        this.user_id = user_id;
        this.theme_id = theme_id;
        this.status = status;
        this.grade = grade;
        this.start_time = start_time;
        this.end_time = end_time;
        this.test_time = test_time;
        this.date = date;
    }
    public Test(Integer user_id, Integer theme_id, Status status, Integer grade, String start_time, String end_time, String test_time, String date) {
        this.user_id = user_id;
        this.theme_id = theme_id;
        this.status = status;
        this.grade = grade;
        this.start_time = start_time;
        this.end_time = end_time;
        this.test_time = test_time;
        this.date = date;
    }

    public Test(Builder builder) {
        id = builder.id;
         user_id = builder.user_id;
         theme_id = builder.theme_id;
         status = builder.status;
         grade = builder.grade;
         start_time = builder.start_time;
         end_time = builder.end_time;
         test_time = builder.test_time;
         date = builder.date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTest_time() {
        return test_time;
    }

    public void setTest_time(String test_time) {
        this.test_time = test_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", theme_id=" + theme_id +
                ", status=" + status +
                ", grade=" + grade +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", test_time='" + test_time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public enum Status{
        PASSED,
        FAILED
    }
    public static class Builder{
        private Integer id;
        private Integer user_id;
        private Integer theme_id;
        private Status status;
        private Integer grade;
        private String start_time;
        private String end_time;
        private String test_time;
        private String date;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Integer user_id) {
            this.user_id = user_id;
            return this;
        }

        public Builder setThemeId(Integer theme_id) {
            this.theme_id = theme_id;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setGrade(Integer grade) {
            this.grade = grade;
            return this;
        }

        public Builder setStartTime(String start_time) {
            this.start_time = start_time;
            return this;
        }

        public Builder setEndTime(String end_time) {
            this.end_time = end_time;
            return this;
        }

        public Builder setTestTime(String test_time) {
            this.test_time = test_time;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Test build(){
            return new Test(this);
        }
    }
}


