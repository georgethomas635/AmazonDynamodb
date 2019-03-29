package com.geo.amazondynamodb.contracts.activities;

import com.geo.amazondynamodb.contracts.BasePresenter;
import com.geo.amazondynamodb.models.UserDetail;

import java.util.List;

/**
 * Created by george
 * on 27/03/19.
 */
public interface MainActivityContract {

    interface View  {
        void settableDetails(List<UserDetail> userDetails);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchtabledetals();
    }
}
