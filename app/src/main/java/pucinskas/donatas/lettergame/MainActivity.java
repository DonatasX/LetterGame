package pucinskas.donatas.lettergame;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.Image;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.KeyListener;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
    int[] levelImageIds;
    static boolean isCorrect;
    static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        registerClickListeners();
    }

    // methods:
    // Nustatyti pradinius duomenis.
    private void init() {
        // views
        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textViewTask = findViewById(R.id.textViewTask);
        progressBar = findViewById(R.id.progressBar);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        // vars
        levelStrings = getIntent().getStringArrayExtra("levelStrings");
        levelImages = getResources().obtainTypedArray(getIntent().getExtras().getInt("levelImagesURL"));
        levelImageIds = convertFromTypedArray(levelImages);
        isCorrect = false;
        index = 0;
        shuffleArrays(levelImageIds,levelStrings);
        // view attrs
        textViewTask.setText(getIntent().getStringExtra("levelTask"));
        imageView.setImageResource(levelImageIds[index]);
        button2.setVisibility(View.INVISIBLE);
    }

    // On click listeners
    public void registerClickListeners() {
        // Tikrinti ar įvestas žodis yra teisingas.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str;
                try {
                    str = editText.getEditableText().toString();
                } catch (NullPointerException e) {
                    str = "";
                }
                // Jei teisingas.
                if (str.equals(levelStrings[index])) {
                    isCorrect = true;
                    textView.setText(R.string.teisingai);
                    textView.setTextColor(getResources().getColor(R.color.green));
                    button1.setClickable(false);
                    button2.setVisibility(View.VISIBLE);
                    addProgress();
                    // Jei visi klausimai atsakyti.
                    if (progressBar.getProgress() == 100) {
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
        });

        // Pereiti prie kito klausimo.
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Jei yra lygio pabaiga.
                if (progressBar.getProgress() == 100) {
                    index = 0;
                    Intent intent = new Intent(v.getContext(), StartActivity.class);
                    startActivity(intent);
                }
                // Jei atsakymas buvo teisingas.
                else if (isCorrect) {
                    index++;
                    isCorrect = false;
                    imageView.setImageResource(levelImageIds[index]);
                    textView.setText("");
                    editText.setText("");
                    button1.setClickable(true);
                    button2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    // From typedArray to int array.
    private int[] convertFromTypedArray(TypedArray typedArray){
        int[] ar = new int[typedArray.length()];
        for (int i = 0; i < ar.length; i++){
            ar[i] = typedArray.getResourceId(i,-1);
        }
        return ar;
    }

    // Sumaišyti masyvus
    private void shuffleArrays(int[] ar, String[] str) {
        Random rnd = new Random();
        for (int i = str.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String s = str[index];
            str[index] = str[i];
            str[i] = s;
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    // Padidinti progreso juostos būseną.
    private void addProgress() {
        progressBar.incrementProgressBy(100 / levelStrings.length);
    }
}
