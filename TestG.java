import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestG extends JFrame {
    JLabel Status;
    JTextField timein, timeout, totalhours, overtime, tardiness;
    JButton calculateButton, clear;

    public TestG() {
    	setTitle("Time Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        timein = new JTextField(10);
        timeout = new JTextField(10);
        totalhours = new JTextField(10);
        totalhours.setEditable(false);
        overtime = new JTextField(10);
        overtime.setEditable(false);
        tardiness = new JTextField(10);
        tardiness.setEditable(false);
        Status = new JLabel("");
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new calculateButtonListener());
        clear = new JButton("Clear");
        clear.addActionListener(new ClearButtonListener());
        
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
        contentPane.add(Status);
        contentPane.add(calculateButton);
        contentPane.add(clear);
        setContentPane(contentPane);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class calculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                String timeInStr = timein.getText();
                String timeOutStr = timeout.getText();

                int timeIn = convertToMinutes(timeInStr);
                int timeOut = convertToMinutes(timeOutStr);
                int totalRenderedTime = timeOut - timeIn;
                totalhours.setText("Overtime: " + totalRenderedTime / 60 + " hours " + totalRenderedTime % 60 + " minutes");

                if (totalRenderedTime > 0) {
                	double overtimehours = totalRenderedTime;
                    if (totalRenderedTime > 540) {
                        overtimehours = totalRenderedTime - 540;
                        overtime.setText("Overtime: " + overtimehours / 60 + " hours " + overtimehours % 60 + " minutes");
                        
                    } else {
                        totalhours.setText(totalRenderedTime / 60 + " hours " + totalRenderedTime % 60 + " minutes");
                    }
                } else {
                    totalhours.setText("Invalid Input: Time out should be later than time in.");
                }
            } catch (Exception ex) {
                totalhours.setText("Invalid Input: Please input time in  and time out in 24 hour format.");
                timein.setText("");
                timeout.setText("");
                totalhours.setText("");
                overtime.setText("");
                Status.setText("");
            }
        }
    }
    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timein.setText("");
            timeout.setText("");
            totalhours.setText("");
            overtime.setText("");
            Status.setText("");
        }
    }

    
    private int convertToMinutes(String timeStr) throws Exception {
        String[] timeParts = timeStr.split(":");
        if (timeParts.length != 2) {
            throw new Exception();
        }
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new Exception();
        }
        return hours * 60 + minutes;
    }

    public static void main(String[] args) {
        new TestG();
    }
}
