package game.villagers;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.*;
import game.item.Item;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Aldric extends Villager {
	

	public Aldric()
		 	throws SlickException
	{
			super(540, 612, "Prince Aldric", new Image("assets/units/prince.png"));
	}

	@Override
	public void interact(World world, Unit target) {
		// Player interacting with this Villager
		if(target instanceof Player){
			for(Item i: world.getPlayer().getInventory()){
				if(i.getName() == "Elixir"){
					this.setText("The elixir! My father is cured! Thankyou!");
					world.getPlayer().getInventory().remove(i);
					return;
				}
			}
			this.setText("Please seek out the Elixir of Life to cure the king.");
		}
	 }
}
