package com.example.tomfierens.vieruur;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import static java.lang.Integer.valueOf;

/**
 * Created by Tom Fierens on 28/03/2018.
 */

public class LeadersListAdapter extends RecyclerView.Adapter<LeadersListAdapter.LeadersListViewHolder>{
    private Cursor mCursor;
    private Context mContext;

    public LeadersListAdapter(Context context, Cursor cursor){
        this.mContext=context;
        this.mCursor=cursor;
    }
    @Override
    public LeadersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.leaders_list_item, parent, false);
        return new LeadersListViewHolder(view);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(LeadersListViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        String leaderName = null;
        leaderName = mCursor.getString(mCursor.getColumnIndex("leaderName"));
        String outstandingAmount = null;
        outstandingAmount = mCursor.getString(mCursor.getColumnIndex("outstandingAmount"));
        holder.nameTextView.setText(leaderName);
        holder.amountTextView.setText("€" + outstandingAmount);
        holder.itemView.setTag(mCursor.getString(mCursor.getColumnIndex("id")));
        int amount = valueOf(outstandingAmount);
        if(amount <= 50 && amount > 20){
            holder.amountTextView.setTextColor(Color.rgb(234,114,9));
        }
        else if(amount > 50){
            holder.amountTextView.setTextColor(Color.rgb(234, 9, 9));
        }
        else{
            holder.amountTextView.setTextColor(Color.rgb(106,163,27));
        }
    }

    @Override
    public int getItemCount() {
        if(mCursor == null){
            return 0;
        }
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    class LeadersListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Toast mToast;

        TextView nameTextView;
        TextView amountTextView;

        public LeadersListViewHolder(View itemView) {
            super(itemView);

            amountTextView = (TextView) itemView.findViewById(R.id.tv_amount);

            nameTextView = (TextView) itemView.findViewById(R.id.tv_leaderName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            if(mToast != null){
                mToast.cancel();
            }

            String toastMessage = amountTextView.getText() + "";
            mToast = Toast.makeText(mContext, toastMessage, Toast.LENGTH_LONG);
            mToast.show();
        }

    }
}