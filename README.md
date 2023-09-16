## 09/15/2023 -Note
I've done A LOT today including getting the charts worked out, implementing crossHairs, and am currently working on 
fully implementing interfaces. Should anyone find me so quickly this isnt updated -- this is why and it will be updated 
soon.

Thanks,
EntryInvalid

# TA.Charts_Prototype
This is a prototype of a vision of mine that I made while I was still just learning core Java. 

I originally made this to see if I could make a personalized and more extensive analysis platform than the free version 
of 'Tradingview', a popular technical analysis software. I figured it would be a fun project to test my Java skills 
after ~3 months of learning. I plan to recreate this idea with a cleaner design and much more functionality. Hopefully,
I will start on that project within the next 2 months. It depends on when I finish some courses I am currently taking, 
and my schedule in general has gotten busier. The new program very well may be a private project that gets published as 
an Android or Web app.


## !! The API Key can be entered @ Master.HistoricalCandles.ModelPKG.API_Handler.Api_Key !! 


# Dependencies
- The only current Dependency is JFreeChart 1.5.4, but I have had issues with other older versions
   

# APIs Needed
- AlphaVantage API (free key that is very quick and easy to get) -- must pay for real-time
- Live Data & Algorithmic Trading (Not currently used) -- You need a Broker with a stable and accurate API - ideally 
also simulated trading to test. This is something you may want to pay for to ensure quality, avoid your order data being
sold, and make sure your orders are filled in a timely manner. (Tradestation, Alpaca, Polygon, DTN IQfeed, Interactive 
Brokers, Tradier)

   
# !!! Other useful Financial Analysis Libraries. You can use to solve current issues and to implement more functionality
- Strata
- JQuantLib
- finmath.net
- quantcomponents
- DRIP
- Ta4J


# Synopsis/Flow
First, there is the Directory UI. I planned on adding real-time charts, historical charts, charts with fundamental data,
charts of Economic Indicators, backtesting, and algorithmic trading. Realizing that this ambition was shortsighted, I 
decided I would come back to it later with more knowledge/experience. For now, only the Historical Charts package works. 
Below is the flow of the Historical Analysis module.

As note to myself, it would be pretty cool to also add volume profile/market profile charts
 
There is one controller for the API Query and the Chart. The QueryForm class has a frame and a panel class, the 
Visualization class has the same resources via JFreeChart. The Model package contains the API and the actual Model class
that acts as a gateway to the API and a data relay. The User enters the first view where they choose their Service, then 
it is as follows:
  
- The client starts the application and a controller is made that makes a frame that makes a panel 
- On this panel, the client enters their desired stock(ticker), the time frame(time), and the number of candles 
desired(numCandles)
- The queryButton's actionListener uses the controller's method query2Controller to make a new model and pass the query 
input data as parameters
- The model creates a queryRunner Object that creates an 'AlphavantageAPI' constructor with the ticker and timeframe as 
a parameter, numCandles is saved for later use in the controller
- The AlphaVantageAPI Object composes the constructor parameters into a string being our API URL, then runs the call and 
saves the results.
- The queryRunner copies the apiCall's data in a 2D Array, then passes it to the controller via the model's 
forwardCandles method.
- The forwardCandles method passes the price data by using the controller method newVisualization, which starts to load 
the chart. Each time a query is called it produces a new chart, and this is on purpose to have multiple windows for 
comparison until a new way to do so is created (probably from the menu bar)
- The 'newVisualization' method uses SwingUtilities.invokeLater to create a new instance of a chart and use the charts 
method to send it the data from the API and the number of candles that should be populated. This should ensure the data 
syncs before launching.
- From here the client still has the query window open and can open more chart windows, once a back button is added that
will include windows from different modules/functions.

- the class CustomDateAxis will be needed later for custom ticks unless DateAxis is swapped out
- the class CustomCandleRenderer is currently only for minor changes in appearance
- the class TradingdayTimeline is an example I held on to of using the addExclusion method to remove holidays
- the class SegmentedTimeline is deprecated from JFreeChart but i found it online and pasted it here

# Issues and To-Do List
- the chart has spaces for days when trading is paused. . I have heard a number of ideas that have at one point worked 
for others (FYI I know some i skipped because they have their own problems).
  - Timeline Interface - seems to be the most dynamic solution, but may be difficult to set up
  - SegementedTimeline Class - deprecated, but I found it online and it is working for now - implements timeline
  - CategoryAxis
  - Series/SeriesCollection
- A back button needs to be added to each option in the directory panel, so you can launch different types of charts or 
back test while monitoring a chart in the background
- There should not be a need for a lot of different windows, the frame should instead be able to switch panels
- Data should be passed through an Interface for abstraction and flow altered appropriately
- The chart should load all data available then let the user zoom in and out and scroll left and right (would be simple
in a HTML Web app version).
- The Chart should have cross-hairs that follow the mouse to help the user precisely find the date/price desired. It 
would also allow the user to see the data for the candlestick it is 
hovering over
- There should be a way to open a new window with one of the other packages/services
- An option to add and edit indicators should be added which should again be fairly simple
- Volume should be represented as an area chart on the bottom of the candlestick chart component(currently a bar chart 
instead); the component below the chart has volume but is just proof of concept to add components for indicators
- Automatic trend analysis and trend-line drawing should be relatively easy as well by iterating through OHLC data and 
generating a line with two points based on precision variables
- Add a SQL database to save charts, settings, etc. May also be helpful for some analysis
- Would be simple to add functionality for overlapping charts


- The whole design needs a lot of work. The OOP could be a lot better and cleaner. The functionality I wanted to add 
wouldn't make that any better; hence the pause and choice to alter the entire program after learning more
- The whole GUI can use a lot of work as I only ever wrote this to get the functionality going. Ideally, there would be 
a default page with the last chart loaded or a default stock. Then the packages/service could be chosen from a menu. 
Each chart would also be a more complex frame with an editable component layout. 

  
# There are endless ways that it can be better, I wrote this when I didn't know the difference between swing and spring.
# The next version will be far superior and should have all the functionality listed in the packages. 

  
