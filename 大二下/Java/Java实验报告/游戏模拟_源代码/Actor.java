package 游戏模拟;

public class Actor {
	String name;
	int id;
	int blood;
	int attacknum;
	int defensenum;
	int state;
	
	Actor(){}
	Actor(int id,int blood,int attacknum,int defensenum,int state,String name){
		this.id=id;
		this.blood=blood;
		this.attacknum=attacknum;
		this.defensenum=defensenum;
		this.state=state;
		this.name=name;
	}
	
	void defense() {
		
	}
	
	boolean isdefense() {
		return true;
	}
	
	void attack(Actor a) {
		// TODO Auto-generated method stub
		if(this.name.equals(a.name)) {
			this.doubleattack(a);
		}
		else {
			this.generalattack(a);
		}
		if(this.state==1)
			System.out.println(this.name+this.id+this.state+"attack"+'!');
		else
			System.out.println(this.name+this.id+this.state+"defense"+'!');
	}
	
	void addBlood(int bloodnum) {
		this.blood=this.blood+bloodnum;
	}
	
    void generalattack(Actor a) {}
	void doubleattack(Actor a) {}
	
	int getBlood() {
		return this.blood;
	}
	void setBlood(int blood) {
		this.blood=blood;
	}
	String getName() {
		return this.name;
	}
	void setName(String name) {
		this.name=name;
	}
}
