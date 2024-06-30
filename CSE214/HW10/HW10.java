import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashMap;
import java.util.Stack;
import java.util.Queue;
import java.util.TreeSet;
import java.util.HashSet;

public class HW10 implements Connectable {
    HashMap<Integer,Node> nodes;
    int nodesCount;
    int edgesCount;

    class Node{
        int data;
        HashMap<Integer,Node> edges;
        HashMap<Node,Double> weight;

        public Node(int data){
            this.data = data;
            edges = new HashMap<Integer,Node>();
            weight = new HashMap<Node,Double>();
        }
    }

    class Edge implements Comparable<Edge>{
        int source,target;
        double weight;

        public Edge(int source, int target, double weight){
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        public int compareTo(Edge newEdge){
            if(Math.abs(weight - newEdge.weight) <=0.000001) return 0;
            else if(weight > newEdge.weight) return 1;
            else return -1;
        }

        public boolean equals(Object o){
            if(!(o instanceof Edge)) throw new IllegalArgumentException();
            Edge newEdge = (Edge)o;
            if(newEdge.source == source){
                return newEdge.target == target;
            }

            if(newEdge.source == target){
                return newEdge.target == source;
            }
            return false;
        }

    }

    public HW10(){
        nodes = new HashMap<Integer,Node>();
        nodesCount=0;
        edgesCount=0;

    }
   
    // return the set of nodes
    /*
     * This method simply calls HashMap's keySet method.
     * keySet method calls a default constructor for KeySet class.
     * It is just an assignment or a comparison operation.
     * 
     * Time complexity: O(1).
     * Space complexity: O(1).
     * The dynamic allocation for KeySet instance is only called once.
     */
    public Set<Integer> nodeSet(){
        return nodes.keySet();
    }

    // return the set of neighbors connected to 'node'
    /*
     * This method search a node and calls HashMap's keySet method.
     * Since HashMap can directly access its inner table with hash, HashMap's get method's time complexity is O(1).
     * Explanation about HashMap's keySet method is written in nodeSet method's comment.
     * Everything else is just an assignment or a comparison.
     * 
     * Time Complexity: O(1).
     * Space Complexity:O(1).
     * The only dynamic allocation operation in this method is keySet method.
     * However, the space complexity of keySet method is O(1).
     */
	public Set<Integer> getNeighbors(int node){
        Node sourceNode = nodes.get(node);
        if(sourceNode == null) return null;
        return sourceNode.edges.keySet();
    } 
	

    // return the iterator on nodes in a breadth-first manner
    /*
     * This method first search for input data's node. If it doesn't exit, the method throws exception.
     * Then, it put the node into a HashSet 'visited', a queue 'usingQueue', and a queue 'returnQueue'.
     * 
     * Then a while loop repeats until the usingQueue is empty.
     * The context of the while loop first dequeues the usingQueue and gets a dequeued node.
     * Then, the neighbors of the dequeued node are enqueued to the 'visited', 'usingQueue', and 'returnQueue'
     * if the neighbor is not in the 'visited'.
     * Finally, the method returns returnQueue's iterator.
     * 
     * Time complexity:
     * Except the while loops, everything is just an assignment or a comparison.
     * For the worst case, every nodes in the graph are connected with each other.
     * Then, after a start node is inserted into the queue, n-1 nodes are inserted into the queue.
     * n is the number of nodes in the graph.
     * After, n-2 nodes are inserted, then n-3 nodes are inserted and so on.
     * Thus, in the worst case, the outer while loop has to iterate 1+(n-1)+(n-2)+(n-3)+...+2+1 times.
     * That is upper bounded by n^2.
     * The inner while loop iterates as the number of neighbors the current node has. 
     * That is upper bounded by n.
     * Since these two while loops are nested and the other operations are just an assignment or a comparison,
     * the overall time complexity is O(n^3) where n is the number of nodes in the graph.
     * 
     * Space complexity: 
     * The dynamic allocation that is not inside of while loops are executed only once. 
     * Thus, those allocation's space complexities are O(1).
     * For the inner while loop, the while loop repeats for n times(upper bounded).
     * The add methods of HashSet and queue dynamic allocates only one instance.
     * Thus, the inner while loops space complexity is O(n).
     * For the outer while loop, it iterates n^2 times(upper bounded).
     * The iterator dynamic allocates an instance only once, so its space complexity is O(1).
     * 
     * Since the outer loop and inner loop is nested, the overall space complexity is O(n^3). 
     * 
     */
	public Iterator<Integer> breadthFirstIterator(int src){
        HashSet<Integer> visited = new HashSet<Integer>();
        Queue<Integer> usingQueue = new LinkedList<Integer>();
        Queue<Integer> returnQueue = new LinkedList<Integer>();

        Node node = nodes.get(src);
        if(node == null) throw new IllegalArgumentException();
        visited.add(node.data);
        usingQueue.add(node.data);
        returnQueue.add(node.data);

        while(usingQueue.peek() != null){
            node = nodes.get(usingQueue.poll());
            Iterator<Integer> it = getNeighbors(node.data).iterator();
            while(it.hasNext()){
                int key = it.next();
                if(visited.contains(key)) continue;
                visited.add(key);
                usingQueue.add(key);
                returnQueue.add(key);
            }
        }

        return returnQueue.iterator();
    } 

