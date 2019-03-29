package com.geo.amazondynamodb.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geo.amazondynamodb.R;
import com.geo.amazondynamodb.app.DbConstant;
import com.geo.amazondynamodb.contracts.adapter.UserListAdapterConstract;
import com.geo.amazondynamodb.models.UserDetail;
import com.geo.amazondynamodb.presenters.UserListAdapterPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private UserListAdapterPresenter mPresenter;
    public UserListAdapter(List<UserDetail>  aUserDetail) {
        mPresenter=new UserListAdapterPresenter(aUserDetail);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemWorkOutLevel = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user_details, viewGroup, false);
        return new ViewHolder(itemWorkOutLevel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        mPresenter.attach(viewHolder);
        mPresenter.bindView(position);
        mPresenter.detach();
    }

    @Override
    public int getItemCount() {
        return mPresenter.getUserListSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements UserListAdapterConstract.View {

        @BindView(R.id.tv_time)
        TextView tvTime;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_review)
        TextView tvReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void setTitle(String title) {
            tvTitle.setText(title);
        }

        @Override
        public void setTime(String time) {
            tvTime.setText(time);
        }

        @Override
        public void isReviewed(Boolean isReviewed) {
            if (isReviewed){
                tvReview.setText(DbConstant.REVIEWED);
            }else {
                tvReview.setText(DbConstant.NOT_REVIEWED);
            }
        }
    }
}
