package Master.HistoricalCandles.ControllerPKG;

import Master.HistoricalCandles.ModelPKG.Model;
import Master.HistoricalCandles.ModelPKG.ModelInterface;
import Master.HistoricalCandles.ViewPKG.ChartView;
import Master.HistoricalCandles.ViewPKG.QueryForm;

import javax.swing.*;
import java.text.ParseException;



public class Controller implements QueryInterface, ModelInterface {

    private QueryForm query_form;
    private Model model;
    String ticker;
    String timeframe;
    int candleQuantity;

    public Controller() {
        newQueryForm();
    }
    private ChartView Visualization;

    // Creates a new SelectionFrame upon constructor call
    private void newQueryForm() {
        this.query_form = new QueryForm(this);
    }


    // Creates a new ChartView upon constructor call
    public void newVisualization(Object[][] Candlesticks) {
        SwingUtilities.invokeLater(() -> {

            Visualization = new ChartView(this);

            try {
                Visualization.setCandles(Candlesticks, candleQuantity);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        });
    }

    @Override
    public void sendControllerQueryData(String ticker, String timeframe, int candleQuantity) {
        this.ticker = ticker;
        this.timeframe = timeframe;
        this.candleQuantity = candleQuantity;

        sendModelQueryData(ticker, timeframe, candleQuantity);
    }

    @Override
    public void sendModelQueryData(String ticker, String timeframe, int candleQuantity) {
        this.model = model.getNewModel(this);
        model.sendModelQueryData(ticker, timeframe, candleQuantity);
    }
}
