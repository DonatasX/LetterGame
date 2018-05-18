package pucinskas.donatas.lettergame;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.Image;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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
    TextView textView, textViewTask;
    ProgressBar progressBar;
    Button button1, button2;

    // Variables
    String[] levelStrings;
    TypedArray levelImages;
    static boolean isCorrect;
    static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // methods:
    // Nustatyti pradinius duomenis.
    private void init(){
        // vars
        levelStrings = getIntent().getStringArrayExtra("levelStrings");
        levelImages = getResources().obtainTypedArray(getIntent().getExtras().getInt("levelImagesURL"));
        isCorrect = false;
        index = 0;
        // views
        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textViewTask = findViewById(R.id.textViewTask);
        progressBar = findViewById(R.id.progressBar);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        // view attrs
        textViewTask.setText(getIntent().getStringExtra("levelTask"));
        imageView.setImageResource(levelImages.getResourceId(index,-1));
        button2.setVisibility(View.INVISIBLE);
    }

    // Padidinti progreso juostos būseną.
    private void addProgress(){
        progressBar.incrementProgressBy(100 / levelStrings.length);
    }

    // On click events:
    // Tikrinti ar įvestas žodis yra teisingas.
    public void tikrinti(View view){
        String str;
        try{
           str = editText.getEditableText().toString();
        }catch (NullPointerException e){
            str = "";
        }
        // Jei teisingas.
        if (str.equals(levelStrings[index])){
            isCorrect = true;
            textView.setText(R.string.teisingai);
            textView.setTextColor(getResources().getColor(R.color.green));
            button1.setClickable(false);
            button2.setVisibility(View.VISIBLE);
            addProgress();
            // Jei visi klausimai atsakyti.
            if (progressBar.getProgress() == 100){
                button2.setText("Baigti");
            }
        }
        // Jei neteisingas.
        else {
            isCorrect = false;
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
        else if (isCorrect){
            index++;
            isCorrect = false;
            imageView.setImageResource(levelImages.getResourceId(index,-1));
            textView.setText("");
            editText.setText("");
            button1.setClickable(true);
            button2.setVisibility(View.INVISIBLE);
        }
    }
}
