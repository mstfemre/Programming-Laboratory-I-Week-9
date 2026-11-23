public class Main {

    public static void main(String[] args) {

        System.out.println("=== Creating the team ===");
        Team team = new Team();
        team.addCharacter(new Warrior("Arthur", 20, 200, 16));
        team.addCharacter(new Mage("Merlin", 18, 120, 70));
        team.addCharacter(new Healer("Elena", 15, 130, 25));
        team.addCharacter(new Warrior("Bjorn", 12, 160, 10));

        Warrior golem = new Warrior("Golem", 22, 400, 20);
        System.out.println();
        System.out.println("Enemy: " + golem.getCharacterInfo());
        System.out.println();

        team.displayTeam();

        int round = 1;
        while (round <= 3 && golem.isAlive()) {
            System.out.println();
            System.out.println("========== ROUND " + round + " ==========");

            System.out.println("> Team attacks the enemy");
            team.attackAll(golem);

            if (!golem.isAlive()) {
                System.out.println("Golem is defeated!");
                break;
            }

            System.out.println("> Golem attacks the team");
            for (GameCharacter member : team.getCharacters()) {
                if (member.isAlive()) {
                    int dmg = golem.attack(member);
                    System.out.println("Golem attacks " + member.getName() + " -> " + dmg + " damage (" + member.getName() + " HP: " + member.getHealth() + ")");
                    if (!member.isAlive()) {
                        System.out.println(member.getName() + " is defeated!");
                    }
                }
            }

            System.out.println("> Team heals itself");
            team.healTeam();

            round++;
        }

        System.out.println();
        System.out.println("After the battle:");
        System.out.println("Enemy: " + golem.getCharacterInfo());
        team.displayTeam();

        System.out.println();
        System.out.println("=== Remove / Search ===");
        team.removeCharacter("Bjorn");
        team.removeCharacter("Gandalf");

        GameCharacter found = team.findByName("Merlin");
        if (found != null) {
            System.out.println("Found: " + found.getCharacterInfo());
        } else {
            System.out.println("Merlin not found.");
        }

        found = team.findByName("Bjorn");
        if (found == null) {
            System.out.println("Bjorn not found.");
        }

        System.out.println();
        team.displayTeam();

        System.out.println();
        System.out.println("=== Invalid input tests ===");
        team.addCharacter(new Mage("Merlin", 25, 100, 50));

        Healer lily = new Healer("Lily", 150, -20, 10);
        System.out.println(lily.getCharacterInfo());

        Warrior rookie = new Warrior("Rookie", 0, 80, 5);
        System.out.println(rookie.getCharacterInfo());

        Warrior dummy = new Warrior("Dummy", 5, 50, 0);
        dummy.takeDamage(1000);
        System.out.println("Dummy alive? " + dummy.isAlive());
        System.out.println("Healing Dummy: " + dummy.restoreHealth(30) + " HP restored");
        System.out.println("Golem attacks Dummy: " + golem.attack(dummy) + " damage");
        System.out.println("Dummy attacks Golem: " + dummy.attack(golem) + " damage");

        Mage apprentice = new Mage("Apprentice", 3, 60, 10);
        System.out.println("Apprentice attack: " + apprentice.attack(golem));
        System.out.println("Apprentice heal: " + apprentice.heal(lily));
    }
}
