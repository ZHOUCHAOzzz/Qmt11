package com.miracle.qmt.ui.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.contact.PersonBean;
import com.miracle.qmt.contact.PinyinComparator;
import com.miracle.qmt.contact.PinyinUtils;
import com.miracle.qmt.contact.SideBar;
import com.miracle.qmt.contact.SortAdapter;
import com.miracle.qmt.ui.activity.ContractDetailsActivity;
import com.miracle.qmt.ui.contract.ConContract;
import com.miracle.qmt.ui.model.ContractItem;
import com.miracle.qmt.ui.model.TXLBean;
import com.miracle.qmt.ui.presenter.ContractPresenter;
import com.miracle.qmt.util.pinyin.SortModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/7/25.
 * 通讯录
 */
public class ContractFragment extends BaseFragment<ContractPresenter> implements ConContract.View {



    private ListView listView;
    private SortAdapter sortadapter;
    private List<PersonBean> data;
    private SideBar sidebar;
    private TextView dialog;
    private PromptDialog promptDialog;
    private ImageView iv_nodate;

    public static ContractFragment mFragment;
    ArrayList<ContractItem> mList = new ArrayList<>();


    public static ContractFragment newInstance(){
        mFragment = new ContractFragment();
        return  mFragment;
    }
    private List<PersonBean> getData(List<PersonBean> data) {
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
    private void init() {
        // TODO Auto-generated method stub
        sidebar = (SideBar) findViewById(R.id.sidebar);
        listView = (ListView) findViewById(R.id.listview);
        iv_nodate= (ImageView) findViewById(R.id.iv_no_data);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userid= mList.get(position).getUser_id();
                Intent intent=new Intent(getActivity(), ContractDetailsActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);

            }
        });
        dialog = (TextView) findViewById(R.id.dialog);
        sidebar.setTextView(dialog);
        promptDialog=new PromptDialog(getActivity());




    }



    @Override
    public int getLayoutId() {

        return R.layout.fragment_contract;
    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  promptDialog.dismiss();
    }

    @Override
    public void onPause() {
        super.onPause();
      //  promptDialog.dismiss();
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("通讯录");
        init();
     //   showProgressDialog("获取中");
        mPresenter.getPersonData();


    }


    @Override
    public void getContractSucc(ArrayList<SortModel> mList) {

    }

    @Override
    public void getPersonData(final TXLBean response) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userid= data.get(position).getId()+"";
                Intent intent=new Intent(getActivity(), ContractDetailsActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);

            }
        });

        for (int i = 0; i < response.getInfo().size(); i++) {
            ContractItem  item=new ContractItem();
            item.setName(response.getInfo().get(i).getNick_name());
            Log.d("userid",response.getInfo().get(i).getUser_id()+"");
            item.setUser_id(response.getInfo().get(i).getUser_id()+"");
            mList.add(item);

        }




        if (mList.size()==0){
            listView.setVisibility(View.GONE);
            iv_nodate.setVisibility(View.VISIBLE);
         //   Toast.makeText(getActivity(), "您还没有联系人快寻找分销合伙人吧", Toast.LENGTH_SHORT).show();
            return;
        }
        List<PersonBean> lists=new ArrayList<>();
        for (int i = 0; i <response.getInfo().size() ; i++) {
            lists.add(new PersonBean(response.getInfo().get(i).getNick_name(),response.getInfo().get(i).getUser_id()+""));
            Log.d("11111111",lists.size()+"");
        }
      //  initViewData(response);
        data = getData(lists);
        // ?????????adapter?????????
        Collections.sort(data, new PinyinComparator());
        sortadapter = new SortAdapter(getActivity(), data);
        listView.setAdapter(sortadapter);
        if (mList.size()>0){
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




}