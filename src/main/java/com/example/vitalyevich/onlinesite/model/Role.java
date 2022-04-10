package com.example.vitalyevich.onlinesite.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "role_name", nullable = false, length = 20)
    private String roleName;

    //
    @ManyToMany(mappedBy = "roles")
    private Set<Access> users;

    public Set<Access> getUsers() {
        return users;
    }

    public void setUsers(Set<Access> users) {
        this.users = users;
    }
    //

    public Role() {

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  /*  @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }*/
}
