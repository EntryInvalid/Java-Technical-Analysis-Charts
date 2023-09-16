package Master.HistoricalCandles.ViewPKG.Chart;

import Master.HistoricalCandles.ViewPKG.ChartView;
import org.jfree.chart.axis.*;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static Master.HistoricalCandles.ViewPKG.Chart.SegmentedTimelineExtensions.newTradingDayTimeline;

// Use this class to make custom DateAxis and Ticks
// Edit days skipped on the axis by editing the newTradingDayTimeline in SegmentedTimeline

public class CustomDateAxis extends DateAxis{

    private final Date[] dates; // Dates in dataset
    public ChartView visualization;


    public CustomDateAxis(ChartView visualization, Date[] dates) throws ParseException {
        super();
        this.visualization = visualization;
        this.dates = dates;
        SegmentedTimeline timeline = newTradingDayTimeline(dates);
        this.setTimeline(timeline);
        this.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat("MM-dd-yy")));
        this.setTickLabelPaint(Color.BLACK);
        this.setVerticalTickLabels(true);
    }

    @Override
    public List<DateTick> refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {

        List<DateTick> ticks = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");

        for (int i = 0; i < dates.length - 1; i++) {
            Date date = dates[i];
            ticks.add(new DateTick(date, dateFormat.format(date), TextAnchor.TOP_CENTER, TextAnchor.CENTER, -45.0));
//            System.out.println(dates[i]);  //  To see where the dates are being filled
        }

        Date lastDate = dates[dates.length - 1];
        ticks.add(new DateTick(lastDate, dateFormat.format(lastDate), TextAnchor.TOP_CENTER, TextAnchor.CENTER, -45.0));

        return ticks;
    }


}