package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBoutonVrai;
    private Button mBoutonFaux;
    private Button mBoutonSuivant;
    private TextView mQuestionTextView;
    private Button mBoutonAide;
    private boolean mEstTricheur;
    private static final String A_TRICHE = "a triche";




    public static final String TAG = "QuizActivity";
    private static final String KEY_INDEX="index";




    private VraiFaux[] mTabQuestions = new VraiFaux[]{
            new VraiFaux(R.string.question_oceans,true),
            new VraiFaux(R.string.question_africa,false),
            new VraiFaux(R.string.question_americas,true),
            new VraiFaux(R.string.question_asia,true),
            new VraiFaux(R.string.question_mideast,false),
    };
    private int mIndexActuel=0;
    private boolean[] mQuestionTrichee = new boolean[mTabQuestions.length];

    private void majQuestion(){
        int question = mTabQuestions[mIndexActuel].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void verifieReponse(boolean userVrai) {

        boolean reponseVraie = mTabQuestions[mIndexActuel].isQuestionVraie();

        int messReponseId=0;

        if(mQuestionTrichee[mIndexActuel])
        {
            if(mEstTricheur)
            {
                if(userVrai == reponseVraie)
                {
                    messReponseId = R.string.toast_triche;
                }
                else {
                    messReponseId = R.string.toast_faux;
                }
            }
            else
            {
                if (userVrai == reponseVraie)
                {
                    messReponseId = R.string.toast_correct;
                }
                else {
                    messReponseId = R.string.toast_faux;
                }
            }
        }
        else
        {
            messReponseId = R.string.toast_faux;
        }


        Toast.makeText(MainActivity.this, messReponseId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart appelee");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause appelee");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume appelee");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop appelee");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy appelee");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate appelee");
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView)findViewById(R.id.questionTextView);
        int question = mTabQuestions[mIndexActuel].getQuestion();
        mQuestionTextView.setText(question);

        mBoutonFaux = (Button)findViewById(R.id.bouton_faux);
        mBoutonVrai = (Button)findViewById(R.id.bouton_vrai);
        mBoutonSuivant = (Button)findViewById(R.id.bouton_suivant);
        mBoutonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndexActuel = (mIndexActuel + 1)% mTabQuestions.length;
                int question= mTabQuestions[mIndexActuel].getQuestion();
                mQuestionTextView.setText(question);
                mEstTricheur = false;
            }
        });

        mBoutonVrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifieReponse(true);

            }
        });

        mBoutonFaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifieReponse(false);
            }
        });

        mBoutonAide = (Button)findViewById(R.id.boutonAfficheAide);
        mBoutonAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(MainActivity.this, AideActivity.class);
                boolean reponseVraie= mTabQuestions[mIndexActuel].isQuestionVraie();
                intention.putExtra(AideActivity.EXTRA_REPONSE_VRAIE, reponseVraie);
                startActivityForResult(intention,2016);

            }
        });

        majQuestion();

        if (savedInstanceState != null)
        {
            mIndexActuel=savedInstanceState.getInt(KEY_INDEX,0);
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (data!=null)
        {
            mEstTricheur= data.getBooleanExtra(AideActivity.EXTRA_REPONSE_AFFICHEE,false);
        }
        else return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");
        outState.putInt(KEY_INDEX,mIndexActuel);
        outState.putBoolean(A_TRICHE,mQuestionTrichee[mIndexActuel]);
    }

}

