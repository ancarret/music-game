public class Player {

    //Constantes
    private static final int INITIAL_AGE = 16;
    private static final int INITIAL_SKILL = 70;
    private static final int INITIAL_ENERGY = 100;
    private static final double INITIAL_MONEY = 150;

    //Atributos
    private String name;
    private Instrument instrument;
    private int age;
    private int skill;
    private int energy;
    private double money;

    //Constructor
    public Player(String name, Instrument instrument) {
        this.name = name;
        this.instrument = instrument;
        this.age = INITIAL_AGE;

        this.skill = INITIAL_SKILL;
        this.energy = INITIAL_ENERGY;
        this.money = INITIAL_MONEY;
    }

    public void printSummary() {
        switch(instrument){
            case GUITAR -> System.out.println("Hola! Soy " + name + ", tengo " + age + " años, y toco la guitarra con " + skill + " puntos de habilidad. Tengo " + money + " euros y " + energy + " puntos de energía.");
            case PIANO -> System.out.println("Hola! Soy " + name + ", tengo " + age + " años, y toco el piano con " + skill + " puntos de habilidad. Tengo " + money + " euros y " + energy + " puntos de energía.");
        }
    }
}

