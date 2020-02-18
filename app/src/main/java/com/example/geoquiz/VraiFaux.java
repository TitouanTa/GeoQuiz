package com.example.geoquiz;

public class VraiFaux
{
    //variables membres de la classe
    private int mQuestion;
    private boolean mQuestionVraie;

    //getters
    public int getQuestion() {
        return mQuestion;
    }

    public boolean isQuestionVraie() {
        return mQuestionVraie;
    }

    //setters
    public void setQuestion(int question) {
        mQuestion = question;
    }

    public void setQuestionVraie(boolean questionVraie) {
        mQuestionVraie = questionVraie;
    }

    //constructeur
    public VraiFaux(int question, boolean questionVraie)
    {
        mQuestion = question;
        mQuestionVraie = questionVraie;
    }




}
