package com.example.osama.miwokapp;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends Fragment {
    MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener McompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Releasemediaplayer();
        }
    };
    AudioManager Maudiomanager;
    AudioManager.OnAudioFocusChangeListener mAudiofocus=  new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();

            }else if (focusChange== AudioManager.AUDIOFOCUS_LOSS){
                Releasemediaplayer();

            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);

            }
        }
    };


    public PhraseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.activity_number,container,false);
        final ArrayList<word> words=new  ArrayList<word>();
        Maudiomanager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        words.add(new word("where are you go?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?","oyyisu",R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’m feeling good","michәksәs?",R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?","kuchi achit",R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming.","әәnәs'aa?",R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming","hәә’ әәnәm",R.raw.phrase_im_coming));
        words.add(new word("Let’s go.","әәnәm",R.raw.phrase_lets_go));
        words.add(new word("Come here.","yoowutis",R.raw.phrase_come_here));


//        while (index <words.size())
//        {
//
//            index++;
//        }
        WordAdapter adapter = new WordAdapter(getActivity(),words,R.color.category_phrases);

        ListView listView = (ListView) rootview.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Releasemediaplayer();
                word myword = words.get(position);
                int result= Maudiomanager.requestAudioFocus(mAudiofocus, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED ){
                    mediaPlayer= MediaPlayer.create(getActivity(), myword.getResId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(McompletionListener);
                }}
        });

        listView.setAdapter(adapter);
        return rootview;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    public void Releasemediaplayer (){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer=null;
            Maudiomanager.abandonAudioFocus(mAudiofocus);
        }
    }
}
