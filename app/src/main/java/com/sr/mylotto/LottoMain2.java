package com.sr.mylotto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LottoMain2 extends AppCompatActivity {

    LottoDBHelper ldb = new LottoDBHelper(LottoMain2.this,1);
    TextView tvRound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_main2);

        ldb.showRound();
        tvRound = (TextView)findViewById(R.id.tvRound);
        tvRound.setText("*현재 ["+ldb.lottoRound+"]회 차.");

        findViewById(R.id.btn_select).setOnClickListener(mClick);
        findViewById(R.id.btn_show).setOnClickListener(mClick);
        findViewById(R.id.btn_insert).setOnClickListener(mClick);
        findViewById(R.id.btn_delete).setOnClickListener(mClick);
        findViewById(R.id.btn_edit).setOnClickListener(mClick);

    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btn_select:
                    Intent intent1 = new Intent(LottoMain2.this, LottoSelect2.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);
                    break;
                case R.id.btn_show:
                    Intent intent5 = new Intent(LottoMain2.this, LottoShow2.class);
                    intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent5);
                    break;
                case R.id.btn_insert:
                    Intent intent2 = new Intent(LottoMain2.this, LottoInsert2.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                    break;
                case R.id.btn_delete:
                    Intent intent3 = new Intent(LottoMain2.this, LottoDelete2.class);
                    intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent3);
                    break;
                case R.id.btn_edit:
                    Intent intent4 = new Intent(LottoMain2.this, LottoEdit2.class);
                    intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent4);
                    break;
            }

        }
    };
}