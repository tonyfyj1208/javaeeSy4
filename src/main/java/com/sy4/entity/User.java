package com.sy4.entity;

// 用户实体类 - 对应数据库中的 user 表
public class User {
    private Integer id;
    private String loginName;   // 登录名
    private String password;    // 密码
    private String name;        // 真实姓名
    private String phone;       // 电话
    private String email;       // 邮箱
    private Integer type;       // 类型 0=管理员 1=普通用户

    // Getter / Setter（SpringMVC绑定参数时需要）
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLoginName() { return loginName; }
    public void setLoginName(String loginName) { this.loginName = loginName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
}