    // same as above in a depth-first manner
    /*
     * This method first search for input data's node. If the node doesn't exist, the method throws exception.
     * Then the node gets inserted to HashSet 'visited' and Stack 'usingStack'.
     * 
     * Then a while loop repeats until 'usingStack' is empty.
     * Inside the outer while loop, the while loop search for a node by using stack's popped data.
     * Then it adds the data into the queue and inserts the node's neighbors into HashSet 'visited' and Stack 'usingStack'
     * if the neighbor is not in the HashSet.  
     * Finally, the method returns queue's iterator.
     * 
     * Time complexity: 
     * Just like breadthFirstIterator(), except the while loops, everything is just an assignment or a comparison.
     * The repetition of outer while loop is upper bounded by n^2.
     * The repetition of inner while loop is upper bounded by n.
     * Thus, the overall time complexity is O(n^3).
     * 
     * Space complexity:
     * Just like breadthFirstIterator(), except the while loops, the dynamic allocation is called constant time.
     * For the inner while loop, the add and push function execute dynamic allocation only once.
     * Thus, the inner while loop's space complexity is O(n).
     * For the outer while loop, the iterator method execute dynamic allocation only once.
     * Therefore, the overall space complexity is O(n^3).
     */
	public Iterator<Integer> depthFirstIterator(int src){
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> usingStack = new Stack<Integer>();
        Queue<Integer> returnQueue = new LinkedList<Integer>();

        Node node = nodes.get(src);
        if(node == null) throw new IllegalArgumentException();
        visited.add(node.data);
        usingStack.push(node.data);

        while(!usingStack.isEmpty()){
            int data = usingStack.pop();
            node = nodes.get(data);
            returnQueue.add(data);
            Iterator<Integer> it = getNeighbors(node.data).iterator();
            while(it.hasNext()){
                int key = it.next();
                if(visited.contains(key)) continue;
                visited.add(key);
                usingStack.push(key);
            }
        }

        return returnQueue.iterator();
    }
	

