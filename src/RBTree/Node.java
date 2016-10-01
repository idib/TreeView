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

    public Text GetName() {
        Text f = new Text();
        f.setText(Key.toString());
        f.setX(X + Radius + 5);
        f.setY(Y + Radius / 2);
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
        res.add(new Line(eX - neX - nX, eY - neY - nY, eX - neX, eY - neY));
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
// 100 200 150 500 1000 11 0 16 15 18 17
    private void Fixed() {
        if (C == Color.RED) {
            if (Root == null) {
                ReСolor();
                return;
            }
            if (Root.C == Color.RED) {
                if (Root.Root != null && Root.Root.CountRed() == 2) {
                    Root.Root.ReColorChildren();
                } else {
                    if (Root.Root.Left != null && Root.Root.Left.C == Color.RED) {
                        if (Root.Right != null && Root.Right.C == Color.RED) {
                            Root.LeftRotation();
                            Left.Fixed();
                        } else {
                            Root.ReСolor();
                            Root.Root.ReСolor();
                            Root.Root.RightRotation();
                        }
                    } else {
                        if (Root.Root.Right != null && Root.Root.Right.C == Color.RED) {
                            if (Root.Left != null && Root.Left.C == Color.RED) {
                                Root.RightRotation();
                                Right.Fixed();
                            } else {
                                Root.ReСolor();
                                Root.Root.ReСolor();
                                Root.Root.LeftRotation();
                            }
                        }
                    }
                }
            }
        }
        if (Root != null)
            Root.Fixed();
    }

    public void Insert(K key, V value) {
        if (key.compareTo(Key) >= 0) {
            if (Right != null) {
                Right.Insert(key, value);
                return;
            } else {
                Right = new Node<>(key, value, this);
                Right.Fixed();
            }
        } else {
            if (Left != null) {
                Left.Insert(key, value);
                return;
            } else {
                Left = new Node<>(key, value, this);
                Left.Fixed();
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
        if (Root == null) {
            K k = Key;
            Key = Right.Key;
            V v = Value;
            Value = Right.Value;
            C = Color.BLACK;
            Node<K, V> L = Left;
            Node<K, V> R = Right;
            Right = R.Right;
            Right.Root = this;
            Left = new Node<K, V>(k, v, Color.RED);
            Left.Root = this;
            Left.Left = L;
            Left.Right = R.Left;
        } else {
            if (Root.Left == this)
                Root.Left = Right;
            else
                Root.Right = Right;
            Right.Root = Root;
            Root = Right;
            Node<K, V> s;
            s = Right.Left;
            Right.Left = this;
            Right = s;
        }
    }

    private void RightRotation() {
        if (Root == null) {
            K k = Key;
            Key = Left.Key;
            V v = Value;
            Value = Left.Value;
            Node<K, V> L = Left;
            Node<K, V> R = Right;
            Left = L.Left;
            Right = new Node<K, V>(k, v, Color.RED);
            Right.Left = L.Right;
            Right.Right = R;
        } else {
            if (Root.Left == this)
                Root.Left = Left;
            else
                Root.Right = Left;
            Left.Root = Root;
            Root = Left;
            Node<K, V> s;
            s = Left.Right;
            Left.Right = this;
            Left = s;
        }
    }
}