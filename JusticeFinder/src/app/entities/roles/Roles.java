/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.roles;

import app.userinterface.admin.AdminPanel;
import app.userinterface.admin.CourtPanel;
import app.userinterface.court.CourtWelcomePanel;
import app.userinterface.judge.JudgePanel;
import app.userinterface.sba.StateBarAssociationPanel;
import app.userinterface.lawyer.LawyerPanel;
import app.userinterface.legalEntity.LegalEntityPanel;
import app.userinterface.clerk.Clerkpanel;


/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public enum Roles {

    /**
     *
     *//**
     *
     */
    ADMIN(new Role(AdminPanel.class)),
    LEGAL_ENTITY(new Role(LegalEntityPanel.class)),
    LAWYER(new Role(LawyerPanel.class)),
    STATE_BAR_ASSO_ADMIN(new Role(StateBarAssociationPanel.class)),
    COURT(new Role(CourtWelcomePanel.class)),
    JUDGE(new Role(JudgePanel.class)),
    CLERK(new Role(Clerkpanel.class));
    private final Role type;

    private Roles(Role role) {
        this.type = role;
    }

    public Role getType() {
        return type;
    }
    
}
