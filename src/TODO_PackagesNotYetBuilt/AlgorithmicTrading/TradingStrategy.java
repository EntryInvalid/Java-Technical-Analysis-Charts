package TODO_PackagesNotYetBuilt.AlgorithmicTrading;

import java.util.Date;

public interface TradingStrategy {

    default void getAccountBalances(){
    }
    default void setHourlyOrderLimit(long dollarValue, String minute_hour_day_week, String numOfTimeInterval){
    }
    default void getOHLCV(){
    }
    default void setOrderDate(Date orderDate){
    }
    default void setFillDate(Date fillDate){
    }
    default void getOrderDate(){
    }
    default void getFillDate(){
    }
    default void getCurrentTime(){
    }
    default void getElapsedTimeFromOrder(){
    }
    default void getElapsedTimeFromFill(){
    }
    default void getTradesInThisDay(){
    }
    default void getTradesInLast5TradingDays(){
    }
    default void getIndicatorValues(String indicatorName){
    }
}
