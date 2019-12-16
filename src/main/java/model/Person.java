package model;

import java.util.List;

public class Person {

    private String name;

    private Gender gender;

    List<String> children;

    private String spouse;

    private Integer priority;

    private String parent;

    public Person(String name, Gender gender, List<String> children, String spouse, Integer priority, String parent) {
        this.name = name;
        this.gender = gender;
        this.children = children;
        this.spouse = spouse;
        this.priority = priority;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
