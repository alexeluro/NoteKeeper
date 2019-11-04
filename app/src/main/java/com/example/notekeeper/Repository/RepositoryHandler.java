package com.example.notekeeper.Repository;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.notekeeper.ActivitiesAndFragments.MainActivity;
import com.example.notekeeper.Notes.NoteDetails;
import com.example.notekeeper.Repository.LocalDatabase.DbHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RepositoryHandler {

    public RepositoryHandler instance;
    public ArrayList<NoteDetails> repoNoteDetails = new ArrayList<>();
//    SQLiteDatabase database = dbHandler.getWritableDatabase();
    Context context;
    NoteDetails notes = new NoteDetails();
    int visitCount = 0;

    public RepositoryHandler getInstance() {
        if(instance == null){
            instance = new RepositoryHandler();
        }
        return instance;
    }


    public MutableLiveData<ArrayList<NoteDetails>> getRepoNoteDetails(Context context) {
        MutableLiveData<ArrayList<NoteDetails>> data = new MutableLiveData<>();
//        DbHandler dbHandler = new DbHandler(this.context);
//        pullNotesFromDb(dbHandler);
        data.setValue(repoNoteDetails);
        return data;
    }

    private boolean isFirstTime(){
        boolean response = true;
        if(visitCount > 0){
            response = false;
        }
        visitCount++;
        return response;
    }

    public ArrayList<NoteDetails> pullNotesFromDb(DbHandler dbHandler){
        Cursor cursor = dbHandler.readNote();
        if(isFirstTime()){
//            if(cursor.getCount() > 0 && repoNoteDetails.size() > 0){
//                for(int i = repoNoteDetails.size()-1; i < repoNoteDetails.size(); i--){
//                    if(i < 0){
//                        break;
//                    }else {
//                        repoNoteDetails.remove(i);
//                    }
//                }
//            }

            while(cursor.moveToNext()){
                notes.setNoteTitle(cursor.getString(1));
                notes.setNoteContent(cursor.getString(2));
                repoNoteDetails.add(notes);
            }
        }else{
            pullLatestFromDb(dbHandler);
        }


        return repoNoteDetails;
    }

    public ArrayList<NoteDetails> pullLatestFromDb(DbHandler dbHandler){
        Cursor cursor = dbHandler.readNote();

        if(cursor.moveToLast()){
            notes.setNoteTitle(cursor.getString(1));
            notes.setNoteContent(cursor.getString(2));
            repoNoteDetails.add(notes);
        }
        return repoNoteDetails;
    }

    public void pushToDb(DbHandler dbHandler, NoteDetails noteDetails){
        long insertRes = dbHandler.addNote(noteDetails.getNoteTitle(noteDetails.NoteDetailSize() - 1),
                noteDetails.getNoteContent(noteDetails.NoteDetailSize() - 1));
        if(insertRes == -1){
            Log.d("RepositoryHandler", "Insert Failed!");
        }else{
            Log.d("RepositoryHandler", "Insert Successful!");
        }
    }

}
