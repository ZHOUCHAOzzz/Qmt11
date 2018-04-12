package com.miracle.qmt.ui.presenter;


import android.os.AsyncTask;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.ConContract;
import com.miracle.qmt.ui.model.TXLBean;
import com.miracle.qmt.util.pinyin.CharacterParser;
import com.miracle.qmt.util.pinyin.ConstractUtil;
import com.miracle.qmt.util.pinyin.PinyinComparator;
import com.miracle.qmt.util.pinyin.SortModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ContractPresenter extends ConContract.Presenter {
    PinyinComparator pinyinComparator;
    CharacterParser characterParser;
    @Override
    public void onStart() {
        getPersonData();
    }
    @Override
    public void getData() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        new ConstactAsyncTask().execute(0);
    }


    //获取联系人
    @Override
    public void getPersonData() {
      /* String userid= AppController.getInstance().getUser().getUser_id()+"";
        Log.d("userid",userid);
        mSubscription = ApiFactory.getXynSingleton().contract(userid,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<ArrayList<ContractItem>>(mView) {
                    @Override
                    public void onSuccess(ArrayList<ContractItem> response) {
                        super.onSuccess(response);
                        mView.getPersonData(response);

                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);


                    }
                });*/

        mSubscription = ApiFactory.getXynSingleton().tongxunlu("1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<TXLBean>(mView) {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TXLBean txlBean) {
                        mView.getPersonData(txlBean);

                    }
                });



    }

    private class ConstactAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        Map<String,String> callRecords;
        ArrayList<SortModel> SourceDateList;
        @Override
        protected Integer doInBackground(Integer... arg0) {
            int result = -1;
            callRecords = ConstractUtil.getAllCallRecords(context);
            result = 1;
            return result;
        }
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 1) {
                List<String> constact = new ArrayList<String>();
                for (Iterator<String> keys = callRecords.keySet().iterator(); keys
                        .hasNext();) {
                    String key = keys.next();
                    constact.add(key);
                }
                String[] names = new String[] {};
                names = constact.toArray(names);
                SourceDateList = filledData(names);

                // 根据a-z进行排序源数据
//                Collections.sort(SourceDateList, pinyinComparator);
                mView.getContractSucc(SourceDateList);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }



    private ArrayList<SortModel> filledData(String[] date) {
        ArrayList<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }
}
