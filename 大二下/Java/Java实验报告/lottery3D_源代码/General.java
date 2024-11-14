package Lottery3D;
import java.util.Scanner;

class General extends Lottery{
	int winnum;
	String input;
	Scanner sc=new Scanner(System.in);
	General() {}
	General(int winnum){
		this.winnum =winnum;
		this.input=sc.next();
	}
	
	public int getwinLevel() {
		int a=winnum/100;
		int b=winnum/10%10;
		int c=winnum%10;
		int []winnum= {a,b,c};
		int count=0;
		for(int i=0;i<3;i++) {
			if(input.charAt(i)=='*') {
				count++;
			}
		}
		int count_=0;
		if(count==0) {  // 通选1
			for(int i=0;i<3;i++) {
				if(input.charAt(i)==winnum[i]+'0') {
					count_++;
				}
			}
			if(count_==3)
				return 470;
		}
		else if(count==1) {   //通选2
			if(count_==2)
				return 21;
		}
		return 0;
	}
	
}