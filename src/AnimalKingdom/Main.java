package AnimalKingdom;

public class Main {

    public static void main(String[] args) {

        Animal[] animals = new Animal[4];
        animals[0] = new Dog("Husky");
        animals[1] = new Cat("Tiger");
        animals[2] = new Dog("Wolf");
        animals[3] = new Cat("Lion");

        // You cannot make an instance of an interface
        // You can make a variable of an interface
        Noisy[] noisyAnimals = new Noisy[4];
        noisyAnimals[0] = new Dog("Husky");
        noisyAnimals[1] = new Cat("Tiger");
        noisyAnimals[2] = new Dog("Wolf");
        noisyAnimals[3] = new Cat("Lion");

        for (Noisy noisyAnimal : noisyAnimals) {

            noisyAnimal.makeNoise();

            if (noisyAnimal instanceof Cat c1) {
                c1.purr();
            }

            if (noisyAnimal instanceof Dog d1) {
                d1.fetch();
            }
        }
    }
}
