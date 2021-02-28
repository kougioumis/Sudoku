import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



 public class Menu1 extends JPanel implements ActionListener {
	 
	 final JButton eraserBut;
	 final JButton solverBut;
	 final JButton undoImageBut;
	 private int counter=0;
	 
	 
	 public Menu1() {
        	
        	setBorder(new EmptyBorder(4, 4, 4, 4));
            setLayout(new GridBagLayout());
            GridBagConstraints gbCon = new GridBagConstraints();
           
            gbCon.gridx = 1;  //Init the coordinates
            gbCon.gridy = 0;
            gbCon.weightx = 1;
            gbCon.fill = GridBagConstraints.HORIZONTAL;

            ImageIcon undoImage = new ImageIcon("C:/Users/Giorgos_Kougioumis/workspace/Sudoku/src/return.png");
            ImageIcon eraser = new ImageIcon("C:/Users/Giorgos_Kougioumis/workspace/Sudoku/src/eraser.png");
            ImageIcon solver = new ImageIcon("C:/Users/Giorgos_Kougioumis/workspace/Sudoku/src/solution.png");
            
            JButton oneBut = new JButton("1");
            JButton twoBut = new JButton("2");
            JButton ThreeBut = new JButton("3");
            JButton FourBut = new JButton("4");
            JButton FiveBut = new JButton("5");
            JButton SixBut = new JButton("6");
            JButton SevenBut = new JButton("7");
            JButton EightBut = new JButton("8");
            JButton NineBut = new JButton("9");
            eraserBut = new JButton(eraser);
            
            solverBut = new JButton(solver);
            undoImageBut = new JButton(undoImage);
            JCheckBox sol = new JCheckBox("Verify against solution");
            sol.setHorizontalAlignment(JLabel.LEFT);
            
            
            oneBut.addActionListener(this);
            twoBut.addActionListener(this);
            ThreeBut.addActionListener(this);
            FourBut.addActionListener(this);
            FiveBut.addActionListener(this);
            SixBut.addActionListener(this);
            SevenBut.addActionListener(this);
            EightBut.addActionListener(this);
            NineBut.addActionListener(this);
            eraserBut.addActionListener(this);
            solverBut.addActionListener(this);
            undoImageBut.addActionListener(this);
            sol.addActionListener(this);
            
            add(oneBut, gbCon);
            gbCon.gridx++;
            add(twoBut, gbCon);
            gbCon.gridx++;
            add(ThreeBut, gbCon);
            gbCon.gridx++;
            add(FourBut, gbCon);
            gbCon.gridx++;
            add(FiveBut, gbCon);      //place the bottons in the grid
            gbCon.gridx++;
            add(SixBut, gbCon);
            gbCon.gridx++;
            add(SevenBut, gbCon);
            gbCon.gridx++;
            add( EightBut, gbCon );
            
            gbCon.gridx = 1;
            gbCon.gridy++;

            add(NineBut, gbCon);
            gbCon.gridx++;

            add(eraserBut, gbCon);
            gbCon.gridx++;
            add(undoImageBut, gbCon);
            gbCon.gridx++;
            gbCon.gridwidth = 3;
            add(sol, gbCon);
            
            gbCon.gridx++;
            gbCon.gridx++;
            gbCon.gridx++;
            gbCon.gridx++;

            gbCon.gridwidth = 1;
            add(solverBut, gbCon);

        }

	@Override
	public void actionPerformed(ActionEvent evt) {
		

		String buttonPressed = evt.getActionCommand();
		Object buttonPressed2 = evt.getSource();
		
		switch( buttonPressed ) {
		
			case "1":
				//System.out.println("hi");
				SubBoard.findEquals("1");
				SubBoard.refreshTable(buttonPressed);
				
				break;
			
			case "2":
				SubBoard.findEquals("2");
				SubBoard.refreshTable(buttonPressed);
				
				break;
			
			case "3":
				SubBoard.findEquals("3");
				SubBoard.refreshTable(buttonPressed);
				
				break;
				
			case "4":
				SubBoard.findEquals("4");
				SubBoard.refreshTable(buttonPressed);
				
				break;
				
			case "5":
				SubBoard.findEquals("5");
				SubBoard.refreshTable(buttonPressed);
				
				break;
	
			case "6":
				SubBoard.findEquals("6");
				SubBoard.refreshTable(buttonPressed);
				
				
				break;
				
			case "7":
				SubBoard.findEquals("7");
				SubBoard.refreshTable(buttonPressed);
				
				
				break;
				
			case "8":
				SubBoard.findEquals("8");
				SubBoard.refreshTable(buttonPressed);
				
				break;
			
			case "9":
				SubBoard.findEquals("9");
				SubBoard.refreshTable(buttonPressed);
				
				break;
				
			case "Verify against solution":
				SubBoard.clear("0");
				if (counter==0){
					SubBoard.Verify();
					counter++;
					break;
					}else if (counter == 1){
					SubBoard.clear("0");
					counter=0;
					break;
				}
			break;
		}
		
		
		if( buttonPressed2 == eraserBut ) {
			SubBoard.refreshTable("0");
			SubBoard.clear("0");


		}
		
		else if( buttonPressed2 == solverBut && InterFaceMaker.getSudokuValuesSolve() != null  ) {
			InterFaceMaker.beginSolving();
		
		}
		
		
		
		else if( buttonPressed2 == undoImageBut ) {
			System.out.println("No time to be finished");
		
		}
	}
 }