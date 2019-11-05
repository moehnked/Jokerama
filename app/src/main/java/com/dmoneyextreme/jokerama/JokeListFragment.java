package com.dmoneyextreme.jokerama;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JokeListFragment extends Fragment {
    private RecyclerView mJokeRecyclerView;
    private JokeAdapter mAdapter;
    private ImageButton mResetButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_joke_list, container, false);

        mJokeRecyclerView = view.findViewById(R.id.joke_recycler_view);
        mJokeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mResetButton = getActivity().findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokeLab.get(getActivity()).reset();
                updateUI();
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        ((SingleFragmentActivity)getActivity()).updateToolbar(this);
        JokeLab jokeLab = JokeLab.get(getActivity());
        List<Joke> jokes = jokeLab.getJokes();

        if(mAdapter == null){
            mAdapter = new JokeAdapter(jokes);
            mJokeRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }



    }

    private class JokeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Joke mJoke;

        public JokeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_joke, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.joke_title);
        }

        public void bind(Joke joke){
            mJoke = joke;
            mTitleTextView.setText(mJoke.getmTitle() + " " + mJoke.getmProgress());
            if(mJoke.completed()){
                mTitleTextView.setBackgroundColor(Color.parseColor("#BBBBBB"));
            }
        }

        @Override
        public void onClick(View view){
            Intent intent = JokeActivity.newIntent(getActivity(), mJoke.getmId());
            startActivity(intent);
        }
    }

    private class JokeAdapter extends RecyclerView.Adapter<JokeHolder> {
        private List<Joke> mJokes;

        public JokeAdapter(List<Joke> jokes){
            mJokes = jokes;
        }


        @NonNull
        @Override
        public JokeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new JokeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull JokeHolder holder, int position) {
            Joke joke = mJokes.get(position);
            holder.bind(joke);
        }

        @Override
        public int getItemCount() {
            return mJokes.size();
        }
    }
}
