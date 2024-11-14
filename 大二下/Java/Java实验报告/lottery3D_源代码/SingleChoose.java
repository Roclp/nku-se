package Lottery3D;

class SingleChooose extends Lottery{
	public int winnum;
	public int inputnum;
	SingleChooose() {}
	SingleChooose(int winnum){
		this.winnum =winnum;
		System.out.print("当前选择SingleChoose玩法，请输入000-999的整数：");
		this.inputnum=sc.nextInt();
	}
	public int getwinLevel() {
		if(winnum==inputnum)
			return 1040;
		else 
			return 0;
	}
}