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
public class FamilyFragment extends Fragment {
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


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.activity_number,container,false);
        final ArrayList<word> words=new  ArrayList<word>();
        Maudiomanager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        words.add(new word("father","apa",R.drawable.family_father,R.raw.family_father));
        words.add(new word("mother","ata",R.drawable.family_mother,R.raw.family_mother));
        words.add(new word("daugter","angsi",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new word("son","tune",R.drawable.family_son,R.raw.family_son));
        words.add(new word("olderbrother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new word("younerbrother","chlitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new word("oldresister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new word("youngersister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new word("grandmother","amaa",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new word("grandfather","apaa",R.drawable.family_grandfather,R.raw.family_grandfather));


//        while (index <words.size())
//        {
//
//            index++;
//        }
        WordAdapter adapter = new WordAdapter(getActivity(),words,R.color.category_family);

        ListView listView = (ListView) rootview.findViewById(R.id.list);

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


        return rootview;
    }

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
