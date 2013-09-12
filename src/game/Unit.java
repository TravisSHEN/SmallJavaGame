package game;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Unit{
	/** the position of unit in the world*/
	protected double x,y;
	/** the name of unit */
	protected String name;
	/** the image of unit */
	protected Image unit;
	
	// status
	
	// the maximum health of unit
	protected int maxHealth;
	// the current health of unit
	protected int health;
	// the damage made by unit
	protected int damage;
	
	// within attackRange, attacks occur
	protected int attackRange = 50;
	// within this range, player could pick up item
	protected int pickRange = 30;
	
	// the minimum time between attacks
	protected int coolDown;
	// the time until next attack can be made
	protected int coolDownTimer = 0;
	
	// the unit's movement speed
	protected double speed = 0.25;
	// face east or west when player move
	protected boolean flip;
	
	
	
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public int getMaxHealth(){
		return this.maxHealth;
	}
	public int getDamage(){
		return this.damage;
	}
	public int getCoolDown(){
		return this.coolDown;
	}
	
	public int getCoolDownTimer(){
		return this.coolDownTimer;
	}
	public Image getUnit(){
		return this.unit;
	}
	
	/**
	 *  the movement of unit
	 *  
	 * @param unit The unit that currently moving
	 * @param world
	 * @param dir_x 
	 * @param dir_y
	 * @param delta
	 * @param SPEED
	 */
	public void move(Unit unit,World world,double dir_x, double dir_y, int delta, double SPEED){
    	// make player face east or west
    	// with right or left key be pressed
    	if(dir_x == -1)
    	{
    		flip = true;
    	} else if(dir_x == 1){
    		flip = false;
    	}
    	
    	float next_stepX = (float) (this.x + delta*SPEED*dir_x);
    	float next_stepY = (float) (this.y + delta*SPEED*dir_y);

    	if(world.block(unit,next_stepX, next_stepY)){
    		if (world.block(unit,next_stepX, this.y)){
    			if(world.block(unit,this.x, next_stepY)){
    				// next step on x-axis and y-axis are both 
    				// blocked, then we do nothing
    			}else{
    				//next step on y-axis not be blocked
    				//so we can update player's movement on y-axis
    				this.y = next_stepY;
    			}
    		}else{
    			//if next step on x-axis not be blocked
    			// then we update player's movement on x-axis
    			this.x = next_stepX;
    			}
    			 		
    	} else {
    		//next tile on all direction not be blocked
    		// update player's movement on x and y axis
    		this.x = next_stepX;
    		this.y = next_stepY;
    				
    	}
		
	}
	
	/**
	 * Attack unit 
	 * 
	 * @param target the unit to attack
	 * @param delta
	 *            
	 */
	public void attack(Unit target, int delta)
	{
		// coolDownTimer equal to 0 then attacks occur
		if (coolDownTimer <= 0)
		{
				int min, max, damage;

				min = 0;
				max = this.getDamage();
				damage = (int) (Math.random() * (max - min));

				// damage to target
				target.health -= damage;

				/* debug print
				System.out.println(this.getName() + " attacked " + target.getName()
						+ " for " + damage);*/
			

			// reset coolDownTimer
			this.coolDownTimer = this.coolDown;
		} else {
			// decrease coolDownTimer 
			this.coolDownTimer -= delta;
		}
	}
	
	/** render units onto map */
	public void render(Graphics g) 
			throws SlickException
	{
		this.getUnit().getFlippedCopy(flip, false).drawCentered((float)this.getX(),(float) this.getY() - 18);
	}

	/** update the status of the game */
	public void update(World world, int delta) throws SlickException{}; 
						

	
	
	/** unit's health bar except for player */
	protected float healthBarWidth;
	protected final float healthBarHeight = 16;
	
	/**
	 * Renders a floating health box above the unit with the unit's name and
	 * health representation
	 * 
	 * @param g
	 *            The graphics object to draw with.
	 */
	public void renderHealthBox(Graphics g)
	{
		// Colours for drawing
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red

		// Font for drawing
		// g.setFont(world.getLabelFont());
		// Set the width of the health bar based on the unit's name
		this.healthBarWidth = Math.max(70, g.getFont().getWidth(this.getName()) + 6);

		// Draw the health bar
		float bar_x = (float) this.getX() - healthBarWidth / 2;
		float bar_y = (float) this.getY() - this.getUnit().getWidth() / 2 - healthBarHeight;

		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, healthBarWidth, healthBarHeight);
		g.setColor(BAR);
		g.fillRect(bar_x, bar_y, (healthBarWidth * ((float) getHealth() / getMaxHealth())),
				healthBarHeight);

		// Draw name text in white color
		g.setColor(VALUE);
		float textX = bar_x + (healthBarWidth - g.getFont().getWidth(this.getName())) / 2;
		float textY = bar_y + (healthBarHeight - g.getFont().getHeight(this.getName())) / 2;
		g.drawString(this.getName(), textX, textY);
	}
}
