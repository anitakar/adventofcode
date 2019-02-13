package org.anita.adventofcode.year2015;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;

public class Day4Test {

    private Day4 day4 = new Day4();

    public Day4Test() throws NoSuchAlgorithmException {
    }

    @Test
    public void testMd5() {
        System.out.println(DatatypeConverter.printHexBinary(day4.md5("abcdef609043")));
        System.out.println(DatatypeConverter.printHexBinary(day4.md5("pqrstuv1048970")));
        System.out.println(DatatypeConverter.printHexBinary(day4.md5("ckczppom117946")));
        System.out.println(DatatypeConverter.printHexBinary(day4.md5("ckczppom3938038")));
        System.out.println(DatatypeConverter.printHexBinary(new byte[]{0, 0, 15}));
    }

    @Test
    public void findHashWithFiveZeros() {
        int result = 0;
        for (int i = 1; i < 1000000; ++i) {
            byte[] md5 = day4.md5("ckczppom" + i);
            if (md5[0] == 0 && md5[1] == 0 && md5[2] >= 0  && md5[2] <= 15) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }

    @Test
    public void findHashWithSixZeros() {
        int result = 0;
        for (int i = 1; i < 10000000; ++i) {
            byte[] md5 = day4.md5("ckczppom" + i);
            if (md5[0] == 0 && md5[1] == 0 && md5[2] == 0) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }

}
