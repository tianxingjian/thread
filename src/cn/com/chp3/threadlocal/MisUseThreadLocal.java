package cn.com.chp3.threadlocal;

import java.util.concurrent.ThreadPoolExecutor;

public class MisUseThreadLocal implements Runnable{
	
	private static final Apple apple = new Apple();
	private static final ThreadLocal<Apple> threadlocal = new ThreadLocal<Apple>(){
		public Apple initialValue() { 
			return apple; 
		} 
	};
	
	@Override
	public void run() {
		displayThreadApple();
	}
	
	public Apple getApple(){
		 Apple apple = threadlocal.get();
		 if(apple == null){
			 String currentThreadName = Thread.currentThread().getName();
			 throw new RuntimeException(currentThreadName + ": ¿ÕÖ¸Õë´íÎó£¡"); 
		 }
		 return apple;
	 }
	 
	 public void displayThreadApple(){
		 int index = 10;
		 while(index > 0){
			 String currentThreadName = Thread.currentThread().getName(); 
		     Apple apple = getApple();
		     System.out.println(currentThreadName + " is running: " + "[ThreadLocal: " + threadlocal + "][apple: " + apple + "] [color: " + apple.getColor() + "]");
			 index--;
			 Thread.yield();
		 }
	 }
	 
	 public static void main(String[] args) {
		MisUseThreadLocal test = new MisUseThreadLocal();
		test.displayThreadApple();
		Thread th1 = new Thread(test, "threadA");
		Thread th2 = new Thread(test, "threadB");
		th1.start();
		th2.start();
	}
	 
}
