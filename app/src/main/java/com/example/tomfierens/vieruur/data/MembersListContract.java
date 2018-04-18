package com.example.tomfierens.vieruur.data;

import android.provider.BaseColumns;

/**
 * Created by Tom Fierens on 4/04/2018.
 */

public class MembersListContract {
    public static final class MembersListEntry implements BaseColumns{
       public static final String TABLE_NAME = "membersList";
       public static final String COLUMN_MEMBER_NAME = "memberName";
       public static final String COLUMN_CONSUMPTIONS = "consumptions";
       public static final String COLUMN_GROUP = "memberGroup";
    }
}
