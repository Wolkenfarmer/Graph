public class Node {
	private String id;
	private String name;
	private boolean shows;
	
	public Node(String id, String name, boolean b) {
		this.id = id;
		this.name = name;
		shows = b;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getShows() {
		return shows;
	}
	
	public void setShows(boolean b) {
		shows = b;
	}
}
