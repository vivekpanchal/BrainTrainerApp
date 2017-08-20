package com.example.vicky.braintrainerapp;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbtn;
    TextView pointTextview;
    TextView resultTextview;
    TextView sumTextView;
    TextView timerTextview;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    ArrayList<Integer>answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;



    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;

        timerTextview.setText("30s");
        pointTextview.setText("0/0");
        resultTextview.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextview.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                timerTextview.setText("0s");
                resultTextview.setText("Your Score"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }


/////////////////////////////////////////////////////////////////////////////////////////////
    public void generateQuestion(){

        Random random=new Random();//to generate random numbers btw 0-20
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=random.nextInt(4);//to make the correct ans location to be random place
        answers.clear();

        int inCorrectAnswer;
        for (int i=0;i<4;i++){

            if (i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                inCorrectAnswer=random.nextInt(41);
                while (inCorrectAnswer==a+b){
                    inCorrectAnswer=random.nextInt(41);
                }
                answers.add(inCorrectAnswer);
            }

        }
///////////////////////////////////////////////////////////////////////////////////////////
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


////////////////////////////////////////////////////////////////////////////////////
    public void chooseAnswer(View view){
        /*Log.i( "chooseAnswer: ", (String) view.getTag());//to get the tag we specied in the button*/

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Log.i("correctans", "Correct ans");
            score++;
            resultTextview.setText("Correct !");
            generateQuestion();

        }else{
            Log.i("wrong", "wrong ans ");
            resultTextview.setText("Wrong !");
        }


        numberOfQuestions++;
        pointTextview.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
    }
/////////////////////////////////////////////////
    public void start(View view){

        startbtn.setVisibility(view.INVISIBLE);
        gameLayout.setVisibility(view.VISIBLE);

    }
////////////////////////////////////////////////////




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbtn=(Button)findViewById(R.id.startButton);
        sumTextView=(TextView)findViewById(R.id.sumTextview);
        resultTextview=(TextView)findViewById(R.id.resultTextview);
        pointTextview=(TextView)findViewById(R.id.pointerTextview);
        timerTextview=(TextView)findViewById(R.id.timerTextView);
        gameLayout =(ConstraintLayout)findViewById(R.id.gameLayout);
        button0=(Button)findViewById(R.id.btn0);
        button1=(Button)findViewById(R.id.btn1);
        button2=(Button)findViewById(R.id.btn2);
        button3=(Button)findViewById(R.id.btn3);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.playAgainButton));




    }
}
