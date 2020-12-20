import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application{
	static Group sbRoot;
	static Group root;
	static Scene mainScene;
	static Stage stage;
	static Graph graph;
	static ArrayList<String> input = new ArrayList<String>();
	static Font fHeadline = new Font("Arial", 45);
    static Font fSubheadline = new Font("Arial", 35);
    static Font fNormalText = new Font("Arial", 20);
	static Font fSmallText = new Font("Arial", 15);
	static Font fSmallTextItalic = Font.font("Arial", FontPosture.ITALIC, 15);
    static Background bGreenButton = new Background (new BackgroundFill(Color.rgb(0, 90, 0), new CornerRadii(10),  null));
    static Background bGreenFocusedButton = new Background (new BackgroundFill(Color.rgb(0, 70, 0), new CornerRadii(10),  null));
    static Background bInvisbleButton = new Background (new BackgroundFill(Color.gray(0, 0), new CornerRadii(10),  null));
	static double programHeight;
	static ScrollBar sb;
	Button bground;
	private static HBox hbHeading;
		private static Label lHeadline;
		private static Region rHeadlineSpacer;
		private static VBox vbHeadline;
			private static Label lHeadlineVersion;
			private static Label lHeadlineBy;
	Label lNodes;
	Label lRelations;
	Label lAddNode;
	TextField tfNodeName;
	Button bNewNode;
	static ListView<Node> lvNodes;
	Label lAddRelation;
	static ComboBox<String> cbRelationNode1;
	static ComboBox<String> cbRelationNode2;
	TextField tfWeighting;
	Button bNewRelation;
	static ListView<Relation> lvRelations;
	TextFlow tfExceptions;
	Text tExceptionNodeName;
	Text tExceptionUnselectedChoiceBoxes;
	Text tExceptionWeight;
	Text tExceptionIdenticalChoiceBoxes;
	Label lDisplayFormat;
	static HBox hbDisplayFormat;
	Label lSelectDisplayFormat;
	ToggleGroup tgDisplayFormat;
	RadioButton rbAdjacencyMatrix;
	RadioButton rbGraphicCircle;
	Button bComputeD;
	GridPane adjacencyMatrix;
	GraphicCircle c;
	Group gc;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		System.out.println("starting");
		stage = new Stage();
		sbRoot = new Group();
        root = new Group();
    	stage.setTitle("Graph");
        mainScene = new Scene(sbRoot, 1200, 700, Color.grayRgb(40));
        stage.setScene(mainScene);
        stage.setResizable(true);   
        stage.show();
        
        
        graph = new Graph();
         
        
        Background bGreenButton = new Background (new BackgroundFill(Color.rgb(0, 90, 0), new CornerRadii(10),  null));
        Background bGreenFocusedButton = new Background (new BackgroundFill(Color.rgb(0, 70, 0), new CornerRadii(10),  null));
        Background bInvisbleButton = new Background (new BackgroundFill(Color.gray(0, 0), new CornerRadii(10),  null));
        
        
        bground = new Button();
        bground.setPrefSize(mainScene.getWidth(), mainScene.getHeight());
        bground.setBackground(bInvisbleButton);
        bground.setFocusTraversable(true);
        bground.setVisible(true);
        sbRoot.getChildren().add(bground);
        
        sb = new ScrollBar();
        sb.setOrientation(Orientation.VERTICAL);
        sb.setMin(0);
        sb.setPrefWidth(20);
        sb.setPrefHeight(mainScene.getHeight());
        sb.setLayoutX(mainScene.getWidth()-sb.getWidth());
        sbRoot.getChildren().add(sb);     
        
        
        
        // Headline
        hbHeading = new HBox();
		hbHeading.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
		hbHeading.setLayoutY(50);
		hbHeading.setPrefWidth(890 + ((mainScene.getWidth() - 890) * (1.0 / 5)));
			lHeadline = new Label();
			lHeadline.setText("Graph program");
			lHeadline.setTextFill(Color.WHITESMOKE);
			lHeadline.setFont(fHeadline);
			lHeadline.setAlignment(Pos.CENTER_LEFT);
			
			rHeadlineSpacer = new Region();
			HBox.setHgrow(rHeadlineSpacer, Priority.ALWAYS);
			
			vbHeadline = new VBox();
			vbHeadline.setAlignment(Pos.CENTER_RIGHT);
				lHeadlineVersion = new Label();
				lHeadlineVersion.setText("Version 1.0");
				lHeadlineVersion.setTextFill(Color.WHITESMOKE);
				lHeadlineVersion.setFont(Main.fNormalText);
				lHeadlineVersion.setAlignment(Pos.TOP_RIGHT);
				
				lHeadlineBy = new Label();
				lHeadlineBy.setText("by Wolkenfarmer");
				lHeadlineBy.setTextFill(Color.WHITESMOKE);
				lHeadlineBy.setFont(Main.fSmallTextItalic);
				lHeadlineBy.setAlignment(Pos.BOTTOM_RIGHT);
			vbHeadline.getChildren().addAll(lHeadlineVersion, lHeadlineBy);
		hbHeading.getChildren().addAll(lHeadline, rHeadlineSpacer, vbHeadline);
		root.getChildren().add(hbHeading);
        
        
        
        
        // Relation-related nodes
        lNodes = new Label("Nodes");
        lNodes.setFont(fSubheadline);
        lNodes.setTextFill(Color.WHITESMOKE);
        lNodes.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
        lNodes.setLayoutY(140);
        root.getChildren().add(lNodes);
        
        lAddNode = new Label("Add a node:");
        lAddNode.setFont(fNormalText);
        lAddNode.setTextFill(Color.WHITESMOKE);
        lAddNode.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
        lAddNode.setLayoutY(206);
        root.getChildren().add(lAddNode);
                
        tfNodeName = new TextField();
        tfNodeName.setFont(fNormalText);
        tfNodeName.setPromptText("name");
        tfNodeName.setStyle("-fx-text-inner-color: WHITESMOKE;");
        tfNodeName.setBackground(new Background(new BackgroundFill(Color.grayRgb(90), new CornerRadii(5),  null)));
        tfNodeName.setFocusTraversable(false);
        tfNodeName.setPrefWidth(150);
        tfNodeName.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5) + 120);
        tfNodeName.setLayoutY(200);
        root.getChildren().add(tfNodeName);
        
        bNewNode = new Button("add");
        bNewNode.setBackground(bGreenButton);
        bNewNode.setFont(fNormalText);
        bNewNode.setTextFill(Color.WHITESMOKE);
        bNewNode.setPrefHeight(40);
        bNewNode.setPrefWidth(70);
        bNewNode.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5) + 280);
        bNewNode.setLayoutY(198.5);
        root.getChildren().add(bNewNode);
                
        lvNodes = new ListView<Node>();
        lvNodes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvNodes.getItems().addAll(graph.getNodeList());
        lvNodes.setCellFactory(nodesListView -> new NodeListViewCell());
        lvNodes.setFixedCellSize(30);
        lvNodes.setMaxHeight(200);
        lvNodes.setMinSize(350, 200);
        lvNodes.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
        lvNodes.setLayoutY(260);
        root.getChildren().add(lvNodes);
        
        
        
        
        // Relation-related nodes
        lRelations = new Label("Relations");
        lRelations.setFont(fSubheadline);
        lRelations.setTextFill(Color.WHITESMOKE);
        lRelations.setLayoutX((mainScene.getWidth() - 890) * (3.0 / 5) + 350);
        lRelations.setLayoutY(140);
        root.getChildren().add(lRelations);
        
        lAddRelation = new Label("Add a relation:");
        lAddRelation.setFont(fNormalText);
        lAddRelation.setTextFill(Color.WHITESMOKE);
        lAddRelation.setLayoutX((mainScene.getWidth() - 890) * (3.0 / 5) + 350);
        lAddRelation.setLayoutY(206);
        root.getChildren().add(lAddRelation);
        
        cbRelationNode1 = new ComboBox<String>();
        cbRelationNode1.setStyle("-fx-font: 15px \"Arial\";");
        cbRelationNode1.setPromptText("node 1");
        cbRelationNode1.getItems().addAll(graph.getNodeNameList());
        cbRelationNode1.setPrefSize(100, 35);
        cbRelationNode1.setLayoutX((mainScene.getWidth() - 890) * (3.0 / 5) + 350 + 140);
        cbRelationNode1.setLayoutY(200);
        root.getChildren().add(cbRelationNode1);
        
        cbRelationNode2 = new ComboBox<String>();
        cbRelationNode2.setStyle("-fx-font: 15px \"Arial\";");
        cbRelationNode2.setPromptText("node 2");
        cbRelationNode2.getItems().addAll(graph.getNodeNameList());
        cbRelationNode2.setPrefSize(100, 35);
        cbRelationNode2.setLayoutX((mainScene.getWidth() - 890) * (3.0 / 5) + 350 + 250);
        cbRelationNode2.setLayoutY(200);
        root.getChildren().add(cbRelationNode2);
                
        tfWeighting = new TextField();
        tfWeighting.setFont(fNormalText);
        tfWeighting.setPromptText("weight");
        tfWeighting.setStyle("-fx-text-inner-color: WHITESMOKE;");
        tfWeighting.setBackground(new Background(new BackgroundFill(Color.grayRgb(90), new CornerRadii(5),  null)));
        tfWeighting.setFocusTraversable(false);
        tfWeighting.setPrefWidth(100);
        tfWeighting.setLayoutX((mainScene.getWidth() - 890) * (3.0 / 5) + 350 + 360);
        tfWeighting.setLayoutY(200);
        root.getChildren().add(tfWeighting);
        
        bNewRelation = new Button("add");
        bNewRelation.setBackground(bGreenButton);
        bNewRelation.setFont(fNormalText);
        bNewRelation.setTextFill(Color.WHITESMOKE);
        bNewRelation.setPrefHeight(40);
        bNewRelation.setPrefWidth(70);
        bNewRelation.setLayoutX((mainScene.getWidth() - 890) * (3.0 / 5) + 350 + 470);
        bNewRelation.setLayoutY(198.5);
        root.getChildren().add(bNewRelation);
                
        lvRelations = new ListView<Relation>();
        lvRelations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvRelations.getItems().addAll(graph.getRelationList());
        lvRelations.setCellFactory(nodesListView -> new RelationListViewCell());
        lvRelations.setFixedCellSize(30);
        lvRelations.setPrefHeight(200);
        lvRelations.setPrefWidth(540);
        lvRelations.setLayoutX((mainScene.getWidth() - 890) * (3.0 / 5) + 350);
        lvRelations.setLayoutY(260);
        root.getChildren().add(lvRelations);
        
        
        
        
        // different adding-Exceptions
        tfExceptions = new TextFlow();
        tfExceptions.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
        tfExceptions.setLayoutY(lvNodes.getLayoutY() + lvNodes.getMaxHeight() + 30);
	        tExceptionNodeName = new Text("Enter a name for your node!\n");
	        tExceptionNodeName.setFont(fNormalText);
	        tExceptionNodeName.setFill(Color.INDIANRED);
	        
	        tExceptionUnselectedChoiceBoxes = new Text("Select a name in both choice boxes!\n");
	        tExceptionUnselectedChoiceBoxes.setFont(fNormalText);
	        tExceptionUnselectedChoiceBoxes.setFill(Color.INDIANRED);
	        
	        tExceptionIdenticalChoiceBoxes = new Text("You have to enter two different nodes in the choice boxes!\n");
	        tExceptionIdenticalChoiceBoxes.setFont(fNormalText);
	        tExceptionIdenticalChoiceBoxes.setFill(Color.INDIANRED);
	        
	        tExceptionWeight = new Text("Enter a weight for your Relation!\n\n");
	        tExceptionWeight.setFont(fNormalText);
	        tExceptionWeight.setFill(Color.INDIANRED);
        root.getChildren().add(tfExceptions);
        
        
        
        
        //Display
        lDisplayFormat = new Label("Display your Graph");
        lDisplayFormat.setFont(fSubheadline);
        lDisplayFormat.setTextFill(Color.WHITESMOKE);
        lDisplayFormat.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
        lDisplayFormat.setLayoutY(tfExceptions.getHeight() + tfExceptions.getLayoutY() + 70);
        root.getChildren().add(lDisplayFormat);
        
        hbDisplayFormat = new HBox();
        hbDisplayFormat.setSpacing(30);
        hbDisplayFormat.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
        hbDisplayFormat.setLayoutY(lDisplayFormat.getLayoutY() + 60);
	        lSelectDisplayFormat = new Label("Select a display format!");
	        lSelectDisplayFormat.setFont(fNormalText);
	        lSelectDisplayFormat.setTextFill(Color.WHITESMOKE);
	        
	        tgDisplayFormat = new ToggleGroup();
		        rbAdjacencyMatrix = new RadioButton("adjacency matrix");
		        rbAdjacencyMatrix.setToggleGroup(tgDisplayFormat);
		        rbAdjacencyMatrix.setFont(fNormalText);
		        rbAdjacencyMatrix.setTextFill(Color.WHITESMOKE);
		        rbAdjacencyMatrix.setAlignment(Pos.TOP_LEFT);
		        rbAdjacencyMatrix.setSelected(true);
		         
		        rbGraphicCircle = new RadioButton("graphic circle");
		        rbGraphicCircle.setToggleGroup(tgDisplayFormat);
		        rbGraphicCircle.setFont(fNormalText);
		        rbGraphicCircle.setTextFill(Color.WHITESMOKE);
		        rbGraphicCircle.setAlignment(Pos.TOP_LEFT);
        hbDisplayFormat.getChildren().addAll(lSelectDisplayFormat, rbAdjacencyMatrix, rbGraphicCircle);
        root.getChildren().add(hbDisplayFormat);
        
        bComputeD = new Button("compute");
        bComputeD.setBackground(bGreenButton);
        bComputeD.setFont(fNormalText);
        bComputeD.setTextFill(Color.WHITESMOKE);
        bComputeD.setPrefHeight(40);
        bComputeD.setPrefWidth(120);
        bComputeD.setLayoutX(lvRelations.getLayoutX() + lvRelations.getPrefWidth() - bComputeD.getPrefWidth());
        bComputeD.setLayoutY(hbDisplayFormat.getLayoutY() - 7);
        root.getChildren().add(bComputeD);
                
        
        
        
        
        programHeight = hbDisplayFormat.getLayoutY() + 80;
        sb.setMax(programHeight - mainScene.getHeight());
		sb.setBlockIncrement(programHeight);
        if (mainScene.getHeight() >= programHeight) {sb.setVisible(false);}
        
        sbRoot.getChildren().add(root);
                
        
        
        
        
        
        // Listener
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
            	//input handling
                String code = e.getCode().toString();
                if (!input.contains(code)) input.add(code);
            }
        });
        
		mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
            	// exit program
                if (input.contains("ESCAPE")) {
                	Platform.exit();;
                }
                
            	// input handling
                String code = e.getCode().toString();
                input.remove(code);
            }
        });
		
		// regroup object after resizing window
		mainScene.widthProperty().addListener(new ChangeListener<Number>() {
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	double width = (double) newSceneWidth;
		    	bground.setPrefWidth(width);
		        sb.setLayoutX(width - sb.getWidth());
		        hbHeading.setLayoutX((width - 890) * (2.0 / 5));
		        hbHeading.setPrefWidth(890 + ((mainScene.getWidth() - 890) * (1.0 / 5)));
		        lNodes.setLayoutX((width - 890) * (2.0 / 5));
		        lRelations.setLayoutX((width - 890) * (3.0 / 5) + 350);
		        lAddNode.setLayoutX((width - 890) * (2.0 / 5));
		        tfNodeName.setLayoutX((width - 890) * (2.0 / 5) + 120);
		        bNewNode.setLayoutX((width - 890) * (2.0 / 5) + 280);
		        lvNodes.setLayoutX((width - 890) * (2.0 / 5));
		        lAddRelation.setLayoutX((width - 890) * (3.0 / 5) + 350);
		        cbRelationNode1.setLayoutX((width - 890) * (3.0 / 5) + 350 + 140);
		        cbRelationNode2.setLayoutX((width - 890) * (3.0 / 5) + 350 + 250);
		        tfWeighting.setLayoutX((width - 890) * (3.0 / 5) + 350 + 360);
		        bNewRelation.setLayoutX((width - 890) * (3.0 / 5) + 350 + 470);
		        lvRelations.setLayoutX((width - 890) * (3.0 / 5) + 350);
		        tfExceptions.setLayoutX((width - 890) * (2.0 / 5));
		        lDisplayFormat.setLayoutX((width - 890) * (2.0 / 5));
		        hbDisplayFormat.setLayoutX((width - 890) * (2.0 / 5));
		        bComputeD.setLayoutX(lvRelations.getLayoutX() + lvRelations.getPrefWidth() - bComputeD.getPrefWidth());
		        if (adjacencyMatrix != null) {
		        	adjacencyMatrix.setLayoutX((width - 890) * (2.0 / 5));
		        	adjacencyMatrix.getColumnConstraints().clear();
		        	for (int i = -1; i < graph.getNodeListCleared().size(); i++) {
		                adjacencyMatrix.getColumnConstraints().add(
		                		new ColumnConstraints((890 + (width - 890) * (1.0 / 5)) / (graph.getNodeListCleared().size() + 1)));
		            }
		        } else if (gc != null) {
		        	showCircle();
		        }
		    }
		});
		
		mainScene.heightProperty().addListener(new ChangeListener<Number>() {
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    	double height = (double) newSceneHeight;
		    	bground.setPrefHeight(height);
		    	if (height < programHeight) {
		    		sb.setVisible(true);
			        sb.setPrefHeight(height);
			        sb.setMax(programHeight - height);
		    	} else {
		    		sb.setVisible(false);
		    		root.setLayoutY(0);
		    	}
		    }
		});
		
		tfExceptions.heightProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(tfExceptions.getHeight() + " + " + tfExceptions.getLayoutY());
			}
		});
		
		// forces the textfield to be numeric only
		tfWeighting.textProperty().addListener(new ChangeListener<String>() {
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	tfWeighting.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		// buttons
		bNewNode.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				tfExceptions.getChildren().clear();
				if(!tfNodeName.getText().equals("")) {
					graph.addNode(tfNodeName.getText());
					tfNodeName.clear();
					updateLvNodes();
				} else {
					tfExceptions.getChildren().add(tExceptionNodeName);
				}
	        }
	    });
		
		bNewNode.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				bNewNode.setBackground(bGreenFocusedButton);
			}
	    });
		
		bNewNode.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				bNewNode.setBackground(bGreenButton);
			}
		});
		
		bNewRelation.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				tfExceptions.getChildren().clear();
				if ((cbRelationNode1.getValue() == null) || (cbRelationNode2.getValue() == null)) {
					tfExceptions.getChildren().add(tExceptionUnselectedChoiceBoxes);
				} else if (cbRelationNode1.getValue() == cbRelationNode2.getValue()) {
					tfExceptions.getChildren().add(tExceptionIdenticalChoiceBoxes);
				}
				if (tfWeighting.getText().equals("")) {tfExceptions.getChildren().add(tExceptionWeight);}
				if (tfExceptions.getChildren().isEmpty()){
					graph.addRelation(cbRelationNode1.getValue(), cbRelationNode2.getValue(), Integer.parseInt(tfWeighting.getText()));
					cbRelationNode1.setValue(null);
					cbRelationNode2.setValue(null);
					tfWeighting.clear();
					updateLvRelations();
				}
	        }
	    });
		
		bNewRelation.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				bNewRelation.setBackground(bGreenFocusedButton);
			}
	    });
		
		bNewRelation.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				bNewRelation.setBackground(bGreenButton);
			}
		});
		
		bComputeD.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				if (tgDisplayFormat.getSelectedToggle() == rbAdjacencyMatrix) {
					showAdjacencyMatrix();
				} else if (tgDisplayFormat.getSelectedToggle() == rbGraphicCircle) {
					showCircle();
				} else {
					System.out.println("Exception: nothing selected");
				}
	        }
	    });
		bComputeD.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				bComputeD.setBackground(bGreenFocusedButton);
			}
	    });
		
		bComputeD.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				bComputeD.setBackground(bGreenButton);
			}
		});
		
		// scroll bar
		sb.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getY() > 0 && e.getY() < mainScene.getHeight()) {
					double i = (programHeight - mainScene.getHeight()) * (e.getY() / mainScene.getHeight());
					if (e.getY() < (mainScene.getHeight() / 2)) {
						i = i - ((((programHeight - mainScene.getHeight()) * 0.5) - i) * (1 / (mainScene.getHeight() * 0.02)));
					} else {
						i = i + ((i - ((programHeight - mainScene.getHeight()) * 0.5)) * (1 / (mainScene.getHeight() * 0.02)));
					}
					sb.setValue(i);
					if (sb.getValue() < 0) {
						sb.setValue(0);
					} else if (sb.getValue() > (programHeight - mainScene.getHeight())) {
						sb.setValue(programHeight - mainScene.getHeight());
					}
				}
			}
		});		
		
		sb.valueProperty().addListener(new ChangeListener<Number>() {
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    	root.setLayoutY(-sb.getValue());
		    }
		});
		
		mainScene.addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent e) {				
				if (sb.isVisible()) {
					sb.setValue(sb.getValue() - e.getDeltaY());
					if (sb.getValue() < 0) {
						sb.setValue(0);
					} else if (sb.getValue() > (programHeight - mainScene.getHeight())) {
						sb.setValue(programHeight - mainScene.getHeight());
					}
				}
			}
		});		
        System.out.println("Finished building");
	}
	
	
	
	public static void updateLvNodes() {
		lvNodes.getItems().clear();
		cbRelationNode1.getItems().clear();
		cbRelationNode2.getItems().clear();
		lvNodes.getItems().addAll(graph.getNodeList());	
		cbRelationNode1.getItems().addAll(graph.getNodeNameList());
		cbRelationNode2.getItems().addAll(graph.getNodeNameList());
	}
	
	public static void updateLvRelations() {
		lvRelations.getItems().clear();
		lvRelations.getItems().addAll(graph.getRelationList());	
	}
	
	
	public void showAdjacencyMatrix() {
		root.getChildren().remove(gc);
		root.getChildren().remove(adjacencyMatrix);
		graph.updateNodeListCleared();
		graph.updateRelationListCleared();
		
		adjacencyMatrix = new GridPane();
        adjacencyMatrix.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
        adjacencyMatrix.setLayoutY(hbDisplayFormat.getLayoutY() + hbDisplayFormat.getHeight() + 80);
        adjacencyMatrix.setVisible(false);
		adjacencyMatrix.getChildren().clear();
		graph.computeAdjacencyMatrix();
        for (int i = 0; i < (graph.getNodeListCleared().size() + 1); i++) {
        	for (int k = 0; k < (graph.getNodeListCleared().size() + 1); k++) {
        		Label l = new Label(graph.adjacencyMatrix[i][k]);
        		l.setFont(fSmallText);
        		l.setTextFill(Color.WHITESMOKE);
        		adjacencyMatrix.add(l, i, k);
        	}
        	adjacencyMatrix.getRowConstraints().add(new RowConstraints(40));
        	adjacencyMatrix.getColumnConstraints().add(new ColumnConstraints((890 + (mainScene.getWidth() - 890) * (1.0 / 5)) / (graph.getNodeListCleared().size() + 1)));
        }
        adjacencyMatrix.setVisible(true);
        adjacencyMatrix.setGridLinesVisible(true);
        root.getChildren().add(adjacencyMatrix);

        programHeight = adjacencyMatrix.getLayoutY() + (graph.getNodeList().size() + 1) * 40 + 50;
        sb.setMax(programHeight - mainScene.getHeight());
		sb.setBlockIncrement(programHeight);
        if (mainScene.getHeight() < programHeight) {sb.setVisible(true);}
	}
	
	
	public void showCircle() {
		root.getChildren().remove(adjacencyMatrix);
		adjacencyMatrix = null;
		root.getChildren().remove(gc);
		graph.updateNodeListCleared();
		graph.updateRelationListCleared();
		
		c = new GraphicCircle(graph.getNodeListCleared().size(), (890 + (mainScene.getWidth() - 890) * (1.0 / 5)) / 2);
		gc = new Group();
		gc.setLayoutX((mainScene.getWidth() - 890) * (2.0 / 5));
		gc.setLayoutY(hbDisplayFormat.getLayoutY() + hbDisplayFormat.getHeight() + 80);
		gc.getChildren().addAll(c.getCircles());
		gc.getChildren().addAll(c.getLabels());
		gc.getChildren().addAll(c.getRelations());
		root.getChildren().add(gc);
		
		
		programHeight = hbDisplayFormat.getLayoutY() + 100 + 890 + (mainScene.getWidth() - 890) * (1.0 / 5);
        sb.setMax(programHeight - mainScene.getHeight());
		sb.setBlockIncrement(programHeight);
        if (mainScene.getHeight() < programHeight) {sb.setVisible(true);}
	}
}
