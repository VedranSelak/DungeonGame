package game;

import java.util.Random;

public class Vampire {
	private int positionX;
	private int positionY;
	private int length;
	private int height;
	private Random rand;
	public Vampire(int length, int height) {
		this.rand = new Random();
		this.length = length;
		this.height = height;
		this.positionX = this.rand.nextInt(this.length-1);
		this.positionY = this.rand.nextInt(this.height-1);
	}
	public int getX() {
		return this.positionX;
	}
	public int getY() {
		return this.positionY;
	}
	public void setX(int newX) {
		this.positionX = newX;
	}
	public void setY(int newY) {
		this.positionY = newY;
	}
	public void move() {
		boolean xOrY = this.rand.nextBoolean();
		if(xOrY) {
			boolean uOrD = this.rand.nextBoolean();
			if(uOrD) {
				if(this.positionY+1 < this.height) {
					this.positionY++;
				}else {
					this.positionY--;
				}
			}else {
				if(this.positionY-1 >= 0) {
					this.positionY--;
				}else {
					this.positionY++;
				}
			}
		}else {
			boolean lOrR = this.rand.nextBoolean();
			if(lOrR) {
				if(this.positionX+1 < this.length) {
					this.positionX++;
				}else {
					this.positionX--;
				}
			}else {
				if(this.positionX-1 >= 0) {
					this.positionX--;
				}else {
					this.positionX++;
				}
			}
		}
	}
}
