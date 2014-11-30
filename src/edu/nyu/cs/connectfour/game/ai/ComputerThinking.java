package edu.nyu.cs.connectfour.game.ai;

/**
 * @author shenli
 * <p>
 * The {@code ComputerThinking} class is used to provide computer calculation.
 * <p>
 * {@code ComputerThinking} objects are not constant; their values can be changed after they are created.
 * The {@code ComputerThinking} object is not thread-safe. To use it concurrently, user must surround each 
 * method invocation with external synchronization of the users' choosing.
 */
class ComputerThinking {
	private final int rows;
	private final int columns;
	private final int[] nextPlace;
	private final int[][] state;
	private int maxDepth = 2;
	private int[] block;

	/**
	 * Initializes a newly created {@code ComputerThinking} object so that it records chess map information 
	 * in order to calculate best place location and game result.
	 * <p>
	 * @param rows the row
	 * @param columns the column
	 * @param nextPlace the next chess place location on each column
	 * @param state the chess map state
	 */
	ComputerThinking(int rows, int columns, int[] nextPlace, int[][] state) {
		assert rows > 0;
		assert columns > 0;
		assert nextPlace != null;
		assert state != null;
		
		this.rows = rows;
		this.columns = columns;
		this.state = state;
		this.nextPlace = nextPlace;
		block = new int[8];
	}
	
