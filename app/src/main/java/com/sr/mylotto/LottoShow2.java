package com.sr.mylotto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LottoShow2 extends AppCompatActivity {
    boolean findRound = false;
    EditText etedit;

    TextView tv0,tv1,tv2,tv3,tv4,tv5,tv6,tvb,tvRound;

    String saveNum = "";
    LottoDBHelper ldb = new LottoDBHelper(LottoShow2.this,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_show2);

        etedit = (EditText) findViewById(R.id.etedit);

        tv0 = (TextView) findViewById(R.id.tv0);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tvb = (TextView) findViewById(R.id.tvb);

        ldb.showRound();
        tvRound = (TextView) findViewById(R.id.tvRound);
        tvRound.setText("* 현재 ["+ldb.lottoRound+ "] 회 차 ");

        Button btn_find = (Button) findViewById(R.id.btn_find);
        Button btn_main = (Button) findViewById(R.id.btn_main);
        Button btn_reset = (Button) findViewById(R.id.btn_reset);

        btn_find.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                int idx = 0;
                if(etedit.getText().toString().equals("")){
                    Toast.makeText(LottoShow2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                idx = Integer.parseInt(etedit.getText().toString());

                SQLiteDatabase db = ldb.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM testlotto5 WHERE idx = '"+idx+"'",null);
                while(cursor.moveToNext()) {

                    if (cursor.getInt(0) == idx) {
                        findRound = true;
                        tv0.setText("로또 제 "+cursor.getInt(0)+"회 차");
                        tv1.setText(cursor.getInt(1)+"");
                        tv2.setText(cursor.getInt(2)+"");
                        tv3.setText(cursor.getInt(3)+"");
                        tv4.setText(cursor.getInt(4)+"");
                        tv5.setText(cursor.getInt(5)+"");
                        tv6.setText(cursor.getInt(6)+"");
                        tvb.setText(cursor.getInt(7)+"");
                        saveNum = etedit.getText().toString();
                        etedit.setText("");
                    }

                }
                if(findRound == false) {
                    Toast.makeText(LottoShow2.this, "해당 회차를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                    tv0.setText(saveNum + " 회 차 를 찾을 수 없습니다.");
                    tv1.setText("??");
                    tv2.setText("??");
                    tv3.setText("??");
                    tv4.setText("??");
                    tv5.setText("??");
                    tv6.setText("??");
                    tvb.setText("??");
                }

                findRound = false; //초기화
                saveNum = ""; //초기화
            }
        });

        btn_main.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(LottoShow2.this, LottoMain2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                tv0.setText("");
                tv1.setText("");
                tv2.setText("");
                tv3.setText("");
                tv4.setText("");
                tv5.setText("");
                tv6.setText("");
                tvb.setText("");
                saveNum = ""; //초기화
                findRound = false; //초기화
            }
        });
    }
}