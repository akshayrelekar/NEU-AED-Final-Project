/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.workqueues;

import app.entities.Case;
import app.entities.user.CaseUpdate;
import app.entities.user.UserAccount;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arele
 */
public class CaseFileRequest extends WorkItem{
    
    private Case casereq;
    private static int count = 0;
    List<CaseUpdate> caseUpdates;
    
    public CaseFileRequest(String message, UserAccount sender, UserAccount receiver){
    
        super(message, sender, receiver);
        count++;
        id=count;
        casereq = new Case();
        caseUpdates = new ArrayList<>();
    }

    public List<CaseUpdate> getCaseUpdates() {
        return caseUpdates;
    }

    public void setCaseUpdates(List<CaseUpdate> caseUpdates) {
        this.caseUpdates = caseUpdates;
    }

    public Case getCasereq() {
        return casereq;
    }

    public void setCasereq(Case casereq) {
        this.casereq = casereq;
    }
    
    @Override
    public String toString() {
        return casereq.getCaseName();
    }
}
