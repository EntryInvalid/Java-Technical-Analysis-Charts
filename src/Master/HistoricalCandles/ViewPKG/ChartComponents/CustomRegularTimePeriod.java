package Master.HistoricalCandles.ViewPKG.ChartComponents;

import org.jfree.data.time.*;

import java.util.Date;

public class CustomRegularTimePeriod {


    public static TimeSeries getTimePeriod(Date[] Dates, double[] Volumes, String timeframe, int candleQuantity) {

        TimeSeries volumeTimeSeries = new TimeSeries("Volume");

        for (int i = 0; i < candleQuantity; i++) {

            RegularTimePeriod timePeriod = null;

            switch (timeframe) {
                case "week" -> timePeriod = new Week(Dates[i]);
                case "day" -> timePeriod = new Day(Dates[i]);
                case "30min" -> timePeriod = new Minute(Dates[i]);
                case "15min" -> timePeriod = new Minute(Dates[i]);
                case "5min" -> timePeriod = new Minute(Dates[i]);
                case "1min" -> timePeriod = new Minute(Dates[i]);
            }

            if (timePeriod != null) {
                volumeTimeSeries.addOrUpdate(timePeriod, Volumes[i]);
            }
        }
        return volumeTimeSeries;
    }

}
