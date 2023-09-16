package Master.HistoricalCandles.ViewPKG;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ViewPKG.Chart.CustomCandlestickRenderer;
import Master.HistoricalCandles.ViewPKG.Chart.CustomDateAxis;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.data.time.Day;
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
    private int candleQuantity;

    private Date[] Dates; // = new Date[candleQuantity];
    private double[] Highs = new double[candleQuantity];
    private double[] Lows = new double[candleQuantity];
    private double[] Opens = new double[candleQuantity];
    private double[] Closes = new double[candleQuantity];
    private double[] Volumes = new double[candleQuantity];

    public ChartView(Controller controller) {
        super();
        this.controller = controller;
    }

    // Receive Candlesticks and compose Chart
    @Override
    public void sendChartOhlcv(Object[][] Candlesticks, int candleQuantity) {
        SwingUtilities.invokeLater(() -> {

            // Copy values
            this.candleQuantity = candleQuantity;
            this.Candlesticks = Candlesticks;

            this.Dates = new Date[candleQuantity];
            this.Highs = new double[candleQuantity];
            this.Lows = new double[candleQuantity];
            this.Opens = new double[candleQuantity];
            this.Closes = new double[candleQuantity];
            this.Volumes = new double[candleQuantity];

            System.out.println("Testing  -- Chart has received Candlestick data from Controller");


            // Format the Data
            try {
                formatData();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            // Create Chart
            try {
                createChart();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        });
    }

    // format data from matrix/2D-Array to the proper type from Object
    public void formatData() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < candleQuantity; i++) {
            this.Dates[i] = dateFormat.parse((String) Candlesticks[i][0]);
            this.Opens[i] = Double.parseDouble((String) Candlesticks[i][1]);
            this.Highs[i] = Double.parseDouble((String) Candlesticks[i][2]);
            this.Lows[i] = Double.parseDouble((String) Candlesticks[i][3]);
            this.Closes[i] = Double.parseDouble((String) Candlesticks[i][4]);
            Volumes[i] = Double.parseDouble((String) Candlesticks[i][5]);
        }
    }

    public void createChart() throws ParseException {

        // chart colors
        //Color chartColor = new Color(87, 87, 87);  //-find where to apply and configure colors
        Color chartOutlineColor = new Color(119,136,150);

        // Create datasets
        DefaultHighLowDataset ohlcDataset = new DefaultHighLowDataset("OHLC", Dates, Highs, Lows, Opens, Closes, Volumes);

        // Create Custom DateAxis that skips empty datasets (need to edit to run off of long Objects instead of Date in
        // order to be dynamically used with different timeframe candlestick charts
        CustomDateAxis xAxis = new CustomDateAxis(this, Dates);

        // Create Y axis for price
        NumberAxis yAxis = new NumberAxis("Price");
        yAxis.setAutoRangeIncludesZero(false);

        // Create CandlestickRenderer and candlestickPlot
        CustomCandlestickRenderer customCandlestickRenderer = new CustomCandlestickRenderer(){};
        XYPlot candlestickPlot = new XYPlot(ohlcDataset, xAxis, yAxis, customCandlestickRenderer);

        // Create volume dataset
        TimeSeries volumeTimeSeries = new TimeSeries("Volume");
        for (int i = 0; i < candleQuantity; i++) {
            volumeTimeSeries.add(new Day(Dates[i]), Volumes[i]); // Using Day to represent the date
        }
        TimeSeriesCollection volumeDataset = new TimeSeriesCollection();
        NumberAxis volumeAxis = new NumberAxis("Volume");
        XYPlot volumePlot = new XYPlot(volumeDataset, xAxis, volumeAxis, new XYAreaRenderer());
        volumeDataset.addSeries(volumeTimeSeries);

        // Separate Logarithmic Volume chart
        LogAxis volumeLogAxis = new LogAxis("Volume");
        XYPlot volumeLogPlot = new XYPlot(volumeDataset, xAxis, volumeLogAxis, new XYAreaRenderer());
        volumePlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);  // use to set volume legend to the right side

        // Combine all plots
        CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot(xAxis); // Using the same xAxis for both subplots
        mainPlot.add(candlestickPlot, 3);
        mainPlot.add(volumeLogPlot, 1);

        // Create the main chart
        JFreeChart chart = new JFreeChart("Financial Chart", JFreeChart.DEFAULT_TITLE_FONT, mainPlot, true);
        chart.setBackgroundPaint(chartOutlineColor);

        // Set the layout and add to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1500, 1000));
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setHorizontalAxisTrace(true);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        //Finalize
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

