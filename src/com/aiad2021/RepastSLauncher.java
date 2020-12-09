package com.aiad2021;

import com.aiad2021.Agents.Auction;
import com.aiad2021.Agents.User;
import jade.core.Profile;
import jade.core.ProfileImpl;
import sajas.core.Runtime;
import sajas.wrapper.ContainerController;
import sajas.sim.repast3.Repast3Launcher;
import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.gui.Object2DDisplay;
import uchicago.src.sim.space.Object2DGrid;
import uchicago.src.sim.space.Object2DTorus;

import java.awt.*;
import java.util.ArrayList;

public class RepastSLauncher extends Repast3Launcher {

    private ContainerController mainContainer;
    private Runtime rt;
    private Profile profile;

    private ArrayList<Auction> auctionsList;
    private ArrayList<User> usersList;

    private DisplaySurface dsurf;
    private Object2DTorus space;

    private OpenSequenceGraph plot;

    @Override
    protected void launchJADE() {

        //This will start JADE Gui
        rt = Runtime.instance();
        profile = new ProfileImpl();
        profile.setParameter(Profile.GUI, "true");

        mainContainer = rt.createMainContainer(profile);
        
    }

    @Override
    public String[] getInitParam() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "Auction Model";
    }

    @Override
    public void setup() {
        super.setup();  // crucial!

        // property descriptors
        // ...
    }

    public int getWinningBid(){
        return (int) auctionsList.get(0).getWinningPrice();
    }

    @Override
    public void begin() {
        super.begin();  // crucial!
        buildModel();
        buildDisplay();
        buildSchedule();

        //build simulation ambient
        Simulation sim = new Simulation(mainContainer,plot,space);
        sim.sim1(this.usersList,this.auctionsList);

    }

    private void buildModel(){
        // build model
        usersList= new ArrayList<>();
        auctionsList = new ArrayList<>();

        if (plot != null) plot.dispose();
        plot = new OpenSequenceGraph("Population", this);
        plot.setAxisTitles("World cycles", "# of people");

        plot.addSequence("Green - CC", this::getWinningBid, Color.GREEN, 10);
        plot.display();

        space = new Object2DTorus(500,1000);

        //space.putObjectAt(...
        //usersList.add(...)
    }

    private void buildSchedule(){
        getSchedule().scheduleActionAtInterval(1, dsurf, "updateDisplay", Schedule.LAST);
        getSchedule().scheduleActionAt(100, plot, "step", Schedule.LAST);
        //getSchedule().execute();

    }

    private void buildDisplay(){

        if (dsurf != null) dsurf.dispose();
        this.dsurf = new DisplaySurface(this,"Auction Simulation");
        registerDisplaySurface("Auction Simulation",dsurf);

        // space and display surface
        Object2DDisplay display = new Object2DDisplay(space);
        display.setObjectList(auctionsList);
        dsurf.addDisplayableProbeable(display, "Agents Space");
        addSimEventListener(dsurf);
        dsurf.display();


    }
}
