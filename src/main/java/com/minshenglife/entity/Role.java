package com.minshenglife.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    @Id
    private Long id;
    private String roleName;

    @ManyToMany(mappedBy = "sysRolesList")
    List<User> usersList;

    @ManyToMany
    @JoinTable(name="SYS_ROLES_AUTHORITIES",
            joinColumns = {@JoinColumn(name="ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name="AUTHORITY_ID")}
    )
    List<Authorities> authoritiesList;

    public List<Authorities> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", usersList=" + usersList +
                ", authoritiesList=" + authoritiesList +
                '}';
    }
}
