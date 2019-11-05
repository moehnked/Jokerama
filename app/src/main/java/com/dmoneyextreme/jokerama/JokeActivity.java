package com.dmoneyextreme.jokerama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class JokeActivity extends SingleFragmentActivity {
    private static final String EXTRA_JOKE_ID = "com.dmoneyextreme.jokerama.joke_id";

    public static Intent newIntent(Context packageContext, UUID jokeId){
        Intent intent = new Intent(packageContext, JokeActivity.class);
        intent.putExtra(EXTRA_JOKE_ID, jokeId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        //return new JokeFragment();
        UUID jokeId = (UUID) getIntent().getSerializableExtra(EXTRA_JOKE_ID);
        return JokeFragment.newInstance(jokeId);
    }
}
