package com.example.wjdwn.loadsearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    LinearLayout layout;
    Button addbtn;
    EditText editText;
    Context context;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        addbtn = findViewById(R.id.add);
        layout = findViewById(R.id.layout);
        editText = findViewById(R.id.edit);
        context = this;

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (arrayList.contains(editText.getText().toString()) || arrayList.size() >= 9)
                        Toast.makeText(getApplicationContext(), "더는 안됩니다.", Toast.LENGTH_SHORT).show();
                    else {
                        final Button btn = new Button(context);
                        btn.setText(editText.getText().toString());
                        arrayList.add(btn.getText().toString());


                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.gravity = Gravity.CENTER;
                        btn.setLayoutParams(lp);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                                intent.putExtra("data", arrayList);
                                intent.putExtra("data_name", btn.getText().toString());
                                startActivity(intent);
                            }
                        });
                        //부모 뷰에 추가
                        layout.addView(btn);
                        editText.setText("");
                    }
                }
            }
        });

    }
}
