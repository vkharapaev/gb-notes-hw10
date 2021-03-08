package com.headmostlab.notes.ui.notelist;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.headmostlab.notes.model.Note;
import com.headmostlab.notes.repositories.notelist.NoteListRepository;
import com.headmostlab.notes.repositories.notelist.NoteListRepositoryImpl;

import java.util.List;

public class NoteListViewModelImpl extends androidx.lifecycle.ViewModel implements NoteListViewModel {

    public static final String NOTE_KEY = "NOTE";
    private final SavedStateHandle dataStorage;
    private final MutableLiveData<Note> selectedNote = new MutableLiveData<>();
    private final NoteListRepository noteListRepository;

    public NoteListViewModelImpl(SavedStateHandle savedState) {
        this.noteListRepository = new NoteListRepositoryImpl();
        loadNotes();
        dataStorage = savedState;
        Note note = savedState.get(NOTE_KEY);
        if (note != null) {
            selectedNote.setValue(note);
        }
    }

    public LiveData<List<Note>> getNotes() {
        return noteListRepository.requestNotes();
    }

    @Override
    public LiveData<Note> getSelectedNote() {
        return selectedNote;
    }

    @Override
    public void selectNote(int position) {
        Note note = noteListRepository.requestNotes().getValue().get(position);
        selectedNote.setValue(note);
        dataStorage.set(NOTE_KEY, note);
    }

    @Override
    public void deselect() {
        selectedNote.setValue(null);
    }

    @Override
    public void deleteNote() {
        // TODO: 3/7/2021
    }

    @Override
    public void updateNote(Note note) {
        // TODO: 3/7/2021
    }

    private void loadNotes() {
        noteListRepository.requestNotes();
    }
}
