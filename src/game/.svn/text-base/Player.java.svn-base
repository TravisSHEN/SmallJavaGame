package game;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import java.util.ArrayList;

import game.RPG;
import game.item.Item;
import game.monsters.Monsters;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


/** represent the player
 */
public class Player extends Unit{
	
	private Image panel;
	
	private final double initialX = 756;
	private final double initialY = 684;
	
	private ArrayList<Item> inventory;
	
	/** initialize player's features
	 * 
	 * @throws SlickException
	 */
	public Player()
	throws SlickException{
		
    	// initialize player
		this.x = initialX;
		this.y = initialY;
		this.name = "Mao";
		this.unit = new Image("assets/units/player.png");
		this.maxHealth = 100;
		this.health = this.maxHealth;
		this.damage = 26;
		this.coolDown = 600;
		panel = new Image("assets/panel.png");
		inventory = new ArrayList<Item>();
    
	}
	
    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(World world,double dir_x, double dir_y, int delta)
    throws SlickException
    {
    	if(this.getHealth() <= 0){
    		this.die(world);
    		return;
    	}
    	this.move(this, world, dir_x, dir_y, delta, speed);
    	
    	// find monster and attack
    	// if monster is dead, then remove
    	// this monster from world
    	Monsters monster = scanforMonsters(world);   	
		
		if(monster != null){					
			this.attack(monster, delta);
	    	if(monster.getHealth() <= 0){
	    		world.getMonsters().remove(monster);
	    		return;
	    	}
			return;
		}
		
		// scan for items and 
		// equip them
		// also remove them from world
		Item item = scanforItems(world);
		
		if(item != null){
			inventory.add(item);
			world.getItems().remove(item);
			if(item.getName() == "Amulet"){
				this.maxHealth += 40;
			} else if(item.getName() == "Sword"){
				this.damage += 10;
			} else if(item.getName() == "Tome"){
				this.coolDown -= 200;
			} else if(item.getName() == "Elixir"){
				
			}
		}
    	

    }
    
    /** 
     *  scan for monster
     * @param world 
     * @return monster if found
     */
	public Monsters scanforMonsters(World world){
		
		for(Monsters m: world.getMonsters()){
			double distTotal = Math.abs(World.distanceTo(this.getX(), this.getY(), m));
			if(distTotal < this.attackRange){
				return m;
			}
		}
		return null;
	}
	
	/**
	 * scan for item on map and pick up them
	 * @param world
	 * @return
	 */
	public Item scanforItems(World world){
		for(Item i: world.getItems()){
			double distTotal = Math.abs(World.distanceTo(this.getX(), this.getY(), i));
			if(distTotal < this.pickRange){
				return i;
			}
		}
		return null;
	}
    
	/**
	 * if player die, then return to
	 * village with full health
	 * @param world
	 */
	public void die(World world) {
		this.x = 950;
		this.y = 590;
		this.health = this.getMaxHealth();
	}
	
	public ArrayList<Item> getInventory(){
		return this.inventory;
	}
    
	
	
    /** Renders the player's status panel.
     * @param g The current Slick graphics context.
     */
    public void renderPanel(Graphics g)
    {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.screenheight - RPG.panelheight);

        // Display the player's health
        text_x = 15;
        text_y = RPG.screenheight - RPG.panelheight + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = Math.max(0, this.getHealth()) + "/" + this.getMaxHealth();                               

        bar_x = 90;
        bar_y = RPG.screenheight - RPG.panelheight + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = (float) Math.max(0, this.getHealth()) / this.getMaxHealth();                        
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = Integer.toString(this.getDamage());                                    // TODO: Damage
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = Integer.toString(this.getCoolDown());                                    // TODO: Cooldown
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.screenheight - RPG.panelheight + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.screenheight - RPG.panelheight
            + ((RPG.panelheight - 72) / 2);
        for(Item i: this.getInventory())               // TODO
        {
        	i.getUnit().draw(inv_x, inv_y);
        	inv_x += 72;
        }
    }

}
