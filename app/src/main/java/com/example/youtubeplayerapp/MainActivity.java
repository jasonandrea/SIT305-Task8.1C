package com.example.youtubeplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.playButton);
        EditText link = findViewById(R.id.linkEditText);

        // OnClickListener for Play button on this activity
        playButton.setOnClickListener(new View.OnClickListener() {

            // This onClick method will run when the button is clicked. It will start PlayerActivity
            // with YouTube API key passed to that activity via intent putExtra
            @Override
            public void onClick(View v) {
                // Get the video URL from EditText box
                // Example URL:
                // https://www.youtube.com/watch?v=kAJAViPWR_8
                // https://youtu.be/kAJAViPWR_8
                String videoUrl = link.getText().toString();

                // Check whether EditText box is empty
                if (videoUrl.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter video URL", Toast.LENGTH_SHORT).show();
                else {
                    // First, split the URL into substrings with "/" as delimiter
                    String[] videoIdLong = videoUrl.split("/");

                    // Next, split the last segment of videoIdLong into substrings with "=" as delimiter
                    // This is necessary to get the video id from a complete URL such as
                    // https://www.youtube.com/watch?v=kAJAViPWR_8
                    // Nothing will happen if the input URL is a short URL (example: https://youtu.be/kAJAViPWR_8)
                    String[] videoIdShort = videoIdLong[videoIdLong.length - 1].split("=");

                    // Finally, put video id and YT API to intent and start next activity
                    Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                    intent.putExtra(Keys.YT_API_KEY, YTConfig.getApi());                        // Extra for API key
                    intent.putExtra(Keys.YT_URL_KEY, videoIdShort[videoIdShort.length - 1]);    // Extra for video id
                    startActivity(intent);                                                      // Start PlayerActivity
                }
            }
        });
    }
}