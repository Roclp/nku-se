package cdshop;

import java.util.Date;
import java.util.Random;

public class SoldThread extends Thread {

	SoldCD[] cds;

	public SoldThread(SoldCD[] cds) {
		super();
		this.cds = cds;
		this.setName(this.getName()+"�����߳�");
	}

	@Override
	public void run() {
		while (true) {
			Random r = new Random();
			int index = r.nextInt(cds.length);
			SoldCD cd = cds[index];
			int num = r.nextInt(6);
			try {
				synchronized (cd) {
					while (true) {
						boolean solded = cd.sold(num);
						if (solded) {
							break;
						} else {
							if (r.nextBoolean()) {
								System.out.println(
										new Date() + Thread.currentThread().getName() + cd.cdName + "�������㣬������");
								break;
							} else {
								System.out.println(
										new Date() + Thread.currentThread().getName() + cd.cdName + "�������㣬�����Ⱥ�,���ѽ����߳�");
								cd.notifyAll();
								cd.wait(r.nextInt(200));
							}
						}
					}
				}
				int sleep=r.nextInt(200);
				System.out.println(
						new Date() + Thread.currentThread().getName() +"˯��ʱ��"+sleep);
				this.sleep(sleep);
			} catch (Exception e) {

			}
		}
	}
}
