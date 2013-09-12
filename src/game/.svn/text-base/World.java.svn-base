package game;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: litao SHEN (litaos)
 */

import game.villagers.*;
import game.monsters.*;
import game.item.*;
import java.util.ArrayList;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
    /** Create a new World object. */
	private TiledMap map;
	private final String filePath = "assets";
	private final String mapName = "map.tmx";
		
	// using camera to make screen centered 
	// around the player
	private double cameraX;
	private double cameraY;
	
	//private ArrayList<Unit> allunits = new ArrayList<Unit>();
	private Player player;
	//private Villager villager;
	private ArrayList<Villager> villagers;
	private ArrayList<Monsters> monsters;
	private ArrayList<Item> items;
	
	
	/** initialize the map and player on to the map
	 * 
	 *@throws SlickException
	 *
	 */
    public World()
    throws SlickException
    {
        // TODO: Fill in
    	map = new TiledMap(filePath + "/" + mapName, filePath);  
    	player = new Player();
    	
    	villagers = new ArrayList<Villager>();
    	initVillager();
    	monsters = new ArrayList<Monsters>();
    	initPassiveMonsters();
    	initZombie();
    	initBandit();
    	initSkeleton();
    	initDraelic();
    	items = new ArrayList<Item>();
    	initItem();
    	
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(World world,double dir_x, double dir_y, int delta)
    		throws SlickException
    {
    	player.update(world,dir_x, dir_y, delta);
    	
    	for(Villager v: villagers){
    		v.update(world,delta);
    	}
    	
    	for(Monsters m: monsters){
    		m.update(world, delta);
    	}
    	/* using value of player's position 
    	* minus half width and height 
    	* could make camera always centered around 
    	* player
    	*/
    	cameraX =  (float) (player.getX() - RPG.screenwidth/2);
    	cameraY =  (float) (player.getY() - RPG.screenheight/2);   	

    }

    /** Render the entire screen, so it reflects the current game state.
     *  Render player, villagers, monsters, and items
     *  also render their status panel and dialogue box
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    		throws SlickException
    {
        // TODO: Fill in
    	int offsetX = (int) (cameraX % map.getTileWidth());
    	int offsetY = (int) (cameraY % map.getTileHeight());
    	map.render(- offsetX,- offsetY,
    			(int) (cameraX / map.getTileWidth()),(int) (cameraY / map.getTileHeight()),
    			13 ,10);  	
		// Translate the scene to allow drawing at real co-ordinates
		g.translate((float) -this.cameraX, (float) -this.cameraY);
		
    	player.render(g);
    	
    	for(Villager v: villagers){
    		v.render(g);
    		if(!v.getText().isEmpty()){
    			v.renderDialogueBox(g);
    		}
    		v.renderHealthBox(g);
    	}
    	
    	for(Monsters m: monsters){
    		m.render(g);
    		m.renderHealthBox(g);
    	}
    	
    	for(Item i: items){
    		i.render(g);
    	}
    	
    	// render panel of player
    	g.translate((float) this.cameraX, (float) this.cameraY);
    	player.renderPanel(g);

    }
    
    /** using to judge whether the next tile could move in
     * 
     * @param unit that currently moving
     * @param newx the next x-position that player will move to
     * @param newy	the next y-position that player will move to
     * @return true if next tile is blocked
     */
    public boolean block(Unit unit,double newx, double newy){
    	
    	ArrayList<Unit> allunits = new ArrayList<Unit>();
    	
    	int tileID;   	
    	//property name for given tile
    	String p_name;
    	allunits.add(getPlayer());
    	allunits.addAll(getVillagers());
    	allunits.addAll(getMonsters());
    	//allunits.addAll(getItems());
    	
    	tileID = map.getTileId((int) newx / map.getTileWidth(), (int) newy /map.getTileHeight(), 0);
    	p_name = map.getTileProperty(tileID, "block", "0");
    	
    	
    	// if tile is blocked
    	// or two units collide, 
    	// then return true. otherwise,
    	// return false that unit could keep moving
    	for(Unit u: allunits){
    		if(! u.equals(unit)){
    			if(distanceTo(newx,newy,u) <= 45 || p_name != "0"){
    				return true;
    			}
    		}
    	}
		return false;
    }

	
	/** Get the distance between two units 
	 * 
	 * @param x the location X of player
	 * @param y the location Y of player
	 * @param object
	 * @return
	 */
	public static double distanceTo(double x, double y, Unit object)
	{
		// Find the distance from the player
		double distX = x - object.getX();
		double distY = y - object.getY();
		return Math.sqrt(distX * distX + distY * distY);
	}
	
	
	// the code below is to add all characters onto map
	/** put Villagers onto map */
	public void initVillager() 
			throws SlickException
	{
		villagers.add(new Aldric());
		villagers.add(new Elvira());
		villagers.add(new Garth());
	}

	/** put passive monsters onto map */
	public void initPassiveMonsters()
		throws SlickException
	{
		final String name = "Giant Bats";
		final Image bat = new Image("assets/units/dreadbat.png");
		final int maxHP = 40;
		final int damage = 0;
		final double speed = 0.2;
		final int coolDown = 0;
		monsters.add(new PassiveMonster(1260,640, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(1476,1000, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(1548,1392, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(856,2556, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(1260,1808, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(2882,2556, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(2944,1548, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(2296,1044, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(2916,874, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(1980,712, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(2000,972, name, bat, maxHP, damage, speed, coolDown));
		monsters.add(new PassiveMonster(2772,640, name, bat, maxHP, damage, speed, coolDown));
	}
	/**
	 * initialise zombie
	 * @throws SlickException
	 */
	public void initZombie()
		throws SlickException
	{
		final String name = "Zombie";
		final Image zom = new Image("assets/units/zombie.png");
		final int maxHP = 60;
		final int damage = 10;
		final double speed = 0.25;
		final int coolDown = 800;
		
		monsters.add(new AggressiveMonster(1260,540,name,zom,maxHP,damage,speed,coolDown));
		monsters.add(new AggressiveMonster(1476,900,name,zom,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(1548,1332,name,zom,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(756,2556,name,zom,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(900,2844,name,zom,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(1980,2412,name,zom,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(2844,1548,name,zom,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(2196,1044,name,zom,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(2988,396,name,zom,maxHP,damage,speed, coolDown));
	}
	/**
	 * initialize bandit
	 * @throws SlickException
	 */
	public void initBandit()
		throws SlickException
	{
		final String name = "Bandit";
		final Image ban = new Image("assets/units/bandit.png");
		final int maxHP = 40;
		final int damage = 8;
		final double speed = 0.25;
		final int coolDown = 200;
		
		monsters.add(new AggressiveMonster(1116,1476,name,ban,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(1260,1908,name,ban,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(540,1476,name,ban,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(1404,2484,name,ban,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(2772,2556,name,ban,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(2052,1548,name,ban,maxHP,damage, speed, coolDown));
		monsters.add(new AggressiveMonster(1980,1404,name,ban,maxHP,damage,speed, coolDown));
	}
	/**
	 * Initialise skeleton
	 * @throws SlickException
	 */
	public void initSkeleton()
		throws SlickException
	{
		final String name = "Skeleton";
		final Image ske = new Image("assets/units/skeleton.png");
		final int maxHP = 100;
		final int damage = 16;
		final double speed = 0.25;
		final int coolDown = 500;
		
		monsters.add(new AggressiveMonster(2916,974,name,ske,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(1980,612,name,ske,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(2052,972,name,ske,maxHP,damage,speed, coolDown));
		monsters.add(new AggressiveMonster(2772,540,name,ske,maxHP,damage,speed, coolDown));
	}
	/**
	 * initialise draelic
	 * @throws SlickException
	 */
	public void initDraelic()
		throws SlickException
	{
		final String name = "Draelic";
		final Image dra = new Image("assets/units/necromancer.png");
		final int maxHP = 140;
		final int damage = 30;
		final double speed = 0.25;
		final int coolDown = 400;
		
		monsters.add(new AggressiveMonster(2052,468,name,dra,maxHP,damage,speed, coolDown));
	}
	/**
	 * Initialise items
	 * @throws SlickException
	 */
	public void initItem()
		throws SlickException
	{
		items.add(new Item(972,2916,"Amulet", new Image("assets/items/amulet.png")));
		items.add(new Item(1980,1476,"Sword", new Image("assets/items/sword.png")));
		items.add(new Item(2052,900,"Tome", new Image("assets/items/book.png")));
		items.add(new Item(2052,396,"Elixir", new Image("assets/items/elixir.png")));
	}
	
	
    /**
     * get player on the world
     * @return player
     */
	public Player getPlayer() {
		return player;
	}
	/**
	 * return villagers
	 * @return
	 */
	public ArrayList<Villager> getVillagers(){
		return villagers;
	}
	/**
	 * return monsters
	 * @return
	 */
	public ArrayList<Monsters> getMonsters(){
		return monsters;
	}
	/**
	 * return items
	 * @return
	 */
	public ArrayList<Item> getItems(){
		return items;
	}

}
