package com.aribhatt.automate.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by aribhatt on 28/12/17.
 */

public class Utils {

    public static int getColorCode(String name){
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        return generator.getColor(name);
    }

    public static void showToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showSnackBar(View view, String text, Context context){
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public static void hideKeyboard(@NonNull Activity activity, @NonNull EditText et){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

}
