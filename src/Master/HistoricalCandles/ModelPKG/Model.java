package Master.HistoricalCandles.ModelPKG;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ModelPKG.API_Handler.QueryRunner;


public class Model implements ModelInterface {

    private final Controller controller;

    public Model(Controller controller) {
        super();
        this.controller = controller;
    }

    // Create a QueryRunner then call the QueryRunner's method to query the API with Query input as parameters
    public void newQueryRunner(Controller controller, String ticker, String timeframe, int candleQuantity, String dataSource) {
        QueryRunner queryRunner = new QueryRunner(controller);
        queryRunner.ApiCall(ticker, timeframe, candleQuantity, dataSource);
    }

    // Receive the Query data and pass it into newQueryRunner method as parameters
    @Override
    public void sendModelQueryData(String ticker, String timeframe, int candleQuantity, String dataSource) {
        newQueryRunner(controller, ticker, timeframe, candleQuantity, dataSource);
    }
}