	/**
	 * Returns the state of game. True if the player is win, false if it's not.
	 * <p>
	 * @param row the row
	 * @param column the column
	 * @param player the player index
	 * @return true if player is win, otherwise false
	 */
	boolean isWin(int row, int column, int player) {
		assert row >= 0;
		assert column >= 0;
		assert player >= 0;
		
		int[][] placeValue = getColumnState(row, column);
		for (int dir = 0; dir < 4 ; dir++) {
			if (placeValue[player][dir] + placeValue[player][7 - dir] > 2) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the state of game. True if two players draw, false if it's not.
	 * <p>
	 * @param chessNumber the chess number
	 * @return true if two players are drawing, otherwise false
	 */
	boolean isDraw(int chessNumber) {
		assert chessNumber >= 0;
		
		return rows * columns == chessNumber;
	}

	/**
	 * Calculate the best location that computer should place.
	 * <p>
	 * @param degree the degree of computer level
	 * @param player the player index
	 * @return the best location
	 */
	int bestValue(int degree, int player) {
		int bestValue;
		if (degree == 1) {
			int[] maxValues = new int[columns];
			int max = 0;
			for (int i = 0; i < columns; i++) {
				maxValues[i] = calValue(i, player);
				if (maxValues[i] > max) {
					max = maxValues[i];
				}
			}
			if (max >= 100) {
				for (int i = 0; i < columns * columns * columns; i++) {
					bestValue = (int) (Math.random() * columns);
					if (nextPlace[bestValue] >= 0 && maxValues[bestValue] / 10 == max / 10) {
						return bestValue;
					}
				}
			} else {
				for (int i = 0; i < columns * columns * columns; i++){
					bestValue = (int) (Math.random() * columns);
					if (nextPlace[bestValue] >= 0 && maxValues[bestValue] == max ) {
						return bestValue;
					}
				}
			}
		} else if (degree > 1) {
			maxDepth = degree;
			int[] maxValues = new int[columns];
			int max = miniMax(
					maxDepth * 2, Integer.MIN_VALUE, Integer.MAX_VALUE, player, maxValues);
			for (int i = 0; i < columns * columns * columns; i++) {
				bestValue = (int) (Math.random() * columns);
				if (nextPlace[bestValue] >= 0 && maxValues[bestValue] == max ) {
					return bestValue;
				}
			}
		}
		do {
			bestValue = (int) (Math.random() * columns);
		} while (nextPlace[bestValue] < 0);
		return bestValue;
	}

	/**
	 * Returns the column state.
	 * <p>
	 * @param row the row
	 * @param column the column
	 * @return the column state
	 */
	private int[][] getColumnState(int row, int column) {
		int[][] columnState = new int[2][8];
		block = new int[8];
		int state;
		for (int i = 0; i < 8 ; i++ ) {
			state = getBoardState(row, column, i, 1);
			if (state != 2) {
				int j = 1;
				while (state == getBoardState(row, column, i, j)) {
					j++;
					if (state == -1) {
						block[i]--;
					} else {
						columnState[state][i]++;
					}
				}
			} else {
				block[i] = 2;
			}
		}
		return columnState;
	}

	/**
	 * Returns the calculated value.
	 * <p>
	 * @param column the column
	 * @param player the player index
	 * @return the calculated value
	 */
	private int calValue(int column, int player) {
		int row  = nextPlace[column];
		int[][] nextValue = new int[2][8];
		if (row > 0) {
			nextValue = getColumnState(row - 1, column);
		}
		int colValue = 0;
		if (row >= 0) {
			int[][] placeValue = getColumnState(row , column);
			for (int dir = 0; dir < 4; dir++) {
				if (placeValue[player][dir] + placeValue[player][7 - dir] >= 3) {
					return Integer.MAX_VALUE / 2;
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				if (placeValue[(player + 1) % 2][dir] 
						+ placeValue[(player + 1) % 2][7 - dir] >= 3) {
					return Integer.MAX_VALUE / 4;
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				if (nextValue[(player + 1) % 2][dir]
						+ nextValue[(player + 1) % 2][7 - dir] >= 3) {
					return Integer.MIN_VALUE / 2;
				}
			}
			for (int dir = 1; dir < 4; dir++) {
				if (placeValue[player][dir] == 1
						&& placeValue[player][7 - dir] == 1) {
					if (block[dir] < 0 && inSameLine(column, dir, 2)
							&& block[7 - dir] < 0 && inSameLine(column, 7 - dir, 2)) {
						colValue += 100000;
					} else if ((block[dir] < 0 && inSameLine(column, dir, 2))
							|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir , 2))) {
						colValue += 1000;
					} else {
						colValue += 10;
					}
				}
				if (placeValue[(player + 1) % 2 ][dir] == 1
						&& placeValue[(player + 1) % 2 ][7 - dir] == 1) {
					if (block[dir] < 0
							&& inSameLine(column, dir, 2)
							&& block[7 - dir] < 0
							&& inSameLine(column, 7 - dir, 2)) {
						colValue += 80000;
					} else if ((block[dir] < 0 && inSameLine(column , dir , 2))
							|| (block[7 - dir] < 0 && inSameLine(column ,7 - dir , 2))) {
						colValue += 800;
					} else {
						colValue += 8;
					}
				}
			}

			for (int dir = 1; dir < 8; dir++) {
				if (placeValue[player][dir] == 2) {
					if (block[dir] < 0
							&& inSameLine(column, dir, 3)
							&& block[7 - dir] < 0
							&& inSameLine(column, 7 - dir, 1)) {
						colValue += 50000;
					} else if ((block[dir] < 0 && inSameLine(column , dir , 3))
							|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir, 1))) {
						colValue += 500;
					} else {
						colValue += 5;
					}
				} else if (placeValue[player][dir] == 1 ) {
					if (block[dir] < 0
							&& inSameLine(column, dir, 2)
							&& block[7 - dir] < 0
							&& inSameLine(column, 7 - dir, 1)) {
						colValue += 5000;
					} else if ((block[dir] < 0 && inSameLine(column, dir, 2))
							|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir, 1))) {
						colValue += 50;
					} else {
						colValue += 5;
					}
				}
				if (placeValue[(player + 1) % 2][dir] == 2) {
					if (block[dir] < 0
							&& inSameLine(column, dir, 3)
							&& block[7 - dir] < 0
							&& inSameLine(column, 7 - dir, 1)) {
						colValue += 40000;
					} else if ((block[dir] < 0 && inSameLine(column, dir, 3))
							|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir, 1))) {
						colValue += 400;
					} else {
						colValue += 4;
					}
				} else if (placeValue[(player + 1) % 2][dir] == 1) {
					if (block[dir] < 0
							&& inSameLine(column, dir, 2)
							&& block[7 - dir] < 0 && inSameLine(column, 7 - dir, 1)) {
						colValue += 4000;
					} else if ((block[dir] < 0 && inSameLine(column, dir, 2))
							|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir, 1))) {
						colValue += 40;
					} else {
						colValue += 4;
					}
				}
			}
		}
		return colValue;
	}
	
	/**
	 * Returns the state of same line. True if in the same line, false if it's not.
	 * <p>
	 * @param column the column
	 * @param dir the dir
	 * @param step the step
	 * @return true if in the same line, otherwise false
	 */
	private boolean inSameLine(int column, int dir, int step) {
		int row = nextPlace[column];
		if (dir == 0) {
			if (row >= step && row < rows && column >= 0 && column < columns) {
				return true;
			}
		} else if (dir == 1) {
			if (row >= step && row < rows && column >= step && column < columns) {
				if (nextPlace[column] - step == nextPlace[column - step]) {
					return true;
				}
			}
		} else if (dir == 2) {
			if (row >= 0 && row < rows && column >= step && column < columns) {
				if (nextPlace[column] == nextPlace[column - step]) {
					return true;
				}
			}
		} else if (dir == 3) {
			if (row >= 0 && row + step < rows && column >= step && column < columns) {
				if (nextPlace[column] + step == nextPlace[column - step]) {
					return true;
				}
			}
		} else if (dir == 4) {
			if (row >= step && row < rows && column >= 0 && column + step < columns) {
				if (nextPlace[column] - step == nextPlace[column + step]) {
					return true;
				}
			}
		} else if (dir == 5) {
			if (row >= 0 && row < rows && column >= 0 && column + step < columns) {
				if (nextPlace[column]  == nextPlace[column + step]) {
					return true;
				}
			}
		} else if (dir == 6) {
			if (row >= 0 && row + step < rows && column >= 0 && column + step < columns) {
				if (nextPlace[column] + step  == nextPlace[column + step]) {
					return true;
				}
			}
		} else if (dir == 7) {
			if (row >= 0 && row + step < rows && column >= 0 && column < columns) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the chess map state information.
	 * <p>
	 * @param row the row
	 * @param column the column
	 * @param dir the dir
	 * @param step the step
	 * @return the chess map state information
	 */
	private int getBoardState(int row, int column, int dir, int step) {
		if (dir == 0) {
			if (row >= step && row < rows && column >= 0 && column < columns) {
				return -1;
			} else {
				return 2;
			}
		} else if (dir == 1) {
			if (row >= step && row < rows && column >= step && column < columns) {
				return state[row - step][column - step];
			} else {
				return 2;
			}
		} else if (dir == 2) {
			if (row >= 0 && row < rows && column >= step && column < columns) {
				return state[row][column - step];
			} else {
				return 2;
			}
		} else if (dir == 3) {
			if (row >= 0 && row  + step < rows && column >= step && column < columns) {
				return state[row + step][column - step];
			} else {
				return 2;
			}
		} else if (dir == 4) {
			if (row >= step && row < rows && column >= 0 && column + step < columns) {
				return state[row - step][column + step];
			} else {
				return 2;
			}
		} else if (dir == 5) {
			if (row >= 0 && row < rows && column >= 0 && column + step < columns) {
				return state[row][column + step];
			} else {
				return 2;
			}
		} else if (dir == 6) {
			if (row >= 0 && row + step < rows && column >= 0 &&  column + step < columns) {
				return state[row + step][column + step];
			} else {
				return 2;
			}
		} else if (dir == 7) {
			if (row >= 0 && row + step < rows && column >= 0 && column < columns) {
				return state[row + step][column];
			} else {
				return 2;
			}
		}
		return 2;
	}
	
	/**
	 * Returns the value after calculating by mini-Max algorithm.
	 * <p>
	 * @param depth the depth
	 * @param Alpha alpha
	 * @param Beta beta
	 * @param player the player index
	 * @param trace trace information
	 * @return the value after calculating by mini-Max algorithm
	 */
	private int miniMax(int depth, int Alpha, int Beta, int player, int[] trace) {
		int MaxMin;
		if (depth % 2 == 0) {
			Alpha = Integer.MIN_VALUE;        
		} else {
			Beta = Integer.MAX_VALUE; 
		}
		if (depth == 0) {
			int[] val = calBordValue();
			MaxMin = val[player] - val[(player+1) % 2] / 10 * 8;
			return MaxMin;
		}
		for (int i = 0; i < columns; i++) {
			if (nextPlace[i] >= 0) {
				if (depth % 2 == 0 && isWin(nextPlace[i], i, player)) {
					MaxMin = 20000000 * depth;
					if (depth == maxDepth * 2) {
						trace[i] = MaxMin;
					}
					return MaxMin;
				} else if (depth % 2 == 1 && isWin(nextPlace[i], i, (player + 1) % 2)) {
					MaxMin = -20000000 * depth;
					if (depth == maxDepth * 2) {
						trace[i] = MaxMin;
					}
					return MaxMin;
				} else {
					if (depth % 2 == 0) {
						state[nextPlace[i]][i] = player;
					} else {
						state[nextPlace[i]][i] = (player +1) % 2;
					}
					nextPlace[i]--;
					MaxMin =  miniMax(depth - 1, Alpha, Beta, player, trace);
				}
				if (depth == maxDepth * 2) {
					trace[i] = MaxMin;
				}
				if (depth % 2 == 0) {
					if (MaxMin > Alpha) {
						Alpha = MaxMin;
					}
				} else {
					if (MaxMin < Beta) {
						Beta = MaxMin;
					}
				}
				nextPlace[i]++;
				state[nextPlace[i]][i] = -1;
				if (Alpha > Beta) {
					return MaxMin;
				}
			}
		}
		if (depth % 2 == 0) {
			return Alpha;
		} else {
			return Beta;
		}
	}

	/**
	 * Returns calculated value.
	 * <p>
	 * @return calculated value
	 */
	private int[] calBordValue() {
		int[] bordValue = new int[2];
		for (int column = 0; column < columns; column++) {
			int row = nextPlace[column];
			if (row >= 0) {
				int[][] placeValue = getColumnState(row, column);
				for (int player = 0; player < 2; player++) {
					for (int dir = 0; dir < 4; dir++ ) {
						if (placeValue[player][dir] + placeValue[player][7 - dir] >= 3) {
							bordValue[player] += 1000000;
						}
					}
					for (int dir = 1; dir < 4; dir++) {
						if (placeValue[player][dir] == 1 
								&& placeValue[player][7 - dir] == 1) {
							if (block[dir] < 0 
									&& inSameLine(column, dir, 2)
									&& block[7 - dir] < 0
									&& inSameLine(column, 7 - dir, 2)) {
								bordValue[player] += 100000;
							} else if ((block[dir] < 0 && inSameLine(column, dir, 2))
									|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir, 2))) {
								bordValue[player] += 1000;
							} else {
								bordValue[player] += 10;
							}
						}
					}
					for (int dir = 0; dir < 8; dir++) {
						if (placeValue[player][dir] == 2) {
							if (block[dir] < 0
									&& inSameLine(column, dir, 3)
									&& block[7 - dir] < 0
									&& inSameLine(column, 7 - dir, 1)) {
								bordValue[player] += 50000;
							} else if ((block[dir] < 0 && inSameLine(column, dir, 3))
									|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir, 1))) {
								bordValue[player] += 500;
							} else {
								bordValue[player] += 5;
							}
						} else if (placeValue[player][dir] == 1 ) {
							if (block[dir] < 0
									&& inSameLine(column, dir, 2)
									&& block[7 - dir] < 0
									&& inSameLine(column, 7 - dir, 1)) {
								bordValue[player] += 5000;
							} else if ((block[dir] < 0 && inSameLine(column , dir , 2))
									|| (block[7 - dir] < 0 && inSameLine(column, 7 - dir, 1))) {
								bordValue[player] += 50;
							} else {
								bordValue[player] += 5;
							}
						}
						if (placeValue[player][dir] - block[dir] <= 4) {
							bordValue[player] += 3;
						}
					}
				}
			}
		}
		return bordValue;
	}

	/**
	 * Compares the specified object with this ComputerThinking object for equality. Returns true if and only 
	 * if the specified object is also a {@code ComputerThinking} object, both objects have the same row, 
	 * column, max depth and reference to the same next place array and chess map state array.
	 * <p>
	 * This implementation first checks if the specified object is this {@code ComputerThinking} object. If so, 
	 * it returns true; if not, it checks if the specified object is a {@code ComputerThinking} object. If not, 
	 * it returns false; if so, it iterates over both {@code ComputerThinking} objects, comparing corresponding 
	 * fields. If any comparison returns false, this method returns false. Otherwise it returns true when the 
	 * iterations complete.
	 * <p>
	 * @param o the object to be compared for equality with this {@code ComputerThinking} object
	 * @return true if the specified object is equal to this {@code ComputerThinking} object.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
		    return true;
		}
		if (! (o instanceof ComputerThinking)) {
		    return false;
		}
		ComputerThinking ct = (ComputerThinking) o;
		return rows == ct.rows
				&& columns == ct.columns
				&& nextPlace == ct.nextPlace
				&& state == ct.state
				&& maxDepth == ct.maxDepth;
	}
	
	/**
	 * Returns the hash code value for this {@code ComputerThinking} object.
	 * <p>
	 * @return the hash code value for this {@code ComputerThinking} object
	 */
	@Override
	public int hashCode() {
	    final int prime = 31;
		int hashCode = 17;
		hashCode = hashCode * prime + rows;
		hashCode = hashCode * prime + columns;
		hashCode = hashCode * prime + nextPlace.hashCode();
		hashCode = hashCode * prime + state.hashCode();
		hashCode = hashCode * prime + maxDepth;
		return hashCode;
	}
	
	/**
	 * Returns string representation of this {@code ComputerThinking} object. The string representation 
	 * consists of row, column number, next chess place array and chess map state array fields of the
	 * {@code ComputerThinking} object. Each fields are separated by the characters "," (comma).
	 * <p>
	 * @return string representation of this {@code ComputerThinking} object
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rows);
		sb.append("," + columns);
		for (int next : nextPlace) {
			sb.append("," + next);
		}
		for (int row[] : state) {
			for (int column : row) {
				sb.append("," + column);
			}
		}
		sb.append("," + maxDepth);
		return sb.toString();
	}
	
}