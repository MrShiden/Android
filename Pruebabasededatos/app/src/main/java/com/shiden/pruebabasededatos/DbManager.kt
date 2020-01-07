package com.shiden.pruebabasededatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder

class DbManager {

    val dbName = "MistyGame"
    val dbTable = "LogGame"
    val colID = "ID"
    val colAccion = "Accion"
    val colPlayer = "Player"
    val colDes = "Descripcion"
    val dbVersion = 1

    val sqlCreateTable =
        "CREATE TABLE IF NOT EXISTS $dbTable ($colID INTEGER PRIMARY KEY, $colAccion TEXT, $colPlayer TEXT,  $colDes TEXT);"



    var sqlDB: SQLiteDatabase? = null

    constructor(context: Context) {

        var db = DatabaseHelperNotes(context)
        sqlDB = db.writableDatabase


    }

    inner class DatabaseHelperNotes : SQLiteOpenHelper {

        var context: Context? = null

        constructor(context: Context) : super(context, dbName, null, dbVersion) {

            this.context = context

        }

        override fun onCreate(db: SQLiteDatabase?) {

            db!!.execSQL(sqlCreateTable)

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS $dbTable")

        }


    }

    fun Insert(values: ContentValues):Long{


        val id= sqlDB!!.insert(dbTable,"",values)
        return id


    }

    fun Query(projection: Array<String>,selection: String, selectionArgs:Array<String>,sorOrder:String): Cursor {

        var qb= SQLiteQueryBuilder()
        qb.tables=dbTable
        val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null, sorOrder)
        return cursor

    }


    fun Delete(selection:String,selectionArgs:Array<String>):Int{

        val count = sqlDB!!.delete(dbTable,selection,selectionArgs)


        return count

    }

    fun DeleteData(): Int {


        val db = sqlDB!!.delete(dbTable, null,null)
        return db


    }


    fun Update(values:ContentValues,selection:String,selectionargs:Array<String>):Int{

        val count=sqlDB!!.update(dbTable,values,selection,selectionargs)
        return count
    }


}