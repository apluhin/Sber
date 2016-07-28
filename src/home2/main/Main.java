package home2.main;

import home2.person.Person;

/**
 * Created by aleksejpluhin on 25.07.16.
 */
public class Main {

    public static void main(String[] args) {
        Person alex = new Person(true, "Alex");
        Person maria = new Person(false, "Maria");
        Person pavel = new Person(true, "Pavel");
        Person alina = new Person(false, "Alina");

        alex.marry(pavel);
        System.out.println(String.valueOf(alex.getSpouse()));
        alex.marry(maria);
        System.out.println("Alex spouse: " + alex.getSpouse().getName());
        alex.marry(alina);
        System.out.println("Alex spouse: " + alex.getSpouse().getName());
        maria.marry(pavel);
        System.out.println("Maria spouse: " + maria.getSpouse().getName());
        alex.marry(alina);
        System.out.println("Alex spouse: " + alex.getSpouse().getName());
        pavel.marry(maria);
        System.out.println("Alex spouse : " + alex.getSpouse().getName() + ", Maria spouse: " + maria.getSpouse().getName() + ", Pavel spouse: " + pavel.getSpouse().getName()
                + ", Alina spouse: " + alina.getSpouse().getName());
        alex.marry(maria);
        System.out.println("Alex spouse : " + alex.getSpouse().getName() + ", Maria spouse: " + maria.getSpouse().getName() + ", Pavel spouse: " +
                String.valueOf(pavel.getSpouse()) + ", Alina spouse: " + String.valueOf(alina.getSpouse()));

    }
}
