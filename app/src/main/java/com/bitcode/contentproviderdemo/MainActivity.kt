package com.bitcode.contentproviderdemo

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var uri = Uri.parse("content://in.bitcode.tasks")

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
        var tasksUri =Uri.withAppendedPath(uri, "tasks")
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
        cursor.close()
    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}