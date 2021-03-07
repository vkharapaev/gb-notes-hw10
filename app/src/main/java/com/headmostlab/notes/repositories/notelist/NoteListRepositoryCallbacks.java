package com.headmostlab.notes.repositories.notelist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.headmostlab.notes.model.Note;

import java.util.List;

public interface NoteListRepositoryCallbacks {
    void onSuccess(@NonNull List<Note> notes);

    void onError(@Nullable String message);
}
