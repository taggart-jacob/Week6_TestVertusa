package com.example.week6_testvertusa;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HighSchoolRecyclerViewAdapter extends RecyclerView.Adapter<HighSchoolRecyclerViewAdapter.ViewHolder> {

    ArrayList<HighSchool> highSchools;

    public HighSchoolRecyclerViewAdapter(ArrayList<HighSchool> highSchools) {
        this.highSchools = highSchools;
    }

    @NonNull
    @Override
    public HighSchoolRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.high_school_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HighSchoolRecyclerViewAdapter.ViewHolder holder, int position) {

        final HighSchool highSchool = highSchools.get(position);
        holder.tvSchoolName.setText(highSchool.getSchoolName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivitySAT.class);
                intent.putExtra("highschool", highSchools.get(position));
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return highSchools.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSchoolName;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvSchoolName = itemView.findViewById(R.id.tvSchoolName);

        }
    }
}
