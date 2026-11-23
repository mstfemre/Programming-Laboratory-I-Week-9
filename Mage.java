public class Mage extends GameCharacter implements Attacker, Supporter {

    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        if (mana < 0) {
            mana = 0;
        }
        this.mana = mana;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
    }

    @Override
    public int attack(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }
        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana to attack! (mana: " + mana + ")");
            return 0;
        }
        int damage = calculatePower() / 3;
        mana -= 20;
        return target.takeDamage(damage);
    }

    @Override
    public int heal(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }
        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana to heal! (mana: " + mana + ")");
            return 0;
        }
        mana -= 15;
        return target.restoreHealth(getLevel() * 2);
    }

    @Override
    public String getCharacterInfo() {
        return "[Mage] " + getName() + " | Level: " + getLevel() + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Mana: " + mana + " | Power: " + calculatePower();
    }

    public int getMana() {
        return mana;
    }
}
