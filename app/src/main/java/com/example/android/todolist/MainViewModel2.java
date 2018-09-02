package com.example.android.todolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

import java.util.List;

// Viewmodel works like SharePreferences
public class MainViewModel2 extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel2.class.getSimpleName();

    private LiveData<List<TaskEntry>> tasks;

    // Constructor
    public MainViewModel2(@NonNull Application application, LiveData<List<TaskEntry>> tasks) {
        super(application);

        // get a copy of the database
        AppDatabase database = AppDatabase.getInstance(this.getApplication());

        Log.d(TAG, "Actively retrieving the tasks from the Database");
        tasks = database.taskDao().loadAllTasks();

    } // Constructor


    // Getter
    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    } // getTasks


} // class MainViewModel
