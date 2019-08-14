package com.withustudy.koudaizikao.db;

import android.content.Context;
import android.util.Log;
import com.withustudy.koudaizikao.MyApplication;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import java.util.List;
import koudai.db.BrushExcerciseBatchDao;
import koudai.db.DaoSession;
import koudai.db.MajorDBDao;
import koudai.db.ProvinceDBDao;
import koudai.db.UserAnsDao;

public class DbService
{
  private static final String TAG = DbService.class.getSimpleName();
  private static Context appContext;
  private static DbService instance;
  private BrushExcerciseBatchDao brushExcerciseBatchDao;
  private DaoSession mDaoSession;
  private MajorDBDao majorDBDao;
  private ProvinceDBDao provinceDBDao;
  private UserAnsDao userAnsDao;

  public static DbService getInstance(Context paramContext)
  {
    if (instance == null)
    {
      instance = new DbService();
      if (appContext == null)
        appContext = paramContext.getApplicationContext();
      instance.mDaoSession = MyApplication.getDaoSession(paramContext);
      instance.userAnsDao = instance.mDaoSession.getUserAnsDao();
      instance.majorDBDao = instance.mDaoSession.getMajorDBDao();
      instance.provinceDBDao = instance.mDaoSession.getProvinceDBDao();
      instance.brushExcerciseBatchDao = instance.mDaoSession.getBrushExcerciseBatchDao();
    }
    return instance;
  }

  public <T> void deleteAllObject(AbstractDao<T, Long> paramAbstractDao)
  {
    paramAbstractDao.deleteAll();
  }

  public <T> void deleteObject(AbstractDao<T, Long> paramAbstractDao, long paramLong)
  {
    paramAbstractDao.deleteByKey(Long.valueOf(paramLong));
    Log.i(TAG, "delete");
  }

  public <T> void deleteObject(AbstractDao<T, Long> paramAbstractDao, T paramT)
  {
    paramAbstractDao.delete(paramT);
  }

  public BrushExcerciseBatchDao getBrushExcerciseBatchDao()
  {
    return this.brushExcerciseBatchDao;
  }

  public MajorDBDao getMajorDBDao()
  {
    return this.majorDBDao;
  }

  public ProvinceDBDao getProvinceDBDao()
  {
    return this.provinceDBDao;
  }

  public UserAnsDao getUserAnsDao()
  {
    return this.userAnsDao;
  }

  public <T> long insert(T paramT, AbstractDao<T, Long> paramAbstractDao)
  {
    return paramAbstractDao.insert(paramT);
  }

  public <T> List<T> loadAllObject(AbstractDao<T, Long> paramAbstractDao)
  {
    return paramAbstractDao.loadAll();
  }

  public <T> T loadObject(AbstractDao<T, Long> paramAbstractDao, long paramLong)
  {
    return paramAbstractDao.load(Long.valueOf(paramLong));
  }

  public <T> List<T> queryObject(AbstractDao<T, Long> paramAbstractDao, String paramString, String[] paramArrayOfString)
  {
    return paramAbstractDao.queryRaw(paramString, paramArrayOfString);
  }

  public <T> Long saveEntity(AbstractDao<T, Long> paramAbstractDao, T paramT)
  {
    return Long.valueOf(paramAbstractDao.insertOrReplace(paramT));
  }

  public <T> void saveEntityLists(List<T> paramList, AbstractDao<T, Long> paramAbstractDao)
  {
    if ((paramList == null) || (paramList.isEmpty()))
      return;
    paramAbstractDao.getSession().runInTx(new Runnable(paramList, paramAbstractDao)
    {
      public void run()
      {
        for (int i = 0; ; i++)
        {
          if (i >= this.val$list.size())
            return;
          Object localObject = this.val$list.get(i);
          this.val$dao.insertOrReplace(localObject);
        }
      }
    });
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.db.DbService
 * JD-Core Version:    0.6.0
 */