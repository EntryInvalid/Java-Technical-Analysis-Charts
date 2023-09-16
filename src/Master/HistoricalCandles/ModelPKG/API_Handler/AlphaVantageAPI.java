package Master.HistoricalCandles.ModelPKG.API_Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class AlphaVantageAPI implements Master.HistoricalCandles.ModelPKG.API_Handler.Api_Key {

    private final String ticker;
    private final String timeframe;
    private final String urlString;
    public Object[][] Candlesticks;


    // Constructor that takes apikey and ticker to do the api call, get the data, and parse it.
    AlphaVantageAPI(String ticker, String timeframe, int numCandles) throws IOException {

        this.ticker = ticker;
        this.timeframe = timeframe;

        Candlesticks = new Object[numCandles + 1][6];

        String function;

        // define the function for the api call
        if (this.timeframe.equals("day")) {
            function = "function=TIME_SERIES_DAILY";
        } else if (this.timeframe.equals("week")) {
            function = "function=TIME_SERIES_WEEKLY";
        } else {
            function = ("function=TIME_SERIES_INTRADAY&interval=" + this.timeframe);
        }

        // output stays at full for max availability then numCandles limits the amount saved/printed on chart
        String outputsize = "&outputsize=full";

        // GET request URL builder
        urlString = "https://www.alphavantage.co/query?" + function + "&symbol=" + ticker
                + "&apikey=" + Api_Key + outputsize + "&datatype=csv";

        // for testing
        System.out.println(urlString);

        // establish API connection
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Set up the API reader then print output line by line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        int lineCount = 0;

        // Parse CSV to organzied Array of data
        while ((line = reader.readLine()) != null) {

            lineCount++;

            // The first line is skipped because it is just headers.
            if (lineCount > 1) {

                // Each line is split at each comma for an array of 6 elements (date, open, high, low, close, and volume)
                Object[] parsedData = line.split(",");
                int Counter = (lineCount - 2);

                System.out.println(" ");

                // copy the array of each line as an element in the 2d array "candleData"
                for (int i = 0; i < 6; i++) {

                    Candlesticks[Counter][i] = parsedData[i];
                    System.out.println(Candlesticks[Counter][i]);
                }
                System.out.println("Candlestick #" + (lineCount-1) + " from current day looking back");
            }

            if (lineCount > numCandles) {
                 System.out.println(" ");
                 reader.close();
                 break;
            }

        }

    }

}


