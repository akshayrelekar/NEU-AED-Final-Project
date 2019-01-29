/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.business;

import app.business.interfaces.Actions;
import app.data.DBUtil;
import app.data.Network;
import app.data.Session;
import app.entities.user.UserAccount;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class LoginAction implements Actions{
    
    private static final Network DATA_STORE = Network.getInstance();
    
    public UserAccount login(String username, String password){
        UserAccount acc = DATA_STORE.getUSER_ACCOUNTS().getEntry(username);
        if(acc!=null && acc.getPassword().equals(password)){
            Session.createNewSession(acc);
            return Session.getUserAccount();
        }
        else
            return null;
    }
    
    public void logout(){
        Session.clearSession();
        DBUtil.getInstance().storeSystem(DATA_STORE);
    }
}
