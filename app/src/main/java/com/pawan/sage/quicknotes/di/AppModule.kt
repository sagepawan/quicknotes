package com.pawan.sage.quicknotes.di

import android.app.Application
import androidx.room.Room
import com.pawan.sage.quicknotes.feature_note.data.data_source.NoteDatabase
import com.pawan.sage.quicknotes.feature_note.data.repository.NoteRepositoryImplementation
import com.pawan.sage.quicknotes.feature_note.domain.repository.NoteRepository
import com.pawan.sage.quicknotes.feature_note.domain.use_case.DeleteNoteUseCase
import com.pawan.sage.quicknotes.feature_note.domain.use_case.GetNotesUseCase
import com.pawan.sage.quicknotes.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //function that provides notes db
    @Provides
    @Singleton
    fun provideNoteDB(application: Application): NoteDatabase{
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    //function that provides notes repository
    @Provides
    @Singleton
    fun provideNoteRepository(dbInstance: NoteDatabase): NoteRepository {
        return  NoteRepositoryImplementation(dbInstance.noteDao)
    }

    //function to access note uses cases
    @Provides
    @Singleton
    fun providesNotesUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNotes = DeleteNoteUseCase(repository)
        )
    }
}