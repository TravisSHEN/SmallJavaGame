package game.villagers;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Villager extends Unit {
	
	// the distance that villager will interact with player
	private float interactDist = 50;
	// check whether player already interact with villager (to avoid repeats)
	private boolean interacting = false;
	// the maximum time a dialogue box will exist (ms)
	private int dialogueLife = 3000;
	// the timer to calculate when dialogue box disappear
	private int dialogueTimer = 0;
	// the string to be spoken upon interaction 
	private String text = "";

	// The height of the dialogue bar 
	private final int dialogueBarHeight = 16;
	// The width of the dialogue bar 
	private int dialogueBarWidth;

	/**
	 *  initialise villagers
	 * @param x the location of villager
	 * @param y the location of villager
	 * @param name the name of villager
	 * @param villager the image of villager
	 * @throws SlickException
	 */
	public Villager(double x, double y, String name, Image villager) 
			throws SlickException
	{		
		// set details of villagers 
		this.x = x;
		this.y = y;
		this.name = name;
		this.unit = villager;
		this.maxHealth = 1;
		this.health = this.maxHealth;
	}

	@Override
	/** Update the villager's state for a frame.
	 * 
	 * @param delta
	 * @param world to get the player, and items
	 */
	public void update(World world,int delta)
	{
		if (this.dialogueTimer > 0){
		
			this.dialogueTimer -= delta;
		}
		else{
		
			this.text = "";
		}

		// Check for interaction
		// if player is within interact distance
		// then villager will interact with player
		if (World.distanceTo(world.getPlayer().getX(),world.getPlayer().getY(),this) < interactDist){
		
			if (!this.interacting){
			
				this.interact(world, world.getPlayer());
			}
		}
		else{
		
			if (this.interacting){
			
				this.interacting = false;
			}
		}
	}
	
	
	/**
	 * the action happen when player talk to villagers
	 * @param world
	 */
	public abstract void interact(World world, Unit target);
	
	/**
	 * Sets the required fields for villagers dialogue given a string to say
	 * @param text  The message to speak
	 */
	public void setText(String text){
	
		this.text = text;
		this.dialogueTimer = this.dialogueLife;
		this.interacting = true;
	}
	
	/**
	 * @return the dialogueText
	 */
	public String getText()
	{
		return text;
	}
	
	
	/**
	 * render the dialogue box 
	 * @param g
	 */
	public void renderDialogueBox(Graphics g){
	
		// Colours for drawing
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black

		// Set the width of the health bar based on the unit's name
		this.dialogueBarWidth = Math.max(70, g.getFont().getWidth(this.getText()) + 6);

		// Draw the health bar
		float barX = (float) this.getX() - dialogueBarWidth / 2;
		float barY = (float) this.getY() - this.getUnit().getWidth() / 2 - this.getUnit().getHeight() / 2
				- dialogueBarHeight - 5;

		g.setColor(BAR_BG);
		g.fillRect(barX, barY, dialogueBarWidth, dialogueBarHeight);

		// Draw name text (in white)
		g.setColor(VALUE);
		float textX = barX + (dialogueBarWidth - g.getFont().getWidth(this.getText())) / 2;
		float textY = barY + (dialogueBarHeight - g.getFont().getHeight(this.getText())) / 2;

		g.drawString(this.getText(), textX, textY);
	}

}
