package com.minshenglife.entity;

import javax.persistence.*;
import java.util.List;

public class Authorities {

    @Id
    private Long id;
    private String authorityMark;
    private String authorityName;
    private String message;

    @ManyToMany(mappedBy = "sysAuthoritiesList")
    List<Role> rolesList;

    public List<Role> getSysRolesList() {
        return rolesList;
    }

    public void setSysRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityMark() {
        return authorityMark;
    }

    public void setAuthorityMark(String authorityMark) {
        this.authorityMark = authorityMark;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
