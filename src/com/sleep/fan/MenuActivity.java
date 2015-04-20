package com.sleep.fan;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MenuActivity extends Activity implements OnClickListener 
{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	private ImageView ac_fan=null;
	private ImageView ac_fan_box=null;
	private ImageView ac_fan_oven=null;
	private ImageView ac_fan_fire_furnace=null;

	public static int fanSelection=0;

	public static float factor=0.7f;  //high

	public static int screenWidth;
	public static int screenHeight;

	public static int soundSelection=R.raw.air_conditioner_fan_with_loop;

	protected static boolean onTouchFan1=false;
	protected static boolean onTouchFan2=false;
	protected static boolean onTouchFan3=false;
	protected static boolean onTouchFan4=false;

	protected static MenuActivity instance=null;

	private long timeValue=10800000; 

	private long timeincrease=0;

	private Timer timer=null;

	private boolean isAppWentToBg = false;

	private boolean isWindowFocused = false;


	@Override
	protected void onStart() {

		applicationWillEnterForeground();

		super.onStart();
	}

	private void applicationWillEnterForeground() {
		if (isAppWentToBg) {
			isAppWentToBg = false;
		
			if(!isAppWentToBg)
			{
				if(timer!=null)
				{
					timer.purge();
					timer.cancel();
					timer=null;
				}
			}
		}
	}

	@Override
	protected void onStop() {
		super.onStop();

		applicationdidenterbackground();
	}

	public void applicationdidenterbackground() {
		if (!isWindowFocused) {
			isAppWentToBg = true;
		
			if(isAppWentToBg)
			{
				timeValue=10800000;
				timeincrease=0;
				setTimeToFinish();
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);    

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);  

		DisplayMetrics metrics = getResources().getDisplayMetrics();
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;

		setContentView(R.layout.menu_activity);   

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		instance=this;
		
		try
		{
			Class.forName("android.os.AsyncTask");   //it prevents AdMob from crashing on HTC with Android 4.0.x
		}
		catch(Throwable ignored)
		{

		}

		ac_fan=(ImageView) findViewById(R.id.ac1);      
		ac_fan.setOnClickListener(this);

		ac_fan_box=(ImageView) findViewById(R.id.ac2);
		ac_fan_box.setOnClickListener(this);

		ac_fan_oven=(ImageView) findViewById(R.id.ac3);
		ac_fan_oven.setOnClickListener(this);

		ac_fan_fire_furnace=(ImageView) findViewById(R.id.ac4);
		ac_fan_fire_furnace.setOnClickListener(this);

		//BannerAd
		AdView adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-7701524183129395/2274237262");

		RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.rootViewGroup);
		rootLayout.addView(adView, 0);

		com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	@Override
	public void onClick(View view)
	{
		// TODO Auto-generated method stub
		switch (view.getId())
		{
		case R.id.ac1:
			fanSelection=0;
			onTouchFan1=true;
			if(onTouchFan1)
			{
				if(timer!=null)
				{
					timer.purge();
					timer.cancel();
					timer=null;
				}
			}
			Intent intent1=new Intent(MenuActivity.this, Fan1Activity.class);
			MenuActivity.this.startActivity(intent1);
			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);	
			break;

		case R.id.ac2:
			fanSelection=1;
			onTouchFan2=true;
			if(onTouchFan2)
			{
				if(timer!=null)
				{
					timer.purge();
					timer.cancel();
					timer=null;
				}
			}
			Intent intent2=new Intent(MenuActivity.this, Fan2Activity.class);
			MenuActivity.this.startActivity(intent2);
			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);	
			break;

		case R.id.ac3:
			fanSelection=2;
			onTouchFan3=true;
			if(onTouchFan3)
			{
				if(timer!=null)
				{
					timer.purge();
					timer.cancel();
					timer=null;
				}
			}
			Intent intent3=new Intent(MenuActivity.this, Fan3Activity.class);
			MenuActivity.this.startActivity(intent3);
			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);	
			break;

		case R.id.ac4:
			fanSelection=3;
			onTouchFan4=true;
			if(onTouchFan4)
			{
				if(timer!=null)
				{
					timer.purge();
					timer.cancel();
					timer=null;
				}
			}
			Intent intent4=new Intent(MenuActivity.this, Fan4Activity.class);
			MenuActivity.this.startActivity(intent4);
			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);	
			break;	
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		switch (item.getItemId()) 
		{
		case R.id.action_menu:
			AlertDialog alertDialog;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			View view = getLayoutInflater().inflate(R.layout.menuu, null);
			builder.setView(view)

			.setPositiveButton("OK",
					new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog,
						int which) {
					// TODO Add your code for the button here.

				}
			})
			.setNegativeButton(" Rate This App ",
					new DialogInterface.OnClickListener() 
			{
				@Override
				public void onClick(DialogInterface dialog,
						int which) 
				{
					// TODO Auto-generated method stub
					Intent i = new Intent(
							Intent.ACTION_VIEW,
							Uri.parse("https://play.google.com/store/apps/details?id=com.sleep.fan"));
					startActivity(i);

				}
			});       

			alertDialog = builder.create();
			alertDialog.show();
		}
		return super.onOptionsItemSelected(item);
	}

	private void setTimeToFinish() {
		// TODO Auto-generated method stub
	
		if(timer!=null)
		{
			timer.scheduleAtFixedRate(new TimerTask() 
			{
				@Override
				public void run() 
				{
					// TODO Auto-generated method stub
					if(!onTouchFan1 || !onTouchFan2 || !onTouchFan3 || !onTouchFan4)
					{		
						if(timeincrease<timeValue)
						{
							timeincrease+=1 * 1000;
						}
						else if(timeincrease>=timeValue)
						{
							try 
							{
								finish();
								if(timer!=null)
								{
									timer.cancel();
									timer=null;
								}

								if(MenuActivity.instance!=null)
								{
									try 
									{
										MenuActivity.instance.finish();
										MenuActivity.instance=null;
									} 
									catch (Exception e) 
									{
										// TODO: handle exception
									}
								}

								android.os.Process.killProcess(android.os.Process.myPid());			
							} 
							catch (Exception e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}   
					}
				}
			}, timeincrease, 1000);
		}
		
	}

	@Override
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		super.onResume();
		
		if(timer!=null)
		{
			timer.purge();
			timer.cancel();
			timer=null;
		}
		timer=new Timer();
	}
	@Override
	protected void onDestroy() 
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		this.finish();
	}
}
