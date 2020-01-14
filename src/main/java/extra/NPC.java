package extra;

import org.newdawn.slick.SpriteSheet;

public class NPC {

    String name;
    Coordinates coordinates;
    int AP;
    float strength;
    float speed;
    float endurance;
    float agility;
    float luck;
    float intelligence;
    float wisdom;
    SpriteSheet spriteSheet;
    boolean isActive;

    public NPC(String name, Coordinates coordinates, int AP, float strength, float speed, float endurance, float agility, float luck, float intelligence, float wisdom, SpriteSheet spriteSheet, boolean isActive) {
        this.name = name;
        this.coordinates = coordinates;
        this.AP = AP;
        this.strength = strength;
        this.speed = speed;
        this.endurance = endurance;
        this.agility = agility;
        this.luck = luck;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.spriteSheet = spriteSheet;
        this.isActive = isActive;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }

    public float getAgility() {
        return agility;
    }

    public void setAgility(float agility) {
        this.agility = agility;
    }

    public float getLuck() {
        return luck;
    }

    public void setLuck(float luck) {
        this.luck = luck;
    }

    public float getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(float intelligence) {
        this.intelligence = intelligence;
    }

    public float getWisdom() {
        return wisdom;
    }

    public void setWisdom(float wisdom) {
        this.wisdom = wisdom;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
