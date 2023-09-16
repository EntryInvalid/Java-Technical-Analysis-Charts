package Master.HistoricalCandles.ViewPKG;

import java.text.ParseException;

public interface ChartInterface {
    void sendChartOhlcv(Object[][] Candlesticks, int candleQuantity) throws ParseException;
}

