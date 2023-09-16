package Master.HistoricalCandles.ViewPKG.Chart;

import Master.HistoricalCandles.ViewPKG.ChartView;
import org.jfree.chart.axis.*;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Master.HistoricalCandles.ViewPKG.Chart.SegmentedTimelineExtensions.newTimelineWithGapsRemoved;

public class CustomDateAxis extends DateAxis{

    private final Date[] dates; // Dates in dataset
    private ChartView chart;
    
    // CustomDateAxis Constructor + settings
    public CustomDateAxis(ChartView chart, Date[] dates) {
        super();
        this.chart = chart;
        this.dates = dates; // Dates from chart (from Api with no gaps)
        
        // create newTimelineWithGapsRemoved (custom implementation written in SegmentedTimelineExtensions)
        SegmentedTimeline timeline = newTimelineWithGapsRemoved(dates); 
        this.setTimeline(timeline);
        
        // tick settings
        this.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1, 
                new SimpleDateFormat("MM/dd/yyyy"))); // custom format
        this.setTickLabelPaint(Color.BLACK); // color of date ticks
        this.setVerticalTickLabels(true);
    }

    // set custom date format and angle for date ticks
    @Override
    public List<DateTick> refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
        
        List<DateTick> ticks = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // custom format

        // set angle to date/time ticks
        for (int i = 0; i < dates.length - 1; i++) {
            Date date = dates[i];
            ticks.add(new DateTick(date, dateFormat.format(date), TextAnchor.TOP_CENTER, TextAnchor.CENTER, -45.0));
            System.out.println("Testing  -- from CustomDateAxis" + dates[i]);  //  To see where the dates are being filled
        }

        // set angle to date/time ticks
        Date lastDate = dates[dates.length - 1];
        ticks.add(new DateTick(lastDate, dateFormat.format(lastDate), TextAnchor.TOP_CENTER, TextAnchor.CENTER, -45.0));

        return ticks;
    }


}