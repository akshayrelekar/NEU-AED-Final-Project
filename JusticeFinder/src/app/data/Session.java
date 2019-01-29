/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data;

import app.entities.user.UserAccount;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class Session {
    
    private static UserAccount userAccount;
    private static Session session;
    
    private Session(UserAccount userAccount){
        Session.userAccount = userAccount;
    }
    
    public static Session createNewSession(UserAccount userAccount){
        session = new Session(userAccount);
        return session;
    }

    public static UserAccount getUserAccount() {
        return userAccount;
    }
    
    public static void clearSession(){
        userAccount = null;
        session = null;
    }
    
}
