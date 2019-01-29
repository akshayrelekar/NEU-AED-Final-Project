/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import app.data.directories.interfaces.DirectoryEntry;
import app.data.org.Organization;
import app.entities.roles.Roles;

/**
 *
 * @author arele
 */
public class StateBarAssoAdmin extends User implements DirectoryEntry<Integer>{
    private static int lastId = 0;
    private int id = 0;
    private Organization parent;
    
    @Override
    public Integer getKey() {
        return id;
    }

    public StateBarAssoAdmin(Organization parent) {
        super(Roles.STATE_BAR_ASSO_ADMIN);
        id = lastId;
        lastId++;
        this.parent = parent;
    }

    public Organization getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return parent.toString();
    }

    
}
