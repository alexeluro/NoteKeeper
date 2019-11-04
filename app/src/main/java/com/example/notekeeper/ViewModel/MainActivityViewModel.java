package com.example.notekeeper.ViewModel;

import android.content.Context;

import com.example.notekeeper.Notes.NoteDetails;
import com.example.notekeeper.Repository.LocalDatabase.DbHandler;
import com.example.notekeeper.Repository.RepositoryHandler;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    MutableLiveData<ArrayList<NoteDetails>> data = new MutableLiveData<>();
    ArrayList<NoteDetails> details = new ArrayList<>();
    RepositoryHandler repoHandler;
    Context context;

    public void initialize(Context context){
        if(data == null){
            return;
        }
        this.context = context;
        repoHandler = new RepositoryHandler().getInstance();
        setNoteDetails();
        data.setValue(repoHandler.getRepoNoteDetails(context).getValue());
    }

    public LiveData<ArrayList<NoteDetails>> getData() {
        return data;
    }

    public ArrayList<NoteDetails> getNoteDetails(){
        if(details == null) {
            details = data.getValue();
        }
//        Why is this "details" null ?
//        details
        return details;
    }

    private void setNoteDetails(){
        DbHandler dbHandler = new DbHandler(context);
        details = repoHandler.pullNotesFromDb(dbHandler);

    }

//    public void pullFromRepo(){
//        DbHandler dbHandler = new DbHandler(context);
//        data.setValue(repoHandler.pullLatestFromDb(dbHandler));
//    }

    public Boolean isNoteEmpty(){
        boolean reply = true;
        setNoteDetails();
        if(details.size() > 0){
            reply = false;
        }

        return reply;
    }

}
