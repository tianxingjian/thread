package cn.com.chp3.threadlocal;

import java.util.Random;

public class ThreadLocalDemo {
	
	 private static final ThreadLocal<Apple> threadlocal = new ThreadLocal<Apple>();
	 
	 public Apple getApple(){
		 Apple apple = threadlocal.get();
		 if(apple == null){
			 Random random = new Random();
		     int index = random.nextInt(5);
			 apple = new Apple();
			 apple.setColor(index);
			 threadlocal.set(apple);
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
