package com.example.dronecs420;

import javafx.fxml.FXMLLoader;

public class Dashboard {
    private static Dashboard INSTANCE;

    private FXMLLoader start;

    private Dashboard(){
        start = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    }

    public static Dashboard getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new Dashboard();
        }
        return INSTANCE;
    }

    public void setDashboard(FXMLLoader create){
        this.start = create;
    }
}


