package com.huadin.shard;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by NCL on 2017/7/5.
 * Application
 */

public class ShardApplication extends Application
{
  {
     PlatformConfig.setSinaWeibo("2406859642","17c5d54fc55dd09b54edb7d3c73ecec6","https://api.weibo.com/oauth2/default.html");
    PlatformConfig.setQQZone("1105901253","msU3M2Dt5yXo0Y92");
  }

  @Override
  public void onCreate()
  {
    super.onCreate();
    UMShareAPI.get(this);

    Config.DEBUG = true;
  }
}
