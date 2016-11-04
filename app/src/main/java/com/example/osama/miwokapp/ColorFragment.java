package com.example.osama.miwokapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ColorFragment extends Fragment {

    MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener McompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Releasemediaplayer();
        }
    };
    AudioManager Maudiomanager;
   private AudioManager.OnAudioFocusChangeListener mAudiofocus = new AudioManager.OnAudioFocusChangeListener() {
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


    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.activity_number,container,false);
        final ArrayList<word> words=new  ArrayList<word>();
        Maudiomanager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        words.add(new word("wetetti","red",R.drawable.color_red,R.raw.color_red));
        words.add(new word("chocokki","green",R.drawable.color_green,R.raw.color_green));
        words.add(new word("takakki","brown",R.drawable.color_brown,R.raw.color_brown));
        words.add(new word("topoopi","grey",R.drawable.color_gray,R.raw.color_gray));
        words.add(new word("kululli","black",R.drawable.color_black,R.raw.color_black));
        words.add(new word("kelelli","white",R.drawable.color_white,R.raw.color_white));
        words.add(new word("topiisa","dustyellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new word("chiwiita","mustardyellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


//        while (index <words.size())
//        {
//
//            index++;
//        }

        WordAdapter adapter = new WordAdapter(getActivity(),words,R.color.category_colors);

        ListView listView = (ListView)rootview.findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Releasemediaplayer();
                word myword = words.get(position);
                int result= Maudiomanager.requestAudioFocus(mAudiofocus, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED ){
                    mediaPlayer = MediaPlayer.create(getActivity(), myword.getResId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(McompletionListener);
                }}
        });
        return rootview; }

    // craete root view
    // release method

    @Override
    public void onStop() {
        super.onStop();
        Releasemediaplayer();
    }

    public void Releasemediaplayer (){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer=null;
            Maudiomanager.abandonAudioFocus(mAudiofocus);
        }
    }

    }



