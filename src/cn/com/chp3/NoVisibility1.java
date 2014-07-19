package cn.com.chp3;

/**
 * <th>测试多线程的可见性<th>
 * 
 * <p>
 *   运行结果：多核处理器进行运行时，输出结果可能是１　０，０　１，　０　０　，１　１
 * <p>
 * 
 * <p>
 * 可见性：可见性是指某个变量值被写入后，在没有其他修改的情况下读取这个变量时值总是相等
 * 多线程可见性问题：在A线程里面非原子操作修改变量值，线程B可能因为指令重排序等原因对A的修改操作不可见
 * java重排序概念：
 * 编译器和处理器可能会对操作做重排序。编译器和处理器在重排序时，会遵守数据依赖性，编译器和处理器不会改变存在数据依赖关系的两个操作的执行顺序。
 * 注意，这里所说的数据依赖性仅针对单个处理器中执行的指令序列和单个线程中执行的操作，不同处理器之间和不同线程之间的数据依赖性不被编译器和处理器考虑。
 * <p>
 * 
 * @author tianxingjian <h1>date: 2014-01-11</h1>
 */
public class NoVisibility1 {

	
	static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10000; i++) {
            x=y=a=b=0;
            Thread one = new Thread() {
                public void run() {
                    a = 1;
                    x = b;
                }
            };
            Thread two = new Thread() {
                public void run() {
                    b = 1;
                    y = a;
                }
            };
            one.start();
            two.start();
            one.join();
            two.join();
            System.out.println(x + " " + y);
        }
    } 

}
