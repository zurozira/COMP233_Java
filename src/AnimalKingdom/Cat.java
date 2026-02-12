package AnimalKingdom;

/**
 * @author
 */
public class Cat extends Animal implements Noisy{

    /**
     * This constructor provide access to the parent class 's constructor
     * @param name String - the name of the animal
     */
    public Cat(String name) {

        super(name);
    }

    @Override
    public void makeNoise() {
        System.out.printf("%s: Meow\n", getName());
    }

    public void purr() {
        System.out.printf("%s: Prrrrrrrrrrr\n", getName());
    }
}
