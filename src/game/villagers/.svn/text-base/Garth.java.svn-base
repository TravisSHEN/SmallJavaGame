package game.villagers;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.*;
import game.item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Garth extends Villager{

	public Garth()
		 	throws SlickException
	{
			super(828, 828, "Garth", new Image("assets/units/peasant.png"));
	}

	@Override
	public void interact(World world, Unit target) {
		
		if(target instanceof Player){
			for(Item i: world.getItems()){
				//System.out.println(!world.getPlayer().getInventory().contains(i));
				if(i.getName().equals("Amulet") &&
						!world.getPlayer().getInventory().contains(i)){
				
					this.setText("Find the Amulet of Vitality, across the river to the west.");
					return;
				
				} else if(i.getName().equals("Sword") &&
						!world.getPlayer().getInventory().contains(i)){
				
					this.setText( "Find the Sword of Strength - cross the river and back" +
						", on the east side.");
					return;
				
				} else if(i.getName().equals("Tome") &&
						!world.getPlayer().getInventory().contains(i)){
				
					this.setText("Find the Tome of Agility, in the Land of Shadows.");
					return; 				
				}
			}
		
			this.setText( "You have found all the treasure I know of.");
					
				
		}
	}
}
