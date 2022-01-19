package com.pawan.sage.quicknotes.feature_note.domain.use_case

import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import com.pawan.sage.quicknotes.feature_note.domain.repository.NoteRepository

class GetNoteByIDUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}