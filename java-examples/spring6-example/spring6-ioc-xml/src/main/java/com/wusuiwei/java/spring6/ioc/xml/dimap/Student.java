package com.wusuiwei.java.spring6.ioc.xml.dimap;

import java.util.List;
import java.util.Map;

public class Student {
    private String sid;
    private String sname;
    public Map<String, Teacher> teacherMap;

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public List<Lesson> lessonList;
    public Map<String, Teacher> getTeacherMap() {
        return teacherMap;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", teacherMap=" + teacherMap +
                ", lessonList=" + lessonList +
                '}';
    }

    public void setTeacherMap(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }


    public void run() {
        System.out.println("学生编号：" + sid +
                "学生姓名：" + sname);
    }
}
