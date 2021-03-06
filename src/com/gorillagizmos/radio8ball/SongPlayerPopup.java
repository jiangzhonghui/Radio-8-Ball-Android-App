package com.gorillagizmos.radio8ball;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SongPlayerPopup extends Activity {
	MediaPlayer mp;
	final String SONGSERVPATH = "http://www.radio8ball.com/sites/all/themes/box_grey/upload/song/";

	private Button playPauseButton, shareButton, anotherQuestionButton;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player_popup);
        
        TextView songTitleTextView = (TextView) findViewById(R.id.song_title);
        TextView songArtistTextView = (TextView) findViewById(R.id.song_artist);
        TextView songAlbumTextView = (TextView) findViewById(R.id.song_album);
        playPauseButton = (Button) findViewById(R.id.play_pause_button);
        shareButton = (Button) findViewById(R.id.share_button);
        anotherQuestionButton = (Button) findViewById(R.id.ask_another_button);
        
        songTitleTextView.setText(QuestionPopup.soapClient.getSongTitle());
        songArtistTextView.setText("Artist: "+QuestionPopup.soapClient.getSongArtist());
        songAlbumTextView.setText("Album: "+QuestionPopup.soapClient.getSongAlbum());
        
        playPauseButton.setOnClickListener(playPauseListener);
        shareButton.setOnClickListener(shareButtonListener);
        anotherQuestionButton.setOnClickListener(anotherQuestionButtonListener);

        
        mp = new MediaPlayer();
        try {
        	mp.setDataSource(SONGSERVPATH + QuestionPopup.soapClient.getSongFile());
        	mp.prepare();
        	mp.start();
        } catch(Exception e) {
        	
        }
	}
	
	private OnClickListener playPauseListener = new OnClickListener() {
		public void onClick(View v) {
			// Toggle between playing or pausing the song
			if(playPauseButton.getText() == "Pause") {
				if(mp.isPlaying()) {
					mp.pause();
				}
				playPauseButton.setText("Play");
			} else {
				mp.start();
				playPauseButton.setText("Pause");
			}
		}
	};
	
	private OnClickListener shareButtonListener = new OnClickListener() {
	    public void onClick(View v) {
	    	Intent shareIntent = new Intent(SongPlayerPopup.this, ShareActivity.class);
			SongPlayerPopup.this.startActivity(shareIntent);
	    }
	};
	
	private OnClickListener anotherQuestionButtonListener = new OnClickListener() {
	    public void onClick(View v) {
	    	finish();
	    	Intent anotherQuestionIntent = new Intent(SongPlayerPopup.this, QuestionPopup.class);
			SongPlayerPopup.this.startActivity(anotherQuestionIntent);
	    }
	};
}
