package cn.com.chp3;

/**
 * <th>���Զ��̵߳Ŀɼ���<th>
 * 
 * <p>
 * Ԥ�ڽ�������Number������11��Ҳ������0{���0�ͱ�ʾ���̶߳�Number�޸Ĳ��ɼ�}
 * <p>
 * 
 * <p>
 * �ɼ��ԣ��ɼ�����ָĳ������ֵ��д�����û�������޸ĵ�����¶�ȡ�������ʱֵ�������
 * ���߳̿ɼ������⣺��A�߳������ԭ�Ӳ����޸ı���ֵ���߳�B������Ϊָ���������ԭ���A���޸Ĳ������ɼ�
 * java��������
 * �������ʹ��������ܻ�Բ����������򡣱������ʹ�������������ʱ�����������������ԣ��������ʹ���������ı��������������ϵ������������ִ��˳��
 * ע�⣬������˵�����������Խ���Ե�����������ִ�е�ָ�����к͵����߳���ִ�еĲ�������ͬ������֮��Ͳ�ͬ�߳�֮������������Բ����������ʹ��������ǡ�
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
				//yield������ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̣߳���ǰ�߳��ó�cpu�����ǲ�����
				//�ó������������»�ȡ��
				//sleep()��������ͣһ��ʱ�䣬�����ͷ�cpu
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
