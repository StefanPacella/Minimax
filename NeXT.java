package gj.kalah.player.pacella;

import java.util.LinkedList;

public interface NeXT extends Minmax, Control {

	public static void move(Nodo game) {
		LinkedList<Nodo> pila = new LinkedList<Nodo>();
		pila.add(game);
		while (!pila.isEmpty()) {
			game = pila.getFirst();
			Nodo nextgame = Control.newNodo(game);
			boolean gameover = Control.isEmptytab(game);
			if (nextgame != game && !gameover) {
				gameover = Control.isEmptytab(nextgame);
				if (nextgame.getH() > 9 || gameover) {
					Minmax.aphabetaleaf(pila, nextgame, gameover);
				} else {
					pila.addFirst(nextgame);
				}
			} else {
				Minmax.aphabeta(pila, game, gameover);
			}
		}
	}
}
