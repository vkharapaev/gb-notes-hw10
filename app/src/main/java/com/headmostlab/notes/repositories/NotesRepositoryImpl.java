package com.headmostlab.notes.repositories.notelist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.headmostlab.notes.model.Note;
import com.headmostlab.notes.ui.Constants;

import java.util.ArrayList;
import java.util.List;

public class NotesRepositoryImpl implements NotesRepository {
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final LiveData<List<Note>> notes = new NotesMutableLiveData();

    @Override
    public LiveData<List<Note>> requestNotes() {
        return notes;
    }

    private final class NotesMutableLiveData extends MutableLiveData<List<Note>> {

        private ListenerRegistration listenerRegistration;

        @Override
        protected void onActive() {
            listenerRegistration = firebaseFirestore
                    .collection(Constants.COLLECTION_NOTES).addSnapshotListener((snapshot, error) -> {
                        if (snapshot != null) {
                            List<Note> notes = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : snapshot.getDocuments()) {
                                notes.add(documentSnapshot.toObject(Note.class));
                            }
                            setValue(notes);
                        }
                    });
        }

        @Override
        protected void onInactive() {
            listenerRegistration.remove();
        }
    }
}
