package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {
	private int length;
	private int height;
	private int vampires;
	private int moves;
	private boolean vampiresMove;
	private Random rand;
	private String[][] map;
	private Character c;
	private Scanner reader;
	private ArrayList<Vampire> vamps;
	public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
		this.length = length;
		this.height = height;
		this.vampires = vampires;
		this.moves = moves;
		this.vampiresMove = vampiresMove;
		this.reader = new Scanner(System.in);
		this.rand = new Random();
		this.vamps = new ArrayList<Vampire>();
		this.map = new String[this.height][this.length];
		this.c = new Character(this.length,this.height);
		for(int i=0; i<this.vampires; i++) {
			addVampire();
		}
	}
	public void run() {
		startMap();
		while(true) {
			System.out.println(this.moves);
			System.out.println(c.getName() + " " + c.getY() + " " + c.getX());
			for(Vampire v : this.vamps) {
				System.out.println("v " + v.getY() + " " + v.getX());
			}
			System.out.println();
			printMap();
			String comand = this.reader.nextLine();
			boolean check = false;
			for(int i=0; i<comand.length(); i++) {
				if(comand.charAt(i) != 'a' && comand.charAt(i) != 's' && comand.charAt(i) != 'w' && comand.charAt(i) != 'd') {
					System.out.println("Incorrect input");
					check = true;
				}
			}
			if(check) {
				continue;
			}
			int xPrev = c.getX();
			int yPrev = c.getY();
			c.move(comand);
			if(this.map[c.getY()][c.getX()] == "v") {
				removeVamp(c.getX(),c.getY());
				this.map[yPrev][xPrev] = ".";
				this.map[c.getY()][c.getX()] = c.getName();
			}else {
				this.map[yPrev][xPrev] = ".";
				this.map[c.getY()][c.getX()] = c.getName();
			}
			if(this.vamps.isEmpty()) {
				System.out.println("YOU WIN");
				break;
			}else {
				this.moves--;
				if(this.moves == 0) {
					System.out.println("YOU LOSE");
					break;
				}
			}
			for(int i=0; i<comand.length(); i++) {
				if(this.vampiresMove) {
					for(Vampire v : this.vamps) {
						int vxPrev = v.getX();
						int vyPrev = v.getY();
						v.move();
						if(this.map[v.getY()][v.getX()] == "v" || this.map[v.getY()][v.getX()] == "@") {
							v.setX(vxPrev);
							v.setY(vyPrev);
							this.map[v.getY()][v.getX()] = "v";
							continue;
						}
						this.map[vyPrev][vxPrev] = ".";
						this.map[v.getY()][v.getX()] = "v";
					}
				}
			}
		}
		
	}
	public void startMap() {
		for(int i=0; i<this.height; i++) {
			for(int j=0; j<this.length; j++) {
				this.map[i][j] = ".";
			}
		}
		for(Vampire v : this.vamps) {
			this.map[v.getY()][v.getX()] = "v";
		}
		this.map[0][0] = this.c.getName();
	}
	public void addVampire() {
		Vampire v = new Vampire(this.length,this.height);
		boolean check = false;
		if(this.vamps.isEmpty()) {
			this.vamps.add(v);
			return;
		}
		while(true) {
			check = false;
			for(Vampire vam : this.vamps) {
				if((vam.getX() == v.getX() && vam.getY() == v.getY()) || (v.getX() == 0 && v.getY() == 0)) {
					v.setX(this.rand.nextInt(this.length));
					v.setY(this.rand.nextInt(this.height));
					check = true;
				}
			}
			if(check == false) {
				break;
			}
		}
		this.vamps.add(v);
	}
	public void printMap() {
		for(int i=0; i<this.height; i++) {
			for(int j=0; j<this.length; j++) {
				System.out.print(this.map[i][j]);
			}
			System.out.print("\n");
		}
	}
	public void removeVamp(int x, int y) {
		for(Vampire v : this.vamps) {
			if(v.getX() == x && v.getY() == y) {
				int index = getIndexOfVamp(v.getX(),v.getY());
				this.vamps.remove(index);
				return;
			}
		}
	}
	public int getIndexOfVamp(int x,int y) {
		for(int i=0; i<this.vamps.size(); i++) {
			if(this.vamps.get(i).getX() == x && this.vamps.get(i).getY() == y) {
				return i;
			}
		}
		return -1;
	}
}
