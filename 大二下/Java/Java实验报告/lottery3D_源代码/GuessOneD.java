package Lottery3D;

class GuessOneD extends Lottery{
	public int winnum;
	public int guess;
	GuessOneD() {}
	GuessOneD(int winnum){
		this.winnum =winnum;
		System.out.print("当前选择GuessOneD玩法，请输入0-9的整数：");
		this.guess=sc.nextInt();
	}
	public int getwinLevel() {
		int a=winnum/100;
		int b=winnum/10%10;
		int c=winnum%10;
		int [] winnum= {a,b,c};
		int count=0;
		for(int i=0;i<3;i++) {
			if(winnum[i]==guess) {
				count++;
			}
		}
		if(count==1)
			return 2;
		else if(count==2)
			return 12;
		else if(count==3)
			return 230;
		return 0;
	}
	
}
