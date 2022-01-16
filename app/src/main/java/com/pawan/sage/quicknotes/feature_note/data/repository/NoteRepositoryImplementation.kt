package com.pawan.sage.quicknotes.feature_note.data.repository

import com.pawan.sage.quicknotes.feature_note.data.data_source.NoteDao
import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import com.pawan.sage.quicknotes.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

//Implementation of domain layer repository
class NoteRepositoryImplementation(
    private val dao: NoteDao
): NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteByID(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}