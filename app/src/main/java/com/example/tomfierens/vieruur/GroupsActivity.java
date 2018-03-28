package com.example.tomfierens.vieruur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GroupsActivity extends AppCompatActivity {
    private Button sloebersButton, speelclubButton, rakkersButton, toppersButton, kerelsButton, aspisButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        sloebersButton = (Button) findViewById(R.id.button_sloebers);
        speelclubButton = (Button) findViewById(R.id.button_speelclub);
        rakkersButton = (Button) findViewById(R.id.button_rakkers);
        toppersButton = (Button) findViewById(R.id.button_toppers);
        kerelsButton = (Button) findViewById(R.id.button_kerels);
        aspisButton = (Button) findViewById(R.id.button_aspis);
    }
}
