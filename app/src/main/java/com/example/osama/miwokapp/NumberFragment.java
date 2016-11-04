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

import java.util.ArrayList;



public class NumberFragment extends Fragment {



    private MediaPlayer mediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager Maudiomanager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                Releasemediaplayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            Releasemediaplayer();
        }
    };

    public NumberFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.activity_number, container, false);
        final ArrayList<word> words=new  ArrayList<word>();
        Maudiomanager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);


        words.add(new word("luti","one",R.drawable.number_one,R.raw.number_one));
        words.add(new word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new word("four","oyyisu",R.drawable.number_four,R.raw.number_four));
        words.add(new word("five","massoku",R.drawable.number_five,R.raw.number_five));
        words.add(new word("six","temmoka",R.drawable.number_six,R.raw.number_six));
        words.add(new word("seven","kenekaka",R.drawable.number_seven,R.raw.number_seven));
        words.add(new word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new word("nine","woe",R.drawable.number_nine,R.raw.number_nine));
        words.add(new word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));


//        while (index <words.size())
//        {
//
//            index++;
//        }
        WordAdapter adapter = new WordAdapter(getActivity(),words,R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Releasemediaplayer();
                word myword = words.get(position);
                int result= Maudiomanager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED ){
                    mediaPlayer= MediaPlayer.create(getActivity(), myword.getResId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);}

            }

        });

        listView.setAdapter(adapter);
        return rootView;
    }
    public void onStop() {
        super.onStop();
        Releasemediaplayer();
    }


    public void Releasemediaplayer (){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer=null;
            Maudiomanager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }}
}
