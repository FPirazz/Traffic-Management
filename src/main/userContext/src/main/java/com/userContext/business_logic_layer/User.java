package com.userContext.business_logic_layer;

import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String role;
    public User(final String name, final String surname, final String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public void saveUser() {
        try {
            DomainModelImpl.getDataSourcePort().saveUser();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, role);
    }
}
