package pucinskas.donatas.lettergame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_start);
    }

    // Pirmo lygio pradžia.
    public void startGame(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("level1strings",getResources().getStringArray(R.array.level1strings));
        intent.putExtra("level1imagesURL",R.array.level1images);
        startActivity(intent);
    }
}
