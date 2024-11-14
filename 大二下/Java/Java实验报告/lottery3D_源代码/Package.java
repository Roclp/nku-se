package Lottery3D;

class Package extends Lottery{
	int winnum;
	int inputnum;
	Package() {}
	Package(int winnum){
		this.winnum =winnum;
		System.out.print("当前选择Package玩法，请输入000-999的整数：");
		this.inputnum=sc.nextInt();
	}
	boolean zuhe (int a,int b,int c) {
		if(a*100+b*10+c==winnum||a*100+c*10+b==winnum||b*100+a*10+c==winnum||b*100+c*10+a==winnum||c*100+a*10+b==winnum||c*100+b*10+a==winnum)
			return true;
		else
			return false;
	}
	public int getwinLevel() {
		int a=inputnum/100;
		int b=inputnum/10%10;
		int c=inputnum%10;
		if((a==b&&a!=c)||(a==c&&a!=b)||(b==c&&b!=a)) {//包选3
			if(winnum==inputnum)   //包选3全中
				return 693;
			else if(zuhe(a,b,c))   // 包选3组中
				return 173;
		}
		else if (a!=b&&b!=c&&a!=c){  //包选6
			if(winnum==inputnum)   //包选6全中
				return 606;
			else if(zuhe(a,b,c))   // 包选6组中
				return 86;
		}
		return 0;
	}
}