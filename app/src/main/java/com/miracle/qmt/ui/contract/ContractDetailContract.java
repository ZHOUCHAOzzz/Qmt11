package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.CollectionBean;
import com.miracle.qmt.ui.model.ConDetailsBean;

/**
 * Created by WJQ on 2016/11/14.
 */
public interface ContractDetailContract {
    public interface View extends BaseView {
        public void getContractSucc(ConDetailsBean bean);
        public void collection(CollectionBean bean);

    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getData(String userid);
        public abstract void collection(String tx_id);

    }
}
