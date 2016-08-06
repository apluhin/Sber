package less6.home;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        Stream.of(from.getClass().getMethods()).filter(s -> s.getName().startsWith("get")).forEach(s -> workMethod(from, to, s));
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

    }

    public static void main(String[] args) {
        PersonGen<String, Object, Integer> from = new PersonGen<>("String", new Object(), 1);
        PersonGen<Object, Object, Number> to = new PersonGen<>(null, null, null);
        assign(to, from);
        System.out.println(from.getObj1().equals(to.getObj1()) + " " + to.getObj1());
        System.out.println(from.getObj2().equals(to.getObj2()) + " " + to.getObj2());
        System.out.println(from.getObj3().equals(to.getObj3()) + " " + to.getObj3());

    }


}
