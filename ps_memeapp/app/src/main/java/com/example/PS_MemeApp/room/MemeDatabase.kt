package com.example.PS_MemeApp.room

import com.example.PS_MemeApp.model.Meme
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Meme::class], version = 1)
abstract class MemeDatabase: RoomDatabase(){

    abstract fun memeDao(): Roomdao

    companion object{
        private var INSTANCE : MemeDatabase? = null
        fun getDatabase(context: Context): MemeDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MemeDatabase::class.java,
                    "memesDB"
                ).build()
            }
            return INSTANCE!!
        }
    }

}