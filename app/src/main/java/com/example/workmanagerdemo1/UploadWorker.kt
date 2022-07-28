package com.example.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        const val KEY_WORKER = "key_worker"
    }

    override fun doWork(): Result {
        return try {
            val count = inputData.getInt(MainActivity.KEY_COUNT_VALUE, 0)
            for (a in 0 until count) {
                Log.d("MYTAG", "Uploading $a")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
            val currentDate = time.format(Date())

            // mengirim data ke MainActivity
            val outputData = Data.Builder()
                .putString(KEY_WORKER, currentDate)
                .build()

            Result.success(outputData)
        } catch (e: Exception) {
            Result.failure()
        }
    }
}