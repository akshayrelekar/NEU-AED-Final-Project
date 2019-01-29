/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data;

import app.data.directories.Directory;
import app.data.directories.UserAccountDirectory;
import app.data.org.Court;
import app.data.org.PublicDomain;
import app.data.org.StateBarAssociation;
import app.entities.user.Admin;
import app.entities.user.Judge;
import app.entities.user.Lawyer;
import app.entities.user.LegalEntity;
import app.entities.user.UserAccount;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class Network {

    private static Network store;
    private Directory<String, UserAccount> USER_ACCOUNTS = new UserAccountDirectory();
    private Directory<String, PublicDomain> PUBLIC_DOMAIN = new Directory();
    private Directory<String, StateBarAssociation> STATE_BAR_ASSOCIATIONS = new Directory();
    private Directory<String, Lawyer> LAWYER_DIRECTORY = new Directory();
    private Directory<String, Court> COURT = new Directory();
    private Network(){
        initValues();
    }
    
    public static Network getInstance(){
        
        if(store == null){
            store = DBUtil.getInstance().retrieveSystem();
        }
        if(store == null){
            store = new Network();
            DBUtil.getInstance().storeSystem(store);
        }
        return store;
    }

    public Directory<String, UserAccount> getUSER_ACCOUNTS() {
        return USER_ACCOUNTS;
    }

    public Directory<String, PublicDomain> getPUBLIC_DOMAIN() {
        return PUBLIC_DOMAIN;
    }

    public Directory<String, StateBarAssociation> getSTATE_BAR_ASSOCIATIONS() {
        return STATE_BAR_ASSOCIATIONS;
    }

    public Directory<String, Lawyer> getLAWYER_DIRECTORY() {
        return LAWYER_DIRECTORY;
    }

    public Directory<String, Court> getCOURT() {
        return COURT;
    }

    private void initValues(){
        try {
            USER_ACCOUNTS.addNew(new UserAccount("admin", "admin", new Admin())); //super Admin
        } catch (Exception ex) {
            //super Admin present
            //ex.printStackTrace();
        }

    }
}
