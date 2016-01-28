/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1
 */

public enum Name
{
    ANKYLOSAURUS(0),
    APATOSAURUS(1),
    GALLIMIMUS(2),
    STEGOSAURUS(3),
    TRICERATOPS(4),
    VELOCIRAPTOR(5),
    TREX(6),
    IREX(7),
    TREE(8)
    ; // Semi colon here for clarity

    private final int value; //value is the associated number

    Name(int value) {
        this.value = value;
    } //constructor

    public int getValue()
    {
        return this.value;
    }
}