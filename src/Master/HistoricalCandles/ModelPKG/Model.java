package Master.HistoricalCandles.ModelPKG;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ModelPKG.API_Handler.QueryRunner;


public class Model implements ModelInterface {

    private final Controller controller;

    public Model(Controller controller) {
        super();
        this.controller = controller;
    }

    public Model getNewModel(Controller controller){
        Model model = new Model(controller);
        return model;
    }

    // Create a QueryRunner by giving it the Query input. call the method to query the API
    public void newQueryRunner(String ticker, String timeframe, int candleQunatity) {
        QueryRunner query = new QueryRunner(this);
        query.ApiCall(ticker, timeframe, candleQunatity);
    }

    //receive candlestick data and forward it back to the ControllerPKG
    public void forwardCandles(Object[][] Candlesticks) {
        controller.newVisualization(Candlesticks);
    }

    @Override
    public void sendModelQueryData(String ticker, String timeframe, int candleQuanitity) {
        newQueryRunner(ticker, timeframe, candleQuanitity);
    }
}
