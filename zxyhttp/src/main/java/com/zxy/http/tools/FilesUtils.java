package com.zxy.http.tools;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FilesUtils {
    /**
     * 返回学生名单 以String 数组形式
     *
     * @param mContext
     * @return
     */
    public static Boolean initAssets(Context mContext, String myPackage) {
        try {
            InputStream inputStream = mContext.getAssets().open("allocation");//这里的名字是你的txt 文本文件名称
            String str = getString(inputStream);
            String[] arr = str.split("\n");
            for (String packs : arr) {
                if (packs.equals(myPackage)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件内容
     *
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //创建字符缓冲流
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            //读取每行学生
            while ((line = reader.readLine()) != null) {
                //添加到字符缓冲流中
                sb.append(line);
                //一条一行
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回学生名单字符串
        return sb.toString();
    }
}
