package com.geo.amazondynamodb.presenters;

import android.os.AsyncTask;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.geo.amazondynamodb.app.DbConstant;
import com.geo.amazondynamodb.contracts.activities.MainActivityContract;
import com.geo.amazondynamodb.models.UserDetail;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by george
 * on 27/03/19.
 */
public class MainActivityPresenter implements MainActivityContract.Presenter {

    private final AmazonDynamoDBClient mDynamoDbClient;
    private MainActivityContract.View mView;

    public MainActivityPresenter(AmazonDynamoDBClient dynamoDbClient) {
        mDynamoDbClient = dynamoDbClient;
    }

    @Override
    public void attach(MainActivityContract.View view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView= null;
    }

    @Override
    public void fetchtabledetals() {
        ScanDynamoDB scanDynamoDb= new ScanDynamoDB(mView);
        scanDynamoDb.execute(mDynamoDbClient);

    }

}

class ScanDynamoDB extends AsyncTask<AmazonDynamoDBClient, Void, List<UserDetail>> {
    private List<UserDetail> userDetails = new LinkedList<>();
    private MainActivityContract.View mView;

    public ScanDynamoDB(MainActivityContract.View view) {
        this.mView = view;
    }

    @Override
    protected List<UserDetail> doInBackground(AmazonDynamoDBClient... amazonDynamoDBClients) {
        ScanRequest scanRequest = new ScanRequest();
        scanRequest.setLimit(DbConstant.LIMIT);
        scanRequest.setTableName(DbConstant.TABLE_NAME);
        ScanResult result = amazonDynamoDBClients[DbConstant.ZERO].scan(scanRequest);
        for (Map<String, AttributeValue> item : result.getItems()){
            UserDetail user = new UserDetail();
            user.setmTitle(item.get(DbConstant.TITLE).getS());
            user.setmTimeStamp(Long.valueOf(item.get(DbConstant.TIMESTAMP).getN()));
            user.setmIsReviewed(Boolean.valueOf(item.get(DbConstant.IS_REVIEWED).getN()));
            userDetails.add(user);
        }
        return userDetails;
    }

    @Override
    protected void onPostExecute(List<UserDetail> userDetails) {
        super.onPostExecute(userDetails);
        mView.settableDetails(userDetails);
    }
}
