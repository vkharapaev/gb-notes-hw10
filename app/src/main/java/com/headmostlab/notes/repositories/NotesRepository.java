package com.headmostlab.notes.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.headmostlab.notes.Event;
import com.headmostlab.notes.model.Note;

import java.util.List;

public interface NotesRepository {
    LiveData<List<Note>> requestNotes();

    LiveData<Event<String>> delete(@NonNull String noteId);

    LiveData<Event<String>> update(@NonNull Note note);
}
