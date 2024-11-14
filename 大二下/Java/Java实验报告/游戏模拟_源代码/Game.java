package 游戏模拟;
import java.util.Scanner;

public abstract class Game extends Actor {
	int actor_typenum;
	Actor a1;
	Actor a2;
	
	abstract Actor randomActor();
	abstract Actor setActor(String type);
	abstract void play();
}
