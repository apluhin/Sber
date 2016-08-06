package less6.home;

public class PersonGen<E, T, K> {
    E obj1;
    T obj2;
    K obj3;

    public E getObj1() {
        return obj1;
    }

    public void setObj1(E obj1) {
        this.obj1 = obj1;
    }

    public T getObj2() {
        return obj2;
    }

    public void setObj2(T obj2) {
        this.obj2 = obj2;
    }

    public K getObj3() {
        return obj3;
    }

    public void setObj3(K obj3) {
        this.obj3 = obj3;
    }

    public PersonGen(E obj1, T obj2, K obj3) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.obj3 = obj3;
    }
}
