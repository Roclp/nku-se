package Lottery3D;

class Sum extends Lottery{
	int winnum;
	int inputnum;
	Sum() {}
	Sum(int winnum){
		this.winnum =winnum;
		System.out.print("当前选择Sum玩法，请输入0-27的整数：");
		this.inputnum=sc.nextInt();
	}
	public int getwinLevel() {
		int a=winnum/100;
		int b=winnum/10%10;
		int c=winnum%10;
		int num=a+b+c;
		if(num==inputnum) {   //中奖
			if(num==0||num==27)
				return 1040;
			else if(num==1||num==26)
				return 345;
			else if(num==2||num==25)
				return 172;
			else if(num==3||num==24)
				return 104;
			else if(num==4||num==23)
				return 69;
			else if(num==5||num==22)
				return 49;
			else if(num==6||num==21)
				return 37;
			else if(num==7||num==20)
				return 29;
			else if(num==8||num==19)
				return 23;
			else if(num==9||num==18)
				return 19;
			else if(num==10||num==17)
				return 16;
			else if(num==11||num==16)
				return 15;
			else if(num==12||num==15)
				return 15;
			else if(num==13||num==14)
				return 14;
		}
	return 0;	
	}
}