    // add a new vertex into the current graph.
    /*
     * Node class represents a vertex in a graph. The integer data is contained in Node class's 'data' field.
     * Also, HW10 class has HashMap field named nodes. It has integer data as a key and Node instance as a value.
     * With nodes field, HW10 class can track the nodes in the current graph.
     * 
     * This method first check whether the graph already has the input data.
     * If the data already exists, the method just ends.
     * If it doesn't exists, a new Node is created and added to the nodes field.
     * Then it increments the nodesCount field.
     * 
     * Time Complexity: Amortized constant time.
     * Except HashMap's put method, everything is just an assignment or a comparison.
     * Since HashMap uses hash to access certain index of its inner array, most of operations are just an assignment or a comparison.
     * However, the put method rarely executes rehash operation.
     * The rehash operation basically resizes the inner array and iterates the old array from the first element to the last element.
     * That is O(n) where n is the old array's size.
     * Since rehash operations are rarely called and everything else is just an assignment or a comparison,
     * the overall time complexity is Amortized constant time.
     * 
     * Space Complexity: O(1).
     * No matter what the input data is, the dynamic allocations in this method are called maximum three times.
     * First is the constructor for Node class.
     * The other is called inside the Node's constructor, which are HashMap constructors. They are called only two times.
     * Since the dynamic allocations are called only three times regardless to the input data,
     * the overall space complexity is O(1).
     */
	public void addNode(int node){
        if(nodes.containsKey(node)) return;
        Node newNode = new Node(node);
        nodes.put(newNode.data, newNode);
        nodesCount++;
    }
    
    // add a new edge. also add non-existing nodes. return false if edge already exists.
    /*
     * The addEdge method first search for nodes that represents the input data.
     * If there is no node, it creates a node for that data and add to nodes field.
     * 
     * The Node class has two HashMap fields.
     * The first is 'edges' field, that has integer data as key and Node instance as value.
     * The second is 'weight' field, that has Node instance as key and double value 'weight' as value.
     * The edges field represents directed edge.
     * Since this graph is undirected graph, a undirected edge is represented as two directed edge that points each other.
     * For example, if a Node A has another Node B in its edges field, the Node B also has Node A in its edges field.
     * THe weight field stores weight for each directed edge.
     * 
     * After searching/adding nodes, this method checks whether the inserting edge already exists or not.
     * If does, the method returns false.
     * If not, the method adds each nodes into each other's edges and weight field.
     * Then, it increments edgesCount by 1.
     * 
     * Time Complexity: Amortized constant time.
     * As it is explained in addNode()'s comment, addNode() and HashMap's put method's time complexities are amortized constant time.
     * Since HashMap uses hash to access certain index of its inner array, get and containsKey methods' time complexities are O(1).
     * Everything else are just an assignment or comparison.
     * Thus, the overall time complexity is amortized constant time.
     * 
     * Space Complexity: O(1).
     * The dynamic allocation operations in this method are addNode and put method.
     * However, as written in addNode method's comment, addNode method's space complexity is O(1).
     * The put method executes the dynamic allocation only once, so its space complexity is O(1).
     * Therefore, the overall space complexity is O(1).
     */
	public boolean addEdge(int source, int target, double w){
        Node sourceNode = nodes.get(source);
        if(sourceNode == null){
            addNode(source);
            sourceNode = nodes.get(source);
        }
        Node targetNode = nodes.get(target);
        if(targetNode == null){
            addNode(target);
            targetNode = nodes.get(target);
        }

        if(sourceNode.edges.containsKey(target) || targetNode.edges.containsKey(source)) return false;
        sourceNode.edges.put(target, targetNode);
        sourceNode.weight.put(targetNode, w);

        targetNode.edges.put(source,sourceNode);
        targetNode.weight.put(sourceNode,w);

        edgesCount++;


        return true;
    }

    // remove node. return false if node doesn't exist.
    /*
     * This method first search for input data's node. If it doesn't exist, the method returns false.
     * Then the method removes every edges connected to the node.
     * Finally, the node gets removed from nodes field and nodesCount gets decreased by 1.
     * 
     * Time Complexity: Linear.
     * Except the while loop, everything is just an assignment or a comparison.
     * The while loop repeats as the number of edges the node is connected.
     * If there is n nodes in a graph, a node can have maximum n-1 edges.
     * Since the context of while loop has constant time complexity,
     * the overall time complexity is O(n) where n is the number of nodes in the graph.
     * 
     * Space Complexity: Constant.
     * The only dynamic allocation in this method is the iterator method.
     * However, it is only called once.
     * Thus, the overall space complexity is O(1).
     */
	public boolean removeNode(int node){
        Node sourceNode = nodes.get(node);
        if(sourceNode == null) return false;

        Iterator<Integer> it = sourceNode.edges.keySet().iterator();
        while(it.hasNext()){
            int key = it.next();
            removeEdge(node, key);
        }

        nodes.remove(node);
        nodesCount--;
        return true;
    } 

