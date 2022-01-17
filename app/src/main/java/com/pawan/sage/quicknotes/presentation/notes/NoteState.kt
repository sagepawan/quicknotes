package com.pawan.sage.quicknotes.presentation.notes

import com.pawan.sage.quicknotes.feature_note.domain.model.Note
import com.pawan.sage.quicknotes.feature_note.domain.util.NoteOrder
import com.pawan.sage.quicknotes.feature_note.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending), //descending by default
    val isOrderSectionVisible: Boolean = false
)
