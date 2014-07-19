package cn.com.chp3;

import java.awt.Event;
import java.util.EventListener;

/**
 * <th>���Զ����ݳ�<th>
 * 
 * <p>
 *   1���ڹ��췽���ڷ����ڲ��࣬�ڲ�������������this���õ����ⲿ���ݳ���
 *   2����Ҫ�ڹ����������this�����ݳ���ThisEscape��δ������ɾ��ݳ���ͬʱ�����ݳ����ܻᱻ�ⲿ�߳�����
 * <p>
 * �������ݳ������󷢲�����Ϊ�˶����ܹ��ڵ�ǰ������֮�ⱻ���ʣ����󷢲��ķ����У�1������public�ֶΣ�2������һ������ʱͬʱ�������װ��
 * ������Set<Vector> vectorҲ�������ˣ�3����������Ϊ�����������ݣ� 4��������Ϊpublic�������ظ�������
 * �����ݳ���ָ��Ӧ�÷����Ķ��󱻷��������ڲ�״̬������
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-11</h1>
 */
class ThisEscape{
	private final String initBeforeEscape;
	private final int initAfterEscape;
	
	public ThisEscape(EventSource source){
		initBeforeEscape = "nimei";
		source.registerListener( 
				new EventListener() { 
					public void onEvent(Event e) { 
						//�ڲ���������������this���ã�����������ݳ�
						doSomething(e);
						ThisEscape.this.doSomething(e);
						System.out.println("initBeforeEscape:" + initBeforeEscape);
						System.out.println("initAfterEscape:" + initAfterEscape);
					} 
				}); 
		initAfterEscape = 23;
	}
	
	private void doSomething(Event e){
		System.out.println(e.key);
	}
}

class EventSource{
	
	public void registerListener(EventListener listener){
		
	}
	
}





