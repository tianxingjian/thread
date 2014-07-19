package cn.com.chp3.threadlocal;

public class ThreadLocalTest implements Runnable{
	
	public final static ThreadLocalDemo td = new ThreadLocalDemo();

	public static void main(String[] args) {
		ThreadLocalTest test = new ThreadLocalTest();
		td.displayThreadApple();
		Thread th1 = new Thread(test, "threadA");
		Thread th2 = new Thread(test, "threadB");
		th1.start();
		th2.start();
	}

	@Override
	public void run() {
		td.displayThreadApple();
	}
	
}
