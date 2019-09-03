package ThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//�з���ֵ�߳�
//1. ���� Callable �ӿڵ�ʵ���࣬��ʵ�� call() �������� call() ��������Ϊ�߳�ִ���壬�����з���ֵ��
//2. ���� Callable ʵ�����ʵ����ʹ�� FutureTask ������װ Callable ���󣬸� FutureTask �����װ�˸� Callable ����� call() �����ķ���ֵ��
//3. ʹ�� FutureTask ������Ϊ Thread ����� target �������������̡߳�
//4. ���� FutureTask ����� get() ������������߳�ִ�н�����ķ���ֵ��

public class CallableTest implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		return 0;
	}
	
	public static void main(String[] args) {
		CallableTest c =new CallableTest();
		FutureTask<Integer> f =new FutureTask<Integer>(c);
		Thread t =new Thread(f);
		t.start();

		try {
			System.out.println("resualt :"+f.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
