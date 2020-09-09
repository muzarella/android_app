package com.example.sample_project;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample_project.ui.main.GoogleBoardPagerAdapter;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class learn_hourFragment extends Fragment {

    View v;
    private Context mContext;
    private RecyclerView mRecyclerView ;
    private GoogleBoardPagerAdapter mGoogleBoardPagerAdapter;
    private LinearLayoutManager mLeaderLinearLayout ;
    private ArrayList<GoogleLearningBoard> dataList ;
    private LearnRecyclerAdapter mRecyclerAdapter;

    private String TAG = MainActivity.class.getSimpleName();

    public learn_hourFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.fragment_learning_leader, container, false);
        mRecyclerView =(RecyclerView)v.findViewById(R.id.recycler_learning_item);
        this.mContext = getContext() ;

        new populateData().execute() ;

       // Using_DummyData();

        return  v ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList =  new ArrayList<>() ;
        this.mContext = getContext() ;

    }

    public void Using_DummyData(){
        learn_dummyData();
        mRecyclerAdapter = new LearnRecyclerAdapter(mContext , dataList );
    }

    @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  new populateData().execute() ;

    }

    private void learn_dummyData() {
        dataList.add(new GoogleLearningBoard("tunde", 145, "nigeria" ,"R.drawable.toplearner")) ;
        dataList.add(new GoogleLearningBoard("bola", 130, "nigeria" ,"R.drawable.toplearner")) ;
        dataList.add(new GoogleLearningBoard("blessing", 25, "nigeria" ,"R.drawable.toplearner")) ;
        dataList.add(new GoogleLearningBoard("more", 45, "nigeria" ,"R.drawable.toplearner")) ;
        dataList.add(new GoogleLearningBoard("greate", 15, "nigeria" ,"R.drawable.toplearner")) ;
    }

    private void displayLearn(){
        mLeaderLinearLayout = new LinearLayoutManager( getActivity() ) ;
         mRecyclerView.setLayoutManager(mLeaderLinearLayout);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }


    private class populateData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText( getContext(),"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler() ;
            // making a request to url and getting response
            String url = "https://gadsapi.herokuapp.com/api/hours";
            String jsonStr = sh.makeServiceCall(url) ;

            JSONArray top_20_Learners_array ;
            Log.e(TAG, "Response from url: " + jsonStr);
            if ( jsonStr != null){
                try{

                    top_20_Learners_array = new JSONArray(jsonStr) ;

                  //  JSONObject jsonObject = new JSONObject(jsonStr) ;
                  //  JSONArray  top_20_Learners = jsonObject.getJSONArray("") ;

                    // Getting JSON ARRAY NODE

                    for (int i = 0 ; i < top_20_Learners_array.length(); i++ ){

                      //  JSONObject c = top_20_Learners_array.getJSONObject(i);
                      JSONObject c = new JSONObject(top_20_Learners_array.getJSONObject(i).toString()) ;
                        String name = c.getString("name") ;
                        int hours = Integer.parseInt(c.getString("hours") );
                        String country = c.getString("country") ;
                        String badgeUrl = c.getString("badgeUrl") ;

                        dataList.add(new GoogleLearningBoard(name, hours ,country,badgeUrl));
                    }

                }catch( final JSONException e ){
                    e.printStackTrace();
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                    displayMessage("Exception begining learning hour of json fetch data" + e.getMessage());
                }
            }else{
                    displayMessage(" Internet Error: while Fectching SkillIq json data");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mRecyclerAdapter = new LearnRecyclerAdapter(mContext , dataList );
            displayLearn();
        }
    }

    public  void displayMessage(String message){
        Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
