package Master.HistoricalCandles.ViewPKG;

import java.text.ParseException;

// For the Controller to pass the Date:OHLCV matrix to the ChartView
public interface ChartInterface {
    void sendChartOhlcv(Object[][] Candlesticks, int candleQuantity, String ticker, String timeframe) throws ParseException;
}