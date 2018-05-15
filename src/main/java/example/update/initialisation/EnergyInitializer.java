package example.update.initialisation;

import example.update.constraints.energy.Battery;
import example.update.strategies.Energy;
import example.update.constraints.energy.SimpleEnergy;
import peersim.config.Configuration;
import peersim.core.Control;
import peersim.core.Network;
import peersim.core.Node;


/**
 * Created by jibou on 20/10/17.
 */
public class EnergyInitializer implements Control {

    // ------------------------------------------------------------------------
    // Parameters
    // ------------------------------------------------------------------------
    /**
     * The protocol to operate on.
     *
     * @config
     */
    private static final String PAR_PROT = "protocol";

    // ------------------------------------------------------------------------
    // Fields
    // ------------------------------------------------------------------------

    /** Protocol identifier, obtained from config property {@link #PAR_PROT}. */
    private final int pid;

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * Standard constructor that reads the configuration parameters. Invoked by
     * the simulation engine.
     *
     * @param prefix
     *            the configuration prefix for this class.
     */
    public EnergyInitializer(String prefix) {

        pid = Configuration.getPid(prefix + "." + PAR_PROT);
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    /**
     * Initialize the energy state to true in the SimpleEnergy Protocol.
     * -> "power on"the nodes
     */
    public boolean execute() {

        Node n ;
        Energy protocol;

        for (int i = 0; i < Network.size(); i++) {
            n = Network.get(i);
            protocol = (Energy) n.getProtocol(pid);

            //50% on battery, 50% on AC power.
            if (i%2 == 0 ) {
                protocol.setPowerSource( new Battery() );
                protocol.getPowerSource().setOnlineStatus(true);
            } else {
                protocol.setPowerSource( new SimpleEnergy("") );
                protocol.getPowerSource().charge(100);
            }
        }
        return false;
    }

}