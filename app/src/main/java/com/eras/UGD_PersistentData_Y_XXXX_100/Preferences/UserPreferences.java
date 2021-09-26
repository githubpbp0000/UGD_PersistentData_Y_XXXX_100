package com.eras.UGD_PersistentData_Y_XXXX_100.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.eras.UGD_PersistentData_Y_XXXX_100.Model.User;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public UserPreferences(Context context) {
        this.context = context;
        /* penamaan bebas namun disini digunakan "userPreferences" */
        sharedPreferences = context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String username, String password){
        /* Menyimpan data login ke sharedPreferences dengan key dan value  */
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);

        /* Jangan lupa commit karena kalo hanya set editonya saja tidak commit akan sia-sia */
        editor.commit();
    }

    public User getUserLogin(){
        /* Mengembalikan object User untuk menampilkan data user jika user sudah login */
        String username,password;

        username = sharedPreferences.getString(KEY_USERNAME,null);
        password = sharedPreferences.getString(KEY_PASSWORD,null);

        return new User(username,password);
    }

    public boolean checkLogin(){
        /* Mengembalikan nilai is_login, jika sudah login otomatis nilai true jika tidak akan return false */
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        /* Melakukan clear data yang ada pada sharedPreferences  , jangan lupa di commit agar data terubah*/
        editor.clear();
        editor.commit();
    }
}