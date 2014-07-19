package cn.com.chp3;

/**
 * <th>测试多线程的可见性<th>
 * 
 * <p>
 * 预期结果：输出Number可能是11，也可能是0{输出0就表示主线程对Number修改不可见}
 * <p>
 * 
 * <p>
 * 可见性：可见性是指某个变量值被写入后，在没有其他修改的情况下读取这个变量时值总是相等
 * 多线程可见性问题：在A线程里面非原子操作修改变量值，线程B可能因为指令重排序等原因对A的修改操作不可见
 * java重排序概念：
 * 编译器和处理器可能会对操作做重排序。编译器和处理器在重排序时，会遵守数据依赖性，编译器和处理器不会改变存在数据依赖关系的两个操作的执行顺序。
 * 注意，这里所说的数据依赖性仅针对单个处理器中执行的指令序列和单个线程中执行的操作，不同处理器之间和不同线程之间的数据依赖性不被编译器和处理器考虑。
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-11</h1>
 */
public class NoVisibility {
	
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread{
		public void run(){
			while(!ready){
				//yield方法暂停当前正在执行的线程对象，并执行其他线程，当前线程让出cpu，但是不代表
				//让出后又马上重新获取到
				//sleep()方法是暂停一段时间，但不释放cpu
				Thread.yield();
			}
			System.out.println("Number:" + number);		
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 11;
		ready = true;
	}

}
