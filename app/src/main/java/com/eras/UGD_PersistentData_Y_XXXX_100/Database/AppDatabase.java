package com.eras.UGD_PersistentData_Y_XXXX_100.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.eras.UGD_PersistentData_Y_XXXX_100.Dao.TodoDao;
import com.eras.UGD_PersistentData_Y_XXXX_100.Model.Todo;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();

}
