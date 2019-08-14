package com.withustudy.koudaizikao.action;

import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.get.AcKouDaiAddPostReply;
import com.withustudy.koudaizikao.action.get.AcKouDaiAuthCodeImpl;
import com.withustudy.koudaizikao.action.get.AcKouDaiBBSFollow;
import com.withustudy.koudaizikao.action.get.AcKouDaiCollectImpl;
import com.withustudy.koudaizikao.action.get.AcKouDaiCollectPost;
import com.withustudy.koudaizikao.action.get.AcKouDaiDeletePost;
import com.withustudy.koudaizikao.action.get.AcKouDaiErrorExerciseBasket;
import com.withustudy.koudaizikao.action.get.AcKouDaiFollowSection;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetAllSection;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetCollectPost;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetFavoriteNews;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetMajorSubject;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetMyPost;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetNewsComment;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetPost;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetPostReply;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetProv;
import com.withustudy.koudaizikao.action.get.AcKouDaiGetSinglePost;
import com.withustudy.koudaizikao.action.get.AcKouDaiHotPost;
import com.withustudy.koudaizikao.action.get.AcKouDaiHotSection;
import com.withustudy.koudaizikao.action.get.AcKouDaiInfoContent;
import com.withustudy.koudaizikao.action.get.AcKouDaiInfoList;
import com.withustudy.koudaizikao.action.get.AcKouDaiLaunch;
import com.withustudy.koudaizikao.action.get.AcKouDaiNewsCollect;
import com.withustudy.koudaizikao.action.get.AcKouDaiNewsMain;
import com.withustudy.koudaizikao.action.get.AcKouDaiNewsPraise;
import com.withustudy.koudaizikao.action.get.AcKouDaiNewsPush;
import com.withustudy.koudaizikao.action.get.AcKouDaiPersonal;
import com.withustudy.koudaizikao.action.get.AcKouDaiPraisePost;
import com.withustudy.koudaizikao.action.get.AcKouDaiPublishPost;
import com.withustudy.koudaizikao.action.get.AcKouDaiSimuImpl;
import com.withustudy.koudaizikao.action.get.AcKouDaiSubjectRankImpl;
import com.withustudy.koudaizikao.action.get.AcKouDaiUpdate;
import com.withustudy.koudaizikao.action.post.AcKouDaiAddExerciseComment;
import com.withustudy.koudaizikao.action.post.AcKouDaiAddfFavoriteExercise;
import com.withustudy.koudaizikao.action.post.AcKouDaiBindPhone;
import com.withustudy.koudaizikao.action.post.AcKouDaiBrushExcerciseImpl;
import com.withustudy.koudaizikao.action.post.AcKouDaiBrushRank;
import com.withustudy.koudaizikao.action.post.AcKouDaiChangePersonalInfo;
import com.withustudy.koudaizikao.action.post.AcKouDaiChapterKpointSummary;
import com.withustudy.koudaizikao.action.post.AcKouDaiChapterSectionGetNextExercise;
import com.withustudy.koudaizikao.action.post.AcKouDaiChapterSectionList;
import com.withustudy.koudaizikao.action.post.AcKouDaiDeleteFavorExcercise;
import com.withustudy.koudaizikao.action.post.AcKouDaiErrorDelete;
import com.withustudy.koudaizikao.action.post.AcKouDaiExcerciseDetail;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetBrushRank;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetErrorExerciseBasket;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetErrorList;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetExerciseCommentMore;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetExercisefavorites;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetKpointExercise;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetMockHistory;
import com.withustudy.koudaizikao.action.post.AcKouDaiGetMockRank;
import com.withustudy.koudaizikao.action.post.AcKouDaiIntell;
import com.withustudy.koudaizikao.action.post.AcKouDaiKowledgeExplain;
import com.withustudy.koudaizikao.action.post.AcKouDaiLogin;
import com.withustudy.koudaizikao.action.post.AcKouDaiNewsComment;
import com.withustudy.koudaizikao.action.post.AcKouDaiOldExamList;
import com.withustudy.koudaizikao.action.post.AcKouDaiPushUserAnswerImpl;
import com.withustudy.koudaizikao.action.post.AcKouDaiRegistImpl;
import com.withustudy.koudaizikao.action.post.AcKouDaiSaveMajorAndSubject;
import com.withustudy.koudaizikao.action.post.AcKouDaiSelectSubject;
import com.withustudy.koudaizikao.action.post.AcKouDaiStartSimuExam;
import com.withustudy.koudaizikao.action.post.AcKouDaiSubjectState;
import com.withustudy.koudaizikao.action.post.AcKouDaiSuggestion;
import com.withustudy.koudaizikao.action.post.AcKouDaiUpdatePassword;
import java.lang.reflect.Constructor;

