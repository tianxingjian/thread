package cn.com.chp5.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <th>Barrier����<th>
 * 
 * <p>
 * Barrier(�ϰ�)��Barrier�ȴ�һ������ɻ���һ������ʼ����ִ���̵߳ĺ�������������֯ȥ��԰�����˵����˲ų���
 * Barrier��Latch���� 1��Barrier�Ķ����������̶߳��õ���ͬһ����ܼ�������ִ�У�Latch�Ķ����ǵ�Latch�ﵽһ��
 * �ٽ磨�յ㣩״̬ʱ���е��̲߳��ܻ������ִ�е�ͨ����
 * 2��Latch���̵߳ȴ�ĳ���¼���ɣ�Barrier���̵߳ȴ������߳�
 * 3������������Latch�Ǳ����ȴ���Barrier�������ȴ���Barrier�ȴ����ᱻ�ȴ�����ͬһ���Լ��𣬶����̡߳����Է���֯���Σ������˰�Լ��
 * ��ʱ�䣬�ص���ͬһ���ط����ϣ��ȵ����˵ȴ������˶��������һ�������Latch���񿪻�ǩ��������ǰ�������Ƚ���ǩ��������֯�ߵȵ����в����߶�ǩ��
 * ���ٽ����������
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
			System.out.println("�̡߳�" + thNames[i] + "����ʼ����ϣ�");
			th.start();
		}
	}
	
	class BTRunnable implements Runnable{

		@Override
		public void run() {
			System.out.println(Thread.currentThread() + "[����ǰ������]" + System.currentTimeMillis());
			try {
				barrier.await();
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "[���������!]" + System.currentTimeMillis());
		}
		
	}
	
}


