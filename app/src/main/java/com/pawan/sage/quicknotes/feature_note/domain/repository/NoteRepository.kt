package com.pawan.sage.quicknotes.feature_note.domain.repository

import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

//load data from cache or api use case
//interface for ease of creating test cases
interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}