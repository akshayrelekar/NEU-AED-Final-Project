/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.workqueues;

import app.entities.user.UserAccount;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 * @param <I>
 */
public abstract class WorkQueue<I extends WorkItem> {
    
    private List<WorkItem> workList;

    public WorkQueue() {
        workList = new ArrayList<>();
    }

    public List<WorkItem> getWorkList() {
        return workList;
    }
    
    public void addWorkItem(I item){
        this.workList.add(item);
    }
    
    public WorkItem createNewWorkItem(UserAccount sender, UserAccount receiver, String message){
        WorkItem item = this.getNewItem(sender, receiver, message);
        workList.add(item);
        return item;
    }
    
    protected abstract WorkItem getNewItem(UserAccount sender, UserAccount receiver, String message);
    
    public void removeItem(I item){
        this.workList.remove(item);
    }
}
