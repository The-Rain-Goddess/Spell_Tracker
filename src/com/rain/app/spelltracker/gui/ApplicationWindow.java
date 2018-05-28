package com.rain.app.spelltracker.gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationWindow extends Application {
	
	private final double WINDOW_MIN_WIDTH =	200;
	private final double WINDOW_MIN_HEIGHT =300;
	private final String RESOURCE_PATH = "com/rain/spelltracker/res/";
	private final Insets DEFAULT_INSET = new Insets(10,10,10,10);
	private static final Integer STARTTIME = 300;
    //private Timeline timeline;

	public ApplicationWindow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage rootStage) throws Exception {
		AnchorPane componentWindow = new AnchorPane();
		VBox componentLayout = new VBox();
		BorderPane tableDisplay = new BorderPane();
		
		tableDisplay.setLeft(getPlayerTable());
		
		//set main window size
		componentWindow.setMinHeight(WINDOW_MIN_HEIGHT);
		componentWindow.setMinWidth(WINDOW_MIN_WIDTH);
		
		//setup main components
		VBox.setMargin(tableDisplay, new Insets(10,10,10,10));
        componentLayout.getChildren().addAll(setupMenuBar(), tableDisplay);
		
		//add componentLayout to Window
		componentWindow.getChildren().addAll(componentLayout);
		
		//Create the scene and add the parent container to it
        Scene scene = new Scene(componentWindow, WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT, Color.BLACK);
        
        //Add the Scene to the Stage
        rootStage.setScene(scene);
        
        //rootStage.getIcons().add(new Image(this.getClass().getResourceAsStream( "media_library.png" ))); 
        rootStage.show();

	}
	
	public void begin(String[] args) {
		launch(args);
	}

	private VBox getPlayerTable() {
		VBox container = new VBox();
		
		container.setPadding(DEFAULT_INSET);
		container.getChildren().addAll( getPlayerButton("Top"		, "#e7beb6"),
										getPlayerButton("Jungle"	, "#b6e7c9"), 
										getPlayerButton("Middle"	, "#b6d5e7"), 
										getPlayerButton("Marksman"	, "#e7b6c3"), 
										getPlayerButton("Support"	, "#e0b6e7"));
		
		return container;
	}
	
	private HBox getPlayerButton(String name, String bColor) {
		HBox container = new HBox();
		Button player = new Button(name);
		player.setPadding(DEFAULT_INSET);
		player.setMinWidth(75);
		player.setMaxWidth(100);
		//Timeline timeline = new Timeline();
		IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
		player.setStyle("-fx-font: 22 arial; -fx-base: " + bColor + ";");
		player.textProperty().bind(timeSeconds.asString());
		player.setOnAction(
		new EventHandler<ActionEvent>() {
            @Override
			public void handle(ActionEvent t) {
            	/*if (timeline != null) {
                    timeline.stop();
                }*/
                timeSeconds.set(STARTTIME);
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
            }
		});
		Label position = new Label(name);
		position.setMinSize(60,10);
		position.setMaxSize(100,20);
		container.setAlignment(Pos.CENTER_LEFT);
		container.getChildren().addAll(position, player);
		return container;
	}
	
//private menu mutators / accessors	
	private MenuBar setupMenuBar(){
		MenuBar menuBar = new MenuBar();
        
        // --- Menu File
        Menu menuFile = getMenuFileOption();
        
        // --- Menu Edit
        Menu menuEdit = getMenuEditOption();
 
        // --- Menu View
        Menu menuView = getMenuViewOption();
        
        menuBar.setMinWidth(WINDOW_MIN_WIDTH);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        menuBar.autosize();
        return menuBar;
	}
	
	private Menu getMenuFileOption(){
		Menu menu = new Menu("File");
		
		MenuItem add = getMenuItem("Add", new MenuAction(){
			@Override
			public void execute() {
				//showAddWindow();
			}
		});
		
		MenuItem remove = getMenuItem("Remove", new MenuAction(){
			@Override
			public void execute() {
				//showRemoveWindow();
			}
		});
		
	    menu.getItems().addAll(add, remove);
	    return menu;
	}
	
	private MenuItem getMenuItem(String name, MenuAction action){
		MenuItem item = new MenuItem(name,
            new ImageView(new Image(RESOURCE_PATH + name.toLowerCase()+".png")));
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
			public void handle(ActionEvent t) {
                action.execute();
            }
        });
        return item;
	}
	
	private Menu getMenuEditOption(){
		Menu menu = new Menu("Edit");
		return menu;
	}
	
	private Menu getMenuViewOption(){
		Menu menu = new Menu("View");
		return menu;
	}

}
