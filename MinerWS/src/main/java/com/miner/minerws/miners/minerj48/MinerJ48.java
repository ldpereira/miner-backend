/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miner.minerws.miners.minerj48;

import com.miner.minerws.model.Node;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Drawable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author ldpereira
 */
public class MinerJ48 {

    public Node minerJ48() {
        String retornoWeka = executeWekaJ48();

        System.out.println(retornoWeka);
        String[] list = retornoWeka.split("\n");

        HashMap<String, Node> nodes = new HashMap<String, Node>();
        Node root = null;

        for (String item : list) {
            if (!item.contains("{") && !item.contains("}")) {
                if (!item.contains("->")) {
                    String[] nodeString = item.split("\\[");
                    String key = nodeString[0].trim();
                    String text = nodeString[1];
                    String title = text.substring(text.indexOf("\"") + 1, text.lastIndexOf("\""));

                    if (nodes.containsKey(key)) {
                        nodes.get(key).setText(title);
                    } else {
                        Node node = new Node(key, title);
                        nodes.put(node.getKey(), node);

                        if (root == null) {
                            root = node;
                        }
                    }
                } else {
                    //Arrumar para colocar a lista de nós, não só left right.
                    String[] nodeString = item.split("->");
                    Node nodeParent = nodes.get(nodeString[0].trim());
                    
                    String[] restString = nodeString[1].split("\\[");
                    String key = restString[0].trim();
                    String text = restString[1];
                    String title = text.substring(text.indexOf("\"") + 1, text.lastIndexOf("\""));
                    
                    Node node;
                    if (nodes.containsKey(key)) {
                        node = nodes.get(key);
                    } else {
                        node = new Node(key, title);
                    }
                    
                    if (nodeParent.getNodeLeft() == null) {
                        nodeParent.setLabelLeft(title);
                        nodeParent.setNodeLeft(node);
                    } else {
                        nodeParent.setLabelRight(title);
                        nodeParent.setNodeRight(node);
                    }
                    nodes.put(node.getKey(), node);
                }
            }
        }

        return root;
    }

    public static String executeWekaJ48() {
        try {
            ConverterUtils.DataSource ds = new ConverterUtils.DataSource(new FileInputStream("C:/temp/dadosClassificados.arff"));
            Instances ins = ds.getDataSet();
            File file = new File("C:/temp/dadosClassificados.arff");
            file.getAbsolutePath();
            ins.setClassIndex(7);

            J48 j48 = new J48();
            //cria o classificador
            j48.buildClassifier(ins);

            Instance novo = new DenseInstance(7);
            //vincula o arquivo e os dados
            novo.setDataset(ins);
            novo.setValue(0, "SantanaShopping");
            novo.setValue(1, "Fevereiro");
            novo.setValue(2, "Tarde");
            novo.setValue(3, "FluxoModerado");
            novo.setValue(4, "VitrineMedio");
            novo.setValue(5, "Normal");
            novo.setValue(6, "MuitaChuva");

            //gerar a previsao do campo gasta_muito
            double retorno = j48.classifyInstance(novo);
            System.out.println("retorno: " + retorno);
            String graph = ((Drawable) j48).graph();
            System.out.println("#########\n" + j48.numElements() + "\n" + j48.measureTreeSize() + "\n" + graph);

            return graph;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Can't process the file arff";
    }
}
