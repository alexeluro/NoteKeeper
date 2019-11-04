package com.example.notekeeper.ActivitiesAndFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.EditText;

import com.example.notekeeper.Notes.NoteDetails;
import com.example.notekeeper.R;
import com.example.notekeeper.Repository.LocalDatabase.DbHandler;
import com.example.notekeeper.Repository.RepositoryHandler;
import com.example.notekeeper.ViewModel.MainActivityViewModel;

public class NoteReadable extends AppCompatActivity {
    EditText noteHeader, noteBody;
    MainActivity mainActivity = new MainActivity();
    DbHandler dbHandler = new DbHandler(this);
    RepositoryHandler repositoryHandler = new RepositoryHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_readable);

        noteHeader = findViewById(R.id.note_header);
        noteBody = findViewById(R.id.note_body);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NoteDetails noteDetails = new NoteDetails();
        String title = noteHeader.getText().toString().trim();
        String content = noteBody.getText().toString().trim();
        if(!title.isEmpty() || !content.isEmpty()){
            noteDetails.setNoteTitle(title);
            noteDetails.setNoteContent(content);
            repositoryHandler.pushToDb(dbHandler, noteDetails);
        }

    }
}
