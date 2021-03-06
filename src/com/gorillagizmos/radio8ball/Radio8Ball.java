package com.gorillagizmos.radio8ball;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Radio8Ball extends Radio8BallActivity implements OnClickListener {
	AnimationDrawable eightBallAnimation;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button askButton = (Button) findViewById(R.id.ask_button);
        askButton.setOnClickListener(this);
        
        ImageView eightBallImage = (ImageView) findViewById(R.id.eight_ball_image);
        eightBallImage.setBackgroundResource(R.anim.eight_ball);
        eightBallAnimation = (AnimationDrawable) eightBallImage.getBackground();
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	eightBallAnimation.start();
    }

	@Override
	public void onClick(View v) {
		Intent questionIntent = new Intent(Radio8Ball.this, QuestionPopup.class);
    	Radio8Ball.this.startActivity(questionIntent);
	}
}