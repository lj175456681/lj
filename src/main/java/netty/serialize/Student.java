package netty.serialize;

import java.io.Serializable;

/**
 * Title: Student
 * Description:
 * author: liujie
 * date: 2017-08-17 下午4:10
 */
public class Student implements Serializable {

    private String name;

    private int age;

    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
