package com.les.carest.util.pdfGenerator;

import com.les.carest.util.pdfGenerator.Plot.PlotOptions;

import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlotUtils {

    public static BufferedImage generateConsumoDiarioChart(List<Object[]> valores, String titulo) {
        // Converter lista de valores para arrays de X (dias) e Y (valores)

        double[] dias = new double[valores.size()];
        double[] totais = new double[valores.size()];

        for (int i = 0; i < valores.size(); i++) {
            Object[] row = valores.get(i);

            // Primeira coluna é BigDecimal representando o dia do mês
            int diaDoMes = ((Number) row[0]).intValue();

            // Segunda coluna é o consumo, pode vir como BigDecimal
            double consumo = ((Number) row[1]).doubleValue();

            dias[i] = diaDoMes;
            totais[i] = consumo;
        }


        // Configurar opções do gráfico
        PlotOptions opts = Plot.plotOpts()
                .title(titulo)
                .width(800)
                .height(400)
                .bgColor(java.awt.Color.WHITE)
                .fgColor(java.awt.Color.BLACK)
                .grids(10, 10);

        // Criar e retornar o gráfico como BufferedImage
        return Plot.plot(opts)
                .series("Consumo Diário",
                        Plot.data().xy(dias, totais),
                        Plot.seriesOpts()
                                .color(java.awt.Color.BLUE)
                                .marker(Plot.Marker.CIRCLE))
                .save();
    }
}