/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miner.minerws.miners.minerj48;

import com.miner.minerws.model.Node;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
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

    public Node minerJ48(String file) {
        String retornoWeka;

        ClassLoader classLoader = getClass().getClassLoader();
        retornoWeka = executeWekaJ48(classLoader.getResource("data/dados_completos.arff").getFile());
        
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
                        nodes.get(key).setText(formatText(title));
                    } else {
                        Node node = new Node(key, formatText(title));
                        nodes.put(node.getKey(), node);

                        if (root == null) {
                            root = node;
                        }
                    }
                } else {
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
                        node = new Node(key, formatTitle(title));
                    }

                    node.setLabel(formatTitle(title));
                    nodeParent.addNode(node);

                    nodes.put(node.getKey(), node);
                }
            }
        }

        return root;
    }

    public static String executeWekaJ48() {
        try {
            ConverterUtils.DataSource ds = new ConverterUtils.DataSource(new FileInputStream("C:/temp/dados_completos.arff"));
            Instances ins = ds.getDataSet();
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

    public static String executeWekaJ48(String file) {
        try {
            ConverterUtils.DataSource ds = new ConverterUtils.DataSource(new FileInputStream(file));
            Instances ins = ds.getDataSet();
            ins.setClassIndex(8);

            J48 j48 = new J48();
            //cria o classificador
            j48.buildClassifier(ins);

            Instance novo = new DenseInstance(8);
            //vincula o arquivo e os dados
            novo.setDataset(ins);
            novo.setValue(0, "SantanaShopping");
            novo.setValue(1, "quarta-feira");
            novo.setValue(2, "Fevereiro");
            novo.setValue(3, "Tarde");
            novo.setValue(4, "FluxoModerado");
            novo.setValue(5, "VitrineMedio");
            novo.setValue(6, "Morno");
            novo.setValue(7, "PoucaChuva");

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

    public static String formatTitle(String text) {
        String textChanged;
        if ("= SemFluxo".equals(text)) {
            textChanged = "= Sem fluxo (0 clientes)";
        } else if ("= FluxoBaixo".equals(text)) {
            textChanged = "= Fluxo baixo (1 a 50 clientes)";
        } else if ("= FluxoModerado".equals(text)) {
            textChanged = "= Fluxo moderado (51 a 100 clientes)";
        } else if ("= FluxoAlto".equals(text)) {
            textChanged = "= Fluxo alto (101 a 200 clientes)";
        } else if ("= VitrineNenhumMovimento".equals(text)) {
            textChanged = "= Sem movimento (0 visitantes)";
        } else if ("= VitrineBaixo".equals(text)) {
            textChanged = "= Movimento baixo (1 a 90 visitantes)";
        } else if ("= VitrineMedio".equals(text)) {
            textChanged = "= Movimento médio (91 a 300 visitantes)";
        } else if ("= VitrineAlto".equals(text)) {
            textChanged = "= Movimento alto (301 a 743 visitantes)";
        } else if ("= Manha".equals(text)) {
            textChanged = "= Matutino (10h - 12h)";
        } else if ("= Tarde".equals(text)) {
            textChanged = "= Vespertino (13h-18h)";
        } else if ("= Noite".equals(text)) {
            textChanged = "= Noturno (19h-22h)";
        } else if ("= Frio".equals(text)) {
            textChanged = "= Frio (18ºC a 21ºC)";
        } else if ("= Morno".equals(text)) {
            textChanged = "= Morno (21,1ºC a 24ºC)";
        } else if ("= Quente".equals(text)) {
            textChanged = "= Quente (24,1ºC a 27ºC)";
        } else if ("= MuitoQuente".equals(text)) {
            textChanged = "= Muito quente (27.1ºC a 34ºC)";
        } else if ("= SantanaShopping".equals(text)) {
            textChanged = "= Shopping Santana";
        } else if ("= PinheirosShopping".equals(text)) {
            textChanged = "= Shopping Pinheiros";
        } else if ("= SemChuva".equals(text)) {
            textChanged = "= Ensolarado (0mm)";
        } else if ("= PoucaChuva".equals(text)) {
            textChanged = "= Pouca chuva (0mm a 1mm)";
        } else if ("= ChuvaLeve".equals(text)) {
            textChanged = "= Chuva leve (2mm a 4,6mm)";
        } else if ("= ChuvaModerada".equals(text)) {
            textChanged = "= Chuva moderada (4,7mm a 8,2mm)";
        } else if ("= ChuvaForte".equals(text)) {
            textChanged = "= Chuva forte (8,2mm a 26,5mm)";
        } else if ("= Tempestade".equals(text)) {
            textChanged = "= Tempestade (26,6mm a 46,6mm)";
        } else if ("= Marco".equals(text)) {
            textChanged = "= Março";
        } else {
            textChanged = text;
        }

        return textChanged;
    }

    public static String formatText(String title) {
        String titleChanged;

        if ("Loja".equalsIgnoreCase(title)) {
            titleChanged = "Se loja:";
        } else if ("DiaDaSemana".equalsIgnoreCase(title)) {
            titleChanged = "Se dia da semana:";
        } else if ("Mes".equalsIgnoreCase(title)) {
            titleChanged = "Se mês:";
        } else if ("PeriodoDoDia".equalsIgnoreCase(title)) {
            titleChanged = "Se período:";
        } else if ("Fluxo".equalsIgnoreCase(title)) {
            titleChanged = "Se fluxo:";
        } else if ("FluxoDaVitrine".equalsIgnoreCase(title)) {
            titleChanged = "Se fluxo da vitrine:";
        } else if ("Temperatura".equalsIgnoreCase(title)) {
            titleChanged = "Se temperatura:";
        } else if ("PrevisaoDoTempo".equalsIgnoreCase(title)) {
            titleChanged = "Se previsão do tempo:";
        } else if ("Atendimentos".equalsIgnoreCase(title)) {
            titleChanged = "Então, possível quantidade de atendimento:";
        } else if (title.contains("Atendimento")) {
            if (title.contains("/")) {
                String[] valores = title.substring(title.indexOf("(") + 1, title.indexOf(")")).split("/");
                double vlPositivo = Double.valueOf(valores[0]);
                double vlNegativo = Double.valueOf(valores[1]);

                Double acertividade = (100 - ((vlNegativo * 100) / vlPositivo));
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String acertividadeString = decimalFormat.format(acertividade) + "%";

                if (title.startsWith("SemAtendimento")) {

                    decimalFormat = new DecimalFormat("#.0");

                    titleChanged = String.format("Extimativa de atendimento: sem atendimento, %s de acertividade baseado em "
                            + "registros na base de conhecimento.",
                            acertividadeString, decimalFormat.format(vlPositivo + vlNegativo));
                } else {
                    String vlInferior = title.substring(0, title.indexOf("-"));
                    String vlSuperior = title.substring(title.indexOf("-")+1, title.indexOf("Atend"));

                    titleChanged = String.format("Extimativa de atendimento: de %s à %s, %s de acertividade baseado em "
                            + "%s registros na base de conhecimento.",
                            vlInferior, vlSuperior, acertividadeString, decimalFormat.format((vlPositivo + vlNegativo)));
                }
            } else {
                String valor = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
                double vlPositivo = Double.valueOf(valor);

                DecimalFormat decimalFormat = new DecimalFormat("#.00");

                if (title.startsWith("SemAtendimento")) {
                    decimalFormat = new DecimalFormat("#.0");

                    titleChanged = String.format("Extimativa de atendimento: sem atendimento, %s de acertividade baseado em "
                            + "%s registros na base de conhecimento.",
                            "100%", decimalFormat.format(vlPositivo));
                } else if (title.startsWith("19Atendimentos")) {
                    decimalFormat = new DecimalFormat("#.0");

                    titleChanged = String.format("Extimativa de atendimento: 19 atendimentos, %s de acertividade baseado em "
                            + "%s registros na base de conhecimento.",
                            "100%", decimalFormat.format(vlPositivo));
                } else {

                    String vlInferior = title.substring(0, title.indexOf("-"));
                    String vlSuperior = title.substring(title.indexOf("-")+1, title.indexOf("Atend"));

                    titleChanged = "Extimativa de atendimento: de " + vlInferior + " à " + vlSuperior
                            + ", 100% de acertividade baseado em "
                            + vlPositivo + " registros na base de conhecimento.";
                }
            }
        } else {
            titleChanged = title;
        }

        return titleChanged;
    }

}
