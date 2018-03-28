package com.example.tomfierens.vieruur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AdjustActivity extends AppCompatActivity {
    private Button addMember, addLeader, removeMember, removeLeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust);
        addMember = (Button) findViewById(R.id.button_addMembers);
        addLeader = (Button) findViewById(R.id.button_addLeader);
        removeMember = (Button) findViewById(R.id.button_removeMember);
        removeLeader = (Button) findViewById(R.id.button_removeLeader);
    }
}
