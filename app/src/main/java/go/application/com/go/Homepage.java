package go.application.com.go;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import go.application.com.go.Utilities.SessionManager;

public class Homepage extends NavigationScreen {
    ImageButton visualizerbutton, recognizerbutton ;
    Button button;

    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content_frame = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_homepage, content_frame);

        session = new SessionManager(getApplicationContext());

        visualizerbutton = (ImageButton) findViewById(R.id.imagebutton);
        recognizerbutton = (ImageButton) findViewById(R.id.imageButton3);
        session.checkLogin();
    }

    public void send(View v)
    {
        Intent intent = new Intent(Homepage.this, Recognizerfirstpage.class);
        startActivity(intent);
    }


    public void gosomewhere(View v)
    {
        Intent intent = new Intent(Homepage.this, VisualizerPage.class);
        startActivity(intent);
    }
}
