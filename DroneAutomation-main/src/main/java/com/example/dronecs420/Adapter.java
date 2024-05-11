package com.example.dronecs420;

import java.io.IOException;

public class Adapter extends Adaptee{
    
    Adaptee adp = new Adaptee();
    
    public Adapter() throws IOException {
		
	}

	public void launch() throws IOException, InterruptedException{
		
		// Get connection to the drone.
		adp.initialize();
		//Activate the Tello-SDK
		adp.activateSDK();
		// Get battery life before taking flight.
	    adp.getBattery();
	    
		try {
	    	adp.takeoff();
    	}catch (Exception e) {
	    	e.printStackTrace();
	    }
    }
	
	public void gotoXY(int x, int y, int speed) throws IOException {
		
		// Get connection to the drone.
		adp.initialize();
		//Activate the Tello-SDK
		adp.activateSDK();
		// Get battery life before taking flight.
	    adp.getBattery();
		
		try {
			adp.takeoff();
	    	adp.gotoXY(x, y, speed);
	    	adp.turnCW(360);
	    	adp.hoverInPlace(2);
	    	adp.gotoXY(-x, -y, speed);
	    	adp.land();
	    	adp.end();
    	}catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public void scanFarm() throws IOException {
		int X = 200, Y = 200;
		
		// Get connection to the drone.
		adp.initialize();
		//Activate the Tello-SDK
		adp.activateSDK();
		// Get battery life before taking flight.
	    adp.getBattery();
		
		try {
			adp.takeoff();
			adp.increaseAltitude(30);
			adp.gotoXY(X, Y, 100);
			adp.turnCW(180);
			
			for(int i=0; i < 10; i++) {
				adp.gotoXY(X, 0, 100);
				adp.turnCW(90);
				adp.gotoXY(30, 0, 100);
				adp.turnCW(90);
				adp.gotoXY(X, 0, 100);
				adp.turnCCW(90);
				adp.gotoXY(30, 0, 100);
				adp.turnCCW(90);
			}
			
			adp.gotoXY(-X, Y, 100);
			adp.land();
			adp.end();
    	}catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
