package com.sr.mylotto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LottoDelete2 extends AppCompatActivity {

    boolean findRound = false; //회차 찾음 메세지를 위한 플래그변수
    int delRound; //delete 메서드에 매개변수를 위해 선언
    TextView tv0,tv1,tv2,tv3,tv4,tv5,tv6,tvb , tvRound;
    EditText etdel;

    LottoDBHelper dbHelper = new LottoDBHelper(LottoDelete2.this,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_delete2);

        tv0 = (TextView)findViewById(R.id.tv0);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        tvb = (TextView)findViewById(R.id.tvb);

      //  dbHelper.showRound();
        tvRound = (TextView)findViewById(R.id.tvRound);
        tvRound.setText("* 현재 ["+ dbHelper.lottoRound+"] 회 차");

        Button btn_main = (Button)findViewById(R.id.btn_main);
        Button btn_delete = (Button)findViewById(R.id.btn_delete); //삭제데이터 조회
        Button btn_deleteOK = (Button)findViewById(R.id.btn_deleteOK); //삭제데이터 삭제

        etdel = (EditText)findViewById(R.id.etdel);

        dbHelper = new LottoDBHelper(LottoDelete2.this, 1);

        btn_main.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(LottoDelete2.this, LottoMain2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
            //삭제할 데이터 조회
        btn_delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                findRound = false; //플래그변수 초기화
                if(etdel.getText().toString().equals("")){
                    Toast.makeText(LottoDelete2.this, "삭제 할 회차를 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                    tv0.setText("");
                    tv1.setText("");
                    tv2.setText("");
                    tv3.setText("");
                    tv4.setText("");
                    tv5.setText("");
                    tv6.setText("");
                    tvb.setText("");
                    delRound = 0;
                    return;
                }
                int idx = 0;
                delRound = Integer.parseInt(etdel.getText().toString());
                dbHelper = new LottoDBHelper(LottoDelete2.this,1);
                idx = delRound;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery(("SELECT * FROM testlotto5"),null);

                //해당 회차를 찾는기능.
                while(cursor.moveToNext()){
                    if(idx == cursor.getInt(0)){
                        findRound = true;
                        Toast.makeText(LottoDelete2.this, "해당 회차를 찾았습니다.", Toast.LENGTH_SHORT).show();
                        tv0.setText("로또 제 "+cursor.getString(0)+" 회차");
                        tv1.setText(cursor.getString(1));
                        tv2.setText(cursor.getString(2));
                        tv3.setText(cursor.getString(3));
                        tv4.setText(cursor.getString(4));
                        tv5.setText(cursor.getString(5));
                        tv6.setText(cursor.getString(6));
                        tvb.setText(cursor.getString(7));
                        etdel.setText("");
                    }
                }
                if(findRound == false){
                    Toast.makeText(LottoDelete2.this, "해당 회차를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                    tv0.setText(etdel.getText().toString()+" 회차 를 찾을 수 없음");
                    tv1.setText("??");
                    tv2.setText("??");
                    tv3.setText("??");
                    tv4.setText("??");
                    tv5.setText("??");
                    tv6.setText("??");
                    tvb.setText("??");
                    etdel.setText("");
                    delRound = 0;
                }

            }
        });
        btn_deleteOK.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                if(delRound==0 || findRound ==false) {
                    Toast.makeText(LottoDelete2.this, "해당 회차를 삭제 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbHelper.delete(delRound);
                    Toast.makeText(LottoDelete2.this, delRound + " 회차 데이터 삭제완료", Toast.LENGTH_SHORT).show();
                    tv0.setText(delRound+" 회차 데이터 삭제 완료");
                    tv1.setText(delRound+" 회차 데이터 삭제 완료");
                    tv2.setText(delRound+" 회차 데이터 삭제 완료");
                    tv3.setText(delRound+" 회차 데이터 삭제 완료");
                    tv4.setText(delRound+" 회차 데이터 삭제 완료");
                    tv5.setText(delRound+" 회차 데이터 삭제 완료");
                    tv6.setText(delRound+" 회차 데이터 삭제 완료");
                    tvb.setText(delRound+" 회차 데이터 삭제 완료");
                    delRound = 0;
                }
            }
        });
    }
}