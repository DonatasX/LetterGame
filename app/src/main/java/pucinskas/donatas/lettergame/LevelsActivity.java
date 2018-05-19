package pucinskas.donatas.lettergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class LevelsActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cardLevel1, cardLevel2, cardLevel3, cardLevel4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        //defining cards
        cardLevel1 = findViewById(R.id.card_level1);
        cardLevel2 = findViewById(R.id.card_level2);
        cardLevel3 = findViewById(R.id.card_level3);
        cardLevel4 = findViewById(R.id.card_level4);
        cardLevel1.setOnClickListener(this);
        cardLevel2.setOnClickListener(this);
        cardLevel3.setOnClickListener(this);
        cardLevel4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PlayActivity.class);
        switch (v.getId()) {
            case R.id.card_level1:
                intent.putExtra("levelStrings", getResources().getStringArray(R.array.level1strings));
                intent.putExtra("levelImagesURL", R.array.level1images);
                intent.putExtra("levelTask", getResources().getString(R.string.level1task));
                startActivity(intent);
                break;
            case R.id.card_level2:
                intent.putExtra("levelStrings", getResources().getStringArray(R.array.level2strings));
                intent.putExtra("levelImagesURL", R.array.level2images);
                intent.putExtra("levelTask", getResources().getString(R.string.level2task));
                startActivity(intent);
                break;
            case R.id.card_level3:
                intent.putExtra("levelStrings", getResources().getStringArray(R.array.level3strings));
                intent.putExtra("levelImagesURL", R.array.level3images);
                intent.putExtra("levelTask", getResources().getString(R.string.level3task));
                startActivity(intent);
                break;
            case R.id.card_level4:
                intent.putExtra("levelStrings", getResources().getStringArray(R.array.level4strings));
                intent.putExtra("levelImagesURL", R.array.level4images);
                intent.putExtra("levelTask", getResources().getString(R.string.level4task));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
