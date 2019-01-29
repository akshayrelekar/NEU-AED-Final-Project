/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.workqueues;

import app.entities.user.LegalEntity;
import app.entities.user.UserAccount;
import java.util.Date;

/**
 *
 * @author Saurabh Gujare (NUID : 001424874)
 */
public class GrievanceRequest extends WorkItem {


    public GrievanceRequest(String message, UserAccount sender, UserAccount receiver,int id) {
        super(message, sender, receiver);
        this.id = id;
    }

    @Override
    public String toString() {
        return id+"";
    }
    
    
}
