public class Relation {
	private String id;
	private Node node1;
	private Node node2;
	private byte relate;
	private int weight;
	private boolean active;
	
	public Relation(String id, Node n1, Node n2, byte r, int w) {
		this.id = id;
		node1 = n1;
		node2 = n2;
		relate = r;
		weight = w;
		active = true;
	}
	
	public String getId() {
		return id;
	}
	
	public Node getNode1() {
		return node1;
	}
	
	public Node getNode2() {
		return node2;
	}
	
	public byte getRelate() {
		return relate;
	}
	
	public int getWeight() {
		return weight;
	}
	public boolean getActive() {
		return active;
	}
	
	public void setRelate(byte i) {
		relate = i;
	}
	
	public void setActive(boolean b) {
		active = b;
	}
}
