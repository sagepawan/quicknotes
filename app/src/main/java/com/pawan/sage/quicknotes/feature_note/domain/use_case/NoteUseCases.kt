package com.pawan.sage.quicknotes.feature_note.domain.use_case

//variables for each use case accessed from here
data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNotes: DeleteNoteUseCase,
    val addRestoreNote: AddRestoreNote
)
