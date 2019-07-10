package com.example.week6_testvertusa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvHighSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHighSchools = findViewById(R.id.rvHighSchools);
        RetrofitVertusa retrofitVertusa = new RetrofitVertusa();

        retrofitVertusa.getService().getHighSchools("https://data.cityofnewyork.us/resource/s3k6-pzi2.json").enqueue(new Callback<ArrayList<HighSchool>>() {
            @Override
            public void onResponse(Call<ArrayList<HighSchool>> call, Response<ArrayList<HighSchool>> response) {
                ArrayList<HighSchool> highSchools = response.body();
                populateRVHighSchool(highSchools);
            }

            @Override
            public void onFailure(Call<ArrayList<HighSchool>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });

    }

    private void populateRVHighSchool(ArrayList<HighSchool> highSchools){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        HighSchoolRecyclerViewAdapter adapter = new HighSchoolRecyclerViewAdapter(highSchools);
        rvHighSchools.setLayoutManager(layoutManager);
        rvHighSchools.setAdapter(adapter);
    }
}
