package com.pawan.sage.quicknotes.presentation.add_edit_notes

//another state to check if hint for 'choose a title' is visible or not

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
