import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class InterFaceMaker extends JPanel implements ActionListener {

	private String easy;
	private String intermediate;
	private String expert;
	private char [] SudokuValues;
	public SudokuBoard SudokuBoards;
	private char[] realSudokuValues;
	public static  int SudokuValuesSolve[][];
	public static int [] finalValues;
	public static int [] tempValues;
	
	public static void main(String[] args) {
        new InterFaceMaker(); // Everything Starts here
    }
    
    

    public InterFaceMaker() {
    	
    	JFrame SudokuWindow = new JFrame();
    	


	    SudokuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    SudokuWindow.setLayout(new BorderLayout());
	    SudokuWindow.add(new SudokuBoard());
	    SudokuWindow.add(new Menu1(), BorderLayout.SOUTH);
	    SudokuWindow.setResizable(false);
	    SudokuWindow.setPreferredSize( new Dimension( 640, 480 ) );
	    SudokuWindow.pack();
	    SudokuWindow.addComponentListener( new ComponentListener() {
            public void componentResized( ComponentEvent e ) {}
            public void componentMoved( ComponentEvent e ) {
                Point p = SudokuWindow.getLocation() ;
                SubBoard.location=  p;	
                //System.out.println(SubBoard.location);
                }
            public void componentShown( ComponentEvent e ) {}
            public void componentHidden( ComponentEvent e ) {}
        } );
    
	    
	    
	    JMenuBar mBar = new JMenuBar();
		
		JMenu menu1 = new JMenu("New Game");
	    
	    
	    JMenuItem mItem11 = new JMenuItem("Easy");
		mItem11.setMnemonic( KeyEvent.VK_E );
	    mItem11.addActionListener( this );
	    
	    
	    JMenuItem mItem12 = new JMenuItem("Intermediate");
		mItem12.setMnemonic( KeyEvent.VK_I );
	    mItem12.addActionListener( this );
	    
	    JMenuItem mItem13 = new JMenuItem("Expert");
		mItem13.setMnemonic( KeyEvent.VK_X );
	    mItem13.addActionListener( this );
	    
	    
		mBar.add( menu1 );
		SudokuWindow.setJMenuBar(mBar);
	    menu1.add( mItem11 );
	    menu1.add( mItem12 );
	    menu1.add( mItem13 );
	    
	    
	    SudokuWindow.pack();
		SudokuWindow.setVisible(true);
		
	    this.easy = "http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=easy";
	    this.intermediate = "http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=intermediate";
	    this.expert = "http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=expert";
		
	    
    }

    
    public void actionPerformed(ActionEvent evt) {
		
	  	String menuItemActivated = evt.getActionCommand();
	  			
	
	  	
	  	
	  	switch (menuItemActivated.toLowerCase()) {
	  	
	  		case "easy":
			try {
				download_values(easy);
			}
			
			catch (IOException e) {
				System.out.println("IO EXCEPTION");
				e.printStackTrace();
			}	
	  			break;
	  		
	  		case "intermediate":
			try {
				download_values(intermediate);
			} catch (IOException e) {
				System.out.println("IO EXCEPTION");
				e.printStackTrace();
			}
	  			break;
	  		
	  		case "expert":
			try {
				download_values(expert);
			} catch (IOException e) {
				System.out.println("IO EXCEPTION");
				e.printStackTrace();
			}
	  			break;
	  		
	  		default:
	  			System.out.print("Invalid choice");
	  			break;
			
			
	  	
	  	}
		
	}

	public void download_values(String difficulty) throws IOException {
		
		URL ValuesUrl;
		URLConnection ValuesUrc;
		int c = 0;
		PrintWriter output;
		InputStream ValuesInp;
		int i = 0;
		int k = 0;
		
		SudokuBoard.clearSudokuBoard();
		
		SudokuValues = new char[81 + 9 + 1]; // 9*'/n' plus the EOF
		realSudokuValues = new char[81]; //SudokuValues
		SudokuValuesSolve = new int[9][9];
		tempValues = new int[81];
		
		ValuesUrl = new URL(difficulty);
		ValuesUrc = ValuesUrl.openConnection();
		ValuesInp = ValuesUrc.getInputStream();
		
		
		while( (c = ValuesInp.read()) != -1) {
			SudokuValues[i] = (char) c;
			//System.out.print((char) c);
			i++;		
		}
		
		for(i = 0; i<SudokuValues.length; i++) {
			if(SudokuValues[i] != '\n' & SudokuValues[i] !=  (-1)) {
				realSudokuValues[k] = SudokuValues[i];
				k++;
			}
			
			//else if
		}
		
		k=0;
		for(i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				if(realSudokuValues[k] == '.') {
					SudokuValuesSolve[i][j] = 0;
					tempValues[k] = 0;
					
				}
				
				else {
					SudokuValuesSolve[i][j] = Character.getNumericValue(realSudokuValues[k]);
					tempValues[k] = Character.getNumericValue(realSudokuValues[k]);
				}
				
				//System.out.print(SudokuValuesSolve[i][j] + " ");

				k++;
			}
		}
		
		
		finalValues = new int[81];

	    System.out.println(SudokuValues);
	    System.out.println();

		new SudokuBoard(realSudokuValues);
		
		//new SudokuBoard(realSudokuValues);
	    
	}

	public static void beginSolving() {
		
		if(SudokuBoard.Solve( SudokuValuesSolve ) ) {
			System.out.println("Found a solution!");
			Print(SudokuValuesSolve);
		}
		
		else {
			System.out.println("No solution was found!");
		}
	}
	


	public static void Print(int[][] Values) {
		
		int index = 0;
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				finalValues[index] = Values[i][j];
				//System.out.print(finalValues[index] + " ");
				index++;

			}
			
			//System.out.println();
		}
		
		
		SudokuBoard.printFinalValues( finalValues );

	}

	
	public static int[][] getSudokuValuesSolve() {
		return SudokuValuesSolve;
	
	}
	
	public static void setSudokuValuesSolve(int i, int j, String num) {
		
		//System.out.println(i +" " + j);
		tempValues[ j*9 + i ] = Integer.parseInt(num); 
		SudokuBoard.printFromBut( tempValues );
		

	}
	
     

}


