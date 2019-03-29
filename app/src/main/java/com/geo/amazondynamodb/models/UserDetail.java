package com.geo.amazondynamodb.models;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.Date;

/**
 * Created by george
 * on 27/03/19.
 */

public class UserDetail {

    private String mUserId;
    private String mTimeStamp;
    private String mTitle;
    private Boolean mIsReviewed;

    public UserDetail( ) {
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTimeStamp() {
        return mTimeStamp;
    }

    public void setmTimeStamp(Long mTimeStamp) {
        Date currentDate = new Date(mTimeStamp);
        currentDate.toString();
        this.mTimeStamp = currentDate.toGMTString();
    }

    public String getmTitle() {
        return mTitle;
    }

    public Boolean getmIsReviewed() {
        return mIsReviewed;
    }

    public void setmIsReviewed(Boolean mIsReviewed) {
        this.mIsReviewed = mIsReviewed;
    }
}
