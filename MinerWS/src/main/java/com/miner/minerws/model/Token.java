package com.miner.minerws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {

    public String token;

    public Token() {
    }

    public Token(String token) {
        super();
        this.token = token;
    }
;

}
