package 游戏模拟;
import java.util.Random;
import java.util.Scanner;
public class Game2 extends Game{
	int Actorcount;
	Random r=new Random();
	Game2(){}

	@Override
	Actor randomActor() {
		// TODO Auto-generated method stub
		Actor a;
		int id=r.nextInt(10);
		int attacknum=r.nextInt(50)+5;
		int defensenum=r.nextInt(5)+1;
		boolean isdefense=r.nextBoolean();
		int state;
		if(isdefense)
			state=1;
		else
			state=0;
		boolean isWarrior =r.nextBoolean();
		if(isWarrior)
			a=new Actor(id,300,attacknum,defensenum,state,"warrior");
		else
			a=new Actor(id,100,attacknum,defensenum,state,"Master");
		return a;
	}

	@Override
	Actor setActor(String type) {
		// TODO Auto-generated method stub
		int id=r.nextInt(10);
		int attacknum=r.nextInt(50)+5;
		int defensenum=r.nextInt(5)+1;
		if(type.equals("Warrior")) {
			Actor a=new Actor(id,300,attacknum,defensenum,0,"Warrior");
			return a;
		}
		else if(type.equals("Master")) {
			Actor a=new Actor(id,100,attacknum,defensenum,0,"Master");
			return a;
		}
		else 
			return null;
	}

	@Override
	void play() {
		// TODO Auto-generated method stub
		System.out.println("请输入您选择的角色：Warrior 或 Master");
		Scanner sc=new Scanner(System.in);
		String type=sc.next();
		a1=setActor(type);
		a2=randomActor();
		System.out.println("请您选择操作：Defense 或者 Attack");
		String option=sc.next();
		a1.attack(a2);
		a2.attack(a2);
     	if(option.equals("attack"))
			a1.state=1;
		else
			a1.state=0;
		if(a1.blood>a2.blood) {
			System.out.println("you win!"+a1.name+a1.id+"blood"+':'+a1.blood);
		}
		else if(a1.blood==a1.blood) {
			System.out.println("drew!"+a1.name+a1.id+"blood"+':'+a1.blood);
		}
		else
			System.out.println("you lost!"+a1.name+a1.id+"blood"+':'+a1.blood);
	}

}
