package com.siziksu.crypto.common.utils;

import android.content.Context;
import android.util.Log;

import com.siziksu.crypto.common.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    public String getFileContent(Context context, String fileName) {
        BufferedReader reader = null;
        try {
            StringBuilder json = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return json.toString();
        } catch (Exception e) {
            Log.e(Constants.TAG, e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(Constants.TAG, e.getMessage(), e);
                }
            }
        }
        return "";
    }
}
