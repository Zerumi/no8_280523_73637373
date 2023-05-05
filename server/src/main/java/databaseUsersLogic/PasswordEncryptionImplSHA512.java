package databaseUsersLogic;

public class PasswordEncryptionImplSHA512 implements PasswordEncryption {

    @Override
    public String encrypt(char[] sequence) {
        // todo: encrypt
        return "";
    }

    @Override
    public char[] generateSalt() {
        return new char[0];
    }
}
