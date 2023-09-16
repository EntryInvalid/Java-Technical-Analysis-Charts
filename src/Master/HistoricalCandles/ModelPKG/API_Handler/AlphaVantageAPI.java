package Master.HistoricalCandles.ModelPKG.API_Handler;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ControllerPKG.OhlcvInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class AlphaVantageAPI implements Api_Key, OhlcvInterface {

    private final Controller controller;
    private final String ticker;
    private final String timeframe;
    private final int candleQuantity;
    private final String function;
    private final String Outputsize;
    private final String urlString;


    // Constructor that takes apikey and ticker to do the api call, get the data, and parse it.
    AlphaVantageAPI(Controller controller, String ticker, String timeframe, int candleQuantity) throws IOException {

        this.controller = controller;
        this.ticker = ticker;
        this.timeframe = timeframe;
        this.candleQuantity = candleQuantity;

        Object[][] Candlesticks = new Object[candleQuantity + 1][6];

        // define the function for the api call based on the
        if (this.timeframe.equals("day")) {
            this.function = "function=TIME_SERIES_DAILY";
        } else if (this.timeframe.equals("week")) {
            this.function = "function=TIME_SERIES_WEEKLY";
        } else {
            this.function = ("function=TIME_SERIES_INTRADAY&interval=" + this.timeframe);
        }

        // output stays at full for max availability then numCandles limits the amount saved/printed on chart
        this.Outputsize = "&outputsize=full";

        // API call GET request URL builder
        this.urlString = "https://www.alphavantage.co/query?" + this.function + "&symbol=" + this.ticker
                + "&apikey=" + this.Api_Key + this.Outputsize + "&datatype=csv";

        // for testing
        System.out.println(this.urlString);

        // establish API connection
        URL url = new URL(this.urlString);
        HttpURLConnection HttpConnect = (HttpURLConnection) url.openConnection();
        HttpConnect.setRequestMethod("GET");

        // Set up the API reader then print output line by line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(HttpConnect.getInputStream()));

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

                // Printing Candlestick Data
                System.out.println("Candlestick #" + (lineCount-1) + " counting from present day backwards");
            }

            // close reader and send data to Controller controller once the correct
            // amount of Candlesticks data has been recorded
            if (lineCount > this.candleQuantity) {
                 System.out.println(" ");
                 reader.close();
                 break;
            }

        }
        // Pass Date:OHLCV matrix to Controller
        sendControllerOhlcv(Candlesticks);

    }

    // Pass Date:OHLCV matrix to Controller
    @Override
    public void sendControllerOhlcv(Object[][] Candlesticks) {
        controller.sendControllerOhlcv(Candlesticks);
    }

}