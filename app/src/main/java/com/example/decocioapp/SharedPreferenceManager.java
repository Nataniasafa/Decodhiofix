package com.example.decocioapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    public static final String SP_STUDENT =  "spStudent";
    public static final String SP_EMAIL = "emailStudent";
    public static final String SP_SIGNED = "signed";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    public SharedPreferenceManager(Context context){
        sharedPreferences = context.getSharedPreferences(SP_STUDENT, context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }
    public void saveBoolean(String keySp, Boolean valueSp) {
        spEditor.putBoolean(keySp, valueSp);
        spEditor.commit();
    }
        public boolean getSigned () {
            return sharedPreferences.getBoolean(SP_SIGNED, false);
        }

        }

