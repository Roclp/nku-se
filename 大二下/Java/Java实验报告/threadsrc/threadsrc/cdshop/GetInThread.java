package cdshop;

public class GetInThread extends Thread {

	SoldCD cd;
	GetInThread(SoldCD cd){
		this.cd=cd;
		this.setName(this.getName()+cd.cdName+"�����߳�");
	}
	@Override
	public void run() {
		try {
			while(true) {
		synchronized(cd) {
			cd.wait(1000);
			cd.getin();			
		}
			}
		}
		catch(Exception e) {
			
		}
	}
}
