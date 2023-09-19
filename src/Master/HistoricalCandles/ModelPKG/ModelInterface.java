package Master.HistoricalCandles.ModelPKG;

// For the Controller to pass the Query input to the Model
public interface ModelInterface {

    void sendModelQueryData(String ticker,String timeframe, int CandleQuantity, String dataSource);

}