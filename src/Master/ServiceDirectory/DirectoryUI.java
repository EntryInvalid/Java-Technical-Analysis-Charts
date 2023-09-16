package Master.ServiceDirectory;

import Master.HistoricalCandles.ControllerPKG.ProgramLauncher;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class DirectoryUI extends JFrame {

    public void Start() {
        setupFrame();
    }

    private void setupFrame() {
        DirectoryPanel directory = new DirectoryPanel();
        setContentPane(directory);
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setResizable(true);
        setTitle("Market Analysis & Algorithmic Trading");
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private class DirectoryPanel extends JPanel {

        private DirectoryPanel() {
            setFocusable(true);
            setOpaque(true);
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),
                    "ServiceDirectory UI", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
            setupPanel();
            setupListeners();
        }

        private JPanel DirectoryPanel;
        JLabel Title1;
        JLabel Title2;
        JLabel Blurb1;
        JLabel Blurb2;
        JLabel Blurb3;
        JLabel Blurb4;
        JButton Button1;
        JButton Button2;
        JButton Button3;
        JButton Button4;
        JButton Button5;
        JButton Button6;

        private void setupPanel() {
            Color White = new Color(255, 255, 255);
            Color Green = new Color(102, 255, 0);
            Color darkGrey = new Color(50, 50, 50);
            Color Black = new Color(0, 0, 0);
            GridLayout grid = new GridLayout(6, 2);
            this.setLayout(grid);
            this.setBackground(darkGrey);

            Title1 = new JLabel();
            Title1.setBackground(darkGrey);
            Title1.setForeground(Green);
            Title1.setHorizontalAlignment(4);
            Title1.setText("Hello and welcome to ");
            this.add(Title1, grid);

            Title2 = new JLabel();
            Title2.setBackground(darkGrey);
            Title2.setForeground(Green);
            Title2.setHorizontalAlignment(2);
            Title2.setText("Market Analysis 360!");
            this.add(Title2, grid);

            Blurb1 = new JLabel();
            Blurb1.setBackground(darkGrey);
            Blurb1.setForeground(White);
            Blurb1.setHorizontalAlignment(4);
            Blurb1.setText("This is a passion project meant to aid in my learning; it may ");
            this.add(Blurb1, grid);

            Blurb2 = new JLabel();
            Blurb2.setBackground(darkGrey);
            Blurb2.setForeground(White);
            Blurb2.setHorizontalAlignment(2);
            Blurb2.setText("have some bugs, but it will be developed until it is all smooth");
            this.add(Blurb2, grid);

            Blurb3 = new JLabel();
            Blurb3.setBackground(darkGrey);
            Blurb3.setForeground(White);
            Blurb3.setHorizontalAlignment(0);
            Blurb3.setText("--------------------");
            this.add(Blurb3, grid);

            Blurb4 = new JLabel();
            Blurb4.setBackground(darkGrey);
            Blurb4.setForeground(White);
            Blurb4.setHorizontalAlignment(0);
            Blurb4.setText("--------------------");
            this.add(Blurb4, grid);

            Button1 = new JButton();
            Button1.setBackground(Black);
            Button1.setEnabled(true);
            Button1.setText("Live Data Stream");
            this.add(Button1, grid);

            Button2 = new JButton();
            Button2.setBackground(Black);
            Button2.setEnabled(true);
            Button2.setText("Historical Charts (TA Oriented)");
            this.add(Button2, grid);

            Button3 = new JButton();
            Button3.setBackground(Black);
            Button3.setEnabled(true);
            Button3.setText("Fundamental Analysis Data");
            this.add(Button3, grid);

            Button4 = new JButton();
            Button4.setBackground(Black);
            Button4.setEnabled(true);
            Button4.setText("Economic Indicators");
            this.add(Button4, grid);

            Button5 = new JButton();
            Button5.setBackground(Black);
            Button5.setEnabled(true);
            Button5.setText("Back Testing");
            this.add(Button5, grid);

            Button6 = new JButton();
            Button6.setBackground(Color.BLACK);
            Button6.setEnabled(true);
            Button6.setText("Algorithmic Trading");
            this.add(Button6, grid);

        }

        private void setupListeners() {

            //Historical Charts Launchers
            ProgramLauncher HistoricAnalysis = new ProgramLauncher();

            String errorTitle = "SERVICE NOT FOUND";
            String errorMessage = "This is service is coming Soon!, For now try out our 'Historic Analysis' Package!";

            // Live Data Stream
            Button1.addActionListener(e -> ErrorMessage.showPopup(errorMessage,errorTitle));

            // Historical Data Analysis
            Button2.addActionListener(e -> {
                DirectoryUI.this.setVisible(false);
                HistoricAnalysis.Launch();
            });

            // Fundamentals Analysis Data
            Button3.addActionListener(e -> ErrorMessage.showPopup(errorMessage,errorTitle));

            // Economic Indicators
            Button4.addActionListener(e -> ErrorMessage.showPopup(errorMessage,errorTitle));

            // Back testing
            Button5.addActionListener(e -> ErrorMessage.showPopup(errorMessage,errorTitle));

            // Algorithmic Trading
            Button6.addActionListener(e -> ErrorMessage.showPopup(errorMessage,errorTitle));
        }

    }

    private class ErrorMessage {
        public static void showPopup(String infoMessage, String titleBar) {

            JOptionPane.showMessageDialog(null, infoMessage, "ERROR: " + titleBar,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}


