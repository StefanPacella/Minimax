package gj.kalah.player.pacella;

import java.util.LinkedList;
import gj.kalah.player.Player;

public class Player implements  Control, NeXT {
	private int tab[][] = { { 4, 4, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4 } };
	private int score[] = { 0, 0 };
	private boolean isFirst;
	private Nodo game = new Nodo(isFirst, tab, score);

	public void start(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public int move() {
		Nodo game = Control.resetFornewgame(this.game, isFirst);
		LinkedList<Nodo> nodileaf = new LinkedList<Nodo>();
		for (int i : game.getTab()[Control.how(game)]) {
			if (i > 0) {
				nodileaf.add(Control.newNodo(game));
			}
		}
		nodileaf.parallelStream().forEach(a -> NeXT.move(a));
		int n = Control.nextMove(game, nodileaf);
		game.setPlayer(isFirst);
		this.game = game.nextMove(game, n);
		if (!isFirst) {
			n = 5 - n;
		}
		return n;
	}

	public void tellMove(int m) {
		game = Control.resetFornewgame(this.game, isFirst);
		if (isFirst) {
			m = 5 - m;
		}
		game.setPlayer(!isFirst);
		game = game.nextMove(game, m);
	}
}
