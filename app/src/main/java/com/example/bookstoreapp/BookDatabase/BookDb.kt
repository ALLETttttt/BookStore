package com.bookstore.BookDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class BookDb: RoomDatabase() {
    abstract fun getBookDao(): BookDao

    companion object {
        fun getBookDb(context: Context): BookDb {
            return Room.databaseBuilder(
                context.applicationContext,
                BookDb::class.java,
                name = "books.db"
            ).build()
        }
    }
}