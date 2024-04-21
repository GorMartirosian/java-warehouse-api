package material.collection;

import material.Material;

/**
 * Represents a pair of a material with its quantity.
 */
public class MaterialCountPair {
    private Material material;
    private int count;

    public MaterialCountPair(Material material, Integer count){
        this.material = material;
        this.count = count;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Material Pair : " + material + "\n\t" + "Count : " + count; //todo
    }
}
