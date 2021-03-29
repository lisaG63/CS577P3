import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class bandManager {
	
	
	
	private static int tourTime(ArrayList<ArrayList<Integer>> paths, int cities) {
		
		boolean visited[] = new boolean[cities];
		int week[] = new int[cities];
		visited[0] = true;
		week[0] = 0;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		
		while(!queue.isEmpty()) {
			int u = queue.remove();
			for (int i = 0; i < paths.get(u).size(); i ++) {
				if (!visited[paths.get(u).get(i)]) {
					visited[paths.get(u).get(i)] = true;
					week[paths.get(u).get(i)] = week[u] + 1;
					queue.add(paths.get(u).get(i));
					if (paths.get(u).get(i) == cities - 1) {
						return week[cities - 1];
					}
				}
			}
		}
		return -1;
	}
	
	private static void findCity(int d, boolean[] visited, int start, int cur, ArrayList<ArrayList<Integer>> paths, 
			ArrayList<ArrayList<Integer>> adj) {
		
		
		if (d == 5) {
			addEdge(paths, start, cur);
		} else {
			boolean[] v_copy = new boolean[visited.length];
			for (int j = 0; j < visited.length; j ++) {
				v_copy[j] = visited[j];
			}
			v_copy[cur] = true;

			
			
			for (int i = 0; i < adj.get(cur).size(); i ++) {
				if (!v_copy[adj.get(cur).get(i)]) {
					findCity(d + 1, v_copy, start, adj.get(cur).get(i), paths, adj);
				}
			}
		}
	}
	
	private static void addEdge(ArrayList<ArrayList<Integer>> adjacency, int startCity, int endCity) {
		adjacency.get(startCity).add(endCity);
		adjacency.get(endCity).add(startCity);
	}
	
	

	public static void main(String[] args) {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			String[] numbers = bf.readLine().split(" ");
			int cities = Integer.parseInt(numbers[0]);
			int roads  = Integer.parseInt(numbers[1]);
			ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < cities; i ++) {
				adj.add(new ArrayList<Integer>());
				paths.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < roads; i ++) {
				String[] edge = bf.readLine().split(" ");
				addEdge(adj, Integer.parseInt(edge[0]) - 1, Integer.parseInt(edge[1]) - 1);
			}
			for (int i = 0; i < cities; i ++) {
				boolean[] visited = new boolean[cities];
				findCity(0, visited, i, i, paths, adj);
			}
			
			
			
			System.out.println(tourTime(paths, cities));

	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		

	}

}
