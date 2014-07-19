package cn.com.chp5.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorizer<V,T> implements Computor<V, T>{

	private final ConcurrentMap<V, Future<T>> cache = new ConcurrentHashMap<V, Future<T>>();
	private final Computor<V, T> computor;
	
	public Memorizer(Computor<V, T> c){
		this.computor = c;
	}
	
	@Override
	public T compute(final V arg) {
		System.out.println(Thread.currentThread() + "Q£º¡¾" + arg + "¡¿");
		System.out.println(Thread.currentThread() + "A£º¡¾" + arg + "¡¿");
		System.out.println(Thread.currentThread() + "B£º¡¾" + arg + "¡¿");
		Future<T> f = cache.get(arg);
		T result = null;
		if(f == null){
			Callable cal = new Callable<T>() {
				@Override
				public T call() throws Exception {
					return computor.compute(arg);
				}
			};
			FutureTask<T> ft= new FutureTask<T>(cal);
			f = cache.putIfAbsent(arg, ft);
			if(f == null){
				f = ft;
				ft.run();
			}
		}
		
		try {
			result = f.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
