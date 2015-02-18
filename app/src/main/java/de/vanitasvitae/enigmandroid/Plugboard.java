package de.vanitasvitae.enigmandroid;

/**
 * Class representing the plugboard
 *
 * @author vanitasvitae
 */
public class Plugboard
{
    //Plugboard
    //	Q		W		E		R		T		Z		U		I		O
    //		A		S		D		F		G		H		J		K
    //	P		Y		X		C		V		B		N		M		L

    //Array containing plugged pairs
    int[] pb;
    //Standard array to compare pb to.
    public static final int[] ref = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

    /**
     * Create new plugboard without any plugged pairs. (empty, no scrambling here)
     */
    public Plugboard()
    {
        pb = new int[26];
        resetPlugboard();
    }

    /**
     * En-/decrypt a char following the connections on the plugboard
     *
     * @param x char to perform crypto on
     * @return en-/decrypted char
     */
    public int encrypt(int x)
    {
        return pb[x];
    }

    /**
     * Reset the plugboard (no plugged pairs)
     */
    public void resetPlugboard()
    {
        pb = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    }

    /**
     * Set a pair of plugs on the plugboard
     *
     * @param a first Plug
     * @param b second Plug
     */
    public void setPlugPair(char a, char b) throws PlugAlreadyUsedException
    {
        //prevent to plug a plug to itself
        if (a == b)
        {
            throw new PlugAlreadyUsedException("Unable to plug " + a + " to " + a);
        }
        int x = a - 65;
        int y = b - 65;
        //Check, if plugs already plugged
        if (pb[x] != ref[x])
        {
            throw new PlugAlreadyUsedException("Plug " + a + " used twice!");
        } else if (pb[y] != ref[y])
        {
            throw new PlugAlreadyUsedException("Plug " + b + " used twice!");
        }
        //If everything is correct
        else
        {
            //set the pair
            int c = pb[x];
            pb[x] = pb[y];
            pb[y] = c;
        }
    }

    public static class PlugAlreadyUsedException extends Exception
    {
        public PlugAlreadyUsedException(String m)
        {
            super(m);
        }
    }
}
