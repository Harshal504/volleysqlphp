package com.hammer.volleysqlphp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Rviewadapter extends RecyclerView.Adapter<Rviewadapter.ViewHolder> {

Context context;
List<Registrationmodel> registrationmodels;

    public Rviewadapter(List<Registrationmodel> registrationmodels, Context context) {
        super();
        this.registrationmodels= registrationmodels;
        this.context = context;
    }

    @Override
    public Rviewadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rviewadapter,parent,false);
       ViewHolder viewHolder=new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Rviewadapter.ViewHolder holder, int position) {
        Registrationmodel registrationmodel=registrationmodels.get(position);
        holder.vidtext.setText(registrationmodel.getId());
        holder.vnametext.setText(registrationmodel.getName());
        holder.vemailtext.setText(registrationmodel.getEmail_Id());
        holder.vpasstext.setText(registrationmodel.getPassword());
    }

    @Override
    public int getItemCount() {

        return registrationmodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
public TextView vidtext,vnametext,vemailtext,vpasstext;

        public ViewHolder(View itemView) {
            super(itemView);
            vidtext=(TextView)itemView.findViewById(R.id.vidtext);
            vnametext=(TextView)itemView.findViewById(R.id.vnametext);
            vemailtext=(TextView)itemView.findViewById(R.id.vemailtext);
            vpasstext=(TextView)itemView.findViewById(R.id.vpasstext);

        }
    }
}
