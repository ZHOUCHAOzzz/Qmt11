package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;

import java.util.List;

/**
 * Created by WJQ on 2016/11/14.
 */
public interface UploadPicturesContract {
    public interface View extends BaseView {
        public void getInfoImgsSucc(String s);



    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getInfoImgs(List<String> imgList);

    }
}
