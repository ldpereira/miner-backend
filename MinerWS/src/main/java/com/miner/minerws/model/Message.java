/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miner.minerws.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Lucas Pereira
 */
@XmlRootElement
@XmlType(propOrder = {"groupID", "username", "textMessage", "date"})
public class Message {
    
    private final int groupID;
    private final String username;
    private final String textMessage;
    private final String date;
    
    public Message() {
        groupID = 0;
        username = "teste";
        textMessage = "teste";
        date = "01/01/2016 11:11:11";
    }
    
    public Message(int groupID, String username, String text) {
        this.groupID = groupID;
        this.username = username;
        this.textMessage = text;
        this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    @XmlElement
    public int getGroupID() {
        return groupID;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    @XmlElement
    public String getTextMessage() {
        return textMessage;
    }

    @XmlElement
    public String getDate() {
        return date;
    }    
}
