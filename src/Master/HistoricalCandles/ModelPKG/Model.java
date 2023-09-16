package Master.HistoricalCandles.ModelPKG;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ModelPKG.API_Handler.QueryRunner;


public class Model implements ModelInterface {

    private final Controller controller;
    //private final Model model;


    public Model(Controller controller) {
        super();
        this.controller = controller;
    }

    // Create a QueryRunner by giving it the Query input. call the method to query the API
    public void newQueryRunner(Controller controller, String ticker, String timeframe, int candleQunatity) {
        QueryRunner queryRunner = new QueryRunner(controller);
        queryRunner.ApiCall(ticker, timeframe, candleQunatity);
    }

    @Override
    public void sendModelQueryData(String ticker, String timeframe, int candleQuanitity) {
        newQueryRunner(controller, ticker, timeframe, candleQuanitity);
    }
}
