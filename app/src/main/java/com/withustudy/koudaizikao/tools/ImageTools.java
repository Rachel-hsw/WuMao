package com.withustudy.koudaizikao.tools;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.InputStream;

public class ImageTools
{
  private static int computeInitialSampleSize(Options paramOptions, int paramInt1, int paramInt2)
  {
    double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    int i;
    int j;
    if (paramInt2 == -1)
    {
      i = 1;
      if (paramInt1 != -1)
        break label60;
      j = 128;
      label31: if (j >= i)
        break label84;
    }
    label60: label84: 
    do
    {
      return i;
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
      break;
      j = (int)Math.min(Math.floor(d1 / paramInt1), Math.floor(d2 / paramInt1));
      break label31;
      if ((paramInt2 == -1) && (paramInt1 == -1))
        return 1;
    }
    while (paramInt1 == -1);
    return j;
  }

  public static int computeSampleSize(Options paramOptions, int paramInt1, int paramInt2)
  {
    int i = computeInitialSampleSize(paramOptions, paramInt1, paramInt2);
    if (i <= 8)
    {
      int j = 1;
      while (true)
      {
        if (j >= i)
          return j;
        j <<= 1;
      }
    }
    return 8 * ((i + 7) / 8);
  }

  public static Bitmap createFramedPhoto(Bitmap paramBitmap, float paramFloat)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(paramBitmap);
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    RectF localRectF = new RectF(0.0F, 0.0F, i, j);
    Paint localPaint = new Paint(1);
    localPaint.setColor(-65536);
    localCanvas.drawRoundRect(localRectF, paramFloat, paramFloat, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localBitmapDrawable.setBounds(0, 0, i, j);
    localCanvas.saveLayer(localRectF, localPaint, 31);
    localBitmapDrawable.draw(localCanvas);
    localCanvas.restore();
    return localBitmap;
  }

  public static Options getOptions(InputStream paramInputStream)
  {
    Options localOptions = new Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(paramInputStream, null, localOptions);
    localOptions.inSampleSize = computeSampleSize(localOptions, -1, 230400);
    localOptions.inJustDecodeBounds = false;
    return localOptions;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.ImageTools
 * JD-Core Version:    0.6.0
 */