import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Graph {
	private static ObservableList<Node> nodeList;
	private static ObservableList<String> nodeIdList;
	private static ObservableList<Relation> relationList;
	private static ObservableList<Node> nodeListCleared;
	private static ObservableList<Relation> relationListCleared;
	private static int currentNodeId;
	private static int currentRelationId;
	public String[][] adjacencyMatrix;

	public Graph() {
		nodeList = FXCollections.observableArrayList();
		nodeIdList = FXCollections.observableArrayList();
		relationList = FXCollections.observableArrayList();
		nodeListCleared = FXCollections.observableArrayList();
		relationListCleared = FXCollections.observableArrayList();
		nodeList.add(new Node("n0", "example 1", true));
		nodeList.add(new Node("n1", "example 2", true));
		nodeList.add(new Node("n2", "example 3", true));
		nodeList.add(new Node("n3", "example 4", true));
		nodeList.add(new Node("n4", "example 5", true));
		nodeList.add(new Node("n5", "example 6", true));
		nodeList.add(new Node("n6", "example 7", true));
		nodeList.add(new Node("n7", "example 8", true));
		nodeIdList.add("n0");
		nodeIdList.add("n1");
		nodeIdList.add("n2");
		nodeIdList.add("n3");
		nodeIdList.add("n4");
		nodeIdList.add("n5");
		nodeIdList.add("n6");
		nodeIdList.add("n7");
		relationList.add(new Relation("r0", nodeList.get(0), nodeList.get(4),(byte) 0, 555));
		relationList.add(new Relation("r1", nodeList.get(1), nodeList.get(5),(byte) 0, 555));
		relationList.add(new Relation("r2", nodeList.get(2), nodeList.get(6),(byte) 0, 555));
		relationList.add(new Relation("r3", nodeList.get(3), nodeList.get(7),(byte) 0, 555));

		currentNodeId = 8;
		currentRelationId = 4;
	}
	
	public void addNode(String name) {
		nodeList.add(new Node("n" + currentNodeId, name, true));
		nodeIdList.add("n" + currentNodeId);
		currentNodeId++;
	}
	
	public void delNode(String id) {	
		if (getNodeIndexFromId(id) == -1) {
			System.out.println("doesn't exist");
		} else {
			for (int k = 0; k < relationList.size(); k ++) {
				if ((relationList.get(k).getNode1() == getNodeFromId(id))  || (relationList.get(k).getNode2() == getNodeFromId(id))) {
					relationList.remove(k);
				}
			}
			nodeIdList.remove(getNodeIndexFromId(id));
			nodeList.remove(getNodeIndexFromId(id));
			Main.updateLvNodes();
			Main.updateLvRelations();
		}
	}
	
	public void addRelation(String id1, String id2, int weight) {
		relationList.add(new Relation("r" + currentRelationId, nodeList.get(getNodeIndexFromId(id1)), nodeList.get(getNodeIndexFromId(id2)),(byte) 0, weight));
		currentRelationId++;
	}
	
	public void delRelation(String id) {	
		if (getRelationIndexFromId(id) == -1) {
			System.out.println("doesn't exist");
		} else {
			relationList.remove(getRelationIndexFromId(id));
			Main.updateLvRelations();
		}
	}
	
	public void computeAdjacencyMatrix() {
		adjacencyMatrix = new String[0][0];
		int x = nodeListCleared.size() + 1;
		adjacencyMatrix = new String[x][x];
		
		for (int i = 0; i < x; i++) {
			for (int k = 0; k < x; k++) {
				if (i == 0 && k != 0) {
					adjacencyMatrix[0][k] = nodeListCleared.get(k - 1).getName();
				} else if (i != 0 && k == 0) {
					adjacencyMatrix[i][0] = nodeListCleared.get(i - 1).getName();
				} else if (i == k && i != 0) {
					adjacencyMatrix[i][k] = "" + 0;
				} else {
					adjacencyMatrix[i][k] = "-";
				}
				
			}
		}
		
		for (int i = 0; i < relationListCleared.size(); i++) {
			int y = nodeListCleared.indexOf(relationListCleared.get(i).getNode1()) + 1;
			int z = nodeListCleared.indexOf(relationListCleared.get(i).getNode2()) + 1;
			switch (relationListCleared.get(i).getRelate()) {
			case 0:
				adjacencyMatrix[y][z] = "" + relationListCleared.get(i).getWeight();
				adjacencyMatrix[z][y] = "" + relationListCleared.get(i).getWeight();
				break;
			case 1:
				adjacencyMatrix[z][y] = "" + relationListCleared.get(i).getWeight();
				break;
			case 2:
				adjacencyMatrix[y][z] = "" + relationListCleared.get(i).getWeight();
			}
		}
		
		adjacencyMatrix[0][0] = "matrix";
	}
	
	public void updateRelationsActive() {
		for (int i = 0; i < relationList.size(); i++) {
			if (!relationList.get(i).getNode1().getShows() || !relationList.get(i).getNode2().getShows()) {
				relationList.get(i).setActive(false);
			} else {
				relationList.get(i).setActive(true);
			}
		}
		Main.updateLvRelations();
	}
	
	public void updateNodeListCleared() {
		nodeListCleared.clear();
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getShows()) {
				nodeListCleared.add(nodeList.get(i));
			}
		}
	}
	
	public void updateRelationListCleared() {
		relationListCleared.clear();
		for (int i = 0; i < relationList.size(); i++) {
			if (relationList.get(i).getActive()) {
				relationListCleared.add(relationList.get(i));
			}
		}
	}
	
	public int getNodeIndexFromId(String id) {
		for (int k = 0; k < nodeList.size(); k ++) {
			if (nodeList.get(k).getId().equals(id))
				return k;
		}
		return -1;
	}
	
	public int getNodeIndexClearedFromId(String id) {
		for (int k = 0; k < nodeListCleared.size(); k ++) {
			if (nodeListCleared.get(k).getId().equals(id))
				return k;
		}
		return -1;
	}
	
	public Node getNodeFromId(String id) {
		for (int k = 0; k < nodeList.size(); k ++) {
			if (nodeList.get(k).getId().equals(id))
				return nodeList.get(k);
		}
		return null;
	}
	
	public int getRelationIndexFromId(String id) {
		for (int k = 0; k < relationList.size(); k ++) {
			if (relationList.get(k).getId().equals(id))
				return k;
		}
		return -1;
	}
	
	public ObservableList<Node> getNodeList() {
		return nodeList;
	}
	
	public ObservableList<String> getNodeNameList() {
		return nodeIdList;
	}
	
	public ObservableList<Relation> getRelationList() {
		return relationList;
	}
	
	public ObservableList<Node> getNodeListCleared() {
		return nodeListCleared;
	}
	
	public ObservableList<Relation> getRelationListCleared() {
		return relationListCleared;
	}
}
