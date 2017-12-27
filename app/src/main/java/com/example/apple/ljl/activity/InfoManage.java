package com.example.apple.ljl.activity;

/**
 * Created by apple on 2017/12/27.
 */
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.ljl.R;
import com.example.apple.ljl.dao.InaccountDAO;
import com.example.apple.ljl.dao.OutaccountDAO;
import com.example.apple.ljl.model.Tb_inaccount;
import com.example.apple.ljl.model.Tb_outaccount;


public class InfoManage extends Activity {
    private static final int DATE_DIALOG_ID = 0;
    private TextView tvtitle, textView;
    private EditText txtMoney, txtTime, txtHA, txtMark;
    private Spinner spType;
    private Button btnEdit, btnDel;
    private String[] strInfos;
    private String strid, strType;

    private int mYear;
    private int mMonth;
    private int mDay;

    private OutaccountDAO outaccountDAO = new OutaccountDAO(InfoManage.this);
    private InaccountDAO inaccountDAO = new InaccountDAO(InfoManage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomanage);
        tvtitle = (TextView) findViewById(R.id.inouttitle);
        textView = (TextView) findViewById(R.id.tvInOut);
        txtMoney = (EditText) findViewById(R.id.txtInOutMoney);
        txtTime = (EditText) findViewById(R.id.txtInOutTime);
        spType = (Spinner) findViewById(R.id.spInOutType);
        txtHA = (EditText) findViewById(R.id.txtInOut);
        txtMark = (EditText) findViewById(R.id.txtInOutMark);
        btnEdit = (Button) findViewById(R.id.btnInOutEdit);
        btnDel = (Button) findViewById(R.id.btnInOutDelete);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strInfos = bundle.getStringArray(Showinfo.FLAG);
        strid = strInfos[0];
        strType = strInfos[1];
        if (strType.equals("btnoutinfo")) {

            tvtitle.setText("支出管理");
            textView.setText("地  点：");
            Tb_outaccount tb_outaccount = outaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
            txtTime.setText(tb_outaccount.getTime());
            spType.setPrompt(tb_outaccount.getType());
            txtHA.setText(tb_outaccount.getAddress());
            txtMark.setText(tb_outaccount.getMark());
        } else if (strType.equals("btnininfo")) {

            tvtitle.setText("收入管理");
            textView.setText("付款方：");
            Tb_inaccount tb_inaccount = inaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));
            txtTime.setText(tb_inaccount.getTime());
            spType.setPrompt(tb_inaccount.getType());
            txtHA.setText(tb_inaccount.getHandler());
            txtMark.setText(tb_inaccount.getMark());
        }
//      修改按钮
        btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (strType.equals("btnoutinfo")) {

                    Tb_outaccount tb_outaccount = new Tb_outaccount();
                    tb_outaccount.setid(Integer.parseInt(strid));
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
                    tb_outaccount.setTime(txtTime.getText().toString());
                    tb_outaccount.setType(spType.getSelectedItem().toString());
                    tb_outaccount.setAddress(txtHA.getText().toString());
                    tb_outaccount.setMark(txtMark.getText().toString());
                    outaccountDAO.update(tb_outaccount);
                } else if (strType.equals("btnininfo")) {

                    Tb_inaccount tb_inaccount = new Tb_inaccount();
                    tb_inaccount.setId(Integer.parseInt(strid));
                    tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
                    tb_inaccount.setTime(txtTime.getText().toString());
                    tb_inaccount.setType(spType.getSelectedItem().toString());
                    tb_inaccount.setHandler(txtHA.getText().toString());
                    tb_inaccount.setMark(txtMark.getText().toString());
                    inaccountDAO.update(tb_inaccount);
                }

                Toast.makeText(InfoManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT).show();
            }
        });
//        删除按钮
        btnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (strType.equals("btnoutinfo")) {

                    outaccountDAO.detele(Integer.parseInt(strid));
                } else if (strType.equals("btnininfo")) {

                    inaccountDAO.detele(Integer.parseInt(strid));
                }
                Toast.makeText(InfoManage.this, "〖数据〗删除成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}