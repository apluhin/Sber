package less6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProxyInvocationHandler implements InvocationHandler {

    Object delegate;


    public ProxyInvocationHandler(Object obj) {
        this.delegate = obj;
    }

    public static <T> T getProxy(Object obj) {

        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                obj.getClass().getInterfaces(), new ProxyInvocationHandler(obj));
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Proxy");
        if(method.getName().equals("size")) {
            System.out.println("Unsuported");
            return 0;
        } else {
            return method.invoke(delegate, args);
        }
    }


    public static void main(String[] args) {
        List<Integer> list = ProxyInvocationHandler.getProxy(new HashMap<>());
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        System.out.println(list.size());
    }
}
