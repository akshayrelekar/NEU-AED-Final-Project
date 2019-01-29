/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.workqueues;

import app.entities.user.UserAccount;

/**
 *
 * @author Saurabh Gujare (NUID : 001424874)
 */
public class GrievanceRequestWorkQueue extends WorkQueue<GrievanceRequest> {

    @Override
    protected WorkItem getNewItem(UserAccount sender, UserAccount receiver, String message) {
        int id = this.getWorkList().size()+1;
        return new GrievanceRequest(message,sender,receiver,id);
    }
    
}
