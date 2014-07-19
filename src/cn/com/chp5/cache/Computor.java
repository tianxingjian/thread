package cn.com.chp5.cache;

public interface Computor<V, T> {
	T compute(final V arg);
}
