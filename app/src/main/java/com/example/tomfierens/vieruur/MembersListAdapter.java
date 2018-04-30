package com.example.tomfierens.vieruur;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tomfierens.vieruur.data.MembersListContract;

import static java.lang.Integer.valueOf;

/**
 * Created by Tom Fierens on 28/03/2018.
 */

public class MembersListAdapter extends RecyclerView.Adapter<MembersListAdapter.MembersListViewHolder>{
    private Cursor mCursor;
    private Context mContext;
    private String memberGroup;

    public MembersListAdapter(Context context, Cursor cursor){
        this.mContext=context;
        this.mCursor=cursor;
    }
    @Override
    public MembersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.members_list_item, parent, false);
        return new MembersListViewHolder(view);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(MembersListViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String memberName = null;
        memberName = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry.COLUMN_MEMBER_NAME));
        memberGroup = mCursor.getString(mCursor.getColumnIndex(MembersListContract.MembersListEntry.COLUMN_GROUP));
        int memberConsumptions = mCursor.getInt(mCursor.getColumnIndex(MembersListContract.MembersListEntry.COLUMN_CONSUMPTIONS));

        long id = mCursor.getLong(mCursor.getColumnIndex(MembersListContract.MembersListEntry._ID));

        holder.nameTextView.setText(memberName);
        holder.consumptionsTextView.setText(String.valueOf(memberConsumptions));
        holder.itemView.setTag(id);

        if(memberConsumptions <= 6 && memberConsumptions > 0){
            holder.consumptionsTextView.getBackground().setTint(Color.rgb(234,114,9));
        }
        else if(memberConsumptions <= 0){
            holder.consumptionsTextView.getBackground().setTint(Color.rgb(234, 9, 9));
        }
        else{
            holder.consumptionsTextView.getBackground().setTint(Color.rgb(106,163,27));
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
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    class MembersListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Toast mToast;

        // Will display the gamesList name
        TextView nameTextView;
        TextView consumptionsTextView;

        public MembersListViewHolder(View itemView) {
            super(itemView);

            consumptionsTextView = (TextView) itemView.findViewById(R.id.tv_circle);

            nameTextView = (TextView) itemView.findViewById(R.id.tv_memberName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(mContext, ActionActivity.class);
            intent.putExtra("MemberName", nameTextView.getText());
            intent.putExtra("GroupName", memberGroup);
            mContext.startActivity(intent);
        }

    }
}
