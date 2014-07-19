package cn.com.chp5.semaphore;

import java.util.concurrent.Semaphore;

/**
 * <th>Semaphore����<th>
 * 
 * <p>
 * Ԥ�ڽ�����߳̿����������������ͬʱִ��aquireAction���߳����count��
 * <p>
 * 
 * <p>
 * �ź������ã��ź�����������ͬʱ������Դ��ִ�е�ǰ�������̸߳���
 * �ź���Ӧ�ó�����1��ʵ����Դ�أ������ݿ����ӳأ�
 * 2��������collection ת�����н������collection
 * 3�������ź�����Semaphore�������ʵ�ֻ����������ҿ�����һ���߳�����������һ���߳��ͷţ��ǽ��������һ�ַ���
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
		System.out.println(Thread.currentThread() + "[׼�������ź���!]");
		aquireAction();
		System.out.println(Thread.currentThread() + "[׼���ͷ��ź�����]");
		releaseAction();
	}
	
	public void aquireAction(){
		 try {  
             //acquire:��ã����淽���ǻ�ȡ�ź���
             se.acquire();
             System.out.println(Thread.currentThread() + "[�ź�������ɹ�����ǰaquireAction()������Ϊ��" + (count - se.availablePermits()) + "]");
             Thread.sleep((long)Math.random()*10000);  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }
	}
	
	public void releaseAction(){
		se.release();
		System.out.println(Thread.currentThread() + "[�ź����ͷųɹ�!]");
	}
}
