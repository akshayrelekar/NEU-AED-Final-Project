/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import app.data.directories.interfaces.DirectoryEntry;
import app.data.org.Court;
import app.data.org.Organization;
import app.entities.roles.Roles;
import app.entities.workqueues.CaseFileRequestWorkQueue;
import app.entities.workqueues.WorkQueue;
import java.util.Date;

/**
 *
 * @author arele
 */
public class Clerk extends User implements DirectoryEntry<Integer> {
    
    private String name;
    private ContactDetails workContact;
    private String email;
    private WorkQueue workqueue;
    private int id = 0;

    public Clerk(Organization court) {
        super(Roles.CLERK);
        workContact = new ContactDetails();
        this.workqueue = new CaseFileRequestWorkQueue();   
        this.setParent(court);
        this.id = ((Court) court).getClerkDirectory().size();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactDetails getWorkContact() {
        return workContact;
    }

    public void setWorkContact(ContactDetails workContact) {
        this.workContact = workContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WorkQueue getWorkqueue() {
        return workqueue;
    }

    public void setWorkqueue(WorkQueue workqueue) {
        this.workqueue = workqueue;
    }
    
    @Override
    public Integer getKey(){
        return id;
    }
}
