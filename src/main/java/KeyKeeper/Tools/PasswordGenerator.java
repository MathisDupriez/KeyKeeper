package KeyKeeper.Tools;

import java.security.SecureRandom;

public class PasswordGenerator {
    public static String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        // Plage de caractères ASCII imprimables: de 33 ('!') à 126 ('~')
        int lowerLimit = 33;
        int upperLimit = 126;

        for (int i = 0; i < length; i++) {
            // Génère un entier aléatoire entre lowerLimit et upperLimit
            int randomAscii = lowerLimit + random.nextInt(upperLimit - lowerLimit + 1);
            password.append((char) randomAscii);
        }

        return password.toString();
    }
}
