package com.company.demo.web;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow;
import com.vaadin.ui.Layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtAppMainWindow extends AppMainWindow {
    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        Layout vLayout = workArea.getInitialLayout().unwrap(Layout.class);

        addBarChart(vLayout);
    }

    private void addBarChart(Layout vLayout) {
        BarChartConfig config = new BarChartConfig();
        config.data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().type().label("Dataset 1")
                        .backgroundColor("rgba(151,187,205,0.5)")
                        .borderColor("white")
                        .borderWidth(2))
                .addDataset(new LineDataset().type().label("Dataset 2")
                        .backgroundColor("rgba(151,187,205,0.5)")
                        .borderColor("white")
                        .borderWidth(2))
                .addDataset(new BarDataset().type().label("Dataset 3")
                        .backgroundColor("rgba(220,220,220,0.5)"))
                .and();

        config.options()
                .responsive(true)
                .devicePixelRatio(1)
                .maintainAspectRatio(false)
                .done();

        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }

            if (ds instanceof BarDataset) {
                BarDataset bds = (BarDataset) ds;
                bds.dataAsList(data);
            }

            if (ds instanceof LineDataset) {
                LineDataset lds = (LineDataset) ds;
                lds.dataAsList(data);
            }
        }

        ChartJs chart = new ChartJs(config);
        chart.setSizeFull();

        vLayout.addComponent(chart);
    }
}