package com.geo.amazondynamodb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.geo.amazondynamodb.R;
import com.geo.amazondynamodb.adapters.UserListAdapter;
import com.geo.amazondynamodb.app.DbConstant;
import com.geo.amazondynamodb.contracts.activities.MainActivityContract;
import com.geo.amazondynamodb.models.UserDetail;
import com.geo.amazondynamodb.presenters.MainActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;

    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();
        mPresenter.fetchtabledetals();

    }

    private void initPresenter() {
        mPresenter = new MainActivityPresenter(getAwsClient());
        mPresenter.attach(this);
    }

    private AmazonDynamoDBClient getAwsClient() {
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                DbConstant.POOL_ID, // Identity pool ID
                Regions.AP_SOUTH_1 // Region
        );
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentialsProvider);
        client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        return client;
    }

    @Override
    public void settableDetails(List<UserDetail> userDetails) {
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        UserListAdapter modulesAdapter = new UserListAdapter(userDetails);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(modulesAdapter);

    }
}
