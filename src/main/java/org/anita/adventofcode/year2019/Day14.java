package org.anita.adventofcode.year2019;

import java.util.*;

public class Day14 {
    Set<String> basicResources = new HashSet<>();
    HashMap<String, Resource> resources = new HashMap<>();

    public int task1(List<Resource> inputResources) {
        for (Resource resource : inputResources) {
            if (resource.getIngredients().size() == 1 && resource.getIngredients().containsKey("ORE")) {
                resource.resolve(resource.getIngredients().get("ORE"));
                basicResources.add(resource.getName());
            }
            resources.put(resource.getName(), resource);
        }

        final Resource fuel = resources.get("FUEL");

        while (!fuel.isResolved()) {

            Set<String> toRemove = new HashSet<>();
            Map<String, Integer> toAdd = new HashMap<>();
            for (Map.Entry<String, Integer> ingredient : fuel.getBasicIngredients().entrySet()) {
                if (basicResources.contains(ingredient.getKey())) {
                    continue;
                }

                final Resource resource = resources.get(ingredient.getKey());
                toRemove.add(ingredient.getKey());

                for (Map.Entry<String, Integer> basicIngredient : resource.getIngredients().entrySet()) {
                    int portions = ingredient.getValue();
                    int producedPortions = resource.getQuantity();
                    if (producedPortions > portions) {
                        portions = 1;
                    } else {
                        portions = portions / producedPortions;
                        if (portions % producedPortions != 0) {
                            portions += 1;
                        }
                    }
                    int neededQuantity = basicIngredient.getValue();
                    int toAddQuantity = portions * neededQuantity;
                    toAdd.compute(basicIngredient.getKey(), (k, v) -> (v == null) ? toAddQuantity : v + toAddQuantity);
                }
            }

            for (String ingredient : toRemove) {
                fuel.getBasicIngredients().remove(ingredient);
            }
            for (Map.Entry<String, Integer> ingredient : toAdd.entrySet()) {
                fuel.getBasicIngredients().compute(ingredient.getKey(), (k, v) -> v == null ? ingredient.getValue() : v + ingredient.getValue());
            }

            if (basicResources.containsAll(fuel.getBasicIngredients().keySet())) {
                fuel.resolve(fuel.getBasicIngredients());
            }
        }

        int totalOre = 0;
        for (Map.Entry<String, Integer> basicIngredient : fuel.getBasicIngredients().entrySet()) {
            int neededQuantity = basicIngredient.getValue();
            Resource ingredientResource = resources.get(basicIngredient.getKey());
            int obtainedQuantity = ingredientResource.getQuantity();

            int portions;
            if (obtainedQuantity > neededQuantity) {
                portions = 1;
            } else {
                portions = neededQuantity / obtainedQuantity;
                if (neededQuantity % obtainedQuantity != 0) {
                    portions += 1;
                }
            }
            totalOre += (ingredientResource.getOreCount() * portions);
        }

        return totalOre;
    }

    public Resource parseResource(String line) {
        final String[] recipe = line.split("=>");
        String output = recipe[1];
        String[] inputs = recipe[0].split(",");
        final String[] outputParsed = parseIngredient(output);
        final String name = outputParsed[1];
        final int quantity = Integer.parseInt(outputParsed[0]);
        HashMap<String, Integer> ingredients = new HashMap<>();
        for (String input : inputs) {
            final String[] inputParsed = parseIngredient(input);
            ingredients.put(inputParsed[1], Integer.parseInt(inputParsed[0]));
        }
        return new Resource(name, quantity, ingredients);
    }

    private String[] parseIngredient(String ingredient) {
        ingredient = ingredient.trim();
        return ingredient.split(" ");
    }

    public static class Resource {
        private String name;
        private int quantity;
        private Map<String, Integer> ingredients;

        private boolean resolved = false;
        private int oreCount = -1;
        private Map<String, Integer> basicIngredients = new HashMap<>();

        public Resource(String name, int quantity, HashMap<String, Integer> ingredients) {
            this.name = name;
            this.quantity = quantity;
            this.ingredients = ingredients;
            this.basicIngredients = new HashMap<>(ingredients);
        }

        public void resolve(int oreCount) {
            this.resolved = true;
            this.oreCount = oreCount;
        }

        public void resolve(Map<String, Integer> basicIngredients) {
            this.resolved = true;
            this.basicIngredients = basicIngredients;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public Map<String, Integer> getIngredients() {
            return ingredients;
        }

        public boolean isResolved() {
            return resolved;
        }

        public int getOreCount() {
            return oreCount;
        }

        public Map<String, Integer> getBasicIngredients() {
            return basicIngredients;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Resource resource = (Resource) o;
            return name.equals(resource.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
