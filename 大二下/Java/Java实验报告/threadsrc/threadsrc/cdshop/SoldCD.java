package cdshop;

import java.util.Date;

public class SoldCD extends CD {

	public int count;
	public SoldCD() {
		count=10;
	}
	synchronized public boolean sold(int num) {
		if(count>=num) {
			count-=num;
			System.out.println(
					new Date()+
					Thread.currentThread().getName()
					+this.cdName
					+"������"+num
					+"ʣ��"+count);
			return true;
		}
		else
		{
			System.out.println(new Date()+
			Thread.currentThread().getName()
			+this.cdName
			+"��������");
			return false;
		}
		
	}
	synchronized public void getin() {
		count=10;
		
		System.out.println(
				new Date()+
				Thread.currentThread().getName()
				+this.cdName
				+"����");
		
	}
}
