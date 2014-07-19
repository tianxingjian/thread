package cn.com.chp5.cache;

import java.math.BigInteger;

public class MemoTest {
	private static final Computor<BigInteger, BigInteger> c = new Computor<BigInteger, BigInteger>() {
		public BigInteger compute(BigInteger arg) {
			System.out.println(Thread.currentThread() + "计算：【" + arg + "】");
			return arg.multiply(new BigInteger("2"));
		}
	};
	private static final Computor<BigInteger, BigInteger> cache = new Memorizer<BigInteger, BigInteger>(c);

	public static void main(String[] args) {
		String []threadName = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
		for(int i = 0; i < threadName.length; i++){
			final int index = (int)(Math.random() * 10);
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					//System.out.println(index);
					BigInteger inte = new BigInteger(String.valueOf(index));
					System.out.println("取值结果：" + cache.compute(inte));
				}
			}, threadName[i]);
			th.start();
		}
	}
}
