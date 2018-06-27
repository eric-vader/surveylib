package com.e2empire.survey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.e2empire.survey.adapters.AdapterFragmentQ;
import com.e2empire.survey.fragment.FragmentCheckboxes;
import com.e2empire.survey.fragment.FragmentEnd;
import com.e2empire.survey.fragment.FragmentMultiline;
import com.e2empire.survey.fragment.FragmentNumber;
import com.e2empire.survey.fragment.FragmentRadioboxes;
import com.e2empire.survey.fragment.FragmentStart;
import com.e2empire.survey.fragment.FragmentTextSimple;
import com.e2empire.survey.models.Question;
import com.e2empire.survey.models.SurveyPojo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    private SurveyPojo mSurveyPojo;
    private ViewPager mPager;
    private String style_string = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);



        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mSurveyPojo = new Gson().fromJson(bundle.getString("json_survey"), SurveyPojo.class);
            if (bundle.containsKey("style")) {
                style_string = bundle.getString("style");
            }
        }

        Log.i("json Object = ", String.valueOf(mSurveyPojo.getQuestions()));

        mPager = (ViewPager) findViewById(R.id.pager);
        AdapterFragmentQ mPagerAdapter = new AdapterFragmentQ(getSupportFragmentManager(), mSurveyPojo, style_string);
        mPager.setAdapter(mPagerAdapter);

    }

    public void go_to_next() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void event_survey_completed(Answers instance) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("answers", instance.get_json_object());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
