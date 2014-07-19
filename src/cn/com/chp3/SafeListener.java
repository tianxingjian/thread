package cn.com.chp3;

import java.awt.Event;
import java.util.EventListener;

/**
 * <th>解决对象构造过程中逸出的方法，先初始化完成然后通过工厂方法进行<th>
 * 
 * <p>
 *   通过先初始化再发布的方法避免对象在未构造完成时逸出
 * <p>
 * 发布和逸出：对象发布就是为了对象能够在当前作用域之外被访问，对象发布的方法有：1、定义public字段，2、发布一个对象时同时发布其包装的
 * 对象，如Set<Vector> vector也被发布了，3、将对象作为方法参数传递， 4、对象作为public方法返回给调用者
 * 对象逸出是指不应该发布的对象被发布，如内部状态被发布
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
		//事件监听里面的onEvent方法是只有在事件被触发的时候才会执行的，事件触发监听必须先注册监听
		listener = new EventListener() {
			public void onEvent(Event e) { 
				//doSomething写在外边是为了体现内部类含有外部类的this引用
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