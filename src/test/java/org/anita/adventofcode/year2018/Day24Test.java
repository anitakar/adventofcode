package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day24Test {

    @Test
    public void shouldParseUnit() {
        String line = "17 groups each with 5390 hit points (weak to radiation, bludgeoning) with" +
                " an attack that does 4507 fire damage at initiative 2";

        Day24.Group group = new Day24().parseGroup(1, "Immune System", line);

        Assert.assertEquals(group.id, 1);
        Assert.assertEquals(group.type, "Immune System");
        Assert.assertEquals(group.numberOfUnits, 17);
        Assert.assertEquals(group.hitPoints, 5390);
        Assert.assertEquals(group.weaknesses, Arrays.asList("radiation", "bludgeoning"));
        Assert.assertEquals(group.immunities, Arrays.asList());
        Assert.assertEquals(group.damage, 4507);
        Assert.assertEquals(group.typeOfDamage, "fire");
        Assert.assertEquals(group.initiative, 2);
    }

    @Test
    public void shouldParseUnit2() {
        String line = "79 groups each with 1935 hit points with an attack that does 244 radiation damage at initiative 8";

        Day24.Group group = new Day24().parseGroup(1, "Immune System", line);

        Assert.assertEquals(group.id, 1);
        Assert.assertEquals(group.type, "Immune System");
        Assert.assertEquals(group.numberOfUnits, 79);
        Assert.assertEquals(group.hitPoints, 1935);
        Assert.assertEquals(group.weaknesses, Arrays.asList());
        Assert.assertEquals(group.immunities, Arrays.asList());
        Assert.assertEquals(group.damage, 244);
        Assert.assertEquals(group.typeOfDamage, "radiation");
        Assert.assertEquals(group.initiative, 8);
    }

    @Test
    public void task1Test1() throws IOException {
        Day24 day24 = new Day24();
        InputStream inputStream = getClass().getResourceAsStream("/testday24.txt");
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        List<Day24.Group> groups = new ArrayList<>();
        List<String> immuneSystem = lines.subList(1, 3);
        for (int i = 0; i < immuneSystem.size(); ++i) {
            groups.add(day24.parseGroup(i, "Immune System", immuneSystem.get(i)));
        }
        List<String> infection = lines.subList(5, lines.size());
        for (int i = 0; i < infection.size(); ++i) {
            groups.add(day24.parseGroup(i + 2, "Infection", infection.get(i)));
        }
        day24.setGroups(groups);

        Assert.assertEquals(5216, day24.combat());
    }

    @Test
    public void task1() throws IOException {
        Day24 day24 = new Day24();
        InputStream inputStream = getClass().getResourceAsStream("/day24.txt");
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        List<Day24.Group> groups = new ArrayList<>();
        List<String> immuneSystem = lines.subList(1, 11);
        for (int i = 0; i < immuneSystem.size(); ++i) {
            groups.add(day24.parseGroup(i, "Immune System", immuneSystem.get(i)));
        }
        List<String> infection = lines.subList(13, lines.size());
        for (int i = 0; i < infection.size(); ++i) {
            groups.add(day24.parseGroup(i + 10, "Infection", infection.get(i)));
        }
        day24.setGroups(groups);

        Assert.assertEquals(22676, day24.combat());
    }

    @Test
    @Ignore("There is possibility of draw. Groups can not kill each other because of immunities or not enough damage to kill even one unit")
    public void task2() throws IOException {
        Day24 day24 = new Day24();
        InputStream inputStream = getClass().getResourceAsStream("/day24.txt");
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        List<Day24.Group> groups = new ArrayList<>();
        List<String> immuneSystem = lines.subList(1, 11);
        for (int i = 0; i < immuneSystem.size(); ++i) {
            groups.add(day24.parseGroup(i, "Immune System", immuneSystem.get(i)));
        }
        List<String> infection = lines.subList(13, lines.size());
        for (int i = 0; i < infection.size(); ++i) {
            groups.add(day24.parseGroup(i + 10, "Infection", infection.get(i)));
        }

        int boost = 1;
        int unitsLeft = 0;
        while (true) {
            day24.setGroups(cloneGroups(groups));
            day24.boostAttack(boost, "Immune System");
            int result = day24.combat();
            System.out.println("Boost " + boost + ", winner " + day24.winner() + ", result " + result);
            if (day24.winner().equals("Immune System")) {
                unitsLeft = result;
                break;
            }
            boost += 1;
        }

        Assert.assertEquals(1, unitsLeft);
    }

    @Test
    public void task2Part2() throws IOException {
        Day24 day24 = new Day24();
        InputStream inputStream = getClass().getResourceAsStream("/day24.txt");
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        List<Day24.Group> groups = new ArrayList<>();
        List<String> immuneSystem = lines.subList(1, 11);
        for (int i = 0; i < immuneSystem.size(); ++i) {
            groups.add(day24.parseGroup(i, "Immune System", immuneSystem.get(i)));
        }
        List<String> infection = lines.subList(13, lines.size());
        for (int i = 0; i < infection.size(); ++i) {
            groups.add(day24.parseGroup(i + 10, "Infection", infection.get(i)));
        }
        day24.setGroups(groups);
        day24.boostAttack(38, "Immune System");

        Assert.assertEquals(4510, day24.combat());
    }

    private List<Day24.Group> cloneGroups(List<Day24.Group> groups) {
        List<Day24.Group> copy = new ArrayList<>();
        for(Day24.Group group : groups) {
            copy.add(group.clone());
        }
        return copy;
    }
}
