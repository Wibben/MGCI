/**
 * ENUMS basically store values as words, so you can write Group.LARGE instead of a number, very good for visibility
 */

public enum Age {
    BABY(0),
    JUVENILE(1),
    ADULT(2),
    OLD(3); // Semi colon here for clarity

    private final int value;
    private static final double multiplier[] = {0.5, 1.5, 2, 1}; // Multiplier for stats depending on age

    Age(int value) {
        this.value = value;
    } //constructor for enum

    public int getValue() {
        return this.value;
    } //return value

    public Age next() { //this method progresses a given age to the next stage
        if (this == Age.BABY) return Age.JUVENILE;
        else if (this == Age.JUVENILE) return Age.ADULT;
        else return Age.OLD;
    }

    public double ageMulti() {
        return multiplier[value];
    } //multipliers are used for movement
}