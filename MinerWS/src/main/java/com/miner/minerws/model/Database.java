/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miner.minerws.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas Pereira
 */
public class Database {

    private int idGroup;
    private Map<String, User> users;
    private Map<Integer, Group> groups;
    private static Database database;

    private Database() {
        users = new HashMap<String, User>();
        groups = new HashMap<Integer, Group>();
        idGroup = 1;
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
            database.addUser(new User("ldpereira", "email@mail.com", "teste123"));
            database.addUser(new User("marina", "email@mail.com", "pass"));
            database.addGroup("AngularJS", "ldpereira", "Fórum sobre AngularJS =) ");
            
            database.addUser(new User("gbieging", "email@mail.com", "teste123"));
            database.addUser(new User("afurtado", "email@mail.com", "teste123"));
            database.addUser(new User("fulano", "email@mail.com", "teste123"));
            database.addUser(new User("deltrano", "email@mail.com", "teste123"));
            database.addUser(new User("sicrano", "email@mail.com", "teste123"));
            database.addUser(new User("jose ninguem", "email@mail.com", "teste123"));
            database.addUser(new User("dona maria", "email@mail.com", "teste123"));
            Group g = database.groups.get(database.addGroup("Política", "ldpereira", "Fórum sobre política"));

            g.addUser(database.users.get("gbieging"));
            g.addUser(database.users.get("marina"));
            g.addUser(database.users.get("afurtado"));
            g.addUser(database.users.get("fulano"));
            g.addUser(database.users.get("deltrano"));

            database.sendMessage(g.getId(), "ldpereira", "Política de Interesse\n"
                    + "Em Portugal não há ciência de governar nem há ciência de organizar oposição. Falta igualmente a aptidão, e o engenho, e o bom senso, e a moralidade, nestes dois factos que constituem o movimento político das nações.\n"
                    + "A ciência de governar é neste país uma habilidade, uma rotina de acaso, diversamente influenciada pela paixão, pela inveja, pela intriga, pela vaidade, pela frivolidade e pelo interesse.\n"
                    + "A política é uma arma, em todos os pontos revolta pelas vontades contraditórias; ali dominam as más paixões; ali luta-se pela avidez do ganho ou pelo gozo da vaidade; ali há a postergação dos princípios e o desprezo dos sentimentos; ali há a abdicação de tudo o que o homem tem na alma de nobre, de generoso, de grande, de racional e de justo; em volta daquela arena enxameiam os aventureiros inteligentes, os grandes vaidosos, os especuladores ásperos; há a tristeza e a miséria; dentro há a corrupção, o patrono, o privilégio. A refrega é dura; combate-se, atraiçoa-se, brada-se, foge-se, destrói-se, corrompe-se. Todos os desperdícios, todas as violências, todas as indignidades se entrechocam ali com dor e com raiva.\n"
                    + "À escalada sobem todos os homens inteligentes, nervosos, ambiciosos (...) todos querem penetrar na arena, ambiciosos dos espectáculos cortesãos, ávidos de consideração e de dinheiro, insaciáveis dos gozos da vaidade.\n"
                    + "\n"
                    + "Eça de Queiroz, in 'Distrito de Évora (1867)");
            database.sendMessage(g.getId(), "marina", "A Falsa Sabedoria Política\n"
                    + "É reduzido o número daqueles que vêem com os seus próprios olhos e sentem com o próprio coração. Mas da sua força dependerá que os homens tendam ou não a cair no estado amorfo para onde parece caminhar hoje uma multidão cega.\n"
                    + "Quem dera que os povos vissem a tempo, quanto terão de sacrificar da sua liberdade para escapar à luta de todos contra todos! A força da consciência e do espírito internacional demonstrou ser demasiado fraca. Apresenta-se agora superficialmente enfraquecida para consentir a formação de pactos com os mais perigosos inimigos da civilização. Existe, assim, uma espécie de compromisso, criminoso para a Humanidade, embora o considerem como sabedoria política.\n"
                    + "Não podemos desesperar dos homens, pois nós próprios somos homens.\n"
                    + "\n"
                    + "Albert Einstein, in 'Como Vejo o Mundo'");
            database.sendMessage(g.getId(), "ldpereira", "Eaê!");
            database.sendMessage(g.getId(), "gbieging", "Eaê!");
            database.sendMessage(g.getId(), "fulano", "Eaê!");
            database.sendMessage(g.getId(), "deltrano", "Eaê!");
            database.sendMessage(g.getId(), "gbieging", "Quantas mulheres são necessárias pra quebrar um país?");
            database.sendMessage(g.getId(), "gbieging", "Só dilma.");
            database.sendMessage(g.getId(), "fulano", "kkkkkkkkkkkkkkk");
            database.sendMessage(g.getId(), "deltrano", "kkkkkkkkkkkkk");

            g = database.groups.get(database.addGroup("Programação", "gbieging", "Fórum sobre programação"));

            g.addUser(database.users.get("sicrano"));
            g.addUser(database.users.get("afurtado"));
            g.addUser(database.users.get("fulano"));
            g.addUser(database.users.get("deltrano"));

            database.sendMessage(g.getId(), "fulano", "C#!");
            database.sendMessage(g.getId(), "deltrano", "Java!");
            database.sendMessage(g.getId(), "sicrano", "LISP!");

            g = database.groups.get(database.addGroup("Futebol", "fulano", "Fórum sobre futebol"));

            g.addUser(database.users.get("sicrano"));
            g.addUser(database.users.get("dona maria"));
            g.addUser(database.users.get("deltrano"));

            database.sendMessage(g.getId(), "fulano", "Vasco!");
            database.sendMessage(g.getId(), "sicrano", "Flamingo!");
            database.sendMessage(g.getId(), "fulano", "GOOOL!");
            database.sendMessage(g.getId(), "sicrano", "GOOOL!");

            g = database.groups.get(database.addGroup("Jardinagem", "marina", "Fórum sobre jardinagem"));

            g.addUser(database.users.get("sicrano"));
            g.addUser(database.users.get("fulano"));
            g.addUser(database.users.get("deltrano"));

            database.sendMessage(g.getId(), "dona maria", "Gosto de plantar jabuticabeiras");
            database.sendMessage(g.getId(), "fulano", "Rosas");
            database.sendMessage(g.getId(), "deltrano", "eu como terra!");
            database.sendMessage(g.getId(), "sicrano", "legal");

            
            
        }
        return database;
    }

    public boolean addUser(User user) {
        return getUsers().put(user.getUsername(), user) == null;
    }

    public User getUser(String username, String password) throws IllegalArgumentException {
        User user = getUsers().get(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public User createUser(String username, String password, String email) throws IllegalArgumentException {
        if (getUsers().containsKey(username)) {
            throw new IllegalArgumentException("Usuário existente!");
        }
        User user = new User(username, email, password);
        addUser(user);
        return user;
    }

    public int addGroup(String title, String username, String description) {
        Group group = new Group(++idGroup, title, username, description);
        getGroups().put(group.getId(), group);
        return group.getId();
    }

    public Map<String, User> getUsers() {
        if (users == null) {
            users = new HashMap<String, User>();
        }
        return users;
    }

    public Map<Integer, Group> getGroups() {
        if (groups == null) {
            groups = new HashMap<Integer, Group>();
        }
        return groups;
    }

    public Map<Integer, Group> getGroupsUser(String username) {
        Map<Integer, Group> myGroups = new HashMap<Integer, Group>();
        if (groups != null) {
            for (Map.Entry<Integer, Group> entry : groups.entrySet()) {
                Integer integer = entry.getKey();
                Group group = entry.getValue();
                if (username.equals(group.getUserCreate())) {
                    myGroups.put(integer, group);
                }
            }
        }
        return myGroups;
    }

    public Message sendMessage(int group, String username, String message) {
        Message mes = new Message(group, username, message);
        groups.get(group).addMessage(mes);
        return mes;
    }

    public List<Message> getMessages(int group) {
        return this.groups.get(group).getMessages();
    }

    public List<ReportUserGroup> getUsuariosGrupos() {
        List<ReportUserGroup> lista = new ArrayList<ReportUserGroup>();

        for (Group group : groups.values()) {
            ReportUserGroup reportUserGroup = new ReportUserGroup(group.getTitle(), group.getUsers().size());
            lista.add(reportUserGroup);
        }

        return lista;
    }

    public List<ReportMensagem> getMensagensGrupos() {
        List<ReportMensagem> lista = new ArrayList<ReportMensagem>();

        for (Group group : groups.values()) {
            ReportMensagem reportMensagem = new ReportMensagem(group.getTitle(), group.getMessages().size());
            lista.add(reportMensagem);
        }

        return lista;
    }

    public List<ReportMensagem> getMensagensUsuarios() {
        HashMap<String, ReportMensagem> userMensagem = new HashMap<String, ReportMensagem>();

        for (Group group : groups.values()) {
            if (group.getMessages() != null) {
                for (Message message : group.getMessages()) {
                    ReportMensagem report = userMensagem.get(message.getUsername());
                    if (report == null) {
                        report = new ReportMensagem(message.getUsername(), 1);
                    } else {
                        report.add();
                    }
                    userMensagem.put(message.getUsername(), report);
                }
            }
        }

        return new ArrayList(userMensagem.values());
    }
}
