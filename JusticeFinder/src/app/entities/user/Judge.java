/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import app.data.directories.Directory;
import app.data.directories.interfaces.DirectoryEntry;
import app.data.org.Court;
import app.data.org.Organization;
import app.entities.roles.Roles;
import app.entities.workqueues.CaseFileRequestWorkQueue;
import app.entities.workqueues.GrievanceRequestWorkQueue;
import app.entities.workqueues.WorkQueue;
import java.util.Date;

/**
 *
 * @author arele
 */
public class Judge extends User implements DirectoryEntry<Integer> {
   
    private String name;
    private ContactDetails workContact;
    private String email;
    private Date joiningdate;
    private WorkQueue workqueue;
    private int id = 0;
    
    public Judge(Organization parent) {
        super(Roles.JUDGE);
        workContact = new ContactDetails();
        this.workqueue = new CaseFileRequestWorkQueue();
        this.joiningdate = new Date();
        this.setParent(parent);
        this.id = ((Court) parent).getJudgeDirectory().size();
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

    public Date getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(Date joiningdate) {
        this.joiningdate = joiningdate;
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

    @Override
    public String toString() {
        return name;
    }
    
    
}
