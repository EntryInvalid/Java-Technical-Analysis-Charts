# TA.Charts_Prototype
This is a prototype of a vision of mine that I made while I was still just learning core Java. I traded equities, 
derivatives, and crypto for approximately 6 years now. My specialty has always been mean reversion and I know it will be 
fairly easy to implement the code for. Wanting a bigger project, I figured I'd make a full-fledged trading app with all 
types of datasets, Back Testing, and Algorithmic Trading. After a working implementation of everything is built my 
continued attention will probably only be noticeable for the UI, the Back-Testing/Algorithmic Trading, and Historical 
Charts (they will be more TA-Oriented than Live Charts). If all goes well I may develop this into a desktop or web-app 
as well.


## !! The API Key can be entered @ Master.HistoricalCandles.ModelPKG.API_Handler.Api_Key !! 


# Dependencies
- The only current Dependency is JFreeChart 1.5.4, but I have had issues with other older versions
- AlphaVantage API { https://www.alphavantage.co/ } -- key is free and only takes a few minutes to get

- For Live Data & Algorithmic Trading (Not currently used) -- You need a Broker with a stable and accurate API - ideally 
also simulated trading to test. This is something you may want to pay for to ensure quality, avoid your order data being
sold, and make sure your orders are filled in a timely manner. 
- (Tradestation, Alpaca, Polygon, DTN IQfeed, Interactive Brokers, Tradier)
   
# !!! Other useful Financial Analysis Libraries. You can use to solve current issues and to implement more functionality
- Strata
- JQuantLib
- finmath.net
- quantcomponents
- DRIP
- Ta4J


# Synopsis/Flow
There are three main packages being Model, View, and Controller. Model also has an API Package and soon a SQL Package. 
The View has a package for the chart. There is an interface in each of the main Packages for data to be passed in the 
abstract. The class it is in is the receiving class, as an example of this the controller stands out as the only one 
with 2 interface, as it needs to receive data from the QueryView and then again from AlphavantageAPI. Ultimately all 
the data is in the Model (API and soon SQL), the view is what's visible/interacted with, and the controller just uses 
interface methods to relay data between the two of them; typical MVC pattern.


- Master launcher opens DirectoryUI (can only choose Historic Charts for now :: others give popup); disappears after 
choosing the service
- Program_Launcher makes a new controller and uses its method to open a Frame, which chains methods to also make a Panel.
All this is the QueryView where the data inputted
- In the QueryView the user chooses a stock ticker (soon dropdown), a timeframe for each candlestick, and the number of 
candlesticks to be generated (issues for over 100 hence need slider). User hits Query
- The data is passes with interface to the controller, then the model, then QueryRunner, then AlphaVantageAPI.
- The Query is performed returning a matrix of Dates:OHLCV data that is sent straight to the controller, which relays it
to the ChartView class. 
- The class get the data, processes everything, then launches a candlestick chart
- From here you can still use the QueryView to create new chart (a design pattern for multiple charts; I will switch to 
new instances from the menu bar once available)



* * CLASSES THAT ARE EXTENDED FROM JFREECHART(ALL IN CHART PACKAGE) :
* - the class CustomDateAxis is for setting the timeline and for custom DateTicks, 
* - the class CustomCandleRenderer is used for candlestick width and some color changes
* - the class SegmentedTimeline is deprecated from JFreeChart but I found it online. 
* - the class SegmentedTimeLineExtensions extends it and is where my custom timeline code is. That is where you can 
* * * find the code to not skip days (skipping weekends and null dates is a big problem in JfreeChart Candlesticks)
* 


# Issues and To-Do List
- A back button needs to be added to each option in the directory panel, so you can launch different types of charts or 
back test while monitoring a chart in the background
- Add a SQL database to save charts, settings, etc. May also be helpful for some analysis
- There should not be a need for a lot of different windows, the frame should instead be able to switch panels
- The chart should load all data available then let the user zoom in and out and scroll left and right (would be simple
in a HTML Web app version).
- There should be a way to open a new window with one of the other packages/services
- An option to add and edit indicators should be added which should again be fairly simple
- Automatic trend analysis and trend-line drawing should be relatively easy as well by iterating through OHLCV data and 
generating a line with two points based on precision variables
- Would be simple to add functionality for overlapping charts
- it would be pretty cool to also add volume profile/market profile charts

- The whole GUI can use a lot of work and that is really my #1 concern at the moment. After some UI customization it
will be time to implement Live Data and Fundamental Data