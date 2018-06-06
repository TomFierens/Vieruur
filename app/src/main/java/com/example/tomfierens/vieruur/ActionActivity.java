package com.example.tomfierens.vieruur;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.tomfierens.vieruur.data.MembersListContract;
import com.example.tomfierens.vieruur.data.MembersListDbHelper;



public class ActionActivity extends AppCompatActivity {
    private TextView memberName;
    private String nameFilter;
    private String groupFilter;
    private Cursor mCursor;
    private SQLiteDatabase mDb;
    private String memberConsumptions;
    private TextView consumptionsCircle;
    private int consumptionNumber;
    private String memberID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        memberName = findViewById(R.id.memberName_text);
        consumptionsCircle = findViewById(R.id.circle_consumptions);
        nameFilter = getIntent().getStringExtra("MemberName");
        memberName.setText(nameFilter);
        groupFilter = getIntent().getStringExtra("GroupName");
        MembersListDbHelper dbHelper = new MembersListDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        mCursor = getCurrentMember();
        mCursor.moveToFirst();
        memberID = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry._ID));
        memberConsumptions = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS));
        setCircle();

    }
    private Cursor getCurrentMember(){
        return mDb.query(
                MembersListContract.MembersListEntry.TABLE_NAME,
                null,
                "memberName = ? AND memberGroup = ?",
                new String[]{nameFilter, groupFilter},
                null,
                null,
                MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS

        );
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setCircle(){

        consumptionsCircle.setText(memberConsumptions);
        consumptionNumber = Integer.parseInt(memberConsumptions);

        if(consumptionNumber <= 6 && consumptionNumber > 0){
            consumptionsCircle.getBackground().setTint(Color.rgb(234,114,9));
        }
        else if(consumptionNumber <= 0){
            consumptionsCircle.getBackground().setTint(Color.rgb(234, 9, 9));
        }
        else{
            consumptionsCircle.getBackground().setTint(Color.rgb(106,163,27));
        }

    }
    public void pushConsumption(View view){
        int newConsumptions = consumptionNumber - 1;
        consumptionNumber = newConsumptions;
        ContentValues cv = new ContentValues();
        cv.put(MembersListContract.MembersListEntry.COLUMN_MEMBER_NAME, nameFilter);
        cv.put(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS, newConsumptions);
        cv.put(MembersListContract.MembersListEntry.COLUMN_GROUP, groupFilter);
        mDb.update(MembersListContract.MembersListEntry.TABLE_NAME, cv, "_id = " + memberID, null);
        mCursor = getCurrentMember();
        mCursor.moveToFirst();
        memberID = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry._ID));
        memberConsumptions = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS));
        setCircle();


    }
    public void add12(View view){
        int newConsumptions = consumptionNumber + 12;
        consumptionNumber = newConsumptions;
        ContentValues cv = new ContentValues();
        cv.put(MembersListContract.MembersListEntry.COLUMN_MEMBER_NAME, nameFilter);
        cv.put(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS, newConsumptions);
        cv.put(MembersListContract.MembersListEntry.COLUMN_GROUP, groupFilter);
        mDb.update(MembersListContract.MembersListEntry.TABLE_NAME, cv, "_id = " + memberID, null);
        mCursor = getCurrentMember();
        mCursor.moveToFirst();
        memberID = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry._ID));
        memberConsumptions = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS));
        setCircle();
    }
    public void add6(View view){
        int newConsumptions = consumptionNumber + 6;
        consumptionNumber = newConsumptions;
        ContentValues cv = new ContentValues();
        cv.put(MembersListContract.MembersListEntry.COLUMN_MEMBER_NAME, nameFilter);
        cv.put(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS, newConsumptions);
        cv.put(MembersListContract.MembersListEntry.COLUMN_GROUP, groupFilter);
        mDb.update(MembersListContract.MembersListEntry.TABLE_NAME, cv, "_id = " + memberID, null);
        mCursor = getCurrentMember();
        mCursor.moveToFirst();
        memberID = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry._ID));
        memberConsumptions = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS));
        setCircle();
    }

}
