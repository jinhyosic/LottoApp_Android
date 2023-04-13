package com.sr.mylotto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LottoInsert2 extends AppCompatActivity {

    //db
    LottoDBHelper ldb = new LottoDBHelper(LottoInsert2.this,1);
    int db1,db2,db3,db4,db5,db6,dbBonus;
    EditText num1, num2, num3,num4,num5,num6,numBonus;

    TextView tvRound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_insert2);

        num1 = (EditText) findViewById(R.id.num1);


        num2 = (EditText) findViewById(R.id.num2);
        num3 = (EditText) findViewById(R.id.num3);
        num4 = (EditText) findViewById(R.id.num4);
        num5 = (EditText) findViewById(R.id.num5);
        num6 = (EditText) findViewById(R.id.num6);
        numBonus = (EditText) findViewById(R.id.numBonus);

        ldb.showRound();
        tvRound = (TextView) findViewById(R.id.tvRound);
        tvRound.setText("* 현재 ["+ (ldb.lottoRound) +"] 회 차.");

        Button btn_insert =(Button) findViewById(R.id.btn_insert);
        Button btn_main = (Button) findViewById(R.id.btn_main);
        Button btn_reset = (Button) findViewById(R.id.btn_reset);





        btn_insert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                if (num1.getText().toString().equals("")) {
                    Toast.makeText(LottoInsert2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num2.getText().toString().equals("")) {
                    Toast.makeText(LottoInsert2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num3.getText().toString().equals("")) {
                    Toast.makeText(LottoInsert2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num4.getText().toString().equals("")) {
                    Toast.makeText(LottoInsert2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num5.getText().toString().equals("")) {
                    Toast.makeText(LottoInsert2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num6.getText().toString().equals("")) {
                    Toast.makeText(LottoInsert2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (numBonus.getText().toString().equals("")) {
                    Toast.makeText(LottoInsert2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //공백 없을 시 db로 전달할 변수에 숫자 저장
                db1 = Integer.parseInt(num1.getText().toString());
                db2 = Integer.parseInt(num2.getText().toString());
                db3 = Integer.parseInt(num3.getText().toString());
                db4 = Integer.parseInt(num4.getText().toString());
                db5 = Integer.parseInt(num5.getText().toString());
                db6 = Integer.parseInt(num6.getText().toString());
                dbBonus = Integer.parseInt(numBonus.getText().toString());

                //모든 값이 1부터 45까지의 수만 입력됨
                if ((0 < db1 && 46 > db1) && (0 < db2 && 46 > db2) && (0 < db3 && 46 > db3) &&
                        (0 < db4 && 46 > db4) && (0 < db5 && 46 > db5) &&
                        (0 < db6 && 46 > db6) && (0 < dbBonus && 46 > dbBonus)) {
                    //db에 값 넣기
                    ldb.showRound();
                    ldb.insert(db1,db2,db3,db4,db5,db6,dbBonus);
                    Toast.makeText(LottoInsert2.this,"당첨번호 갱신완료",Toast.LENGTH_SHORT).show();
                    num1.setText("");
                    num2.setText("");
                    num3.setText("");
                    num4.setText("");
                    num5.setText("");
                    num6.setText("");
                    numBonus.setText("");
                    ldb.showRound();
                    tvRound.setText("* 현재 ["+ (ldb.lottoRound) +"] 회 차.");

                }
                else
                    Toast.makeText(LottoInsert2.this,"로또 번호는 정수 1부터 45까지만 입력 받습니다.",Toast.LENGTH_LONG).show();
            }
        });
        btn_main.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(LottoInsert2.this, LottoMain2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                num1.setText("");
                num2.setText("");
                num3.setText("");
                num4.setText("");
                num5.setText("");
                num6.setText("");
                numBonus.setText("");
            }
        });

    }

}