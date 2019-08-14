package com.withustudy.koudaizikao.config;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.List;

public class Constants
{
  public static String DB_NAME = "koudai_db_1.0";
  public static HashMap<String, Integer> typeToIndex;
  public static HashMap<String, Integer> xY;

  @SuppressLint({"UseSparseArrays"})
  public static HashMap<Integer, String> yX = new HashMap()
  {
    private static final long serialVersionUID = 1L;
  };

  static
  {
    xY = new HashMap()
    {
      private static final long serialVersionUID = 1L;
    };
    typeToIndex = new HashMap()
    {
      private static final long serialVersionUID = 1L;
    };
  }

  public static class BrushType
  {
    public static String ERROR_BASKET_BRUSH;
    public static String FAVORITE_BRUSH;
    public static String MOCK_EXAM;
    public static String REAL_EXAM;
    public static String SEQUENCE_BRUSH = "SEQUENCE_BRUSH";
    public static String SMART_BRUSH = "SMART_BRUSH";

    static
    {
      MOCK_EXAM = "MOCK_EXAM";
      REAL_EXAM = "REAL_EXAM";
      FAVORITE_BRUSH = "FAVORITE_BRUSH";
      ERROR_BASKET_BRUSH = "ERROR_BASKET_BRUSH";
    }
  }

  public static class Error
  {
    public static final String JSON_NULL_ERROR = "服务器未能返回数据，请稍后再试";
    public static final String SERVICE_EXCEPTION_ERROR = "服务器未响应，请稍后再试";
  }

  public static class InformationType
  {
    public static final String COMMEN_QUESTION = "COMMEN_QUESTION";
    public static final String DAILY_CHEESE = "DAILY_CHEESE";
    public static final String EXAMINATION_ROAD = "EXAMINATION_ROAD";
    public static final String OFFICIAL_NEWS = "OFFICIAL_NEWS";
    public static final String SIGN_UP_INFO = "SIGN_UP_INFO";
  }

  public static class PushType
  {
    public static String Mock = "MOCK_EXAM";
    public static String section = "SEQUENCE_BRUSH";
    public static String smart = "SMART_BRUSH";
  }

  public static class Result
  {
    public static final String NEWS_STATUS_OK = "NEWS_STATUS_OK";
    public static final String OK = "OK";
    public static final String STATE_AUTHCODE_ERROR = "STATE_AUTHCODE_ERROR";
    public static final String STATE_AUTHCODE_UNEXPIRED = "STATE_AUTHCODE_UNEXPIRED";
    public static final String STATE_OK = "STATE_OK";
    public static final String STATE_USER_ALREADY_EXIST = "STATE_USER_ALREADY_EXIST";
    public static final String STATE_USER_NOT_EXIST = "STATE_USER_NOT_EXIST";
    public static final String STATUS_OK = "STATUS_OK";
  }

  public static class Subject
  {
    public static List<String> sId;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.config.Constants
 * JD-Core Version:    0.6.0
 */