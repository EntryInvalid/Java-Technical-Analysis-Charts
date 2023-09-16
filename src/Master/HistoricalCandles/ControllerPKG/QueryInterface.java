package Master.HistoricalCandles.ControllerPKG;

// For the QueryView in ViewPKG to send the Query Input to the Controller
public interface QueryInterface {
    void sendControllerQueryData(String ticker, String timeframe, int candleQuantity);
}