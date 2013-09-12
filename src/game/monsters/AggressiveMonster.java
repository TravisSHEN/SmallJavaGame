package game.monsters;
/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.Unit;
import game.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AggressiveMonster extends Monsters{
	
	/**
	 *  construct Aggressive monsters
	 * @param x the location
	 * @param y the location
	 * @param name the name of monsters
	 * @param monster the image of monster
	 * @param maxHealth the max health of monster
	 * @param damage the damage that monster could make
	 * @param speed the speed of monster
	 * @param coolDown the minimum time between each attacks made by monster
	 * @throws SlickException
	 */
	public AggressiveMonster(double x, double y, String name, Image monster,
			int maxHealth, int damage, double speed, int coolDown)
			throws SlickException {
		super(x, y, name, monster, maxHealth, damage, speed, coolDown);
	}
	
	@ Override
	/**
	 *  control the movement of monster
	 *  and attack player.
	 */
	public void update(World world, int delta)
			throws SlickException
	{
			double distx = world.getPlayer().getX()- this.getX();
			double disty = world.getPlayer().getY() - this.getY();
			double distTotal = Math.sqrt(distx*distx + disty*disty);
			double dx = (distx / distTotal);
			double dy = (disty / distTotal);

			Unit player = scanforPlayer(world.getPlayer(), distTotal);
			
			// if find player, then it will move toward player and attack
			if(player != null){
				this.move(this, world, dx, dy, delta, this.speed);
						
				if(Math.abs(distTotal) <= this.attackRange){
						this.attack(player, delta);
						return;
				}
			}
	}
}
