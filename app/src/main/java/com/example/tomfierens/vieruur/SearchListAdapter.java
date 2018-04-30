package com.example.tomfierens.vieruur;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>{
    private Cursor mCursor;
    private Context mContext;

    public SearchListAdapter(Context context, Cursor cursor){
        this.mContext=context;
        this.mCursor=cursor;
    }
    @Override
    public SearchListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.search_list_item, parent, false);
        return new SearchListAdapter.SearchListViewHolder(view);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(SearchListAdapter.SearchListViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        String name = null;
        name = mCursor.getString(mCursor.getColumnIndex("name"));
        String price = null;
        price = mCursor.getString(mCursor.getColumnIndex("price"));
        holder.drinkTextView.setText(name);
        holder.priceTextView.setText("€" + price + "/L");
        holder.itemView.setTag(mCursor.getString(mCursor.getColumnIndex("name")));

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

    class SearchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Toast mToast;

        TextView drinkTextView;
        TextView priceTextView;

        public SearchListViewHolder(View itemView) {
            super(itemView);

            priceTextView = (TextView) itemView.findViewById(R.id.tv_searchAmount);

            drinkTextView = (TextView) itemView.findViewById(R.id.tv_searchName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            if(mToast != null){
                mToast.cancel();
            }

            String toastMessage = priceTextView.getText() + "";
            mToast = Toast.makeText(mContext, toastMessage, Toast.LENGTH_LONG);
            mToast.show();
        }

    }
}