package com.sr.mylotto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class LottoSelect2 extends AppCompatActivity {

    static final String DB_NAME = "testlotto5.db";
    int img = 0;
    LottoDBHelper ldb = new LottoDBHelper(LottoSelect2.this,1);
    EditText num1, num2, num3,num4,num5,num6,numBonus;
    TextView num1Per,num2Per,num3Per,num4Per,num5Per,num6Per,numBonusPer;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tvb , tvRound;
    ImageView imglotto;
    int db1,db2,db3,db4,db5,db6,dbBonus;//값 비교를 위해 정수값 저장
    double rs1,rs2,rs3,rs4,rs5,rs6,rsb;
    int n1cnt=0,n2cnt=0,n3cnt=0,n4cnt=0,n5cnt=0,n6cnt=0,nbcnt = 0;//숫자 당첨 확률 상승 변수
    int total = 0; //로또 총회차
    String strTotal = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_select2);

        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        num3 = (EditText) findViewById(R.id.num3);
        num4 = (EditText) findViewById(R.id.num4);
        num5 = (EditText) findViewById(R.id.num5);
        num6 = (EditText) findViewById(R.id.num6);
        numBonus = (EditText) findViewById(R.id.numBonus);

        num1Per = (TextView) findViewById(R.id.num1Per);
        num2Per = (TextView) findViewById(R.id.num2Per);
        num3Per = (TextView) findViewById(R.id.num3Per);
        num4Per = (TextView) findViewById(R.id.num4Per);
        num5Per = (TextView) findViewById(R.id.num5Per);
        num6Per = (TextView) findViewById(R.id.num6Per);
        numBonusPer = (TextView) findViewById(R.id.numBonusPer);

        ldb.showRound();
        tvRound = (TextView) findViewById(R.id.tvRound);
        tvRound.setText("* 현재 ["+ldb.lottoRound+ "] 회 차 까지 확률 결과");

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tvb = (TextView) findViewById(R.id.tvb);

        imglotto = (ImageView)findViewById(R.id.imglotto);

        Button btn_select = (Button) findViewById(R.id.btn_select);
        Button btn_main = (Button) findViewById(R.id.btn_main);
        Button btn_rand = (Button) findViewById(R.id.btn_rand);

        btn_select.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (num1.getText().toString().equals("")) {
                    Toast.makeText(LottoSelect2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num2.getText().toString().equals("")) {
                    Toast.makeText(LottoSelect2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num3.getText().toString().equals("")) {
                    Toast.makeText(LottoSelect2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num4.getText().toString().equals("")) {
                    Toast.makeText(LottoSelect2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num5.getText().toString().equals("")) {
                    Toast.makeText(LottoSelect2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num6.getText().toString().equals("")) {
                    Toast.makeText(LottoSelect2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (numBonus.getText().toString().equals("")) {
                    Toast.makeText(LottoSelect2.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                db1 = Integer.parseInt(num1.getText().toString());
                db2 = Integer.parseInt(num2.getText().toString());
                db3 = Integer.parseInt(num3.getText().toString());
                db4 = Integer.parseInt(num4.getText().toString());
                db5 = Integer.parseInt(num5.getText().toString());
                db6 = Integer.parseInt(num6.getText().toString());
                dbBonus = Integer.parseInt(numBonus.getText().toString());

                if ((0 < db1 && 46 > db1) && (0 < db2 && 46 > db2) && (0 < db3 && 46 > db3) &&
                        (0 < db4 && 46 > db4) && (0 < db5 && 46 > db5) &&
                        (0 < db6 && 46 > db6) && (0 < dbBonus && 46 > dbBonus)) {
                    //확률 조회시 랜덤 이미지 기능
                    Random rd = new Random();
                    img = rd.nextInt(4)+1;

                    if(img == 1) {
                        imglotto.setImageResource(R.drawable.coin4);
                    }
                    else if(img == 2){
                        imglotto.setImageResource(R.drawable.coin);
                    }
                    else if(img == 3){
                        imglotto.setImageResource(R.drawable.coin2);
                    }
                    else if(img == 4){
                        imglotto.setImageResource(R.drawable.coin3);
                    }
                    select();
                }
                else{
                    Toast.makeText(LottoSelect2.this,"로또는 정수 1부터 45까지만  입력 받습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_main.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                num1.setText("");
                num2.setText("");
                num3.setText("");
                num4.setText("");
                num5.setText("");
                num6.setText("");
                numBonus.setText("");
                cleanNum();
            }
        });
        btn_rand.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                randNumber();
                Toast.makeText(LottoSelect2.this,"자동 번호 생성 완료",Toast.LENGTH_SHORT).show();
                Random rd = new Random();
                img = rd.nextInt(4)+1;

                if(img == 1) {
                    imglotto.setImageResource(R.drawable.coin4);
                }
                else if(img == 2){
                    imglotto.setImageResource(R.drawable.coin);
                }
                else if(img == 3){
                    imglotto.setImageResource(R.drawable.coin2);
                }
                else if(img == 4){
                    imglotto.setImageResource(R.drawable.coin3);
                }
            }
        });
    }
    //db조회 후 확률 조회연산
    public void select(){
        ldb = new LottoDBHelper(LottoSelect2.this, 1);
        SQLiteDatabase db = ldb.getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM testlotto5"),null);
       // Cursor cursr1 = db.rawQuery(("SELECT COUNT(idx) FROM testlotto2"),null);
        total =0;
        String str1, str2,str3,str4,str5,str6,strBonus;//문자열로 변환된 텍스트필드값

        //db값과 비교전 문자열로 변환 후 비교
        str1 = num1.getText().toString();
        str2 = num2.getText().toString();
        str3 = num3.getText().toString();
        str4 = num4.getText().toString();
        str5 = num5.getText().toString();
        str6 = num6.getText().toString();
        strBonus = numBonus.getText().toString();
        while(cursor.moveToNext()){

           if(str1.equals(cursor.getString(1).toString())){
               n1cnt++;
           }
           if(str1.equals(cursor.getString(2).toString())){
               n1cnt++;
           }
            if(str1.equals(cursor.getString(3).toString())){
                n1cnt++;
            }
            if(str1.equals(cursor.getString(4).toString())){
                n1cnt++;
            }
            if(str1.equals(cursor.getString(5).toString())){
                n1cnt++;
            }
            if(str1.equals(cursor.getString(6).toString())){
                n1cnt++;
            }
            if(str1.equals(cursor.getString(7).toString())){
                n1cnt++;
            }
            //첫번째 숫자 확률 상승 끝
            if(str2.equals(cursor.getString(1).toString())){
                n2cnt++;
            }
            if(str2.equals(cursor.getString(2).toString())){
                n2cnt++;
            }
            if(str2.equals(cursor.getString(3).toString())){
                n2cnt++;
            }
            if(str2.equals(cursor.getString(4).toString())){
                n2cnt++;
            }
            if(str2.equals(cursor.getString(5).toString())){
                n2cnt++;
            }
            if(str2.equals(cursor.getString(6).toString())){
                n2cnt++;
            }
            if(str2.equals(cursor.getString(7).toString())){
                n2cnt++;
            }
            //두번째 숫자 확률 상승 끝


            if(str3.equals(cursor.getString(1).toString())){
                n3cnt++;
            }
            if(str3.equals(cursor.getString(2).toString())){
                n3cnt++;
            }
            if(str3.equals(cursor.getString(3).toString())){
                n3cnt++;
            }
            if(str3.equals(cursor.getString(4).toString())){
                n3cnt++;
            }
            if(str3.equals(cursor.getString(5).toString())){
                n3cnt++;
            }
            if(str3.equals(cursor.getString(6).toString())){
                n3cnt++;
            }
            if(str3.equals(cursor.getString(7).toString())){
                n3cnt++;
            }
            //3번째 숫자 확률 상승 끝


            if(str4.equals(cursor.getString(1).toString())){
                n4cnt++;
            }
            if(str4.equals(cursor.getString(2).toString())){
                n4cnt++;
            }
            if(str4.equals(cursor.getString(3).toString())){
                n4cnt++;
            }
            if(str4.equals(cursor.getString(4).toString())){
                n4cnt++;
            }
            if(str4.equals(cursor.getString(5).toString())){
                n4cnt++;
            }
            if(str4.equals(cursor.getString(6).toString())){
                n4cnt++;
            }
            if(str4.equals(cursor.getString(7).toString())){
                n4cnt++;
            }
            //4번째 숫자 확률 상승 끝

            if(str5.equals(cursor.getString(1).toString())){
                n5cnt++;
            }
            if(str5.equals(cursor.getString(2).toString())){
                n5cnt++;
            }
            if(str5.equals(cursor.getString(3).toString())){
                n5cnt++;
            }
            if(str5.equals(cursor.getString(4).toString())){
                n5cnt++;
            }
            if(str5.equals(cursor.getString(5).toString())){
                n5cnt++;
            }
            if(str5.equals(cursor.getString(6).toString())){
                n5cnt++;
            }
            if(str5.equals(cursor.getString(7).toString())){
                n5cnt++;
            }
            //5번째 숫자 확률 상승 끝

            if(str6.equals(cursor.getString(1).toString())){
                n6cnt++;
            }
            if(str6.equals(cursor.getString(2).toString())){
                n6cnt++;
            }
            if(str6.equals(cursor.getString(3).toString())){
                n6cnt++;
            }
            if(str6.equals(cursor.getString(4).toString())){
                n6cnt++;
            }
            if(str6.equals(cursor.getString(5).toString())){
                n6cnt++;
            }
            if(str6.equals(cursor.getString(6).toString())){
                n6cnt++;
            }
            if(str6.equals(cursor.getString(7).toString())){
                n6cnt++;
            }
            //6번째 숫자 확률 상승 끝

            if(strBonus.equals(cursor.getString(1).toString())){
                nbcnt++;
            }
            if(strBonus.equals(cursor.getString(2).toString())){
                nbcnt++;
            }
            if(strBonus.equals(cursor.getString(3).toString())){
                nbcnt++;
            }
            if(strBonus.equals(cursor.getString(4).toString())){
                nbcnt++;
            }
            if(strBonus.equals(cursor.getString(5).toString())){
                nbcnt++;
            }
            if(strBonus.equals(cursor.getString(6).toString())){
                nbcnt++;
            }
            if(strBonus.equals(cursor.getString(7).toString())){
                nbcnt++;
            }
            //보너스번호 숫자 확률 상승 끝

            total++;

        }
        //확률연산결과 저장
        rs1 = Math.round(((((double)n1cnt / (double)total)*100.0)) *100) / 100.0;
        rs2 = Math.round(((((double)n2cnt / (double)total)*100.0)) *100) / 100.0;
        rs3 = Math.round(((((double)n3cnt / (double)total)*100.0)) *100) / 100.0;
        rs4 = Math.round(((((double)n4cnt / (double)total)*100.0)) *100) / 100.0;
        rs5 = Math.round(((((double)n5cnt / (double)total)*100.0)) *100) / 100.0;
        rs6 = Math.round(((((double)n6cnt / (double)total)*100.0)) *100) / 100.0;
        rsb = Math.round(((((double)nbcnt / (double)total)*100.0)) *100) / 100.0;

        //텍스트 뷰에 확률 연산 결과 띄우기
        showPercent();

        //알림창 띄우기
        Toast.makeText(LottoSelect2.this,"확률 연산 완료",Toast.LENGTH_SHORT).show();

        //다음 연산을 위해 여러 확률 변수 초기화
         cleanNum();

         //사용자가 에디트 텍스트에 넣었던 값을 텍스트뷰에 띄움
        showPickNum();

        //에디트텍스트 초기화
        cleanText();
    }
        public void showPickNum(){
            tv1.setText("숫자 "+num1.getText().toString()+"번 당첨 확률 :");
            tv2.setText("숫자 "+num2.getText().toString()+"번 당첨 확률 :");
            tv3.setText("숫자 "+num3.getText().toString()+"번 당첨 확률 :");
            tv4.setText("숫자 "+num4.getText().toString()+"번 당첨 확률 :");
            tv5.setText("숫자 "+num5.getText().toString()+"번 당첨 확률 :");
            tv6.setText("숫자 "+num6.getText().toString()+"번 당첨 확률 :");
            tvb.setText("숫자 "+numBonus.getText().toString()+"번 당첨 확률 :");
        }
        public void cleanText(){
            num1.setText("");
            num2.setText("");
            num3.setText("");
            num4.setText("");
            num5.setText("");
            num6.setText("");
            numBonus.setText("");

        }
        public void cleanNum(){
            n1cnt = 0;  rs1 = 0;
            n2cnt = 0;  rs2 = 0;
            n3cnt = 0;  rs3 = 0;
            n4cnt = 0;  rs4 = 0;
            n5cnt = 0;  rs5 = 0;
            n6cnt = 0;  rs6 = 0;
            nbcnt = 0;  rsb = 0;
            total = 0;
        }
        public void showPercent(){
            num1Per.setText(String.valueOf(rs1)+" %");
            num2Per.setText(String.valueOf(rs2)+" %");
            num3Per.setText(String.valueOf(rs3)+" %");
            num4Per.setText(String.valueOf(rs4)+" %");
            num5Per.setText(String.valueOf(rs5)+" %");
            num6Per.setText(String.valueOf(rs6)+" %");
            numBonusPer.setText(String.valueOf(rsb)+" %");
        }
        public void randNumber(){
            Random rd = new Random();
            num1.setText((rd.nextInt(45)+1)+"");
            num2.setText((rd.nextInt(45)+1)+"");
            num3.setText((rd.nextInt(45)+1)+"");
            num4.setText((rd.nextInt(45)+1)+"");
            num5.setText((rd.nextInt(45)+1)+"");
            num6.setText((rd.nextInt(45)+1)+"");
            numBonus.setText((rd.nextInt(45)+1)+"");

        }
}