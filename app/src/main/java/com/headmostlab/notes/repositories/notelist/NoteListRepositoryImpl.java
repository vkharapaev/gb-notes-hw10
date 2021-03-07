package com.headmostlab.notes.repositories.notelist;

import com.google.firebase.firestore.FirebaseFirestore;
import com.headmostlab.notes.model.Note;
import com.headmostlab.notes.ui.Constants;

import java.util.List;

public class NoteListRepositoryImpl implements NoteListRepository {
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final NoteListRepositoryCallbacks callbacks;

    public NoteListRepositoryImpl(NoteListRepositoryCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void requestNotes() {
        firebaseFirestore
                .collection(Constants.COLLECTION_NOTES)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.getResult() != null) {
                        List<Note> notes = task.getResult().toObjects(Note.class);
                        callbacks.onSuccess(notes);
                    }
                })
                .addOnFailureListener(e -> callbacks.onError(e.getMessage()));
    }
}
