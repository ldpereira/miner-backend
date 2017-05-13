/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miner.minerws.miners.minernaivebayes;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Lucas Pereira
 */
public class MinerNaiveBayes {

    /**
     * @param args the command line arguments
     */
    public static void teste(String[] args) {
        try {
            DataSource ds = new DataSource("src/br/com/minerl/vendas/vendas.arff");
            Instances ins = ds.getDataSet();
            //System.out.println(ins.toString());
            
            ins.setClassIndex(3);
            
            NaiveBayes nb = new NaiveBayes();
            //cria o classificador
            nb.buildClassifier(ins);
            
            Instance novo = new DenseInstance(4);
            
            //vincula o arquivo e os dados
            novo.setDataset(ins);
            novo.setValue(0, "M");
            novo.setValue(1, "20-39");
            novo.setValue(2, "Sim");
            
            //gerar a previsao do campo gasta_muito
            double probabilitdade[] = nb.distributionForInstance(novo);
            System.out.println("Probabilidade não: " + probabilitdade[0]);
            System.out.println("Probabilidade sim: " + probabilitdade[1]);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
