package cn.com.chp5.barrier;

/**
 * <th>Barrier����<th>
 * 
 * <p>
	������ı�BarrierDemo barrier = new BarrierDemo(5, new Runnable())�еĲ���5�ᷢ�֡�ִ�д���������ӡ������һ��������
	��Ϊÿ��һ���߳������ﵽ�����ʱ��ͻ�����ִ�У�Ȼ��Barrier resetֵ�������߳̽�����������һ���߳�ͨ�����Barrier��
	���5�ı�Ϊ��thNames���ȵ����ӣ��������������Ϊ�����һ���߳����ﲻ�����ֵ������Щ�߳�һֱ�ڵȴ�״̬
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
				System.out.println("ִ�д����� " + ++count);
			}
		});
		
		barrier.doAction(thNames);
	}

}