    // remove edge. return false if edge doesn't exist.
    /*
     * This method first search for source data's node and its directed edge to target data's node.
     * If one of nodes or edge does not exist, the method returns false.
     * Then, the method removes an edge and weight from each node.
     * Finally, edgesCount is decreased by 1.
     * 
     * Time Complexity: O(1).
     * HashMap's get and remove methods' time complexities are O(1).
     * This is because HashMap can access to its inner table by hash.
     * Everything else are just an assignment or a comparison.
     * 
     * Space Complexity: O(1).
     * There is no dynamic allocation in this method.
     */
	public boolean removeEdge(int source, int target){
        Node sourceNode = nodes.get(source);
        if(sourceNode == null) return false;
        Node targetNode = sourceNode.edges.get(target);
        if(targetNode == null) return false;

        sourceNode.weight.remove(targetNode);
        sourceNode.edges.remove(target);

        targetNode.weight.remove(sourceNode);
        targetNode.edges.remove(source);

        edgesCount--;
        return true;
    }

    // Returns true if source-target is a valid edge. The source-target order doesn't matter in undirected graphs.
    /*
     * This method search for source data's node and its directed edge to target data's node.
     * If one of nodes or edge does not exist, the method returns false.
     * Else, the method returns true.
     * 
     * Time Complexity: Constant.
     * Everything in this method is just an assignment or a comparison.
     * HashMap can directly access its inner table by using hash.
     * Thus, the overall time complexity is O(1).
     * 
     * Space Complexity: Constant.
     * There is no dynamic allocation in this method.
     */
	public boolean isEdge(int source, int target){
        Node sourceNode = nodes.get(source);
        if(sourceNode == null) return false;
        Node targetNode = sourceNode.edges.get(target);
        if(targetNode == null) return false;
        return true;
    }
	

    // return weight of edge (what if edge doesn't exist?).
    /*
     * This method first search for source data's node and its directed edge to target data's node.
     * If one of node or edge does not exist, the method throws exception.
     * Then, the method returns target edge's weight through node's weight field.
     * 
     * Time complexity: Constant.
     * Everything in this method is just an assignment or a comparison.
     * HashMap can directly access to its inner table by using hash.
     * Exception is calling a constructor, that is just an assignment or a comparison.
     * Thus, the overall time complexity is O(1).
     * 
     * Space complexity: Constant.
     * The only dynamic allocation in this method is IllegalArgumentException().
     * However, it is called maximum two times.
     * Thus, the overall space complexity is O(1).
     */
	public double getWeight(int source, int target){
        Node sourceNode = nodes.get(source);
        if(sourceNode == null) throw new IllegalArgumentException();
        Node targetNode = sourceNode.edges.get(target);
        if(targetNode == null) throw new IllegalArgumentException();

        return sourceNode.weight.get(targetNode);
    }

    // set the weight. existing weights are overwritten. if edge doesn't exist, return false;
    /*
     * This method first search for source data's node and its directed edge to target data's node.
     * If one of node or edge does not exist, the method returns false.
     * Then, the method put the new weight to each nodes' weight field.
     * 
     * Time complexity: Amortized constant time.
     * Except HashMap's put method, everything is just an assignment or a comparison.
     * The put method is also consisted with an assignment or a comparison, but it rarely do rehash.
     * The rehash's time complexity is O(n) where n is the size of the graph.
     * Thus, the overall time complexity is amortized constant time.
     * 
     * Space complexity: Constant.
     * The put method executes dynamic allocation only once, so its space complexity is O(1).
     */
	public boolean setWeight(int source, int target, double w){
        Node sourceNode = nodes.get(source);
        if(sourceNode == null) return false;
        Node targetNode = sourceNode.edges.get(target);
        if(targetNode == null) return false;

        sourceNode.weight.put(targetNode,w);
        targetNode.weight.put(sourceNode,w);

        return true;
    }
    
