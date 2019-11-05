package com.dmoneyextreme.jokerama;

import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class JokeFragment extends Fragment {
    private static final String ARG_JOKE_ID = "joke_id";
    private ImageButton mResetButton;

    private Joke mJoke;

    public static JokeFragment newInstance(UUID jokeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOKE_ID, jokeId);

        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //UUID jokeId = (UUID) getActivity().getIntent().getSerializableExtra(JokeActivity.EXTRA_JOKE_ID);
        UUID jokeId = (UUID) getArguments().getSerializable(ARG_JOKE_ID);
        mJoke = JokeLab.get(getActivity()).getJoke(jokeId);


    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_joke, container, false);

        LinearLayout layout = v.findViewById(R.id.joke_fragment_layout);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJoke.next();
                updateUI(v, mJoke);
            }
        });

        mResetButton = getActivity().findViewById(R.id.reset_button);
        mResetButton.setVisibility(View.INVISIBLE);

        v.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }

            public boolean onSwipeRight() {
                mJoke.next();
                return true;
            }
            public boolean onSwipeLeft() {
                if(mJoke.getmProgress() > 0){
                    mJoke.setmProgress(mJoke.getmProgress() - 1);
                }
                return true;
            }
        });

        updateUI(v, mJoke);



        return v;
    }

    private void updateUI(View v, Joke j){

        LinearLayout layout = v.findViewById(R.id.joke_fragment_layout);
        if(layout != null){
            if(layout.getChildCount() > 0){
                layout.removeAllViews();
            }
            for(int i = 0; (i <= j.getmProgress()) && (i < j.getmLines().length); i++){
                TextView jokeLine = new TextView(getActivity());
                jokeLine.setText(j.getmLines()[i]);
                if(i%2 != 0){
                    jokeLine.setGravity(Gravity.RIGHT);
                }
                layout.addView(jokeLine);
            }
        }
        ((SingleFragmentActivity)getActivity()).updateToolbar(this);
    }

}
