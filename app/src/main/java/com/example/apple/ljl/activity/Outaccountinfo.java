package com.example.apple.ljl.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.ljl.R;
import com.example.apple.ljl.dao.OutaccountDAO;
import com.example.apple.ljl.model.Tb_outaccount;

import java.util.List;

public class Outaccountinfo extends Activity {
    public static final String FLAG = "id";// ����һ��������������Ϊ������
    ListView lvinfo;// ����ListView����
    String strType = "";// �����ַ���¼��������

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outaccountinfo);// ���ò����ļ�
        lvinfo = (ListView) findViewById(R.id.lvoutaccountinfo);// ��ȡ�����ļ��е�ListView���

        ShowInfo(R.id.btnoutinfo);// �����Զ��巽����ʾ֧����Ϣ

        lvinfo.setOnItemClickListener(new OnItemClickListener()// ΪListView�������¼�
        {
            // ��дonItemClick����
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());// ��¼֧����Ϣ
                String strid = strInfo.substring(0, strInfo.indexOf('|'));// ��֧����Ϣ�н�ȡ֧�����
                Intent intent = new Intent(Outaccountinfo.this, InfoManage.class);// ����Intent����
                intent.putExtra(FLAG, new String[] { strid, strType });// ���ô������
                startActivity(intent);// ִ��Intent����
            }
        });
    }

    private void ShowInfo(int intType) {// ������ݴ���Ĺ������ͣ���ʾ��Ӧ����Ϣ
        String[] strInfos = null;// �����ַ����飬�����洢֧����Ϣ
        ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
        strType = "btnoutinfo";// ΪstrType������ֵ
        OutaccountDAO outaccountinfo = new OutaccountDAO(Outaccountinfo.this);// ����OutaccountDAO����
        // ��ȡ����֧����Ϣ�����洢��List���ͼ�����
        List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0, (int) outaccountinfo.getCount());
        strInfos = new String[listoutinfos.size()];// �����ַ�����ĳ���
        int i = 0;// ����һ����ʼ��ʶ
        for (Tb_outaccount tb_outaccount : listoutinfos) {// ����List���ͼ���
            // ��֧�������Ϣ��ϳ�һ���ַ��洢���ַ��������Ӧλ��
            strInfos[i] = tb_outaccount.getid() + "|" + tb_outaccount.getType() + " " + String.valueOf(tb_outaccount.getMoney()) + "Ԫ     "
                    + tb_outaccount.getTime();
            i++;// ��ʶ��1
        }
        // ʹ���ַ������ʼ��ArrayAdapter����
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);// ΪListView�б��������Դ
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();// ʵ�ֻ����еķ���
        ShowInfo(R.id.btnoutinfo);// ��ʾ������Ϣ
    }
}