    // return the total number of nodes. should have O(1) time complexity.
    /*
     * HW10 class's nodesCount field  is already tracking the number of nodes.
     * Therefore, this method simply returns the nodesCount field.
     * That is, both time and space complexity is O(1).
     */
	public int numNodes(){
        return nodesCount;
    } 

    // return the total number of edges. should have O(1) time complexity.
    /*
     * HW10 class's edgesCount field is already tracking the number of edges.
     * Therefore, this method simply returns the edgesCount field.
     * That is, both time and space complexity is O(1).
     */
	public int numEdges(){
        return edgesCount;
    } 
	
    // return a minimum spanning tree using either Prim's or Kruskal's algorithm.
    /*
     * For every nodes in this graph, this method creates new graph and put it into a HashMap 'forest'.
     * The key is node's data and the value is the graph.
     * Then, the node's edges are inserted into a minHeap if it is not already in the minHeap.
     * The minHeap is sorted by the edges' weight.
     * 
     * Next, another while loop starts.
     * This while loop first dequeue an Edge and merge two vertex's graph.
     * However, if the graphs are the same, then the loop continues with the next edge.
     * The merging operation simply gets the node from the target graph and put it in the source graph.
     * Then, the while loop changes forest's value to merged graph for the node's data as the key.
     * 
     * Finally, the method returns the final graph.
     * 
     * Time complexity:
     * 
     * For the first outer while loop, it iterates n times where n is the number of nodes in the graph.
     * The first inner while loop repeats for the number of edges the node has. That is upper bounded by n.
     * The minHeap's enqueue method's time complexity is upper bounded by linear, so its time complexity is O(n).
     * Thus, the first nested while loop's time complexity is O(n^3).
     * 
     * For the second outer while loop, it iterates until there is no edges in the minHeap. 
     * Since the maximum number of edges in a graph is upper bounded by nC2, the time complexity of outer while loop it self if O(n^2).
     * minHeap's dequeue method's time complexity is O(n).
     * The inner while loop iterates k times where k is the number of nodes in the targetTree.
     * Then, k is also upper bounded by n.
     * Thus, the second while loop's time complexity is O(n^3).
     * 
     * Everything else is just an assignment or a comparison.
     * 
     * Therefore, the overall time complexity is O(n^3) where n is the number of nodes in the graph.
     * 
     * Space complexity:
     * 
     * The dynamic allocations outside while loops are executed only once.
     * 
     * For the first inner while loop, the dynamic allocation is from new Edge() and that is called only once.
     * Since the inner while loop iteration is upper bounded by n, the space complexity of inner loop is O(n).
     * For the outer loop, the while loop has the biggest space complexity.
     * Since the outer loop iteration is upper bounded by n^2, the overall first nested while loop's space complexity is O(n^3).
     * 
     * For the second inner while loop, its iteration is upper bounded by n.
     * Since put method's space complexity is O(1), the inner while loop's space complexity is O(n).
     * For the outer loop, its iteration is upper bounded by n^2.
     * The inner while loop has the biggest space complexity in outer while loop's context.
     * Thus, the second nested while loop's space complexity is O(n^3).
     * 
     * Therefore, the overall space complexity is O(n^3) where n is the number of nodes in the graph.
     */
	public Connectable getMST(){
        HashMap<Integer,HW10> forest = new HashMap<Integer,HW10>();
        MinHeap<Edge> edges = new MinHeap(edgesCount);
        Iterator<Integer> nodeIt = nodeSet().iterator();
        while(nodeIt.hasNext()){
            HW10 newHW10 = new HW10();//1
            int nodeData = nodeIt.next();
            newHW10.addNode(nodeData);//1
            Iterator<Node> edgeIt = nodes.get(nodeData).edges.values().iterator();//1
            while(edgeIt.hasNext()){
                Node node = edgeIt.next();
                Edge edge = new Edge(nodeData, node.data, nodes.get(nodeData).weight.get(node));//1
                if(!edges.contains(edge)) edges.enqueue(edge);
            }
            forest.put(nodeData, newHW10);//1
        }

        while(edges.size != 0){
            Edge edge = edges.dequeue();
            HW10 sourceTree = forest.get(edge.source);
            HW10 targetTree = forest.get(edge.target);
            if(sourceTree == targetTree) continue;
            Iterator<Integer> treeIt = targetTree.nodeSet().iterator();
            while(treeIt.hasNext()){
                Node currentNode = targetTree.nodes.get(treeIt.next());
                sourceTree.nodes.put(currentNode.data,currentNode);
                forest.put(currentNode.data,sourceTree);
                sourceTree.nodesCount++;
            }
            sourceTree.edgesCount += targetTree.edgesCount;
            sourceTree.addEdge(edge.source, edge.target, edge.weight);
        }

        return forest.values().iterator().next();
    }

}

class MinHeap<E extends Comparable<E>>{

