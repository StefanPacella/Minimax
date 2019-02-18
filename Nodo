package gj.kalah.player.pacella;

public class Nodo {

	private Nodo padre;
	private boolean player;
	private int[][] tab;
	private int[] score = { 0, 0 };
	private int minmax;
	private boolean aphabeta = false;
	private int posizione = 0;
	private int h = 0;

	public Nodo(boolean player, Nodo pointer, int h, int[][] tab, int[] score) {
		this.padre = pointer;
		this.player = player;
		this.tab = tab;
		this.score = score;
		this.h = h + 1;
	}

	public Nodo(boolean player, int[][] tab, int[] score) {
		padre = null;
		this.player = player;
		this.tab = tab;
		this.score = score;
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public Nodo newChildren(Nodo pointer, int position) {
		int[][] tab = Control.copyArray(this.tab);
		int[] score = Control.copyArrayS(this.score);
		boolean next = Update.move(position, tab, player, score);
		Nodo newNodo = new Nodo(next, pointer, pointer.getH(), tab, score);
		return newNodo;
	}

	public Nodo nextMove(Nodo pointer, int position) {
		int[][] tab = Control.copyArray(this.tab);
		int[] score = Control.copyArrayS(this.score);
		Update.move(position, tab, player, score);
		Nodo nextNodo = new Nodo(pointer.isPlayer(), tab, score);
		return nextNodo;
	}

	public int getH() {
		return h;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}

	public boolean isPlayer() {
		return player;
	}

	public int[][] getTab() {
		return tab;
	}

	public int[] getScore() {
		return score;
	}

	public void setScore(int[] score) {
		this.score = score;
	}

	public int getMinmax() {
		return minmax;
	}

	public void setMinmax(int minmax) {
		this.minmax = minmax;
	}

	public boolean isAphabeta() {
		return aphabeta;
	}

	public void setAphabeta(boolean aphabeta) {
		this.aphabeta = aphabeta;
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

}
