package in.example.oopsrestaurant;

public class User {

    String n;
    String p;
    String d;
    String vn;
    String uid;

    public User(String n, String p, String d, String vn, String uid) {
        this.n = n;
        this.p = p;
        this.d = d;
        this.vn = vn;
        this.uid = uid;
    }

    public String getN() {
        return n;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }
}
