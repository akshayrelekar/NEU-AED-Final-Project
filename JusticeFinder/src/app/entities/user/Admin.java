/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import app.data.directories.interfaces.DirectoryEntry;
import app.entities.user.UserAccount;
import app.entities.roles.Roles;
import app.entities.roles.Roles;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class Admin extends User implements DirectoryEntry<Integer>{

    private static int lastId = 0;
    private int id;
    public Admin() {
        super(Roles.ADMIN);
        id = lastId;
        lastId++;
    }

    @Override
    public Integer getKey() {
        return id;
    }

    @Override
    public String getName() {
        return "System Admin";
    }
    
}