	Object[] array;
	int size;

	public MinHeap(int capacity){
		array = new Object[capacity];
		size = 0;
	}

	public void enqueue(E obj){
		if(size == 0){
			array[size] = obj;
			size++;
			return;
		}

		array[size] = obj;
		percolateUp();
		size++;
	}

	public void percolateUp(){

		int targetIndex = size;
		int parentIndex = ((targetIndex-1)/2);

		if(array[parentIndex] == null) return;

		while(((E)array[targetIndex]).compareTo((E)array[parentIndex])<0){
			Object temp = array[targetIndex];
			array[targetIndex] = array[parentIndex];
			array[parentIndex] = temp;

			targetIndex = parentIndex;
			parentIndex = (int)((targetIndex-1)/2);

			if(array[parentIndex] == null) return;
		}

	}

	public E dequeue(){
		if(size == 0){
			return null;
		}

		Object targetObject = array[0];
		array[0] = array[size-1];
		array[size-1] = null;
		size--;

		percolateDown();

		return (E)targetObject;
	}

	public void percolateDown(){

		int targetIndex = 0;
		int leftChildIndex = 2*targetIndex +1;
		int rightChildIndex = 2*targetIndex +2;

		while(leftChildIndex<array.length){

			if(array[leftChildIndex] == null && array[rightChildIndex] == null){
				return;
			}

			if(array[rightChildIndex] == null){
				if(((E)array[targetIndex]).compareTo((E)array[leftChildIndex])>0){
					Object temp = array[targetIndex];
					array[targetIndex] = array[leftChildIndex];
					array[leftChildIndex] = temp;
				}
				
				return;
			}

		
			int smallerChildIndex = getSmallerChildIndex(targetIndex);

			if(((E)array[targetIndex]).compareTo((E)array[smallerChildIndex])<= 0) return;

			Object temp = array[targetIndex];
			array[targetIndex] = array[smallerChildIndex];
			array[smallerChildIndex] = temp;

			targetIndex = smallerChildIndex;
			leftChildIndex = 2*targetIndex +1;
			rightChildIndex = 2*targetIndex +2;
		}

	}

	public int getSmallerChildIndex(int targetIndex){
		int leftChildIndex = 2*targetIndex +1;
		int rightChildIndex = 2*targetIndex +2;

		if(((E)array[leftChildIndex]).compareTo((E)array[rightChildIndex])<=0)
			return leftChildIndex;
		else
			return rightChildIndex;
	}

	public boolean contains(E e){
		int currentIndex = 0;

		while(currentIndex<size && array[currentIndex] != null){
			if(((E)array[currentIndex]).equals(e))
				return true;
			currentIndex++;
		}
		return false;
	}
}