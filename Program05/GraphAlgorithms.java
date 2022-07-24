import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class GraphAlgorithms {

	PriorityQueue<PathState> pq; // Used for Dijkstra's shortest path algorithm
	Queue<Vertex> queue; // use for BFS
	Stack<Vertex> stack; // used for DFS and for Topological Sort
	Set<String> visited; // names of the visited vertices

	public GraphAlgorithms() {
		pq = new PriorityQueue<PathState>();
	}

	public String findShortestPath(Graph graph, String startVertexName, String endVertexName) {
		pq = new PriorityQueue<PathState>();
		visited = new TreeSet<String>();
		
		pq.add(new PathState(graph.getVertex(startVertexName), 0, startVertexName));
		
		while (!pq.isEmpty()) {
			PathState nextEntry = pq.remove();
			
			if (!visited.contains(nextEntry.vertex.getName())) {
				visited.add(nextEntry.vertex.getName());
				if (nextEntry.vertex.equals(graph.getVertex(endVertexName))) {
					return "Shortest Path " + nextEntry.pathToThisVertex +
						   "\nTotal weight: " + nextEntry.totalPathWt;
				} else {
					Vertex currentVertex = nextEntry.vertex;
					int currentCost = nextEntry.totalPathWt;
					String currentPath = nextEntry.pathToThisVertex;
					
					Map <String, Integer> map = currentVertex.getAdjacentVerticesWeighted();
					
					for (String vertex : currentVertex.getAdjacentVertices()) {
						if (!visited.contains(vertex)) {
							int nextCost = currentCost + map.get(vertex);
							String nextPath = currentPath + " " + vertex;
							pq.add(new PathState(graph.getVertex(vertex), nextCost, nextPath));
						}
					}
				}
			}
			
			
			
		}
		return "";
	}

	public String breadthFirstTraversal(Graph graph, String startVertexName) {
		String result = "";
		visited = new TreeSet<String>();
		queue = new LinkedList<Vertex>();

		queue.add(graph.getVertex(startVertexName));
		visited.add(startVertexName);
		result = startVertexName + " ";

		while (!queue.isEmpty()) {
			Vertex nextVertex = queue.remove();
			Set<String> vertices = nextVertex.getAdjacentVertices();

			for (String vertex : vertices) {
				if (!visited.contains(vertex)) {
					queue.add(graph.getVertex(vertex));
					visited.add(vertex);
					result += vertex + " ";
				}
			}
		}

		return result.trim();
	}

	public String depthFirstTraversal(Graph graph, String startVertexName) {
		String result = "";

		visited = new TreeSet<String>();
		stack = new Stack<Vertex>();

		stack.push(graph.getVertex(startVertexName));
		visited.add(startVertexName);
		result = startVertexName;

		while (!stack.isEmpty()) {
			Vertex topVertex = stack.peek();
			boolean success = false;
			for (String vertex : topVertex.getAdjacentVertices()) {
				if (!visited.contains(vertex)) {
					Vertex nextVertex = graph.getVertex(vertex);
					result += " " + vertex;
					stack.push(nextVertex);
					visited.add(vertex);
					success = true;
					break;
				}
			}
			if (success == false)
				stack.pop();
		}

		return result.trim();
	}

	public String topologicalSort(Graph graph) {
		String result = "";
		stack = new Stack<Vertex>();
		visited = new TreeSet<String>();

		// Goes until all are visited
		while (visited.size() != graph.vertexCount()) {
			
			for (Vertex vertex : (TreeSet<Vertex>) graph.getVertices()) {
				boolean nextNodeIsGood = true;

				// If current vertex is visited, it will fail
				if (visited.contains(vertex.getName()))
					nextNodeIsGood = false;

				// If ajacent vertices to current vertex are visited, It will fail
				for (String ajvertex : vertex.getAdjacentVertices()) {
					if (!visited.contains(ajvertex)) {
						nextNodeIsGood = false;
					}
				}

				// If current vertex and ajacent verticies are not visited, then add to list
				if (nextNodeIsGood == true) {
					visited.add(vertex.getName());
					stack.push(vertex);
					result = vertex.getName() + " " + result;
					break; // breaks so it maintains alphabetical order
				}
			}
		}

		return result.trim();
	}

	public static void main(String[] args) throws IOException {
		/*
		// TEST CODE BELOW
		Graph graph = new Graph("graphData3.csv");
		GraphAlgorithms graphAlgorithms = new GraphAlgorithms();

		System.out.println(graphAlgorithms.findShortestPath( graph, "CVG", "LAX"));
		System.out.println(graphAlgorithms.findShortestPath( graph, "CVG", "DEN"));
		System.out.println(graphAlgorithms.findShortestPath( graph, "CVG", "DFW"));
		System.out.println(graphAlgorithms.breadthFirstTraversal(graph, "CVG"));
		System.out.println(graphAlgorithms.depthFirstTraversal(graph, "CVG"));
		System.out.println(graphAlgorithms.topologicalSort(graph));
		*/
	}

	public class PathState implements Comparable {
		public Vertex vertex;
		public int totalPathWt;
		public String pathToThisVertex;

		public PathState(Vertex v, int wt, String path) {
			vertex = v;
			totalPathWt = wt;
			pathToThisVertex = path;
		}

		@Override
		public int compareTo(Object other) {
			if (this.totalPathWt < ((PathState) other).totalPathWt)
				return -1;
			else if (this.totalPathWt > ((PathState) other).totalPathWt)
				return 1;
			else
				return 0;

		}
	}
}
