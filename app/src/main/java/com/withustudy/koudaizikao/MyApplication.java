package com.withustudy.koudaizikao;

import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import com.android.http.RequestManager;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.withustudy.koudaizikao.config.Constants;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.tools.FileTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import java.io.File;
import koudai.db.DaoMaster;
import koudai.db.DaoMaster.DevOpenHelper;
import koudai.db.DaoMaster.OpenHelper;
import koudai.db.DaoSession;

public class MyApplication extends Application
{
  private static DaoMaster daoMaster;
  private static DaoSession daoSession;
  private final int connectTimeout = 10000;
  private final int discCacheFileCount = 100;
  private final int discCacheSize = 52428800;
  private Context mContext;
  public LocationClient mLocationClient;
  public MyLocationListener mMyLocationListener;
  private final int memoryCacheSize = 2097152;
  private final int readTimeout = 40000;
  private final int threadPoolSize = 2;

  private File getCachePath()
  {
    File localFile = new File(FileTools.getInstance(this.mContext).getRootPath() + "Cache");
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static DaoMaster getDaoMaster(Context paramContext)
  {
    if (daoMaster == null)
      daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(paramContext, Constants.DB_NAME, null).getWritableDatabase());
    return daoMaster;
  }

  public static DaoSession getDaoSession(Context paramContext)
  {
    if (daoSession == null)
    {
      if (daoMaster == null)
        daoMaster = getDaoMaster(paramContext);
      daoSession = daoMaster.newSession();
    }
    return daoSession;
  }

  private int getScreenHeight()
  {
    return ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getHeight();
  }

  private int getScreenWidth()
  {
    return ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getWidth();
  }

  private void initImageLoader()
  {
    ImageLoaderConfiguration localImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this.mContext).memoryCacheExtraOptions(getScreenWidth(), getScreenHeight()).threadPoolSize(2).threadPriority(3).denyCacheImageMultipleSizesInMemory().memoryCacheSize(2097152).discCacheSize(52428800).discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).discCacheFileCount(100).discCache(new UnlimitedDiscCache(getCachePath())).defaultDisplayImageOptions(DisplayImageOptions.createSimple()).imageDownloader(new BaseImageDownloader(this.mContext, 10000, 40000)).writeDebugLogs().build();
    ImageLoader.getInstance().init(localImageLoaderConfiguration);
  }

  public void onCreate()
  {
    super.onCreate();
    this.mContext = getApplicationContext();
    RequestManager.getInstance().init(this);
    initImageLoader();
    this.mLocationClient = new LocationClient(getApplicationContext());
    this.mMyLocationListener = new MyLocationListener();
    this.mLocationClient.registerLocationListener(this.mMyLocationListener);
  }

  public class MyLocationListener
    implements BDLocationListener
  {
    public MyLocationListener()
    {
    }

    public void onReceiveLocation(BDLocation paramBDLocation)
    {
      KouDaiSP.getInstance(MyApplication.this).setLongitude(paramBDLocation.getLongitude());
      KouDaiSP.getInstance(MyApplication.this).setLatitude(paramBDLocation.getLatitude());
      KouDaiSP.getInstance(MyApplication.this).setCityName(paramBDLocation.getCity());
      LogUtils.myLog("百度定位经度：" + paramBDLocation.getLongitude() + ";纬度：" + paramBDLocation.getLatitude());
      LogUtils.myLog("百度定位城市：" + paramBDLocation.getCity());
      LogUtils.myLog("百度定位省份：" + paramBDLocation.getProvince());
      LogUtils.myLog("百度定位地址：" + paramBDLocation.getAddrStr());
      LogUtils.myLog("百度定位网络类型：" + paramBDLocation.getNetworkLocationType());
      String str = paramBDLocation.getCity();
      if ((str != null) && (!str.equals("")))
        MyApplication.this.mLocationClient.stop();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.MyApplication
 * JD-Core Version:    0.6.0
 */