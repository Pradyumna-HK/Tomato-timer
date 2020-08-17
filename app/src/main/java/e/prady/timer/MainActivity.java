package e.prady.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    SeekBar timerSeekBar;
    MediaPlayer playSound;
    Boolean timerIsActive = false;
    Button GoButton;
    CountDownTimer timer;

    public void restartTimer(){
        timerText.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        timer.cancel();
        GoButton.setText("START");
        timerIsActive = false;


    }


    public void startTimer(View view) {

        if (timerIsActive) {
            restartTimer();

        } else {
            timerIsActive = true;
            timerSeekBar.setEnabled(false);
            GoButton.setText("STOP");
           timer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {

                    updateTimer((int) l / 1000);


                }

                @Override
                public void onFinish() {
                    Log.i("info", "done");
                    playSound.start();
                    restartTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int min = secondsLeft / 60;
        int secs = secondsLeft - (min * 60);
        String seconds;
        if(secs == 0){
            seconds = "00";
        }else if(secs<=9) {
            seconds = "0" + Integer.toString(secs);
        }else
        {
            seconds = Integer.toString(secs);
        }

        timerText.setText(Integer.toString(min) + ":" + seconds);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       timerSeekBar = (SeekBar)findViewById(R.id.seekBar);
         timerText = (TextView)findViewById(R.id.textView);
         playSound = MediaPlayer.create(this,R.raw.sound);
         GoButton = (Button)findViewById(R.id.button);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(60);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);

        }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
