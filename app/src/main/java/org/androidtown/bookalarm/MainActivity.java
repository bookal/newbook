package org.androidtown.bookalarm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.TextView.OnEditorActionListener;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spList, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //각 항목 클릭시 포지션값을 토스트에 띄운다.
                str = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button8 = (Button) findViewById(R.id.button8);
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //검색 버튼
                String searchData = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this ,AlarmSearch.class);
                intent.putExtra("setData",searchData);
                intent.putExtra("Spinner",str);
                startActivityForResult(intent, 101);
            }
        });
        editText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        String searchData = editText.getText().toString();

                        Intent intent = new Intent(MainActivity.this ,AlarmSearch.class);
                        intent.putExtra("setData",searchData);
                        intent.putExtra("Spinner",str);
                        startActivityForResult(intent, 101);
                        break;
                }
                return true;
            }
        });
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
        button8.setOnClickListener(new View.OnClickListener() { // 검색
            public void onClick(View v) { //알림설정 버튼
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,102);
            }
        });
    }
    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String target;
        @Override
        protected void onPreExecute() {
            target = "http://bk66.cafe24.com/List.php";
        }
        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return  null;
        }
        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }
        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(MainActivity.this,ManagementActivity.class);
            intent.putExtra("userList",result);
            MainActivity.this.startActivity(intent);
        }
    }
}