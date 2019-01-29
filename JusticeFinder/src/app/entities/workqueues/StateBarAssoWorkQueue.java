/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.workqueues;

import app.entities.user.UserAccount;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class StateBarAssoWorkQueue extends WorkQueue<LawyerApprovalRequest>{

    @Override
    protected WorkItem getNewItem(UserAccount sender, UserAccount receiver, String message) {
        return new LawyerApprovalRequest(message, sender, receiver);
    }
    
}
