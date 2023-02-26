import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestG extends JFrame {
    JTextField timein, timeout, totalhours, overtime, tardiness;
    JLabel statusLabel;
    
    public TestG() {
        super("Time Log"); 
        timein = new JTextField(10);
        timeout = new JTextField(10);
        totalhours = new JTextField(10);
        totalhours.setEditable(false);
        overtime = new JTextField(10);
        overtime.setEditable(false);
        tardiness = new JTextField(10);
        tardiness.setEditable(false);
        statusLabel = new JLabel("");
        
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
       
        JPanel contentPane = new JPanel(new GridLayout(7, 15, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(new JLabel("Time In: "));
        contentPane.add(timein);
        contentPane.add(new JLabel("Time Out: "));
        contentPane.add(timeout);
        contentPane.add(new JLabel("Total Hours: "));
        contentPane.add(totalhours);
        contentPane.add(new JLabel("Overtime Hours: "));
        contentPane.add(overtime);
        contentPane.add(new JLabel("Tardiness: "));
        contentPane.add(tardiness);
        contentPane.add(new JLabel("Status: "));
        contentPane.add(statusLabel);
        
        contentPane.add(calculateButton);
        contentPane.add(clearButton);
        setContentPane(contentPane);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
  
                String inTimeString = timein.getText();
                String outTimeString = timeout.getText();
                int inTime = Integer.parseInt(inTimeString.replaceAll(":", ""));
                int outTime = Integer.parseInt(outTimeString.replaceAll(":", ""));
                
                int totalMinutes = (outTime % 100 + 60 * (outTime / 100)) - (inTime % 100 + 60 * (inTime / 100));
                double totalHours = (double) totalMinutes / 60.0;

                totalhours.setText(String.format("%.2f", totalHours));

                if (totalHours > 9) {
                	double overtimeHours = totalHours - 8.0;
                    overtimeHours = Math.round(overtimeHours * 100.0) / 100.0;
                    overtime.setText(String.valueOf(overtimeHours));
                    tardiness.setText("0.00");
                    statusLabel.setText("Overtime");
                } else if (totalHours == 9) {
                    overtime.setText("0.00");
                    tardiness.setText("0.00");
                    statusLabel.setText("On Time");
                } else if (totalHours < 9 ) {
                overtime.setText("0.00");
                double result = 9 - totalHours;
                tardiness.setText(String.valueOf(result));
                statusLabel.setText("Late");
                } else {
                throw new IllegalArgumentException();
                }
                } catch (Exception ex) {
                
              
                JOptionPane.showMessageDialog(TestG.this, "Invalid input. Please enter time in the format HH:MM AM/PM.");
                timein.setText("");
                timeout.setText("");
                totalhours.setText("");
                overtime.setText("");
                tardiness.setText("");
                statusLabel.setText("");
      }
        }
    }
    
    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timein.setText("");
            timeout.setText("");
            totalhours.setText("");
            overtime.setText("");
            tardiness.setText("");
            statusLabel.setText("");
        }
    }
    
    public static void main(String[] args) {
        new TestG();
    }
}
