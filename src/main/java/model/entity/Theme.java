package model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Theme implements Serializable {

    private Integer id;
    private String course_name;
    private String theme_name;
    private Integer time;
    private Integer passing_grade;

    public Theme() {
    }

    public Theme(Integer id, String course_name, String theme_name, Integer time, Integer passing_grade) {
        this.id = id;
        this.course_name = course_name;
        this.theme_name = theme_name;
        this.time = time;
        this.passing_grade = passing_grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getPassing_grade() {
        return passing_grade;
    }

    public void setPassing_grade(Integer passing_grade) {
        this.passing_grade = passing_grade;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", course_name='" + course_name + '\'' +
                ", theme_name='" + theme_name + '\'' +
                ", time=" + time +
                ", passing_grade=" + passing_grade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return Objects.equals(id, theme.id) &&
                Objects.equals(course_name, theme.course_name) &&
                Objects.equals(theme_name, theme.theme_name) &&
                Objects.equals(time, theme.time) &&
                Objects.equals(passing_grade, theme.passing_grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course_name, theme_name, time, passing_grade);
    }
}
