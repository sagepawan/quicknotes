package com.pawan.sage.quicknotes.presentation.notes

import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import com.pawan.sage.quicknotes.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    //key events
    //ordering notes
    data class Order(val noteOrder: NoteOrder): NotesEvent()

    //event for deleting note
    data class DeleteNote(val note: Note): NotesEvent()

    //to restore note, called when user decides to undo a note delete action
    object RestoreNote: NotesEvent()

    //event to make changes to order pattern for notes
    object ToggleOrderSection: NotesEvent()
}