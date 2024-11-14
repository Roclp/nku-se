package 游戏模拟;

public class Warrior extends Actor{
	Warrior(){
		this.blood=300;
		
	}
	Warrior(int id,int blood,int attacknum,int defensenum,int state)	{
		this.id=id;
		this.blood=blood;
		this.attacknum=attacknum;
		this.defensenum=defensenum;
		this.state=state;
	}
	@Override
	void attack(Actor a) {
		// TODO Auto-generated method stub
		if(this.name.equals(a.name)) {
			this.doubleattack(a);
		}
		else {
			this.generalattack(a);
		}
	}

	@Override
	void generalattack(Actor a) {
		// TODO Auto-generated method stub
		a.blood=a.blood-this.attacknum/a.defensenum;
	}

	@Override
	void doubleattack(Actor a) {
		// TODO Auto-generated method stub
		a.blood=a.blood-2*this.attacknum/a.defensenum;
	}
}
