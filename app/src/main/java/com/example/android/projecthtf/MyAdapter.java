package com.example.android.projecthtf;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        User user =list.get(position);
        holder.bedNo.setText(String.valueOf(user.getBedNo()));
        holder.age.setText(String.valueOf(user.getAge()));
       holder.progressBp.setProgress(user.getBp());
       holder.textBP.setText(String.valueOf(user.getBp()));
       holder.progressTemp.setProgress(user.getTemperature());
        holder.textTemp.setText(String.valueOf(user.getTemperature()));
       holder.progressSpo2.setProgress(user.getSpo2());
        holder.textSpo2.setText(String.valueOf(user.getSpo2()));
       holder.name.setText(user.getName());
       holder.gender.setText(user.getGender());
       if(user.getGender().equals("male")||user.getGender().equals("Male")||user.getGender().equals("m")||user.getGender().equals("M"))
       {
           holder.imgPatient.setImageResource(R.drawable.man);
       }
       else if (user.getGender().equals("female")||user.getGender().equals("Female")||user.getGender().equals("F")||user.getGender().equals("f")){
           holder.imgPatient.setImageResource(R.drawable.woman);
       }

       holder.phone.setText(user.getPhone());

     if(user.getSpo2()<96)
     {
         holder.cardView.setCardBackgroundColor(Color.RED);
     }
     else{
         holder.cardView.setCardBackgroundColor(Color.GREEN);
     }
       holder.expandBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(list.get(position).isVisibility())
               {
                 holder.hidden_view.setVisibility(View.GONE);
                 list.get(position).visibility=false;
               }
               else
               {
                   holder.hidden_view.setVisibility(View.VISIBLE);
                   list.get(position).visibility=true;

               }
           }
       });
        holder.showBG.setText(user.getBloodGroup());
        holder.showPI.setText(user.getPreviousIllness());
        holder.showCS.setText(user.getCovidStatus());


    }

    @Override
    public int getItemCount() {
         return  list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView bedNo, age,name,gender,phone;
        ProgressBar progressBp,progressTemp,progressSpo2;
        ImageView imgPatient;
        Button expandBtn;
        LinearLayout hidden_view;
        TextView textBP,textTemp,textSpo2;
        TextView showBG,showPI,showCS;
        CardView cardView;
       ;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            bedNo=itemView.findViewById(R.id.textbed);
            age=itemView.findViewById(R.id.textage);
            progressBp=itemView.findViewById(R.id.progressBP);
            progressTemp=itemView.findViewById(R.id.progressTemp);
            progressSpo2=itemView.findViewById(R.id.progressSPO2);
            name=itemView.findViewById(R.id.showName);
            gender=itemView.findViewById(R.id.showGender);
            phone=itemView.findViewById(R.id.showPhone);
            imgPatient=itemView.findViewById(R.id.patientImg);
            expandBtn=itemView.findViewById(R.id.expandBtn);
            hidden_view=itemView.findViewById(R.id.hidden_view);
            textBP=itemView.findViewById(R.id.textBP);
            textSpo2=itemView.findViewById(R.id.textSpo2);
            textTemp=itemView.findViewById(R.id.textTemp);
            showBG=itemView.findViewById(R.id.showBloodGroup);
            showCS=itemView.findViewById(R.id.showCovidStatus);
            showPI=itemView.findViewById(R.id.showPreviousIllness);
            cardView=itemView.findViewById(R.id.card_view);
        }
    }
}
