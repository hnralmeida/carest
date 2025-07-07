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

        double maxDia = Double.MIN_VALUE;
        double maxConsumo = Double.MIN_VALUE;

        for (int i = 0; i < valores.size(); i++) {
            Object[] row = valores.get(i);

            // Primeira coluna é BigDecimal representando o dia do mês
            int diaDoMes = ((Number) row[0]).intValue();

            // Segunda coluna é o consumo, pode vir como BigDecimal
            double consumo = ((Number) row[1]).doubleValue();

            dias[i] = diaDoMes;
            totais[i] = consumo;

            if (diaDoMes > maxDia) maxDia = diaDoMes;
            if (consumo > maxConsumo) maxConsumo = consumo;
        }

        // Calcular grids baseados no maior valor, para garantir inteiros nos eixos
        int gridsX = (int) Math.ceil(maxDia)-1;
        int gridsY = (int) Math.ceil(maxConsumo)+1;

        // Limitar para não exagerar na quantidade de grids
        gridsX = Math.min(gridsX, 31); // No máximo 31 dias
        gridsY = Math.max(5, Math.min(gridsY, 10)); // Mantém o eixo Y legível

        PlotOptions opts = Plot.plotOpts()
                .title(titulo)
                .width(800)
                .height(400)
                .bgColor(java.awt.Color.WHITE)
                .fgColor(java.awt.Color.BLACK)
                .grids(gridsX, gridsY);

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