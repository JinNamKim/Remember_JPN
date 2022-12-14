package com.example.remember_jpn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class subActivity extends Activity {

    private Button btn_OK;
    private Button btn_NO;
    private Button btn_check;
    private TextView tv_correct;

    private TextView tv_kindOfQuiz;
    public List<Voca> vocaList = new ArrayList<>();
    public int selectNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmode);

        btn_check = findViewById(R.id.btn_check);

        tv_kindOfQuiz = findViewById(R.id.tv_kindOfQuiz);
        tv_correct = findViewById(R.id.tv_correct);

        btn_OK = findViewById(R.id.btn_OK);
        btn_NO = findViewById(R.id.btn_NO);

        Intent intent = getIntent();
        String str_kindOfQuiz = intent.getStringExtra("kindOfQuiz");

        //tv_kindOfQuiz.setText(str_kindOfQuiz);


        readtext();


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.btn_OK:
                        answerOK();
//                        tv_kindOfQuiz.setText(vocaList.get(selectNumber++).일본어한자);
//                        if(selectNumber >= vocaList.size())
//                            selectNumber = 0;
                        break;

                    case R.id.btn_NO:
                        answerNO();
                        break;

                    case R.id.btn_check:
//                        String aaa = btn_check.getText().toString();
//                        Log.e("a",aaa);
                        //JAVA에서 == 비교는 주소값 비교 !?
                        if(btn_check.getText().equals("START")) {
                            startQuiz();
                        }
                        else {
                            showQuiz(false);
                        }
                        break;
                }

            }
        };

        btn_OK.setOnClickListener(onClickListener);
        btn_NO.setOnClickListener(onClickListener);
        btn_check.setOnClickListener(onClickListener);

    }


    private void startQuiz() {
        btn_check.setText("CHECK");
        selectNumber = 0;
        tv_correct.setText("");
        showQuiz(true);
    }

    private void showQuiz(Boolean isFirst) {
        if(isFirst)
            tv_kindOfQuiz.setText(vocaList.get(selectNumber++).일본어한자);
        btn_check.setEnabled(false);
        btn_OK.setEnabled(true);
        btn_NO.setEnabled(true);

        if(selectNumber >= vocaList.size())
            selectNumber = 0;
    }

    private void nextQuiz() {
        tv_kindOfQuiz.setText(vocaList.get(selectNumber++).일본어한자);
        btn_check.setEnabled(true);
        btn_OK.setEnabled(false);
        btn_NO.setEnabled(false);

        if(selectNumber >= vocaList.size())
            selectNumber = 0;

    }

    private void answerOK() {
        nextQuiz();
    }
    private void answerNO() {
        nextQuiz();
    }

    public class Voca {
        public String 일본어한자;
        public String 음독;
        public String 훈독;
        public String 부수;
        public String 총획수;
        public String 한국한자;
    }

    private void readtext()
    {
        try {



            InputStream is = getResources().openRawResource(R.raw.test);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            Voca temp = null;
            while((line = br.readLine()) != null) {
                Log.e("test",line);

                if(line.length() == 0) {
                    if(temp != null)
                        vocaList.add(temp);

                    temp = new Voca();
                    continue;
                }
                else if(line.contains("음독")) {
                    temp.음독 = line;
                }
                else if(line.contains("훈독")) {
                    temp.훈독 = line;
                }
                else if(line.contains("부수")) {
                    temp.부수 = line;
                }
                else if(line.contains("총획수")) {
                    temp.총획수 = line;
                }
                else if(line.contains("한국한자")) {
                    temp.한국한자 = line;
                }
                else{
                    temp.일본어한자 = line;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
