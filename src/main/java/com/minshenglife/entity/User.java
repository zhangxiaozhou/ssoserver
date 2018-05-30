package com.minshenglife.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 客户信息表
 */
@Entity
@Table(indexes = {
        @Index(name = "mobile_index", columnList = "mobile", unique = true)
})
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;//用户名

    @JsonIgnore
    private String password;//登录密码

    private String mobile;//手机号码

    private String name; //客户姓名

    private Boolean gender;//性别

    private Date birthDate;//生日

    private String idType;//证件类型

    private String idNo;//证件号码

    private Date idExpireDateBegin; //证件有效期开始

    private Date idExpireDateEnd; //证件有效期结束

    private Date validTime;//帐号有效期

    private Date createTime;//创建时间

    private Date updateTime;//最后更新时间

    private Integer upgrade;//是否已经高级认证 0未认证  1审核中  2认证完成

    private Long frontAttachId; //证件照正面ID
    private Long backAttachId; //证件照反面ID


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = { @JoinColumn(name ="USER_ID")},
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID")})
    private List<Role> roleList;

    public User() {
    }

    public User(String mobile, String password, Date createTime, Date updateTime, Date validTime) {
        this.password = password;
        this.mobile = mobile;

        this.createTime = createTime;
        this.updateTime = updateTime;
        this.validTime = validTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Role> roleList = this.getRoleList();

        List<GrantedAuthority> authoritiesList = new ArrayList();
        for(Role role : roleList){
            authoritiesList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authoritiesList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getIdExpireDateBegin() {
        return idExpireDateBegin;
    }

    public void setIdExpireDateBegin(Date idExpireDateBegin) {
        this.idExpireDateBegin = idExpireDateBegin;
    }

    public Date getIdExpireDateEnd() {
        return idExpireDateEnd;
    }

    public void setIdExpireDateEnd(Date idExpireDateEnd) {
        this.idExpireDateEnd = idExpireDateEnd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Integer getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Integer upgrade) {
        this.upgrade = upgrade;
    }

    public Long getFrontAttachId() {
        return frontAttachId;
    }

    public void setFrontAttachId(Long frontAttachId) {
        this.frontAttachId = frontAttachId;
    }

    public Long getBackAttachId() {
        return backAttachId;
    }

    public void setBackAttachId(Long backAttachId) {
        this.backAttachId = backAttachId;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", idExpireDateBegin=" + idExpireDateBegin +
                ", idExpireDateEnd=" + idExpireDateEnd +
                ", validTime=" + validTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", upgrade=" + upgrade +
                ", frontAttachId=" + frontAttachId +
                ", backAttachId=" + backAttachId +
                ", roleList=" + roleList +
                '}';
    }
}
