package com.example.sample_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmission extends AppCompatActivity {

    public EditText firstNameEt;
    public EditText lastNameEt;
    public EditText emailEt;
    public EditText gitHubEt;
    public Button btn_submit;
    Context mContext ;
    private PostAPIclient mPostAPIclient;
    private String mGetFirstName;
    private String mGetLastName;
    private String mGetEmail;
    private String mGetGithub;
    boolean validateValue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        mContext = getApplicationContext() ;
        firstNameEt = (EditText) findViewById(R.id.firstName);
        lastNameEt = (EditText) findViewById(R.id.lastName);
        emailEt = (EditText) findViewById(R.id.email);
        gitHubEt = (EditText) findViewById(R.id.github);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        //  mPostAPIclient = ApiUtils.getAPIService() ;

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                if(validateValue){
                    showCustomDailog();
                  //  sendPost(mGetFirstName, mGetLastName, mGetEmail, mGetGithub);
                }

            }
        });
    }

    private boolean validate() {
        mGetFirstName = firstNameEt.getText().toString().trim();
        mGetLastName = lastNameEt.getText().toString().trim();
        mGetEmail = emailEt.getText().toString().trim();
        mGetGithub = gitHubEt.getText().toString().trim();
        validateValue =true ;

        if (mGetFirstName.isEmpty()) {
            firstNameEt.setError("First name is required");
            firstNameEt.requestFocus();
            validateValue = false ;
        }else{
            firstNameEt.setError(null);
        }

        if (mGetLastName.isEmpty()) {
            lastNameEt.setError("Last name is required");
            lastNameEt.requestFocus();
            validateValue = false ;
        }else{
            lastNameEt.setError(null);
        }

        if (mGetEmail.isEmpty()) {
            emailEt.setError("Email is required");
            emailEt.requestFocus();
            validateValue = false ;
        }else{
            emailEt.setError(null);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mGetEmail).matches()) {
            emailEt.setError("Invalid Email Address");
            emailEt.requestFocus();
            validateValue = false ;
        }else{
            emailEt.setError(null);
        }

        if (mGetGithub.isEmpty()) {
            gitHubEt.setError("Email is required");
            gitHubEt.requestFocus();
            validateValue = false;
        }
      //  showCustomDailog();
        return validateValue;
    }

  /*  private void ShowDailog() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getResources().getString(R.string.dialog_title)) ;
        builder.setMessage(getResources().getString(R.string.dialog_message)) ;
        builder.setIcon(R.drawable.skilliq) ;
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/

    public  void showCustomDailog(){
        final Dialog dialog = new Dialog(ProjectSubmission.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.custom_dialog);
        Button dialogButton = dialog.findViewById(R.id.btn_dialog) ;
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sendPost(mGetFirstName, mGetLastName, mGetEmail, mGetGithub);
              //  Post post = new Post(mGetFirstName, mGetLastName,mGetEmail,mGetGithub) ;
             //   sendNetworkRequest(post);
            }
        });
        dialog.show();
    }

    public  void showSuccessDialog(){
        final Dialog dialog = new Dialog(ProjectSubmission.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.custom_success_dialog);

        dialog.show();
    }
    public  void showFailureDailog(){
        final Dialog dialog = new Dialog(ProjectSubmission.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.custom_failure_dialog);

        dialog.show();
    }
    private void sendPost(String getFirstName, String getLastName, String getEmail, String getGithub) {
        // using retrofit
        PostAPIinterface client = PostAPIclient.postApIinterface();
       Call<Post> call = client.savePost(getFirstName, getLastName,getEmail,getGithub);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                String s ="" ;
                Post post  =  response.body();
                String name = post.firstName ;
                String lastName = post.getLastName() ;
                String email = post.getEmail() ;
                String github =  post.getGithub() ;

                s += "name: " + name + " last name: "+ lastName + " email: " +
                email + " github: " + github ;

                if (response.isSuccessful()) {
                    try{
                        showMessage("Request Successful" + s );
                        showSuccessDialog();
                        Log.d("TAG",response.code()+ "");

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                } else {
                    //showFailureDailog();
                    switch (response.code()){
                        case 200:
                            Log.d("TAG",response.code()+ "");
                            Log.e("error 200 ", " An error occured with error 200 code" + s);
                            showMessage("An error occured with internet with code 200");
                            showFailureDailog();
                            break;

                        case 400:
                            Log.e("error 400 ", " An error occured with error 400 code" + s);
                            showMessage("An error occured with code 400");
                            showFailureDailog();
                            break;
                        case 500:
                            Log.d("TAG",response.code()+ "");
                            Log.e("error 50 ", " An error occured with error 500 code" + s );
                            showMessage("An error occured with internet with code 500 ");
                            showFailureDailog();
                            break;

                            default:
                                Log.d("TAG",response.code()+ "");
                                Log.e("Unknown error", " An error occured with the internet"+ s);
                                showMessage("Unknown error An error occured with internet");
                                showFailureDailog();
                                break;
                    }
                }
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("OnFailure Message", "Unable to submit post to API." + t );
                showMessage("OnFailure Unable to submit post to API" + t);
                showFailureDailog();
                call.cancel();
            }
        });
    }

    public void showMessage(String response) {
        Toast toast = Toast.makeText(ProjectSubmission.this,
               response ,
                Toast.LENGTH_LONG);
        toast.show();
    }


    private void sendNetworkRequest( Post post){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build() ;

                // get client and call object for the request
            PostAPIinterface client = retrofit.create(PostAPIinterface.class);
          Call<Post>  call= client.createPost(post) ;
        //   Call<Post>  call= client.savePost(post.getFirstName(),post.getLastName(),post.getEmail(), post.getGithub()) ;
           call.enqueue(new Callback<Post>() {
               @Override
               public void onResponse(Call<Post> call, Response<Post> response) {
                   String s ="" ;
                   Post post  =  response.body();
                   String name = post.firstName ;
                   String lastName = post.getLastName() ;
                   String email = post.getEmail() ;
                   String github =  post.getGithub() ;

                   s += "name: " + name + " last name: "+ lastName + " email: " +
                           email + " github: " + github ;

                   Log.e("error ", " An error occured" + response.body().getId());
                   showMessage("Network Successful"+ s + " id:" + response.body().getId());
               }

               @Override
               public void onFailure(Call<Post> call, Throwable t) {
                   Log.e("Unknown error", " An error occured with the internet"+ t);
                    showMessage("something went wrong ");
                    call.cancel();
               }
           });

    }
}
