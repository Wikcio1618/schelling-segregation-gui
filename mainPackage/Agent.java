package mainPackage;

public class Agent {
	private int xpos, ypos, type;
	
	public Agent(int xpos, int ypos, int type) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.type = type;
	}
	
	public void move(int newX, int newY) {
		this.xpos = newX;
		this.ypos = newY;
	}
	
	public boolean checkHood(int xpos, int ypos, int[][] fields, int L, double tolerance, int neiSize) {
	
		int x1_nei = xpos - neiSize > 0 ? xpos - neiSize : 0;
		int x2_nei = xpos + neiSize < L ? xpos + neiSize : L-1;
		int y1_nei = ypos - neiSize > 0 ? ypos - neiSize : 0;
		int y2_nei = ypos + neiSize < L ? ypos + neiSize : L-1;
	
		int sum_all = 0;
		int sum_same = 0;
		for (int i = y1_nei; i <= y2_nei; i++) 
			for (int j = x1_nei; j <= x2_nei; j++) {
				// ignore center of neighbourhood
				if (i == ypos && j == xpos)
					continue;
				if (fields[i][j] == this.type)
					sum_same++;
				if (fields[i][j] != 0)
					sum_all++;
			}
		
		boolean decision;
		if (sum_all == 0)
			decision = true;
		else
			decision = sum_same / sum_all < tolerance;
		
		return decision;
	}

	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
