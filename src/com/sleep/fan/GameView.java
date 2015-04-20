package com.sleep.fan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View 
{	
	private Bitmap fanblade=null;
	private Bitmap fanFront=null;
	private Bitmap fanBack=null;

	private float degrees=0.0f;	
	private float xBack=0;
	private float yBack=0;

	private Matrix rotate=null;
	private Paint paint=null;

	public GameView(Context context)   
	{  
		super(context);

		degrees=0;

		if(rotate==null)
		{  
			rotate = new Matrix();			
		}

		switch (SplashActivity.choose_screenSize)
		{
		case 0:
			switch ((MenuActivity.fanSelection)) 
			{      
			case 0:  
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back1);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade1);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front1);
				break;
			case 1:
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back2);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade2);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front2);
				break;
			case 2:
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back3);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade3);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front3);
				break;
			case 3:
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back4);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade4);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front4);
				break;
			}
			break;

		case 1:
			switch ((MenuActivity.fanSelection)) 
			{
			case 0:
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back1);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade1);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front1);
				break;
			case 1:
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back2);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade2);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front2);
				break;
			case 2:
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back3);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade3);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front3);
				break;
			case 3:
				fanBack=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_back4);
				fanblade=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_blade4);
				fanFront=BitmapFactory.decodeResource(context.getResources(), R.drawable.fan_front4);
				break;
			}
			break;  
		}

		xBack=MenuActivity.screenWidth/2 - fanBack.getWidth()/2;
		yBack=MenuActivity.screenHeight * 0.5f -fanBack.getHeight();

		// TODO Auto-generated constructor stub
	}	

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO Auto-generated method stub   
		super.onDraw(canvas);

		if(paint==null) 
		{   
			paint=new Paint();
			paint.setAntiAlias(true);
			paint.setFilterBitmap(true);
			paint.setDither(true);
		}		

		if(rotate!=null)
		{
			rotate=null;
			rotate=new Matrix();
		}
		
		switch (MenuActivity.fanSelection)
		{
		case 0:
			canvas.drawBitmap(fanBack, xBack,yBack, null);		
			rotate.postRotate(degrees, fanblade.getWidth()/2, fanblade.getHeight()/2);
			rotate.postTranslate(MenuActivity.screenWidth/2 - fanblade.getWidth()/2, MenuActivity.screenHeight * 0.5f -fanblade.getHeight());	
			canvas.drawBitmap(fanblade, rotate, null);		
			canvas.drawBitmap(fanFront, xBack,yBack, null);
			break;

		case 1:
			canvas.drawBitmap(fanBack, xBack,yBack, null);		
			rotate.postRotate(degrees, fanblade.getWidth()/2, fanblade.getHeight()/2);
			rotate.postTranslate(MenuActivity.screenWidth/2 - fanblade.getWidth()/2, MenuActivity.screenHeight * 0.5f -fanblade.getHeight());	
			canvas.drawBitmap(fanblade, rotate, null);		
			canvas.drawBitmap(fanFront, xBack,yBack, null);
			break;

		case 2:
			canvas.drawBitmap(fanBack, xBack,yBack, null);		
			rotate.postRotate(degrees, fanblade.getWidth()/2, fanblade.getHeight()/2);
			rotate.postTranslate(MenuActivity.screenWidth/2 - fanblade.getWidth()/2, MenuActivity.screenHeight * 0.5f -fanblade.getHeight());	
			canvas.drawBitmap(fanblade, rotate, null);		
			canvas.drawBitmap(fanFront, xBack,yBack, null);
			break;

		case 3:
			canvas.drawBitmap(fanBack, xBack,yBack, null);	   
			canvas.drawBitmap(fanFront, xBack,yBack, null);
			rotate.postRotate(degrees, fanblade.getWidth()/2, fanblade.getHeight()/2);
			rotate.postTranslate(MenuActivity.screenWidth/2 - fanblade.getWidth()/2, MenuActivity.screenHeight * 0.5f -fanblade.getHeight());	
			canvas.drawBitmap(fanblade, rotate, null);				
			break;
		}				


		switch (MenuActivity.fanSelection) 
		{
		case 0:
			
			if(MenuActivity.onTouchFan1)
			{
				if(Fan1Activity.onTouchPauseBtn )
				{    
					degrees=0;
				}

				if(Fan1Activity.onStopSpin )
				{
					degrees=0;
				}

				if(Fan1Activity.onTouchSetTime)
				{
					degrees=0;
					MenuActivity.onTouchFan1=false;
					Fan1Activity.onTouchStopBtn=false;
					Fan1Activity.onTouchPauseBtn=false;
					Fan1Activity.onTouchResumeBtn=false;
					Fan1Activity.onStartSpin=false;
					Fan1Activity.onStopSpin=false;
				}
				
				if(!Fan1Activity.onTouchFastBtn && !Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchSlowBtn && !Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				if(Fan1Activity.onTouchFastBtn && !Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchSlowBtn &&!Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				else if(Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchFastBtn && !Fan1Activity.onTouchSlowBtn &&!Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=18.0f;
				}

				else if(Fan1Activity.onTouchSlowBtn && !Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchFastBtn &&!Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=15.0f;
				}       

				if(Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees=0;
					Fan1Activity.onTouchStopBtn=false;
				}

				if(Fan1Activity.onTouchStopBtn && Fan1Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan1Activity.onTouchPauseBtn=false;
					Fan1Activity.onTouchStopBtn=false;
				}

				if(Fan1Activity.onStopSpin && Fan1Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan1Activity.onStopSpin=false;
					Fan1Activity.onStartSpin=false;
					Fan1Activity.onTouchStopBtn=false;
				}

				if(Fan1Activity.onStopSpin && Fan1Activity.onTouchStopBtn && Fan1Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan1Activity.onStopSpin=false;
					Fan1Activity.onTouchPauseBtn=false;
					Fan1Activity.onStartSpin=false;
					Fan1Activity.onTouchStopBtn=false;
				}
			}
			
			if(Fan1Activity.onTouchDoneBtn)
			{
				if(Fan1Activity.onTouchPauseBtn )
				{    
					degrees=0;
				}

				if(Fan1Activity.onStopSpin )
				{
					degrees=0;
				}

				if(Fan1Activity.onTouchSetTime)
				{
					degrees=0;
					Fan1Activity.onTouchDoneBtn=false;
					Fan1Activity.onTouchStopBtn=false;
					Fan1Activity.onTouchPauseBtn=false;
					Fan1Activity.onTouchResumeBtn=false;
					Fan1Activity.onStartSpin=false;
					Fan1Activity.onStopSpin=false;
				}
				
				if(!Fan1Activity.onTouchFastBtn && !Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchSlowBtn && !Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				if(Fan1Activity.onTouchFastBtn && !Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchSlowBtn &&!Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				else if(Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchFastBtn && !Fan1Activity.onTouchSlowBtn &&!Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=18.0f;
				}

				else if(Fan1Activity.onTouchSlowBtn && !Fan1Activity.onTouchMediumBtn && !Fan1Activity.onTouchFastBtn &&!Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees+=15.0f;
				}       

				if(Fan1Activity.onTouchStopBtn && !Fan1Activity.onTouchPauseBtn && !Fan1Activity.onStopSpin)
				{
					degrees=0;
					Fan1Activity.onTouchStopBtn=false;
				}

				if(Fan1Activity.onTouchStopBtn && Fan1Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan1Activity.onTouchPauseBtn=false;
					Fan1Activity.onTouchStopBtn=false;
				}

				if(Fan1Activity.onStopSpin && Fan1Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan1Activity.onStopSpin=false;
					Fan1Activity.onStartSpin=false;
					Fan1Activity.onTouchStopBtn=false;
				}

				if(Fan1Activity.onStopSpin && Fan1Activity.onTouchStopBtn && Fan1Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan1Activity.onStopSpin=false;
					Fan1Activity.onTouchPauseBtn=false;
					Fan1Activity.onStartSpin=false;
					Fan1Activity.onTouchStopBtn=false;
				}
			}
			break;

		case 1:
			
			if(MenuActivity.onTouchFan2)
			{
				if(Fan2Activity.onTouchPauseBtn)
				{
					degrees=0;
				}

				if(Fan2Activity.onStopSpin)
				{
					degrees=0;
				}
				
				if(Fan2Activity.onTouchSetTime)
				{
					degrees=0;
					MenuActivity.onTouchFan2=false;
					Fan2Activity.onTouchStopBtn=false;
					Fan2Activity.onTouchPauseBtn=false;
					Fan2Activity.onTouchResumeBtn=false;
					Fan2Activity.onStartSpin=false;
					Fan2Activity.onStopSpin=false;
				}

				if(!Fan2Activity.onTouchFastBtn && !Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchSlowBtn && !Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin) 
				{
					degrees+=20.0f;
				}

				if(Fan2Activity.onTouchFastBtn && !Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchSlowBtn &&!Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{ 
					degrees+=20.0f;
				}

				else if(Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchFastBtn && !Fan2Activity.onTouchSlowBtn &&!Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{
					degrees+=18.0f;
				}

				else if(Fan2Activity.onTouchSlowBtn && !Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchFastBtn &&!Fan2Activity.onTouchStopBtn&& !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{
					degrees+=15.0f;
				}       

				if(Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{
					degrees=0;
					Fan2Activity.onTouchStopBtn=false;
				}

				if(Fan2Activity.onTouchStopBtn && Fan2Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan2Activity.onTouchPauseBtn=false;
					Fan2Activity.onTouchStopBtn=false;
				}


				if(Fan2Activity.onStopSpin && Fan2Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan2Activity.onStopSpin=false;
					Fan2Activity.onStartSpin=false;
					Fan2Activity.onTouchStopBtn=false;
				}

				if(Fan2Activity.onStopSpin && Fan2Activity.onTouchStopBtn && Fan2Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan2Activity.onStopSpin=false;
					Fan2Activity.onTouchPauseBtn=false;
					Fan2Activity.onStartSpin=false;
					Fan2Activity.onTouchStopBtn=false;
				}
			}
			
			if(Fan2Activity.onTouchDoneBtn)
			{
				if(Fan2Activity.onTouchPauseBtn)
				{
					degrees=0;
				}

				if(Fan2Activity.onStopSpin)
				{
					degrees=0;   
				}
				
				if(Fan2Activity.onTouchSetTime)
				{
					degrees=0;
					Fan2Activity.onTouchDoneBtn=false;
					Fan2Activity.onTouchStopBtn=false;
					Fan2Activity.onTouchPauseBtn=false;
					Fan2Activity.onTouchResumeBtn=false;
					Fan2Activity.onStartSpin=false;
					Fan2Activity.onStopSpin=false;
				}

				if(!Fan2Activity.onTouchFastBtn && !Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchSlowBtn && !Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin) 
				{
					degrees+=20.0f;
				}

				if(Fan2Activity.onTouchFastBtn && !Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchSlowBtn &&!Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{ 
					degrees+=20.0f;
				}

				else if(Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchFastBtn && !Fan2Activity.onTouchSlowBtn &&!Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{
					degrees+=18.0f;
				}

				else if(Fan2Activity.onTouchSlowBtn && !Fan2Activity.onTouchMediumBtn && !Fan2Activity.onTouchFastBtn &&!Fan2Activity.onTouchStopBtn&& !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{
					degrees+=15.0f;
				}       

				if(Fan2Activity.onTouchStopBtn && !Fan2Activity.onTouchPauseBtn && !Fan2Activity.onStopSpin)
				{
					degrees=0;
					Fan2Activity.onTouchStopBtn=false;
				}

				if(Fan2Activity.onTouchStopBtn && Fan2Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan2Activity.onTouchPauseBtn=false;
					Fan2Activity.onTouchStopBtn=false;
				}


				if(Fan2Activity.onStopSpin && Fan2Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan2Activity.onStopSpin=false;
					Fan2Activity.onStartSpin=false;
					Fan2Activity.onTouchStopBtn=false;
				}

				if(Fan2Activity.onStopSpin && Fan2Activity.onTouchStopBtn && Fan2Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan2Activity.onStopSpin=false;
					Fan2Activity.onTouchPauseBtn=false;
					Fan2Activity.onStartSpin=false;
					Fan2Activity.onTouchStopBtn=false;
				}
			}
			break;

		case 2:
			
			if(MenuActivity.onTouchFan3)
			{
				if(Fan3Activity.onTouchPauseBtn)        
				{       
					degrees=0;
				}

				if(Fan3Activity.onStopSpin)        
				{       
					degrees=0;
				}
				
				if(Fan3Activity.onTouchSetTime)
				{
					degrees=0;
					MenuActivity.onTouchFan3=false;
					Fan3Activity.onTouchStopBtn=false;
					Fan3Activity.onTouchPauseBtn=false;
					Fan3Activity.onTouchResumeBtn=false;
					Fan3Activity.onStopSpin=false;
					Fan3Activity.onStartSpin=false;
				}	

				if(!Fan3Activity.onTouchFastBtn && !Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchSlowBtn && !Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=20.0f;
				}
				if(Fan3Activity.onTouchFastBtn && !Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchSlowBtn &&!Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				else if(Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchFastBtn && !Fan3Activity.onTouchSlowBtn &&!Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=15.0f;
				}

				else if(Fan3Activity.onTouchSlowBtn && !Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchFastBtn &&!Fan3Activity.onTouchStopBtn&& !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=12.0f;
				}       

				if(Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees=0;
					Fan3Activity.onTouchStopBtn=false;
				}


				if(Fan3Activity.onTouchStopBtn && Fan3Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan3Activity.onTouchPauseBtn=false;
					Fan3Activity.onTouchStopBtn=false;
				}

				if(Fan3Activity.onStopSpin && Fan3Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan3Activity.onStopSpin=false;
					Fan3Activity.onStartSpin=false;
					Fan3Activity.onTouchStopBtn=false;
				}

				if(Fan3Activity.onStopSpin && Fan3Activity.onTouchStopBtn && Fan3Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan3Activity.onStopSpin=false;
					Fan3Activity.onTouchPauseBtn=false;
					Fan3Activity.onStartSpin=false;
					Fan3Activity.onTouchStopBtn=false;
				}
			}
					   
			if(Fan3Activity.onTouchDoneBtn)
			{
				if(Fan3Activity.onTouchPauseBtn)        
				{       
					degrees=0;
				}

				if(Fan3Activity.onStopSpin)        
				{       
					degrees=0;
				}
				
				if(Fan3Activity.onTouchSetTime)
				{
					degrees=0;
					Fan3Activity.onTouchDoneBtn=false;
					Fan3Activity.onTouchStopBtn=false;
					Fan3Activity.onTouchPauseBtn=false;
					Fan3Activity.onTouchResumeBtn=false;
					Fan3Activity.onStopSpin=false;
					Fan3Activity.onStartSpin=false;
				}	

				if(!Fan3Activity.onTouchFastBtn && !Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchSlowBtn && !Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=20.0f;
				}
				if(Fan3Activity.onTouchFastBtn && !Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchSlowBtn &&!Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				else if(Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchFastBtn && !Fan3Activity.onTouchSlowBtn &&!Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=15.0f;
				}

				else if(Fan3Activity.onTouchSlowBtn && !Fan3Activity.onTouchMediumBtn && !Fan3Activity.onTouchFastBtn &&!Fan3Activity.onTouchStopBtn&& !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees+=12.0f;
				}       

				if(Fan3Activity.onTouchStopBtn && !Fan3Activity.onTouchPauseBtn && !Fan3Activity.onStopSpin)
				{
					degrees=0;
					Fan3Activity.onTouchStopBtn=false;
				}


				if(Fan3Activity.onTouchStopBtn && Fan3Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan3Activity.onTouchPauseBtn=false;
					Fan3Activity.onTouchStopBtn=false;
				}

				if(Fan3Activity.onStopSpin && Fan3Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan3Activity.onStopSpin=false;
					Fan3Activity.onStartSpin=false;
					Fan3Activity.onTouchStopBtn=false;
				}

				if(Fan3Activity.onStopSpin && Fan3Activity.onTouchStopBtn && Fan3Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan3Activity.onStopSpin=false;
					Fan3Activity.onTouchPauseBtn=false;
					Fan3Activity.onStartSpin=false;
					Fan3Activity.onTouchStopBtn=false;
				}
			}
			break;

		case 3:
			
			if(MenuActivity.onTouchFan4)
			{
				if(Fan4Activity.onTouchPauseBtn)
				{
					degrees=0;
				}

				if(Fan4Activity.onStopSpin)
				{
					degrees=0;
				}
				
				if(Fan4Activity.onTouchSetTime)
				{
					degrees=0;
					MenuActivity.onTouchFan4=false;
					Fan4Activity.onTouchStopBtn=false;
					Fan4Activity.onTouchPauseBtn=false;
					Fan4Activity.onTouchResumeBtn=false;
					Fan4Activity.onStartSpin=false;
					Fan4Activity.onStopSpin=false;
				}
				
				if(!Fan4Activity.onTouchFastBtn && !Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchSlowBtn && !Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{	
					degrees+=20.0f;
				}
				if(Fan4Activity.onTouchFastBtn && !Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchSlowBtn &&!Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				else if(Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchFastBtn && !Fan4Activity.onTouchSlowBtn &&!Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{
					degrees+=15.0f;
				}

				else if(Fan4Activity.onTouchSlowBtn && !Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchFastBtn &&!Fan4Activity.onTouchStopBtn&& !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{
					degrees+=13.0f;
				}       

				if(Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{	
					degrees=0;
					Fan4Activity.onTouchStopBtn=false;
				}

				if(Fan4Activity.onTouchStopBtn && Fan4Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan4Activity.onTouchPauseBtn=false;
					Fan4Activity.onTouchStopBtn=false;
				}

				if(Fan4Activity.onStopSpin && Fan4Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan4Activity.onStopSpin=false;
					Fan4Activity.onStartSpin=false;
					Fan4Activity.onTouchStopBtn=false;
				}

				if(Fan4Activity.onStopSpin && Fan4Activity.onTouchStopBtn && Fan4Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan4Activity.onStopSpin=false;
					Fan4Activity.onTouchPauseBtn=false;
					Fan4Activity.onStartSpin=false;
					Fan4Activity.onTouchStopBtn=false;
				}
			}
			
			if(Fan4Activity.onTouchDoneBtn)
			{
				if(Fan4Activity.onTouchPauseBtn)
				{
					degrees=0;
				}

				if(Fan4Activity.onStopSpin)
				{
					degrees=0;
				}
				
				if(Fan4Activity.onTouchSetTime)
				{
					degrees=0;
					Fan4Activity.onTouchDoneBtn=false;
					Fan4Activity.onTouchStopBtn=false;
					Fan4Activity.onTouchPauseBtn=false;
					Fan4Activity.onTouchResumeBtn=false;
					Fan4Activity.onStartSpin=false;
					Fan4Activity.onStopSpin=false;
				}
				
				if(!Fan4Activity.onTouchFastBtn && !Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchSlowBtn && !Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{	
					degrees+=20.0f;
				}
				if(Fan4Activity.onTouchFastBtn && !Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchSlowBtn &&!Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{
					degrees+=20.0f;
				}

				else if(Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchFastBtn && !Fan4Activity.onTouchSlowBtn &&!Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{
					degrees+=15.0f;
				}

				else if(Fan4Activity.onTouchSlowBtn && !Fan4Activity.onTouchMediumBtn && !Fan4Activity.onTouchFastBtn &&!Fan4Activity.onTouchStopBtn&& !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{
					degrees+=13.0f;
				}       

				if(Fan4Activity.onTouchStopBtn && !Fan4Activity.onTouchPauseBtn && !Fan4Activity.onStopSpin)
				{	
					degrees=0;
					Fan4Activity.onTouchStopBtn=false;
				}

				if(Fan4Activity.onTouchStopBtn && Fan4Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan4Activity.onTouchPauseBtn=false;
					Fan4Activity.onTouchStopBtn=false;
				}

				if(Fan4Activity.onStopSpin && Fan4Activity.onTouchStopBtn)
				{
					degrees=0;
					Fan4Activity.onStopSpin=false;
					Fan4Activity.onStartSpin=false;
					Fan4Activity.onTouchStopBtn=false;
				}

				if(Fan4Activity.onStopSpin && Fan4Activity.onTouchStopBtn && Fan4Activity.onTouchPauseBtn)
				{
					degrees=0;
					Fan4Activity.onStopSpin=false;
					Fan4Activity.onTouchPauseBtn=false;
					Fan4Activity.onStartSpin=false;
					Fan4Activity.onTouchStopBtn=false;
				}
			}
			break;
		}

		switch (MenuActivity.fanSelection) 
		{
		case 0:
			if(!Fan1Activity.onTimeFinish)
				invalidate();	
			break;

		case 1:
			if(!Fan2Activity.onTimeFinish)
				invalidate();	
			break;

		case 2:
			if(!Fan3Activity.onTimeFinish)
				invalidate();	   
			break;

		case 3:
			if(!Fan4Activity.onTimeFinish)  
				invalidate();	
			break;
		}
	}	

	public void RecycleImage()   
	{
		if(fanblade!=null)    
		{
			fanblade.recycle();
			fanblade=null;
		}  

		if(fanBack!=null)    
		{
			fanBack.recycle();
			fanBack=null;
		}  

		if(fanFront!=null)    
		{
			fanFront.recycle();
			fanFront=null;
		}  
	}
}
