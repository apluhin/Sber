package less7.home2.encryptedloader;

import java.io.File;


public class Test {
    public static void main(String[] args) throws Exception {
        Encrypter encrypter = new Encrypter(new File("/Users/aleksejpluhin/IdeaProjects/Sber/src/main/java/less7/home2/encryptedloader/HelloWorldComplile")
                , "abasdksadijsaldhasdpoasuduasydhjkasdas;jdpsaojdpaosjdc");
//
        encrypter.encrypt("HelloWorld.class");


        Class<?> a = new EncryptedClassLoader("abasdksadijsaldhasdpoasuduasydhjkasdas;jdpsaojdpaosjdc", new File("/Users/aleksejpluhin/IdeaProjects/Sber/src/main/java/less7/home2/encryptedloader/HelloWorldComplile"), null)
                .loadClassFromFile("HelloWorld.txt");
        Object o = a.newInstance();
        o.getClass().getMethod("hello").invoke(o);
    }
}
