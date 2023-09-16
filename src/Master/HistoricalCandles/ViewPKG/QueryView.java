package Master.HistoricalCandles.ViewPKG;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ControllerPKG.QueryInterface;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class QueryView extends JFrame implements QueryInterface{

    private final Controller controller;
    private final QueryPanel query_panel;
    public String ticker;
    public String time;
    public int candleQuantity;

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
        this.setTitle("Chart Analysis");
        this.setVisible(true);
    }

    // Pass the Query input data to the Controller controller
    @Override
    public void sendControllerQueryData(String ticker, String timeframe, int candleQuantity) {
        controller.sendControllerQueryData(ticker, timeframe, candleQuantity);
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
        JLabel Title1;
        JLabel Title2;
        JLabel TickerLabel;
        JLabel ContractLabel;
        JLabel StartLabel;
        JLabel EndLabel;
        JTextField TickerInput;
        JTextField candleTimeframe;
        JTextField candleQuantityInput;
        JTextField toBeDetermined;
        JButton ExitButton;
        JButton QueryButton;


        // List all components -- using gridLayout so fills top to bottom left to right
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
                    "QueryView", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

            // Program Title
            Title1 = new JLabel();
            Title1.setBackground(darkGrey);
            Title1.setForeground(Green);
            Title1.setHorizontalAlignment(4);
            Title1.setText("Welcome to TA-Charts! ");
            this.add(Title1, grid);


            // Program Title continued
            Title2 = new JLabel();
            Title2.setBackground(darkGrey);
            Title2.setForeground(Green);
            Title2.setHorizontalAlignment(2);
            Title2.setText("Enter QueryView to get started:");
            this.add(Title2, grid);

            // First textField Label
            TickerLabel = new JLabel();
            TickerLabel.setBackground(darkGrey);
            TickerLabel.setForeground(White);
            TickerLabel.setHorizontalAlignment(0);
            TickerLabel.setText("Ticker Symbol (AAPL, TSLA, META, etc)");
            this.add(TickerLabel, grid);

            // First textField
            TickerInput = new JTextField();
            TickerInput.setBackground(lightGrey);
            TickerInput.setEditable(true);
            TickerInput.setForeground(veryLightGrey);
            TickerInput.setHorizontalAlignment(0);
            TickerInput.setText("AAPL");
            TickerInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder()));
            this.add(TickerInput, grid);

            // Second textField Label
            ContractLabel = new JLabel();
            ContractLabel.setBackground(darkGrey);
            ContractLabel.setForeground(White);
            ContractLabel.setHorizontalAlignment(0);
            ContractLabel.setText("Timeframe (Choose from: 5min, 15min, 30min, 60min, 1d, 1w)");
            this.add(ContractLabel, grid);

            // Second textField
            candleTimeframe = new JTextField();
            candleTimeframe.setBackground(lightGrey);
            candleTimeframe.setEditable(true);
            candleTimeframe.setForeground(veryLightGrey);
            candleTimeframe.setHorizontalAlignment(0);
            candleTimeframe.setText("day");
            candleTimeframe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder()));
            this.add(candleTimeframe, grid);

            // Third textField Label
            StartLabel = new JLabel();
            StartLabel.setBackground(darkGrey);
            StartLabel.setForeground(White);
            StartLabel.setHorizontalAlignment(0);
            StartLabel.setText("How many candles should be populated?");
            this.add(StartLabel, grid);

            // Third textField Label
            candleQuantityInput = new JTextField();
            candleQuantityInput.setBackground(lightGrey);
            candleQuantityInput.setEditable(true);
            candleQuantityInput.setForeground(veryLightGrey);
            candleQuantityInput.setHorizontalAlignment(0);
            candleQuantityInput.setText("100");
            candleQuantityInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder()));
            this.add(candleQuantityInput, grid);

            // Fourth textField Label
            EndLabel = new JLabel();
            EndLabel.setBackground(darkGrey);
            EndLabel.setForeground(White);
            EndLabel.setHorizontalAlignment(0);
            EndLabel.setText("Testing");
            this.add(EndLabel, grid);

            // Fourth textField
            toBeDetermined = new JTextField();
            toBeDetermined.setBackground(lightGrey);
            toBeDetermined.setEditable(true);
            toBeDetermined.setForeground(veryLightGrey);
            toBeDetermined.setHorizontalAlignment(0);
            toBeDetermined.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder()));
            this.add(toBeDetermined, grid);

            // Exit Button
            ExitButton = new JButton();
            ExitButton.setBackground(Black);
            ExitButton.setEnabled(true);
            ExitButton.setText("Exit QueryView Page");
            this.add(ExitButton, grid);

            // Enter/Query Button
            QueryButton = new JButton();
            QueryButton.setBackground(Color.BLACK);
            QueryButton.setEnabled(true);
            QueryButton.setText("QueryView");
            this.add(QueryButton, grid);

        }

        private void setupListeners() {

            // read all input values when the 'Query' button is clicked
            QueryButton.addActionListener(e -> {

                System.out.println("Ticker: " + TickerInput.getText());
                System.out.println("Timeframe: " + candleTimeframe.getText() + " per Candle");
                System.out.println("Data-Set Size: " + candleQuantityInput.getText() + " Candles");
                System.out.println("Testing " + toBeDetermined.getText());

                // copy values of first three since fourth field is not currently used
                ticker = String.valueOf(TickerInput.getText());
                time = String.valueOf(candleTimeframe.getText());
                candleQuantity = Integer.parseInt(candleQuantityInput.getText());

                sendControllerQueryData(ticker, time, candleQuantity);
            });

            // Close the Program when the Exit Button is clicked
            ExitButton.addActionListener(e -> System.exit(0));
        }

    }
}