package com.example.android.todolist;

import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Handler;


public class AppExecutors {

    // 'Singleton' instantiation
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;


    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {

        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    } // constructor

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor()
                ); // >> a new class, bottom of this file
            }
        }
        return sInstance;
    }


    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }


    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    } // class MainThreadExecutor

} // class AppExecutors
