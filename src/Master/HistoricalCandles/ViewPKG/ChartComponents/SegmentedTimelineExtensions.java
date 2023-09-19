package Master.HistoricalCandles.ViewPKG.ChartComponents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class SegmentedTimelineExtensions extends SegmentedTimeline {
    /**
     * Constructs a new segmented timeline, optionally using another segmented
     * timeline as its base. This chaining of SegmentedTimelines allows further
     * segmentation into smaller timelines.
     * <p>
     * If a base
     *
     * @param segmentSize      the size of a segment in ms. This time unit will be
     *                         used to compute the included and excluded segments of the
     *                         timeline.
     * @param segmentsIncluded Number of consecutive segments to include.
     * @param segmentsExcluded Number of consecutive segments to exclude.
     */
    public SegmentedTimelineExtensions(long segmentSize, int segmentsIncluded, int segmentsExcluded) {
        super(segmentSize, segmentsIncluded, segmentsExcluded);
    }



    public static SegmentedTimeline newTimelineWithGapsRemoved(Date[] dates, String timeframe) {

        List<Long> timestampList = new ArrayList<>();

        for (int i = dates.length - 1; i >= 0; i--) {
            Date date = dates[i];
            timestampList.add(date.getTime());
        }

        long candleLength = 0;

        // Adjust your candle length based on the time period you want to handle
        switch (timeframe){
            case "Weekly" -> candleLength = 1000*60*60*24*7; // milliseconds * seconds * minutes * hours * days
            case "Daily" -> candleLength = 1000*60*60*24; // milliseconds * seconds * minutes * hours
            case "60min" ->  candleLength = 1000*60*60; // milliseconds * seconds * minutes
            case "30min" -> candleLength = 1000*60*30; // milliseconds * seconds * minutes
            case "15min" -> candleLength = 1000*60*15; // milliseconds * seconds * minutes
            case "5min" -> candleLength = 1000*60*5; // milliseconds * seconds * minutes
            case "1min" -> candleLength = 1000*60; // milliseconds * seconds
        }

        // No need to leave out segments as all missing segments are already excluded
        SegmentedTimeline timeline = new SegmentedTimeline(candleLength, 7, 0);

        // JFreeChart always calculates time in milliseconds from the first Monday after the year 1900
        timeline.setStartTime(firstMondayAfter1900());

        List<Long> missingSegments = getMissingDates(timestampList, candleLength);

        // Print the missing dates to the terminal
        System.out.println("Missing Increments:");
        for (Long missingSegment : missingSegments) {
            System.out.println(new java.sql.Timestamp(missingSegment));
            timeline.addException(missingSegment);
        }
        System.out.println("----------------------------------------------------------------------------------------------------"); // For console view

        return timeline;
    }

    // Finds the missing Dates in a dataset and excludes them from the timeline - this ensures no gaps
    private static List<Long> getMissingDates(List<Long> timestampList, long candleLength) {

        // All dates and times are calculated in milliseconds -- the getTime method tells you the time in seconds
        // Define first timestamp, last timestamp, and # of segments in between
        long firstTimestamp = timestampList.get(0);
        long lastTimestamp = timestampList.get(timestampList.size()-1);
        long SeriesBetween = (lastTimestamp - firstTimestamp) / candleLength;

        Long currentMilliseconds = firstTimestamp;

        List<Long> missingDateTimes = new ArrayList<>(); // Used to store missing dates

        // Iterates through dates and saves dates not in the dataset in missingDates
        for (int i = 0; i <= SeriesBetween; i++) {
            //Date currentDate = new Date(currentMilliseconds);
            if (!timestampList.contains(currentMilliseconds)) {
                missingDateTimes.add(currentMilliseconds);
            }
            currentMilliseconds += candleLength;
        }

        return missingDateTimes;
    }
}

