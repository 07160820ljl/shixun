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
    private static final String FLAG = "id";
    private ListView lvinfo;
    private String strType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outaccountinfo);
        lvinfo = (ListView) findViewById(R.id.lvoutaccountinfo);

        ShowInfo(R.id.btnoutinfo);

        lvinfo.setOnItemClickListener(new OnItemClickListener()
        {
            // 覆写onItemClick方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('|'));
                Intent intent = new Intent(Outaccountinfo.this, InfoManage.class);
                intent.putExtra(FLAG, new String[] { strid, strType });
                startActivity(intent);
            }
        });
    }

    private void ShowInfo(int intType) {
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        strType = "btnoutinfo";
        OutaccountDAO outaccountinfo = new OutaccountDAO(Outaccountinfo.this);
        List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0, (int) outaccountinfo.getCount());
        strInfos = new String[listoutinfos.size()];
        int i = 0;
        for (Tb_outaccount tb_outaccount : listoutinfos) {
            strInfos[i] = tb_outaccount.getid() + "|" + tb_outaccount.getType() + " " + String.valueOf(tb_outaccount.getMoney()) + "元     "
                    + tb_outaccount.getTime();
            i++;
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        ShowInfo(R.id.btnoutinfo);
    }
}