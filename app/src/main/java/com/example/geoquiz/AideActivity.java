package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AideActivity extends MainActivity {
    public static final String EXTRA_REPONSE_VRAIE="com.example.geoquiz.reponse_vraie";
    public static final String EXTRA_REPONSE_AFFICHEE ="com.example.geoquiz.reponse_affichee";
    public static final String TRICHE_APRES_ROTATION ="a triche";
    private boolean mReponseVraie;
    private TextView mReponseTextView;
    private Button mAfficheReponse;
    private boolean mRotationTriche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);

        final boolean mReponseVraie = getIntent().getBooleanExtra(EXTRA_REPONSE_VRAIE,false);

        mReponseTextView = (TextView)findViewById(R.id.reponseTextView);
        mAfficheReponse =(Button)findViewById(R.id.boutonAfficheAide);
        mAfficheReponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mReponseVraie)
                {
                    mReponseTextView.setText(R.string.bouton_vrai);
                }
                else
                    {
                        mReponseTextView.setText("Faux");
                    }
            setReponseAffichee(true);
            }

        });
        if(savedInstanceState != null)
        {
            mRotationTriche = savedInstanceState.getBoolean(TRICHE_APRES_ROTATION);
        }
    }

    private void setReponseAffichee(boolean isReponseAffichee)
    {
        Intent donnees= new Intent();
        donnees.putExtra(EXTRA_REPONSE_AFFICHEE,isReponseAffichee);
        setResult(RESULT_OK, donnees);
        mRotationTriche = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBoolean(TRICHE_APRES_ROTATION,mRotationTriche);
        Log.d(TAG,"valeur de mRotationTriche dans onSaveInstanceState() : "+mRotationTriche);
    }



}
