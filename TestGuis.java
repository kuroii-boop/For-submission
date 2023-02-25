import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestGuis extends JFrame {
    private JTextField inTimeTextField, outTimeTextField, totalHoursTextField, overtimeTextField, Tardiness;
    private JLabel statusLabel;
    private JButton calculateButton, clearButton;
    
    public TestGuis() {
        setTitle("Time Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        inTimeTextField = new JTextField(10);
        outTimeTextField = new JTextField(10);
        totalHoursTextField = new JTextField(10);
        totalHoursTextField.setEditable(false);
        overtimeTextField = new JTextField(10);
        overtimeTextField.setEditable(false);
        Tardiness = new JTextField(10);
        Tardiness.setEditable(false);
        statusLabel = new JLabel("");
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        
        JPanel contentPane = new JPanel(new GridLayout(7, 15, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(new JLabel("Time In: "));
        contentPane.add(inTimeTextField);
        contentPane.add(new JLabel("Time Out: "));
        contentPane.add(outTimeTextField);
        contentPane.add(new JLabel("Total Hours: "));
        contentPane.add(totalHoursTextField);
        contentPane.add(new JLabel("Overtime Hours: "));
        contentPane.add(overtimeTextField);
        contentPane.add(new JLabel("Tardiness: "));
        contentPane.add(Tardiness);
        contentPane.add(new JLabel("Status: "));
        contentPane.add(statusLabel);
        contentPane.add(calculateButton);
        contentPane.add(clearButton);
        setContentPane(contentPane);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
            try {
            	int inHour, inMinute, outHour, outMinute, totalMinutes;
                boolean inIsAM, outIsAM;
                
                
                String inTimeStr = inTimeTextField.getText();
                String outTimeStr = outTimeTextField.getText();
              
                String[] inTimeParts = inTimeStr.split(":| ");
                inHour = Integer.parseInt(inTimeParts[0]);
                inMinute = Integer.parseInt(inTimeParts[1]);
                inIsAM = inTimeParts[2].equalsIgnoreCase("AM");
                
                String[] outTimeParts = outTimeStr.split(":| ");
                outHour = Integer.parseInt(outTimeParts[0]);
                outMinute = Integer.parseInt(outTimeParts[1]);
                outIsAM = outTimeParts[2].equalsIgnoreCase("AM");
                
                totalMinutes = (outHour + (outIsAM ? 0 : 12) - inHour - (inIsAM ? 0 : 12)) * 60 + outMinute - inMinute;
                
                double totalHours = totalMinutes / 60.0;
                totalHours = Math.round(totalHours * 100.0) / 100.0;
                totalHoursTextField.setText(String.valueOf(totalHours));
                
                if (totalHours > 9) {
                    double overtimeHours = totalHours - 9.0;
                    overtimeHours = Math.round(overtimeHours * 100.0) / 100.0;
                    overtimeTextField.setText(String.valueOf(overtimeHours));
                    Tardiness.setText("0.00");
                    statusLabel.setText("Overtime");
                } else if (totalHours == 9) {
                    overtimeTextField.setText("0.00");
                    Tardiness.setText("0.00");
                    statusLabel.setText("On Time");
                } else if (totalHours < 9 ) {
                overtimeTextField.setText("0.00");
                double result = 9 - totalHours;
                Tardiness.setText(String.valueOf(result));
                statusLabel.setText("Late");
                } else {
                throw new IllegalArgumentException();
                }
                } catch (Exception ex) {
                
              
                JOptionPane.showMessageDialog(TestGuis.this, "Invalid input. Please enter time in the format HH:MM AM/PM.");
                inTimeTextField.setText("");
                outTimeTextField.setText("");
                totalHoursTextField.setText("");
                overtimeTextField.setText("");
                statusLabel.setText("");
      }
   }
}
    
        private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            inTimeTextField.setText("");
            outTimeTextField.setText("");
            totalHoursTextField.setText("");
            overtimeTextField.setText("");
            statusLabel.setText("");
        }
    }

    public static void main(String[] args) {
        new TestGuis();
    }
}

