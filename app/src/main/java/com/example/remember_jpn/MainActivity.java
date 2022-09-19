package com.example.remember_jpn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_text;
    private Button btn_kanji_quiz;
    private Button btn_korean_quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_kanji_quiz = findViewById(R.id.btn_kanji_quiz);
        btn_korean_quiz = findViewById(R.id.btn_korean_quiz);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), subActivity.class);
                switch (view.getId()) {
                    case R.id.btn_kanji_quiz:
                        intent.putExtra("kindOfQuiz","kanji");
                        break;

                    case R.id.btn_korean_quiz:
                        intent.putExtra("kindOfQuiz","korean");
                        break;
                }
                startActivity(intent);
            }
        };

        btn_kanji_quiz.setOnClickListener(onClickListener);
        btn_korean_quiz.setOnClickListener(onClickListener);

//        btn_text = findViewById(R.id.btn_text);
//
//        btn_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //readtext();
//                Intent intent = new Intent(getApplicationContext(), subActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    /*
    public class Voca {
        public String 일본어한자;
        public String 음독;
        public String 훈독;
        public String 부수;
        public String 총획수;
        public String 한국한자;
    }

     */


//    private void readtext()
//    {
//        try {
//            /*
//            InputStream in = getResources().openRawResource(R.raw.test);
//            byte[] b = new byte[in.available()];
//            in.read(b);
//            String s = new String(b);
//            Log.e("test",s);
//            */
//
//            List<Voca>vocaList = new ArrayList<>();
//
//            InputStream is = getResources().openRawResource(R.raw.test);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            String line;
//            Voca temp = null;
//            while((line = br.readLine()) != null) {
//                Log.e("test",line);
//
//                if(line.length() == 0) {
//                    if(temp != null)
//                        vocaList.add(temp);
//
//                    temp = new Voca();
//                    continue;
//                }
//                else if(line.contains("음독")) {
//                    temp.음독 = line;
//                }
//                else if(line.contains("훈독")) {
//                    temp.훈독 = line;
//                }
//                else if(line.contains("부수")) {
//                    temp.부수 = line;
//                }
//                else if(line.contains("총획수")) {
//                    temp.총획수 = line;
//                }
//                else if(line.contains("한국한자")) {
//                    temp.한국한자 = line;
//                }
//                else{
//                    temp.일본어한자 = line;
//                }
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}