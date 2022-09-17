package cn.itcast.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_age")
    private Integer age;
    /**配置映射多对多关系
     *      1.声明表关系配置
     *          @ManyToMany(targetEntity = User.class) //多对多
     *              targetEntity：代表对方实体类字节码
     *      2.配置中间表（包含两个外键）
     *          @JoinTable
     *              name:中间表名称
     *              joinColumns：配置当前对象在中间表的外键
     *                  JoinColumn的数组
     *                      name：外键名
     *                      referencedColumnName：参照主表的主键
     *              inverseJoinColumns：配置对方表在中间表的外键
     */
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinTable(name="sys_user_role",joinColumns = {@JoinColumn(name="sys_user_id",referencedColumnName = "user_id")}
            ,inverseJoinColumns = {@JoinColumn(name="sys_role_id",referencedColumnName = "role_id")})
    private Set<Role> roles=new HashSet<Role>();
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
