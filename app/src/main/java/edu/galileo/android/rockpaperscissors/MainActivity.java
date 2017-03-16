package edu.galileo.android.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView scorePhone, scoreTies, scoreUser;
    private TextView winnerPhone, winnerUser, optPhone, optUser;
    private Button btnRock, btnPaper, btnScissor, btnTryAgain;
    private LinearLayout buttonsGame;

    private int scrPhone = 0, scrTie = 0, scrUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setListeners();
    }

    private void getViews() {
        scorePhone = (TextView) findViewById(R.id.score_phone);
        scoreTies = (TextView) findViewById(R.id.score_ties);
        scoreUser = (TextView) findViewById(R.id.score_user);
        winnerPhone = (TextView) findViewById(R.id.winner_phone);
        winnerUser = (TextView) findViewById(R.id.winner_user);
        optPhone = (TextView) findViewById(R.id.option_phone);
        optUser = (TextView) findViewById(R.id.option_user);

        btnRock = (Button) findViewById(R.id.btn_rock);
        btnPaper = (Button) findViewById(R.id.btn_paper);
        btnScissor = (Button) findViewById(R.id.btn_scissor);
        btnTryAgain = (Button) findViewById(R.id.btn_again);

        buttonsGame = (LinearLayout) findViewById(R.id.buttons_game);
    }

    private void setListeners(){
        btnRock.setOnClickListener(this);
        btnPaper.setOnClickListener(this);
        btnScissor.setOnClickListener(this);
        btnTryAgain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_rock:
                play(0);
                break;
            case R.id.btn_paper:
                play(1);
                break;
            case R.id.btn_scissor:
                play(2);
                break;
            case R.id.btn_again:
                start();
                break;
        }
    }

    /*
        0 = Rock
        1 = Paper
        2 = Scissor
     */
    private void play(int option){
        int optionPhone = generateOptionPhone();

        if(option == optionPhone){
            updateViews(0, option, optionPhone);
        }
        else{
            switch (option){
                case 0:
                    if(optionPhone == 1){
                        updateViews(1, option, optionPhone);
                    }
                    else if(optionPhone == 2){
                        updateViews(2, option, optionPhone);
                    }
                    break;
                case 1:
                    if(optionPhone == 0){
                        updateViews(2, option, optionPhone);
                    }
                    else if(optionPhone == 2){
                        updateViews(1, option, optionPhone);
                    }
                    break;
                case 2:
                    if(optionPhone == 0){
                        updateViews(1, option, optionPhone);
                    }
                    else if(optionPhone == 1){
                        updateViews(2, option, optionPhone);
                    }
                    break;
            }
        }
    }

    /*
        0 = Rock
        1 = Paper
        2 = Scissor
     */
    private int generateOptionPhone(){
        Random generator = new Random();
        return generator.nextInt(3);
    }

    private void start(){
        winnerPhone.setVisibility(View.INVISIBLE);
        winnerUser.setVisibility(View.INVISIBLE);
        btnTryAgain.setVisibility(View.GONE);
        buttonsGame.setVisibility(View.VISIBLE);

        optPhone.setText(getString(R.string.msg_unknown));
        optUser.setText(getString(R.string.msg_unknown));
    }

    /*
        0 = Tie
        1 = Phone
        2 = User
     */
    private void updateViews(int winner, int optionUser, int optionPhone){
        String[] options = {getString(R.string.button_rock),getString(R.string.button_paper),getString(R.string.button_scissors)};
        btnTryAgain.setVisibility(View.VISIBLE);
        buttonsGame.setVisibility(View.GONE);

        optPhone.setText(options[optionPhone]);
        optUser.setText(options[optionUser]);

        switch (winner){
            case 0:
                winnerUser.setVisibility(View.VISIBLE);
                winnerUser.setText(getString(R.string.msg_tie));
                scrTie++;
                scoreTies.setText(String.valueOf(scrTie));
                break;
            case 1:
                winnerPhone.setVisibility(View.VISIBLE);
                winnerPhone.setText(getString(R.string.msg_winner));
                scrPhone++;
                scorePhone.setText(String.valueOf(scrPhone));
                break;
            case 2:
                winnerUser.setVisibility(View.VISIBLE);
                winnerUser.setText(getString(R.string.msg_winner));
                scrUser++;
                scoreUser.setText(String.valueOf(scrUser));
                break;
        }
    }
}
