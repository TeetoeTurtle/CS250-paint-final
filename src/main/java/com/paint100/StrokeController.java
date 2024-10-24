package com.paint100;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.StrokeLineCap;

import static java.lang.Math.abs;

/**
 * Controls the logic for the drawing (stroking) of all the shapes
 */
public class StrokeController {

    /**
     * Controls the logic for the drawing (stroking) of all the shapes
     */
    public StrokeController() {};

    /**
     * Draws an n-sided star on a GraphicsContext based on the mouses starting and ending location
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     * @param numPoints The number of points on the star
     */
    public void drawStar(GraphicsContext gc, MouseEvent event, double mouseXInitial, double mouseYInitial, int numPoints) {
        gc.setMiterLimit(10);

        double mouseXFinal = event.getX();
        double mouseYFinal = event.getY();
        double outerRadius = Math.sqrt(Math.pow(mouseXFinal - mouseXInitial,2) + Math.pow(mouseYFinal - mouseYInitial, 2));
        double innerRadius = outerRadius * 0.5; //"Magic number" acts as the point inset multiplier (1 making it a regular polygon).
        //0.5 was choosen
        double angleStep = Math.PI / numPoints;

        gc.beginPath();

        for (int i = 0; i < numPoints * 2; i++) {
            double radius;
            if (i % 2 == 0){
                radius = outerRadius;
            }
            else{
                radius = innerRadius;
            }

            double angle = i * angleStep;
            double x = mouseXInitial + radius * Math.cos(angle - Math.PI / 2);
            double y = mouseYInitial + radius * Math.sin(angle - Math.PI / 2);

            if (i == 0) {
                gc.moveTo(x,y);
            }
            else {
                gc.lineTo(x,y);
            }
        }
        gc.closePath();
        gc.stroke();
    }


    /**
     * Draws a rectangle on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     */
    public void drawRectangle(GraphicsContext gc, MouseEvent event,double mouseXInitial, double mouseYInitial) {
        gc.setMiterLimit(10);
        gc.setLineCap(StrokeLineCap.SQUARE);
        double[] xCord = new double[]{mouseXInitial,mouseXInitial,event.getX(),event.getX()};
        double[] yCord = new double[]{mouseYInitial,event.getY(),event.getY(),mouseYInitial};
        gc.strokePolygon(xCord,yCord,4);
    }

    /**
     * Draws an any sided regular polygon on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     * @param numSides Number of sides for the polygon
     */
    //Uses the general formula for the equation of a general polygon
    public void drawPolygon(GraphicsContext gc, MouseEvent event ,double mouseXInitial, double mouseYInitial, int numSides) {
        gc.setMiterLimit(10);
        double[] xCord = new double[numSides];
        double[] yCord = new double[numSides];
        double r = Math.sqrt((Math.pow((event.getX()-mouseXInitial),2)+Math.pow((event.getY()-mouseYInitial),2))); //Calcs the radius
        //double startDirection = Math.atan2((event.getY()-mouseYInitial), event.getX()-mouseXInitial); //Calcs mouse direction
        for (int i = 0; i < numSides; i++) {
            xCord[i]= mouseXInitial + (r * Math.cos((2*Math.PI * i)/ numSides));
            yCord[i]= mouseYInitial + (r * Math.sin((2*Math.PI * i)/ numSides));
        }
        gc.strokePolygon(xCord,yCord,numSides);
    }

    /**
     * Draws a square on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     */
    //Draws a square based on the mouses starting and ending location, but does some basic math to ensure equal length sides.
    //Is dependent on the mouse's Y location (as opposed to X location)
    public void drawSquare(GraphicsContext gc, MouseEvent event,double mouseXInitial, double mouseYInitial) {
        gc.setMiterLimit(10);
        gc.setLineCap(StrokeLineCap.SQUARE);
        double[] xCord;
        double[] yCord;
        double xInital,yInital,xFinal,yFinal;
        xInital = mouseXInitial;
        yInital = mouseYInitial;
        yFinal=event.getY();
        double A = abs(yInital - event.getY());
        if(xInital<event.getX()){
            xFinal=xInital+A;
        }
        else{
            xFinal=xInital-A;
        }
        xCord = new double[]{xInital, xInital, xFinal, xFinal};
        yCord = new double[]{yInital,yFinal,yFinal,yInital};
        gc.strokePolygon(xCord,yCord,4);
    }

    /**
     * Draws a circle on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     */
    //Draws a circle. Due to strokeOval only taking parameters for the upper left corner, it is more complex than strokePolygon. Determines the upper left corner
    //by constantly modifying the mouse's initial coords with its current coords. Also ensures that width and height are equal
    public void drawCircle(GraphicsContext gc, MouseEvent event,double mouseXInitial, double mouseYInitial) {
        if (mouseXInitial<event.getX() && mouseYInitial<event.getY()){
            gc.strokeOval(mouseXInitial,mouseYInitial,abs(mouseYInitial-event.getY()),abs(mouseYInitial-event.getY()));
        }
        else if(mouseXInitial>event.getX() && mouseYInitial<event.getY()){
            gc.strokeOval(mouseXInitial-(event.getY()-mouseYInitial),mouseYInitial,abs(mouseYInitial-event.getY()),abs(mouseYInitial-event.getY()));
        }
        else if(mouseXInitial>event.getX() && mouseYInitial>event.getY()){
            gc.strokeOval(mouseXInitial-(mouseYInitial-event.getY()),mouseYInitial-(mouseYInitial-event.getY()),abs(mouseYInitial-event.getY()),abs(mouseYInitial-event.getY()));
        }
        else if(mouseXInitial<event.getX() && mouseYInitial>event.getY()){
            gc.strokeOval(mouseXInitial,mouseYInitial-(mouseYInitial-event.getY()),abs(mouseYInitial-event.getY()),abs(mouseYInitial-event.getY()));
        }
    }

