package com.sleep.fan;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class Fan2Activity extends Activity implements OnClickListener 
{
	private TextView title_fan = null;
	private Button fast_btn = null;
	private Button medium_btn = null;
	private Button slow_btn = null;
	private Button time_btn = null;
	private Button back_btn = null;
	private TimePicker time_picker = null;
	private Button done_btn = null;
	private Button cancel_btn = null;
	private Button startspin_btn = null;
	private Button stopspin_btn = null;

	private TextView time_set = null;
	private ImageView stopImage = null;
	private ImageView pauseImage = null;
	private ImageView playImage = null;

	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	private long getcountTimer = 0;

	protected static boolean onTouchFastBtn = false;
	protected static boolean onTouchMediumBtn = false;
	protected static boolean onTouchSlowBtn = false;
	protected static boolean onTouchDoneBtn = false;
	protected static boolean onTouchStopBtn = false;
	protected static boolean onTouchPauseBtn = false;
	protected static boolean onTouchResumeBtn = false;
	protected static boolean onTimeFinish = false;
	protected static boolean onStartSpin = false;
	protected static boolean onStopSpin = false;
	protected static boolean onTouchSetTime = false;
	protected static boolean onTouchZeroSet= false;

	private boolean OnTimeChange=true;

	private SoundCrossFading sound = null;
	private Typeface font = null;
	private AdView adView = null;
	private RelativeLayout relatieLayout = null;
	private TimerClass setTime = null;
	private RelativeLayout fanParentLayout = null;
	private GameView fanBlade = null;

	private Timer timer=null;
	private int timestopvalue=0;
	private int timestopvalue1=0;

	private int increaseMint=0;
	private int increaseHour=0;

	private int stopvalue=1;

	private TextView text_hour = null;
	private TextView text_minute = null;

	private boolean isAppWentToBg = false;
	private boolean isWindowFocused = false;

	int multiply_hour;
	int multiply_minutes;

	private Timer timerkill=null;

	private long timefinishValue=10800000;
	private long timeincrease=0;
	
	private int checkTime=0;
	
	private int timechangeValue=10800000;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fan2);

		try
		{
			Class.forName("android.os.AsyncTask");   //it prevents AdMob from crashing on HTC with Android 4.0.x
		}
		catch(Throwable ignored)
		{}


		MenuActivity.soundSelection=R.raw.box_fan_with_loop;

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		OnTimeChange = true;
		timestopvalue=0;
		stopvalue=1;
		checkTime=0;
		
		setTimerClass();

		onTouchFastBtn = false;
		onTouchMediumBtn = false;
		onTouchSlowBtn = false;
		onTouchDoneBtn = false;
		onTouchStopBtn = false;
		onTouchPauseBtn = false;
		onTouchResumeBtn = false;
		onTimeFinish = false;
		onStartSpin = false;
		onStopSpin = false;
		onTouchZeroSet=false;


		fanParentLayout = (RelativeLayout) findViewById(R.id.fan);
		fanBlade = new GameView(Fan2Activity.this);
		fanParentLayout.addView(fanBlade);

		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-7701524183129395/2274237262");

		RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.rootViewGroup);
		rootLayout.addView(adView, 0);

		com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder()
		.build();
		adView.loadAd(adRequest);

		title_fan = (TextView) findViewById(R.id.txt_title_fan);
		text_hour = (TextView) findViewById(R.id.hour_text);
		text_minute = (TextView) findViewById(R.id.minute_text);

		fast_btn = (Button) findViewById(R.id.fastButton);
		medium_btn = (Button) findViewById(R.id.mediumButton);
		slow_btn = (Button) findViewById(R.id.slowButton);
		time_btn = (Button) findViewById(R.id.timeButton);
		startspin_btn = (Button) findViewById(R.id.startspin_btn);
		stopspin_btn = (Button) findViewById(R.id.stopspin_btn);
		back_btn = (Button) findViewById(R.id.backBtn);

		time_picker = (TimePicker) findViewById(R.id.time_picker);
		time_picker.setIs24HourView(true);
		time_picker.setCurrentHour(0);
		time_picker.setCurrentMinute(0);

		time_set = (TextView) findViewById(R.id.time_set);

		font = Typeface.createFromAsset(getAssets(), "digital-7 (mono).ttf");
		time_set.setTypeface(font);

		done_btn = (Button) findViewById(R.id.done_btn);
		cancel_btn = (Button) findViewById(R.id.cancel_btn);

		relatieLayout = (RelativeLayout) findViewById(R.id.layout);
		stopImage = (ImageView) findViewById(R.id.stopbtn);
		pauseImage = (ImageView) findViewById(R.id.pausebtn);
		playImage = (ImageView) findViewById(R.id.playbtn);
		playImage.setVisibility(View.GONE);

		time_btn.setOnClickListener(this);
		fast_btn.setOnClickListener(this);
		medium_btn.setOnClickListener(this);
		slow_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
		done_btn.setOnClickListener(this);
		stopImage.setOnClickListener(this);
		pauseImage.setOnClickListener(this);
		playImage.setOnClickListener(this);
		startspin_btn.setOnClickListener(this);
		stopspin_btn.setOnClickListener(this);
		back_btn.setOnClickListener(this);

		if (sound != null && !onTouchFastBtn && !onTouchMediumBtn
				&& !onTouchSlowBtn) {
			MenuActivity.factor = 0.7f;
		}

		if(MenuActivity.onTouchFan2)
		{
			sound = new SoundCrossFading(this);
		}

		if(sound!=null && MenuActivity.onTouchFan2)
		{
			sound.play();
		}
	}

	@Override
	public void onClick(View view) 
	{
		// TODO Auto-generated method stub
		switch (view.getId()) 
		{
		case R.id.timeButton:

			MenuActivity.onTouchFan2=false;
			OnTimeChange=false;
			onTouchSetTime=true;
			onTouchPauseBtn=false;
			onTouchResumeBtn=false;
			onStartSpin=false;
			onStopSpin=false;

			//adView.setVisibility(View.GONE);
			relatieLayout.setVisibility(View.GONE);

			time_picker.setVisibility(View.VISIBLE);
			text_hour.setVisibility(View.VISIBLE);
			text_minute.setVisibility(View.VISIBLE);
			done_btn.setVisibility(View.VISIBLE);
			cancel_btn.setVisibility(View.VISIBLE);

			time_picker.setCurrentHour(0);
			time_picker.setCurrentMinute(0);

			checkTime=0;
			
			if(sound!=null)
			{
				sound.stop();
				sound=null;
			}

			if(timer!=null)
			{
				timestopvalue=0;
				timestopvalue1=0;
				increaseMint=0;
				increaseHour=0;
				timer.purge();
				timer.cancel();
				timer=null;
			}

			if(setTime!=null)
			{
				hours=0;
				minutes=0;
				seconds=0;
				setTime.cancel();
				setTime=null;
			}

			break;

		case R.id.fastButton:
			onTouchFastBtn = true;
			onTouchMediumBtn = false;
			onTouchSlowBtn = false;
			FastBtnTouchFunction();
			break;

		case R.id.mediumButton:
			onTouchMediumBtn = true;
			onTouchFastBtn = false;
			onTouchSlowBtn = false;
			MediumBtnTouchFunction();
			break;

		case R.id.slowButton:
			onTouchSlowBtn = true;
			onTouchFastBtn = false;
			onTouchMediumBtn = false;
			slowBtnTouchFunction();
			break;

		case R.id.done_btn:
			stopvalue=0;
			onTimeFinish = false;
			onTouchSetTime=false;
			onTouchStopBtn = false;
			onTouchDoneBtn = true;

			if(!onTouchPauseBtn || !onTouchResumeBtn)
			{
				pauseImage.setVisibility(View.VISIBLE);
				playImage.setVisibility(View.GONE);

			}

			if(!onStartSpin || !onStopSpin)
			{
				startspin_btn.setVisibility(View.GONE);
				stopspin_btn.setVisibility(View.VISIBLE);
			}
			
			doneBtnFunction();

			break;

		case R.id.cancel_btn:
			
			stopvalue=0;   
			onTimeFinish = true;
			onTouchStopBtn = false;
			onTouchDoneBtn = true;
			onTouchZeroSet=true;
			onTouchSetTime=false;


			time_picker.setVisibility(View.GONE);
			cancel_btn.setVisibility(View.GONE);
			done_btn.setVisibility(View.GONE);
			text_hour.setVisibility(View.GONE);
			text_minute.setVisibility(View.GONE); 
			relatieLayout.setVisibility(View.VISIBLE);
			
			time_picker.setCurrentHour(0);
			time_picker.setCurrentMinute(0);

			time_set.setText(""+"00"+ ":" + "00"+ ":"+"00");

			if(!onTouchPauseBtn || !onTouchResumeBtn)
			{
				pauseImage.setVisibility(View.VISIBLE);
				playImage.setVisibility(View.GONE);

			}

			if(!onStartSpin || !onStopSpin)
			{
				startspin_btn.setVisibility(View.GONE);
				stopspin_btn.setVisibility(View.VISIBLE);
			}
			break;

		case R.id.stopbtn:
			stopvalue=1;

			onTouchStopBtn = true;
			
			onTouchPauseBtn=false;
			onTouchResumeBtn=false;
			onStartSpin=false;  
			onStopSpin=false;

			if(!onTouchPauseBtn || !onTouchResumeBtn)
			{
				pauseImage.setVisibility(View.VISIBLE);
				playImage.setVisibility(View.GONE);
			}

			if(!onStartSpin || ! onStopSpin)
			{
				startspin_btn.setVisibility(View.GONE);
				stopspin_btn.setVisibility(View.VISIBLE);
			}

			if (onTouchStopBtn) 
			{
				if(setTime!=null)
				{ 
					hours=0;
					minutes=0;
					seconds=0;
					setTime.cancel();
					setTime=null;
					time_picker.setCurrentHour(0);   
					time_picker.setCurrentMinute(0);			
				}
				if(timer!=null)
				{
					timestopvalue=0;
					timestopvalue1=0;
					increaseMint=0;
					increaseHour=0;
					timer.cancel();
					timer=null;
				}

				if(MenuActivity.onTouchFan2)
				{
					if(timer!=null)
					{
						timestopvalue=0;
						timestopvalue1=0;
						increaseMint=0;
						increaseHour=0;
						timer.cancel();
						timer=null;
					}
				}

				if (sound != null) 
				{
					sound.stop();
				}

				if(onTouchZeroSet)
				{
					onTimeFinish=false;
					onTouchStopBtn=false;
					onTouchSetTime=false;
					sound = null;
					sound = new SoundCrossFading(Fan2Activity.this);
					sound.load(R.raw.box_fan_with_loop, true);
					sound.play();

					fanBlade.RecycleImage();
					fanBlade = null;

					fanBlade = new GameView(Fan2Activity.this);
					fanParentLayout.removeAllViews();
					fanParentLayout.addView(fanBlade);
				}
			}

			try 
			{
				if(!onTouchPauseBtn)
				{
					OnTimeChange=true;
				}
				checkTime=0;
				time_set.setText(""+"00"+ ":" + "00"+ ":"+"00");
				
				setTimerClass();

				if(!onTouchZeroSet)
				{
					if (sound != null) 
					{
						sound = null;
						sound = new SoundCrossFading(Fan2Activity.this);
						sound.load(R.raw.box_fan_with_loop, true);
						sound.play();
					}   
				}

			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			break;

		case R.id.pausebtn:  

			onTouchPauseBtn = true;
			OnTimeChange=false;
			onTouchResumeBtn = false;

			if (onTouchPauseBtn) 
			{
				pauseImage.setVisibility(View.GONE);
				playImage.setVisibility(View.VISIBLE);
				if(onTouchDoneBtn)
				{
					if(setTime!=null)
					{
						hours=0;
						minutes=0;
						seconds=0;
						setTime.cancel();
						setTime=null;
						time_picker.setCurrentHour(0);   
						time_picker.setCurrentMinute(0);			
					}

					if (sound != null) 
					{
						sound.stop();
					}
				}

				if(MenuActivity.onTouchFan2)
				{
					if (sound != null) 
					{
						sound.stop();
					}
				}

				if(onTouchZeroSet)
				{
					if (sound != null) 
					{
						sound.stop();
					}
				}
			}
			break;

		case R.id.playbtn:

			if(	stopvalue==1)
			{
				OnTimeChange=true;
			}

			onTouchResumeBtn = true;
			onTouchPauseBtn = false;

			if (onTouchResumeBtn)      
			{
				pauseImage.setVisibility(View.VISIBLE);
				playImage.setVisibility(View.GONE);

				if(onTouchDoneBtn)
				{
					if(!OnTimeChange && !onTouchZeroSet)
					{
						setTime = new TimerClass((1000 * getcountTimer) / 1000, 1000);
						setTime.start();
					}

				}
			}

			if (sound != null) 
			{
				sound = null;
				sound = new SoundCrossFading(Fan2Activity.this);
				sound.load(R.raw.box_fan_with_loop, true);
				sound.play();
			}
			break;

		case R.id.startspin_btn:
			onStartSpin = true;
			onStopSpin = false;

			if (onStartSpin)
			{
				startspin_btn.setVisibility(View.GONE);
				stopspin_btn.setVisibility(View.VISIBLE);
			}
			break;

		case R.id.stopspin_btn:

			onStartSpin = false;
			onStopSpin = true;

			if (onStopSpin) 
			{
				startspin_btn.setVisibility(View.VISIBLE);
				stopspin_btn.setVisibility(View.GONE);
			}
			break;

		case R.id.backBtn:


			try {
				if(onTouchPauseBtn || onTouchStopBtn)
				{

				}

				else 
				{
					if (setTime != null) 
					{
						setTime.onFinish();
						setTime.cancel();
						setTime = null;
					}

					if (sound != null)
					{
						sound.stop();
						sound=null;
					}

					if(timer!=null)
					{
						timer.purge();
						timer.cancel();
						timer=null;
					}
					
					if(timerkill!=null)
					{
						timerkill.purge();
						timerkill.cancel();
						timerkill=null;
					}
					checkTime=0;
				}

				if(adView!=null)
				{
					adView.destroy();
					adView=null;
				}

				MenuActivity.onTouchFan2=false;
				onTouchFastBtn = false;
				onTouchMediumBtn = false;
				onTouchSlowBtn = false;
				onTouchDoneBtn = false;
				onTouchPauseBtn = false;
				onTouchResumeBtn = false;
				onTimeFinish = false;
				onTouchStopBtn = false;
				onStartSpin = false;
				onStopSpin = false;
				onTouchSetTime = false;
				OnTimeChange=true;
				onTouchZeroSet=false;

				timestopvalue=0;
				timestopvalue1=0;
				stopvalue=0;

				System.gc();
				finish();
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);	

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	protected void onDestroy() 
	{
		// TODO Auto-generated method stub
		super.onDestroy();

		MenuActivity.factor = 0.7f;

		MenuActivity.onTouchFan2=false;
		onTouchFastBtn = false;
		onTouchMediumBtn = false;
		onTouchSlowBtn = false;
		onTouchDoneBtn = false;
		onTouchPauseBtn = false;
		onTouchResumeBtn = false;
		onTimeFinish = false;
		onTouchStopBtn = false;
		onStartSpin = false;
		onStopSpin = false;
		onTouchSetTime = false;
		OnTimeChange=true;
		onTouchZeroSet=false;

		timestopvalue=0;
		timestopvalue1=0;
		stopvalue=0;
		checkTime=0;

		if (setTime != null) 
		{
			setTime.onFinish();
			setTime.cancel();
			setTime = null;
		}

		if (fanBlade != null)
		{
			fanBlade.RecycleImage();
			fanBlade = null;
		}

		if (sound != null)
		{
			sound.stop();
			sound=null;
		}

		if(timer!=null)
		{
			timer.purge();
			timer.cancel();
			timer=null;
		}

		if(adView!=null)
		{
			adView.destroy();
			adView=null;
		}
		
		if(timerkill!=null)
		{
			timerkill.purge();
			timerkill.cancel();
			timerkill=null;
		}
	}

	private void doneBtnFunction()
	{
		sound = new SoundCrossFading(this);

		int get_hour = time_picker.getCurrentHour();
		int get_minute = time_picker.getCurrentMinute();

		int multiply_hour = (get_hour * 60);
		int multiply_minutes = (get_minute + multiply_hour) * 60;

		relatieLayout.setVisibility(View.VISIBLE);

		time_picker.setVisibility(View.GONE);
		done_btn.setVisibility(View.GONE);
		cancel_btn.setVisibility(View.GONE);   
		text_hour.setVisibility(View.GONE);
		text_minute.setVisibility(View.GONE);

		setTime = new TimerClass(1000 * multiply_minutes, 1000);
		setTime.start();

		if (onTouchFastBtn) 
		{     
			MenuActivity.factor = 0.7f;
		}

		else if (onTouchMediumBtn) {
			MenuActivity.factor = 0.5f;
		}

		else if (onTouchSlowBtn) {
			MenuActivity.factor = 0.3f;
		}

		if (multiply_hour == 0 && multiply_minutes == 0) 
		{
			onTouchZeroSet=true;
			relatieLayout.setVisibility(View.VISIBLE);
			//adView.setVisibility(View.VISIBLE);
			time_set.setText(""+"00"+ ":" + "00"+ ":"+"00");
		} 
		else 
		{
			checkTime=2;
			
			if(1000 * multiply_minutes <= timefinishValue)
			{
				timefinishValue=10800000;
				System.out.println("value of less");
			}
			if(1000 * multiply_minutes > timefinishValue)
			{
				timefinishValue =1000 * multiply_minutes;
				System.out.println("value of more");
			}
		
			onTouchZeroSet=false;
			sound.play();

			fanBlade.RecycleImage();
			fanBlade = null;

			fanBlade = new GameView(Fan2Activity.this);

			fanParentLayout.removeAllViews();
			fanParentLayout.addView(fanBlade);
		}
	}

	private void FastBtnTouchFunction() {
		if (onTouchFastBtn && !onTouchMediumBtn && !onTouchSlowBtn) {
			switch (SplashActivity.choose_screenSize) {
			case 0:
				fast_btn.setBackgroundResource(R.drawable.fast_selected);
				medium_btn
				.setBackgroundResource(R.drawable.medium_unselected);
				slow_btn.setBackgroundResource(R.drawable.slow_unselected);
				break;

			case 1:
				fast_btn.setBackgroundResource(R.drawable.fast_selected);
				medium_btn.setBackgroundResource(R.drawable.medium_unselected);
				slow_btn.setBackgroundResource(R.drawable.slow_unselected);
				break;
			}

			if (sound != null) {
				MenuActivity.factor = 0.7f;
			}
		} else {
			switch (SplashActivity.choose_screenSize) {
			case 0:
				fast_btn.setBackgroundResource(R.drawable.fast_unselected);
				break;

			case 1:
				fast_btn.setBackgroundResource(R.drawable.fast_unselected);
				break;

			}
		}
	}

	private void MediumBtnTouchFunction() {
		if (onTouchMediumBtn && !onTouchFastBtn && !onTouchSlowBtn) {
			switch (SplashActivity.choose_screenSize) {
			case 0:
				medium_btn
				.setBackgroundResource(R.drawable.medium_selected);
				fast_btn.setBackgroundResource(R.drawable.fast_unselected);
				slow_btn.setBackgroundResource(R.drawable.slow_unselected);
				break;

			case 1:
				medium_btn.setBackgroundResource(R.drawable.medium_selected);
				fast_btn.setBackgroundResource(R.drawable.fast_unselected);
				slow_btn.setBackgroundResource(R.drawable.slow_unselected);
				break;


			}

			if (sound != null) {
				MenuActivity.factor = 0.5f;
			}
		} else {
			switch (SplashActivity.choose_screenSize) {
			case 0:
				medium_btn
				.setBackgroundResource(R.drawable.medium_unselected);
				break;

			case 1:
				medium_btn.setBackgroundResource(R.drawable.medium_unselected);
				break;


			}
		}
	}

	private void slowBtnTouchFunction() {
		if (onTouchSlowBtn && !onTouchFastBtn && !onTouchMediumBtn) {
			switch (SplashActivity.choose_screenSize) {
			case 0:
				slow_btn.setBackgroundResource(R.drawable.slow_selected);
				medium_btn
				.setBackgroundResource(R.drawable.medium_unselected);
				fast_btn.setBackgroundResource(R.drawable.fast_unselected);
				break;

			case 1:
				slow_btn.setBackgroundResource(R.drawable.slow_selected);
				medium_btn.setBackgroundResource(R.drawable.medium_unselected);
				fast_btn.setBackgroundResource(R.drawable.fast_unselected);
				break;


			}

			if (sound != null) {
				MenuActivity.factor = 0.3f;
			}
		} else {
			switch (SplashActivity.choose_screenSize) {
			case 0:
				slow_btn.setBackgroundResource(R.drawable.slow_unselected);
				break;

			case 1:
				slow_btn.setBackgroundResource(R.drawable.slow_unselected);
				break;


			}
		}
	}

	// Timer Class
	private class TimerClass extends CountDownTimer {
		public TimerClass(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			onTouchZeroSet=true;
			onTimeFinish = true;
			onStartSpin = false;
			onStopSpin = false;

			relatieLayout.setVisibility(View.VISIBLE);
			//adView.setVisibility(View.VISIBLE);
			time_set.setText(""+"00"+ ":" + "00"+ ":"+"00");

			if (sound != null)
			{
				sound.stop();
				sound=null;
			}
			time_picker.setCurrentHour(0);
			time_picker.setCurrentMinute(0);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			getcountTimer = millisUntilFinished;
			seconds = (int) (millisUntilFinished / 1000) % 60;
			minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
			hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);

			String secondValue = String.format("%02d", seconds);
			String minuteValue = String.format("%02d", minutes);
			String hourValue = String.format("%02d", hours);


			time_set.setText(hourValue + ":" + minuteValue + ":" + secondValue);
		}
	}

	private void setTimerClass()
	{
		checkTime=1;
		timer = new Timer();
		timer.schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				// TODO Auto-generated method stub


				if(OnTimeChange)
					System.out.println(" timer runnign value :  "+timestopvalue++);

				timestopvalue1 =timestopvalue;

				if (timestopvalue1>= 60) 
				{
					increaseMint = (int) (timestopvalue1 / 60);
					timestopvalue1 %= 60;
				}
				if(increaseMint>=60)
				{
					increaseHour=(int)(increaseMint/60);
					increaseMint %=60;
				}

				if(OnTimeChange)
					new AsyncTaskStopTime().execute();

			}
		}, 0, 1000);
	}

	class AsyncTaskStopTime extends AsyncTask<String, String, String> 
	{
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			String secondIn = String.format("%02d", timestopvalue1);
			String minuteIn = String.format("%02d", increaseMint);
			String hourIn=String.format("%02d", increaseHour);
			time_set.setText(""+hourIn+ ":" + minuteIn+ ":"+secondIn);
		}
	}

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
				if(timerkill!=null)
				{
					timerkill.purge();
					timerkill.cancel();
					timerkill=null;
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
				if(checkTime==1)
				{
					timefinishValue=10800000;
					timeincrease=0;
					System.out.println("value of timefinishValue in check 1"+timefinishValue);
				}
				if(onTouchDoneBtn)
				{	
					if(checkTime==2)
					{
						if(onTouchPauseBtn)
						{
							System.out.println("value of timefinishValue in check 2 if"+timefinishValue);
						}
						else
						{
							timefinishValue=timefinishValue + timechangeValue;
							System.out.println("value of timefinishValue in check 2 else "+timefinishValue);
						}
						
					}	
				}
				
				setTimeToFinish();
			}
		}
	}
	
	private void setTimeToFinish() {
		// TODO Auto-generated method stub
		if(timerkill!=null)
		{
			timerkill.scheduleAtFixedRate(new TimerTask() 
			{
				@Override
				public void run() 
				{
					// TODO Auto-generated method stub	
					if(!OnTimeChange)
					{
						if(timeincrease<timefinishValue)
						{
							timeincrease+=1* 1000;
							System.out.println("value of time in if"+timeincrease);
						}
						else if(timeincrease>=timefinishValue)
						{
								finish();
								if(timerkill!=null)
								{
									timerkill.cancel();
									timerkill=null;
								}

								if(MenuActivity.instance!=null)
								{
										MenuActivity.instance.finish();
										MenuActivity.instance=null;
								}

								android.os.Process.killProcess(android.os.Process.myPid());			
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
		
		if(timerkill!=null)
		{
			timerkill.purge();
			timerkill.cancel();
			timerkill=null;
		}
		timerkill=new Timer();
	}
	
	@Override
	protected void onRestart()
	{
		// TODO Auto-generated method stub
		super.onRestart();
		fanBlade.RecycleImage();
		fanBlade = null;

		fanBlade = new GameView(Fan2Activity.this);
		fanParentLayout.removeAllViews();
		fanParentLayout.addView(fanBlade);
	}
}
