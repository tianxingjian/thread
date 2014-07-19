package cn.com.chp5.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <th>Barrier例子<th>
 * 
 * <p>
 * Barrier(障碍)：Barrier等待一组活动都完成或者一组活动都开始后再执行线程的后续操作，如组织去公园，等人到齐了才出发
 * Barrier跟Latch区别： 1、Barrier的定义是所有线程都得到达同一点才能继续向下执行，Latch的定义是当Latch达到一个
 * 临界（终点）状态时所有的线程才能获得往下执行的通道。
 * 2、Latch是线程等待某个事件完成，Barrier是线程等待其他线程
 * 3、个人理解觉得Latch是被动等待，Barrier是主动等待。Barrier等待方会被等待方是同一属性级别，都是线程。如自发组织旅游，所有人按约定
 * 的时间，地点在同一个地方集合，先到的人等待其他人都到齐后再一起出发。Latch就像开会签到，开会前参与者先进行签到，当组织者等到所有参与者都签到
 * 后再进入会议主题
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-16</h1>
 */
public class BarrierDemo {
	private final CyclicBarrier barrier;
	
	public BarrierDemo(int barCount, Runnable runnable){
		barrier = new CyclicBarrier(barCount, runnable);
	}
	
	public void doAction(String [] thNames){
		for(int i = 0; i < thNames.length; i++){
			Thread th = new Thread(new BTRunnable(), thNames[i]);
			System.out.println("线程【" + thNames[i] + "】初始化完毕！");
			th.start();
		}
	}
	
	class BTRunnable implements Runnable{

		@Override
		public void run() {
			System.out.println(Thread.currentThread() + "[阻塞前操作！]" + System.currentTimeMillis());
			try {
				barrier.await();
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "[阻塞后操作!]" + System.currentTimeMillis());
		}
		
	}
	
}


