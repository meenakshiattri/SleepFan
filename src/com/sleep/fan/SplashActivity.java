package com.sleep.fan;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends Activity 
{
	public static int choose_screenSize=1;

	private CheckNetwork checkNetwork;   

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

		switch (screenSize)
		{
		case Configuration.SCREENLAYOUT_SIZE_SMALL:  //ldpi 320 * 240
			choose_screenSize=0;   
			break;

		case Configuration.SCREENLAYOUT_SIZE_NORMAL: //mdpi,hdpi,xhdpi phn 
			choose_screenSize=1;
			break;
		}

		setContentView(R.layout.main_activity); 

		try
		{
			Class.forName("android.os.AsyncTask");   //it prevents AdMob from crashing on HTC with Android 4.0.x
		}
		catch(Throwable ignored)
		{}

		checkNetwork = new CheckNetwork();


		if(checkNetwork.checkNetworkConnection(this))
		{
			final InterstitialAd interstitialAd = new InterstitialAd(this);

			com.google.android.gms.ads.AdRequest adRequestInter; adRequestInter = new com.google.android.gms.ads.AdRequest.Builder().build();
			interstitialAd.setAdUnitId("ca-app-pub-7701524183129395/5227703660");
			interstitialAd.loadAd(adRequestInter);

			interstitialAd.setAdListener(new AdListener() 
			{		
				@Override
				public void onAdLoaded() 
				{
					// TODO Auto-generated method stub
					super.onAdLoaded();
					interstitialAd.show();
				}

				@Override
				public void onAdFailedToLoad(int errorCode)
				{   
					// TODO Auto-generated method stub   
					super.onAdFailedToLoad(errorCode);
					Intent i=new Intent(SplashActivity.this, MenuActivity.class);
					SplashActivity.this.startActivity(i);
					SplashActivity.this.finish();
				}

				@Override
				public void onAdOpened()
				{
					// TODO Auto-generated method stub
					super.onAdOpened();
					interstitialAd.show();  
				}

				@Override
				public void onAdClosed() 
				{
					// TODO Auto-generated method stub
					super.onAdClosed();

					Intent i=new Intent(SplashActivity.this, MenuActivity.class);
					SplashActivity.this.startActivity(i);
					SplashActivity.this.finish();
				}				
			});

		}
		else
		{
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() 
			{
				public void run() 
				{
					Intent i=new Intent(SplashActivity.this, MenuActivity.class);
					SplashActivity.this.startActivity(i);
					SplashActivity.this.finish();
				}
			}, 2000);
		}	         
	}

	@Override
	protected void onDestroy() 
	{
		// TODO Auto-generated method stub	
		super.onDestroy();
		SplashActivity.this.finish();
		System.gc();
	}
}