public class UrlFactory
{
  private static Gson gson;
  private static UrlFactory searchFactory;

  public static UrlFactory getInstance()
  {
    if (searchFactory == null)
      monitorenter;
    try
    {
      if (searchFactory == null)
        searchFactory = new UrlFactory();
      return searchFactory;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static Gson getInstanceGson()
  {
    if ((gson == null) && (gson == null))
      monitorenter;
    try
    {
      gson = new Gson();
      return gson;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private Object getUrl(String paramString)
  {
    try
    {
      Object localObject = Class.forName(paramString).getConstructor(new Class[0]).newInstance(new Object[0]);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public AbsBaseUrlConstructor AddPostReply()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiAddPostReply.class.getName());
  }

  public AbsBaseUrlConstructor Update()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiUpdate.class.getName());
  }

  public AbsSimpleImplUrlFactory bindPhone()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiBindPhone.class.getName());
  }

  public AbsSimpleImplUrlFactory changePersonalInfo()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiChangePersonalInfo.class.getName());
  }

  public AbsBaseUrlConstructor collectPost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiCollectPost.class.getName());
  }

  public AbsSimpleImplUrlFactory deleteErrorsById()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiErrorDelete.class.getName());
  }

  public AbsSimpleImplUrlFactory deleteFavorExcercise()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiDeleteFavorExcercise.class.getName());
  }

  public AbsBaseUrlConstructor deletePost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiDeletePost.class.getName());
  }

  public AbsBaseUrlConstructor followSection()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiFollowSection.class.getName());
  }

  public AbsBaseUrlConstructor getAllSection()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetAllSection.class.getName());
  }

  public AbsBaseUrlConstructor getAuthCode()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiAuthCodeImpl.class.getName());
  }

  public AbsBaseUrlConstructor getBBSFollow()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiBBSFollow.class.getName());
  }

  public AbsSimpleImplUrlFactory getBrushExcercise()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiBrushExcerciseImpl.class.getName());
  }

  public AbsSimpleImplUrlFactory getBrushRank()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiBrushRank.class.getName());
  }

  public AbsSimpleImplUrlFactory getChapterKpointSummary()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiChapterKpointSummary.class.getName());
  }

  public AbsSimpleImplUrlFactory getChapterSectionGetNextExercise()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiChapterSectionGetNextExercise.class.getName());
  }

  public AbsSimpleImplUrlFactory getChapterSectionList()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiChapterSectionList.class.getName());
  }

  public AbsBaseUrlConstructor getCollectExcercise()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiCollectImpl.class.getName());
  }

  public AbsBaseUrlConstructor getCollectPost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetCollectPost.class.getName());
  }

  public AbsSimpleImplUrlFactory getErrorBaseket()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetErrorList.class.getName());
  }

  public AbsBaseUrlConstructor getErrorExerciseBasket()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiErrorExerciseBasket.class.getName());
  }

  public AbsSimpleImplUrlFactory getExcerciseDetail()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiExcerciseDetail.class.getName());
  }

  public AbsSimpleImplUrlFactory getExerciseCommentMore()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetExerciseCommentMore.class.getName());
  }

  public AbsSimpleImplUrlFactory getExercisefavorites()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetExercisefavorites.class.getName());
  }

  public AbsBaseUrlConstructor getFavoriteNews()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetFavoriteNews.class.getName());
  }

  public AbsBaseUrlConstructor getHotPost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiHotPost.class.getName());
  }

  public AbsBaseUrlConstructor getHotSection()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiHotSection.class.getName());
  }

  public AbsBaseUrlConstructor getInfoDetail()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiInfoContent.class.getName());
  }

  public AbsBaseUrlConstructor getInfoList()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiInfoList.class.getName());
  }

  public AbsSimpleImplUrlFactory getKowledgeExplain()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiKowledgeExplain.class.getName());
  }

  public AbsSimpleImplUrlFactory getKpointExercise()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetKpointExercise.class.getName());
  }

  public AbsBaseUrlConstructor getMajorSubject()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetMajorSubject.class.getName());
  }

  public AbsSimpleImplUrlFactory getMockHistory()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetMockHistory.class.getName());
  }

  public AbsSimpleImplUrlFactory getMockRank()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetMockRank.class.getName());
  }

  public AbsBaseUrlConstructor getMyPost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetMyPost.class.getName());
  }

  public AbsBaseUrlConstructor getNewsComment()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetNewsComment.class.getName());
  }

  public AbsSimpleImplUrlFactory getOldExamList()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiOldExamList.class.getName());
  }

  public AbsBaseUrlConstructor getPost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetPost.class.getName());
  }

  public AbsBaseUrlConstructor getPostReply()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetPostReply.class.getName());
  }

  public AbsBaseUrlConstructor getProv()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetProv.class.getName());
  }

  public AbsSimpleImplUrlFactory getSelectSubject()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiSelectSubject.class.getName());
  }

  public AcKouDaiSimuImpl getSimuTestGroup()
  {
    return (AcKouDaiSimuImpl)getUrl(AcKouDaiSimuImpl.class.getName());
  }

  public AbsBaseUrlConstructor getSinglePost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiGetSinglePost.class.getName());
  }

  public AbsSimpleImplUrlFactory getStartSimuExam()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiStartSimuExam.class.getName());
  }

  public AbsBaseUrlConstructor getSubjectRank()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiSubjectRankImpl.class.getName());
  }

  public AbsSimpleImplUrlFactory getSubjectState()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiSubjectState.class.getName());
  }

  public AbsSimpleImplUrlFactory getUserBrushRank()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetBrushRank.class.getName());
  }

  public AbsSimpleImplUrlFactory getUserErrorExerciseBasket()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiGetErrorExerciseBasket.class.getName());
  }

  public AbsBaseUrlConstructor launch()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiLaunch.class.getName());
  }

  public AbsSimpleImplUrlFactory login()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiLogin.class.getName());
  }

  public AbsBaseUrlConstructor newsCollect()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiNewsCollect.class.getName());
  }

  public AbsSimpleImplUrlFactory newsComment()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiNewsComment.class.getName());
  }

  public AbsBaseUrlConstructor newsMain()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiNewsMain.class.getName());
  }

  public AbsBaseUrlConstructor newsPraise()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiNewsPraise.class.getName());
  }

  public AbsBaseUrlConstructor newsPush()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiNewsPush.class.getName());
  }

  public AbsBaseUrlConstructor personal()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiPersonal.class.getName());
  }

  public AbsSimpleImplUrlFactory postAddExerciseComment()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiAddExerciseComment.class.getName());
  }

  public AbsSimpleImplUrlFactory postAddfFavoriteExercise()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiAddfFavoriteExercise.class.getName());
  }

  public AbsSimpleImplUrlFactory postIntellBrush()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiIntell.class.getName());
  }

  public AcKouDaiRegistImpl postRegist()
  {
    return (AcKouDaiRegistImpl)getUrl(AcKouDaiRegistImpl.class.getName());
  }

  public AbsSimpleImplUrlFactory postUserAnserList()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiPushUserAnswerImpl.class.getName());
  }

  public AbsBaseUrlConstructor praisePost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiPraisePost.class.getName());
  }

  public AbsBaseUrlConstructor publishPost()
  {
    return (AbsBaseUrlConstructor)getUrl(AcKouDaiPublishPost.class.getName());
  }

  public AbsSimpleImplUrlFactory saveMajorAndSubject()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiSaveMajorAndSubject.class.getName());
  }

  public AbsSimpleImplUrlFactory suggestion()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiSuggestion.class.getName());
  }

  public AbsSimpleImplUrlFactory updatePassword()
  {
    return (AbsSimpleImplUrlFactory)getUrl(AcKouDaiUpdatePassword.class.getName());
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.action.UrlFactory
 * JD-Core Version:    0.6.0
 */