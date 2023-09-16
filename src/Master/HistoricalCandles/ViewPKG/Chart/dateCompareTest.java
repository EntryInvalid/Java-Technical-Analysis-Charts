package Master.HistoricalCandles.ViewPKG.Chart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class dateCompareTest {


    public static void main(String[] args){

        long millsecondsInDay = 24*60*60*1000;
        long starttime = 0;

        int[] dates = {1,2,3,0,4,0,0,5,6,7,8,9,0,0};
        int[] days = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        List<Integer> missingDayIndexes = new ArrayList<Integer>();
//        int[] missingDays = new int[15];

        int numFound = 0;
        int Date = 0;

        for (int i = 0; i < days.length; i++){

            System.out.println("date = " + dates[i] + " & days = " + days[i]);

             if (Integer.valueOf(days[i]) != Integer.valueOf(dates[i])){
                missingDayIndexes.add(i);
             }


        }


        for (int index : missingDayIndexes) {
            System.out.println("dates[" + index + "] is null");
        }



    }
}
