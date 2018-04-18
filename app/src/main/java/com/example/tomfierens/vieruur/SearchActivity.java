package com.example.tomfierens.vieruur;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tomfierens.vieruur.Utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {
    EditText mSearchBoxEditText;
    TextView mUrlDispayTextView;
    TextView mSearchResults;
    Button mSearchQueryButton;
    TextView mErrorMessageDisplay;
    ProgressBar mLoadingIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchBoxEditText = (EditText) findViewById(R.id.search_box);
        mUrlDispayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResults = (TextView) findViewById(R.id.search_results);
        mSearchQueryButton = (Button) findViewById(R.id.search_query_button);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
    }



    public void makeGithubSearchQuery(View view) {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDispayTextView.setText(githubSearchUrl.toString());
        String githubSearchResults = null;
        new GithubQueryTask().execute(githubSearchUrl);
    }
    private void showJsonDataView(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mSearchResults.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage(){
        mSearchResults.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
    public class GithubQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }
        @Override
        protected void onPostExecute(String githubSearchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                showJsonDataView();
                mSearchResults.setText(githubSearchResults);
            } else {
                showErrorMessage();
            }
        }
    }

}
