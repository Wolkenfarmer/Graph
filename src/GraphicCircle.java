import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

public class GraphicCircle {
	private double degrees = 360;
	public static int nodeRad = 40;
	private Circle[] nodes;
	private Label nodeName;
	private HBox[] hbox;
	private Group[] relations;
	
	public GraphicCircle(int numObjects, double rad) {
		rad = rad - 50;
		nodes = new Circle[numObjects];
		hbox = new HBox[numObjects];
		relations = new Group[Main.graph.getRelationListCleared().size()];
		
		degrees = degrees / numObjects;
		double deg = degrees;
		
		
		for (int i = 0; i < numObjects; i++) {
			double radians = Math.toRadians(deg);
			nodes[i] = new Circle(Math.sin(radians) * rad + rad + 25, Math.cos(radians) * rad + rad + 25, nodeRad, Color.rgb(215, 255, 215));
			nodes[i].setStroke(Color.GREEN);
			nodes[i].setStrokeWidth(3);
			nodeName = new Label(Main.graph.getNodeListCleared().get(i).getName());
			nodeName.setFont(Main.fSmallText);
			nodeName.setTextFill(Color.BLACK);
			nodeName.setWrapText(true);
			nodeName.setTextAlignment(TextAlignment.CENTER);
			hbox[i] = new HBox();
			hbox[i].setPrefSize(nodes[i].getRadius() * 2 - 10, 40);
			hbox[i].setLayoutX(nodes[i].getCenterX() - 35);
			hbox[i].setLayoutY(nodes[i].getCenterY() - 20);
			hbox[i].setAlignment(Pos.CENTER);
			hbox[i].getChildren().addAll(nodeName);
			deg = deg + degrees;
		}
		
		for (int i = 0; i < Main.graph.getRelationListCleared().size(); i++) {
			Relation r = Main.graph.getRelationListCleared().get(i);
			int n1 = Main.graph.getNodeIndexClearedFromId(r.getNode1().getId());
			int n2 = Main.graph.getNodeIndexClearedFromId(r.getNode2().getId());
			relations[i] = new Arrow(nodes[n1].getCenterX(), nodes[n1].getCenterY(), nodes[n2].getCenterX(), nodes[n2].getCenterY(), 
					r.getRelate(), r.getWeight()).getArrow();
		}
	}
	
	
	public Circle[] getCircles() {
		return nodes;
	}
	
	public HBox[] getLabels() {
		return hbox;
	}
	
	public Group[] getRelations() {
		return relations;
	}
}