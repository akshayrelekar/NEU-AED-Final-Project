/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data.directories;

import app.entities.user.UserAccount;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class UserAccountDirectory extends Directory<String, UserAccount>{
    
    @Override
    public void addNew(UserAccount entry) throws Exception{
        if(!contains(entry.getKey())){
            super.addNew(entry);
        }
        else{
            throw new Exception("User Already present");
        }
    }
}
