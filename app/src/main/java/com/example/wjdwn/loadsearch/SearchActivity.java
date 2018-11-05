package com.example.wjdwn.loadsearch;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class SearchActivity extends AppCompatActivity {
    ArrayList<String> result;
    Button btn[] = new Button[9];
    Button send;
    String btn_name;
    boolean clicked;
    int start_position, end_position, size;
    FloydAlgorithm floydAlgorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        clicked = false;
        result = new ArrayList<>();
        // 인텐트 받기
        Intent passedIntent = getIntent();
        prosessIntent(passedIntent);
        send = findViewById(R.id.send);
        btn[0] = findViewById(R.id.button1);
        btn[1] = findViewById(R.id.button2);
        btn[2] = findViewById(R.id.button3);
        btn[3] = findViewById(R.id.button4);
        btn[4] = findViewById(R.id.button5);
        btn[5] = findViewById(R.id.button6);
        btn[6] = findViewById(R.id.button7);
        btn[7] = findViewById(R.id.button8);
        btn[8] = findViewById(R.id.button9);


        for (int i = 0; i < result.size(); i++) {
            btn[i].setText(result.get(i));
        }
        for (int i = 0; i < btn.length; i++) {
            if (btn_name.equals(btn[i].getText().toString())) {
                String temp = result.get(i) + " (현재)";
                start_position = i+1;
                btn[i].setText(temp);
                btn[i].setBackgroundColor(Color.parseColor("#1DE9B6"));
            }
        }
        for (int i = 0; i < btn.length; i++) {
            if (btn[i].getText().equals("Button"))
                btn[i].setVisibility(View.GONE);
            else
                btn[i].setOnClickListener(onClickListener);
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }


    Button.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if(!clicked && !String.valueOf(b.getText()).contains("현재")) {
                clicked = true;
                b.setBackgroundColor(Color.parseColor("#FF8A65"));
                switch (v.getId()) {
                    case R.id.button1:
                        end_position = 1;
                        break;
                    case R.id.button2:
                        end_position = 2;
                        break;
                    case R.id.button3:
                        end_position = 3;
                        break;
                    case R.id.button4:
                        end_position = 4;
                        break;
                    case R.id.button5:
                        end_position = 5;
                        break;
                    case R.id.button6:
                        end_position = 6;
                        break;
                    case R.id.button7:
                        end_position = 7;
                        break;
                    case R.id.button8:
                        end_position = 8;
                        break;
                    case R.id.button9:
                        end_position = 9;
                        break;
                }
            }
        }
    };

    private void test(){
        Log.d("포지션 체크", "시작지 : "+start_position+", 도착지 : "+end_position + ", 사이즈 : " + result.size());
        size = result.size();

        floydAlgorithm = new FloydAlgorithm();
        int N = size; //number of node
        int S, V; //start edge, finish edge
        int[][] graph = new int[10][10];
        //graph[i][j] : i -> j, shortest distance

        S = end_position;
        V = start_position;
        int re = floydAlgorithm.floydAlgorithm(N, S, V, graph);
        if(re <= 0){
            floydAlgorithm.makeGraph(N, graph);
            S = end_position;
            V = start_position;
            floydAlgorithm.floydAlgorithm(N, S, V, graph);
        }
        for(int i = floydAlgorithm.getResult().size(); i > 0; i--){
            btn[floydAlgorithm.getResult().pop()-1].setBackgroundColor(Color.parseColor("#A1887F"));
        }

    }

    private void prosessIntent(Intent intent) {
        if (intent != null) {

            // ExtraData .getSerializableExtra 로 받기.
            result = (ArrayList<String>) intent.getSerializableExtra("data");
            btn_name = intent.getStringExtra("data_name");
            Log.d("받은 데이터", btn_name);
            if (result != null) {
                Toast.makeText(getApplicationContext(), "전달받은 data :" + result.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        Collections.shuffle(result);

    }
}
