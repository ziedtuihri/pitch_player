/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.interfaces;

import java.util.List;

/**
 *
 * @author WIJDEN
 * @param <T>
 */
public interface ICRUD<T> {

    //create    
    public void addEntity(T t);

    //Retrieve all entities
    public List<T> displayEntities();

    // Retrieve by ID
    T getById(int id);

    // Update an existing entity
    public void update(T entity);

    // Delete an entity
    public void delete(T entity);
}
