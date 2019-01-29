/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import app.data.directories.Directory;
import app.data.directories.interfaces.DirectoryEntry;
import app.entities.roles.Roles;
import app.entities.workqueues.CaseFileRequestWorkQueue;
import app.entities.workqueues.GrievanceRequestWorkQueue;
import app.entities.workqueues.WorkQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class LegalEntity extends User implements DirectoryEntry<String>{
    
    private String firstName, middleName, lastName;
    private Date dob;
    private Address priamryAddress;
    private ContactDetails primaryContact;
    private String ssn;
    private String email;
    private WorkQueue workqueue;
    private WorkQueue caseQueue;
    private Directory<String, Lawyer> lawyers;
    private BufferedImage pic;

    public BufferedImage getPic() {
        return pic;
    }

    public void setPic(BufferedImage pic) {
        this.pic = pic;
    }
    
    public Directory<String, Lawyer> getLawyers() {
        return lawyers;
    }

    public void setLawyers(Directory<String, Lawyer> lawyers) {
        this.lawyers = lawyers;
    }

    public WorkQueue getCaseQueue() {
        return caseQueue;
    }

    public void setCaseQueue(WorkQueue caseQueue) {
        this.caseQueue = caseQueue;
    }

    public LegalEntity() {
        super(Roles.LEGAL_ENTITY);
        priamryAddress = new Address();
        primaryContact = new ContactDetails();
        dob = new Date();
        this.workqueue = new GrievanceRequestWorkQueue();
        this.caseQueue = new CaseFileRequestWorkQueue();
        this.lawyers = new Directory<>();
    }

    
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getPriamryAddress() {
        return priamryAddress;
    }

    public void setPriamryAddress(Address priamryAddress) {
        this.priamryAddress = priamryAddress;
    }

    public ContactDetails getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(ContactDetails primaryContact) {
        this.primaryContact = primaryContact;
    }

    public WorkQueue getWorkqueue() {
        return workqueue;
    }

    public void setWorkqueue(WorkQueue workqueue) {
        this.workqueue = workqueue;
    }

    @Override
    public String getKey() {
        return ssn;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }

    @Override
    public String getName() {
        return firstName+" "+lastName;
    }
            
}
