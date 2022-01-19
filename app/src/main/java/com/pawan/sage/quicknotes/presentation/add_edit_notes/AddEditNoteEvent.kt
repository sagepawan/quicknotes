package com.pawan.sage.quicknotes.presentation.add_edit_notes

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent{
    //to listen for each keystroke change in title and content parameter
    data class EnteredTitle(val value: String): AddEditNoteEvent()
    data class EnteredContent(val value: String): AddEditNoteEvent()

    //to listen for change in focus in title and content field
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()

    //when note color is changed
    data class ChangeColor(val color: Int): AddEditNoteEvent()

    //when note is saved using float button
    object SaveNote: AddEditNoteEvent()
}