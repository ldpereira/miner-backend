/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miner.minerws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Lucas Pereira
 */
@XmlRootElement
@XmlType(propOrder = {"id", "title", "userCreate", "description"})
public class Group {
    
    private final String title;
    private List<String> tags;
    private final int id;
    private List<String> users;
    private List<Message> messages;
    private final String userCreate;
    private String description;
    
    public Group() {
        title = "";
        id = -1;
        userCreate = "default";
    }
    
    public Group(int id, String title, String userCreate, String description) {
        this.id = id;
        this.title = title;
        this.userCreate = userCreate;
        this.description = description;
        getUsers().add(userCreate);
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getUserCreate() {
        return userCreate;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }
    
    public void addUser(User user) {
        getUsers().add(user.getUsername());
    }
    
    public void removeUser(User user) {
        getUsers().remove(user.getUsername());
    }

    public List<String> getUsers() {
        if (users == null) {
            users = new ArrayList<String>();
        }
        return users;
    }
    
    public void addTag(String tag) {
        getTags().add(tag);
    }

    private List<String> getTags() {
        if (tags == null) {
            tags = new ArrayList<String>();
        }
        return tags;
    }
    
    public void addMessage(Message message) {
        getMessages().add(message);
    }

    public List<Message> getMessages() {
        if (messages == null) {
            messages = new ArrayList<Message>();
        }
        return messages;
    }
    
}
