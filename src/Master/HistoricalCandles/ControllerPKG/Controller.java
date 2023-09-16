package Master.HistoricalCandles.ControllerPKG;

import Master.HistoricalCandles.ModelPKG.Model;
import Master.HistoricalCandles.ModelPKG.ModelInterface;
import Master.HistoricalCandles.ViewPKG.ChartInterface;
import Master.HistoricalCandles.ViewPKG.ChartView;
import Master.HistoricalCandles.ViewPKG.QueryForm;

import javax.swing.*;
import java.text.ParseException;



public class Controller implements QueryInterface, ModelInterface, OhlcvInterface, ChartInterface {

    private QueryForm query_form;
    private Model model;
    private ChartView chart;
    Object[][] Candlesticks;
    String ticker;
    String timeframe;
    int candleQuantity;

    public Controller() {

        newQueryForm();
    }

    // Creates a new SelectionFrame upon constructor call
    private void newQueryForm() {
        this.query_form = new QueryForm(this);

    }

//     // Creates a new ChartView upon constructor call
//    public void newChart(Object[][] Candlesticks) {
//        SwingUtilities.invokeLater(() -> {
//
//            chart = new ChartView(this);
//
//            try {
//                chart.setCandles(Candlesticks, candleQuantity);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//
//        });
//    }

    @Override
    public void sendControllerQueryData(String ticker, String timeframe, int candleQuantity) {
        this.ticker = ticker;
        this.timeframe = timeframe;
        this.candleQuantity = candleQuantity;

        sendModelQueryData(ticker, timeframe, candleQuantity);
    }

    @Override
    public void sendModelQueryData(String ticker, String timeframe, int candleQuantity) {
        Model model = new Model(this);
        this.model = model;
        model.sendModelQueryData(ticker, timeframe, candleQuantity);
    }

    @Override
    public void sendControllerOhlcv(Object[][] Candlesticks) {
        this.Candlesticks = Candlesticks;
    }

    @Override
    public void sendChartOhlcv(Object[][] Candlesticks, int candleQuantity) throws ParseException {
        ChartView chart = new ChartView(this);
        this.chart = chart;
        chart.sendChartOhlcv(Candlesticks, candleQuantity);
        chart.createChart();
    }
}
