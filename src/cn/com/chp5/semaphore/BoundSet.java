package cn.com.chp5.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * <th>Semaphore例子<th>
 * 
 * <p>
 * 功能：通过Semaphore将Set包装成一个有界阻塞的容器
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
