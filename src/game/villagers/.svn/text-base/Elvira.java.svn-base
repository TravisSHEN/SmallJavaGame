package game.villagers;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Elvira extends Villager{

	public Elvira()
		 	throws SlickException
	{
			super(900,540,"Elvira", new Image("assets/units/shaman.png"));
	}

	@Override
	public void interact(World world, Unit target) {

		if(target instanceof Player){
			if(world.getPlayer().getHealth() >= world.getPlayer().getMaxHealth()){
				setText("Return to me if you ever need healing.");
			} else {
				world.getPlayer().setHealth(world.getPlayer().getMaxHealth());
				setText("You're looking munch healthier now.");
			}
		}
	}
}
