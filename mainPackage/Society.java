package mainPackage;
import java.util.ArrayList;
import java.util.Random;

public class Society {
	// FIELDS
	private static Random random = new Random();
	private int[][] fields;
	private ArrayList<Agent> agents = new ArrayList<Agent>();
	private double tolerance = 0.2;
	private double fracVacant = 0.3;
	private int size = 100;
	private final int neiSize = 1;
	private GraphicFrame frame;
	
	
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
		fields = new int[size][size];
		agents.clear();

        // Calculate the number of empty cells
        int numAgentsOfKind = (int) ((1-fracVacant) / 2 * size * size);

        int counter = 0;
        while (counter < numAgentsOfKind) {
        	int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (fields[row][col] == 0) {
            	agents.add(new Agent(col, row, -1));
            	fields[row][col] = -1;
            	counter++;
            }
        }
        counter = 0;
        while (counter < numAgentsOfKind) {
        	int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (fields[row][col] == 0) {
            	agents.add(new Agent(col, row, 1));
            	fields[row][col] = 1;
            	counter++;
            }
        }
        
        for (int i = 0; i < size; i++) 
			for (int j = 0; j < size; j++) 
				if (fields[i][j] == 0)
					agents.add(new Agent(j, i, 0));
	}
	
	public void nextDay() {
		Agent agent = null;
		counter = 0;
		while (agent == null) {
			int rand_idx = random.nextInt(agents.size());
			Agent temp_agent = agents.get(rand_idx);
			if (temp_agent.getType() != 0)
				agent = temp_agent;
		}
		
		int limit = size^2;
		if (!agent.checkHood(agent.getXpos(), agent.getYpos(), fields, size, tolerance, neiSize)) {
			Agent newPlace = null;
			int counter = 0;
			while (newPlace == null) {
				counter++;
				if (counter > limit) {
					if (frame != null)
						frame.setRunning(false);
					System.out.println("New place limit reached");
					break;
				}
				int rand_idx = random.nextInt(agents.size());
				Agent temp_place = agents.get(rand_idx);
				if (temp_place.getType() == 0 
						 /* && agent.checkHood(temp_place.getXpos(), temp_place.getYpos(), fields, size, tolerance, neiSize) */)
					newPlace = temp_place;
			}
			
			if (agent != null && newPlace != null) {
			int _xAgent = agent.getXpos();
			int _yAgent = agent.getYpos();
			agent.move(newPlace.getXpos(), newPlace.getYpos());
			fields[newPlace.getYpos()][newPlace.getXpos()] = agent.getType();
			newPlace.move(_xAgent, _yAgent);
			fields[_yAgent][_xAgent] = 0;		
			}
		}
			
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
}
