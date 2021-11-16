package com.revature.repositories;

// A generic type is a class or interface that is parameterzied over types.
// We use angle brackets to specify the parameter type.
// T for 'Type'

import java.util.List;

public interface CrudRepository <T>{

    // Create
    T add(T t);


    // Read
    T getById(Integer id);

    List<T> getAll();

    // Update
    void update(T t);

    // Delete
    boolean delete(Integer id);
}


