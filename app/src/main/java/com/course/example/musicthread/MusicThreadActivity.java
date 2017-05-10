/*
 * This example is set up to run an audio stream from the BBC.
 * It needs an Internet permission in the Manifest.
 * Alternatively, this example can stream from an mp3 file.
 * Make sure you have a music file in Android memory in the
 * directory data/data/  
 * Use the emulator File Explorer to load the file.
 */

package com.course.example.musicthread;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;


public class  MusicThreadActivity extends Activity {
	
	private MediaPlayer mp;
    private Button button01, button02; 
    private Thread musicThread;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);
        
        button01 = (Button)findViewById(R.id.Button01);
        button02 = (Button)findViewById(R.id.Button02);
             
        button01.setOnClickListener(
        		new OnClickListener(){
        				public void onClick(View v){
        					musicThread = new Thread(background);
        					musicThread.start();
        				}});
        
        button02.setOnClickListener(
        		new OnClickListener(){
        				public void onClick(View v){
        					mp.stop();
        				}});
    }
    
    Runnable background = new Runnable() {
	   
		public void run(){
			mp = new MediaPlayer();
				try {
				//select Harvard stream
				mp.setDataSource("http://hrbinc-hi.streamguys.net");
				//select BBC stream
				//mp.setDataSource("http://vprbbc.streamguys.net:80/vprbbc24.mp3");
				mp.prepare();
				mp.start();
				} catch (IOException e) {};
						
		}
	}; 
     
    
}