package Master.HistoricalCandles.ViewPKG;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ControllerPKG.QueryInterface;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class QueryView extends JFrame implements QueryInterface{

    private final Controller controller;
    private final QueryPanel query_panel;
    private String ticker;
    private String timeframe;
    private String dataSource;
    private int candleQuantity;

    public QueryView(Controller controller) {
        super();
        this.controller = controller;
        this.query_panel = new QueryPanel(controller);
        setupFrame();
    }

    // Frame configurations
    private void setupFrame() {
        this.setContentPane(query_panel);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setTitle("TA.Charts Prototype");
        this.setVisible(true);
    }

    // Pass the Query input data to the Controller
    @Override
    public void sendControllerQueryData(String ticker, String timeframe, int candleQuantity, String dataSource) {
        controller.sendControllerQueryData(ticker, timeframe, candleQuantity, dataSource);
    }

    private class QueryPanel extends JPanel {

        private final Controller controller;

        // Create and Setup QueryPanel
        private QueryPanel(Controller controller) {
            super();
            this.controller = controller;
            QueryPanel= new JPanel();
            setupPanel();
            setupListeners();
        }

        private final JPanel QueryPanel;
        JLabel title1;
        JLabel title2;
        JLabel tickerLabel;
        JLabel timeframeLabel;
        JLabel startLabel;
        JLabel endLabel;
        JTextField tickerInput;
        JComboBox<String> timeframeInput;
        JTextField candleQuantityInput;
        JComboBox<String> dataSourceInput;
        JButton exitButton;
        JButton queryButton;


        // List all components
        private void setupPanel() {

            // Panel Configurations
            Color White = new Color(255, 255, 255); 
            Color Green = new Color(102, 255, 0); 
            Color darkGrey = new Color(50, 50, 50); 
            Color lightGrey = new Color(100, 100, 100); 
            Color veryLightGrey = new Color(150, 150, 150); 
            Color Black = new Color(0, 0, 0); 
            this.setBackground(darkGrey); 
            GridLayout grid = new GridLayout(6, 2);
            this.setLayout(grid);
            this.setFocusable(true);
            this.setOpaque(true);
            this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),
                    "Historical Analysis", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

            // Program Title
            title1 = new JLabel();
            title1.setBackground(darkGrey); 
            title1.setForeground(Green); 
            title1.setHorizontalAlignment(4);
            title1.setText("Welcome to TA.Charts! ");
            this.add(title1, grid);

            // Program Title continued
            title2 = new JLabel();
            title2.setBackground(darkGrey); 
            title2.setForeground(Green); 
            title2.setHorizontalAlignment(2);
            title2.setText("Enter your Query below to get started:");
            this.add(title2, grid);

            // First Option Label - TextField
            tickerLabel = new JLabel();
            tickerLabel.setBackground(darkGrey); 
            tickerLabel.setForeground(White); 
            tickerLabel.setHorizontalAlignment(0);
            tickerLabel.setText("Choose Ticker Symbol (Stock, Crypto, or Forex)");
            this.add(tickerLabel, grid);

            // First Option - TextField
            tickerInput = new JTextField();
            tickerInput.setBackground(lightGrey); 
            tickerInput.setEditable(true);
            tickerInput.setForeground(veryLightGrey); 
            tickerInput.setHorizontalAlignment(0);
            tickerInput.setText("AAPL");
            tickerInput.addFocusListener(new FocusListener() { // Placeholder Text Handler
                @Override
                public void focusGained(FocusEvent e) {
                    if (tickerInput.getText().equals("AAPL")) {
                        tickerInput.setText(""); // Clear the placeholder text when focused
                        tickerInput.setForeground(Color.CYAN); 
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (tickerInput.getText().isEmpty()) {
                        tickerInput.setText("AAPL"); // Restore placeholder if empty
                        tickerInput.setForeground(Color.CYAN); 
                    }
                }
            });
            tickerInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder()));
            this.add(tickerInput, grid);

            // Second Option Label - TextField
            timeframeLabel = new JLabel();
            timeframeLabel.setBackground(darkGrey); 
            timeframeLabel.setForeground(White); 
            timeframeLabel.setHorizontalAlignment(0);
            timeframeLabel.setText("Choose Timeframe : ");
            this.add(timeframeLabel, grid);

            // Second Option - JComboBox
            String[] timeframeOptions =
                    {"1min", "5min", "15min", "30min", "60min", "Daily", "Weekly"};
            timeframeInput = new JComboBox<>(timeframeOptions);
            timeframeInput.setBackground(Color.CYAN); 
            timeframeInput.setForeground(Color.BLACK); 
            timeframeInput.setSelectedItem("Daily");
            this.add(timeframeInput, grid);

            // Third Option Label - TextField
            startLabel = new JLabel();
            startLabel.setBackground(darkGrey); 
            startLabel.setForeground(White); 
            startLabel.setHorizontalAlignment(0);
            startLabel.setText("Choose how many candles should be populated : ");
            this.add(startLabel, grid);

            // Third Option - TextField
            candleQuantityInput = new JTextField();
            candleQuantityInput.setBackground(lightGrey); 
            candleQuantityInput.setEditable(true);
            candleQuantityInput.setForeground(veryLightGrey); 
            candleQuantityInput.setHorizontalAlignment(0);
            candleQuantityInput.setText("100");
            candleQuantityInput.addFocusListener(new FocusListener() { // Placeholder Text Handler
                @Override
                public void focusGained(FocusEvent e) {
                    if (candleQuantityInput.getText().equals("100")) {
                        candleQuantityInput.setText(""); // Clear the placeholder text when focused
                        candleQuantityInput.setForeground(Color.CYAN); 
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (candleQuantityInput.getText().isEmpty()) {
                        candleQuantityInput.setText("100"); // Restore placeholder if empty
                        candleQuantityInput.setForeground(Color.CYAN); 
                    }
                }
            });
            candleQuantityInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder()));
            this.add(candleQuantityInput, grid);

            // Fourth Option Label - Radio Button
            endLabel = new JLabel();
            endLabel.setBackground(darkGrey); 
            endLabel.setForeground(White); 
            endLabel.setHorizontalAlignment(0);
            endLabel.setText("Choose Data Source : ");
            this.add(endLabel, grid);

            // Fourth Option - JComboBox
            String[] dataOptions = {"AlphaVantage API", "sampleData_1min.csv", "sampleData_5min.csv", "sampleData_15min.csv", "sampleData_30min.csv",
            "sampleData_60min.csv", "sampleData_day.csv", "sampleData_week.csv"};
            dataSourceInput = new JComboBox<>(dataOptions);
            dataSourceInput.setBackground(Color.CYAN); 
            dataSourceInput.setForeground(Color.BLACK); 
            dataSourceInput.setSelectedItem("sampleData_day.csv");
            this.add(dataSourceInput, grid);

            // Exit Button
            exitButton = new JButton();
            exitButton.setBackground(Black); 
            exitButton.setEnabled(true);
            exitButton.setText("Exit Program");
            this.add(exitButton, grid);

            // Enter/Query Button
            queryButton = new JButton();
            queryButton.setBackground(Color.BLACK); 
            queryButton.setEnabled(true);
            queryButton.setText("Load ChartComponents");
            this.add(queryButton, grid);
        }

        private void setupListeners() {

            // Read all input values when the Query button is clicked
            queryButton.addActionListener(e -> {

                System.out.println(""); // For console View
                System.out.println("----------------------------------------------------------------------------------------------------"); // For console view
                System.out.println("Ticker: " + tickerInput.getText());
                System.out.println("Timeframe: " + timeframeInput.getSelectedItem());
                System.out.println("Data-Set Size: " + candleQuantityInput.getText() + " Candles");
                System.out.println("Data-Source: " + dataSourceInput.getSelectedItem());
                System.out.println("----------------------------------------------------------------------------------------------------"); // For console view

                // Copy values of first three since fourth field is not currently used
                ticker = String.valueOf(tickerInput.getText());
                timeframe = String.valueOf(timeframeInput.getSelectedItem());
                candleQuantity = Integer.parseInt(candleQuantityInput.getText());
                dataSource = String.valueOf(dataSourceInput.getSelectedItem());

                sendControllerQueryData(ticker, timeframe, candleQuantity, dataSource);
            });

            // Close the Program when the Exit Button is clicked
            exitButton.addActionListener(e -> System.exit(0));
        }

    }
}
