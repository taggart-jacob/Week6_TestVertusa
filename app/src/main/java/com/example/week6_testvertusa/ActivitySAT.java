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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat);
        tvSchoolName = findViewById(R.id.tvSchoolName);
        tvSATMathAverage = findViewById(R.id.tvSATAverage);
        tvSATWritingAverage = findViewById(R.id.tvSATWritingAverage);
        Intent intent = getIntent();
        RetrofitVertusa retrofitVertusa = new RetrofitVertusa();
        HighSchool highSchool = intent.getParcelableExtra("highschool");

        retrofitVertusa.getService().getSAT("https://data.cityofnewyork.us/resource/s3k6-pzi2.json").enqueue(new Callback<ArrayList<SAT>>() {
            @Override
            public void onResponse(Call<ArrayList<SAT>> call, Response<ArrayList<SAT>> response) {
                ArrayList<SAT> sats = response.body();
                Log.d("TAG", sats.get(0).getSatMathAvgScore());
                ActivitySAT.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < sats.size(); i++) {
                            if (sats.get(i).getDbn().equals(highSchool.getDbn())) {
                                tvSchoolName.setText(highSchool.getSchoolName());
                                tvSATMathAverage.setText(sats.get(i).getSatMathAvgScore());
                                tvSATWritingAverage.setText(sats.get(i).getSatWritingAvgScore());
                                break;
                            }
                    }
                }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<SAT>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });
    }
}
