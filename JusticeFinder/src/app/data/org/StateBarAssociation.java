/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data.org;

import app.data.directories.Directory;
import app.entities.user.Address;
import app.entities.user.ContactDetails;
import app.entities.user.StateBarAssoAdmin;
import app.entities.workqueues.StateBarAssoWorkQueue;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class StateBarAssociation extends Organization{

    private String stateBarAssociationName;
    private String stateBarAssociationID;
    private Address workaddress;
    private ContactDetails workphone;
    private String email;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public StateBarAssociation() {
        super(new StateBarAssoWorkQueue(), new Directory(),null);
        super.admin = new StateBarAssoAdmin(this);
        this.active =true;
    }

    public String getStateBarAssociationName() {
        return stateBarAssociationName;
    }

    public void setStateBarAssociationName(String stateBarAssociationName) {
        this.stateBarAssociationName = stateBarAssociationName;
    }

    public String getStateBarAssociationID() {
        return stateBarAssociationID;
    }

    public void setStateBarAssociationID(String stateBarAssociationID) {
        this.stateBarAssociationID = stateBarAssociationID;
    }

    public Address getWorkaddress() {
        return workaddress;
    }

    public void setWorkaddress(Address workaddress) {
        this.workaddress = workaddress;
    }

    public ContactDetails getWorkphone() {
        return workphone;
    }

    public void setWorkphone(ContactDetails workphone) {
        this.workphone = workphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return stateBarAssociationName;
    }

    
}
