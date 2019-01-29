/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils.email.templates;

/**
 *
 * @author PC
 */
public enum Templates {
    LAWYER_APPROVED("lawyerApproved.txt"),
    LAWYER_REQUEST("lawyerRequest.txt"),
    LEGALENTITY_APPROVED("legalEnityApproved.txt"),
    LEGALENTITY_REQUEST("legalEnityRequest.txt"),
    CASE_APPROVED("caseApproved.txt"),
    CASE_UPDATES("caseUpdates.txt");
    
    String pageName;

    private Templates(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }
    
    
}
