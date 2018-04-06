package com.example.tomfierens.vieruur;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

public class LeadersActivity extends AppCompatActivity {

    private LeadersListAdapter mAdapter;
    private RecyclerView mLeadersList;
    private Cursor mCursor;
    private String sample_response = "[{\"id\":1,\"outstandingAmount\":5,\"leaderName\":\"Tom Fierens\"},{\"id\":2,\"outstandingAmount\":60,\"leaderName\":\"Bram Cuppens\"},{\"id\":3,\"outstandingAmount\":21,\"leaderName\":\"Jordy Blocken\"}]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaders);

        mLeadersList = (RecyclerView) this.findViewById(R.id.rv_leaders);

        mCursor = getJSONCursor(sample_response);
        mAdapter = new LeadersListAdapter(this, mCursor);

        mLeadersList.setLayoutManager(new LinearLayoutManager(this));

        mLeadersList.setAdapter(mAdapter);

    }
    private Cursor getJSONCursor(String response){
        try{
            JSONArray array = new JSONArray(response);
            return new JSONArrayCursor(array);
        } catch(JSONException exception)
        {
            String ex = exception.getMessage();
        }
        return null;
    }
}