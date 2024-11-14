package Lottery3D;


class Group extends Lottery{
	int winnum;
	int inputnum;
	Group() {}
	Group(int winnum){
		this.winnum =winnum;
		System.out.print("当前选择SingleChoose玩法，请输入000-999的整数：");
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
		if((a==b&&a!=c)||(a==c&&a!=b)||(b==c&&b!=a)) {//组选3
			if(zuhe(a,b,c))
				return 346;
			else
				return 0;
		}
		else if (a!=b&&b!=c&&a!=c){  //组选6
			if(this.zuhe(a,b,c))
				return 173;
			else
				return 0;
		}
		return 0;
	}
	
}
