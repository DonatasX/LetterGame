package pucinskas.donatas.lettergame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cardLevels, cardSettings, cardAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining cards
        cardLevels = findViewById(R.id.card_levels);
        cardSettings = findViewById(R.id.card_settings);
        cardAbout = findViewById(R.id.card_about);
        cardLevels.setOnClickListener(this);
        cardSettings.setOnClickListener(this);
        cardAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.card_levels:
                i = new Intent(this, LevelsActivity.class);
                startActivity(i);
                break;
            case R.id.card_settings:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.card_about:
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
