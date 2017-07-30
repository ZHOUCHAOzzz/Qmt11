package com.miracle.qmt.ui.presenter;


import android.os.AsyncTask;

import com.miracle.qmt.ui.contract.ConContract;
import com.miracle.qmt.util.pinyin.CharacterParser;
import com.miracle.qmt.util.pinyin.ConstractUtil;
import com.miracle.qmt.util.pinyin.PinyinComparator;
import com.miracle.qmt.util.pinyin.SortModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ContractPresenter extends ConContract.Presenter {
    PinyinComparator pinyinComparator;
    CharacterParser characterParser;
    @Override
    public void onStart() {

    }

    @Override
    public void getData() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        new ConstactAsyncTask().execute(0);
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
                Collections.sort(SourceDateList, pinyinComparator);
                mView.getContractSucc(SourceDateList);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
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
