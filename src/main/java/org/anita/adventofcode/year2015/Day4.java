package org.anita.adventofcode.year2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

    private MessageDigest md;

    public Day4() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("MD5");
    }

    public byte[] md5(String password) {
        md.reset();
        md.update(password.getBytes());
        return md.digest();
    }
}
