package Master.HistoricalCandles.ViewPKG.Chart;

import java.util.*;

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

    public static SegmentedTimeline newTimelineWithGapsRemoved(Date[] dates) {

        List<Date> dateList = Arrays.asList(dates); // Convert to ArrayList
        List<Date> missingDates = findMissingDates(dateList); // Find missing dates in the dataset
        Collections.reverse(dateList); // Reverse for proper orientation on chart

        // No need to leave out days as all missing days are already excluded - a dynamic solution
        SegmentedTimeline timeline = new SegmentedTimeline(DAY_SEGMENT_SIZE, 7, 0);

        // JFreeChart always calculates time in milliseconds from the first monday after the year 1900
        timeline.setStartTime(firstMondayAfter1900());

        // Exclude the missing dates from the timeline
        for (Date missingDate : missingDates) {
            timeline.addException(missingDate);
        }

        return timeline;
    }

    // Finds the missing Dates in a dataset and excludes them from the timeline - this ensures no gaps
    private static List<Date> findMissingDates(List<Date> dates) {

        Collections.sort(dates);

        // All dates and times are calculated in milliseconds -- the getTime method tells you the time in seconds
        Date firstDate = dates.get(0);
        Date lastDate = dates.get(dates.size() - 1);

        // Calculate milliseconds in a day
        long millisecondsInDay = 24 * 60 * 60 * 1000;

        // The time of the first date in milliseconds from the firstMondayAfter1900
        long currentMilliseconds = firstDate.getTime();

        // Using milliseconds to calculate the time between the first and last Date
        long daysBetween = (lastDate.getTime() - firstDate.getTime()) / millisecondsInDay;

        List<Date> missingDates = new ArrayList<>(); // Used to store missing dates

        // Iterates through dates and saves dates not in the dataset in missingDates
        for (int i = 0; i <= daysBetween; i++) {
            Date currentDate = new Date(currentMilliseconds);
            if (!dates.contains(currentDate)) {
                missingDates.add(currentDate);
            }
            currentMilliseconds += millisecondsInDay;
        }

        // Print the missing dates to the terminal
        System.out.println("Missing Dates:");
        for (Date missingDate : missingDates) {
            System.out.println(missingDate);
        }

        return missingDates;
    }
}