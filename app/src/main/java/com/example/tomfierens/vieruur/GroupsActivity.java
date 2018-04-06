package com.example.tomfierens.vieruur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupsActivity extends AppCompatActivity {
    private Button sloebersButton, speelclubButton, rakkersButton, toppersButton, kerelsButton, aspisButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        sloebersButton = findViewById(R.id.button_sloebers);
        speelclubButton = findViewById(R.id.button_speelclub);
        rakkersButton = findViewById(R.id.button_rakkers);
        toppersButton = findViewById(R.id.button_toppers);
        kerelsButton = findViewById(R.id.button_kerels);
        aspisButton = findViewById(R.id.button_aspis);
        sloebersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupsActivity.this, MembersActivity.class);
                startActivity(intent);
            }
        });
    }
}
