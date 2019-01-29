/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import app.entities.enums.ContactType;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class ContactDetails {

    private String contactNumber;
    private ContactType type;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }
    
    
}
