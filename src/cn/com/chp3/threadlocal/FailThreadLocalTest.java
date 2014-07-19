package cn.com.chp3.threadlocal;

public class FailThreadLocalTest implements Runnable{
	
	public static final FailThreadLocalDemo td = new FailThreadLocalDemo();

	public static void main(String[] args) {
		FailThreadLocalTest test = new FailThreadLocalTest();
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
