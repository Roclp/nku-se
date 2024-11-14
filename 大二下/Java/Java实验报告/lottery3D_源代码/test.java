package Lottery3D;
import java.util.Random;
import java.util.Scanner;
public class test{
	public static void main(String[] arg)  {
		Scanner sc=new Scanner(System.in);
		Random random=new Random();
		int winnum=0;
		while(true) {
			System.out.println("欢迎进入Lottery3D,请输入投注方式:");
			String type=sc.next();
			int []winnum1=random.ints(3).toArray();
			winnum=winnum1[0]*100+winnum1[1]*10+winnum1[2];
				if(type.equals("SingleChoose")) {
					System.out.println("本期中奖号码为："+winnum);
					SingleChooose s=new SingleChooose(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
				}
				else if(type.equals("OneDChoose")) {
					System.out.println("本期中奖号码为："+winnum);
					OneDChoose s=new OneDChoose(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
					}
				else if(type.equals("TowDChoose")) {
					System.out.println("本期中奖号码为："+winnum);
					TowDChoose s=new TowDChoose(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
				}
				else if(type.equals("GuessOneD")) {
					System.out.println("本期中奖号码为："+winnum);
					GuessOneD s=new GuessOneD(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
				}
				else if(type.equals("General")) {
					System.out.println("本期中奖号码为："+winnum);
					General s=new General(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
				}
				else if(type.equals("Sum")) {
					System.out.println("本期中奖号码为："+winnum);
					Sum s=new Sum(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
				}
				else if(type.equals("Package")) {
					System.out.println("本期中奖号码为："+winnum);
					Package s=new Package(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
				}
				else if(type.equals("Tractor")) {
					System.out.println("本期中奖号码为："+winnum);
					Tractor s=new Tractor(winnum);
					System.out.println("您获得的奖金为:"+s.getwinLevel());
				}
		}
	}
}

