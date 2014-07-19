package cn.com.chp5.semaphore;

import java.util.concurrent.Semaphore;

/**
 * <th>Semaphore例子<th>
 * 
 * <p>
 * 预期结果：线程可以启动多个，但是同时执行aquireAction的线程最多count个
 * <p>
 * 
 * <p>
 * 信号量作用：信号量用来控制同时访问资源和执行当前动作的线程个数
 * 信号量应用场景：1、实现资源池，如数据库连接池；
 * 2、将任意collection 转换成有界的阻塞collection
 * 3、单个信号量的Semaphore对象可以实现互斥锁，而且可以由一个线程锁定，另外一个线程释放，是解决死锁的一种方法
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-16</h1>
 */
public class SemaphoreDemo {
	private final Semaphore se;
	private int count;
	
	public SemaphoreDemo(int count){
		this.count = count;
		se = new Semaphore(count);
	} 
	
	public void doAction(){
		System.out.println(Thread.currentThread() + "[准备申请信号量!]");
		aquireAction();
		System.out.println(Thread.currentThread() + "[准备释放信号量！]");
		releaseAction();
	}
	
	public void aquireAction(){
		 try {  
             //acquire:获得；下面方法是获取信号量
             se.acquire();
             System.out.println(Thread.currentThread() + "[信号量申请成功，当前aquireAction()并发数为：" + (count - se.availablePermits()) + "]");
             Thread.sleep((long)Math.random()*10000);  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }
	}
	
	public void releaseAction(){
		se.release();
		System.out.println(Thread.currentThread() + "[信号量释放成功!]");
	}
}
