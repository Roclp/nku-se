package cdshop;

public class CDShop {

	SoldCD[] soldcds=new SoldCD[10];
	CDShop() {
		for(int i=0;i<10;i++) {
			soldcds[i]=new SoldCD();
		}
	}
	public static void main(String[] arg) throws InterruptedException {
		new CDshopControlThread(new CDShop()).start();
		Thread.sleep(1000*20);
	}
}
