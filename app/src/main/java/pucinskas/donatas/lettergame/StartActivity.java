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
    public void startLevel1(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("levelStrings",getResources().getStringArray(R.array.level1strings));
        intent.putExtra("levelImagesURL",R.array.level1images);
        intent.putExtra("levelTask",getResources().getString(R.string.level1task));
        startActivity(intent);
    }

    // Antro lygio pradžia.
    public void startLevel2(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("levelStrings",getResources().getStringArray(R.array.level2strings));
        intent.putExtra("levelImagesURL",R.array.level2images);
        intent.putExtra("levelTask",getResources().getString(R.string.level2task));
        startActivity(intent);
    }

    // Antro lygio pradžia.
    public void startLevel3(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("levelStrings",getResources().getStringArray(R.array.level3strings));
        intent.putExtra("levelImagesURL",R.array.level3images);
        intent.putExtra("levelTask",getResources().getString(R.string.level3task));
        startActivity(intent);
    }

    // Antro lygio pradžia.
    public void startLevel4(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("levelStrings",getResources().getStringArray(R.array.level4strings));
        intent.putExtra("levelImagesURL",R.array.level4images);
        intent.putExtra("levelTask",getResources().getString(R.string.level4task));
        startActivity(intent);
    }
}