    /**
     * Draws a oval on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     */
    //Draws an oval. Due to strokeOval only taking parameters for the upper left corner, it is more complex than strokePolygon. Determines the upper left corner
    //by constantly modifying the mouse's initial coords with its current coords
    public void drawOval(GraphicsContext gc, MouseEvent event,double mouseXInitial, double mouseYInitial) {
        if (mouseXInitial<event.getX() && mouseYInitial<event.getY()){
            gc.strokeOval(mouseXInitial,mouseYInitial,abs(mouseXInitial-event.getX()),abs(mouseYInitial-event.getY()));
        }
        else if(mouseXInitial>event.getX() && mouseYInitial<event.getY()){
            gc.strokeOval(mouseXInitial-(mouseXInitial-event.getX()),mouseYInitial,abs(mouseXInitial-event.getX()),abs(mouseYInitial-event.getY()));
        }
        else if(mouseXInitial>event.getX() && mouseYInitial>event.getY()){
            gc.strokeOval(mouseXInitial-(mouseXInitial-event.getX()),mouseYInitial-(mouseYInitial-event.getY()),abs(mouseXInitial-event.getX()),abs(mouseYInitial-event.getY()));
        }
        else if(mouseXInitial<event.getX() && mouseYInitial>event.getY()){
            gc.strokeOval(mouseXInitial,mouseYInitial-(mouseYInitial-event.getY()),abs(mouseXInitial-event.getX()),abs(mouseYInitial-event.getY()));
        }
    }

    /**
     * Draws a regular triangle on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     */
    //Draws a regular triangle based on the mouses starting and ending location. Changes drawing behavior depending on if the mouse's current
    //X & Y coords are greater or less than the starting X & Y coords
    public void drawTriangle(GraphicsContext gc, MouseEvent event,double mouseXInitial, double mouseYInitial){
        gc.setMiterLimit(10);
        gc.setLineCap(StrokeLineCap.ROUND);

        double[] xCord;
        double[] yCord;
        if (mouseYInitial < event.getY()) {
            xCord = new double[]{mouseXInitial, abs(mouseXInitial + event.getX()) / 2, event.getX()};
            yCord = new double[]{event.getY(), mouseYInitial, event.getY()};
        } else {
            xCord = new double[]{mouseXInitial, abs(mouseXInitial + event.getX()) / 2, event.getX()};
            yCord = new double[]{mouseYInitial, event.getY(), mouseYInitial};
        }
        gc.strokePolygon(xCord, yCord, 3);
    }

    /**
     * Draws a right triangle on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     */
    //Draws a right triangle based on the mouses starting and ending location. Changes drawing behavior depending on if the mouse's current
    //X & Y coords are greater or less than the starting X & Y coords
    public void drawRTriangle(GraphicsContext gc, MouseEvent event,double mouseXInitial, double mouseYInitial){
        gc.setMiterLimit(10);
        gc.setLineCap(StrokeLineCap.ROUND);

        double[] xCord;
        double[] yCord;
        xCord = new double[]{mouseXInitial,mouseXInitial,event.getX()};
        yCord = new double[]{mouseYInitial,event.getY(),event.getY()};

        gc.strokePolygon(xCord,yCord,3);
    }

    /**
     * Draws a regular hexagon on a GraphicsContext based on the mouses starting and ending location
     *
     * @param gc GraphicsContext to draw on
     * @param event Mouse's current location
     * @param mouseXInitial Mouse's initial X position
     * @param mouseYInitial Mouse's initial Y position
     */
    public void drawHexagon(GraphicsContext gc, MouseEvent event ,double mouseXInitial, double mouseYInitial) {
        gc.setMiterLimit(10);
        int numSides = 6;
        double[] xCord = new double[numSides];
        double[] yCord = new double[numSides];
        double r = Math.sqrt((Math.pow((event.getX()-mouseXInitial),2)+Math.pow((event.getY()-mouseYInitial),2))); //Calcs the radius
        //double startDirection = Math.atan2((event.getY()-mouseYInitial), event.getX()-mouseXInitial); //Calcs mouse direction
        for (int i = 0; i < numSides; i++) {
            xCord[i]= mouseXInitial + (r * Math.cos((2*Math.PI * i)/ numSides));
            yCord[i]= mouseYInitial + (r * Math.sin((2*Math.PI * i)/ numSides));
        }
        gc.strokePolygon(xCord,yCord,numSides);
    }


}
