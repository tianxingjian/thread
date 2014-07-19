package cn.com.chp6;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
	
	private final static int poolCount = 3;
	private final static int thCount = 10;
	
	public static void main(String[] args) {
		ExecutorService excute = Executors.newFixedThreadPool(poolCount);
		
		for(int i = 0; i < thCount; i++){
			excute.execute(new EXRunnable());
		}
		excute.shutdown();
		//System.out.println("baicai");
		//excute.execute(new EXRunnable());
	}
}

class EXRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread() + "：执行开始！");
		long time = (long)(Math.random() * 10000);

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread() + "：执行完毕！");
	}
	
}
