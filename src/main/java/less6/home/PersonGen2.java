package less6.home;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonGen2 {
    Number obj1;
    Number obj2;
    Object obj3;
    Object obj4;
    SimpleDateFormat obj5;

    public void setObj1(Number obj1) {
        this.obj1 = obj1;
    }

    public void setObj2(Number obj2) {
        this.obj2 = obj2;
    }

    public void setObj3(Object obj3) {
        this.obj3 = obj3;
    }

    public void setObj4(Date obj4) {
        this.obj4 = obj4;
    }

    public void setObj4(String a) {
        this.obj4 = a;
    }



    public void setObj5(SimpleDateFormat obj5) {
        this.obj5 = obj5;
    }

    public PersonGen2(Number obj1, Number obj2, Object obj3, Date obj4, SimpleDateFormat obj5) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.obj3 = obj3;
        this.obj4 = obj4;
        this.obj5 = obj5;
    }

    public Number getObj1() {
        return obj1;
    }

    public Number getObj2() {
        return obj2;
    }

    public Object getObj3() {
        return obj3;
    }

    public Object getObj4() {
        return obj4;
    }

    public SimpleDateFormat getObj5() {
        return obj5;
    }


}
