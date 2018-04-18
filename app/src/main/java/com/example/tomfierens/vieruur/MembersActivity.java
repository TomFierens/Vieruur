package com.example.tomfierens.vieruur;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tomfierens.vieruur.data.MembersListContract;
import com.example.tomfierens.vieruur.data.MembersListDbHelper;

import org.json.JSONArray;
import org.json.JSONException;

public class MembersActivity extends AppCompatActivity {

    private MembersListAdapter mAdapter;
    private RecyclerView mMembersList;
    private Cursor mCursor;
    private SQLiteDatabase mDb;
    private EditText mNewMemberNameEditText;
    private EditText mNewStartConsumptionsEditText;
    private Spinner groupSpinner;
    private ArrayAdapter<CharSequence> groupAdapter;

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        groupSpinner = (Spinner)findViewById(R.id.group_spinner);
        groupAdapter = ArrayAdapter.createFromResource(this, R.array.memberGroups,android.R.layout.simple_spinner_item);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupAdapter);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mMembersList = (RecyclerView) this.findViewById(R.id.rv_members);

        mNewMemberNameEditText = (EditText) findViewById(R.id.member_name_edit_text);
        mNewStartConsumptionsEditText = (EditText) findViewById(R.id.consumptions_edit_text);
        //mNewGroupEditText = (EditText) findViewById(R.id.group_edit_text);

        mMembersList.setLayoutManager(new LinearLayoutManager(this));

        MembersListDbHelper dbHelper = new MembersListDbHelper(this);

        mDb = dbHelper.getWritableDatabase();

        mCursor = getAllMembers();

        mAdapter = new MembersListAdapter(this, mCursor);

        mMembersList.setAdapter(mAdapter);

    }

    private Cursor getAllMembers(){
       return mDb.query(
               MembersListContract.MembersListEntry.TABLE_NAME,
               null,
               null,
               null,
               null,
               null,
               MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS

       );
    }
    public void addToMembersList(View view){
        if(mNewMemberNameEditText.getText().length() == 0 || mNewStartConsumptionsEditText.getText().length() == 0 || groupSpinner.getSelectedItem().toString().length() == 0){
            return;
        }
        int consumptions = 1;
        try {
            consumptions = Integer.parseInt(mNewStartConsumptionsEditText.getText().toString());
        }
        catch(NumberFormatException ex){

            Log.e(LOG_TAG, "Failed to parse consumptions text to number: " + ex.getMessage());

        }
        addNewMember(mNewMemberNameEditText.getText().toString(), consumptions, groupSpinner.getSelectedItem().toString());
        mAdapter.swapCursor(getAllMembers());
        mNewStartConsumptionsEditText.clearFocus();;
        mNewMemberNameEditText.getText().clear();
        mNewStartConsumptionsEditText.getText().clear();

        //mNewGroupEditText.getText().clear();
    }
    private long addNewMember(String name, int consumptions, String memberGroup){
        ContentValues cv = new ContentValues();
        cv.put(MembersListContract.MembersListEntry.COLUMN_MEMBER_NAME, name);
        cv.put(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS, consumptions);
        cv.put(MembersListContract.MembersListEntry.COLUMN_GROUP, memberGroup);
        return mDb.insert(MembersListContract.MembersListEntry.TABLE_NAME, null,cv);
    }
}
