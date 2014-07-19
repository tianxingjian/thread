package cn.com.chp3;

import java.awt.Event;
import java.util.EventListener;

/**
 * <th>测试对象逸出<th>
 * 
 * <p>
 *   1、在构造方法内发布内部类，内部类隐藏外层类的this引用导致外部类逸出。
 *   2、不要在构造过程中是this引用逸出，ThisEscape还未构造完成就逸出，同时这种逸出可能会被外部线程所见
 * <p>
 * 发布和逸出：对象发布就是为了对象能够在当前作用域之外被访问，对象发布的方法有：1、定义public字段，2、发布一个对象时同时发布其包装的
 * 对象，如Set<Vector> vector也被发布了，3、将对象作为方法参数传递， 4、对象作为public方法返回给调用者
 * 对象逸出是指不应该发布的对象被发布，如内部状态被发布
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
						//内部类隐藏了外层类的this引用，造成外层类的逸出
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





