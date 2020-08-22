import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class RelationListViewCell extends ListCell<Relation> {
	ImageView ivLR;
	ImageView ivL;
	ImageView ivR;
	
	protected void updateItem(Relation relation, boolean empty) {
        super.updateItem(relation, empty);
        
        if (empty) {
        	setText(null);
            setGraphic(null);
        } else {
        	HBox layout = new HBox();
        	layout.setPrefHeight(Main.lvRelations.getFixedCellSize());
        	layout.setAlignment(Pos.CENTER);
            layout.setSpacing(15);
                                
            Label lId = new Label(relation.getId());
            lId.setPrefWidth(30);
            
            Label lNode1Name = new Label(relation.getNode1().getName());
            lNode1Name.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            lNode1Name.setPrefWidth(130);       
            
            Button bRelate = new Button();
            bRelate.setPrefSize(25, 20);
            bRelate.setMinHeight(20);
            try {
            	ivLR = new ImageView(new Image(new FileInputStream("resources/icon_leftright_64.PNG")));
            	ivLR.fitWidthProperty().bind(bRelate.prefWidthProperty());
            	ivLR.fitHeightProperty().bind(bRelate.prefHeightProperty());
            	ivL = new ImageView(new Image(new FileInputStream("resources/icon_left_64.PNG")));
            	ivL.fitWidthProperty().bind(bRelate.prefWidthProperty());
            	ivL.fitHeightProperty().bind(bRelate.prefHeightProperty());
            	ivR = new ImageView(new Image(new FileInputStream("resources/icon_right_64.PNG")));
            	ivR.fitWidthProperty().bind(bRelate.prefWidthProperty());
            	ivR.fitHeightProperty().bind(bRelate.prefHeightProperty());
            	
            	switch (relation.getRelate()) {
            	case 0: bRelate.setGraphic(ivLR); break;
            	case 1: bRelate.setGraphic(ivR); break;
            	case 2: bRelate.setGraphic(ivL);
            	}
			} catch (FileNotFoundException e) {e.printStackTrace();}
            bRelate.setOnAction(new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent t) {
    				byte b = relation.getRelate();
    				if (b < 2) {
    					b++;
    					if (b == 1) {
    						bRelate.setGraphic(ivR);
    					} else {
    						bRelate.setGraphic(ivL);
    					}
    				} else {
    					b = 0;
    					bRelate.setGraphic(ivLR);
    				}
    				relation.setRelate(b);
    	        }
    	    });
            
            Label lNode2Name = new Label(relation.getNode2().getName());
            lNode2Name.setPrefWidth(130);
            
            Label lWeight = new Label(Integer.toString(relation.getWeight()));
            lWeight.setPrefWidth(45);
            
            Button bDel = new Button("delete");
            bDel.setPrefWidth(50);
            bDel.setOnAction(new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent t) {
    				Main.graph.delRelation(relation.getId());
    	        }
    	    });
            
            if (relation.getActive()) {
            	lId.setFont(Main.fSmallText);
            	lNode1Name.setFont(Main.fSmallText);
            	lNode2Name.setFont(Main.fSmallText);
            	lWeight.setFont(Main.fSmallText);
            	lId.setTextFill(Color.BLACK);
            	lNode1Name.setTextFill(Color.BLACK);
            	lNode2Name.setTextFill(Color.BLACK);
            	lWeight.setTextFill(Color.BLACK);
            } else {
            	lId.setFont(Main.fSmallTextItalic);
            	lNode1Name.setFont(Main.fSmallTextItalic);
            	lNode2Name.setFont(Main.fSmallTextItalic);
            	lWeight.setFont(Main.fSmallTextItalic);
            	lId.setTextFill(Color.DARKGRAY);
            	lNode1Name.setTextFill(Color.DARKGRAY);
            	lNode2Name.setTextFill(Color.DARKGRAY);
            	lWeight.setTextFill(Color.DARKGRAY);
            }

            layout.getChildren().addAll(lId, lNode1Name, bRelate, lNode2Name, lWeight, bDel);

            setText(null);
            setGraphic(layout);
        }
    }
}
