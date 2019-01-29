/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.user;
import app.data.org.Court;

/**
 *
 * @author Saurabh Gujare (NUID : 001424874)
 */
public class Case {
    private int caseID;
    private LegalEntity defendentName;
    private LegalEntity legaleintity;
    private Lawyer lawyer;
    private Clerk clerk;
    private Judge judge;
    private Court court;
    private CaseUpdate caseUpdate;

    public Case(int caseID, LegalEntity defendentName, LegalEntity legaleintity, Lawyer lawyer, Clerk clerk, Judge judge, Court court, CaseUpdate caseUpdate) {
        this.caseID = caseID;
        this.defendentName = defendentName;
        this.legaleintity = legaleintity;
        this.lawyer = lawyer;
        this.clerk = clerk;
        this.judge = judge;
        this.court = court;
        this.caseUpdate = caseUpdate;
    }

    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public LegalEntity getDefendentName() {
        return defendentName;
    }

    public void setDefendentName(LegalEntity defendentName) {
        this.defendentName = defendentName;
    }

    public LegalEntity getLegaleintity() {
        return legaleintity;
    }

    public void setLegaleintity(LegalEntity legaleintity) {
        this.legaleintity = legaleintity;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public CaseUpdate getCaseUpdate() {
        return caseUpdate;
    }

    public void setCaseUpdate(CaseUpdate caseUpdate) {
        this.caseUpdate = caseUpdate;
    }
    
    @Override
    public String toString(){
        return Integer.toString(this.caseID);
    }
}
