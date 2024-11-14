package cdshop;

public class CDshopControlThread extends Thread{

	CDShop shop;

	public CDshopControlThread(CDShop shop) {
		super();
		this.shop = shop;
		this.setDaemon(true);
	}
	@Override
	public void run() {
		for(SoldCD cd:shop.soldcds) {
			new GetInThread(cd).start();
		}
		int soldthreadcount=2;
		for(int i=0;i<soldthreadcount;i++) {
			new SoldThread(shop.soldcds).start();
		}
	}
	
}
