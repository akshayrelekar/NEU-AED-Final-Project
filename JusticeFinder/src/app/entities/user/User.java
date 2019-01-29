/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import app.data.org.Organization;
import app.entities.roles.Roles;

/**
 *
 * @author Akshay Relekar
 */
public abstract class User {
    
    private Roles role;
    private UserAccount account;
    private Organization parent;

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public User(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }
    
    public abstract String getName();
}
