package com.withustudy.koudaizikao.net;

import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class NetworkImageCache extends LruCache<String, Bitmap>
  implements ImageLoader.ImageCache
{
  public NetworkImageCache()
  {
    this(getDefaultLruCacheSize());
  }

  public NetworkImageCache(int paramInt)
  {
    super(paramInt);
  }

  public static int getDefaultLruCacheSize()
  {
    return (int)(Runtime.getRuntime().maxMemory() / 1024L) / 8;
  }

  public Bitmap getBitmap(String paramString)
  {
    return (Bitmap)get(paramString);
  }

  public void putBitmap(String paramString, Bitmap paramBitmap)
  {
    put(paramString, paramBitmap);
  }

  protected int sizeOf(String paramString, Bitmap paramBitmap)
  {
    return paramBitmap.getRowBytes() * paramBitmap.getHeight() / 1024;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.net.NetworkImageCache
 * JD-Core Version:    0.6.0
 */