package com.example.json_from_asset_file;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList<String> names;
    ArrayList<String> emailIds;
    ArrayList<String> mobileNumbers;
    public CustomAdapter(Context context, ArrayList<String> names, ArrayList<String> emailIds, ArrayList<String> mobileNumbers)
    {
        this.context = context;
        this.names = names;
        this.emailIds = emailIds;
        this.mobileNumbers = mobileNumbers;
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.name.setText(names.get(position));
        holder.email.setText(emailIds.get(position));
        holder.mobile_number.setText(mobileNumbers.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, mobile_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            mobile_number = itemView.findViewById(R.id.mobile_number);
        }


    }
}
