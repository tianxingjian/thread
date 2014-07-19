package cn.com.chp3;

import java.awt.Event;
import java.util.EventListener;

/**
 * <th>���������������ݳ��ķ������ȳ�ʼ�����Ȼ��ͨ��������������<th>
 * 
 * <p>
 *   ͨ���ȳ�ʼ���ٷ����ķ������������δ�������ʱ�ݳ�
 * <p>
 * �������ݳ������󷢲�����Ϊ�˶����ܹ��ڵ�ǰ������֮�ⱻ���ʣ����󷢲��ķ����У�1������public�ֶΣ�2������һ������ʱͬʱ�������װ��
 * ������Set<Vector> vectorҲ�������ˣ�3����������Ϊ�����������ݣ� 4��������Ϊpublic�������ظ�������
 * �����ݳ���ָ��Ӧ�÷����Ķ��󱻷��������ڲ�״̬������
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-11</h1>
 */
public class SafeListener {
	private EventListener listener;
	private final String initBeforeEscape;
	private final int initBeforeEscape1;
	private SafeListener(){
		initBeforeEscape = "nimei";
		//�¼����������onEvent������ֻ�����¼���������ʱ��Ż�ִ�еģ��¼���������������ע�����
		listener = new EventListener() {
			public void onEvent(Event e) { 
				//doSomethingд�������Ϊ�������ڲ��ຬ���ⲿ���this����
				doSomething(e);
				System.out.println("initBeforeEscape:" + initBeforeEscape);
				System.out.println("initBeforeEscape1:" + initBeforeEscape1);
			} 
		};
		initBeforeEscape1 = 23;
	}
	
	private void doSomething(Event e){
		System.out.println(e.key);
	}
	
	public static SafeListener getInstance(EventSource1 source){
		SafeListener safe = new SafeListener();
		source.registerListener(safe.listener);
		return safe;
	}
	
}

class EventSource1{
	
	public void registerListener(EventListener listener){
		
	}
	
}