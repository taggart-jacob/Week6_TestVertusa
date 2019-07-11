package com.example.week6_testvertusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySAT extends AppCompatActivity {

    TextView tvSchoolName;
    TextView tvSATMathAverage;
    TextView tvSATWritingAverage;
    TextView tvSATReadingAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat);
        tvSchoolName = findViewById(R.id.tvSchoolName2);
        tvSATMathAverage = findViewById(R.id.tvSATAverage);
        tvSATWritingAverage = findViewById(R.id.tvSATWritingAverage);
        tvSATReadingAverage = findViewById(R.id.tvSATReadingAverage);

        Intent intent = getIntent();
        RetrofitVertusa retrofitVertusa = new RetrofitVertusa();
        HighSchool highSchool = intent.getParcelableExtra("highschool");
        tvSchoolName.setText(highSchool.getSchoolName());

        retrofitVertusa.getService(highSchool).getSAT("https://data.cityofnewyork.us/resource/f9bf-2cp4.json").enqueue(new Callback<ArrayList<SAT>>() {
            @Override
            public void onResponse(Call<ArrayList<SAT>> call, Response<ArrayList<SAT>> response) {
                ArrayList<SAT> sats = response.body();
                for (int i = 0; i < sats.size(); i++){
                    if(sats.get(i).getDbn().equals(highSchool.getDbn())){
                        tvSATWritingAverage.setText("Writing SAT Average: " + sats.get(i).getSatWritingAvgScore());
                        tvSATMathAverage.setText("Math SAT Average: " + sats.get(i).getSatMathAvgScore());
                        tvSATReadingAverage.setText("Reading SAT Average: " + sats.get(i).getSatCriticalReadingAvgScore());
                        break;
                    } else{
                        tvSATWritingAverage.setText("No Writing SAT Results Found");
                        tvSATMathAverage.setText("No Math SAT Results Found");
                        tvSATReadingAverage.setText("No Reading SAT Results Found");
                    }
                }
            }


            @Override
            public void onFailure(Call<ArrayList<SAT>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }

        });

    }
}
