package gj.kalah.player.pacella;

public interface Update {
	static boolean move(int position, int[][] x, boolean player, int[] score) {
		boolean nextgame = false;
		if (player) {
			int rock = x[1][position];
			x[1][position] = 0;
			position = position + 1 ;
			while (rock > 0) {
				nextgame = false;
				rock = cicleR(x, rock, position, player, score);
				if (rock == 1) {
					nextgame = true;
				}
				rock = conca(player, rock, score);
				rock = cicleL(x, rock, x[0].length - 1, player, score);
				position = 0;
			}
		} else {
			nextgame = true;
			int rock = x[0][position];
			x[0][position] = 0;
			position = position - 1 ;
			while (rock > 0) {
				rock = cicleL(x, rock, position, player, score);
				if (rock == 1) {
					nextgame = false;
				}
				rock = conca(player, rock, score);
				rock = cicleR(x, rock, 0, player, score);
				position = x[0].length - 1;
			}
		}
		return nextgame;

	}

	static int cicleR(int[][] x, int rock, int position, boolean player, int[] score) {
		while (rock > 0 && position < x[0].length ) {
			rock--;
			if (rock == 0 && x[1][position] == 0 && player) {
				rock = takerock(x, position, player, score);
			} else {
				x[1][position] = x[1][position] + 1;
			}
			position = position + 1;
		}
		return rock;

	}

	static int cicleL(int[][] x, int rock, int position, boolean player, int[] score) {
		while (rock > 0 && position >= 0) {
			rock--;
			if (rock == 0 && x[0][position] == 0 && !player) {
				rock = takerock(x, position, player, score);
			} else {
				x[0][position] = x[0][position] + 1;
			}
			position = position - 1;
		}
		return rock;

	}

	static int conca(boolean player, int rock, int[] score) {
		if (rock > 0) {
			if (player) {
				score[1] = score[1] + 1;
				rock--;
			} else {
				score[0] = score[0] + 1;
				rock--;
			}
		}
		return rock;
	}

	static int takerock(int[][] x, int position, boolean player, int[] score) {
		if (player) {
			x[1][position] = 0;
			int temp = x[0][position];
			x[0][position] = 0;
			score[1] = score[1] + temp + 1;
			return 0;
		} else {
			x[0][position] = 0;
			int temp = x[1][position];
			x[1][position] = 0;
			score[0] = score[0] + temp + 1;
			return 0;
		}
	}

}
