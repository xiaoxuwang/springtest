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
		  doSome(a1); //这里
		  doSome(a2); //这里
		  doSome(b); //这里，如果下面的方法不是用抽象类作参数，比如用C c做参数，这里会有什么问题？
		  doSome(c); //这里

	}
	public static void doSome(A a) {//这里，用抽象类，为什么，LZ自己思考
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
	//C继承了抽象类A,必须实现A中的抽象方法doSomething(), 如果doSomething()方法不被abstract修饰，
	//doSomething()可以实现也可以不实现
	class C extends A{

		@Override
		public void doSomething() {
			// TODO Auto-generated method stub
			
		}
	 
	}



