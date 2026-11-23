import java.util.ArrayList;

public class Team {

    private ArrayList<GameCharacter> characters;

    public Team() {
        characters = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) {
            System.out.println("Error: character cannot be null!");
            return false;
        }
        if (findByName(character.getName()) != null) {
            System.out.println("Error: there is already a character named " + character.getName() + " in the team!");
            return false;
        }
        characters.add(character);
        System.out.println(character.getName() + " joined the team.");
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter c = findByName(name);
        if (c == null) {
            System.out.println("Could not remove, " + name + " is not in the team.");
            return false;
        }
        characters.remove(c);
        System.out.println(name + " is removed from the team.");
        return true;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : characters) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        if (target == null) {
            return;
        }
        for (GameCharacter c : characters) {
            if (!target.isAlive()) {
                System.out.println(target.getName() + " is already defeated, it cannot be attacked.");
                break;
            }
            if (c.isAlive() && c instanceof Attacker) {
                int dmg = ((Attacker) c).attack(target);
                System.out.println(c.getName() + " attacks " + target.getName() + " -> " + dmg + " damage (" + target.getName() + " HP: " + target.getHealth() + ")");
            }
        }
    }

    public void healTeam() {
        for (GameCharacter c : characters) {
            if (c.isAlive() && c instanceof Supporter) {
                GameCharacter lowest = null;
                double lowestRatio = 2.0;
                for (GameCharacter member : characters) {
                    if (member.isAlive()) {
                        double ratio = (double) member.getHealth() / member.getMaxHealth();
                        if (ratio < lowestRatio) {
                            lowestRatio = ratio;
                            lowest = member;
                        }
                    }
                }
                if (lowest != null) {
                    int healed = ((Supporter) c).heal(lowest);
                    System.out.println(c.getName() + " heals " + lowest.getName() + " -> +" + healed + " HP (" + lowest.getName() + " HP: " + lowest.getHealth() + ")");
                }
            }
        }
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : characters) {
            if (c.isAlive()) {
                total += c.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("----- TEAM (" + characters.size() + " members) -----");
        for (GameCharacter c : characters) {
            String status = c.isAlive() ? "" : " (DEFEATED)";
            System.out.println(c.getCharacterInfo() + status);
        }
        System.out.println("Total power of alive members: " + getTotalPower());
        System.out.println("-----------------------------------");
    }

    public ArrayList<GameCharacter> getCharacters() {
        return characters;
    }
}
