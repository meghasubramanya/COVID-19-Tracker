package com.example.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailInfo extends AppCompatActivity {

    private int positionCountry;

    TextView tvCountry,tvCases,tvRecovered,tvActive,tvCritical,tvTodayCases,tvDeaths,tvTodayDeaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);


        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);


        getSupportActionBar().setTitle("Details of "+AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvTodayCases=findViewById(R.id.tvTodayCases);
        tvDeaths=findViewById(R.id.tvDeaths);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);
        tvCountry=findViewById(R.id.tvCountry);

        tvCountry.setText(AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getCases());
        tvActive.setText(AffectedCountries.countryModelsList.get(positionCountry).getActive());
        tvCritical.setText(AffectedCountries.countryModelsList.get(positionCountry).getCritical());
        tvRecovered.setText(AffectedCountries.countryModelsList.get(positionCountry).getRecovered());
        tvTodayCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayCases());
        tvDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayDeaths());
    }

    @Override //for showing back arrow to mainActivity in action bar
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}