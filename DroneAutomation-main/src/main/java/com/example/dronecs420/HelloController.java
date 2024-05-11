package com.example.dronecs420;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.shape.*;


public class HelloController implements Initializable{
    @FXML
    private TreeView treeView;

    @FXML
    public AnchorPane Farm;

    @FXML
    private Label welcomeText;

    @FXML
    private ImageView ImageView;

    @FXML
    private VBox itemCmds;

    @FXML
    private VBox itemContCmds;

    @FXML
    private VBox droneBtns;

    public String selectedItem = "";

    public List<ItemsClass> itemList = new ArrayList<ItemsClass>();
    public List<ItemContainer> containerList = new ArrayList<ItemContainer>();

    @FXML
    private Label purchasePriceValue;

    @FXML
    private Label CurrentMarketValue;
    
    @FXML
    private RadioButton visitBtn;
    
    @FXML
    private RadioButton scanBtn;

    @FXML
    private RadioButton goHome;
    
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("sWelcome to JavaFX Application!");
    }

    private RotateTransition rotate = new RotateTransition();
    private PathTransition pathTransition = new PathTransition();
    @FXML
    void launchSimBtn(ActionEvent event) {
    	if(visitBtn.isSelected() == true) {
    		String[] info = getItemInfo();
            int itemIndex = Integer.parseInt(info[0]);
            String type = info[1];
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(ImageView); //ImageView = Image of the drone

            double x = -1;
            double y = -1;

            if(type == "item") {
                x = (itemList.get(itemIndex)).getLx();
                y = (itemList.get(itemIndex)).getLy();
                translate.setToX(x - ImageView.getLayoutX());
                translate.setToY(y - ImageView.getLayoutY());
                translate.play();
            }else if (type == "itemContainer") {
                x = (containerList.get(itemIndex)).getLx();
                y = (containerList.get(itemIndex)).getLy();
                translate.setToX(x - ImageView.getLayoutX());
                translate.setToY(y - ImageView.getLayoutY());
                translate.play();
            }

            System.out.println("x: " + x + ", y: " + y);
            
    	}else if(scanBtn.isSelected() == true) {
    		Path path = new Path();
            rotate.setNode(ImageView);
            rotate.setDuration(Duration.seconds(1));
            path.getElements().add(new MoveTo(-100,50));
            rotate.setByAngle(90);
            path.getElements().add(new VLineTo(525));
            path.getElements().add(new HLineTo(-75));
            path.getElements().add(new VLineTo(50));
            path.getElements().add(new HLineTo(-50));
            path.getElements().add(new VLineTo(525));
            path.getElements().add(new HLineTo(-25));
            path.getElements().add(new VLineTo(50));
            path.getElements().add(new HLineTo(0));
            path.getElements().add(new VLineTo(525));
            path.getElements().add(new HLineTo(25));
            path.getElements().add(new VLineTo(50));
            path.getElements().add(new HLineTo(50));
            path.getElements().add(new VLineTo(525));
            path.getElements().add(new HLineTo(75));
            path.getElements().add(new VLineTo(50));
            path.getElements().add(new HLineTo(100));
            path.getElements().add(new VLineTo(525));
            path.getElements().add(new HLineTo(125));
            path.getElements().add(new VLineTo(50));
            path.getElements().add(new HLineTo(150));
            path.getElements().add(new VLineTo(525));
            path.getElements().add(new HLineTo(175));
            path.getElements().add(new VLineTo(50));
            path.getElements().add(new HLineTo(200));
            path.getElements().add(new VLineTo(525));
            path.getElements().add(new HLineTo(225));
            path.getElements().add(new VLineTo(50));
            path.getElements().add(new ClosePath());
            pathTransition.setNode(ImageView);
            pathTransition.setDuration(Duration.seconds(15));
            pathTransition.setPath(path);
            pathTransition.play();
            rotate.play();
            
    	}else if(goHome.isSelected() == true){
    		TranslateTransition translate = new TranslateTransition();
            //TreeItem<Object> commandCenter = new TreeItem<Object>(new ItemContainer("Command Center", 0, 174, 32, 100, 76, 66));
            translate.setNode(ImageView);
            translate.setToX(174 - ImageView.getLayoutX());
            translate.setToY(32 - ImageView.getLayoutY());
            pathTransition.setDuration(Duration.seconds(15));
            translate.play();
            
    	}else {
    		// Create ERROR dialog box.
            Dialog<Double> errmsg = new Dialog<>();
            errmsg.setTitle("ERROR");
            errmsg.setHeaderText("Select a radio button to perform a task.");
            errmsg.setResizable(true);

            // Add button to close dialog box after user enters values.
            ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
            errmsg.getDialogPane().getButtonTypes().add(okButton);

            // Display the dialog box.
            errmsg.showAndWait();
    		System.out.println("Select a radio button to perform a task.");
    	}
    }
    
    @FXML
    void LaunchDroneBtn(ActionEvent event) throws IOException, InterruptedException {
    	int x = 0, y = 0;
    	
    	Adapter adpt = new Adapter();
    	
    	// Determine whether the selected item is an Item or ItemContainer and get the x & y values.
    	if(selectItem() == null) {
    		System.out.println("SELECT A TREE ITEM!!");
    	}else if(selectItem().isLeaf()) {
    		ItemsClass item = getItem(selectItem().getValue());
    		x = (int)item.getLx();
    		y = (int)item.getLy();
    	}else if(!(selectItem().isLeaf()) && selectItem().getValue() != "Root" && selectItem() != null) {
    		ItemContainer itemCont = getItemContainer(selectItem().getValue());
    		x = (int)itemCont.getLx();
    		y = (int)itemCont.getLy();
    	}
    	
    	// Used to zero out the drone to the center of the Farm AnchorPane.
    	if(x > 227) {
    		y = y * -1;
    	}
    	
    	// Determine the radio button selected and call the proper drone functions.
    	if(visitBtn.isSelected() == true) {
    		try {
        		adpt.gotoXY(x, y, 100);
        	}catch (Exception e) {
    	    	e.printStackTrace();
    	    }
    	}else if(scanBtn.isSelected() == true) {
    		try {
        		adpt.scanFarm();
        	}catch (Exception e) {
    	    	e.printStackTrace();
    	    }
    	}else {
    		// Create ERROR dialog box.
            Dialog<Double> errmsg = new Dialog<>();
            errmsg.setTitle("ERROR");
            errmsg.setHeaderText("Select a radio button to perform a task.");
            errmsg.setResizable(true);

            // Add button to close dialog box after user enters values.
            ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
            errmsg.getDialogPane().getButtonTypes().add(okButton);

            // Display the dialog box.
            errmsg.showAndWait();
    	}
    }

    String[] getItemInfo() {

        int itemIndex = -1;
        String type = "";

        ItemsClass item = new ItemsClass(selectItem().getParent().getValue(), selectedItem, 0, 0, 0, 0, 0, 0, 0);
        ItemContainer itemContainer = new ItemContainer(selectItem().getParent().getValue(), selectedItem, 0, 0, 0, 0, 0, 0);

        try {
        	for(int i=0; i<itemList.size(); i++) {
        		String name = itemList.get(i).getName();
        		if(name.equals(selectedItem)) {
            		type = "item";
            		itemIndex = i;
            	}
        	}
        }catch(IndexOutOfBoundsException e) {
        	for(int i=0; i<itemList.size();i++) {
        		System.out.println(itemList.get(i).getName());
        	}
        	System.out.println("Not an item");
        	System.out.println(e);
        }
        
        try {
        	for(int i=0; i<containerList.size(); i++) {
        		String name = containerList.get(i).getName();
        		if(name.equals(selectedItem)) {
            		type = "itemContainer";
            		itemIndex= i;
            	}
        	}
        }catch(IndexOutOfBoundsException e) {
        	System.out.println("Not an item container");
        	System.out.println(e);
        }
        
        String[] info = new String[2];
        info[0] = Integer.toString(itemIndex);
        info[1] = type;

        return info;
    }
    
    @FXML
    void saveClick(ActionEvent event) {
    	try {
    		String storagePath = "src/main/resources/com/example/dronecs420/storage.txt";
        	File oldFile = new File(storagePath);
        	oldFile.delete();
        	
        	File newFile = new File(storagePath);
        	
    		FileWriter fw = new FileWriter(newFile, false);
    		
    		String tempStr = "";
    		for(int i = 0; i<containerList.size(); i++) {
    			ItemContainer container = containerList.get(i);
    			tempStr = "itemContainer," + container.getParent() + "," + container.getName() + "," + String.valueOf(container.getPrice()) + "," + String.valueOf(container.getLx()) + "," + String.valueOf(container.getLy()) + "," +String.valueOf(container.getLength()) + "," + String.valueOf(container.getWidth()) + "," + String.valueOf(container.getHeight()) + ";";
    			System.out.println(tempStr);
    			fw.write(tempStr);
    			fw.write(System.getProperty("line.separator"));
    		}
    		
    		for(int i = 0; i<itemList.size(); i++) {
    			ItemsClass item = itemList.get(i);
    			tempStr = "item," + item.getParent() + "," + item.getName() + "," + String.valueOf(item.getPrice()) + "," + String.valueOf(item.getLx()) + "," + String.valueOf(item.getLy()) + "," +String.valueOf(item.getLength()) + "," + String.valueOf(item.getWidth()) + "," + String.valueOf(item.getHeight()) + "," + String.valueOf(item.getCur_price()) + ";";
    			System.out.println(tempStr);
    			fw.write(tempStr);
    			fw.write(System.getProperty("line.separator"));
    		}
    		fw.close();
    		
    		// Create Success dialog box.
            Dialog<Double> errmsg = new Dialog<>();
            errmsg.setTitle("Saved");
            errmsg.setHeaderText("You have successfully saved.");
            errmsg.setResizable(true);

            // Add button to close dialog box
            ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
            errmsg.getDialogPane().getButtonTypes().add(okButton);

            // Display the dialog box.
            errmsg.showAndWait();
    	} catch (IOException e) {
    		e.printStackTrace();
    		// Create Success dialog box.
            Dialog<Double> errmsg = new Dialog<>();
            errmsg.setTitle("Save Unsuccessful");
            errmsg.setHeaderText("Something went wrong :(");
            errmsg.setResizable(true);

            // Add button to close dialog box
            ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
            errmsg.getDialogPane().getButtonTypes().add(okButton);

            // Display the dialog box.
            errmsg.showAndWait();
    	}
    	
    }

    @FXML
    void itemChangeDClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);
        String type = info[1];

        // Create dialog box.
        Dialog<Double> changeLoc = new Dialog<>();
        changeLoc.setTitle("Dimensions");
        changeLoc.setHeaderText("Enter the new dimensions: ");
        changeLoc.setResizable(true);
        //changeLoc.setWidth(1000.0);

        // Add the attributes of the dialog box.
        Label label1 = new Label("Width: ");
        Label label2 = new Label("Height: ");
        TextField width = new TextField();
        TextField height = new TextField();

        // Add the layout, and add the attributes to layout.
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(width, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(height, 2, 2);
        changeLoc.getDialogPane().setContent(grid);

        // Add button to close dialog box after user enters values.
        ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
        changeLoc.getDialogPane().getButtonTypes().add(okButton);

        Optional<Double> result = changeLoc.showAndWait();
        if(result.isPresent()){
            System.out.println(width.getText() + " " + height.getText());
            if(type == "item") {
            	System.out.println("Changed Width + Height of " + (itemList.get(itemIndex)).getName() + " " + (itemList.get(itemIndex)).getWidth() + " " + (itemList.get(itemIndex)).getHeight());
                (itemList.get(itemIndex)).setWidth(Integer.parseInt(width.getText()));
                (itemList.get(itemIndex)).setHeight(Integer.parseInt(height.getText()));
                
                // Delete old rectangle.
                deleteRectangle(selectItem().getValue().toString());
                makeRectangle((itemList.get(itemIndex)).getName(), (itemList.get(itemIndex)).getLx(), (itemList.get(itemIndex)).getLy(), (itemList.get(itemIndex)).getWidth(), (itemList.get(itemIndex)).getHeight());
            }else if (type == "itemContainer") {
            	System.out.println("Changed Width + Height of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getWidth() + " " + (containerList.get(itemIndex)).getHeight());
                (containerList.get(itemIndex)).setWidth(Integer.parseInt(width.getText()));
                (containerList.get(itemIndex)).setHeight(Integer.parseInt(height.getText()));
                
             // Delete old rectangle.
                deleteRectangle(selectItem().getValue().toString());
                makeRectangle((containerList.get(itemIndex)).getName(), (containerList.get(itemIndex)).getLx(), (containerList.get(itemIndex)).getLy(), (containerList.get(itemIndex)).getWidth(), (containerList.get(itemIndex)).getHeight());
            }
        }
    }

    @FXML
    void itemChangeLClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);
        String type = info[1];

        // Create dialog box.
        Dialog<Double> changeDim = new Dialog<>();
        changeDim.setTitle("Location");
        changeDim.setHeaderText("Enter the new location: ");
        changeDim.setResizable(true);

        // Add the attributes of the dialog box.
        Label label1 = new Label("Change x: ");
        Label label2 = new Label("Change y: ");
        TextField xvalue = new TextField();
        TextField yvalue = new TextField();

        // Add the layout, and add the attributes to layout.
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(xvalue, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(yvalue, 2, 2);
        changeDim.getDialogPane().setContent(grid);

        // Add button to close dialog box after user enters values.
        ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
        changeDim.getDialogPane().getButtonTypes().add(okButton);

        Optional<Double> result = changeDim.showAndWait();
        if(result.isPresent()){
            System.out.println(xvalue.getText() + " " + yvalue.getText());
            
            if(type == "item") {
            	(itemList.get(itemIndex)).setLx(Integer.parseInt(xvalue.getText()));
                (itemList.get(itemIndex)).setLy(Integer.parseInt(yvalue.getText()));
                System.out.println("Changed X + Y of " + (itemList.get(itemIndex)).getName() + " " + (itemList.get(itemIndex)).getLx() + " " + (itemList.get(itemIndex)).getLy());
                
                // Delete old rectangle.
                deleteRectangle(selectItem().getValue().toString());
                makeRectangle((itemList.get(itemIndex)).getName(), (itemList.get(itemIndex)).getLx(), (itemList.get(itemIndex)).getLy(), (itemList.get(itemIndex)).getWidth(), (itemList.get(itemIndex)).getHeight());
            }else if (type == "itemContainer") {
            	(containerList.get(itemIndex)).setLx(Integer.parseInt(xvalue.getText()));
                (containerList.get(itemIndex)).setLy(Integer.parseInt(yvalue.getText()));
                System.out.println("Changed X + Y of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getLx() + " " + (containerList.get(itemIndex)).getLy());
                
                // Delete old rectangle.
                deleteRectangle(selectItem().getValue().toString());
                makeRectangle((containerList.get(itemIndex)).getName(), (containerList.get(itemIndex)).getLx(), (containerList.get(itemIndex)).getLy(), (containerList.get(itemIndex)).getWidth(), (containerList.get(itemIndex)).getHeight());
            }
        }
    }

    @FXML
    void itemChangePClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);
        String type = info[1];

        // Create dialog box.
        Dialog<Double> changePrice = new Dialog<>();
        changePrice.setTitle("Change Prices");
        changePrice.setHeaderText("Enter the new prices: ");
        changePrice.setResizable(true);
        //changeLoc.setWidth(1000.0);

        // Add the attributes of the dialog box.
        Label label1 = new Label("Purchase Price: ");
        Label label2 = new Label("Current Market Value: ");
        TextField pp = new TextField();
        TextField cmv = new TextField();

        // Add the layout, and add the attributes to layout.
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(pp, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(cmv, 2, 2);
        changePrice.getDialogPane().setContent(grid);

        // Add button to close dialog box after user enters values.
        ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
        changePrice.getDialogPane().getButtonTypes().add(okButton);

        // Capture the users input.
        Optional<Double> result = changePrice.showAndWait();
        if(result.isPresent()){
            System.out.println(result.get());
            
            // If price is equal to 0, set both price and cur_price, else just change cur_price.
            if(type == "item") {
                (itemList.get(itemIndex)).setPrice(Integer.parseInt(pp.getText()));
                System.out.println("Changed Price of " + (itemList.get(itemIndex)).getName() + " " + (itemList.get(itemIndex)).getPrice());
                (itemList.get(itemIndex)).setCur_price(Integer.parseInt(cmv.getText()));
                System.out.println("Changed Current Price of " + (itemList.get(itemIndex)).getName() + " " + (itemList.get(itemIndex)).getCur_price());
            }else if (type == "itemContainer") {
                (containerList.get(itemIndex)).setPrice(Integer.parseInt(pp.getText()));
                System.out.println("Changed Price of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getPrice());
            	(containerList.get(itemIndex)).setCur_price(Integer.parseInt(cmv.getText()));
                System.out.println("Changed Price of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getCur_price());
            }
        }
    }

    @FXML
    void itemDeleteClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);
        String type = info[1];

        if(type == "item") {
        	itemList.remove(itemIndex);
        	
        	TreeItem delete = (TreeItem)treeView.getSelectionModel().getSelectedItem();
            System.out.println(delete);
            boolean remove = delete.getParent().getChildren().remove(delete);

            // Delete rectangle.
            System.out.println("item to be deleted string: " + delete.getValue().toString());
            deleteRectangle(delete.getValue().toString());
        }else if (type == "itemContainer") {
        	containerList.remove(itemIndex);
            
        	TreeItem delete = (TreeItem)treeView.getSelectionModel().getSelectedItem();
            System.out.println(delete);
            boolean remove = delete.getParent().getChildren().remove(delete);

            // Delete rectangle.
            System.out.println("item to be deleted string: " + delete.getValue().toString());
            deleteRectangle(delete.getValue().toString());
        }
    }

    @FXML
    void itemRenameClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);
        String type = info[1];

        // Store the old name before changing the ItemClass object name.
        String old_name = selectItem().getValue();

        // Create the TextInputDialog box.
        TextInputDialog renameItem = new TextInputDialog();
        renameItem.setTitle("Rename");
        renameItem.setHeaderText("Enter new name: ");
        renameItem.setContentText("Name: ");

        // Capture the users input.
        Optional<String> result = renameItem.showAndWait();
        if(result.isPresent()){
            System.out.println(result.get());
            
            if(type == "item") {
            	(itemList.get(itemIndex)).setName(result.get());
                System.out.println("Changed Name of " + (itemList.get(itemIndex)).getName());
                
             // Delete old rectangle.
                deleteRectangle(selectItem().getValue().toString());

                makeRectangle((itemList.get(itemIndex)).getName(), (itemList.get(itemIndex)).getLx(), (itemList.get(itemIndex)).getLy(), (itemList.get(itemIndex)).getWidth(), (itemList.get(itemIndex)).getHeight());
                
                // Rename TreeItem item value.
                TreeItem<String> item = selectItem();
                item.setValue(result.get());
            }else if (type == "itemContainer") {
            	(containerList.get(itemIndex)).setName(result.get());
                System.out.println("Changed Name of " + (containerList.get(itemIndex)).getName());
                
                // Delete old rectangle.
                deleteRectangle(selectItem().getValue().toString());

                makeRectangle((containerList.get(itemIndex)).getName(), (containerList.get(itemIndex)).getLx(), (containerList.get(itemIndex)).getLy(), (containerList.get(itemIndex)).getWidth(), (containerList.get(itemIndex)).getHeight());
                
                // Rename TreeItem item value.
                TreeItem<String> item = selectItem();
                item.setValue(result.get());
            }
        }
    }

    @FXML
    void itemContAddItemCClick(ActionEvent event) {
        // Used to determine whether the given name matches any existing objects.
        boolean matching = false;

        // Create the TextInputDialog box.
        TextInputDialog renameItem = new TextInputDialog();
        renameItem.setTitle("Add Item Container");
        renameItem.setContentText("Name: ");

        // Capture the users input.
        Optional<String> result = renameItem.showAndWait();
        if(result.isPresent()){
            System.out.println(result.get());

            // Call matchingName() function and save result.
            matching = matchingName(result.get());
            
            if(matching == false){
                // Create new TreeItem branch node.
                TreeItem<String> treeBranch = new TreeItem<>(result.get());

                // Get parent (root node)
                TreeItem<String> parent = selectItem();
                parent.getChildren().add(treeBranch);

                // Add default child so commands don't read it as a leaf.
                String childName = (result.get() + " Child");
                TreeItem<String> defaultchild = new TreeItem<>(childName);
                treeBranch.getChildren().add(defaultchild);
                
                //Add Item Container
                ItemContainer container = new ItemContainer(parent.getValue(), result.get(), 0, 0, 0, 0, 100, 75);
                containerList.add(container);

                // Add Default Item
                ItemsClass newitem = new ItemsClass(container.getName(), childName, 0, 0, 0, 0, 50, 35, 0);
                newitem.setName(result.get() +" Child");
                container.addItem(newitem);
                itemList.add(newitem);

                System.out.println(newitem.getName());
                
                //Make Container Rectangle
                makeRectangle(container.getName(), container.getLx(), container.getLy(), container.getWidth(), container.getHeight());
                
                //Make Child Rectangle
                makeRectangle(newitem.getName(), newitem.getLx(), newitem.getLy(), newitem.getWidth(), newitem.getHeight());
            }
        }
    }

    @FXML
    void itemContAddItemClick(ActionEvent event) {
        // Used to determine whether the given name matches any existing objects.
        boolean matching = false;

        // Create the TextInputDialog box.
        TextInputDialog renameItem = new TextInputDialog();
        renameItem.setTitle("Add Item");
        renameItem.setContentText("Name: ");

        // Capture the users input.
        Optional<String> result = renameItem.showAndWait();
        if(result.isPresent()){
            System.out.println(result.get());

            // Call matchingName() function and save result.
            matching = matchingName(result.get());

            if(matching == false){
                // Create a new item.
                String itemName = result.get();
                ItemsClass item = new ItemsClass(selectItem().getValue(), itemName, 0, 0, 0, 0, 100, 75, 0);
                itemList.add(item);

                System.out.println(item.getName());

                // Create new TreeItem leaf node.
                TreeItem<String> treeItem = new TreeItem<>(result.get());
                TreeItem<String> parent = selectItem();
                parent.getChildren().add(treeItem);

                makeRectangle(item.getName(), item.getLx(), item.getLy(), item.getHeight(), item.getWidth());
            }  
        }
    }

    @FXML
    void itemContChangeDClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);

        // Create dialog box.
        Dialog<Double> changeLoc = new Dialog<>();
        changeLoc.setTitle("Dimensions");
        changeLoc.setHeaderText("Enter the new dimensions: ");
        changeLoc.setResizable(true);
        //changeLoc.setWidth(1000.0);

        // Add the attributes of the dialog box.
        Label label1 = new Label("Width: ");
        Label label2 = new Label("Height: ");
        TextField width = new TextField();
        TextField height = new TextField();

        // Add the layout, and add the attributes to layout.
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(width, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(height, 2, 2);
        changeLoc.getDialogPane().setContent(grid);

        // Add button to close dialog box after user enters values.
        ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
        changeLoc.getDialogPane().getButtonTypes().add(okButton);

        Optional<Double> result = changeLoc.showAndWait();
        if(result.isPresent()){
            System.out.println(width.getText() + " " + height.getText());

            (containerList.get(itemIndex)).setWidth(Integer.parseInt(width.getText()));
            (containerList.get(itemIndex)).setHeight(Integer.parseInt(height.getText()));
            System.out.println("Changed Width + Height of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getWidth() + " " + (containerList.get(itemIndex)).getHeight());
            
         // Delete old rectangle.
            deleteRectangle(selectItem().getValue().toString());

            makeRectangle((containerList.get(itemIndex)).getName(), (containerList.get(itemIndex)).getLx(), (containerList.get(itemIndex)).getLy(), (containerList.get(itemIndex)).getWidth(), (containerList.get(itemIndex)).getHeight());
        }
    }

    @FXML
    void itemContChangeLClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);

        // Create dialog box.
        Dialog<Double> changeDim = new Dialog<>();
        changeDim.setTitle("Location");
        changeDim.setHeaderText("Enter the new location: ");
        changeDim.setResizable(true);

        // Add the attributes of the dialog box.
        Label label1 = new Label("Change x: ");
        Label label2 = new Label("Change y: ");
        TextField xvalue = new TextField();
        TextField yvalue = new TextField();

        // Add the layout, and add the attributes to layout.
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(xvalue, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(yvalue, 2, 2);
        changeDim.getDialogPane().setContent(grid);

        // Add button to close dialog box after user enters values.
        ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
        changeDim.getDialogPane().getButtonTypes().add(okButton);

        Optional<Double> result = changeDim.showAndWait();
        if(result.isPresent()){
            System.out.println(xvalue.getText() + " " + yvalue.getText());

            (containerList.get(itemIndex)).setLx(Integer.parseInt(xvalue.getText()));
            (containerList.get(itemIndex)).setLy(Integer.parseInt(yvalue.getText()));
            System.out.println("Changed X + Y of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getLx() + " " + (containerList.get(itemIndex)).getLy());
            
            // Delete old rectangle.
            deleteRectangle(selectItem().getValue().toString());

            makeRectangle((containerList.get(itemIndex)).getName(), (containerList.get(itemIndex)).getLx(), (containerList.get(itemIndex)).getLy(), (containerList.get(itemIndex)).getWidth(), (containerList.get(itemIndex)).getHeight());
        }
    }

    @FXML
    void itemContChangePClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);

        // Create dialog box.
        Dialog<Double> changePrice = new Dialog<>();
        changePrice.setTitle("Change Prices");
        changePrice.setHeaderText("Enter the new prices: ");
        changePrice.setResizable(true);
        //changeLoc.setWidth(1000.0);

        // Add the attributes of the dialog box.
        Label label1 = new Label("Purchase Price: ");
        Label label2 = new Label("Current Market Value: ");
        TextField pp = new TextField();
        TextField cmv = new TextField();

        // Add the layout, and add the attributes to layout.
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(pp, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(cmv, 2, 2);
        changePrice.getDialogPane().setContent(grid);

        // Add button to close dialog box after user enters values.
        ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
        changePrice.getDialogPane().getButtonTypes().add(okButton);

        // Capture the users input.
        Optional<Double> result = changePrice.showAndWait();
        if(result.isPresent()){
            System.out.println(result.get());

            // Set both price and cur_price.
            (containerList.get(itemIndex)).setPrice(Integer.parseInt(pp.getText()));
            System.out.println("Changed Price of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getPrice());
            (containerList.get(itemIndex)).setCur_price(Integer.parseInt(cmv.getText()));
            System.out.println("Changed Current Price of " + (containerList.get(itemIndex)).getName() + " " + (containerList.get(itemIndex)).getCur_price());            
        }
    }

    @FXML
    void itemContDeleteClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);

        if(Farm.getChildren() == null){
            containerList.remove(itemIndex);
            TreeItem delete = (TreeItem)treeView.getSelectionModel().getSelectedItem();
            boolean remove = delete.getParent().getChildren().remove(delete);

            // Delete rectangle.
            deleteRectangle(delete.getValue().toString());
        }else{
            // Create dialog box.
            Dialog<Double> errmsg = new Dialog<>();
            errmsg.setTitle("ERROR");
            errmsg.setHeaderText("Delete all leaf nodes first");
            errmsg.setResizable(true);

            // Add button to close dialog box after user enters values.
            ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
            errmsg.getDialogPane().getButtonTypes().add(okButton);

            // Display the dialog box.
            errmsg.showAndWait();
        }
    }

    @FXML
    void itemContRenameClick(ActionEvent event) {
        String[] info = getItemInfo();
        int itemIndex = Integer.parseInt(info[0]);

        // Store the old name before changing the ItemClass object name.
        String old_name = selectItem().getValue();

        // Create the TextInputDialog box.
        TextInputDialog renameItem = new TextInputDialog();
        renameItem.setTitle("Rename");
        renameItem.setHeaderText("Enter new name: ");
        renameItem.setContentText("Name: ");
        
        // Capture the users input.
        Optional<String> result = renameItem.showAndWait();
        if(result.isPresent()){
            System.out.println(result.get());

            (containerList.get(itemIndex)).setName(result.get());
            System.out.println("Changed Name of " + (containerList.get(itemIndex)).getName());
            
            // Delete old rectangle.
            deleteRectangle(selectItem().getValue().toString());

            makeRectangle((containerList.get(itemIndex)).getName(), (containerList.get(itemIndex)).getLx(), (containerList.get(itemIndex)).getLy(), (containerList.get(itemIndex)).getWidth(), (containerList.get(itemIndex)).getHeight());
            
            // Rename TreeItem item container value.
            TreeItem<String> item = selectItem();
            item.setValue(result.get());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	String storagePath = "src/main/resources/com/example/dronecs420/storage.txt";
    	File storageFile = new File(storagePath);
    	FileReader fr = null;
    	BufferedReader reader = null;

		try {
			fr = new FileReader(storageFile);
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Failed to open file reader for storage file");
			e.printStackTrace();
		}
    	
		reader = new BufferedReader(fr);
		String line = null;
		String current = null;
		String rest = null;
		ItemsClass item = null;
		ItemContainer container = null;
		List<TreeItem<String>> treeContainers = new ArrayList<TreeItem<String>>();
		
    	try {
    		while ((line = reader.readLine()) != null) {
    			current = line.substring(0, line.indexOf(","));
    			rest = line.substring(line.indexOf(",")+1, line.length());
    			
    			if(current.equals("item")) {
    				current = rest.substring(0, rest.indexOf(","));
    				String parent = current;
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				String name = current;
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				int price = Integer.parseInt(current);

    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				double x = Double.parseDouble(current);

    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				double y = Double.parseDouble(current);
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				int length = Integer.parseInt(current);
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				int width = Integer.parseInt(current);
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				int height = Integer.parseInt(current);
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(";"));
    				int initialPrice = Integer.parseInt(current);
    				
    				item = new ItemsClass(parent, name, price, x, y, length, width, height, initialPrice);
    		        itemList.add(item);
    		        
    		        TreeItem<String> node = new TreeItem<>(name);
    		        makeRectangle(name, x, y, width, height);
    		        
    		        treeContainers.add(node);
    			}else if(current.equals("itemContainer")) {
    				current = rest.substring(0, rest.indexOf(","));
    				String parent = current;
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				String name = current;
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				int price = Integer.parseInt(current);

    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				double x = Double.parseDouble(current);

    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				double y = Double.parseDouble(current);
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				int length = Integer.parseInt(current);
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(","));
    				int width = Integer.parseInt(current);
    				
    				rest = rest.substring(rest.indexOf(",")+1, rest.length());
    				current = rest.substring(0, rest.indexOf(";"));
    				int height = Integer.parseInt(current);
    				
    				container = new ItemContainer(parent, name, price, x, y, length, width, height);
    		        containerList.add(container);
    		        
    		        TreeItem<String> node = new TreeItem<>(name);
    		        makeRectangle(name, x, y, width, height);
    		        
    		        treeContainers.add(node);
    			}
    		 }
    		formTree(treeContainers);
		} catch (IOException e) {
			System.out.println("ERROR: Failed to read storage file");
			e.printStackTrace();
		}
    	
    	for(int i=0; i<itemList.size(); i++) {
    		System.out.println(itemList.get(i));
    	}
    	
    	System.out.println(itemList.size());		
    }
    
    public void formTree(List<TreeItem<String>> treeContainers){
    	List<TreeItem<String>> addedContainers = new ArrayList<TreeItem<String>>();
    	//Set root node
		TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);
        
    	TreeItem<String> parent;
    	TreeItem<String> child;
    	for(int i=0; i<treeContainers.size(); i++) {
    		parent = treeContainers.get(i);
    		for(int j=0; j<treeContainers.size(); j++) {
        		child = treeContainers.get(j);
        		for(int z=0; z<itemList.size(); z++) {
        			if(itemList.get(z).getName().equals(child.getValue())) {
        				String itemParent = ((ItemsClass) itemList.get(z)).getParent();
        				if((itemParent.equals(parent.getValue()))) {
            				if(addedContainers.indexOf(child) == -1) {
            					addedContainers.add(child);
            					parent.getChildren().add(child);
                				System.out.println("added to container");
            				}
            			}else if((itemParent.equals("Root"))) {
            				if(addedContainers.indexOf(child) == -1) {
            					addedContainers.add(child);
            					rootItem.getChildren().add(child);
                				System.out.println("added to root");
            				}
            			}
        			}
  
        		}
        		for(int y=0; y<containerList.size(); y++) {
        			if(((containerList.get(y)).getName().equals(child.getValue()))) {
        				String containerParent = (containerList.get(y)).getParent();
        				if(((containerParent.equals(parent.getValue())))) {
        					if(addedContainers.indexOf(child) == -1) {
            					addedContainers.add(child);
            					parent.getChildren().add(child);
                				System.out.println("added to container");
            				}
            			}else if((containerParent.equals("Root"))) {
            				if(addedContainers.indexOf(child) == -1) {
            					addedContainers.add(child);
            					rootItem.getChildren().add(child);
                				System.out.println("added to root");
            				}
            			}
        			}
        		}
        	}
    	}
    	
    	treeView.setRoot(rootItem);
    }

    /*
     * Function used to create new rectangles and text within and adds them to the dashboard.
     * Accepts a string that will match the label within the rectangle, and will be the rectangle and labels ID.
     * Accepts X and Y coordinates that will be used to place the rectangle on the dashboard.
     * Accepts width and height which will be used to make the rectangle to a specific size.
     */
    public void makeRectangle(String name, double x, double y, double width, double height){
        //Make sure the rectangles name doesn't have spaces
    	  name = name.replaceAll(" ", "_");
    	
    	  //The text up top of the rectangle
        Text text = new Text(name);

        // Drawing the Rectangle
        Rectangle rectangle = new Rectangle();

        // Set up all the properties of the rectangle.
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setId(name);

        // Set up all the properties of the label contained within the rectangle.
        text.setLayoutX(rectangle.getX() + 5);
        text.setLayoutY(rectangle.getY() + 10);
        text.setId(name+"text");

        // Add objects to the AnchorPane.
        Farm.getChildren().add(rectangle);
        Farm.getChildren().add(text);
    }

    /*
     * Function used to delete the rectangles and text within on the dashboard.
     * Accepts a string that will match the label and rectangle id.
     */
    public void deleteRectangle(String name){
        // Create a temp variable to use lookup() function to find the rectangle id.
        String temp = "#"+name.replaceAll(" ", "_");

        // Remove the rectangle and text based off the id.
        Farm.getChildren().remove(Farm.lookup(temp));
        Farm.getChildren().remove(Farm.lookup(temp+"text"));
    }

    public boolean matchingName(String name){
        boolean match = false;

        // Loop through all ItemClass objects and see if name matches. If so, set match to true.
        for(int i=0; i<itemList.size(); i++){
            if(name.equals(itemList.get(i).getName())){
                // Set the matching variable to true.
                match = true;

                // Create dialog box.
                Dialog<Double> errmsg = new Dialog<>();
                errmsg.setTitle("ERROR");
                errmsg.setHeaderText("Cannot create an object with a matching name.");
                errmsg.setResizable(true);

                // Add button to close dialog box after user enters values.
                ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
                errmsg.getDialogPane().getButtonTypes().add(okButton);

                // Display the dialog box.
                errmsg.showAndWait();
            }
        }

        // Loop through all ItemContainer objects and see if name matches. If so, set match to true.
        for(int i=0; i<containerList.size(); i++){
            if(name.equals(containerList.get(i).getName())){
                // Set the matching variable to true.
                match = true;

                // Create dialog box.
                Dialog<Double> errmsg = new Dialog<>();
                errmsg.setTitle("ERROR");
                errmsg.setHeaderText("Cannot create an object with a matching name.");
                errmsg.setResizable(true);

                // Add button to close dialog box after user enters values.
                ButtonType okButton = new ButtonType("Okay", ButtonData.OK_DONE);
                errmsg.getDialogPane().getButtonTypes().add(okButton);

                // Display the dialog box.
                errmsg.showAndWait();
            }
        }

        return match;
    }
    
    public ItemsClass getItem(String name) {
    	String item = selectItem().getValue();
    	
    	for(int i=0; i<itemList.size();i++){
            if(item.equals(((ItemsClass) itemList.get(i)).getName())){
                return itemList.get(i);
            }
        }
		return null;
    }

    public ItemContainer getItemContainer(String name) {
    	String item = selectItem().getValue();
    	
    	for(int i=0; i<containerList.size();i++){
            if(item.equals(((ItemContainer) containerList.get(i)).getName())){
                return containerList.get(i);
            }
        }
		return null;
    }
    
    //Printing out the Item Values when selecting each specific Item or Item Container
    public TreeItem<String> selectItem(){
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        int childCMV = 0, childPPV = 0, parentPPV = 0;

        ItemsVisitor visitor = new ItemsVisitor();

        // Check to see if the selected item is a branch or leaf and show the respective commands.
        if (item == null) {
        	try {
        		System.out.println("SELECT A TREE ITEM!!");
            } catch (NullPointerException e) {
                System.out.println(e);
            }
        }else if(item.isLeaf()){
            this.itemCmds.setVisible(true);
            this.itemContCmds.setVisible(false);

        }else{
            this.itemCmds.setVisible(false);
            this.itemContCmds.setVisible(true);
        }

        if(item != null) {
	         //If drone is selected, show the "go home" button.
	        System.out.println(item.getValue());
	        if(item.getValue().equals("Drone")){
	            goHome.setVisible(true);
	        }else{
	            goHome.setVisible(false);
	        }

	        // Testing purposes.
            System.out.println(item.getValue());
            selectedItem = item.getValue();

	        /* 
	         * Loops through the itemList to find the matching item name and displays 
	         * the purchase price and current market price of that item to dashboard
	         * 
	         * Also, checks to see if selected TreeItem is equal to any itemList's parent,
	         * if so get a sum of all that parents children's price values.
	        */
	        for(int i=0; i<itemList.size();i++){
	            if(item.getValue().equals(((ItemsClass) itemList.get(i)).getName())){
	                purchasePriceValue.setText("$"+Integer.toString(((ItemsClass) itemList.get(i)).getPrice())+".00");
	                CurrentMarketValue.setText("$"+Integer.toString(((ItemsClass) itemList.get(i)).getCur_price())+".00");
	            }
	            if(item.getValue().equals(((ItemsClass) itemList.get(i)).getParent())){
	                childPPV += itemList.get(i).accept1(visitor);
	                childCMV += itemList.get(i).accept2(visitor);
	            }
	        }
	
	        /* 
	         * Loops through the containerList to find the matching item container name, then adds the sum of all children's prices to 
	         * the purchase price and current market price and displays info on dashboard.
	         * 
	         * purchase price value = parent's price + all children's prices.
	         * current market value = all children's cur_price's.
	        */
	        for(int i=0; i<containerList.size();i++){
	            if(item.getValue() == ((ItemsClass) containerList.get(i)).getName()){
	                parentPPV = containerList.get(i).accept1(visitor) + childPPV;
	                purchasePriceValue.setText("$"+Integer.toString(parentPPV)+".00");
	                CurrentMarketValue.setText("$"+Integer.toString(childCMV)+".00");
	            }
	        }
	
	        return item;
        }else {
        	return null;
        }
    }
}