import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Arrow {
	private Group g;
	private Line l;
	private Polygon p1;
	private Polygon p2;
	private Label l1;
	private Label l2;
	private HBox h1;
	private HBox h2;
	private double offsetX;
	private double offsetY;
	
	public Arrow(double startX, double startY, double endX, double endY, byte type, int weight) {
		offsetX = Math.sqrt(Math.pow(GraphicCircle.nodeRad + 3, 2) / (1 + Math.pow(((startY - endY) / (startX - endX)), 2)));
		offsetY = Math.sqrt(Math.pow(GraphicCircle.nodeRad + 3, 2) / (1 + Math.pow(((startX - endX) / (startY - endY)), 2)));
		
		if (startX <= endX) {
			startX = startX + offsetX;
			endX = endX - offsetX;
		} else {
			startX = startX - offsetX;
			endX = endX + offsetX;
		}
		if (startY <= endY) {
			startY = startY + offsetY;
			endY = endY - offsetY;
		} else {
			startY = startY - offsetY;
			endY = endY + offsetY;
		}
		
		g = new Group();
		l = new Line(startX, startY, endX, endY);
		l.setStrokeWidth(5);
		l.setStroke(Color.CADETBLUE);
		
		
		switch (type) {
		case 0:
			if (startX <= endX && startY <= endY) {
				p1 = new Polygon(endX, endY, 
						endX - offsetY - (offsetX * 2), endY + offsetX - (offsetY * 2), 
						endX + offsetY - (offsetX * 2), endY - offsetX - (offsetY * 2));
			} else if (startX >= endX && startY >= endY) {
				p1 = new Polygon(endX, endY, 
						endX - offsetY + (offsetX * 2), endY + offsetX + (offsetY * 2), 
						endX + offsetY + (offsetX * 2), endY - offsetX + (offsetY * 2));
			} else if (startX >= endX && startY <= endY) {
				p1 = new Polygon(endX, endY, 
						endX + offsetY + (offsetX * 2), endY + offsetX - (offsetY * 2), 
						endX - offsetY + (offsetX * 2), endY - offsetX - (offsetY * 2));
				
			} else {
				p1 = new Polygon(endX, endY, 
						endX - offsetY - (offsetX * 2), endY - offsetX + (offsetY * 2), 
						endX + offsetY - (offsetX * 2), endY + offsetX + (offsetY * 2));
			}
			p1.setFill(Color.ALICEBLUE);
			p1.setStroke(Color.CADETBLUE);
			p1.setStrokeWidth(3);
			
			if (startX <= endX && startY <= endY) {
				p2 = new Polygon(startX, startY, 
						startX - offsetY + (offsetX * 2), startY + offsetX + (offsetY * 2), 
						startX + offsetY + (offsetX * 2), startY - offsetX + (offsetY * 2));
			} else if (startX >= endX && startY >= endY) {
				p2 = new Polygon(startX, startY, 
						startX - offsetY - (offsetX * 2), startY + offsetX - (offsetY * 2), 
						startX + offsetY - (offsetX * 2), startY - offsetX - (offsetY * 2));
			} else if (startX >= endX && startY <= endY) {
				p2 = new Polygon(startX, startY, 
						startX - offsetY - (offsetX * 2), startY - offsetX + (offsetY * 2), 
						startX + offsetY - (offsetX * 2), startY + offsetX + (offsetY * 2));
			} else {
				p2 = new Polygon(startX, startY, 
						startX + offsetY + (offsetX * 2), startY + offsetX - (offsetY * 2), 
						startX - offsetY + (offsetX * 2), startY - offsetX - (offsetY * 2));
			}
			p2.setFill(Color.ALICEBLUE);
			p2.setStroke(Color.CADETBLUE);
			p2.setStrokeWidth(3);
			
			l1 = new Label("" + weight);
			l1.setFont(Main.fSmallText);
			h1 = new HBox();
			h1.setPrefSize(30, 30);
			h1.setAlignment(Pos.CENTER);
			h1.setLayoutX(p1.getBoundsInParent().getMinX() + p1.getBoundsInParent().getWidth() * 0.3);
			h1.setLayoutY(p1.getBoundsInParent().getMinY() + p1.getBoundsInParent().getHeight() * 0.3);
			
			l2 = new Label("" + weight);
			l2.setFont(Main.fSmallText);
			h2 = new HBox();
			h2.setPrefSize(30, 30);
			h2.setAlignment(Pos.CENTER);
			h2.setLayoutX(p2.getBoundsInParent().getMinX() + p2.getBoundsInParent().getWidth() * 0.3);
			h2.setLayoutY(p2.getBoundsInParent().getMinY() + p2.getBoundsInParent().getHeight() * 0.3);
			
			h1.getChildren().add(l1);
			h2.getChildren().add(l2);
			g.getChildren().addAll(l, p1, p2, h1, h2);
			break;
		case 1:
			if (startX <= endX && startY <= endY) {
				p1 = new Polygon(endX, endY, 
						endX - offsetY - (offsetX * 2), endY + offsetX - (offsetY * 2), 
						endX + offsetY - (offsetX * 2), endY - offsetX - (offsetY * 2));
			} else if (startX >= endX && startY >= endY) {
				p1 = new Polygon(endX, endY, 
						endX - offsetY + (offsetX * 2), endY + offsetX + (offsetY * 2), 
						endX + offsetY + (offsetX * 2), endY - offsetX + (offsetY * 2));
			} else if (startX >= endX && startY <= endY) {
				p1 = new Polygon(endX, endY, 
						endX + offsetY + (offsetX * 2), endY + offsetX - (offsetY * 2), 
						endX - offsetY + (offsetX * 2), endY - offsetX - (offsetY * 2));
			} else {
				p1 = new Polygon(endX, endY, 
						endX - offsetY - (offsetX * 2), endY - offsetX + (offsetY * 2), 
						endX + offsetY - (offsetX * 2), endY + offsetX + (offsetY * 2));
			}
			p1.setFill(Color.ALICEBLUE);
			p1.setStroke(Color.CADETBLUE);
			p1.setStrokeWidth(3);
			
			l1 = new Label("" + weight);
			l1.setFont(Main.fSmallText);
			h1 = new HBox();
			h1.setPrefSize(30, 30);
			h1.setAlignment(Pos.CENTER);
			h1.setLayoutX(p1.getBoundsInParent().getMinX() + p1.getBoundsInParent().getWidth() * 0.3);
			h1.setLayoutY(p1.getBoundsInParent().getMinY() + p1.getBoundsInParent().getHeight() * 0.3);
			
			h1.getChildren().add(l1);
			g.getChildren().addAll(l, p1, h1);
			break;
		case 2:
			if (startX <= endX && startY <= endY) {
				p2 = new Polygon(startX, startY, 
						startX - offsetY + (offsetX * 2), startY + offsetX + (offsetY * 2), 
						startX + offsetY + (offsetX * 2), startY - offsetX + (offsetY * 2));
			} else if (startX >= endX && startY >= endY) {
				p2 = new Polygon(startX, startY, 
						startX - offsetY - (offsetX * 2), startY + offsetX - (offsetY * 2), 
						startX + offsetY - (offsetX * 2), startY - offsetX - (offsetY * 2));
			} else if (startX >= endX && startY <= endY) {
				p2 = new Polygon(startX, startY, 
						startX - offsetY - (offsetX * 2), startY - offsetX + (offsetY * 2), 
						startX + offsetY - (offsetX * 2), startY + offsetX + (offsetY * 2));
			} else {
				p2 = new Polygon(startX, startY, 
						startX + offsetY + (offsetX * 2), startY + offsetX - (offsetY * 2), 
						startX - offsetY + (offsetX * 2), startY - offsetX - (offsetY * 2));
			}
			p2.setFill(Color.ALICEBLUE);
			p2.setStroke(Color.CADETBLUE);
			p2.setStrokeWidth(3);
			
			l2 = new Label("" + weight);
			l2.setFont(Main.fSmallText);
			h2 = new HBox();
			h2.setPrefSize(30, 30);
			h2.setAlignment(Pos.CENTER);
			h2.setLayoutX(p2.getBoundsInParent().getMinX() + p2.getBoundsInParent().getWidth() * 0.3);
			h2.setLayoutY(p2.getBoundsInParent().getMinY() + p2.getBoundsInParent().getHeight() * 0.3);
			
			h2.getChildren().add(l2);
			g.getChildren().addAll(l, p2, h2);
		}
	}
	
	public Group getArrow() {
		return g;
	}
}
