package com.dmoneyextreme.jokerama;

import androidx.fragment.app.Fragment;

public class JokeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new JokeListFragment();
    }
}
