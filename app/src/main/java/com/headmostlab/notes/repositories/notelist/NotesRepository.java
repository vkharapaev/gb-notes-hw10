package com.headmostlab.notes.repositories.notelist;

import androidx.lifecycle.LiveData;

import com.headmostlab.notes.model.Note;

import java.util.List;

public interface NotesRepository {
    LiveData<List<Note>> requestNotes();
}
