package less6.home;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;


public class BeanUtils {


    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Class setClass = to.getClass();
        Stream.of(setClass.getMethods()).filter(s -> s.getDeclaringClass().equals(setClass) &&
                s.getName().startsWith("set") && Character.isUpperCase(s.getName().charAt(3)))
                .forEach(s -> workMethod(from, to, s));
    }

    private static void workMethod(Object from, Object to, Method s) {
        String nameMethod = "get" + s.getName().substring(3, s.getName().length());
        try {
            Method m = from.getClass().getMethod(nameMethod);
            if (m != null && isSubClass(m.getReturnType(), s.getParameters())) {
                s.invoke(to, m.invoke(from));
            }

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }


    }

    private static boolean isSubClass(Class<?> returnType, Parameter[] parameters) {
        if (parameters.length > 1) {
            return false;
        }
        while (returnType != null) {
            if (returnType == parameters[0].getType()) {
                return true;
            } else {
                returnType = returnType.getSuperclass();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PersonGen1 from = new PersonGen1(1, 1d, "test", new Date(), new SimpleDateFormat());
        PersonGen2 to = new PersonGen2(1d, 1, new Object(), null, null);
        assign(to, from);
        System.out.println(from.getObj1().equals(to.getObj1()) + " " + to.getObj1());
        System.out.println(from.getObj2().equals(to.getObj2()) + " " + to.getObj2());
        System.out.println(from.getObj3().equals(to.getObj3()) + " " + to.getObj3());
        System.out.println(from.getObj4().equals(to.getObj4()) + " " + to.getObj4());
        System.out.println(from.getObj5().equals(to.getObj5()) + " " + to.getObj5());


    }


}
