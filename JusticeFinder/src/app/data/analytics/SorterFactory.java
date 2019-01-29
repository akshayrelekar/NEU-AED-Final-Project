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
public enum SorterFactory {
    RATING_SORTER(new RatingSortor(Boolean.TRUE)),
    HIGH_TO_LOW_SORTER(new FeesSorter(Boolean.TRUE)),
    LOW_TO_HIGH_SORTER(new FeesSorter(Boolean.FALSE));
    
    Comparator<Lawyer> sorter;

    private SorterFactory(Comparator<Lawyer> sorter) {
        this.sorter = sorter;
    }

    public Comparator<Lawyer> getSorter() {
        return sorter;
    }
    
}
