package com.geo.amazondynamodb.presenters;

import com.geo.amazondynamodb.adapters.UserListAdapter;
import com.geo.amazondynamodb.contracts.adapter.UserListAdapterConstract;
import com.geo.amazondynamodb.models.UserDetail;

import java.util.List;

/**
 * Created by george
 * on 28/03/19.
 */
public class UserListAdapterPresenter implements UserListAdapterConstract.Presenter {

    private UserListAdapterConstract.View mView;
    private List<UserDetail> mUserDetail;

    public UserListAdapterPresenter(List<UserDetail>  aUserDetail) {
        mUserDetail=aUserDetail;
    }


    @Override
    public void attach(UserListAdapterConstract.View view) {
        mView=view;
    }

    @Override
    public void detach() {
        mView=null;

    }

    @Override
    public void bindView(int position) {
        mView.setTime(mUserDetail.get(position).getmTimeStamp());
        mView.setTitle(mUserDetail.get(position).getmTitle());
        mView.isReviewed(mUserDetail.get(position).getmIsReviewed());

    }

    @Override
    public int getUserListSize() {
        return mUserDetail.size();
    }
}
