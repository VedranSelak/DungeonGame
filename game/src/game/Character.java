package game;

import java.util.Random;

public class Character {
	private int positionX;
	private int positionY;
	private int length;
	private int height;
	private Random rand;
	private String name;
	public Character(int length, int height) {
		this.name = "@";
		this.positionX = 0;
		this.positionY = 0;
		this.length = length;
		this.height = height;
		this.rand = new Random();
	}
	public int getX() {
		return this.positionX;
	}
	public int getY() {
		return this.positionY;
	}
	public String getName() {
		return this.name;
	}
	public void move(String comand) {
		for(int i=0; i<comand.length(); i++) {
			if(comand.charAt(i) == 'a') {
				if(this.positionX-1 >= 0) {
					this.positionX--;
				}
			}else if(comand.charAt(i) == 'd') {
				if(this.positionX+1 < this.length) {
					this.positionX++;
				}
			}else if(comand.charAt(i) == 'w') {
				if(this.positionY-1 >= 0) {
					this.positionY--;
				}
			}else if (comand.charAt(i) == 's'){
				if(this.positionY+1 < this.height) {
					this.positionY++;
				}
			}
		}
	}
}
