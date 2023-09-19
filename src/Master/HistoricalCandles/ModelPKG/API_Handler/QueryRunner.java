package Master.HistoricalCandles.ModelPKG.API_Handler;

import Master.HistoricalCandles.ControllerPKG.Controller;

import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryRunner implements Master.HistoricalCandles.ModelPKG.API_Handler.Api_Key {

    private final Controller controller;
    private AlphaVantageAPI Api;

    public QueryRunner(Controller controller){
        super();
        this.controller = controller;

    }

    // Run API call, parse data, and get data as Candlesticks
    public void ApiCall(String ticker, String timeframe, int candleQuanitity, String dataSource){

        //Create an instance of 'AlphaVantageAPI' to create stock data Date:OHLCV Matrix
        try {
            Api = new AlphaVantageAPI(controller, ticker, timeframe, candleQuanitity, dataSource);
        }
        catch (Exception e) {
            Logger logger = Logger.getLogger(QueryRunner.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

}