import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class NodeListViewCell extends ListCell<Node>{
	HBox layout;
	CheckBox cb;
	Label id;
	Label name;
	Button bDel;
	
	protected void updateItem(Node node, boolean empty) {
        super.updateItem(node, empty);
        
        if (empty) {
        	setText(null);
            setGraphic(null);
        } else {
        	layout = new HBox();
        	layout.setPrefHeight(Main.lvNodes.getFixedCellSize());
        	layout.setAlignment(Pos.CENTER);
            layout.setSpacing(15);
                                
            cb = new CheckBox();
            cb.setIndeterminate(false);
            cb.setSelected(node.getShows());
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                	node.setShows(new_val);
                	Main.graph.updateRelationsActive();
                	if (new_val) {
                		id.setTextFill(Color.BLACK);
                    	name.setTextFill(Color.BLACK);
                        id.setFont(Main.fSmallText);
                        name.setFont(Main.fSmallText);
                	} else {
                		id.setTextFill(Color.DARKGRAY);
                    	name.setTextFill(Color.DARKGRAY);
                        id.setFont(Main.fSmallTextItalic);
                        name.setFont(Main.fSmallTextItalic);
                	}
                }
            });
            
            id = new Label(node.getId());
            id.setPrefWidth(30);
            
            name = new Label(node.getName());
            name.setPrefWidth(170);
            
            if (cb.isSelected()) {
            	id.setFont(Main.fSmallText);
        		name.setFont(Main.fSmallText);
            } else {
            	id.setTextFill(Color.DARKGRAY);
            	name.setTextFill(Color.DARKGRAY);
                id.setFont(Main.fSmallTextItalic);
                name.setFont(Main.fSmallTextItalic);
            }
            
            bDel = new Button("delete");
            bDel.setPrefWidth(50);
            bDel.setOnAction(new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent t) {
    				Main.graph.delNode(node.getId());
    	        }
    	    });

            layout.getChildren().addAll(cb, id, name, bDel);

            setText(null);
            setGraphic(layout);
        }
    }
}
