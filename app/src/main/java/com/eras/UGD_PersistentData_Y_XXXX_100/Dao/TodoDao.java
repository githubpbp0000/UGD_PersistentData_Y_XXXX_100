package com.eras.UGD_PersistentData_Y_XXXX_100.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eras.UGD_PersistentData_Y_XXXX_100.Model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo")
    List<Todo> getAll();

    @Query("SELECT * FROM todo WHERE id = :id")
    Todo findTodoById(int id);

    @Insert
    void insertTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);
}