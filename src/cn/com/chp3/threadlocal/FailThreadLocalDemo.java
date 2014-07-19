package cn.com.chp3.threadlocal;

import java.util.Random;

public class FailThreadLocalDemo {
	private static final Apple apple;
	private static final ThreadLocal<Apple> threadlocal = new ThreadLocal<Apple>();
	
	static{
		apple = new Apple();
		threadlocal.set(apple);
	}
	
	/*
	public FailThreadLocalDemo() {
		apple = new Apple();
		Random random = new Random();
		int index = random.nextInt(5);
		apple.setColor(index);
		threadlocal.set(apple);
	}
	*/
	
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
}
