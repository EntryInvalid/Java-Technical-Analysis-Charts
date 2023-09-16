package Master.HistoricalCandles.ViewPKG.Chart;

import org.jfree.chart.renderer.xy.CandlestickRenderer;

import java.awt.*;

public abstract class CustomCandlestickRenderer extends CandlestickRenderer {

    Color lightGreen = new Color(171, 218, 127);

    public CustomCandlestickRenderer() {
        setAutoWidthGap(CandlestickRenderer.WIDTHMETHOD_AVERAGE); // can also use setCandleWidth(); {i'd start at 5}
        setDrawVolume(true); // set false to remove the overlayed volume bar chart
        this.setVolumePaint(lightGreen); // change volume bar chart color
        this.setUseOutlinePaint(true); // changes finish - if set flase tends to outline candlesticks red

    }

}

