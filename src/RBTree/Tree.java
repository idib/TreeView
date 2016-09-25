package RBTree;

import javafx.scene.paint.Color;

import java.security.PublicKey;

/**
 * Created by idib on 20.09.16.
 */
public class Tree<K extends Comparable<K>,V> {
    private Node<K,V> Root;

    private double distX = 45;
    private double distY = 45;
    private double sX = 0;
    private double sY = 0;

    public Tree(){

    }

    public Node<K, V> getRoot() {
        return Root;
    }

    public void setRoot(Node<K, V> root) {
        Root = root;
    }

    public void refreshXY(){
        refreshX(Root, sX);
        refreshY(Root, sY);
    }

    private double refreshX(Node<K,V> n, double curX){
        double s = curX;
        if(n.TryLeft())
            s = refreshX(n.getLeft(),s);
        n.setX(s);
        if(n.TryRight())
            return refreshX(n.getRight(),s + distX);
        else
            return s + distX;
    }

    private void refreshY(Node<K,V> n, double curY){
        n.setY(curY);
        if(n.TryLeft())
            refreshY(n.getLeft(), curY + distY);
        if(n.TryRight())
            refreshY(n.getRight(),curY + distY);
    }

    public double getDistX() {
        return distX - 2 * Node.Radius;
    }

    public void setDistX(double distX) {
        this.distX = distX + 2 * Node.Radius;
    }

    public double getDistY() {
        return distY - 2 * Node.Radius;
    }

    public void setDistY(double distY) {
        this.distY = distY + 2 * Node.Radius;
    }

    public double getSX() {
        return sX;
    }

    public void setSX(double sX) {
        this.sX = sX;
    }

    public double getSY() {
        return sY;
    }

    public void setSY(double sY) {
        this.sY = sY;
    }

    public void Insert(K key, V value) {
        if(Root == null)
            Root = new Node<K, V>(key,value, Color.BLACK);
        else
            Root.Insert(key,value, null);
    }
}
