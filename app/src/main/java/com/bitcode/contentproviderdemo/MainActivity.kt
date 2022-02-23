package com.bitcode.contentproviderdemo

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.Settings
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //readSettings()
        //readCallLogs()
        readSMS()


        //var uri = Uri.parse("content://in.bitcode.tasks")

        /*var tasksUri =Uri.withAppendedPath(uri, "tasks")
        //tasksUri =Uri.withAppendedPath(tasksUri, "1001")

        var cursor = contentResolver.query(
            tasksUri,
            null,
            null,
            null,
            null
        )

        while (cursor!!.moveToNext()) {
            mt("${cursor.getInt(0)} -- ${cursor.getString(1)} -- ${cursor.getInt(2)}")
        }
        cursor.close()

        mt("---------------***-----------------")

        var notesUri = Uri.withAppendedPath(uri, "notes")
        //notesUri = Uri.withAppendedPath(notesUri, "9090")
        var newCursor  = contentResolver.query(
            notesUri,
            null,
            null,
            null,
            null
        )
        while (newCursor!!.moveToNext()) {
            mt("${newCursor.getInt(0)} -- ${newCursor.getString(1)}")
        }
        newCursor.close()*/

        //insert
       /* var tasksUri =Uri.withAppendedPath(uri, "tasks")
        var values = ContentValues()
        values.put("id", 1005)
        values.put("title", "New Task*****")
        values.put("status", 1)

        var resUri = contentResolver.insert(tasksUri, values)
        mt(resUri.toString())

        var cursor = contentResolver.query(
            resUri!!,
            null,
            null,
            null,
            null
        )

        while (cursor!!.moveToNext()) {
            mt("${cursor.getInt(0)} -- ${cursor.getString(1)} -- ${cursor.getInt(2)}")
        }
        cursor.close()*/
    }

    private fun readSMS() {
        var c = contentResolver.query(
            Uri.parse("content://sms"),
            null,
            null,
            null,
            null
        )
        for(colName in c!!.columnNames) {
            mt("$colName")
        }

        var colThreadId = c.getColumnIndex("thread_id")
        var colBody = c.getColumnIndex("body")
        var colType = c.getColumnIndex("type")
        var colAddress = c.getColumnIndex("address")
        var colDate = c.getColumnIndex("date")

        while(c!!.moveToNext()) {
            mt("Thread: ${c.getInt(colThreadId)} --> Type: ${c.getInt(colType)} Address: ${c.getString(colAddress)} Date: ${c.getString(colDate)} Body: ${c.getString(colBody)}")
        }
        c.close()
    }


    private fun readCallLogs() {
        var c = contentResolver.query(
            android.provider.CallLog.Calls.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        for(colName in c!!.columnNames) {
            mt("$colName")
        }

        var colName = c.getColumnIndex(CallLog.Calls.CACHED_NAME)
        var colNumber = c.getColumnIndex(CallLog.Calls.NUMBER)
        var colType = c.getColumnIndex(CallLog.Calls.TYPE)
        var colPhotoUri = c.getColumnIndex(CallLog.Calls.CACHED_PHOTO_URI)

        while(c.moveToNext()) {
            mt("${c.getString(colName)} -- ${c.getString(colNumber)} -- ${c.getInt(colType)} -- ${c.getString(colPhotoUri)}")
        }
    }

    private fun readSettings() {

        var c = contentResolver.query(
            android.provider.Settings.System.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        var colName = c!!.getColumnIndex(Settings.System.NAME)

        while(c!!.moveToNext()) {
            mt("${c.getString(1)} --> ${c.getString(2)}")
        }
        c.close()


    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}