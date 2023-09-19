package Master.HistoricalCandles.ViewPKG;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ViewPKG.ChartComponents.CustomCandlestickRenderer;
import Master.HistoricalCandles.ViewPKG.ChartComponents.CustomDateAxis;
import Master.HistoricalCandles.ViewPKG.ChartComponents.CustomMenuBar;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLabelLocation;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultHighLowDataset;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ChartView extends JFrame implements ChartInterface {
    private final Controller controller;
    private Object[][] Candlesticks;
    private String ticker;
    private String timeframe;
    private int candleQuantity;
    private Date[] Dates;
    private double[] Highs = new double[candleQuantity];
    private double[] Lows = new double[candleQuantity];
    private double[] Opens = new double[candleQuantity];
    private double[] Closes = new double[candleQuantity];
    private double[] Volumes = new double[candleQuantity];

    public ChartView(Controller controller) {
        super();
        this.controller = controller;
    }

    // Receive Candlesticks and compose ChartComponents
    @Override
    public void sendChartOhlcv(Object[][] Candlesticks, int candleQuantity, String ticker, String timeframe) {

        // Copy values
        this.candleQuantity = candleQuantity;
        this.ticker = ticker;
        this.timeframe = timeframe;
        this.Candlesticks = Candlesticks;

        // Initialization before
        this.Dates = new Date[candleQuantity];
        this.Highs = new double[candleQuantity];
        this.Lows = new double[candleQuantity];
        this.Opens = new double[candleQuantity];
        this.Closes = new double[candleQuantity];
        this.Volumes = new double[candleQuantity];

        SwingUtilities.invokeLater(() -> {
            // Format the Data
            try {this.formatData();
            }
            catch (ParseException e) {throw new RuntimeException(e);
            }

            // Create ChartComponents
            try {this.createChart();
            }
            catch (ParseException e) {throw new RuntimeException(e);
            }

        });
    }

    // Format data from matrix/2D-Array to the proper type from Object
    public void formatData() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < candleQuantity; i++) {
            this.Dates[i] = dateFormat.parse((String) Candlesticks[i][0]);
            this.Opens[i] = Double.parseDouble((String) Candlesticks[i][1]);
            this.Highs[i] = Double.parseDouble((String) Candlesticks[i][2]);
            this.Lows[i] = Double.parseDouble((String) Candlesticks[i][3]);
            this.Closes[i] = Double.parseDouble((String) Candlesticks[i][4]);
            this.Volumes[i] = Double.parseDouble((String) Candlesticks[i][5]);
        }
    }

    public void createChart() throws ParseException {

        // ChartComponents colors
        Color blackPaint = new Color(0, 0, 0);
        Color blueGreyPaint = new Color(150, 175, 200);
        Color gridColor = new Color(33, 33, 122);

        // Create datasets
        DefaultHighLowDataset ohlcDataset = new DefaultHighLowDataset("OHLC", Dates, Highs, Lows, Opens, Closes, Volumes);

        // Create CustomDateAxis that skips empty datasets
        CustomDateAxis xAxis = new CustomDateAxis(this, Dates, this.timeframe);

        // Create Y axis for price
        NumberAxis yAxis = new NumberAxis("Price of Security or Cryptocurrency");
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setVerticalTickLabels(false);
        yAxis.setLabelLocation(AxisLabelLocation.MIDDLE);

        // Create candlestickPlot and customCandlestickRenderer
        CustomCandlestickRenderer customCandlestickRenderer = new CustomCandlestickRenderer(){};
        XYPlot candlestickPlot = new XYPlot(ohlcDataset, xAxis, yAxis, customCandlestickRenderer);
        candlestickPlot.setBackgroundPaint(blackPaint);
        candlestickPlot.setDomainGridlinePaint(gridColor);
        candlestickPlot.setRangeGridlinePaint(gridColor);

         // Create volume dataset
        TimeSeries volumeTimeSeries = new TimeSeries("Volume");
        for (int i = 0; i < candleQuantity; i++) {
            volumeTimeSeries.add(new Minute(Dates[i]), Volumes[i]); // Using Day to represent the date
        }
        TimeSeriesCollection volumeDataset = new TimeSeriesCollection();
        volumeDataset.addSeries(volumeTimeSeries);

        // Create Separate Logarithmic Volume chart
        LogAxis volumeLogAxis = new LogAxis("Logarithmic Volume");
        XYPlot volumeLogPlot = new XYPlot(volumeDataset, xAxis, volumeLogAxis,new XYAreaRenderer());
        volumeLogPlot.setBackgroundPaint(blackPaint);
        volumeLogPlot.setDomainGridlinePaint(gridColor);
        volumeLogPlot.setRangeGridlinePaint(gridColor);

        // Combine all plots
        CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot(xAxis); // Using the same xAxis for both subplots
        mainPlot.add(candlestickPlot, 4);
        mainPlot.add(volumeLogPlot, 1);

        // Create the main chart
        JFreeChart chart = new JFreeChart((ticker + " " + timeframe), JFreeChart.DEFAULT_TITLE_FONT, mainPlot, false);
        chart.setBackgroundPaint(blueGreyPaint);

        // Set the layout and add to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1500, 1000));
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setMouseZoomable(true);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        // JFrame settings
        pack();
        JMenuBar menuBar = CustomMenuBar.createMenuBar();
        this.setJMenuBar(menuBar);
        this.setTitle("TA.Charts Prototype");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}