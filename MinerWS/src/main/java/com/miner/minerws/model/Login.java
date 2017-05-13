package com.miner.minerws.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"username", "password"})
public class Login {

    private String username;
    private String password;
    
    public Login() {
        
    }
    
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

}
