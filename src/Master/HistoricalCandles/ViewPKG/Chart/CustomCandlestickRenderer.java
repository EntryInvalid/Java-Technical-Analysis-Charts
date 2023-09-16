package Master.HistoricalCandles.ViewPKG.Chart;

import org.jfree.chart.renderer.xy.CandlestickRenderer;

import java.awt.*;

public abstract class CustomCandlestickRenderer extends CandlestickRenderer {

    Color green = new Color(171, 218, 127);
    public CustomCandlestickRenderer() {
        setAutoWidthGap(CandlestickRenderer.WIDTHMETHOD_AVERAGE);
        setDrawVolume(true);
        this.setVolumePaint(green);
        this.setUseOutlinePaint(true);

    }

}

