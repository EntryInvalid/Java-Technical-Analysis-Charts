package Master.HistoricalCandles.ControllerPKG;

import Master.HistoricalCandles.ModelPKG.Model;
import Master.HistoricalCandles.ModelPKG.ModelInterface;
import Master.HistoricalCandles.ViewPKG.ChartInterface;
import Master.HistoricalCandles.ViewPKG.ChartView;
import Master.HistoricalCandles.ViewPKG.QueryView;

public class Controller implements QueryInterface, ModelInterface, OhlcvInterface, ChartInterface {

    private final ProgramLauncher launcher;
    private QueryView query_form;
    private Model model;
    private ChartView chart;
    private Object[][] Candlesticks;
    private String ticker;
    private String timeframe;
    private String dataSource;
    private int candleQuantity;

    public Controller(ProgramLauncher launcher) {
        this.launcher = launcher;
        newQueryForm();
    }

    // Creates a new SelectionFrame upon constructor call
    private void newQueryForm() {
        this.query_form = new QueryView(this);
    }

    // Receives Query data
    @Override
    public void sendControllerQueryData(String ticker, String timeframe, int candleQuantity, String dataSource) {
        this.ticker = ticker;
        this.timeframe = timeframe;
        this.candleQuantity = candleQuantity; // save this value to later send to the ChartView chart
        this.dataSource = dataSource;

        sendModelQueryData(this.ticker, this.timeframe, this.candleQuantity, this.dataSource);
    }

    // Makes the Model 'model' and sends it the Query data
    @Override
    public void sendModelQueryData(String ticker, String timeframe, int candleQuantity, String dataSource) {
        Model model = new Model(this);
        this.model = model;
        model.sendModelQueryData(this.ticker, this.timeframe, this.candleQuantity, dataSource);
    }

    // Receives the Candlestick Dates:OHLCV matrix
    @Override
    public void sendControllerOhlcv(Object[][] Candlesticks) {
        this.Candlesticks = Candlesticks;
        sendChartOhlcv(this.Candlesticks, this.candleQuantity, this.ticker, this.timeframe);
    }

    // Makes the ChartView 'chart', sends it the Dates:OHLCV matrix
    @Override
    public void sendChartOhlcv(Object[][] Candlesticks, int candleQuantity, String ticker, String timeframe) {
        ChartView chart = new ChartView(this);
        this.chart = chart;
        chart.sendChartOhlcv(this.Candlesticks, this.candleQuantity, this.ticker, this.timeframe);
    }

}