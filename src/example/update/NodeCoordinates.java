package example.update;

import peersim.core.Node;
import peersim.core.Protocol;

/**
 * <p>
 * This class runs into each nodes and
 * simply stores the node's location, as x;y coordinates.
 * </p>
 */

public class NodeCoordinates implements Protocol {

    /** 2d coordinates */
    private int x, y;

    private long nodeId;

    String prefix;
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    /**
     * Standard constructor that reads the configuration parameters. Invoked by
     * the simulation engine. By default, all the coordinates components are set
     * to -1 value. The {@link InetInitializer} class provides a coordinates
     * initialization.
     *
     * @param prefix
     *            the configuration prefix for this class.
     */
    public NodeCoordinates(String prefix) {
        /* Un-initialized coordinates defaults to -1. */
        x = y = -1;
        this.prefix = prefix;
    }

    public Object clone() {
            return  new NodeCoordinates(this.prefix);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    /**
     * Return the distance between two nodes.
     * The distance is computed between the current node and
     * the one given.
     *
     * @param coord
     *            The NodeCoordinates protocol running on target node.
     */
    public int getDistance(NodeCoordinates coord){
        double distance=0;

        distance=Math.sqrt(Math.pow((this.getX()-coord.getX()),2)+
                    Math.pow((this.getY()-coord.getY()),2));

        return (int)distance;
    }

}