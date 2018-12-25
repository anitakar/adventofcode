package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day24 {

    private List<Group> groups = new ArrayList<>();

    public int combat() {
        this.groups = groups;
        //printBattle(groups);

        while (true) {
            battle();
            printBattle(groups);
            long infectionCount = groups.stream().filter(u -> u.type.equals("Infection")).count();
            if (infectionCount == 0 || infectionCount == groups.size()) {
                break;
            }
        }

        return groups.stream().mapToInt(u -> u.numberOfUnits).sum();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void boostAttack(int boost, String unitType) {
        groups.stream().filter(g -> g.type.equals(unitType)).forEach(g -> g.damage = g.damage + boost);
    }

    public String winner() {
        return groups.get(0).type;
    }

    private void printBattle(List<Group> groups) {
        for (Group group : groups) {
            System.out.println(group);
        }
        System.out.println("=============================");
    }

    public void battle() {
        // target selection

        // get attack order
        List<Group> inOrderOfTargetSelection = groups.stream().sorted((u1, u2) -> {
            int res1 = u2.effectivePower() - u1.effectivePower();
            if (res1 != 0) return res1;
            return u2.groupInitiative() - u1.groupInitiative();
        }).collect(Collectors.toList());
        // choose target
        Map<Integer, Group> groupsToAttack = new HashMap<>();
        Set<Integer> chosenGroups = new HashSet<>();
        for (Group attacker : inOrderOfTargetSelection) {
            int targetDamage = 0;
            int targetEffectivePower = 0;
            int targetInitiative = 0;
            Group targetDefender = null;
            for (Group defender : groups) {
                if (!defender.type.equals(attacker.type) && !chosenGroups.contains(defender.id)) {
                    int damage;
                    if (defender.immunities.contains(attacker.typeOfDamage)) {
                        continue;
                    }
                    damage = attacker.effectivePower();
                    if (defender.weaknesses.contains(attacker.typeOfDamage)) {
                        damage = 2 * damage;
                    }
                    if (damage > targetDamage) {
                        targetDefender = defender;
                        targetDamage = damage;
                        targetEffectivePower = defender.effectivePower();
                        targetInitiative = defender.groupInitiative();
                    } else if (damage == targetDamage) {
                        if (defender.effectivePower() > targetEffectivePower) {
                            targetDefender = defender;
                            targetDamage = damage;
                            targetEffectivePower = defender.effectivePower();
                            targetInitiative = defender.groupInitiative();
                        } else if (defender.effectivePower() == targetEffectivePower) {
                            if (defender.groupInitiative() > targetInitiative) {
                                targetDefender = defender;
                                targetDamage = damage;
                                targetEffectivePower = defender.effectivePower();
                                targetInitiative = defender.groupInitiative();
                            }
                        }
                    }
                }
            }
            if (targetDefender != null) {
                groupsToAttack.put(attacker.id, targetDefender);
                chosenGroups.add(targetDefender.id);
            }
        }

        // attack
        List<Group> inOrderOfAttack = groups.stream().sorted(Comparator.comparingInt(u -> -u.groupInitiative())).collect(Collectors.toList());
        for (Group attacker : inOrderOfAttack) {
            if (groupsToAttack.get(attacker.id) != null) {
                attack(attacker, groupsToAttack.get(attacker.id));
            }
        }

    }

    private void attack(Group attacker, Group defender) {
        if (attacker.numberOfUnits <= 0) {
            groups.remove(attacker);
            return;
        }
        int numberOfUnitsToRemove;
        if (defender.immunities.contains(attacker.typeOfDamage)) {
            numberOfUnitsToRemove = 0;
        } else if (defender.weaknesses.contains(attacker.typeOfDamage)) {
            numberOfUnitsToRemove = (2 * attacker.effectivePower()) / defender.hitPoints;
        } else {
            numberOfUnitsToRemove = (attacker.effectivePower()) / defender.hitPoints;
        }
        defender.numberOfUnits -= numberOfUnitsToRemove;
        if (defender.numberOfUnits <= 0) {
            groups.remove(defender);
        }
    }

    private static String groupRegex = "^(\\d*) groups each with (\\d*) hit points (.*)with" +
            " an attack that does (\\d*) (\\w*) damage at initiative (\\d*)$";
    private Pattern groupPattern = Pattern.compile(groupRegex);

    public Group parseGroup(int id, String type, String line) {
        Matcher matcher = groupPattern.matcher(line);
        if (matcher.find()) {
            String[] immunitiesWeaknesses = matcher.group(3).replace("(", "").replace(")", "").split("; ");
            List<String> weaknessList = new ArrayList<>();
            List<String> immunityList = new ArrayList<>();
            for (String immWeak : immunitiesWeaknesses) {
                if (immWeak.startsWith("weak to ")) {
                    String[] weaknesses = immWeak.substring("weak to ".length()).split(", ");
                    for (String weakness : weaknesses) {
                        weaknessList.add(weakness.trim());
                    }
                } else if (immWeak.startsWith("immune to ")) {
                    String[] immunities = immWeak.substring("immune to ".length()).split(", ");
                    for (String immunity : immunities) {
                        immunityList.add(immunity.trim());
                    }
                }
            }
            return new Group(
                    id,
                    type,
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    weaknessList,
                    immunityList,
                    Integer.parseInt(matcher.group(4)),
                    matcher.group(5),
                    Integer.parseInt(matcher.group(6))
            );
        }
        return null;
    }

    public static class Group {
        int id;
        String type;
        int numberOfUnits;
        int hitPoints;
        List<String> weaknesses;
        List<String> immunities;
        int damage;
        String typeOfDamage;
        int initiative;

        public Group(int id, String type, int numberOfUnits, int hitPoints, List<String> weaknesses, List<String> immunities, int damage, String typeOfDamage, int initiative) {
            this.id = id;
            this.type = type;
            this.numberOfUnits = numberOfUnits;
            this.hitPoints = hitPoints;
            this.weaknesses = weaknesses;
            this.immunities = immunities;
            this.damage = damage;
            this.typeOfDamage = typeOfDamage;
            this.initiative = initiative;
        }

        public int effectivePower() {
            return numberOfUnits * damage;
        }

        public int groupInitiative() {
            return initiative;
        }

        @Override
        protected Group clone() {
            return new Group(
                    this.id,
                    this.type,
                    this.numberOfUnits,
                    this.hitPoints,
                    this.weaknesses,
                    this.immunities,
                    this.damage,
                    this.typeOfDamage,
                    this.initiative
            );
        }

        @Override
        public String toString() {
            return "Group{" +
                    "id=" + id +
                    ", effectivePower='" + effectivePower() + '\'' +
                    ", type='" + type + '\'' +
                    ", numberOfUnits=" + numberOfUnits +
                    ", hitPoints=" + hitPoints +
                    ", weaknesses=" + weaknesses +
                    ", immunities=" + immunities +
                    ", damage=" + damage +
                    ", typeOfDamage='" + typeOfDamage + '\'' +
                    ", initiative=" + initiative +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Group group = (Group) o;
            return id == group.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
