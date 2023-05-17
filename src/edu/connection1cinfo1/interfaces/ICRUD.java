/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.interfaces;

import java.util.List;

/**
 *
 * @author Zied
 */
public interface ICRUD<T> {

    //create    
    public void addEntity(T entity);

    //Retrieve all entities
    public List<T> displayEntities();

    // Retrieve by ID
    T getById(int id);

    // Update an existing entity
    public void update(T entity);

    // Delete an entity
    public void delete(T entity);
}
