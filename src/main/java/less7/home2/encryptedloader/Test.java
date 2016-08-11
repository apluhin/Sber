package less7.home2.encryptedloader;

import java.io.File;


public class Test {
    public static void main(String[] args) throws Exception {
        Encrypter encrypter = new Encrypter(new File("/Users/aleksejpluhin/IdeaProjects/Sber/src/main/java/less7/home2/encryptedloader/HelloWorldComplile")
                , "asdlskadl;akd");

        encrypter.encrypt("HelloWorld.class");


        Class<?> a = new EncryptedClassLoader("asdlskadl;akd", new File("/Users/aleksejpluhin/Desktop"), null)
                .loadClassFromFile("HelloWorld.txt");
        Object o = a.newInstance();
        o.getClass().getMethod("hello").invoke(o);
    }
}
