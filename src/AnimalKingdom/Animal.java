package AnimalKingdom;

/**
 * @author
 */
public abstract class Animal {


    private String name;

    public Animal() {}

    public Animal(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    /**
     * This is the default implementation of make noise
     * It will be overridden by the child classes
     */
    public abstract void makeNoise();
}
