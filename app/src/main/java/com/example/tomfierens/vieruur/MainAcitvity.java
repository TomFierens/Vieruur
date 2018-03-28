package com.example.tomfierens.vieruur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAcitvity extends AppCompatActivity {
    private Button adjustButton, membersButton, leaderButton;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        membersButton = (Button) findViewById(R.id.button_members);
        leaderButton = (Button) findViewById(R.id.button_leaders);
        adjustButton = (Button) findViewById(R.id.button_adjust);
        System.out.println("hello");
        adjustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAcitvity.this, AdjustActivity.class);
                startActivity(intent);
            }
        });
        membersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAcitvity.this, GroupsActivity.class);
                startActivity(intent);
            }
        });
    }
}
