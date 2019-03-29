package com.geo.amazondynamodb.contracts.adapter;

import com.geo.amazondynamodb.contracts.BasePresenter;

/**
 * Created by george
 * on 28/03/19.
 */
public interface UserListAdapterConstract {

    interface View {
        void setTitle(String name);
        void setTime(String time);
        void isReviewed(Boolean isReviewed);
    }

    interface Presenter extends BasePresenter<View> {
        void bindView(int position);

        int getUserListSize();
    }
}
