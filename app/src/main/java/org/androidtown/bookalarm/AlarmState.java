package org.androidtown.bookalarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_state);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button8 = (Button) findViewById(R.id.button8);



        button2.setOnClickListener(new View.OnClickListener() { // 알람셋팅 액티비티 이동

            public void onClick(View v) { //알림설정 버튼
                Intent intent = new Intent(getApplicationContext(), AlarmSetting.class);
                startActivityForResult(intent,102);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() { // 등록현황 액티비티 이동

            public void onClick(View v) { //등록현황 버튼
                Intent intent = new Intent(getApplicationContext(), AlarmState.class);
                startActivityForResult(intent,103);

            }
        });
        button8.setOnClickListener(new View.OnClickListener() { // 알람셋팅 액티비티 이동

            public void onClick(View v) { //알림설정 버튼
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,102);

            }
        });
    }
}

