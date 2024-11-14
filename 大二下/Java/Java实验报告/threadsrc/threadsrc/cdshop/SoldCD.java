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
					+"销售了"+num
					+"剩余"+count);
			return true;
		}
		else
		{
			System.out.println(new Date()+
			Thread.currentThread().getName()
			+this.cdName
			+"数量不足");
			return false;
		}
		
	}
	synchronized public void getin() {
		count=10;
		
		System.out.println(
				new Date()+
				Thread.currentThread().getName()
				+this.cdName
				+"进货");
		
	}
}
