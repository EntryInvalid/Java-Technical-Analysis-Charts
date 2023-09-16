package Master.HistoricalCandles.ViewPKG.Chart;

import org.jfree.chart.renderer.xy.CandlestickRenderer;

import java.awt.*;

public abstract class CustomCandlestickRenderer extends CandlestickRenderer {

    Color volumeColor = new Color(192, 246, 248);


    public CustomCandlestickRenderer() {
        setAutoWidthGap(CandlestickRenderer.WIDTHMETHOD_AVERAGE); // can also use setCandleWidth(); i'd start at 5
        setDrawVolume(true); // set false to remove the overlayed volume bar chart
        this.setVolumePaint(volumeColor); // change volume bar chart color
        this.setUseOutlinePaint(true); // changes finish - if set flase tends to outline candlesticks red
        this.setDefaultSeriesVisibleInLegend(false);
        this.setDefaultItemLabelPaint(volumeColor);
    }

}