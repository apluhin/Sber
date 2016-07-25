/**
 * Created by aleksejpluhin on 25.07.16.
 */
public class Person {

    private final boolean man;

    private final String name;

    private Person spouse;


    public Person(boolean man, String name) {

        this.man = man;

        this.name = name;

    }


    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * <p>
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */

    public boolean marry(Person person) {

        if (this.man != person.man && this.spouse != person) {
            this.divorce();
            person.divorce();
            this.spouse = person;
            person.spouse = this;
            return true;
        }

        return false;
    }


    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */

    public boolean divorce() {
        if (this.spouse != null) {
            this.spouse.spouse = null;
            this.spouse = null;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Person alex = new Person(true, "Alex");
        Person maria = new Person(false, "Maria");
        Person pavel = new Person(true, "Pavel");
        Person alina = new Person(false, "Alina");

        alex.marry(pavel);
        System.out.println(String.valueOf(alex.spouse));
        alex.marry(maria);
        System.out.println("Alex spouse: " + alex.spouse.name);
        alex.marry(alina);
        System.out.println("Alex spouse: " + alex.spouse.name);
        maria.marry(pavel);
        System.out.println("Maria spouse: " + maria.spouse.name);
        alex.marry(alina);
        System.out.println("Alex spouse: " + alex.spouse.name);
        alex.marry(maria);
        System.out.println("Alex spouse : " + alex.spouse.name + ", Maria spouse: " + maria.spouse.name + ", Pavel spouse: " + pavel.spouse
        + ", Alina spouse: " + alina.spouse);
        alex.marry(maria);

    }


}
