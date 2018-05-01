package com.example.tomfierens.vieruur;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tomfierens.vieruur.Utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {



    TextView mErrorMessageDisplay;
    ProgressBar mLoadingIndicator;
    private SearchListAdapter mAdapter;
    private RecyclerView mSearchList;
    private Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mSearchList = (RecyclerView) this.findViewById(R.id.rv_search);

        mCursor = null;
        mAdapter = new SearchListAdapter(this, mCursor);

        mSearchList.setLayoutManager(new LinearLayoutManager(this));

        mSearchList.setAdapter(mAdapter);

        makeSearchQuery();
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



    public void makeSearchQuery() {

        URL SearchUrl = NetworkUtils.buildUrl();

        new QueryTask().execute(SearchUrl);
    }
    private void showJsonDataView(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mSearchList.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage(){
        mSearchList.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
    public class QueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return searchResults;
        }
        @Override
        protected void onPostExecute(String searchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (searchResults != null && !searchResults.equals("")) {
                showJsonDataView();
                mCursor = getJSONCursor(searchResults);
                mAdapter.swapCursor(mCursor);



                mSearchList.setAdapter(mAdapter);
            } else {
                showErrorMessage();
            }
        }
    }

}
