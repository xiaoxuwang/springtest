package com;
/**
 * Author:xiaoxu.wang
 * Date:2010-11-15
 * Des:test2
 */
public class test2 {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a1 = new B();
		  A a2 = new C();
		  B b = new B();
		  C c = new C();
		  doSome(a1); //����
		  doSome(a2); //����
		  doSome(b); //����������ķ��������ó�������������������C c���������������ʲô���⣿
		  doSome(c); //����

	}
	public static void doSome(A a) {//����ó����࣬Ϊʲô��LZ�Լ�˼��
		  a.doSomething();
	}


}
abstract class A {
	  public abstract void doSomething();
	  public void drink(String drink) {
		  System.out.println("I like drinking "+drink);
	  }
	}

	class B extends A {
	  public void doSomething() {
	  System.out.println("do B");
	  }
	}
	//C�̳��˳�����A,����ʵ��A�еĳ��󷽷�doSomething(), ���doSomething()��������abstract���Σ�
	//doSomething()����ʵ��Ҳ���Բ�ʵ��
	class C extends A{

		@Override
		public void doSomething() {
			// TODO Auto-generated method stub
			
		}
	 
	}



