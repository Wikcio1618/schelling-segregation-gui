package mainPackage;
import java.util.ArrayList;
import java.util.Random;

public class Society {
	// FIELDS
	private static Random random = new Random();
	private int[][] fields;
	private double tolerance = 0.2;
	private double fracVacant = 0.3;
	private int size = 50;
	private final int neiSize = 3;
	private int day;
	
	private GraphicFrame frame;
	private int _loopsLimit = size^2*100000;
	
	
	// CONSTRUCTORS	
	public Society() {
		initSociety();
	}
	
	public Society(GraphicFrame frame) {
		this.frame = frame;
		initSociety();
	}
	
	// METHODS
	
	public void initSociety() {
		day = 0;
		_loopsLimit = size^2;
		fields = new int[size][size];

        // Calculate the number of empty cells
        int numAgentsOfKind = (int) ((1-fracVacant) / 2 * size * size);

        int counter = 0;
        while (counter < numAgentsOfKind) {
        	int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (fields[row][col] == 0) {
            	fields[row][col] = -1;
            	counter++;
            }
        }
        counter = 0;
        while (counter < numAgentsOfKind) {
        	int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (fields[row][col] == 0) {
            	fields[row][col] = 1;
            	counter++;
            }
        }
	}
	
	public void nextDay() {
		day++;
		int xAgent, yAgent;
		
//		choose agent for consideration
		int counter = 0;
		while(true) {
			counter++;
			int row = random.nextInt(size);
			int col = random.nextInt(size);
			if (fields[row][col] != 0) {
				xAgent = col;
				yAgent = row;
				break;
			}
			if (counter > _loopsLimit) {
//				System.out.println("Agent limit reached");
//				frame.setRunning(false);
//				return;
			}	
		}
		
		int type = fields[yAgent][xAgent];
		if (!checkHood(xAgent, yAgent, type)) {
			int xPlace, yPlace;
			
			counter = 0;
			while(true) {
				counter++;
				int row = random.nextInt(size);
				int col = random.nextInt(size);
				if (fields[row][col] == 0 
				/* && checkHood(row, col, type) */) {
					xPlace = col;
					yPlace = row;
					break;
				}
				if (counter > _loopsLimit) {
//					System.out.println("New place limit reached");
//					frame.setRunning(false);
//					return;
				}	
			}
			
			fields[yPlace][xPlace] = type;
			fields[yAgent][xAgent] = 0;
		}
		
		
		
	}
	
	private boolean checkHood(int xPos, int yPos, int type) {
//		System.out.println("-------------");
//		System.out.println("Checking positions x: " + xPos + " y: " + yPos);
		int x1_nei = xPos - neiSize > 0 ? xPos - neiSize : 0;
		int x2_nei = xPos + neiSize < size ? xPos + neiSize : size-1;
		int y1_nei = yPos - neiSize > 0 ? yPos - neiSize : 0;
		int y2_nei = yPos + neiSize < size ? yPos + neiSize : size-1;
	
		int sum_all = 0;
		int sum_same = 0;
		for (int i = y1_nei; i < y2_nei+1; i++) 
			for (int j = x1_nei; j < x2_nei+1; j++) {
				// ignore center of the hood
				if (i == yPos && j == xPos)
					continue;
				if (fields[i][j] == type)
					sum_same++;
				if (fields[i][j] != 0)
					sum_all++;
			}
		
		boolean decision;
		if (sum_all == 0)
			decision = true;
		else
			decision = (double) sum_same / sum_all > tolerance;
		
//		System.out.println("same: " + sum_same + " all: " + sum_all + " result: " + (double) sum_same / sum_all + " " + decision);
		return decision;
	}
	
//	Returns number of different neighbourhoods divided by number of all neighbourhoods
	public double getNeighbourhoodLikeness() {
		int all = 0;
		int nSame = 0;
		
		for (int i = 0; i < size-1; i++)
			for (int j = 0; j < size-1; j++) {
				int _down = fields[i][j] * fields[i+1][j];
				int _right = fields[i][j] * fields[i][j+1];
				int _diag = fields[i][j] * fields[i+1][j+1];
				
				all += Math.abs(_down);
				all += Math.abs(_right);
				all += Math.abs(_diag);
				
				nSame += (_down + 1) / 2;
				nSame += (_right + 1) / 2;
				nSame += (_diag + 1) / 2;
			}
		
		double neighbourhoodDifference = (double) nSame / all;
		
		return neighbourhoodDifference;
	}
	
	
	public int[][] getFields() {
		return fields;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public double getFracVacant() {
		return fracVacant;
	}

	public void setFracVacant(double fracVacant) {
		this.fracVacant = fracVacant;
	}

	public int getDay() {
		return day;
	}
}
