package game.monsters;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Monsters extends Unit{
	
	private final double dectectRange = 150;

	public Monsters(double x, double y, String name, Image monster, int maxHealth,int damage, double speed ,int coolDown)
		throws SlickException
	{
		this.x = x;
		this.y = y;
		this.name = name;
		this.unit = monster;
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.damage = damage;
		this.speed = speed;
		this.coolDown = coolDown;
	}
	
	public Unit scanforPlayer(Unit player,double distTotal){
		
		if(distTotal < dectectRange){
			return player;
		}
		return null;
	}
	
}
