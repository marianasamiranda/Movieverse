package security;

import java.security.SecureRandom;

public class Security {

    private static final int TOKENLENGTH = 128;
    private static final char[] TOKENCHARS = {
        '0','1','2','3','4','5','6','7','8','9',
        'A','B','C','D','E','F','G','H','I','J',
        'K','L','M','N','O','P','Q','R','S','T',
        'U','V','W','X','Y','Z','a','b','c','d',
        'e','f','g','h','i','j','k','l','m','n',
        'o','p','q','r','s','t','u','v','w','x',
        'y','z'};

    public static String generateToken() {
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<TOKENLENGTH; i++)
            sb.append(TOKENCHARS[rnd.nextInt(TOKENCHARS.length)]);

        return sb.toString();
    }
}
