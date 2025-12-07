package entities;

import annotations.WeeklySchedule;

public class CrazyProgrammer {
    private int mentalHealth = 0;

    public int getMentalHealth() {
        return mentalHealth;
    }

    private int coffeeCups = 0;

    // Public methods - what is seen to the outside world.
    public void tellLeadEverythingIsFine() {
        System.out.println("To Tech Lead: 'All good, will be done soon! :-)'");
    }

    // Protected methods - for family and friends.
    @WeeklySchedule(5)
    protected void writeGoCode(String feature) {
        mentalHealth -= 1;
        System.out.println("Coding on Go: " + feature);
        System.out.println(" 'goroutines are so fun! youuuhuuuu! panic... AGAIN??? :-#%&");
        System.out.println("Mental health: " + mentalHealth);
        coffeeCups++;

    }

    @WeeklySchedule(3)
    protected void writeJavaCode(String feature) {
        mentalHealth -= 2;
        System.out.println("Coding on Java: " + feature);
        System.out.println("Spring Boot, Hibernate... Why there are so many annotations?! :-$#%");
        System.out.println("Mental health: " + mentalHealth);
        coffeeCups++;

        if (coffeeCups > 3) {
            System.out.println("Hands shaking cause of coffee %-(");
        }
    }

    @WeeklySchedule(5)
    protected void readGoBook(int pages) {
        mentalHealth += 1;
        System.out.println("Read Golang book (" + pages + " pages)...");
        System.out.println(" 'goroutines, channels, defer... aaa! %-)'");
        System.out.println("Mental health: " + mentalHealth);

    }

    protected void walkInPark(int hours) {
        mentalHealth += Math.min(hours, 3);
        System.out.println("Walking in park making me feels better!!!!");
        System.out.println("Mental health: " + mentalHealth);
    }

    // Private - real truth
    @WeeklySchedule(2)
    private void goToGym(int weight) {
        mentalHealth += 3;
        System.out.println("Bench press weigh: " + weight + " kg");
        System.out.println("Yeaah!");
        System.out.println("Mental health: " + mentalHealth);
    }

    @WeeklySchedule(7)
    private void sleep(int max) {
        int min = 4;
        int hours = min + (int) (Math.random() * (max - min + 1));

        if (hours >= 7 ) {
            mentalHealth += 5;
            System.out.println("Refreshing sleep: " + hours + " hours :-) ");
        } else {
            mentalHealth -= 3;
            System.out.println("Oooooh, need sleep longer than " + hours + " hours... %-( ");
        }
        coffeeCups = 0;
        System.out.println("Mental health: " + mentalHealth);
    }

    private void askStackOverflow(String question) {
        System.out.println("Search on StackOwerflow: " + question);
    }

}
