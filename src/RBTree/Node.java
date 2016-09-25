package RBTree;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by idib on 20.09.16.
 */

public class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {

    private K Key;
    private V Value;
    private Color C;

    private Node<K, V> Root;
    private Node<K, V> Left;
    private Node<K, V> Right;

    private double X;
    private double Y;

    public static double Radius = 10;

    public Node() {
        X = 0;
        Y = 0;
        C = Color.RED;
    }

    public Node(K key, V value) {
        X = 0;
        Y = 0;
        Key = key;
        Value = value;
        C = Color.RED;
    }

    public Node(K key, V value, Node<K, V> root) {
        X = 0;
        Y = 0;
        Key = key;
        Value = value;
        Root = root;
        C = Color.RED;
    }

    public Node(K key, V value, Color c) {
        X = 0;
        Y = 0;
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

    public Color getСolor() {
        return C;
    }

    public void setСolor(Color c) {
        if (c.equals(Color.RED) || c.equals(Color.BLACK))
            C = c;
    }

    public void ReСolor() {
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

    public boolean TryChildren() {
        return Left != null || Right != null;
    }

    public boolean TryLeft() {
        return Left != null;
    }

    public boolean TryRight() {
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

    public Ellipse getEllipse() {
        Ellipse s = new Ellipse();
        s.setCenterX(X);
        s.setCenterY(Y);
        s.setRadiusX(Node.Radius);
        s.setRadiusY(Node.Radius);
        s.setFill(C);
        return s;
    }

    public Text GetName(){
        Text f = new Text();
        f.setText(Key.toString());
        f.setX(X + Radius + 5);
        f.setY(Y + Radius/2);
        return f;
    }

    public ArrayList<Shape> getShape() {
        ArrayList<Shape> res = new ArrayList<>();
        res.add(getEllipse());
        res.addAll(getLinePoint());
        res.add(GetName());
        return res;
    }

    private ArrayList<Line> getLP(double sX, double sY, double eX, double eY) {
        ArrayList<Line> res = new ArrayList<>();
        double deg = Math.atan2(eY - Y, eX - X);
        double neX = Node.Radius * Math.cos(deg);
        double neY = Node.Radius * Math.sin(deg);
        res.add(new Line(eX - neX, eY - neY, sX + neX, sY + neY));

        deg -= Math.PI / 6;
        double nX = Node.Radius * Math.cos(deg);
        double nY = Node.Radius * Math.sin(deg);
        res.add(new Line(eX - neX - nX, eY - neY - nY, eX - neX, eY - neY));

        deg += Math.PI / 3;
        nX = Node.Radius * Math.cos(deg);
        nY = Node.Radius * Math.sin(deg);
        res.add(new Line( eX - neX - nX, eY - neY - nY, eX - neX, eY - neY));
        return res;
    }

    public ArrayList<Line> getLinePoint() {
        ArrayList<Line> res = new ArrayList<>();
        if (Left != null)
            res.addAll(getLP(X, Y, Left.X, Left.Y));
        if (Right != null)
            res.addAll(getLP(X, Y, Right.X, Right.Y));
        return res;
    }

    public void Insert(K key, V value, Node<K, V> root) {
        if (key.compareTo(Key) >= 0) {
            if (Right != null) {
                Right.Insert(key, value, this);
            } else {
                Right = new Node<>(key, value);
            }
        } else {
            if (Left != null) {
                Left.Insert(key, value, this);
            } else {
                Left = new Node<>(key, value);
            }
        }
        if (C == Color.RED) {
            int r = root.CountRed();
            if (r == 2) {
                root.ReColorChildren();
                if (Left.C == Color.RED) {
                    Left.ReСolor();
                } else {
                    Right.ReСolor();
                }
            } else {
                if (root.Left!=null && root.Left.C == Color.RED) {
                    if (Right.C == Color.RED)
                        LeftRotation();
                    ReСolor();
                    Root.ReСolor();
                    root.RightRotation();
                } 
                if (root.Right!=null && root.Right.C == Color.RED){
                    if (Left.C == Color.RED)
                        RightRotation();
                    ReСolor();
                    Root.ReСolor();
                    root.LeftRotation();
                }
            }
        }
    }

    @Override
    public int compareTo(Node<K, V> o) {
        return Key.compareTo(o.Key);
    }

    private int CountRed() {
        int r = 0;
        if (Left != null && Left.C == Color.RED) r++;
        if (Right != null && Right.C == Color.RED) r++;
        return r;
    }

    private void ReColorChildren() {
        ReСolor();
        Left.ReСolor();
        Right.ReСolor();
    }

    private void LeftRotation() {
        // TODO: 24.09.16
    }

    private void RightRotation() {
        // TODO: 24.09.16
    }
}