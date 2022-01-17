package com.pawan.sage.quicknotes.feature_note.domain.use_case

import com.pawan.sage.quicknotes.feature_note.domain.model.InvalidNoteException
import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import com.pawan.sage.quicknotes.feature_note.domain.repository.NoteRepository

class AddRestoreNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        //apply validation first
        if(note.title.isBlank()){
            //now inform view model note was not inserted into db
            throw InvalidNoteException("Title of note can't be empty.")
        }

        if(note.content.isBlank()){
            //now inform view model note was not inserted into db
            throw InvalidNoteException("Content of note can't be empty.")
        }

        repository.insertNote(note)
    }
}