package com.withustudy.koudaizikao.tools;

import android.content.Context;
import com.withustudy.koudaizikao.db.DbService;
import com.withustudy.koudaizikao.entity.Major;
import com.withustudy.koudaizikao.entity.Province;
import com.withustudy.koudaizikao.entity.content.ProvMajorsContent;
import java.util.Iterator;
import java.util.List;
import koudai.db.MajorDB;
import koudai.db.MajorDBDao;
import koudai.db.ProvinceDB;
import koudai.db.ProvinceDBDao;

public class DbTools
{
  public static void deleteProvinceList1(Context paramContext)
  {
    DbService localDbService = DbService.getInstance(paramContext);
    new Thread(new Runnable(localDbService.getMajorDBDao(), localDbService.getProvinceDBDao())
    {
      public void run()
      {
        DbTools.this.deleteAll();
        this.val$provinceDBDao.deleteAll();
      }
    }).start();
  }

  public static void saveProvinceList1(Context paramContext, ProvMajorsContent paramProvMajorsContent)
  {
    DbService localDbService = DbService.getInstance(paramContext);
    new Thread(new Runnable(paramProvMajorsContent, localDbService.getMajorDBDao(), localDbService.getProvinceDBDao())
    {
      public void run()
      {
        Iterator localIterator1 = DbTools.this.getProvMajors().iterator();
        if (!localIterator1.hasNext())
          return;
        Province localProvince = (Province)localIterator1.next();
        String str1 = localProvince.getProvId();
        String str2 = localProvince.getProvName();
        Iterator localIterator2 = localProvince.getMajor().iterator();
        while (true)
        {
          if (!localIterator2.hasNext())
          {
            ProvinceDB localProvinceDB = new ProvinceDB();
            localProvinceDB.setProvId(str1);
            localProvinceDB.setProviceName(str2);
            this.val$provinceDBDao.insert(localProvinceDB);
            break;
          }
          Major localMajor = (Major)localIterator2.next();
          String str3 = localMajor.getMajorId();
          String str4 = localMajor.getMajorName();
          String str5 = localMajor.getProvName();
          MajorDB localMajorDB = new MajorDB();
          localMajorDB.setMajorId(str3);
          localMajorDB.setProvName(str5);
          localMajorDB.setMajorName(str4);
          this.val$majorDBDao.insert(localMajorDB);
        }
      }
    }).start();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.DbTools
 * JD-Core Version:    0.6.0
 */