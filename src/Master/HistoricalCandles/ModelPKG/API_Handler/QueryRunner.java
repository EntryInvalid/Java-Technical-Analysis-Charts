package Master.HistoricalCandles.ModelPKG.API_Handler;

import Master.HistoricalCandles.ModelPKG.Model;

import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryRunner implements Master.HistoricalCandles.ModelPKG.API_Handler.Api_Key {

    private final Model model;
    public Object[][] Candlesticks;

    public QueryRunner(Model model){
        super();
        this.model = model;
    }


    //Run API call, parse data, and get data as Candlesticks
    public void ApiCall(String ticker, String timeframe, int numCandles){

        //Create an instance of 'AlphaVantageAPI' to create stock data Matrix. Query info = parameter.
        try {
            AlphaVantageAPI api = new AlphaVantageAPI(ticker, timeframe, numCandles);
            Candlesticks = api.Candlesticks;
        }
        catch (Exception e) {
            Logger logger = Logger.getLogger(QueryRunner.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        //forward back to the ControllerPKG to generate the chart
        model.forwardCandles(Candlesticks);

    }


}
