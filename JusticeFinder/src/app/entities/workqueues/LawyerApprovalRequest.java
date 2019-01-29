/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.workqueues;

import app.entities.user.Lawyer;
import app.entities.user.UserAccount;

/**
 *
 * @author Akshay Relekar
 */
public class LawyerApprovalRequest extends WorkItem{
    
    private static int maxCount = 0;
    
    public LawyerApprovalRequest(String message, UserAccount sender, UserAccount receiver) {
        super(message, sender, receiver);
        maxCount++;
        id = maxCount;
    }

    @Override
    public String toString() {
        return id+"";
    }
    
    
}
