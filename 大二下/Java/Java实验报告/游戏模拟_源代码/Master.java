package 游戏模拟;

public class Master extends Actor{
	Master(){
		this.blood=100;
		
	}
	Master(int id,int blood,int attacknum,int defensenum,int state)	{
		this.id=id;
		this.blood=blood;
		this.attacknum=attacknum;
		this.defensenum=defensenum;
		this.state=state;
	}
	@Override
	void attack(Actor a) {
		// TODO Auto-generated method stub
		if(this.getName().equals(a.getName())) {
			this.generalattack(a);
		}
		else {
			this.doubleattack(a);
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
