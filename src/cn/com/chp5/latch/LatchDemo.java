package cn.com.chp5.latch;

import java.util.concurrent.CountDownLatch;

/**
 * <th>Latch����<th>
 * 
 * <p>
 * ���ܣ�ͨ��CountDownLatch begin�¼������̵߳ȴ������������߳�ȫ��׼���������ٿ�ʼִ���̣߳����������߳�ִ����Ϻ��end�������߳�
 * ����������ɼ�¼�������̴߳Ӿ�����ִ�����ִ��ʱ���¼
 * <p>
 * 
 * <p>
 * Latch�����ţ���������һ��ͬ�������࣬�����ӳ��߳�ִ��ֱ�����е��̶߳�����һ��״̬��
 * ���ã�1��ȷ��ĳ��������������Ҫ��������Դ����ʼ������ִ�У�
 * 2��ȷ��ĳ�������������������ķ���ִ����Ϻ���ִ�У�
 * 3���ȸ�ĳ�����������в����߶�׼���������ټ���ִ�С�
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
		//begin ��end˳��ǧ����д����Ȼ����������ˡ�
		begin.countDown();
		end.await();
		Long endSec = System.nanoTime();
		
		return endSec - beginSec;
	}
}
