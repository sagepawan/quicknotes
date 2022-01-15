package com.pawan.sage.quicknotes.feature_note.data.data_source

import androidx.room.*
import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    //query to load all notes from db
    //not assigned suspend since it returns flow
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    //get note by its ID
    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteByID(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}