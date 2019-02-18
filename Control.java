package gj.kalah.player.pacella;

import java.util.LinkedList;

public interface Control extends Update {
	static boolean isFasable(Nodo pointer, int i) {
		int[][] x = pointer.getTab();
		boolean is = false;
		if (pointer.isPlayer()) {
			is = (x[1][i] > 0);
		} else {
			is = (x[0][i] > 0);
		}
		return is;

	}

	static Nodo newNodo(Nodo pointer) {
		boolean is = false;
		int conta = pointer.getPosizione();
		int n = pointer.getTab()[0].length;
		while (!is && conta < n) {
			if (isFasable(pointer, conta)) {
				pointer.setPosizione(conta + 1);
				pointer = pointer.newChildren(pointer, conta);
				is = true;
			}
			conta = conta + 1;
		}
		return pointer;
	}

	static int[][] copyArray(int[][] x) {
		int[][] y = new int[x.length][x[0].length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				y[i][j] = x[i][j];
			}
		}
		return y;
	}

	static int[] copyArrayS(int[] x) {
		int[] y = new int[2];
		y[0] = x[0];
		y[1] = x[1];
		return y;
	}

	static int nextMove(Nodo nodo, LinkedList<Nodo> x) {
		int minmax = nodo.getMinmax();
		int n = x.size() - 1;
		while (!x.isEmpty() && !(minmax == x.get(n).getMinmax())) {
			n = n - 1;
		}
		for (int i = 0; i <= n; i++) {
			if (!isFasable(nodo, i)) {
				n = n + 1;
			}
		}
		return n;
	}

	public static void win(Nodo pointer) {
		int[] score = pointer.getScore();
		if (score[0] > 24) {
			score[0] = score[0] + 100;
		}
		if (score[1] > 24) {
			score[1] = score[1] + 100;
		}
		pointer.setScore(score);
	}

	public static void upDateloser(Nodo pointer) {
		int[] score = pointer.getScore();
		boolean isTrueF = cheackTab(pointer, 0);
		boolean isTrueT = cheackTab(pointer, 1);
		if (isTrueT && score[1] < 25) {
			score[1] = score[1] - 100;
		}
		if (isTrueF && score[0] < 25) {
			score[0] = score[0] - 100;
		}
	}

	public static boolean cheackTab(Nodo nodo, int index) {
		boolean isTrue = true;
		int[][] x = nodo.getTab();
		for (int i = 0; i < x[0].length; i++) {
			if (isTrue && x[index][i] > 0)
				isTrue = false;
		}
		return isTrue;
	}

	public static int how(Nodo nodo) {
		int n;
		if (nodo.isPlayer()) {
			n = 1;
		} else {
			n = 0;
		}
		return n;
	}

	public static void tABstate(Nodo nodo) {
		int[] score = nodo.getScore();
		int[][] x = nodo.getTab();
		for (int j = 0; j < x.length; j++) {
			for (int i = 0; i < x[0].length; i++) {
				if (x[j][i] > 0)
					score[j] = score[j] - 1;
			}
		}
	}

	public static boolean isEmptytab(Nodo nodo) {
		boolean isTrue1 = cheackTab(nodo, 0);
		boolean isTrue2 = cheackTab(nodo, 1);
		return isTrue1 || isTrue2;
	}

	public static Nodo resetFornewgame(Nodo nodo, boolean isFirst) {
		if (isEmptytab(nodo)) {
			int tab[][] = { { 4, 4, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4 } };
			int score[] = { 0, 0 };
			nodo = new Nodo(isFirst, tab, score);
		} else {
			nodo = new Nodo(isFirst, nodo.getTab(), nodo.getScore());
		}
		return nodo;
	}

}
