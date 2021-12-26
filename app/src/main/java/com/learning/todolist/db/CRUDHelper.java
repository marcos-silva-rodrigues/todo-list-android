package com.learning.todolist.db;

import java.util.List;

public interface CRUDHelper<T> {

    void create(T klazz);
    T findById(int id);
    List<T> findAll();
    int update(T klazz);
    void delete (T klazz);
}
