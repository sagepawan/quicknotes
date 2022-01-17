package com.pawan.sage.quicknotes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import com.pawan.sage.quicknotes.feature_note.domain.use_case.NoteUseCases
import com.pawan.sage.quicknotes.feature_note.domain.util.NoteOrder
import com.pawan.sage.quicknotes.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    //requirements -
    //states that define current note order, list of notes, state to decide if order section visible or not

    //contains all possible states for notes
    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }


    //to keep track of last deleted note for restoring purpose when required
    private var lastDeletedNote: Note? = null

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                //has the order already changed?
                //using class postfix because without it, we will only compare the noteOrder reference
                if(state.value.noteOrder::class == event.noteOrder::class &&
                        state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }

                getNotes(event.noteOrder)

            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNotes(event.note)
                    lastDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addRestoreNote(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){

        //everytime getNotes is called, new flow will be called
        //this mean old coroutine needs to be cancelled everytime

        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}

