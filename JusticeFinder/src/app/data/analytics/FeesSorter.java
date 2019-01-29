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
public class FeesSorter implements Comparator<Lawyer>{

    private boolean asc;

    public FeesSorter(boolean asc) {
        this.asc = asc;
    }
    
    @Override
    public int compare(Lawyer o1, Lawyer o2) {
        return o1.getFees().compareTo(o2.getFees())*(asc?-1:1);
    }

    @Override
    public String toString() {
        if(asc)
            return "High to Low Fees";
        else
            return "Low to High Fees";
    }
    
    
}
