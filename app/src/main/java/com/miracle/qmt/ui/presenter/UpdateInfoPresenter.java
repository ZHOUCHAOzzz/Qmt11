package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.ui.contract.UpdateInfoContract;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.UserManager;

/**
 * Created by WJQ on 2016/11/14.
 */

public class UpdateInfoPresenter extends UpdateInfoContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void updateInfo(UserManager.User user) {
        String userId = PreferencesUtils.getPreferences(context, PreferencesUtils.USER_ID);
//        MultipartBody.Part user_id = MultipartBody.Part.createFormData("user_id", userId);
//        MultipartBody.Part nick_name  = MultipartBody.Part.createFormData("nick_name", (TextUtils.isEmpty(user.getNick_name())?"":user.getNick_name()));
//        MultipartBody.Part address  = MultipartBody.Part.createFormData("address", (TextUtils.isEmpty(user.getAddress())?"":user.getAddress()));
//        MultipartBody.Part management  = MultipartBody.Part.createFormData("management", (TextUtils.isEmpty(user.getManagement())?"":user.getManagement()));
//        MultipartBody.Part buy  = MultipartBody.Part.createFormData("buy", (TextUtils.isEmpty(user.getBuy())?"":user.getBuy()));
//        MultipartBody.Part company_name  = MultipartBody.Part.createFormData("company_name", (TextUtils.isEmpty(user.getCompany_name())?"":user.getCompany_name()));
//        MultipartBody.Part name  = MultipartBody.Part.createFormData("name", (TextUtils.isEmpty(user.getName())?"":user.getName()));
//        MultipartBody.Part phone  = MultipartBody.Part.createFormData("phone", (TextUtils.isEmpty(user.getPhone())?"":user.getPhone()));
//
//        Map<String, RequestBody> head_pics = new HashMap<>();
//        if(!TextUtils.isEmpty(user.getHead_pic())){
//            File file = new File(user.getHead_pic());
//            head_pics.put("head_pic[]\";filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
//        }
//
//        Map<String, RequestBody> imgs = new HashMap<>();
//
//if(user.getPic() != null)
//        for (int i = 0; i < user.getPic().size(); i++) {
//            File file = new File(user.getPic().get(i));
//            imgs.put("pic[]\";filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
//        }
//        @Part MultipartBody.Part user_id,
//        @Part MultipartBody.Part nick_name,
//        @Part MultipartBody.Part address,
//        @Part MultipartBody.Part management,
//        @Part MultipartBody.Part buy,
//        @Part MultipartBody.Part company_name,
//        @Part MultipartBody.Part name,
//        @Part MultipartBody.Part head_pic,
//        @PartMap Map<String, RequestBody> params
       /* mSubscription = ApiFactory.getXynSingleton().gerengai(userId,user.getNick_name(),user.getAddress(),user.getManagement(),user.getBuy()
                ,user.getCompany_name(),user.getName(),user.getPhone(),user.getHead_pic(),user.getPics())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<Object>(mView) {
                    @Override
                    public void onSuccess(Object response) {
                        super.onSuccess(response);
                        mView.updateSucc();
                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);
                        mView.showMsg(info);
                    }
                });
        addSubscription(mSubscription);*/
    }
}
