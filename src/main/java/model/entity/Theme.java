package model.entity;

import java.io.Serializable;

/**
 * Class {@code Theme} represents the Theme model. This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Alex Naumenko
 */
public class Theme implements Serializable {
    /**
     * Id of Theme.
     */
    private Integer id;

    /**
     * Course name of Theme.
     */
    private String courseName;

    /**
     * Theme name of Theme.
     */
    private String themeName;

    /**
     * Time of Theme.
     */
    private Integer time;

    /**
     * Passing grade of Theme.
     */
    private Integer passingGrade;

    /**
     * Default constructor.
     */
    public Theme() {
    }

    /**
     * Constructor of the theme with parameters.
     *
     * @param id           of Test
     * @param courseName   of Test
     * @param themeName    of Test
     * @param time         of Test
     * @param passingGrade of Test
     */
    public Theme(Integer id, String courseName, String themeName, Integer time, Integer passingGrade) {
        this.id = id;
        this.courseName = courseName;
        this.themeName = themeName;
        this.time = time;
        this.passingGrade = passingGrade;
    }

    /**
     * Returns id of Theme.
     *
     * @return id of Theme.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new id to Theme
     *
     * @param id of Theme
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns course name of Theme.
     *
     * @return course name of Theme.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set new course name to Theme
     *
     * @param courseName of Theme
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns theme name of Theme.
     *
     * @return theme name of Theme.
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * Set new theme name to Theme
     *
     * @param themeName of Theme
     */
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    /**
     * Returns time of Theme.
     *
     * @return time of Theme.
     */
    public Integer getTime() {
        return time;
    }

    /**
     * Set new time to Theme
     *
     * @param time of Theme
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * Returns passing grade of Theme.
     *
     * @return passing grade of Theme.
     */
    public Integer getPassingGrade() {
        return passingGrade;
    }

    /**
     * Set new passing grade to Theme
     *
     * @param passingGrade of Theme
     */
    public void setPassingGrade(Integer passingGrade) {
        this.passingGrade = passingGrade;
    }

    /**
     * Indicates whether some other theme is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same
     * as the o argument; {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theme theme = (Theme) o;

        if (id != null ? !id.equals(theme.id) : theme.id != null) return false;
        if (courseName != null ? !courseName.equals(theme.courseName) : theme.courseName != null) return false;
        if (themeName != null ? !themeName.equals(theme.themeName) : theme.themeName != null) return false;
        if (time != null ? !time.equals(theme.time) : theme.time != null) return false;
        return passingGrade != null ? passingGrade.equals(theme.passingGrade) : theme.passingGrade == null;
    }

    /**
     * Returns a hash code value for the theme.
     *
     * @return a hash code value for this theme.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (themeName != null ? themeName.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (passingGrade != null ? passingGrade.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of the theme.
     *
     * @return a string representation of the theme.
     */
    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", themeName='" + themeName + '\'' +
                ", time=" + time +
                ", passingGrade=" + passingGrade +
                '}';
    }

}
