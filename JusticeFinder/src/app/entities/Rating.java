/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import app.data.directories.interfaces.DirectoryEntry;
import app.entities.user.LegalEntity;
import app.entities.user.User;
import app.entities.user.UserAccount;

/**
 *
 * @author PC
 */
public class Rating implements DirectoryEntry<String>{
    
    private String feedBack;
    private Integer value = 1;
    private LegalEntity givenBy;

    public Rating(String feedBack, Integer rating,LegalEntity givenBy) {
        this.feedBack = feedBack;
        this.value = rating;
        this.givenBy = givenBy;
    }

    public LegalEntity getGivenBy() {
        return givenBy;
    }

    public void setGivenBy(LegalEntity givenBy) {
        this.givenBy = givenBy;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return givenBy.getKey();
    }

    @Override
    public String toString() {
        return value+"";
    }
    
    
}
