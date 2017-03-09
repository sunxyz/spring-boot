package cn.sunxyz.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by sunxyz on 2017/3/9.
 */
@Entity
public class User extends AbstractPersistable<Long> {

    private String name;

    private Integer age;

    private String AddressCode;

    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    public User() {
    }

    public User(String name, Integer age, String addressCode, Sex sex) {
        this.name = name;
        this.age = age;
        AddressCode = addressCode;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddressCode() {
        return AddressCode;
    }

    public void setAddressCode(String addressCode) {
        AddressCode = addressCode;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public static enum Sex {
        MAN, WOMAN
    }
}
