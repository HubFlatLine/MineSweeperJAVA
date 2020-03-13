package model;

public class Board {
	private int[][] board = new int[9][9];
	private int[][] boardMask = new int[9][9];
	private int bombs = 1;
	private int remains = board.length*board[0].length - bombs;
	private int tern=0;
	private int totalTime = 0;
	private String challengerName = "unknown";
	private boolean explosionFlag = false;

	public Board(int b) {
		clearBoard();
		if ((0 < b)&&(b < (board.length*board[0].length/2))) bombs = b;
		setBomb();
		remains = board.length*board[0].length - bombs;
		setValue();
		explosionFlag = false;
	}

	int getSize(int d) {
		if (d == 0) return board.length;
		else return board[0].length;
	}

	public int getTern() {
		return tern;
	}
	public void incTern() {
		tern++;
	}
	public int getRemains() {
		return remains;
	}

	public int getCountOfRemains() {
		int cnt = 0;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if (boardMask[i][j] == 0) cnt++;
			}
		}
		remains = board.length*board[0].length - bombs - cnt;
		return remains;
	}

	public String getIMG(int x, int y, boolean allOpen) {
		String imgFileName=getCellStr(x,y,allOpen);
		switch(imgFileName) {
		case "@ ":
			imgFileName="bomb.jpg";
			break;
		case "* ":
			imgFileName="close.jpg";
			break;
		case "  ":
			imgFileName="empty.jpg";
			break;
		case "? ":
			imgFileName="question.png";
			break;
		case "1 ":
			imgFileName="one.png";
			break;
		case "2 ":
			imgFileName="two.png";
			break;
		case "3 ":
			imgFileName="three.png";
			break;
		case "4 ":
			imgFileName="four.png";
			break;
		case "5 ":
			imgFileName="five.png";
			break;
		case "6 ":
			imgFileName="six.png";
			break;
		case "7 ":
			imgFileName="seven.png";
			break;
		case "8 ":
			imgFileName="eight.png";
			break;
		case "9 ":
			imgFileName="nine.png";
			break;
		default:
			imgFileName="zero.png";
			break;
		}
		return imgFileName;
	}
	String getCellStr(int x, int y, boolean allOpen) {
		String strCell = "* ";
		if(!allOpen) {
		if ((boardMask[x][y] == 1)) return strCell;
		if ((boardMask[x][y] == 2)) return "? ";
		}
		int cell = board[x][y];
		switch (cell) {
		case -1:
			strCell = "@ ";
			break;
		case 0:
			strCell = "  ";
			break;
		default:
			strCell = String.valueOf(cell)+" ";
			break;
		}
		return strCell;
	}

	void clearBoard() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j] = 0;
				boardMask[i][j] = 1;
			}
		}
	}

	public int getBombs() {
		return bombs;
	}
	void setBomb() {
		int x,y;
		int n = bombs;
		while (n > 0) {
			x = (int)(Math.random()*board.length);
			y = (int)(Math.random()*board[0].length);
			if (board[x][y] != -1) {
				board[x][y] = -1;
				n--;
			}
		}
	}

	void setValue() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if (board[i][j] != -1) board[i][j] = countAroundBomb(i,j);
			}
		}
	}

	int countAroundBomb(int x, int y) {
		int x1,y1;
		int cnt=0;
		for (int i=-1; i<=1; i++) {
			x1 = x + i;
			for (int j=-1; j<=1; j++) {
				y1 = y + j;
				if ((i==0)&&(j==0)) {
				} else {
					if (((0<=x1)&&(x1<board.length))&&((0<=y1)&&(y1<board[0].length))) {
						if (board[x1][y1] == -1) cnt++;
					}
				}
			}
		}
		return cnt;
	}

	public void openCellMask(int x, int y, int z) {
		if (z==0) {
			boardMask[x][y] = 0;
			if (board[x][y] == -1) explosionFlag = true;
			if (board[x][y] == 0) openAroundCellMask(x,y);
		} else {
			boardMask[x][y]=z;	//？印
		}
	}

	void openAroundCellMask(int x, int y) {
		int x1,y1;
		for (int i=-1; i<=1; i++) {
			x1 = x + i;
			for (int j=-1; j<=1; j++) {
				y1 = y + j;
				if ((i==0)&&(j==0)) {
				} else {
					if ((0<=x1)&&(x1<board.length)&&(0<=y1)&&(y1<board[0].length)) {
						if (board[x1][y1] > 0) boardMask[x1][y1] = 0;
					}
				}
			}
		}
		for (int i=-1; i<=1; i++) {
			x1 = x + i;
			for (int j=-1; j<=1; j++) {
				y1 = y + j;
				if ((i==0)&&(j==0)) {
				} else {
					if ((0<=x1)&&(x1<board.length)&&(0<=y1)&&(y1<board[0].length)) {
						if ((board[x1][y1] == 0)&&(boardMask[x1][y1] == 1)) openCellMask(x1,y1,0);
					}
				}
			}
		}
	}

	public boolean isEnd() {
		if (explosionFlag) return true;
		int cnt = 0;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if (boardMask[i][j] == 0) cnt++;
			}
		}
		if ((board.length*board[0].length-cnt) == bombs) return true;
		else return false;
	}

	public boolean isExplosion() {
		return explosionFlag;
	}

}
