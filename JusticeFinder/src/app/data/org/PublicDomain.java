/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data.org;

import app.data.directories.Directory;

/**
 *
 * @author PC
 */
public class PublicDomain extends Organization{
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public PublicDomain() {
        super(null, new Directory(), null);
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
