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

        // Convert the array to an ArrayList and sort it
        List<Date> dateList = Arrays.asList(dates);
        Collections.sort(dateList);

        List<Date> missingDates = findMissingDates(dateList);

        SegmentedTimeline timeline = new SegmentedTimeline(DAY_SEGMENT_SIZE, 7, 0);
        timeline.setStartTime(firstMondayAfter1900());

        // Exclude the missing dates from the timeline
        for (Date missingDate : missingDates) {
            timeline.addException(missingDate);
        }

        Collections.reverse(dateList);

        return timeline;
    }
    
    
    private static List<Date> findMissingDates(List<Date> dates) {
        Date firstDate = dates.get(0);
        Date lastDate = dates.get(dates.size() - 1);

        long millisecondsInDay = 24 * 60 * 60 * 1000;
        long currentMilliseconds = firstDate.getTime();
        long daysBetween = (lastDate.getTime() - firstDate.getTime()) / millisecondsInDay;

        List<Date> missingDates = new ArrayList<>();

        for (int i = 0; i <= daysBetween; i++) {
            Date currentDate = new Date(currentMilliseconds);
            if (!dates.contains(currentDate)) {
                missingDates.add(currentDate);
            }
            currentMilliseconds += millisecondsInDay;
        }

        // Print the missing dates (you can remove this part)
        System.out.println("Missing Dates:");
        for (Date missingDate : missingDates) {
            System.out.println(missingDate);
        }

        return missingDates;
    }
}





