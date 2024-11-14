package Lottery3D;


class OneDChoose extends Lottery{
	int winnum;
	String inputchar;
	OneDChoose() {}
	OneDChoose(int winnum){
		this.winnum =winnum;
		System.out.print("当前选择SingleChoose玩法，请输入确定位置的一个数字，其他位输入*，例如，如果确定个位数为2，请输入**2：");
		this.inputchar=sc.next();
	}
	public int getwinLevel() {
		int a=winnum/100;
		int b=winnum/10%10;
		int c=winnum%10;
		int []winnum= {a,b,c};
		for(int i=0;i<3;i++) {
			if(inputchar.charAt(i)!='*'&&winnum[i]+'0'==inputchar.charAt(i)) {
				return 10;
			}
		}
		return 0;
	}
	
}