import java.util.*;

public class BlockComposition {

	private static ArrayList<int[][]> blockCompositions;
	
	public void fillCompositions() {
		blockCompositions = new ArrayList<int[][]>();
		blockCompositions.add(new int[][] {{0,0,0}, {0,1,0}, {0,0,0}});
		
		blockCompositions.add(new int[][] {{0,1,0}, {0,1,0}, {0,1,0}});
		blockCompositions.add(new int[][] {{0,0,0}, {1,1,1}, {0,0,0}});
		
		blockCompositions.add(new int[][] {{1,1,0}, {1,0,0}, {0,0,0}});
		blockCompositions.add(new int[][] {{0,1,1}, {0,0,1}, {0,0,0}});
		blockCompositions.add(new int[][] {{0,0,0}, {1,0,0}, {1,1,0}});
		blockCompositions.add(new int[][] {{0,0,0}, {0,0,1}, {0,1,1}});
		
		blockCompositions.add(new int[][] {{1,0,0}, {1,0,0}, {1,1,1}});
		blockCompositions.add(new int[][] {{1,1,1}, {1,0,0}, {1,0,0}});
		blockCompositions.add(new int[][] {{1,1,1}, {0,0,1}, {0,0,1}});
		blockCompositions.add(new int[][] {{0,0,1}, {0,0,1}, {1,1,1}});
		
		blockCompositions.add(new int[][] {{1,1,0}, {1,1,0}, {0,0,0}});
		
		blockCompositions.add(new int[][] {{0,1,0}, {1,1,1}, {0,1,0}});
		
		blockCompositions.add(new int[][] {{1,0,0}, {0,1,0}, {0,0,1}});
		blockCompositions.add(new int[][] {{0,0,1}, {0,1,0}, {1,0,0}});
		
		blockCompositions.add(new int[][] {{1,1,1}, {0,1,0}, {0,1,0}});
		blockCompositions.add(new int[][] {{0,1,0}, {0,1,0}, {1,1,1}});
		blockCompositions.add(new int[][] {{1,0,0}, {1,1,1}, {1,0,0}});
		blockCompositions.add(new int[][] {{0,0,1}, {1,1,1}, {0,0,1}});
		
		blockCompositions.add(new int[][] {{0,1,1}, {1,1,0}, {0,0,0}});
		blockCompositions.add(new int[][] {{1,1,0}, {0,1,1}, {0,0,0}});
		blockCompositions.add(new int[][] {{1,0,0}, {1,1,0}, {0,1,0}});
		blockCompositions.add(new int[][] {{0,0,1}, {0,1,1}, {0,1,0}});
		
		blockCompositions.add(new int[][] {{1,1,0}, {1,0,0}, {1,1,0}});
		blockCompositions.add(new int[][] {{0,1,1}, {0,0,1}, {0,1,1}});
		blockCompositions.add(new int[][] {{1,1,1}, {1,0,1}, {0,0,0}});
		blockCompositions.add(new int[][] {{0,0,0}, {1,0,1}, {1,1,1}});
		
		blockCompositions.add(new int[][] {{1,0,0}, {0,1,0}, {0,0,0}});
		blockCompositions.add(new int[][] {{0,0,1}, {0,1,0}, {0,0,0}});
		
		blockCompositions.add(new int[][] {{1,0,0}, {1,1,0}, {1,0,0}});
		blockCompositions.add(new int[][] {{0,0,1}, {0,1,1}, {0,0,1}});
		blockCompositions.add(new int[][] {{1,1,1}, {0,1,0}, {0,0,0}});
		blockCompositions.add(new int[][] {{0,0,0}, {0,1,0}, {1,1,1}});
		
		blockCompositions.add(new int[][] {{1,1,1}, {1,0,0}, {0,0,0}});
		blockCompositions.add(new int[][] {{1,1,1}, {0,0,1}, {0,0,0}});
		blockCompositions.add(new int[][] {{0,0,0}, {1,0,0}, {1,1,1}});
		blockCompositions.add(new int[][] {{0,0,0}, {0,0,1}, {1,1,1}});
		blockCompositions.add(new int[][] {{0,0,1}, {0,0,1}, {0,1,1}});
		blockCompositions.add(new int[][] {{1,0,0}, {1,0,0}, {1,1,0}});
		blockCompositions.add(new int[][] {{1,1,0}, {1,0,0}, {1,0,0}});
		blockCompositions.add(new int[][] {{0,1,1}, {0,0,1}, {0,0,1}});
		
	}
	
	public int[][] getComposition(){
		int random = (int)(Math.random()*blockCompositions.size());
		return blockCompositions.get(random);
	}
	
}
