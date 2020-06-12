package in.example.oopsrestaurant;
import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {

    String n;
    String p;
    double tp;
    String uid;
    String v;

    public Cart(){

    }
    public Cart(String n, String p, Double tp, String uid, String v) {
        this.n = n;
        this.p = v;
        this.tp = tp;
        this.uid = uid;
        this.v = v;
    }

    public Cart(Parcel in) {
        n = in.readString();
        p = in.readString();
        tp = in.readDouble();
        uid = in.readString();
        v = in.readString();
    }

        public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getTp() {

        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(n);
        parcel.writeString(p);
        parcel.writeDouble(tp);
        parcel.writeString(uid);
        parcel.writeString(v);
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };
}

