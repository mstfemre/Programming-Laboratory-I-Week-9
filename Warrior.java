public class Warrior extends GameCharacter implements Attacker {

    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        if (armor < 0) {
            armor = 0;
        }
        this.armor = armor;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public int takeDamage(int damage) {
        int reduced = damage - armor / 2;
        if (reduced < 1) {
            reduced = 1;
        }
        return super.takeDamage(reduced);
    }

    @Override
    public int attack(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }
        int damage = calculatePower() / 4;
        return target.takeDamage(damage);
    }

    @Override
    public String getCharacterInfo() {
        return "[Warrior] " + getName() + " | Level: " + getLevel() + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Armor: " + armor + " | Power: " + calculatePower();
    }

    public int getArmor() {
        return armor;
    }
}
