/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data.directories;

import app.data.directories.interfaces.DirectoryEntry;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 * @param <K> Key
 * @param <E> Entry
 */
public class Directory<K,E extends DirectoryEntry<K>> {
    
    protected Map<K,E> map = new TreeMap<>();
    
    public ArrayList<E> getAllEntries(){
        return new ArrayList<>(map.values());
    }
    public Map<K,E> getMap(){
        return this.map;
    }
    
    public void addNew(E entry) throws Exception{
        map.put(entry.getKey(),entry);
    }
    
    public void merge(Directory<K,E> directory){
        this.map.putAll(directory.getMap());
    }
    
    public void delete(K key){
        map.remove(key);
    }
    
    public E getEntry(K key){
        return map.get(key);
    }
    
    public void clearAll(){
        map.clear();
    }
    
    public int size(){
        return map.size();
    }
    
    public boolean contains(K key){
        return map.containsKey(key);
    }
    
    public boolean contains(DirectoryEntry entry){
        return map.containsKey(entry.getKey());
    }
}
