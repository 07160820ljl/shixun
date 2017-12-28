package com.example.apple.ljl.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apple.ljl.R;
import com.example.apple.ljl.dao.OutaccountDAO;
import com.example.apple.ljl.model.Tb_outaccount;


public class AddOutaccount extends Activity {
    protected static final int DATE_DIALOG_ID = 0;// �������ڶԻ�����
    EditText txtMoney, txtTime, txtAddress, txtMark;// ����4��EditText����
    Spinner spType;// ����Spinner����
    Button btnSaveButton;// ����Button���󡰱��桱
    Button btnCancelButton;// ����Button����ȡ��

    private int mYear;// ��
    private int mMonth;// ��
    private int mDay;// ��

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addoutaccount);// ���ò����ļ�
        txtMoney = (EditText) findViewById(R.id.txtMoney);// ��ȡ����ı���
        txtTime = (EditText) findViewById(R.id.txtTime);// ��ȡʱ���ı���
        txtAddress = (EditText) findViewById(R.id.txtAddress);// ��ȡ�ص��ı���
        txtMark = (EditText) findViewById(R.id.txtMark);// ��ȡ��ע�ı���
        spType = (Spinner) findViewById(R.id.spType);// ��ȡ��������б�
        btnSaveButton = (Button) findViewById(R.id.btnSave);// ��ȡ���水ť
        btnCancelButton = (Button) findViewById(R.id.btnCancel);// ��ȡȡ��ť

        txtTime.setOnClickListener(new OnClickListener() {// Ϊʱ���ı������õ��������¼�

            @Override
            public void onClick(View arg0) {
                showDialog(DATE_DIALOG_ID);// ��ʾ����ѡ��Ի���
            }
        });

        btnSaveButton.setOnClickListener(new OnClickListener() {// Ϊ���水ť���ü����¼�

                    @Override
                    public void onClick(View arg0) {
                        String strMoney = txtMoney.getText().toString();// ��ȡ����ı����ֵ
                        if (!strMoney.isEmpty()) {// �жϽ�Ϊ��
                            // ����OutaccountDAO����
                            OutaccountDAO outaccountDAO = new OutaccountDAO(AddOutaccount.this);
                            // ����Tb_outaccount����
                            Tb_outaccount tb_outaccount = new Tb_outaccount(outaccountDAO.getMaxId() + 1, Double.parseDouble(strMoney), txtTime.getText()
                                    .toString(), spType.getSelectedItem().toString(), txtAddress.getText().toString(), txtMark.getText().toString());
                            outaccountDAO.add(tb_outaccount);// ���֧����Ϣ
                            // ������Ϣ��ʾ
                            Toast.makeText(AddOutaccount.this, "������֧���������ӳɹ���", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddOutaccount.this, "������֧����", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btnCancelButton.setOnClickListener(new OnClickListener() {// Ϊȡ��ť���ü����¼�

                    @Override
                    public void onClick(View arg0) {
                        txtMoney.setText("");// ���ý���ı���Ϊ��
                        txtMoney.setHint("0.00");// Ϊ����ı���������ʾ
                        txtTime.setText("");// ����ʱ���ı���Ϊ��
                        txtTime.setHint("2011-01-01");// Ϊʱ���ı���������ʾ
                        txtAddress.setText("");// ���õص��ı���Ϊ��
                        txtMark.setText("");// ���ñ�ע�ı���Ϊ��
                        spType.setSelection(0);// ������������б�Ĭ��ѡ���һ��
                    }
                });

        final Calendar c = Calendar.getInstance();// ��ȡ��ǰϵͳ����
        mYear = c.get(Calendar.YEAR);// ��ȡ���
        mMonth = c.get(Calendar.MONTH);// ��ȡ�·�
        mDay = c.get(Calendar.DAY_OF_MONTH);// ��ȡ����

        updateDisplay();// ��ʾ��ǰϵͳʱ��
    }

    @Override
    protected Dialog onCreateDialog(int id) {// ��дonCreateDialog����

        switch (id) {
        case DATE_DIALOG_ID:// ��������ѡ��Ի���
            return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;// Ϊ��ݸ�ֵ
            mMonth = monthOfYear;// Ϊ�·ݸ�ֵ
            mDay = dayOfMonth;// Ϊ�츳ֵ
            updateDisplay();// ��ʾ���õ�����
        }
    };

    private void updateDisplay() {
        // ��ʾ���õ�ʱ��
        txtTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay));
    }
}
