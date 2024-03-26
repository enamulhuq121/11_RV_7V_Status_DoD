package com.icddrb.enamapppractice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParticipantRVAdapter extends RecyclerView.Adapter<ParticipantRVAdapter.ViewHolder> {

    private Context context;
    private List<ParticipantModal> participantList;
    private Button deleteItem;
    public DeleteButtonClicklistener deleteButtonClickListener;
    public ParticipantRVAdapter(Context context, List<ParticipantModal> participantList) {
        this.context = context;
        this.participantList = participantList;
        this.deleteButtonClickListener= (DeleteButtonClicklistener) context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.participant_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParticipantModal participant = participantList.get(position);

        holder.tvParticipantName.setText(participant.getName());
        holder.tvParticipantGender.setText(participant.getGender());
        holder.tvParticipantStatus.setText(participant.getStatus());
        holder.tvParticipantDOB.setText(participant.getDateOfBirth());
        holder.tvParticipantDOD.setText(participant.getDateOfDeath());
        holder.tvParticipantOccupation.setText(participant.getOccupation());
        holder.tvParticipantHobbies.setText(participant.getHobbies());

        // onClickLister for going to UpdateParticipantActivity.java for updating data

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int participantId= participant.getId();
                //String participantName

                Intent intent= new Intent(view.getContext(),UpdateParticipantActivity.class);
                intent.putExtra("PARTICIPANT_ID", participantId);
                intent.putExtra("status", participant.getStatus());
                intent.putExtra("dob",participant.getDateOfBirth());
                intent.putExtra("dod", participant.getDateOfDeath());
                intent.putExtra("occu", participant.getOccupation());
                intent.putExtra("hobbies", participant.getHobbies());

                 view.getContext().startActivity(intent);

          }
        });
    }

    @Override
    public int getItemCount() {
        return participantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvParticipantName;
        TextView tvParticipantGender;
        TextView tvParticipantStatus;
        TextView tvParticipantDOB;
        TextView tvParticipantDOD;
        TextView tvParticipantOccupation;
        TextView tvParticipantHobbies;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvParticipantName = itemView.findViewById(R.id.tvParticipantName);
            tvParticipantGender = itemView.findViewById(R.id.tvParticipantGender);
            tvParticipantStatus = itemView.findViewById(R.id.tvParticipantStatus);
            tvParticipantDOB = itemView.findViewById(R.id.tvParticipantDOB);
            tvParticipantDOD = itemView.findViewById(R.id.tvParticipantDOD);
            tvParticipantOccupation = itemView.findViewById(R.id.tvParticipantOccupation);
            tvParticipantHobbies = itemView.findViewById(R.id.tvParticipantHobbies);

            //Initializing variable with the delete button
            deleteItem=itemView.findViewById(R.id.idBtnDeleteItem);
          //  deleteButtonClickListener = listener;

            deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(deleteButtonClickListener!=null){
                        ParticipantModal modal=participantList.get(getAdapterPosition());
                        deleteButtonClickListener.onItemClick(modal.getName());
                    }

                }
            });
        }
    }
    public void updateParticipantData(List<ParticipantModal> updatedParticipantList){
        participantList = updatedParticipantList;
        notifyDataSetChanged();

    }

    public  void submitList(List<ParticipantModal> participantModalArrayList){
        this.participantList=participantModalArrayList;
        notifyDataSetChanged();
    }
    public interface DeleteButtonClicklistener{
        public void onItemClick(String name);
    }
}

