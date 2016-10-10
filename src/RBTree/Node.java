package RBTree;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by idib on 20.09.16.
 */

public class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {

    private K Key;
    private V Value;
    private Color C;
    private boolean Nil = false;

    private Node<K, V> Root;
    private Node<K, V> Left;
    private Node<K, V> Right;
    public Tree<K, V> T;

    private double X;
    private double Y;

    public static double Radius = 10;

    public Node() {
        X = 0;
        Y = 0;
        C = Color.RED;
    }

    public Node(boolean t) {
        if (t) {
            Nil = true;
            C = Color.BLACK;
        } else {
            X = 0;
            Y = 0;
            C = Color.RED;
        }
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

    public Node(K key, V value, Node<K, V> root, Node<K, V> n) {
        X = 0;
        Y = 0;
        Key = key;
        Value = value;
        Root = root;
        C = Color.RED;
        Right = n;
        Left = n;
    }

    public Node(K key, V value, Color c) {
        X = 0;
        Y = 0;
        Key = key;
        Value = value;
        C = c;
    }

    public Node(K key, V value, Color c, Node<K, V> n) {
        X = 0;
        Y = 0;
        Key = key;
        Value = value;
        C = c;
        Right = n;
        Left = n;
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
        if (Left.Nil)
            return null;
        else
            return Left;
    }

    public Node<K, V> getRight() {
        if (Right.Nil)
            return null;
        else
            return Right;
    }

    public boolean TryLeft() {
        return !Left.Nil;
    }

    public boolean TryRight() {
        return !Right.Nil;
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
        Text f = new Text();
        if (Root != null) {
            f.setText(Root.Key.toString());
        } else {

            f.setText("nil");
        }
        f.setX(X + Radius + 5);
        f.setY(Y + Radius * 1.5);
        res.add(f);
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
        if (TryLeft())
            res.addAll(getLP(X, Y, Left.X, Left.Y));
        if (TryRight())
            res.addAll(getLP(X, Y, Right.X, Right.Y));
        return res;
    }

    private void Fixed(Node<K, V> z) {
        Node<K, V> y;
        while (z.Root != null && z.Root.C == Color.RED) {
            if (z.Root == z.Root.Root.Left) {
                y = z.Root.Root.Right;
                if (y.C == Color.RED) {
                    z.Root.C = Color.BLACK;
                    y.C = Color.BLACK;
                    z.Root.Root.C = Color.RED;
                    z = z.Root.Root;
                } else {
                    if (z == z.Root.Right) {
                        z = z.Root;
                        z.LeftRotation();
                    }
                    z.Root.C = Color.BLACK;
                    z.Root.Root.C = Color.RED;
                    z.Root.Root.RightRotation();
                }
            } else {
                y = z.Root.Root.Left;
                if (y.C == Color.RED) {
                    z.Root.C = Color.BLACK;
                    y.C = Color.BLACK;
                    z.Root.Root.C = Color.RED;
                    z = z.Root.Root;
                } else {
                    if (z == z.Root.Left) {
                        z = z.Root;
                        z.RightRotation();
                    }
                    z.Root.C = Color.BLACK;
                    z.Root.Root.C = Color.RED;
                    z.Root.Root.LeftRotation();
                }
            }
        }
        while (z.Root != null)
            z = z.Root;
        z.C = Color.BLACK;
    }

    private void Transplant(Node<K, V> a, Node<K, V> b) {
        if (a.Root == null) {
            a.T.setRoot(b);
        } else if (a == a.Root.Left) {
            a.Root.Left = b;
        } else {
            a.Root.Right = b;
        }
        b.Root = a.Root;
    }

    public void Insert(K key, V value) {
        if (key.compareTo(Key) >= 0) {
            if (Right.Nil) {
                Right = new Node<>(key, value, this, Right);
                Fixed(Right);
            } else {
                Right.Insert(key, value);
                return;
            }
        } else {
            if (Left.Nil) {
                Left = new Node<>(key, value, this, Left);
                Fixed(Left);
            } else {
                Left.Insert(key, value);
                return;
            }
        }
    }

    @Override
    public int compareTo(Node<K, V> o) {
        return Key.compareTo(o.Key);
    }

    public Node<K, V> Min() {
        if (Left.Nil)
            return this;
        else
            return Left.Min();
    }

    public Node<K, V> Max() {
        if (Right.Nil)
            return this;
        else
            return Right.Max();
    }

    public void Delete(K key) {
        int r = key.compareTo(Key);
        if (r == 0) {
            Node<K, V> y = this;
            Color OC = y.C;
            Node<K, V> x;
            if (Left.Nil) {
                x = Right;
                x.Root = Right;
                Transplant(this, Right);
            } else if (Right.Nil) {
                x = Left;
                x.Root = Left;
                Transplant(this, Left);
            } else {
                y = Right.Min();
                OC = y.C;
                x = y.Right;
                x.Root = y;
                if (y.Root != this) {
                    Transplant(y, y.Right);
                    y.Right = Right;
                    y.Right.Root = y;
                }
                Transplant(this, y);
                y.Left = Left;
                y.Left.Root = y;
                y.C = C;
            }
            if (OC == Color.BLACK) {
                DelFix(x);
            }
            return;
        } else {
            if (r > 0) {
                if (Right != null) {
                    Right.Delete(key);
                    return;
                }
            } else {
                if (Left != null) {
                    Left.Delete(key);
                    return;
                }
            }
        }
    }

    private void DelFix(Node<K, V> x) {
        Node<K, V> w = new Node<K, V>();
        while (x.Root != null && x.C == Color.BLACK) {
            if (x == x.Root.Left) {
                w = x.Root.Right;
                if (w.C == Color.RED) {
                    w.C = Color.BLACK;
                    x.Root.C = Color.RED;
                    x.Root.LeftRotation();        // try;
                    w = x.Root.Right;
                }
                if (w.Left.C == Color.BLACK && w.Right.C == Color.BLACK) {
                    w.C = Color.RED;
                    x = x.Root;
                } else {
                    if (w.Right.C == Color.BLACK) {
                        w.Left.C = Color.BLACK;
                        w.C = Color.RED;
                        w.RightRotation();
                        w = x.Root.Right;
                    }
                    w.C = x.Root.C;
                    x.Root.C = Color.BLACK;
                    w.Right.C = Color.BLACK;
                    x.Root.LeftRotation();
                    while (x.Root != null)
                        x = x.Root;
                }
            } else {
                w = x.Root.Left;
                if (w.C == Color.RED) {
                    w.C = Color.BLACK;
                    x.Root.C = Color.RED;
                    x.Root.LeftRotation();        // try;
                    w = x.Root.Left;
                }
                if (w.Right.C == Color.BLACK && w.Left.C == Color.BLACK) {
                    w.C = Color.RED;
                    x = x.Root;
                } else {
                    if (w.Left.C == Color.BLACK) {
                        w.Right.C = Color.BLACK;
                        w.C = Color.RED;
                        w.LeftRotation();
                        w = x.Root.Left;
                    }
                    w.C = x.Root.C;
                    x.Root.C = Color.BLACK;
                    w.Left.C = Color.BLACK;
                    x.Root.RightRotation();
                    while (x.Root != null)
                        x = x.Root;
                }
            }
        }
        x.C = Color.BLACK;
    }

    private void LeftRotation() {
        Node<K, V> y = Right;
        Right = y.Left;
        if (!y.Left.Nil)
            y.Left.Root = this;
        y.Root = Root;
        if (Root == null) {
            T.setRoot(y);
            y.T = T;
            T = null;
        } else if (this == Root.Left)
            Root.Left = y;
        else
            Root.Right = y;
        y.Left = this;
        Root = y;
    }

    private void RightRotation() {
        Node<K, V> y = Left;
        Left = y.Right;
        if (!y.Right.Nil)
            y.Right.Root = this;
        y.Root = Root;
        if (Root == null) {
            T.setRoot(y);
            y.T = T;
            T = null;
        } else if (this == Root.Right)
            Root.Right = y;
        else
            Root.Left = y;
        y.Right = this;
        Root = y;
    }
}