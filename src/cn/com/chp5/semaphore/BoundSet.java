package cn.com.chp5.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * <th>Semaphore����<th>
 * 
 * <p>
 * ���ܣ�ͨ��Semaphore��Set��װ��һ���н�����������
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
public class BoundSet<T> {
	private final Set<T> set;
	private final Semaphore sem;

	public BoundSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		sem = new Semaphore(bound);
	}

	public boolean add(T o) throws InterruptedException {
		sem.acquire();
		boolean wasAdded = false;
		try {
			wasAdded = set.add(o);
			return wasAdded;
		} finally {
			if (!wasAdded)
				sem.release();
		}
	}

	public boolean remove(Object o) {
		boolean wasRemoved = set.remove(o);
		if (wasRemoved)
			sem.release();
		return wasRemoved;
	}
}
