package com.headmostlab.notes.ui.notelist;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.headmostlab.notes.repositories.notelist.NoteListRepository;
import com.headmostlab.notes.repositories.notelist.NoteListRepositoryCallbacks;
import com.headmostlab.notes.repositories.notelist.NoteListRepositoryImpl;
import com.headmostlab.notes.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteListViewModelImpl extends androidx.lifecycle.ViewModel implements NoteListViewModel, NoteListRepositoryCallbacks {

    public static final String NOTE_KEY = "NOTE";
    private final SavedStateHandle dataStorage;
    private MutableLiveData<Note> selectedNote = new MutableLiveData<>();
    private MutableLiveData<Integer> deletedNote = new MutableLiveData<>();
    private final NoteListRepository noteListRepository;
    private int lastId;

    public NoteListViewModelImpl(SavedStateHandle savedState) {
        this.noteListRepository = new NoteListRepositoryImpl(this);
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
/*        Note note = selectedNote.getValue();
        if (note != null) {
            List<Note> notes = notesLiveData.getValue();
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).getId().equals(note.getId())) {
                    notes.remove(i);
                    notesLiveData.setValue(notes);
                    selectedNote.setValue(null);
                    break;
                }
            }
        }*/
    }

    @Override
    public void updateNote(Note note) {
/*        if (note != null) { // add
            if (note.getId() == null) {
                note.setId(nextId());
                List<Note> notes = notesLiveData.getValue();
                notes.add(0, note);
                notesLiveData.setValue(notes);
            } else { // update
                List<Note> notes = notesLiveData.getValue();
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).getId().equals(note.getId())) {
                        notes.remove(i);
                        notes.add(i, note);
                        notesLiveData.setValue(notes);
                        break;
                    }
                }
            }
        }*/
    }

    private void loadNotes() {
        noteListRepository.requestNotes();
    }

    private ArrayList<Note> createNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            notes.add(new Note(nextId(), "Note " + i, "Note " + i + " Description", new Date()));
        }
        return notes;
    }

    private String nextId() {
        return String.valueOf(++lastId);
    }

    @Override
    public void onSuccess(@NonNull List<Note> notes) {
//        notesLiveData.setValue(new ArrayList<>(notes));
    }

    @Override
    public void onError(@Nullable String message) {
        Log.e("TAG", "onError: " + message);
        // TODO: 3/7/2021
    }
}
