package com.huadin.shard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

public class MainActivity extends AppCompatActivity
{

  private static final String TAG = "MainActivity";

  private UMAuthListener umAuthListener = new UMAuthListener()
  {
    @Override
    public void onStart(SHARE_MEDIA platform)
    {
      //授权开始的回调
      Log.i(TAG, "onStart: ");
    }

    @Override
    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data)
    {
      Log.i(TAG, "onComplete: ");
      Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(SHARE_MEDIA platform, int action, Throwable t)
    {
      Log.i(TAG, "onError: ");
      Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA platform, int action)
    {
      Log.i(TAG, "onCancel: ");
      Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.Sina).setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
//        shardSina();
        shardQQ();
      }
    });
    findViewById(R.id.LoginOut).setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        loginOut();
      }
    });

    findViewById(R.id.shard).setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        shardContent();
      }
    });
  }

  private UMShareListener umShareListener = new UMShareListener()
  {
    @Override
    public void onStart(SHARE_MEDIA share_media)
    {
      Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResult(SHARE_MEDIA share_media)
    {
      Log.i(TAG, "onResult: ");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable)
    {
      Log.i(TAG, "onError: ");
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media)
    {
      Log.i(TAG, "onCancel: ");
    }
  };

  private void shardContent()
  {
    new ShareAction(MainActivity.this)
            .withText("hello")
            .withMedia(new UMWeb("http://www.baidu.com"))
            .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
            .setCallback(umShareListener).open();
  }

  private void loginOut()
  {
    UMShareAPI.get(this).deleteOauth(this,SHARE_MEDIA.QQ,umAuthListener);
  }

  private void shardQQ()
  {
    UMShareAPI.get(this).getPlatformInfo(this,SHARE_MEDIA.QQ,umAuthListener);
  }

//  private void shardSina()
//  {
//    try
//    {
//      UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
//    } catch (Exception e)
//    {
//      e.printStackTrace();
//    }
//  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);
    UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    Log.i(TAG, "onDestroy: ");

  }
}
