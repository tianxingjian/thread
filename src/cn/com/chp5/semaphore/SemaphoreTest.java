package cn.com.chp5.semaphore;

import java.util.concurrent.Executor;

public class SemaphoreTest {
	
	public static void main(String[] args) {
		final SemaphoreDemo seDemo = new SemaphoreDemo(3);
		int threadCount = 10;
		String []threadName = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
		for(int i = 0; i < threadCount; i++){
			
			Thread th = new Thread(new Runnable() {	
				@Override
				public void run() {
					seDemo.doAction();
				}
			}, threadName[i]);
			th.start();
		}
	}
	
}

