package com.icddrb.enamapppractice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewParticipantActivity extends AppCompatActivity  implements ParticipantRVAdapter.DeleteButtonClicklistener {

    private RecyclerView recyclerView;
    private ParticipantRVAdapter adapter;
    private List<ParticipantModal> participantList;
    private DBHandler dbHandler;

    private Button goToAddParticipant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participant);

        recyclerView = findViewById(R.id.rvParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        goToAddParticipant=findViewById(R.id.idBtnAddNewParticipant);

        participantList = new ArrayList<>();
        dbHandler = new DBHandler(this);

        // Fetch participant data from the database
        participantList = dbHandler.readParticipants();

        //set the adapter to the RecyclerView
        adapter = new ParticipantRVAdapter(this, participantList);
        recyclerView.setAdapter(adapter);


        goToAddParticipant.setOnClickListener(View ->{
            startActivity(new Intent(this,MainActivity.class));

        });
    }


    // EXTRA METHODS - onItemClick(),  onResume() ;

    @Override
    public void onItemClick(String name) {
        Toast.makeText(ViewParticipantActivity.this, "Delete " + name,
                Toast.LENGTH_SHORT).show();
        dbHandler.deleteParticipant(name);
       // ParticipantRVAdapter.submitList(dbHandler.readParticipant());
        adapter.submitList(dbHandler.readParticipants());
    }




    protected void onResume(){
        super.onResume();
        List<ParticipantModal> updatedParticipantList= dbHandler.readParticipants();
        adapter.updateParticipantData(updatedParticipantList);

    }
}
