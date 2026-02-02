package AnimalKingdom;

public class Main {

    public static void main(String[] args) {

        Animal[] animals = new Animal[4];
        animals[0] = new Dog("Husky");
        animals[1] = new Cat("Tiger");
        animals[2] = new Dog("Wolf");
        animals[3] = new Cat("Lion");

        for (Animal animal : animals) {

            animal.makeNoise();

            if (animal instanceof Cat c1) {
                c1.purr();
            }

            if (animal instanceof Dog d1) {
                d1.fetch();
            }
        }
    }
}
