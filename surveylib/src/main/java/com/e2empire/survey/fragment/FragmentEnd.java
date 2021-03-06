package com.e2empire.survey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.e2empire.survey.Answers;
import com.e2empire.survey.R;
import com.e2empire.survey.SurveyActivity;
import com.e2empire.survey.models.SurveyProperties;

public class FragmentEnd extends Fragment {

    private FragmentActivity mContext;
    private TextView textView_end;
    private Button button_finish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_end, container, false);


        button_finish = (Button) rootView.findViewById(R.id.button_finish);
        textView_end = (TextView) rootView.findViewById(R.id.textView_end);


        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((SurveyActivity) mContext).go_to_next();

            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survery_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");

        assert survery_properties != null;
        textView_end.setText(Html.fromHtml(survery_properties.getEndMessage()));

        if(survery_properties.getSkipEnd()) {
            // TODO Hack to skip the end Fragment
            button_finish.performClick();
        }

    }
}