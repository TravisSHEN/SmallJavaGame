package game.item;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import org.newdawn.slick.Image;
import game.*;

public class Item extends Unit{
	
	
	/**
	 * create item objects
	 * @param x the location of item on map
	 * @param y the location of item on map
	 * @param name the name of item
	 * @param item the image of item
	 */
	public Item(double x, double y, String name, Image item){
		this.x = x;
		this.y = y;
		this.name = name;
		this.unit = item;
	}
}