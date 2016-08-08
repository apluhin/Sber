package less6.home;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;


public class BeanUtils {

    static long a;

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
        a = System.currentTimeMillis();
        Stream.of(from.getClass().getMethods()).parallel().filter(s -> s.getDeclaringClass().equals(from.getClass()))
                .filter(s -> s.getName().startsWith("get") && Character.isUpperCase(s.getName().charAt(3)))
                .forEach(s -> workMethod(from, to, s));
    }

    private static void workMethod(Object from, Object to, Method s) {
        String nameMethod = "set" + s.getName().substring(3, s.getName().length());
        try {
            Class returnType = s.getReturnType();  /// String return type from
            Method m = null;
            while (returnType != null) {
                try {

                    m = to.getClass().getMethod(nameMethod, returnType);

                    break;
                } catch (NoSuchMethodException e) {
                    returnType = returnType.getSuperclass();
                }

            }
            if (m == null) {
            } else {
                m.invoke(to, s.invoke(from));

            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(System.currentTimeMillis() - a);

    }

    public static void main(String[] args) {
        PersonGen1 from = new PersonGen1(1, 1d, "test", new Date(), new SimpleDateFormat());
        PersonGen2 to = new PersonGen2(null, null, null, null, null);
        assign(to, from);
        System.out.println(System.currentTimeMillis() - a + " end");
        System.out.println(from.getObj1().equals(to.getObj1()) + " " + to.getObj1());
        System.out.println(from.getObj2().equals(to.getObj2()) + " " + to.getObj2());
        System.out.println(from.getObj3().equals(to.getObj3()) + " " + to.getObj3());
        System.out.println(from.getObj4().equals(to.getObj4()) + " " + to.getObj4());
        System.out.println(from.getObj5().equals(to.getObj5()) + " " + to.getObj5());

    }


}
