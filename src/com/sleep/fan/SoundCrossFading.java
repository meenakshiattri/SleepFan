package com.sleep.fan;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundCrossFading 
{
	public MediaPlayer mediaPlayer = null;
	private MediaPlayer mediaPlayer2 = null;
	private Context context;

	private double currentVolumn = 0;
	private int currentPositionOfSound = 0;
	private double currentVolumn2 = 0;
	private boolean oneTimeStarted = false;
	private boolean isFanPlaying = false;

	private Timer timer = new Timer();
	
	public SoundCrossFading(Context context) 
	{
		super();
		this.context = context;
	}

	public void load(int resource, boolean looping)
	{
		mediaPlayer = MediaPlayer.create(context, resource);
		mediaPlayer.setLooping(looping);

		mediaPlayer2 = MediaPlayer.create(context, resource);
		mediaPlayer2.setLooping(looping);

		setFadeffect();
	}

	private void setFadeffect()
	{
		// TODO Auto-generated method stub
		timer.schedule(new TimerTask() 
		{
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				try 
				{
					currentPositionOfSound = mediaPlayer.getCurrentPosition();

					if (currentPositionOfSound < 1500)
					{
						if (!oneTimeStarted) 
						{
							currentVolumn = currentVolumn + 0.02;
							currentVolumn2 = currentVolumn2 - 0.02;
						}
						else 
						{
							currentVolumn = 0;
							currentVolumn2 = 1;

						}
					} 
					else if (currentPositionOfSound > 900 && currentPositionOfSound < mediaPlayer.getDuration() - 2000) 
					{
						currentVolumn = 1;
						currentVolumn2 = 0;

						if (!oneTimeStarted && currentPositionOfSound > mediaPlayer.getDuration() / 2) 
						{
							oneTimeStarted = true;
							playMedia2();

						}

					} 
					else if (currentPositionOfSound > mediaPlayer.getDuration() - 2000 && currentPositionOfSound < mediaPlayer.getDuration()) 
					{
						currentVolumn = 0;
						currentVolumn2 = 1;
					}
					updateSound();
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}, 0, 22);
	}

	protected void updateSound()
	{
		// TODO Auto-generated method stub
		mediaPlayer.setVolume((float) currentVolumn * MenuActivity.factor,
				(float) currentVolumn * MenuActivity.factor);
		mediaPlayer2.setVolume((float) currentVolumn2 * MenuActivity.factor,
				(float) currentVolumn2 * MenuActivity.factor);
	}

	public void play()
	{
		if(mediaPlayer!=null)
		{
			if (!mediaPlayer.isPlaying()) 
			{
				mediaPlayer.start();
				setFanPlaying(true);
			}
		}
		else
		{
			load(MenuActivity.soundSelection, true);
			mediaPlayer.start();
			setFanPlaying(true);
		}
	}

	public void playMedia2() 
	{
		if(mediaPlayer2!=null)
		{  
			if (!mediaPlayer2.isPlaying())
			{
				mediaPlayer2.start();
				setFanPlaying(true);
			}
		}
	}

	public void stop() 
	{
		try {
			if(timer!=null)
			{
				timer.cancel();
				timer.purge();
				timer = null;
			}
			
			if (mediaPlayer!=null && mediaPlayer.isPlaying()) 
			{
				mediaPlayer.pause();
				mediaPlayer.stop();
				mediaPlayer.release();
			}
			if (mediaPlayer2!=null && mediaPlayer2.isPlaying()) 
			{
				mediaPlayer2.pause();
				mediaPlayer2.stop();
				mediaPlayer2.release();
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resume() 
	{
		if (mediaPlayer != null && !mediaPlayer.isPlaying()) 
		{
			play();
		}
	}

	public void resumemMedia2()
	{
		if (mediaPlayer2 != null && !mediaPlayer2.isPlaying())
		{
			playMedia2();
		}
	}

	public boolean isFanPlaying() 
	{
		return isFanPlaying;
	}

	public void setFanPlaying(boolean isFanPlaying)
	{
		this.isFanPlaying = isFanPlaying;
	}
}
