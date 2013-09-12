package game.monsters;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PassiveMonster extends Monsters{

	public PassiveMonster(double x, double y, String name, Image monster,
			int maxHealth, int damage, double speed, int coolDown)
			throws SlickException {
		super(x, y, name, monster, maxHealth, damage, speed, coolDown);
		// TODO Auto-generated constructor stub
	}

	// timer which control the movement of Giant Bats 
	private int timer = 0;
	// if monster is not attacked by player in 5 seconds
	private int timer2;

	// directions control the direction that passive monster moves
	private int[] direction = {-1,0,1};
	
	// give the random index direction
	private int randomIntX = 0;
	private int randomIntY = 0;

	
	@Override
	/**
	 * update the status of monsters
	 * control their movement
	 * and attack
	 */
	public void update(World world, int delta)
			throws SlickException
	{
		double distx = this.getX() - world.getPlayer().getX();
		double disty = this.getY() - world.getPlayer().getY();
		double distTotal = Math.sqrt(distx*distx + disty*disty);
		double dx = (distx / distTotal) ;
		double dy = (disty / distTotal) ;
		

		// if player attacks it 
		// then it will run away.
		// if player not attack it,
		// then after 5000(ms) it will wonder around
		// otherwise, it will wonder around
		// the world
		if(distTotal <= this.attackRange){
			
			this.move(this, world, dx, dy, delta, this.speed);
			timer2 = 5000;
			
		} else {
			if(timer > 0 && timer2 < 0){
			
				// decrease timer until 3000(ms) finished
				this.timer -= delta;
				this.move(this, world, direction[randomIntX], direction[randomIntY], delta, this.speed);
				
			} else {
			
				this.timer = 3000;
				this.timer2 -= delta;
				randomIntX = (int)(Math.random()*3);
				randomIntY = (int)(Math.random()*3);

				//System.out.println(randomIntX + " " + randomIntY);
			}
		}
	}
	
}
