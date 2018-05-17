package pucinskas.donatas.lettergame;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.Image;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Widgets
    ImageView imageView;
    EditText editText;
    TextView textView;
    ProgressBar progressBar;

    // Variables
    String[] level1strings;
    TypedArray level1images;
    static boolean isCorect = false;
    static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        level1strings = getIntent().getStringArrayExtra("level1strings");
        level1images = getResources().obtainTypedArray(getIntent().getExtras().getInt("level1imagesURL"));
    }

    // Tikrinti ar įvestas žodis yra teisingas.
    public void tikrinti(View view){
        String str;
        try{
           str = editText.getEditableText().toString();
        }catch (NullPointerException e){
            str = "";
        }

        // Jei teisingas.
        if (str.equals(level1strings[index])){
            isCorect = true;
            textView.setText(R.string.teisingai);
            textView.setTextColor(getResources().getColor(R.color.green));
            addProgress();

            // Jei visi klausimai atsakyti.
            if (progressBar.getProgress() == 100){
                Button btn = findViewById(R.id.button2);
                btn.setText("Baigti");
            }
        }
        // Jei neteisingas.
        else {
            isCorect = false;
            textView.setText(R.string.neteisingai);
            textView.setTextColor(getResources().getColor(R.color.red));
        }
    }

    // Pereiti prie kito klausimo.
    public void kitas(View view){
        // Jei yra lygio pabaiga.
        if (progressBar.getProgress() == 100){
            index = 0;
            Intent intent = new Intent(this,StartActivity.class);
            startActivity(intent);
        }
        // Jei atsakymas buvo teisingas.
        else if (isCorect){
            index++;
            isCorect = false;
            imageView.setImageResource(level1images.getResourceId(index,-1));
            textView.setText("");
            editText.setText("");
        }
    }

    // Padidinti progreso juostos būseną.
    private void addProgress(){
        progressBar = findViewById(R.id.progressBar);
        progressBar.incrementProgressBy(100 / level1strings.length);
    }
}
