package com.example.notekeeper.Notes;

import java.util.ArrayList;

public class NoteDetails {

    private ArrayList<String> noteTitle = new ArrayList<>();
    private ArrayList<String> noteContent =new ArrayList<>();

    public NoteDetails(String noteTitle, String noteContent) {
        this.noteTitle.add(noteTitle);
        this.noteContent.add(noteContent);
    }

    public NoteDetails(){}


    public String getNoteTitle(int i) {
        return noteTitle.get(i);
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle.add(noteTitle);
    }

    public String getNoteContent(int i) {
        return noteContent.get(i);
    }

    public void setNoteContent(String noteContent) {
        this.noteContent.add(noteContent);
    }

    public void removeNoteTitle(int i){
        if(noteTitle.size() > 0){
            noteTitle.remove(i - 1);

            if(noteTitle.size() == 1){
                noteTitle.remove(0);
            }
        }
    }

    public void removeNoteContent(int i){
        if(noteContent.size() > 0){
            noteContent.remove(i - 1);

            if(noteTitle.size() == 1){
                noteTitle.remove(0);
            }
        }
    }

    public int NoteDetailSize(){
        return noteTitle.size();
    }

}
