package databaseLogic.databaseUserLogic;

import com.google.common.hash.Hashing;
import multiThreadLogic.CollectinonSyncronize;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.locks.Lock;

public class PasswordEncryptionImplSHA512 implements PasswordEncryption {

    private static final SecureRandom RANDOM = new SecureRandom();
    private final Lock readLock;

    {
        readLock = CollectinonSyncronize.getInstance().getLock().readLock();
    }

    @Override
    public String encrypt(char[] sequence) {
        readLock.lock();
        var result = Hashing.sha512().hashBytes(StandardCharsets.UTF_8.encode(CharBuffer.wrap(sequence)).array()).toString();
        readLock.unlock();
        return result;
    }

    @Override
    public char[] generateSalt() {
        readLock.lock();
        byte[] salt = new byte[160];
        RANDOM.nextBytes(salt);
        var result = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(
                Base64.getEncoder().encode(salt))).array();
        readLock.unlock();
        return result;
    }
}
