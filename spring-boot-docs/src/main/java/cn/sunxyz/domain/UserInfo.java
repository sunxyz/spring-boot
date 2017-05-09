package cn.sunxyz.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yangrd on 2017/5/8.
 */
@ApiModel(value = "用户信息", description = "用户信息描述")
@Entity
@Table(name = "sys_user")
public class UserInfo {

    /**
     * 主键
     */
    @ApiModelProperty("用户id")
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;
    /**
     * 住址
     */
    @ApiModelProperty("家庭住址")
    private String address;

    public UserInfo() {
    }

    public UserInfo(String name, Integer age, String sex, String address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}