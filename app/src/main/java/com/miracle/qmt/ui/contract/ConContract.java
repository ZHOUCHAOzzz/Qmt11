package com.miracle.qmt.ui.contract;


import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.TXLBean;
import com.miracle.qmt.util.pinyin.SortModel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/22.
 */
public interface ConContract {
    public interface View extends BaseView {
        public void getContractSucc(ArrayList<SortModel> mList);
       public  void getPersonData(TXLBean s);
    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getData();
        public abstract void getPersonData();
    }

}
