package AnimalKingdom;

/**
 * @author
 */
public class Fish extends Animal implements Noisy{

    public Fish() {}

    public Fish(String name) {
        super(name);
    }

    public void makeNoise() {
        System.out.println("Blub blub blub");
    }
}
