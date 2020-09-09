package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class Lunch_Screen extends AppCompatActivity {

    ProgressBar progressBar ;
    View v ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch__screen);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(3000);
        progressBar.setVisibility(v.VISIBLE );

        new goto_MainActivity().execute() ;
    }


    private class goto_MainActivity extends AsyncTask<Void, Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(1);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i= 0; i <= 3000; i++){
                    publishProgress(i);
                }

            }catch ( Exception e ){

                }

            return null;
        }
        @Override
        protected void onPostExecute( Void result) {
           // super.onPostExecute(result);
            progressBar.setVisibility(v.GONE);
            Intent intent = new Intent(Lunch_Screen.this, MainActivity.class) ;
            startActivity(intent);
            finish();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
           // super.onProgressUpdate(values);
            int progressValues = values[0] ;
            progressBar.setProgress(progressValues);
        }


    }


}
