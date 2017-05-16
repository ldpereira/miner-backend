package com.miner.minerws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ldpereira
 */
@XmlRootElement
@XmlType(propOrder = {"key", "text", "label", "nodes"})
public class Node {
    
    private final String key;
    private String text;
    private String label;
    private List<Node> nodes;
    
    public Node() {
        this.text = "";
        this.key = "";
        this.label = "";
        this.nodes = new ArrayList<Node>();
    }
    
    public Node(String key, String text) {
        this.key = key;
        this.text = text;
        this.label = "";
        this.nodes = new ArrayList<Node>();
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    @XmlElement
    public List<Node> getNodes() {
        return this.nodes;
    }
    
    @XmlElement
    public String getLabel() {
        return this.label;
    }
    
    @XmlElement
    public String getText() {
        return this.text;
    }
    
    @XmlElement
    public String getKey() {
        return this.key;
    }
    
}
