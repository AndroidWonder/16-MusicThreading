/*
 * This example is set up to run an audio stream from the BBC.
 * It needs an Internet permission in the Manifest.
 * Notice the Manifest application attribute: android:usesCleartextTraffic="true"
 *
 * Alternatively, this example can stream from an mp3 file.
 * Make sure you have a music file in Android memory in the
 * directory data/data/  
 * Use the emulator File Explorer to load the file.
 */

package com.course.example.musicthread;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.Button;

public class  MusicThreadActivity extends Activity {
	
	private MediaPlayer mp;
	private Thread musicThread;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);

		Button button01 = (Button) findViewById(R.id.Button01);
		Button button02 = (Button) findViewById(R.id.Button02);
             
        button01.setOnClickListener(
                v -> {
                    musicThread = new Thread(background);
                    musicThread.start();
                });
        
        button02.setOnClickListener(
                v -> mp.stop());
    }

    Runnable background = new Runnable() {

		public void run(){
			mp = new MediaPlayer();
			mp.setAudioAttributes(
					new AudioAttributes
							.Builder()
							.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
							.build());

				try {
				//select Harvard stream
				//mp.setDataSource("http://stream.whrb.org:8000/whrb-mp3");

				//select BBC stream
				mp.setDataSource("http://vprbbc.streamguys.net:80/vprbbc24.mp3");

				mp.prepare();   // might take long! (for buffering, etc)
				mp.start();

				} catch (IOException e) {}

		}
	};


}