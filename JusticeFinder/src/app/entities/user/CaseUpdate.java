/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;

import java.util.Date;

/**
 *
 * @author Saurabh Gujare (NUID : 001424874)
 */
public class CaseUpdate {
    private Date caseUpdate;
    private String caseDescription;

    public CaseUpdate(String caseDescription) {
        this.caseUpdate = new Date();
        this.caseDescription = caseDescription;
    }

    public Date getCaseUpdate() {
        return caseUpdate;
    }

    public void setCaseUpdate(Date caseUpdate) {
        this.caseUpdate = caseUpdate;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }
    
    @Override
    public String toString(){
        return this.caseUpdate.toString();
    }
    
}
