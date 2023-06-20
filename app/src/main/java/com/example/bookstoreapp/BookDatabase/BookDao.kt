package com.bookstore.BookDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book)

    @Query("select * from Books")
    fun getAllBook(): Flow<List<Book>>

    @Query("delete from Books where imageId like :Did")
    fun deleteBookById(Did: Int)

    @Query("update Books set title=:title, cost=:cost, description=:description where imageId=:Did")
    fun updateBook(Did: Int, title: String, cost: Int, description: String)

    @Query("select * from Books order by cost")
    fun getAllBookInAscOrder(): Flow<List<Book>>

    @Query("select * from Books order by cost desc")
    fun getAllBookInDescOrder(): Flow<List<Book>>

}