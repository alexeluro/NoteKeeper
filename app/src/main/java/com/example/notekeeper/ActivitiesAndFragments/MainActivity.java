package com.example.notekeeper.ActivitiesAndFragments;

import android.content.Intent;
import android.os.Bundle;

import com.example.notekeeper.Adapter.CustomAdapter;
import com.example.notekeeper.Notes.NoteDetails;
import com.example.notekeeper.R;
import com.example.notekeeper.ViewModel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView  recyclerView;
    MainActivityViewModel mainActivityViewModel;
    CustomAdapter adapter;
    TextView errorText;
    ImageView errorImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        errorImage = findViewById(R.id.errorImage);
        errorText = findViewById(R.id.errorText);
        recyclerView = findViewById(R.id.recycler_view);
        floatingActionButton = findViewById(R.id.fab);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.initialize(this);
        adapter = new CustomAdapter(this, mainActivityViewModel.getNoteDetails());
        mainActivityViewModel.getData().observe(this, new Observer<ArrayList<NoteDetails>>() {
            @Override
            public void onChanged(ArrayList<NoteDetails> noteDetails) {
                adapter.notifyDataSetChanged();
                if(mainActivityViewModel.isNoteEmpty()){
                    errorImage.setVisibility(View.VISIBLE);
                    errorText.setVisibility(View.VISIBLE);
                }else{
                    errorImage.setVisibility(View.GONE);
                    errorText.setVisibility(View.GONE);
                }

            }
        });

        initRecycler();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Share your thoughts", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), NoteReadable.class);
                startActivity(intent);

            }
        });
    }

    public void initRecycler(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityViewModel.getData().observe(this, new Observer<ArrayList<NoteDetails>>() {
            @Override
            public void onChanged(ArrayList<NoteDetails> noteDetails) {
//                mainActivityViewModel.pullFromRepo();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
