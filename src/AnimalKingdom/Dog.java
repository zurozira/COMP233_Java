package AnimalKingdom;

/**
 * @author
 */
public class Dog extends Animal{

    /**
     * This constructor provide access to the parent class 's constructor
     * @param name String - the name of the animal
     */
    public Dog(String name) {

        super(name);
    }

    @Override
    public void makeNoise() {
        System.out.printf("%s: Bark\n", getName());
    }

    public void fetch() {
        System.out.printf("%s: Going to get the stick!!\n", getName());
    }
}
