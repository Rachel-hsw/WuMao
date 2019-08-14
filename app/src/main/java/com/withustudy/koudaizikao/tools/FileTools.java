package com.withustudy.koudaizikao.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTools
{
  private static final String PATH = "/";
  private static final String ROOT = "WithYouStudy";
  private static final String ROOT_PATH = "/WithYouStudy/";
  private static FileTools instance;
  private Context mContext;

  private FileTools(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static FileTools getInstance(Context paramContext)
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new FileTools(paramContext);
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public File createFile(String paramString)
  {
    File localFile = new File(getRootPath() + paramString);
    try
    {
      if (localFile.exists())
      {
        localFile.delete();
        localFile.createNewFile();
        return localFile;
      }
      localFile.createNewFile();
      return localFile;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return localFile;
  }

  public void deleteFile(String paramString)
  {
    File localFile = new File(getRootPath() + paramString);
    if (localFile.exists())
      localFile.delete();
  }

  public String getRootFilePath(File paramFile)
  {
    if (isFileExists(this.mContext.getExternalFilesDir(null).getPath() + "/WithYouStudy/" + paramFile.getName()))
      return this.mContext.getExternalFilesDir(null).getPath() + "/WithYouStudy/" + paramFile.getName();
    return this.mContext.getResources().getString(2131165380);
  }

  public String getRootFilePath(String paramString)
  {
    if (isFileExists(this.mContext.getExternalFilesDir(null).getPath() + "/WithYouStudy/" + paramString))
      return this.mContext.getExternalFilesDir(null).getPath() + "/WithYouStudy/" + paramString;
    return this.mContext.getResources().getString(2131165380);
  }

  public String getRootPath()
  {
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile = new File(this.mContext.getExternalFilesDir(null).getPath() + "/WithYouStudy/");
      if (!localFile.exists())
        localFile.mkdirs();
      return localFile.getPath() + "/";
    }
    return this.mContext.getFilesDir().getPath();
  }

  public boolean isFileExists(String paramString)
  {
    return new File(paramString).exists();
  }

  public File saveBitmapToSDCard(File paramFile, Bitmap paramBitmap)
  {
    try
    {
      localFileOutputStream = new FileOutputStream(paramFile);
      long l = paramBitmap.getHeight() * paramBitmap.getWidth();
      if (l <= 204800L)
        paramBitmap.compress(CompressFormat.JPEG, 100, localFileOutputStream);
      while (true)
      {
        localFileOutputStream.flush();
        localFileOutputStream.close();
        paramBitmap.recycle();
        return paramFile;
        if ((l <= 204800L) || (l <= 1536000L))
          break;
        paramBitmap.compress(CompressFormat.JPEG, 20, localFileOutputStream);
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        FileOutputStream localFileOutputStream;
        Toast.makeText(this.mContext, "FileNotFound", 0).show();
        localIOException.printStackTrace();
        return null;
        paramBitmap.compress(CompressFormat.JPEG, 5, localFileOutputStream);
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
      {
        Toast.makeText(this.mContext, "图片过大，小袋内存不够用啦!", 0).show();
        localOutOfMemoryError.printStackTrace();
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.FileTools
 * JD-Core Version:    0.6.0
 */