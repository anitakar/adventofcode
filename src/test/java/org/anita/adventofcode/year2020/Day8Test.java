package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day8Test {

    @Test
    public void task1() throws Exception {
        final List<String> lines = FileUtils.readStringsLineByLine(getClass().getResourceAsStream("/2020_8.txt"));
        final Day8.Computer computer = new Day8.Computer();
        Assert.assertEquals(1594, computer.interpret(lines));
    }

    @Test
    public void task2() throws Exception {
        final List<String> lines = FileUtils.readStringsLineByLine(getClass().getResourceAsStream("/2020_8.txt"));
        final Day8.Computer computer = new Day8.Computer();

        for (int i = 0; i < lines.size(); ++i) {
            String line = lines.get(i);
            if (line.startsWith("nop")) {
                List<String> copiedCode = new ArrayList<>(lines);
                copiedCode.set(i, lines.get(i).replace("nop", "jmp"));

                computer.interpret(copiedCode);
            } else if (line.startsWith("jmp")) {
                List<String> copiedCode = new ArrayList<>(lines);
                copiedCode.set(i, lines.get(i).replace("jmp", "nop"));

                computer.interpret(copiedCode);
            } else {
                continue;
            }
            if (computer.terminatedCorrectly()) {
                Assert.assertEquals(758, computer.getAccumulator());
                return;
            }
        }
        Assert.fail("Some code should have worked");
    }
}
