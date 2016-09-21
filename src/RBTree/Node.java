package RBTree;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.awt.geom.Point2D;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by idib on 20.09.16.
 */
public class Node<K,V> {

    private K Key;
    private V Value;
    private Color C;

    private Node<K,V> Root;
    private Node<K,V> Left;
    private Node<K,V> Right;

    private double X;
    private double Y;

    public static double Radius;

    public Node() {
        X = 0; Y = 0;
        C = Color.RED;
    }

    public Node(K key, V value, Color c) {
        X = 0; Y = 0;
        Key = key;
        Value = value;
        C = c;
    }

    public K getKey() {
        return Key;
    }

    public void setKey(K key) {
        Key = key;
    }

    public V getValue() {
        return Value;
    }

    public void setValue(V value) {
        Value = value;
    }

    public Color getReСolor() {
        return C;
    }

    public void setReСolor(Color c) {
        if(c.equals(Color.RED) || c.equals(Color.BLACK))
            C = c;
    }

    public void ReСolor(){
        if (C.equals(Color.RED))
            C = Color.BLACK;
        else
            C = Color.RED;
    }

    public Node<K, V> getRoot() {
        return Root;
    }

    public void setRoot(Node<K, V> root) {
        Root = root;
    }

    public Node<K, V> getLeft() {
        return Left;
    }

    public void setLeft(Node<K, V> left) {
        Left = left;
    }

    public Node<K, V> getRight() {
        return Right;
    }

    public void setRight(Node<K, V> right) {
        Right = right;
    }

    public boolean TryChildren(){
        return Left!=null || Right!=null;
    }

    public boolean TryLeft(){
        return Left != null;
    }

    public boolean TryRight(){
        return Right != null;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public Ellipse getEllipse(){
        Ellipse s = new Ellipse();
        s.setCenterX(X);
        s.setCenterY(Y);
        s.setRadiusX(Node.Radius);
        s.setRadiusY(Node.Radius);
        return s;
    }

    public ArrayList<Line> getLinePoint(){
        ArrayList<Line> res = new ArrayList<>();
        double eX = getLeft().getX();
        double eY = getLeft().getY();
        double deg = Math.atan2(eY-Y,eX-X);
        double neX = Node.Radius * Math.cos(deg);
        double neY = Node.Radius * Math.sin(deg);
        res.add(new Line(eX + neX, eY - neY, X - neX, Y + neY));
        res.add(new Line(eX + neX, eY - neY, X - neX, Y + neY));
        return  res;
    }
}
