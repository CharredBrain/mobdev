package ru.mirea.maksimovaok.mireaproject;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class workWorker extends Worker {
    static final String TAG = "UploadWorker";
    public workWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: start");
        try {
            TimeUnit.SECONDS.sleep(5);
            Log.d(TAG, "workworkworkworkworkwork");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "doWork: end");
        return Result.success();
    }
}