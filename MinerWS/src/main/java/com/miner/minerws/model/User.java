/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miner.minerws.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Lucas Pereira
 */
@XmlRootElement(name = "User")
@XmlType(propOrder = {"username", "email", "password"})
public class User {
    
    private String username;
    private String email;
    private String password;    
    
    public User() {
        //Only used for json return
    }
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }
    
    /*public void addFavorite(Group group) {
        getFavorites().add(group);
    }

    public List<Group> getFavorites() {
        if (favorites == null) {
            favorites = new ArrayList<Group>();
        }
        return favorites;
    }*/

    public void setPassword(String password) {
        this.password = password;
    }
    
}
