package com.example.osama.miwokapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by osama on 8/27/2016.
 */
public class WordAdapter extends ArrayAdapter<word> {
    private  final int COLOR_BACKGROUND= R.color.category_colors;
    private  final int FAMILY_BACKGROUND= R.color.category_family;
    private  final int PHRASE_BACKGROUND= R.color.category_phrases;
    private  final int NUMBER_BACKGROUND= R.color.category_numbers;
    public int Color;


    public WordAdapter (Context context, ArrayList<word> words , int color){
        super(context,0,words);
        Color = color;

    }



    @Override

    public View getView(final int position, View convertView, ViewGroup parent) {
        View listitemView= convertView;

        if (listitemView == null){
            listitemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        final word Myword = getItem(position);
        TextView Miwokword = (TextView)listitemView.findViewById(R.id.miwakword);
        TextView Englishword = (TextView)listitemView.findViewById(R.id.englishword);
        ImageView imagemiwak= (ImageView)listitemView.findViewById(R.id.image_row);
        if (Myword.hasimage()){
            imagemiwak.setVisibility(View.VISIBLE);
        imagemiwak.setImageResource(Myword.getImageId());}
        else if(!Myword.hasimage()) {
         imagemiwak.setVisibility(View.GONE);
        }
        Miwokword.setText(Myword.getMiwakWord());
        Englishword.setText(Myword.getEnglishWord());
        View textcontainer = listitemView.findViewById(R.id.text_container);
//        if (Color == COLOR_BACKGROUND){
//            textcontainer.setBackgroundResource(COLOR_BACKGROUND);
//        } else if (Color == FAMILY_BACKGROUND){
//            textcontainer.setBackgroundResource(FAMILY_BACKGROUND);
//        }  else if (Color == NUMBER_BACKGROUND){
//            textcontainer.setBackgroundResource(NUMBER_BACKGROUND);
//        }else  {
//            textcontainer.setBackgroundResource(PHRASE_BACKGROUND);
////        }
//        listitemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.v("CorrectWord","here:"+ Myword.toString());
//                MediaPlayer mediaPlayer = MediaPlayer.create(v.getContext(), Myword.getResId());
//                mediaPlayer.start();
//            }
//        });

        int color = ContextCompat.getColor(getContext(),Color);
        textcontainer.setBackgroundColor(color);

        return listitemView;
    }
}
