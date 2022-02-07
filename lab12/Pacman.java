package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		Node node = end;
		while(node.parent != null) {
			node = node.parent;
			node.content = '.';
			if(node.parent.content == 'S') {
				break;
			}
		}
		/*if (node.content == 'S') {
			return;
		}
		
		node.parent.content = '.';
		
		backtrack(node.parent);*/
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			output.print(height + " " + width + "\n");
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					output.print(maze[i][j].content);
				}
				output.print("\n");
			}
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}

	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			String inputLine = input.readLine();
			height = Integer.parseInt(inputLine.split(" ")[0]);
			width = Integer.parseInt(inputLine.split(" ")[1]);
			maze = new Node[height][width];
			
			for(int i = 0; i < height; i++) {
				String charRead = input.readLine();
				for(int j = 0; j < width; j++) {
					if(charRead.charAt(j) == 'X') {
						maze[i][j] = new Node(i,j,'X');
					} else if(charRead.charAt(j) == 'S') {
						maze[i][j] = new Node(i,j,'S');
						startX = i;
						startY = j;
					} else if(charRead.charAt(j) == 'G') {
						maze[i][j] = new Node(i,j,'G');
					} else {
						maze[i][j] = new Node(i,j,' ');
					}
				}
			}
			
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		
		ArrayList<Node> neighbors = new ArrayList<Node>();

		// 1. Inspect the north neighbor
		north = maze[(currentNode.row)- 1][currentNode.col];
		if(!north.isWall() && !north.isVisited()) {
			neighbors.add(north);
			north.visited = true;
			north.parent = currentNode;
			
		}

		// 2. Inspect the south neighbor
		
		south = maze[(currentNode.row) + 1][currentNode.col];
		if(!south.isWall() && !south.isVisited()) {
			neighbors.add(south);
			south.visited = true;
			south.parent = currentNode;
		}

		// 3. Inspect the west neighbor
		
		west = maze[(currentNode.row)][currentNode.col-1];
		if(!west.isWall() && !west.isVisited()) {
			neighbors.add(west);
			west.visited = true;
			west.parent = currentNode;
		}

		// 4. Inspect the east neighbor
		
		east = maze[(currentNode.row)][currentNode.col+1];
		if(!east.isWall() && !east.isVisited()) {
			neighbors.add(east);
			east.visited = true;
			east.parent = currentNode;
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		Node x;
		LinkedList<Node> bfsQ = new LinkedList<Node>();
		bfsQ.add(maze[startX][startY]);
		
		while(bfsQ.size() != 0) {
			x = bfsQ.remove();
			ArrayList<Node> neighbors = getNeighbors(x);
			for(int i = 0; i < neighbors.size(); i++) {
				bfsQ.add(neighbors.get(i));
			}
			
			if(x.content == 'G') {
				backtrack(x);
				break;
			}
		}
		
		writeOutput();
			
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Node x;
		Stack<Node> dfsStack = new Stack<Node>();
		dfsStack.push(maze[startX][startY]);
		
		while(!dfsStack.empty()) {
			x = dfsStack.pop();
			ArrayList<Node> neighbors = getNeighbors(x);
			for(int i = 0; i < neighbors.size(); i++) {
				dfsStack.push(neighbors.get(i));
			}
			
			if(x.content == 'G') {
				backtrack(x);
				break;
			}
		}
		
		writeOutput();
	}

}
