package com.capstone.opsr.authentication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.opsr.R;
import com.capstone.opsr.entity.Config;
import com.capstone.opsr.handler.DatabaseConfigHandler;
import com.capstone.opsr.home.MainActivity;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin ;
    TextView textRegister ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Config config = getConfig();
        changeLanguage(new Locale(config.getLanguage(),config.getCountry()));
        setContentView(R.layout.activity_login);
    }

    public void changeToVietnamese(View view){
        if(!getConfig().getLanguage().equals("vi")){
            changeLanguage(new Locale("vi", "VN"));
            updateLangugeToDb("vi", "VN");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void changeToEnglish(View view){
        if(getConfig().getLanguage().equals("vi")){
            changeLanguage(new Locale("en", "US"));
            updateLangugeToDb("en","US");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void changeLanguage(Locale locale){
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration, displayMetrics);
    }

    // Update language to db
    private DatabaseConfigHandler databaseConfigHandler;
    public void updateLangugeToDb(String language, String country){
        if(databaseConfigHandler == null){
            databaseConfigHandler = new DatabaseConfigHandler(this, "configdb", 1);
        }
        SQLiteDatabase db = databaseConfigHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("language", language.toLowerCase());
        values.put("country", country.toUpperCase());
        db.update("config",values,"id = ?", new String[]{"1"});
    }

    public Config getConfig(){
        Config config = null;
        if(databaseConfigHandler == null){
            databaseConfigHandler = new DatabaseConfigHandler(this, "configdb", 1);
        }
        SQLiteDatabase db = databaseConfigHandler.getReadableDatabase();
        Cursor cursor = db.query("config",
                new String[]{"id","language","country"}, null,null,null,null,null);
        while (cursor.moveToNext()){
            config = new Config(cursor.getString(cursor.getColumnIndex("language")),
                    cursor.getString(cursor.getColumnIndex("country")));
        }
        return config;
    }
}
