package AnimalKingdom;

public interface Noisy {

    // All variables in an interface must be a constant
    // In Java, constant are represented with the word final
    public final boolean canMakeNoise = true;

    // All functions in an interface are abstract by default
    public void makeNoise();
}
