package com.rain.app.spelltracker.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationWindow extends Application {
	
	private final double WINDOW_MIN_WIDTH =	400;
	private final double WINDOW_MIN_HEIGHT =600;

	public ApplicationWindow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage rootStage) throws Exception {
		AnchorPane componentWindow = new AnchorPane();
		VBox componentLayout = new VBox();
		
		//set main window size
		componentWindow.setMinHeight(WINDOW_MIN_HEIGHT);
		componentWindow.setMinWidth(WINDOW_MIN_WIDTH);
		
		//add componentLayout to Window
		componentWindow.getChildren().addAll(componentLayout);
		
		//Create the scene and add the parent container to it
        Scene scene = new Scene(componentWindow, WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT);
        
        //Add the Scene to the Stage
        rootStage.setScene(scene);
        //rootStage.getIcons().add(new Image(this.getClass().getResourceAsStream( "media_library.png" ))); 
        rootStage.show();

	}
	
	public void begin(String[] args) {
		launch(args);
	}

}
