package com.comp1601.assignment1;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //create member variables to represent the buttons
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private Button mEButton;
    private Button mPrevButton;
    private Button mSubmitButton;
    private Button mNextButton;
    private String viewScore;

    private TextView mQuestionTextView;
    private int score = 0;

    //create arraylist to store questions
    private ArrayList<Question> mQuestions = new ArrayList<Question>();

    //create array to store answer
    private String[] mAnswers = new String[10];

    //array to store correct answer
    private String[] mCorrectAnswers = {"B", "A", "E", "C", "C", "B", "D", "E", "A", "E"};

    //current index
    private int mCurrentQuestionIndex = 0;

    //create a hashmap to store each button's state
    HashMap<Integer, Drawable> saveButtonColor = new HashMap<Integer, Drawable>();

    //create arraylist to store button
    ArrayList<Button> buttonList = new ArrayList<Button>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the view from layout souce file
        mAButton = (Button) findViewById(R.id.A_button);
        mBButton = (Button) findViewById(R.id.B_button);
        mCButton = (Button) findViewById(R.id.C_button);
        mDButton = (Button) findViewById(R.id.D_button);
        mEButton = (Button) findViewById(R.id.E_button);

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        //add each button to button list
        buttonList.add(mAButton);
        buttonList.add(mBButton);
        buttonList.add(mCButton);
        buttonList.add(mDButton);
        buttonList.add(mEButton);

        //add 10 questions to question list
        mQuestions.add(new Question(getString(R.string.question1)));
        mQuestions.add(new Question(getString(R.string.question2)));
        mQuestions.add(new Question(getString(R.string.question3)));
        mQuestions.add(new Question(getString(R.string.question4)));
        mQuestions.add(new Question(getString(R.string.question5)));
        mQuestions.add(new Question(getString(R.string.question6)));
        mQuestions.add(new Question(getString(R.string.question7)));
        mQuestions.add(new Question(getString(R.string.question8)));
        mQuestions.add(new Question(getString(R.string.question9)));
        mQuestions.add(new Question(getString(R.string.question10)));

        //set question on screen
        mQuestionTextView.setText(mQuestions.get(mCurrentQuestionIndex).getQuestion());

        //initiate button
        initButton();

        //Handle the A button click
        mAButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //store user's answer to a specific quesion
                mAnswers[mCurrentQuestionIndex] = "A";
                //turn button A to a different color
                changeMyColor(mAButton);
            }
        });


        //Handle the B button click
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store user's answer to a specific quesion
                mAnswers[mCurrentQuestionIndex] = "B";
                //turn button A to a different color
                changeMyColor(mBButton);
            }
        });


        //Handle the C button click
        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store user's answer to a specific quesion
                mAnswers[mCurrentQuestionIndex] = "C";
                //turn button C to a different color
                changeMyColor(mCButton);
            }
        });


        //Handle the D button click
        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store user's answer to a specific quesion
                mAnswers[mCurrentQuestionIndex] = "D";
                //turn button D to a different color
                changeMyColor(mDButton);
            }
        });



        //Handle the E button click
        mEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle the E button click
                mAnswers[mCurrentQuestionIndex] = "E";
                //turn button E to a different color
                changeMyColor(mEButton);
            }
        });


        //Handle the PREV button click
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            //save current button color
            saveColor();
            //if current question is the first question, then previous question is the last question
            if(mCurrentQuestionIndex ==0)
            mCurrentQuestionIndex =mQuestions.size();
            //set question index to the previous one
            mCurrentQuestionIndex--;
            //set question to the screen
            mQuestionTextView.setText(mQuestions.get(mCurrentQuestionIndex).

            getQuestion());

            //update color of the previous question
            setColor();
        }
        });

        //Handle the NEXT button click
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save current button color
                saveColor();
                //set question index to next one
                mCurrentQuestionIndex++;
                //if current question is the last question, then next question is the first one
                if (mCurrentQuestionIndex >= mQuestions.size())
                    mCurrentQuestionIndex = 0;
                //set question to the screen
                mQuestionTextView.setText(mQuestions.get(mCurrentQuestionIndex).getQuestion());
                //set color of the next question
                setColor();
            }
        });

        //Handle the SUBMIT button click
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //print score to the user
                mQuestionTextView.setText("Your total score is: " + caculateScore() + "/10");
                //reset the score
                score = 0;
                //clear the answers that user has chosen
                for (int i = 0; i < mAnswers.length; i++) {
                    mAnswers[i] = null;
                }
                //set all of the button color to default
                saveButtonColor.clear();
                hideButton();
            }
        });
    }

    //calculate the total score of the user
    private int caculateScore() {
        for (int i = 0; i < 10; i++) {
            if (mAnswers[i] != null) {
                if (mAnswers[i].equals(mCorrectAnswers[i])) {
                    score++;
                }
            }
        }
        return score;
    }


    //save current button color
    private void saveColor() {
        Character firstOption = 'A';
        Character secondOption = 'B';
        Character thirdOption = 'C';
        Character fourthOption = 'D';
        Character fifthOption = 'E';

        //give each button a specific integer value and store its corresponding background
        saveButtonColor.put(100 + mCurrentQuestionIndex + (int) firstOption, mAButton.getBackground());
        saveButtonColor.put(200 + mCurrentQuestionIndex + (int) secondOption, mBButton.getBackground());
        saveButtonColor.put(300 + mCurrentQuestionIndex + (int) thirdOption, mCButton.getBackground());
        saveButtonColor.put(400 + mCurrentQuestionIndex + (int) fourthOption, mDButton.getBackground());
        saveButtonColor.put(500 + mCurrentQuestionIndex + (int) fifthOption, mEButton.getBackground());
    }

    //set button color
    private void setColor() {
        mAButton.setBackground(saveButtonColor.get(100 + mCurrentQuestionIndex + (int) 'A'));
        mBButton.setBackground(saveButtonColor.get(200 + mCurrentQuestionIndex + (int) 'B'));
        mCButton.setBackground(saveButtonColor.get(300 + mCurrentQuestionIndex + (int) 'C'));
        mDButton.setBackground(saveButtonColor.get(400 + mCurrentQuestionIndex + (int) 'D'));
        mEButton.setBackground(saveButtonColor.get(500 + mCurrentQuestionIndex + (int) 'E'));
    }



    //change color of chosen button
    private void changeMyColor(Button button) {
        for (Button buttons : buttonList) {
            //change color of button in the list corresponds to the user's chosen button
            if (buttons == button) {
                buttons.setBackgroundColor(Color.rgb(255, 204, 230));
            } else {
                buttons.setBackgroundColor(Color.rgb(255, 255, 255));
            }
        }

    }


    //set default background color of buttons
    private void initButton() {
        mAButton.setBackgroundColor(Color.rgb(255, 255, 255));
        mBButton.setBackgroundColor(Color.rgb(255, 255, 255));
        mCButton.setBackgroundColor(Color.rgb(255, 255, 255));
        mDButton.setBackgroundColor(Color.rgb(255, 255, 255));
        mEButton.setBackgroundColor(Color.rgb(255, 255, 255));
    }

    private void hideButton(){
        for (Button b: buttonList){
            b.setVisibility(View.GONE);
        }
        mPrevButton.setVisibility(View.GONE);
        mSubmitButton.setVisibility(View.GONE);
        mNextButton.setVisibility(View.GONE);
    }


}
