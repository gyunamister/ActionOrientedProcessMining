package org.processmining.EIS.OHP;

import java.util.ArrayList;
//Java program to implement Graph 
//with the help of Generics 
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectGraph<T> { 
 // We use Hashmap to store the edges in the graph 
	 public Map<T, List<T> > map = new HashMap<>();
	 public String name;
	 
	 public ObjectGraph(String s) {
		  name = s;
	 }
	
	 // This function adds a new vertex to the graph 
	 public void addVertex(T s) 
	 { 
	     map.put(s, new LinkedList<T>()); 
	 } 
	
	 // This function adds the edge 
	 // between source to destination 
	 public void addEdge(T source, 
	                     T destination, 
	                     boolean bidirectional) 
	 { 
	
	     if (!map.containsKey(source)) 
	         addVertex(source); 
	
	     if (!map.containsKey(destination)) 
	         addVertex(destination); 
	
	     map.get(source).add(destination); 
	     if (bidirectional == true) { 
	         map.get(destination).add(source); 
	     } 
	 } 
	
	 // This function gives the count of vertices 
	 public void getVertexCount() 
	 { 
	     System.out.println("The graph has "
	                        + map.keySet().size() 
	                        + " vertex"); 
	 } 
	
	 // This function gives the count of edges 
	 public void getEdgesCount(boolean bidirection) 
	 { 
	     int count = 0; 
	     for (T v : map.keySet()) { 
	         count += map.get(v).size(); 
	     } 
	     if (bidirection == true) { 
	         count = count / 2; 
	     } 
	     System.out.println("The graph has "
	                        + count 
	                        + " edges."); 
	 } 
	
	 // This function gives whether 
	 // a vertex is present or not. 
	 public boolean hasVertex(T s) 
	 { 
	     if (map.containsKey(s)) { 
//	        System.out.println("The graph contains " + s + " as a vertex.");
	    	 	return true;
	     } 
	     else { 
//	        System.out.println("The graph does not contain " + s + " as a vertex.");
	    	 	return false;
	     } 
	 } 
	
	 // This function gives whether an edge is present or not. 
	 public void hasEdge(T s, T d) 
	 { 
	     if (map.get(s).contains(d)) { 
	         System.out.println("The graph has an edge between "
	                            + s + " and " + d + "."); 
	     } 
	     else { 
	         System.out.println("The graph has no edge between "
	                            + s + " and " + d + "."); 
	     } 
	 } 
	
	 // Prints the adjancency list of each vertex. 
	 @Override
	 public String toString() 
	 { 
	     StringBuilder builder = new StringBuilder(); 
	
	     for (T v : map.keySet()) { 
	         builder.append(v.toString() + ": "); 
	         for (T w : map.get(v)) { 
	             builder.append(w.toString() + " "); 
	         } 
	         builder.append("\n"); 
	     } 
	
	     return (builder.toString()); 
	 }
	 
	// prints BFS traversal from a given source s 
	public List<T> BFS(T s) 
	{ 
		List<T> vertices = new ArrayList<T>();
	    // Mark all the vertices as not visited(By default 
	    // set as false)
		int V = map.keySet().size();
  
        // Create a queue for BFS 
        LinkedList<T> queue = new LinkedList<T>(); 
  
        // Mark the current node as visited and enqueue it 
        Map<T, String> visited = new HashMap<>();
        visited.put(s,"True"); 
        queue.add(s); 
  
        while (queue.size() != 0) { 
            // Dequeue a vertex from queue and print it 
	        s = queue.poll(); 
	        //System.out.print(s+" "); 
	        vertices.add(s);
	  
	        // Get all adjacent vertices of the dequeued vertex s 
	        // If a adjacent has not been visited, then mark it 
	        // visited and enqueue it 
	        Iterator<T> i = map.get(s).listIterator(); 
		    while (i.hasNext()) { 
		        T n = i.next(); 
		        if (visited.get(n)!="True") { 
		        		visited.put(n,"True"); 
		            queue.add(n); 
		        } 
		    }
	    } 
        return vertices;
	}	 
	 
} 