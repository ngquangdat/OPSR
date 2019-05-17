package com.capstone.opsr.introslider;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    private int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "config";

    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTime";

    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME , PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch( boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH , isFirstTime);
        editor.commit();
    }

    public boolean IsFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH , true);
    }
}
