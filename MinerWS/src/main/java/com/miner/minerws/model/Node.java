package com.miner.minerws.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ldpereira
 */
@XmlRootElement
@XmlType(propOrder = {"key", "text", "nodeRight", "labelRight", "nodeLeft", "labelLeft"})
public class Node {
    
    private final String key;
    private String text;
    private String labelRight;
    private String labelLeft;
    private Node nodeRight;
    private Node nodeLeft;
    
    public Node() {
        this.text = "";
        this.key = "";
        this.labelLeft = "";
        this.labelRight = "";
    }
    
    public Node(String key, String text) {
        this.key = key;
        this.text = text;
        this.labelLeft = "";
        this.labelRight = "";
    }
    
    public void setNodeRight(Node node) {
        this.nodeRight = node;
    }
    
    public void setNodeLeft(Node node) {
        this.nodeLeft = node;
    }
    
    public void setLabelLeft(String label) {
        this.labelLeft = label;
    }
    
    public void setLabelRight(String label) {
        this.labelRight = label;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    @XmlElement
    public Node getNodeRight() {
        return this.nodeRight;
    }
    
    @XmlElement
    public Node getNodeLeft() {
        return this.nodeLeft;
    }
    
    @XmlElement
    public String getLabelLeft() {
        return this.labelLeft;
    }
    
    @XmlElement
    public String getLabelRight() {
        return this.labelRight;
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
