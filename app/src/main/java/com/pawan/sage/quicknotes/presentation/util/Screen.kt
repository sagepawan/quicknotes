package com.pawan.sage.quicknotes.presentation.util

//contains all screens and respective routes
sealed class Screen(val route: String){

    object NotesScren: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")

}
