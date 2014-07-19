package cn.com.chp5.barrier;

/**
 * <th>Barrier例子<th>
 * 
 * <p>
	结果：改变BarrierDemo barrier = new BarrierDemo(5, new Runnable())中的参数5会发现“执行次数：”打印次数不一样，这是
	因为每有一组线程数量达到这个数时候就会往下执行，然后Barrier reset值，其他线程进入后重新组成一组线程通过这个Barrier。
	如果5改变为非thNames长度的因子，程序会阻塞，因为有最后一组线程数达不到这个值导致这些线程一直在等待状态
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-16</h1>
 */
public class BarrierTest {
	private static String []thNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}; 
	public static void main(String[] args) {

		BarrierDemo barrier = new BarrierDemo(5, new Runnable() {
			int count = 0;
			@Override
			public void run() {
				System.out.println("执行次数： " + ++count);
			}
		});
		
		barrier.doAction(thNames);
	}

}
