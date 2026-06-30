package org.example;

public class Subject {
    private int subject_id;
    private String code;
    private String name;

    Subject(String code,String name){
        this.code = code;
        this.name = name;
    }

    Subject(int subject_id, String code, String name){
        this.subject_id = subject_id;
        this.code = code;
        this.name = name;
    }

    public int getSubject_id() { return subject_id; }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

}
