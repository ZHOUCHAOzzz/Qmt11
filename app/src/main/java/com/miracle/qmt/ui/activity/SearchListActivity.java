package com.miracle.qmt.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.contact.PersonBean;
import com.miracle.qmt.contact.PinyinComparator;
import com.miracle.qmt.contact.PinyinUtils;
import com.miracle.qmt.contact.SideBar;
import com.miracle.qmt.contact.SortAdapter;
import com.miracle.qmt.ui.contract.SearchContract;
import com.miracle.qmt.ui.model.ContractItem;
import com.miracle.qmt.ui.model.SearchBean;
import com.miracle.qmt.ui.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/1.
 */

public class SearchListActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {
    @Bind(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    ArrayList<ContractItem> mList = new ArrayList<>();
    @Bind(R.id.iv_no_data)
    ImageView ivNoData;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.sidebar)
    SideBar sidebar;
    private ListView listView;
    private SortAdapter sortadapter;
    private List<PersonBean> data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_serach;
    }

    @Override
    public void initView() {
        super.initView();
        listView = (ListView) findViewById(R.id.listview);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getData(etSearch.getText().toString().trim());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userid= data.get(position).getId();
                Log.d("userid111",userid);
                Intent intent=new Intent(SearchListActivity.this, ContractDetailsActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getData(SearchBean response) {

        for (int i = 0; i < response.getInfo().size(); i++) {
            ContractItem item = new ContractItem();
            item.setName(response.getInfo().get(i).getNick_name());
            item.setUser_id(response.getInfo().get(i).getUser_id() + "");
            mList.add(item);


        }


        if (mList.size() == 0) {
            listView.setVisibility(View.GONE);
            /*iv_nodate.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "您还没有联系人快寻找分销合伙人吧", Toast.LENGTH_SHORT).show();*/
            return;
        }
        List<PersonBean> lists = new ArrayList<>();
        for (int i = 0; i < response.getInfo().size(); i++) {

            lists.add(new PersonBean(response.getInfo().get(i).getNick_name(),response.getInfo().get(i).getUser_id()+""));
            Log.d("11111111", lists.size() + "");


        }
        data = getDataD(lists);
        sortadapter = new SortAdapter(SearchListActivity.this, data);
        listView.setAdapter(sortadapter);
        //  initViewData(response);
        // ?????????adapter?????????
        Collections.sort(data, new PinyinComparator());
        sortadapter = new SortAdapter(SearchListActivity.this, data);
        listView.setAdapter(sortadapter);
        if (mList.size() > 0) {
            sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

                @Override
                public void onTouchingLetterChanged(String s) {
                    // TODO Auto-generated method stub
                    // ???????��????��??
                    int position = sortadapter.getPositionForSelection(s.charAt(0));

                    if (position != -1) {
                        listView.setSelection(position);
                    }
                }
            });

        }


    }

    private List<PersonBean> getDataD(List<PersonBean> data) {
        List<PersonBean> listarray = new ArrayList<PersonBean>();
        for (int i = 0; i < data.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(data.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            PersonBean person = new PersonBean();
            person.setName(data.get(i).getName());
            person.setPinYin(pinyin);
            person.setId(data.get(i).getId());
            // ??????????��?????????????????
            if (Fpinyin.matches("[A-Z]")) {
                person.setFirstPinYin(Fpinyin);
            } else {
                person.setFirstPinYin("#");
            }

            listarray.add(person);
        }
        return listarray;

    }
}
