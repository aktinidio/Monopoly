import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Dice extends JComponent implements Runnable{
    
    private int dValue;
    private boolean flag;
    private GameSystem gs;

    public Dice(int n, GameSystem gs){
    	this.gs = gs;
        setPreferredSize(new Dimension(70,70));
        dValue = n;
    }
    
    public Dice(int n){
        setPreferredSize(new Dimension(70,70));
        dValue = n;
    }
    
    public void setNumber(int n){
    	dValue = n;    	
    }
    
    public void roll(boolean b){
    	flag=b;
    	new Thread(this).start();
    }
        
    public void run() {
        try {
        	for(int i=0;i<19;i++){
        		dValue = new Random().nextInt(6)+1;
        		repaint();        		
        		Thread.sleep(i*i);
        	}
        }
        catch (InterruptedException ex){}
        if(flag) gs.resume();
    }
    
    public void paintComponent(Graphics g){
        int w = getWidth();
        int h = getHeight();
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        g2.setColor(Color.BLACK);
        
        g2.drawRect(0, 0, w-1, h-1);
        g2.drawRect(1, 1, w-3, h-3);
        
        switch (dValue) {
            case 1:
                drawSpot(g2, w/2, h/2);
                break;
            case 3:
                drawSpot(g2, w/2, h/2);
            case 2:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                break;
            case 5:
                drawSpot(g2, w/2, h/2);
            case 4:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                drawSpot(g2, 3*w/4, h/4);
                drawSpot(g2, w/4, 3*h/4);
                break;
            case 6:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                drawSpot(g2, 3*w/4, h/4);
                drawSpot(g2, w/4, 3*h/4);
                drawSpot(g2, w/4, h/2);
                drawSpot(g2, 3*w/4, h/2);
                break;
        }
    }
    
    private void drawSpot(Graphics2D g2, int x, int y) {
        g2.fillOval(x-11/2, y-11/2, 11, 11);
    }
}