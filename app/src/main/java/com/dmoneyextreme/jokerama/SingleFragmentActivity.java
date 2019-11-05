package com.dmoneyextreme.jokerama;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();
    private ImageButton mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        updateToolbar(fragment);

        /*
        mResetButton = findViewById(R.id.reset_button);
        final Fragment finalFragment = fragment;
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokeLab.get(finalFragment.getContext()).reset();
                updateToolbar(finalFragment);
            }
        });

         */
    }

    public void updateToolbar(Fragment fragment){
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        TextView scoreText = findViewById(R.id.toolbar_score);
        scoreText.setText(JokeLab.get(fragment.getContext()).getJokes().size() + " jokes, " + JokeLab.get(fragment.getContext()).numberCompleted() + " completed");
        setSupportActionBar(toolbar);
    }
}
