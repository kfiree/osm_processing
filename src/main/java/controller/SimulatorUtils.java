package controller;

import model.graph.Node;
import model.graph.RoadMap;
import model.utils.Coordinates;

import java.util.*;

public class SimulatorUtils {
    public static long hourToMS(double hour){
        return (long) (hour * 3600000);
    }

    /**
     * delete from graph all nodes that aren't part of the largest connected component.
     */
    public static void extractLargestCC(){
        HashSet<Long> largestCC = getLargestCC();
        RoadMap.INSTANCE.removeAllNodesBut(largestCC);
    }

    private static Node pop(HashSet<Node> nodes){
        Iterator<Node> itr = nodes.iterator();
        Node node = itr.next();
        itr.remove();
        return node;
    }
    private static HashSet<Long> getLargestCC(){
        HashSet<Node> nodes = new HashSet<>(RoadMap.INSTANCE.getNodes());
//        Map<Long, Node> nodesMap = new HashMap<>(RoadMap.INSTANCE.getNodesMap());
        Node componentNode = pop(nodes);
        HashSet<Long> largestCC = getCC(componentNode, nodes);

        nodes.removeAll(largestCC);

        while(!nodes.isEmpty() && nodes.size() > largestCC.size()){
            componentNode = pop(nodes);
            HashSet<Long> CC = getCC(componentNode, nodes);
            if(CC.size() > largestCC.size()){
                largestCC = CC;
            }
            nodes.removeAll(CC);
        }

        return largestCC;
    }

    private static HashSet<Long> getCC(Node node, HashSet<Node> nodes){
        HashSet<Long> visited = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();

        queue.add(node);

        while (queue.size() != 0)
        {
            node = queue.poll();
            for(Node adjacent : Objects.requireNonNull(node).getAdjacentNodes()){
                if(!visited.contains(adjacent.getId())){
                    queue.add(adjacent);
                    visited.add(adjacent.getId());
                    nodes.remove(adjacent);
                }
            }
        }
//        LOGGER.fine("BFS from node "+ node.getId());

        return visited;
    }

    public static double distance(Coordinates location1, Coordinates location2){
        return distance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude());
    }
    public static double distance(double lat1, double lon1,double lat2,double lon2){


        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return dist;
    }


    /**
     :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
     ::  This function converts decimal degrees to radians            ::
     :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
     */
    private static double deg2rad(double deg) {
        return deg * Math.PI / 180.0;
    }

    /**
     :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
     ::  This function converts radians to decimal degrees             :
     :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
     */
    private static double rad2deg(double rad) {
        return rad * 180.0 / Math.PI;
    }
}
