package com.ai.girl.friend.chatting.databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ai.girl.friend.chatting.datamodels.NewChatModel

class ChatDbHelper(val context: Context) : SQLiteOpenHelper(context, "chatdb", null, 1) {
    val DB_NAME = "chatdb"
    val TABLE_NAME = "mychat"
    val COLUMN_IS_FROM_HISTORY = "is_from_history"
    val COLUMN_MESSAGE = "answer"
    val COLUMN_QUESTION_TYPE = "que_type"
    val COLUMN_SENDER = "question"
    val COLUMN_SENDER_IMAGE = "image_data"
    val COLUMN_SENDER_TYPE = "sender_type"
    val ID_COL = "id"
    val QUESTION_COL = "question"
    val ANSWER_COL = "answer"
    val QUE_COL = "que_type"
    val IMAGE_DATA_COL = "imagedata"
    val SENDER_COL = "sender"

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE ${TABLE_NAME} (${ID_COL} INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_SENDER TEXT,$COLUMN_MESSAGE TEXT,$COLUMN_QUESTION_TYPE TEXT,$COLUMN_SENDER_IMAGE INTEGER,$COLUMN_SENDER_TYPE TEXT,$COLUMN_IS_FROM_HISTORY TEXT)")
    }

    fun insertChatMessage(newChatModel: NewChatModel) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.apply {
            put(COLUMN_SENDER, newChatModel.questionText)
            put(COLUMN_SENDER_IMAGE, newChatModel.questionImage)
            put(COLUMN_QUESTION_TYPE, newChatModel.questionType)
            put(COLUMN_SENDER_TYPE, newChatModel.senderType)
            put(COLUMN_MESSAGE, newChatModel.ansText)
            put(COLUMN_IS_FROM_HISTORY, newChatModel.isFromHistory)
            database.insert(TABLE_NAME, null, contentValues)
            database.close()
        }
    }

    fun getAllChatMessage(): List<NewChatModel> {
        val rawquery = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val arrayList = mutableListOf<NewChatModel>()
        if (rawquery.moveToFirst()) {
            do {
                val newChatModel = NewChatModel()
                newChatModel.id = rawquery.getInt(rawquery.getColumnIndexOrThrow(ID_COL))
                newChatModel.questionText =
                    rawquery.getString(rawquery.getColumnIndexOrThrow(COLUMN_SENDER))
                newChatModel.questionImage =
                    rawquery.getInt(rawquery.getColumnIndexOrThrow(COLUMN_SENDER_IMAGE))
                newChatModel.questionType =
                    rawquery.getString(rawquery.getColumnIndexOrThrow(COLUMN_QUESTION_TYPE))
                newChatModel.senderType =
                    rawquery.getString(rawquery.getColumnIndexOrThrow(COLUMN_SENDER_TYPE))
                newChatModel.ansText =
                    rawquery.getString(rawquery.getColumnIndexOrThrow(COLUMN_MESSAGE))
                newChatModel.isFromHistory =
                    rawquery.getString(rawquery.getColumnIndexOrThrow(COLUMN_IS_FROM_HISTORY))
                arrayList.add(newChatModel)

            } while (rawquery.moveToNext())
        }
        return arrayList
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}