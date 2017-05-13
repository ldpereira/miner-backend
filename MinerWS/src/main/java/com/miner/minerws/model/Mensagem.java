package com.miner.minerws.model;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensagem {

    public String message;

    public Mensagem() {
    }
    
    public Mensagem(String message) {
        this.message = message;
    }

}
