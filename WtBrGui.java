/*


        WarThunder BR GUI
        calculates BR in WarThunder using a GUI.

   This Br calculator is based off of the formula:
   
   if [Plane A] < .6 BR of [Plane B] or [Plane C]
   
      
           A + ( B + C ) / 2
           _________________
    BR =         
                   2
   
   if [Plane A] - [Plane B or C] > 2.0
   
    BR = [Plane A]


                     9/7/2015

               -Bezalel
*/

import java.awt.*;      // For Dimensions.
import java.awt.event.*;// Event handling.
import javax.swing.*;   // GUI Components.

public class WtBrGui implements ActionListener
{
   public static void main(String[] args)
   {
      WtBrGui gui = new WtBrGui();    
   }
   
   // onscreen components stored as fields.
   
   private JFrame frame;
   private JLabel brLabel;
//    private JLabel brLabel2;
   private JTextField plane1Field;
   private JTextField plane2Field;
   private JTextField plane3Field;
   private JButton calculateButton;
   
   public WtBrGui()
   {
   // set up components.
   
   plane1Field = new JTextField(5);
   plane2Field = new JTextField(5);
   plane3Field = new JTextField(5);
   brLabel = new JLabel("Type BR of three highest planes in lineup");
//    brLabel2 = new JLabel(" _Bezz_ 2015");
   calculateButton = new JButton("Calculate");
//    calculateButton.setPreferredSize(new Dimension(100, 50));

   
   
   // attach GUI as event listener to Calculate button.
   calculateButton.addActionListener(this);
   
   // layout
   JPanel north = new JPanel(new GridLayout(3, 2));
   north.add(new JLabel("Plane 1: "));
   north.add(plane1Field);
   north.add(new JLabel("Plane 2: "));
   north.add(plane2Field);
   north.add(new JLabel("Plane 3: "));
   north.add(plane3Field);
   
   // overall frame
   frame = new JFrame("War Thunder BR Calculator  _Bezz_ 9/2015");
   frame.setPreferredSize(new Dimension(400, 150));
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setLayout(new BorderLayout());
   frame.add(brLabel, BorderLayout.NORTH);
   frame.add(north, BorderLayout.CENTER);
   frame.add(calculateButton, BorderLayout.SOUTH);
//    frame.setLayout(new BorderLayout());
//    frame.add(brLabel2, BorderLayout.SOUTH);
   frame.pack();
   frame.setVisible(true);   
   }
   
   // Handles clicks on Calculate button by computing the BR.
   public void actionPerformed(ActionEvent event)
   {
      // read plane info from text fields.
      String plane1Text = plane1Field.getText();
      double plane1 = Double.parseDouble(plane1Text);
      String plane2Text = plane2Field.getText();
      double plane2 = Double.parseDouble(plane2Text);
      String plane3Text = plane3Field.getText();
      double plane3 = Double.parseDouble(plane3Text);
      
      // calculate BR and diplay on screen.
      if (((plane1 - plane2) <= 2.0) && 
                     ((plane1 - plane3) <= 2.0))
      {  // check to make sure plane differences are within range.
         double br = (((plane2 + plane3) / 2.0) + plane1) / 2.0;
         
         br = brRound(br);// round and formatting
         brLabel.setText("BR " + br);
      }
      
      else
      {  // if difference between plane1 and plane2 and/or plane3
         // is greater than 2.0
         double br = plane1;
//         br = brRound(br);
         
         brLabel.setText("BR " + br);
      }
   }
   public static double brRound(double br)
   {// round br to the nearest #.0, #.3 or #.7
      br = Math.round(br*10)/10.0d;
      double onesPlaceBR = Math.floor(br) / 1.0;// This takes the
      // ones place of the BR to help with calculation.
      
      // adjust if statements
      // as needed for BR
      if(br - onesPlaceBR < 0.2)
      {
         br = onesPlaceBR;
      }
      
      else if(br - onesPlaceBR < 0.5)
      {
         br = onesPlaceBR + 0.3;
      }
      
      else if(br - onesPlaceBR < 0.8)
      {
         br = onesPlaceBR + 0.7;
      }
      else
      {
         br = onesPlaceBR + 1.0;
      }           
      return br;
   }   
}