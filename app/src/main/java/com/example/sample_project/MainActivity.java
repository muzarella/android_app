package com.example.sample_project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.sample_project.ui.main.GoogleBoardPagerAdapter;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity debug" ;
    private GoogleBoardPagerAdapter mGoogleBoardPagerAdapter;
    private ViewPager mViewPager;
    public  static TabLayout mTabLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabs);

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);


        createTabFragment();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MainActivity.this, ProjectSubmission.class) ;
                startActivity(intent);
            }
        });


    }

    private void createTabFragment(){

        mGoogleBoardPagerAdapter = new GoogleBoardPagerAdapter(getSupportFragmentManager());
        mGoogleBoardPagerAdapter.AddFragment(new learn_hourFragment(), getResources().getString(R.string.tab_learning_leader));
        mGoogleBoardPagerAdapter.AddFragment(new skills_iq_fragment(), getResources().getString(R.string.tab_skill_iq_leader));
        mViewPager.setAdapter(mGoogleBoardPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        // if u want image to show on the icon head instead of the title text
      /*  mTabLayout.getTabAt(0).setIcon() ;
        mTabLayout.getTabAt(1).setIcon() ;
        mTabLayout.getTabAt(2).setIcon() ;*/
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
       // finish();
    }
}