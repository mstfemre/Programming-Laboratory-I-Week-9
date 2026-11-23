public class Healer extends GameCharacter implements Supporter {

    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        if (healAmount < 0) {
            healAmount = 0;
        }
        this.healAmount = healAmount;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
    }

    @Override
    public int heal(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }
        return target.restoreHealth(healAmount + getLevel());
    }

    @Override
    public String getCharacterInfo() {
        return "[Healer] " + getName() + " | Level: " + getLevel() + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Heal Amount: " + healAmount + " | Power: " + calculatePower();
    }

    public int getHealAmount() {
        return healAmount;
    }
}
