package com.example.tomfierens.vieruur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    private Button membersButton, leaderButton, searchButton;
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.button_search);
        usernameTextView = findViewById(R.id.username_text);
        membersButton = findViewById(R.id.button_members);
        leaderButton = findViewById(R.id.button_leaders);

        membersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GroupsActivity.class);
                startActivity(intent);
            }
        });
        leaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LeadersActivity.class);
                startActivity(intent);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        setupSharedPreferences();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected == R.id.action_settings){
            Intent intent = new Intent(this, AdjustActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setUsernameTextView(sharedPreferences.getBoolean(getString(R.string.pref_show_username_key),true));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        loadNameFromSharedPreferences(sharedPreferences);
    }
    public void setUsernameTextView(boolean showTV){
        if(showTV){
            usernameTextView.setVisibility(View.VISIBLE);
        }
        else{
            usernameTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.pref_show_username_key))){
            setUsernameTextView(sharedPreferences.getBoolean(key , true));
        }
        else if(key.equals(getString(R.string.pref_default_username_key))){
            loadNameFromSharedPreferences(sharedPreferences);
        }
    }
    private void loadNameFromSharedPreferences(SharedPreferences sharedPreferences){
        String newName = sharedPreferences.getString(getString(R.string.pref_default_username_key), getString(R.string.pref_default_username));
        usernameTextView.setText(newName);
    }
}
