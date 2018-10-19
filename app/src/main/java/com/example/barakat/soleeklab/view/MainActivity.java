package com.example.barakat.soleeklab.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.barakat.soleeklab.R;
import com.example.barakat.soleeklab.adapter.CountriesAdapter;
import com.example.barakat.soleeklab.model.Countries;
import com.example.barakat.soleeklab.rest.ApiClient;
import com.example.barakat.soleeklab.rest.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Setting Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(myToolbar);
//        Get Firebase Instance and check if the user is already logged in
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        }
        firebaseUser = firebaseAuth.getCurrentUser();
//      Setting Recycler View and the layout manager
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Getting the response from API
        ApiInterface apiServices = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Countries>> call = apiServices.getAllCountries();
        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, retrofit2.Response<List<Countries>> response) {
                List<Countries> countries = response.body();
                recyclerView.setAdapter(new CountriesAdapter(getApplicationContext(), R.layout.country_row, countries));
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "response : " + t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Toast.makeText(MainActivity.this, "You have logged out", Toast.LENGTH_LONG).show();

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

