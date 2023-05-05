package databaseUsersLogic;

public interface PasswordEncryption {
    static char[] getPepper() {
        // todo: return pepper
        return new char[0];
    }

    String encrypt(char[] sequence);

    char[] generateSalt();
}
