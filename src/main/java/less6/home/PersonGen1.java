package less6.home;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonGen1 {
    Integer obj1;
    Double obj2;
    String obj3;
    Date obj4;
    SimpleDateFormat obj5;

    public Integer getObj1() {
        return obj1;
    }

    public Double getObj2() {
        return obj2;
    }

    public String getObj3() {
        return obj3;
    }

    public Date getObj4() {
        return obj4;
    }

    public SimpleDateFormat getObj5() {
        return obj5;
    }

    public PersonGen1(Integer obj1, Double obj2, String obj3, Date obj4, SimpleDateFormat obj5) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.obj3 = obj3;
        this.obj4 = obj4;
        this.obj5 = obj5;
    }
}
