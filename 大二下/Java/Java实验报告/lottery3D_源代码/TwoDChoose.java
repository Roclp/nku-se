package Lottery3D;


class TowDChoose extends Lottery{
	int winnum;
	String inputchar;
	TowDChoose() {}
	TowDChoose(int winnum){
		this.winnum =winnum;
		System.out.print("当前选择SingleChoose玩法，请输入两个确定位置的数字，其他位输入*，例如，如果确定个位和十位数为2，请输入*22：");
		this.inputchar=sc.next();
	}
	public int getwinLevel() {
		int a=winnum/100;
		int b=winnum/10%10;
		int c=winnum%10;
		int []winnum= {a,b,c};
		int count=0;
		for(int i=0;i<3;i++) {
			if(inputchar.charAt(i)!='*'&&winnum[i]+'0'==inputchar.charAt(i)) {
				count++;
			}
		}
		if (count==2)
			return 104;
		else
			return 0;
	}
	
}