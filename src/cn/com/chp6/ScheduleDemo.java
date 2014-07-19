package cn.com.chp6;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleDemo {
	
	public void testTimerOnTime(){
		TimerTask task = new THRunnable();
		TimerTask task1 = new THRunnable();
		Timer timer = new Timer();
		timer.schedule(task, 10000);
		timer.schedule(task1, 10000);
	}
	
	public void testTimerException(){
		
	}
	
	public void testSchedulOnTime(){
		Runnable runnable = new SHRunnable();
		ScheduledExecutorService excute = Executors.newScheduledThreadPool(2);
		excute.schedule(runnable, 10000, TimeUnit.MILLISECONDS);
		excute.schedule(runnable, 10000, TimeUnit.MILLISECONDS);
	}
	
	public void testSchedulException(){
		
	}
	
	public static void main(String[] args) {
		ScheduleDemo sh = new ScheduleDemo();
		sh.testSchedulOnTime();
		sh.testTimerOnTime();
	}
}


class THRunnable extends TimerTask{

	@Override
	public void run() {
		System.out.println("TH:" + System.nanoTime());
	}
	
}

class SHRunnable extends TimerTask implements Runnable{

	@Override
	public void run() {
		System.out.println("SH:" + System.nanoTime());
	}
	
}

