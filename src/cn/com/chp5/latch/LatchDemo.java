package cn.com.chp5.latch;

import java.util.concurrent.CountDownLatch;

/**
 * <th>Latch例子<th>
 * 
 * <p>
 * 功能：通过CountDownLatch begin事件触发线程等待，当所有子线程全部准备就绪后再开始执行线程，当所有子线程执行完毕后的end触发主线程
 * 整个功能完成记录所有子线程从就绪到执行完的执行时间记录
 * <p>
 * 
 * <p>
 * Latch（门闩）：闭锁是一个同步工具类，可以延迟线程执行直到所有的线程都到达一个状态。
 * 作用：1、确保某个计算在其所需要的所有资源都初始化后再执行；
 * 2、确保某个服务在其他所依赖的服务都执行完毕后再执行；
 * 3、等个某个操作的所有参与者都准备就绪后再继续执行。
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-16</h1>
 */
public class LatchDemo {
	public Long runTask(int threadNum, final Runnable runnable) throws InterruptedException{
		final CountDownLatch begin = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(threadNum);
		
		for(int i = 0; i < threadNum; i++){
			Thread th = new Thread(){
				public void run(){
					try{
						begin.await();
						try {
							runnable.run();
						}finally{
							end.countDown();
						}
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			th.start();
		}
		Long beginSec = System.nanoTime();
		//begin 和end顺序千万不能写错，不然程序就死锁了。
		begin.countDown();
		end.await();
		Long endSec = System.nanoTime();
		
		return endSec - beginSec;
	}
}
