package gj.kalah.player.pacella;

import java.util.LinkedList;

public interface Minmax extends Control {

	public static void minmaXF(Nodo pointer) {
		int minmax[] = pointer.getScore();
		pointer.setMinmax(minmax[1] - minmax[0]);
		pointer.setAphabeta(true);
	}

	static void aphabetaleaf(LinkedList<Nodo> pila, Nodo nodo, boolean noRock) {
		if (noRock) {
			Control.win(nodo);
			Control.upDateloser(nodo);
		} else {
			Control.win(nodo);
			Control.tABstate(nodo);
		}
		minmaXF(nodo);
		updateDadminmax(nodo);
		cut(nodo.getPadre(), pila);
	}

	static void aphabeta(LinkedList<Nodo> pila, Nodo nodo, boolean noRock) {
		if (!nodo.isAphabeta()) {
			if (noRock) {
				Control.win(nodo);
				Control.upDateloser(nodo);
			} else {
				Control.win(nodo);
				Control.tABstate(nodo);
			}
			minmaXF(nodo);
		}
		updateDadminmax(nodo);
		pila.removeFirst();
		if (!pila.isEmpty()) {
			cut(pila.getFirst(), pila);
		}
	}

	static void updateDadminmax(Nodo nodo) {
		int minmax = nodo.getMinmax();
		Nodo dad = nodo.getPadre();
		if (dad != null) {
			if (!dad.isAphabeta()) {
				dad.setMinmax(minmax);
				dad.setAphabeta(true);
			} else {
				if (dad.isPlayer()) {
					if (dad.getMinmax() < minmax) {
						dad.setMinmax(minmax);
					}
				} else {
					if (dad.getMinmax() > minmax) {
						dad.setMinmax(minmax);
					}
				}
			}
		}
	}

	static void cut(Nodo node, LinkedList<Nodo> pila) {
		Nodo dad = node.getPadre();
		if (dad != null && dad.isAphabeta()) {
			if (node.isPlayer() == false && dad.isPlayer() == true && node.getMinmax() < dad.getMinmax()) {
				pila.remove(node);
			}
			if (node.isPlayer() == true && dad.isPlayer() == false && node.getMinmax() > dad.getMinmax()) {
				pila.remove(node);
			}
		}
	}
}
