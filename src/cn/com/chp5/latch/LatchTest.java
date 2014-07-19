package cn.com.chp5.latch;

import java.util.Random;

public class LatchTest {
	private static final int count = 10;
	public static void main(String[] args) throws InterruptedException {
		System.out.println("进入主线程：。。。。。。");
		Long l = new LatchDemo().runTask(count, new TaskAction());
		System.out.println("工作线程执行时间:" + l);
	}
	
}
class TaskAction implements Runnable{

	@Override
	public void run() {
		Random rand = new Random(1000);
		int count = rand.nextInt() + 1;
		for(int i = 0; i < count; i++){
			int result = i*i;
		}
	}
	
}
