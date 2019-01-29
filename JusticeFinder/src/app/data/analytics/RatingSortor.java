/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data.analytics;

import app.entities.user.Lawyer;
import java.util.Comparator;

/**
 *
 * @author PC
 */
public class RatingSortor implements Comparator<Lawyer>{

    private Boolean asc = false;
    public RatingSortor(Boolean order) {
        this.asc=order;
    }
    
    @Override
    public int compare(Lawyer l1, Lawyer l2) {
        return l1.getRating().compareTo(l2.getRating())*(asc?-1:1);
    }

    @Override
    public String toString() {
        return "Most Rated Lawyer";
    }
    
}
