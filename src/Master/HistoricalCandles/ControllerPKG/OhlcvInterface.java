package Master.HistoricalCandles.ControllerPKG;

// For the AlphaVantageApi to send the Date:OHLCV matrix to the Controller
public interface OhlcvInterface {
    void sendControllerOhlcv(Object[][] Candlesticks);
}
