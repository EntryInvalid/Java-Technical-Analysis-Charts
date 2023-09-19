package Master.HistoricalCandles.ModelPKG.API_Handler;

import Master.HistoricalCandles.ControllerPKG.Controller;
import Master.HistoricalCandles.ControllerPKG.OhlcvInterface;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


////////////////////////////////////////////////////////////////////////////////////
// Setup Alpaca Api or Tradier Api for real time data feed (Separate file) /////////
////////////////////////////////////////////////////////////////////////////////////
// Problem with Intra-day Crypto Api Calls (??Reorder keywords for crypto call??) //
////////////////////////////////////////////////////////////////////////////////////

class AlphaVantageAPI implements Api_Key, OhlcvInterface {

    private final Controller controller;
    private final String ticker;
    private final String timeframe;
    private final int candleQuantity;
    private final String dataSource;
    private String function;
    private String Outputsize;
    private String urlString;


    // Constructor that takes apikey and ticker to do the api call, get the data, and parse it.
    AlphaVantageAPI(Controller controller, String ticker, String timeframe, int candleQuantity, String dataSource) throws IOException {

        this.controller = controller;
        this.ticker = ticker;
        this.timeframe = timeframe;
        this.candleQuantity = candleQuantity;
        this.dataSource = dataSource;

        Object[][] Candlesticks = new Object[candleQuantity + 1][6];

        BufferedReader reader;

        if (dataSource.equals("AlphaVantage API")) reader = getApiReader();
        else {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("Sample-Data/" + dataSource)));
        }

        String line;
        int lineCount = 0;

        // Parse CSV to organzied Array of data
        while ((line = reader.readLine()) != null) {

            lineCount++;

            // The first line is skipped because it is just headers.
            if (lineCount > 1) {

                // Each line is split at each comma for an array of 6 elements (date, open, high, low, close, and volume)
                Object[] parsedData = line.split(",");

                // Spacer for console
                System.out.println(" ");

                for (int i = 0; i < 6; i++) {
                    // Copy each line as in Candlestick matrix
                    Candlesticks[lineCount - 2][i] = parsedData[i];

                    // The timeframe needs to be reset anytime that Saved Data is being used. This will ensure that a
                    // quick click of the saved file without updating the timeframe will still properly display the
                    // data. Otherwise, such an inconsistency will cause a parse exception. Alternate ways to ensure the
                    // data ends in a timestamp would be sufficient as well.
                    
                    // Add seconds when necessary
                    if (timeframe.equals("Daily") || timeframe.equals("Weekly")) {
                        if (i == 0) Candlesticks[lineCount - 2][i] += " 00:00:00";
                    }

                    // Print the Candlestick data to the console
                    System.out.println(Candlesticks[lineCount - 2][i]);
                }

                // Printing Candlestick Data
                System.out.println("Candlestick #" + (lineCount - 1) + " counting from present day backwards");
            }

            // Close reader and send data to controller once the correct Amount of Candlesticks data has been recorded
            if (lineCount > this.candleQuantity) {
                //System.out.println(" ");
                System.out.println("----------------------------------------------------------------------------------------------------"); // For console view
                //System.out.println(" ");
                reader.close();
                break;
            }

        }

        sendControllerOhlcv(Candlesticks);
    }

    // Pass OHLCV matrix to Controller
    @Override
    public void sendControllerOhlcv(Object[][] Candlesticks) {
        controller.sendControllerOhlcv(Candlesticks);
    }

    private BufferedReader getApiReader() throws IOException {

        // define the function for the api call based on the Query input
        if (this.timeframe.equals("Daily")) {
            this.function = "function=TIME_SERIES_DAILY";
        } else if (this.timeframe.equals("Weekly")) {
            this.function = "function=TIME_SERIES_WEEKLY";
        } else {
            this.function = ("function=TIME_SERIES_INTRADAY&interval=" + this.timeframe);
        }

        // Output stays at full for max availability then numCandles limits the amount saved/printed on chart
        this.Outputsize = "&outputsize=full";

        // API call GET request URL builder
        this.urlString = "https://www.alphavantage.co/query?" + this.function + "&symbol=" + this.ticker
                + "&apikey=" + this.Api_Key + this.Outputsize + "&datatype=csv";

        // For testing
        System.out.println(this.urlString);

        // Establish API connection
        URL url = new URL(this.urlString);
        HttpURLConnection HttpConnect = (HttpURLConnection) url.openConnection();
        HttpConnect.setRequestMethod("GET");

        // Return set up the BufferedReader
        return new BufferedReader(new InputStreamReader(HttpConnect.getInputStream()));
    }

    private void saveRawDataToFile(InputStream inputStream) {

        // Make new File --- set new File's name here for now
        File sampleData = new File("sampleData_week.csv");

        // Copy inputStream to new File
        try (OutputStream outputStream = new FileOutputStream(sampleData)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
