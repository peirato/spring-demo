package com.peirato.dome.model;

public class User {

    private Long id;

    private String name;

    private String gender;

    private String birth;

    private Integer year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public User(Long id, String name, String gender, String birth, Integer year) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.year = year;
    }

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", year=" + year +
                '}';
    }
}
