package Lottery3D;


class Tractor extends Lottery{
	int winnum;
	Tractor() {}
	Tractor(int winnum){
		System.out.print("当前选择Tractor玩法，请等待是否中奖：");
		this.winnum =winnum;
	}
	public int getwinLevel() {
		int a=winnum/100;
		int b=winnum/10%10;
		int c=winnum%10;
		if(a+1==b&&b+1==c)
			return 65;
		else
			return 0;
	}
}
