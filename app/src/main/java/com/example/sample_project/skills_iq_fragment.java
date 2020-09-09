
package com.example.sample_project;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class skills_iq_fragment extends Fragment {
    View v ;
    private Context mContext;
    private RecyclerView mRecyclerView ;
    private GoogleBoardPagerAdapter mGoogleBoardPagerAdapter;
    private LinearLayoutManager mLeaderLinearLayout ;
    private ArrayList<GoogleLearningBoard> dataList ;
    private SkillsRecyclerAdapter mRecyclerAdapter;
    private List<GoogleLearningBoard> skillsDataList = new ArrayList<>() ;

    public skills_iq_fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.fragment_skills_iq, container, false);
        mRecyclerView =(RecyclerView)v.findViewById(R.id.recycler_skill_iq);
      //  UsingRetrofit();
        new populateData().execute() ;

        return  v ;
    }

    public  void usingDummyData(){
        skillScore_dummyData();
        mRecyclerAdapter = new SkillsRecyclerAdapter(mContext , dataList );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList =  new ArrayList<>() ;
        this.mContext = getContext() ;

        // usingDummyData();

    //   new populateData().execute() ;

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void skillScore_dummyData() {
        dataList.add(new GoogleLearningBoard("Zara", 145 , "nigeria" ,"")) ;
        dataList.add(new GoogleLearningBoard("Lara", 130 , "france" ,"")) ;
        dataList.add(new GoogleLearningBoard("lopex", 23,  "nigeria" ,"")) ;
        dataList.add(new GoogleLearningBoard("billing", 45 , "cameroon" ,"")) ;
        dataList.add(new GoogleLearningBoard("gef bezoz", 15 , "usa" ,"")) ;
    }

    private void displaySkillsData(){

        mLeaderLinearLayout = new LinearLayoutManager( getActivity() ) ;
         mRecyclerView.setLayoutManager(mLeaderLinearLayout);
        mRecyclerView.setAdapter( mRecyclerAdapter);
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
            String url = "https://gadsapi.herokuapp.com/api/skilliq";
            String jsonStr = sh.makeServiceCall(url) ;
            JSONArray top_20_skills_array ;

            if ( jsonStr != null){
                try{

                    top_20_skills_array = new JSONArray(jsonStr) ;

                    //  JSONObject jsonObject = new JSONObject(jsonStr) ;
                    //  JSONArray  top_20_Learners = jsonObject.getJSONArray("") ;

                    // Getting JSON ARRAY NODE
                    for (int i = 0 ; i < top_20_skills_array.length(); i++ ){
                       // JSONObject c = top_20_skills_array.getJSONObject(i);
                        JSONObject c = new JSONObject(top_20_skills_array.getJSONObject(i).toString()) ;
                        String name = c.getString("name") ;
                        int score = Integer.parseInt(c.getString("score")) ;
                        String country = c.getString("country") ;
                        String badgeUrl = c.getString("badgeUrl") ;

                        dataList.add(new GoogleLearningBoard(name, score,country,badgeUrl));
                    }

                }catch(JSONException e ){
                    e.printStackTrace();
                    displaySnackBar("Exception begining learning hour of json fetch data" + e.getMessage());
                }
            }else{
               displaySnackBar(" Internet Error: while Fectching SkillIq json data");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mRecyclerAdapter = new SkillsRecyclerAdapter(mContext , dataList );
            displaySkillsData();
        }
    }

    public void UsingRetrofit (){
        // using retrofit
        Call<List<GoogleLearningBoard>> call = SkillsAPIclient.apIinterface().getSkills_iq_GoogleLearnData() ;
        call.enqueue(new Callback<List<GoogleLearningBoard>>() {
            @Override
            public void onResponse(Call<List<GoogleLearningBoard>> call, Response<List<GoogleLearningBoard>> response) {

                if(response.isSuccessful()){

                    displayMessage("Request Successful");

                    skillsDataList =response.body();
                    mRecyclerAdapter = new SkillsRecyclerAdapter(mContext , skillsDataList );
                    displaySkillsData();

                }else {
                    displayMessage("An error occured with internet");
                }

            }
            @Override
            public void onFailure(Call<List<GoogleLearningBoard>> call, Throwable t) {
               displayMessage( "An error occured while fetching SkillIq json  data" +t.getLocalizedMessage());
            }
        });
    }

    public void  displaySnackBar( String message ) {
        Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void displayMessage(String message){
        Toast toast = Toast.makeText(getContext(),
               message,
                Toast.LENGTH_LONG);

        toast.show();
    }
}



