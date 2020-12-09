package com.aiad2021;

import com.aiad2021.Agents.Auction;
import com.aiad2021.Agents.User;
import jade.wrapper.StaleProxyException;
import sajas.wrapper.ContainerController;
import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.gui.Object2DDisplay;
import uchicago.src.sim.space.Object2DTorus;

import java.util.ArrayList;

public class Simulation {

    private ContainerController mainContainer;
    private OpenSequenceGraph plot;
    private Object2DTorus space;

    private ArrayList<Auction> auctionsList;
    private ArrayList<User> usersList;

    public Simulation(ContainerController mainContainer, OpenSequenceGraph plot,Object2DTorus space){
        this.mainContainer = mainContainer;
        this.plot= plot;
        this.space=space;
    }

    //autobid 2 agents
    public void sim1(ArrayList<User> usersList, ArrayList<Auction> auctionsList){
        User user1 = new User(1,"JohnDoe",1000);
        User user2 = new User(2,"JohnDoe",1000);
        Auction auction1 = new Auction(1,"english",250,10,5,plot,space);
        usersList.add(user1);
        usersList.add(user2);
        auctionsList.add(auction1);

        try {

            this.mainContainer.acceptNewAgent("1JohnDoe",user1).start();
            this.mainContainer.acceptNewAgent("2JohnDoe",user2).start();
            this.mainContainer.acceptNewAgent("Auction:1",auction1).start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public void sim2(ArrayList<User> usersList, ArrayList<Auction> auctionsList){
        //todo
    }

    public void sim3(ArrayList<User> usersList, ArrayList<Auction> auctionsList){
        //todo
    }

    //...
}
