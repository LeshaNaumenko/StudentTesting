package model.factory;

import model.entity.*;

public abstract class EntityFactory {
    public static User getUser(){
        return new User();
    }

    public static Answer getAnswer(){
        return new Answer();
    }

    public static Question getQuestion(){
        return new Question();
    }

    public static Test getTest(){
        return new Test();
    }
    public static Theme getTheme(){
        return new Theme();
    }
}
