package com.example.apple.ljl.activity;

/**
 * Created by apple on 2017/12/27.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.ljl.R;
import com.example.apple.ljl.dao.FlagDAO;
import com.example.apple.ljl.dao.InaccountDAO;
import com.example.apple.ljl.dao.OutaccountDAO;
import com.example.apple.ljl.model.Tb_flag;
import com.example.apple.ljl.model.Tb_inaccount;
import com.example.apple.ljl.model.Tb_outaccount;

import java.util.List;


public class Showinfo extends Activity {
    public static final String FLAG = "id";
    private Button btnoutinfo, btnininfo, btnflaginfo;
    private ListView lvinfo;
    private String strType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);// 设置布局文件

        lvinfo = (ListView) findViewById(R.id.lvinfo);// 获取布局文件中的ListView组件
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);// 获取布局文件中的支出信息按钮
        btnininfo = (Button) findViewById(R.id.btnininfo);// 获取布局文件中的收入信息按钮
        btnflaginfo = (Button) findViewById(R.id.btnflaginfo);// 获取布局文件中的便签信息按钮

        showInfo(R.id.btnoutinfo);// 默认显示支出信息

        btnoutinfo.setOnClickListener(new OnClickListener() {// 为支出信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                showInfo(R.id.btnoutinfo);// 显示支出信息
            }
        });

        btnininfo.setOnClickListener(new OnClickListener() {// 为收入信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                showInfo(R.id.btnininfo);// 显示收入信息
            }
        });
//        便签信息按钮
        btnflaginfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showInfo(R.id.btnflaginfo);
            }
        });

        lvinfo.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('|'));
                Intent intent = null;
                if (strType == "btnoutinfo" | strType == "btnininfo") {
                    intent = new Intent(Showinfo.this, InfoManage.class);
                    intent.putExtra(FLAG, new String[]{strid, strType});
                } else if (strType == "btnflaginfo") {
//                    intent = new Intent(Showinfo.this, FlagManage.class);
                    intent.putExtra(FLAG, strid);
                }
                startActivity(intent);
            }
        });
    }

    private void showInfo(int intType) {
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        switch (intType) {
            case R.id.btnoutinfo:
                strType = "btnoutinfo";
                OutaccountDAO outaccountinfo = new OutaccountDAO(Showinfo.this);
                List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0, (int) outaccountinfo.getCount());
                strInfos = new String[listoutinfos.size()];
                int i = 0;
                for (Tb_outaccount tb_outaccount : listoutinfos) {
                    strInfos[i] = tb_outaccount.getid() + "|" + tb_outaccount.getType() + " " + String.valueOf(tb_outaccount.getMoney()) + "元     "
                            + tb_outaccount.getTime();
                    i++;
                }
                break;
            case R.id.btnininfo:
                strType = "btnininfo";
                InaccountDAO inaccountinfo = new InaccountDAO(Showinfo.this);
                List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
                strInfos = new String[listinfos.size()];
                int m = 0;
                for (Tb_inaccount tb_inaccount : listinfos) {
                    strInfos[m] = tb_inaccount.getId() + "|" + tb_inaccount.getType() + " " + String.valueOf(tb_inaccount.getMoney()) + "元     "
                            + tb_inaccount.getTime();
                    m++;
                }
                break;
            case R.id.btnflaginfo:
                strType = "btnflaginfo";
                FlagDAO flaginfo = new FlagDAO(Showinfo.this);
                List<Tb_flag> listFlags = flaginfo.getScrollData(0, (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()];
                int n = 0;
                for (Tb_flag tb_flag : listFlags) {
                    strInfos[n] = tb_flag.getid() + "|" + tb_flag.getFlag();
                    if (strInfos[n].length() > 15)
                        strInfos[n] = strInfos[n].substring(0, 15) + "……";
                    n++;
                }
                break;
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();// 实现基类中的方法
        showInfo(R.id.btnoutinfo);// 显示支出信息
    }
}