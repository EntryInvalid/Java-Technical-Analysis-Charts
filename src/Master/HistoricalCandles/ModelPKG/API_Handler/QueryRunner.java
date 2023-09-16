package Master.HistoricalCandles.ModelPKG.API_Handler;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ModelPKG.Model;

import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryRunner implements Master.HistoricalCandles.ModelPKG.API_Handler.Api_Key {

    private final Controller controller;


    public QueryRunner(Controller controller){ //}, Model model){
        super();
        this.controller = controller;

    }


    //Run API call, parse data, and get data as Candlesticks
    public void ApiCall(String ticker, String timeframe, int numCandles){

        //Create an instance of 'AlphaVantageAPI' to create stock data Matrix. Query info = parameter.
        try {
            AlphaVantageAPI api = new AlphaVantageAPI(controller, ticker, timeframe, numCandles);
        }
        catch (Exception e) {
            Logger logger = Logger.getLogger(QueryRunner.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        //forward back to the ControllerPKG to generate the chart
        //model.forwardCandles(Candlesticks);

    }


}
