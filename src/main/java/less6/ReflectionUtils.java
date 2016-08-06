package less6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.stream.Stream;

public class ReflectionUtils {

    public static void main(String[] args) {
        printHierarchy("a");
        printAllMethods(new Date());
    }

    public static void printHierarchy(Object o) {
        Class c = o.getClass();
        while (c != null) {
            System.out.println(c.getName());
            c = c.getSuperclass();
        }
    }


    public static void printAllMethods(Object o) {

        Class c = o.getClass();
        while (c != null) {
            Stream.of(c.getDeclaredMethods()).map(s -> isGet(s) ? s : null).filter(s -> s != null).forEach(s -> workMethod(s, o));
            c = c.getSuperclass();
        }

    }

    private static void workMethod(Method s, Object o) {
        try {
            if (s.getParameterCount() > 0) {
                return;
            }
            s.setAccessible(true);
            System.out.print(s.getName() + "    ");
            System.out.println(s.invoke(o));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static boolean isGet(Method s) {
        return s.getName().startsWith("get") &&
                !s.getName().substring(3, 4).equals(s.getName().substring(3, 4).toLowerCase());
    }


}
