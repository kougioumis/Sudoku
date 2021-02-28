import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



 public class SudokuBoard extends JPanel {
	 
	 
	 public static final int ROWS = 3;
     public static final int COLS = 3;
    
     
     private static SubBoard[] subBoards;
    
     public SudokuBoard() {
    	 setBorder(new EmptyBorder(4, 4, 4, 4));
    	 subBoards = new SubBoard[ROWS*COLS];
    	 setLayout(new GridLayout(ROWS, COLS, 1, 1)); ///creating the 3x3 grid on the panel
    	 
    	 for (int row = 0; row < ROWS; row++) {
    		 for ( int col = 0; col < COLS; col++ ) {
    			 int index = ( row * ROWS ) + col;     ///create 3x3 matrix whith jtextfilds inside through subboard  
    			 SubBoard board = new SubBoard();
    			 
    			 board.setBorder( new CompoundBorder( new LineBorder( Color.LIGHT_GRAY ), new EmptyBorder( 4, 4, 4, 4 ) ) );
    			 subBoards[ index ] = board;
    			 add( board );
    			 
    		 }
    	 }
     }
     
     
     public SudokuBoard(char [] SudokuValues) {
    	int index = 0;
   	    
    	for(int i = 0; i<ROWS; i++) {
    		for(int j = 0; j<ROWS; j++) {
    	    	 for(int k = 0; k<ROWS; k++) {
    	    		 for(int w = 0; w<ROWS; w++) {
    	
    	    			 
                        if( SudokuValues[ index ] == '.' ) {
                        	 
                			 subBoards[k + ROWS*i].getFields(w + ROWS*j).setText("");
                		
                         }
            			 
            			 else if( (SudokuValues[ index ] != -1) & SudokuValues[ index ] !=  '.') {
            				 subBoards[k + ROWS*i].getFields(w + ROWS*j).setText(String.valueOf(SudokuValues[index]));
            				 subBoards[k + ROWS*i].getFields(w + ROWS*j).setHorizontalAlignment(JLabel.CENTER);
            				 subBoards[k + ROWS*i].getFields(w + ROWS*j).setBackground(new Color(220,220,220));
            				 subBoards[k + ROWS*i].getFields(w + ROWS*j).setEditable(false);
            				 
            			 }
                        
                        index++;
                        
    	    		 }
    	    		 
    	    	 }
    			 
    		 }
    		
    	 }
     
     }
    
     public static  void printFinalValues(int[] finalValues) {
     	int index = 0;
  
     	
     	
     	for(int i = 0; i<ROWS; i++) {
     		for(int j = 0; j<ROWS; j++) {
     	    	 for(int k = 0; k<ROWS; k++) {
     	    		 for(int w = 0; w<ROWS; w++) {
     	    			//Put the final Values from the solved sudoku in subboards
     	    			 
     	    			 
     	    			 subBoards[k + ROWS*i].getFields(w + ROWS*j).setText(String.valueOf(finalValues[index] )  );
     	    			 index++;
                     
                         
     	    		 }
     	    		 
     	    	 }
     			 
     		 }
     		
     	 }
          
      }
     
    
     public static  void printFromBut(int [] Values) {
    	 
    	 int index = 0;
    	  
      	
      	
      	for(int i = 0; i<ROWS; i++) {
      		for(int j = 0; j<ROWS; j++) {
      	    	 for(int k = 0; k<ROWS; k++) {
      	    		 for(int w = 0; w<ROWS; w++) {
      	    			//Print the final Values from the solved sudoku
      	    			 
      	    			 
      	    			 if(Values[index] == 0 && (subBoards[k + ROWS*i].getFields(w + ROWS*j).isEditable())) {
      	    				 subBoards[k + ROWS*i].getFields(w + ROWS*j).setText(null);
      	    			 }
      	    			 
      	    			
      	    			 else {
      	    				 if((subBoards[k + ROWS*i].getFields(w + ROWS*j).isEditable())) {
      	    					 subBoards[k + ROWS*i].getFields(w + ROWS*j).setText(String.valueOf(Values[index] )  );
      	    				 }
      	    				 
      	    			 }
      	    			 
      	    			  
                          index++;
                      
                          
      	    		 }
      	    		 
      	    	 }
      			 
      		}
      		
      	}
           
     }
         
     
     
    
     public static void clearSudokuBoard( ) {
    	int index = 0;	
    	 for(int i = 0; i<ROWS; i++) {
         		for(int j = 0; j<ROWS; j++) {
         	    	 for(int k = 0; k<ROWS; k++) {
         	    		 for(int w = 0; w<ROWS; w++) {
         	    			 
         	    			 subBoards[k + ROWS*i].getFields(w + ROWS*j).setText(null);
         	    			 subBoards[k + ROWS*i].getFields(w + ROWS*j).setEditable(true);
         	    			 subBoards[k + ROWS*i].getFields(w + ROWS*j).setBackground(Color.WHITE);
         	    			 index++;
  
         	    		 }
         	    	 }
         		}    	 
    	 
    	 }
     }
     
     public static boolean Solve(int[][] Values)
 	{
 		//we need a support array with same size as board to keep track of status
 		int[][] temp = new int[Values.length][Values[0].length];
 		//now we set the status, for each non-zero position we set status as 2 means fixed!
 		for(int i=0; i<9; i++) { //suduko is 9*9
 			for(int j=0; j<9; j++) {
 				temp[i][j] = Values[i][j]>0?2:0;   
 				
 			}
 		}
 		
 		return Solve(Values, temp, 0, 0);//we start from very first one element
 	}

 	//define the key recursive searching method
 	//board is the values for the game, status is support array to know the status of each position
 	//x,y is the coordinates of position we are intersted in now
 	public static boolean Solve(int[][] Values, int[][] temp, int x, int y)
 	{
 		//now we start processing
 		//first step, we judge if we come to the end, we start from (0,0) until (8,8) then (9,0) will be invalid and should stop!
 		if(x==9)//we come to the end
 		{
 			//we need check of all values are set
 			int count = 0;//we need 81 set values
 			for(int i=0; i<9; i++) {
 				for(int j=0; j<9; j++) { //suduko is 9*9
 				
 					count += temp[i][j]>0?1:0;//we update the value only if status is non-zero as set
 				}
 			}
 			
 			if(count==81)//all set
 				return true;//great we find one!
 			else
 				return false;//sorry this trial failed 
 		}
 		//other wise we can proceed further
 		if(temp[x][y]>=1)//if the current position has already been set!
 		{
 			//we step further to next value;
 			int nextX = x;
 			int nextY = y+1;//we proceed to the right if it is the end, we come to front of next row
 			if(nextY == 9)//last element
 			{
 				nextX = x+1; nextY = 0;
 			}
 			//recursive call of next position
 			return Solve(Values, temp, nextX, nextY);//sorry we should return the value here!
 		}
 		else//this is the key of the method, we check row/column/3*3 box to decide all possible values
 		{
 			//we create a 9-length array to check what values have been used in nearby positions thus cannot be used
 			boolean[] used = new boolean[9];
 			//check row
 			for(int i=0; i<9;i++) {
 				if(temp[x][i]>=1)
 					used[Values[x][i]-1] = true;//notice we call board[x][i] and use the value-1 as index to set used array
 			}

 			//similarly we check column
 			for(int i=0; i<9;i++)
 			{
 				if(temp[i][y]>=1)
 					used[Values[i][y]-1] = true;
 			}

 			//now we need check the associated 3*3 SubBoard to check nearby values
 			//the rows start from 0,3,6, columns also start from 0,3,6
 			for( int i=x-(x%3); i<x-(x%3)+3; i++ )//this makes sure we start from the correct rows
 				for( int j=y-(y%3);j<y-(y%3)+3; j++ ) {//y settings are similar
 					if(temp[i][j] >= 1)
 						used[ Values[i][j] - 1 ] = true;
 				}
 			
 			//after the check of all row/column/3*3 box, we try to assign each possible value to current position and try next!
 			//also remember the use of temp array is for further recovery if that try failed, so we set temp to 1 
 			//to be different from 2 (pre-fixed) so later we can reverse the settings
 			for(int i=0; i<used.length; i++) {
 				if( !used[i] ) //notice only those unused values can be set here
 				{
 					//we set and proceed and lastly reverse the settings for next iteration!
 					temp[x][y] = 1;//1 as we-set status, different from 0-nonset and 2-pre-fixed
 					Values[x][y] = i+1;//index+1 is the value
 					//we repeat the index increasing operation
 					int nextX = x;
 					int nextY = y+1; //we proceed to the right if it is the end, we come to front of next row
 					if(nextY==9)//last element
 					{
 						nextX = x+1; nextY = 0;
 					}
 					//recursive call of next position
 					if(Solve(Values, temp, nextX, nextY))
 						return true;

 					//now it means the setting failed we should reverse the setting to try next value
 					for(int m = 0; m<9; m++)
 						for(int n = 0; n<9; n++)
 						{
 							//only reverse-set those values behind current (x,y) position
 							if(m>x||(m==x && n>=y))
 							{
 								if(temp[m][n]==1)//only reverse those we-set nodes
 								{
 									temp[m][n] = 0;
 									Values[m][n] = 0;
 								}
 							}
 						}
 					}
 				}
 			}
 		
 		// return a default false signal
 		return false;
 	}
 	
 	
 	public static void setNums(String num) {
 		
 		subBoards[0].getFields(0).setText(num);
 		
 	}
     
     
     
     public static SubBoard getSubBoard(int i) {
    	 return subBoards[i];
     }
     
     public static boolean isNumeric(String str)  
 	{  
 	  try  
 	  {  
 	    double d = Double.parseDouble(str);  
 	  }  
 	  catch(NumberFormatException nfe)  
 	  {  
 	    return false;  
 	  }  
 	  return true;  
 	}
 	
 }
 
 
 
 