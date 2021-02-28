import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

 public class SubBoard extends JPanel {

        public static final int ROWS = 3;
        public static final int COLS = 3;
        private static int x_cnt=0;
		private static int y_cnt=0;
		private static int x_cnt2;
		private static int y_cnt2;
        private final  JTextField[] fields;
        private static double a;
        private static double b;
        private static double c;
        private static double d;
        public static double width1 = 0.0080;
    	public static double height1 = 0.0390;		
    	public static double width ;
    	public static Point loc ;
    	public static Point location ;
    	
    	
    	public static double height;
		
        public SubBoard() {
            
        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    		width = (int) screenSize.getWidth();
    		height = (int) screenSize.getHeight();
    		
    		a =  (int) (width*width1);
 			a++;
 			
 			b =  (int) (height*height1);
 			b++;
 			
    		    		
        	this.setLayout(new GridLayout(ROWS, COLS, 4, 4));
            fields = new JTextField[ROWS * COLS];
           
           
			
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    int index = (row * COLS) + col;
                    
                    
                    final JTextField field = new JTextField(1); //create the jtextfield
                                        
         
                    field.addFocusListener(new FocusListener() {
                    	
						@Override
						public void focusGained(FocusEvent e) {
							
							for(int i = 0; i<ROWS; i++) {
				         		for(int j = 0; j<ROWS; j++) {
				         	    	 for(int k = 0; k<ROWS; k++) {
				         	    		 for(int w = 0; w<ROWS; w++) {
			         	    				 if(!(SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).isEditable())) {
					         	    			 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(new Color(220, 220,220));  //fields given made gray and uneditable				  
					         	    			 

			         	    				 }
			         	    				
			         	    				 else {
				         	    				 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(Color.WHITE);              // the other remain white

			         	    				 }

				         	    		 }
				         	    	 }
				         		}
							}
							
							
							
							Point place = field.getLocationOnScreen(); //(0,0) Point of the Sudoku Table
							String Value = field.getText();
							String temp;
							loc = field.getLocationOnScreen();
							
							
							Point p1 = fields[0].getLocation();
							Point p2 = fields[4].getLocation();
														
							c = (int) (p2.x-p1.x);
							d = (int) (p2.y-p1.y+3);
							
							
							
							if(height<=800 && height>=545 ){
								d++;
							}else if (height<=545){
								d++;
								d++;
							}
																				
							for( double i =(location.x+a); i<=place.x; i += c ) {
								x_cnt++;
							}
							
							for( double j =(location.y+b); j<=place.y; j += d ) {
								y_cnt++;
								
							}
							
							x_cnt2 = x_cnt;
							y_cnt2 = y_cnt;
							
							System.out.println(place.getX()+" " + place.getY());
				        	System.out.println((x_cnt2)+ " " + (y_cnt2));
							
							
							for(int i = 0; i<ROWS; i++) {
								for(int j = 0; j<ROWS; j++) {
				         	    	 for(int k = 0; k<ROWS; k++) {
				         	    		 for(int w = 0; w<ROWS; w++) {
			         	    				 temp = SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).getText();                                //when a textfield is seleced
			         	    				 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setHorizontalAlignment(JLabel.CENTER);           //horizontal alingment
				         	    			 if(temp.equals(Value) && !temp.equals("")) {                                                               //
				         	    				SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(new Color(255, 255, 100));      //and makes yellow all the same

				         	    			 }
				         	    		 }
				         	    	 }
								}
							}
						}

						@Override
						public void focusLost(FocusEvent e) {
							
							
							for(int i = 0; i<ROWS; i++) {
				         		for(int j = 0; j<ROWS; j++) {
				         	    	 for(int k = 0; k<ROWS; k++) {
				         	    		 for(int w = 0; w<ROWS; w++) {
			         	    				 if(!(SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).isEditable())) {
					         	    			 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(new Color(220, 220,220));  //fields given made gray and uneditable				  
					         	    			 

			         	    				 }
			         	    				
			         	    				 else {
				         	    				 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(Color.WHITE);              // the other remain white

			         	    				 }

				         	    		 }
				         	    	 }
				         		}
							}
							
							x_cnt = 0;
				        	y_cnt = 0;
							
						}
						
                    	
                    });
                   
                    fields[index] = field; 
                    add( field );  
                   
                }
            }
        }
        
        
        public static void findEquals (String num) {
        	
        	String temp;
        	
        	for(int i = 0; i<ROWS; i++) {
				for(int j = 0; j<ROWS; j++) {
         	    	 for(int k = 0; k<ROWS; k++) {
         	    		 for(int w = 0; w<ROWS; w++) {
     	    				 temp = SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).getText();                                //when a textfield is seleced
     	    				 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setHorizontalAlignment(JLabel.CENTER);           //horizontal alingment
         	    			 
     	    				 if(temp.equals(num)) {
         	    			
         	    				 Point p7 = SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).getLocationOnScreen();         	    				 
         	    				 
         	    				 if (p7.x==loc.x || p7.y==loc.y){
         	    			
         	    					 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(new Color(255, 0, 0));	
         	    				
         	    				}
         	    				
         	    			 }
         	    		 }
         	    	 }
				}
        
        	}
        	
        } 
        
        
        
        
        public static void clear (String num) {
        	
        	for(int i = 0; i<ROWS; i++) {
         		for(int j = 0; j<ROWS; j++) {
         	    	 for(int k = 0; k<ROWS; k++) {
         	    		 for(int w = 0; w<ROWS; w++) {
     	    				 if(!(SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).isEditable())) {
	         	    			 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(new Color(220, 220,220));  //fields given made gray and uneditable				  
	         	    			 

     	    				 }
     	    				
     	    				 else {
         	    				 SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(Color.WHITE);              // the other remain white

     	    				 }

         	    		 }
         	    	 }
         		}
        	}
        }
        
        
       
        
        public static void refreshTable(String num) {
         	
        	System.out.println((x_cnt2-1)+ " " + (y_cnt2-1));
        	InterFaceMaker.setSudokuValuesSolve((x_cnt2-1), (y_cnt2-1), num);
        
        }
        
        
        
        
        public JTextField getFields(int i) {
        	return fields[i];
        }
        
        
        
        
        public static void Verify() {
   		      	
        	String temp;
        	
        	for(int i = 0; i<ROWS; i++) {
         		for(int j = 0; j<ROWS; j++) {
         	    	 for(int k = 0; k<ROWS; k++) {
         	    		 for(int w = 0; w<ROWS; w++) {
         	    			
         	    			 temp = SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).getText();
         	    			 int y = InterFaceMaker.SudokuValuesSolve[k + ROWS*i][w + ROWS*j]; 
         	    			 
         	    			 if (!temp.equals("") && (SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).isEditable()) && y!=Integer.valueOf(temp)){
         	    				 	SudokuBoard.getSubBoard(k + ROWS*i).getFields(w + ROWS*j).setBackground(Color.BLUE);
         	    			 }
         	    		 }
         	    	 }
         		}
        	}
        }
 }
 
