package material.collection;

import material.Material;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A compound object representing pairs of materials and their quantity.
 */
public class MaterialCollection implements Iterable<MaterialCountPair> {
    private Map<Material, Integer> materials;

    public MaterialCollection() {
        materials = new HashMap<>();
    }

    public MaterialCollection(MaterialCollection other) {
        materials = new HashMap<>();
        for(MaterialCountPair pair : other) {
            addMaterial(pair.getMaterial(), pair.getCount());
        }
    }

    /**
     * Checks existence of a material.
     * @param material
     * @return
     */
    public boolean exists(Material material){
        return  materials.containsKey(material);
    }

    public int size() {
        return materials.size();
    }

    public int getCount(Material material) {
        if(exists(material)) {
            return materials.get(material);
        }
        return 0;
    }

    private void setCount(Material material, int count) {
        if(count == 0) {
            removeMaterial(material);
            return;
        }
        materials.put(material,count);
    }

    /**
     * Completely removes material.
     * @param material
     */
    public void removeMaterial(Material material){
        materials.remove(material);
    }

    /**
     * @param material
     * @param countToBeAdded
     * @return How many materials were ACTUALLY added.
     */
    public int addMaterial(Material material, int countToBeAdded) {
        int maxMaterialCount = material.getMaterialType().getMaxPerWarehouse();
        int currentCount = getCount(material);
        if(currentCount == maxMaterialCount) {
            return 0;
        }
        int totalCount = currentCount + countToBeAdded;
        if(totalCount > maxMaterialCount) {
            setCount(material, maxMaterialCount);
            return maxMaterialCount - currentCount;
        }
        setCount(material,totalCount);
        return countToBeAdded;
    }

    /**
     * @param material
     * @param countToBeRemoved
     * @return How many materials were ACTUALLY removed.
     */
    public int removeMaterial(Material material, int countToBeRemoved) {
        int currentCount = getCount(material);
        if(currentCount == 0) {
            return 0;
        }
        if(countToBeRemoved > currentCount) {
            removeMaterial(material);
            return currentCount;
        }
        setCount(material, currentCount - countToBeRemoved);
        return countToBeRemoved;
    }

    /**
     * @param materialsToAdd
     * @return How many materials were ACTUALLY added.
     */
    public MaterialCollection addMaterials(MaterialCollection materialsToAdd) {
        MaterialCollection materialsAdded = new MaterialCollection();
        for(MaterialCountPair materialCountPair : materialsToAdd) {
            Material materialToBeChanged = materialCountPair.getMaterial();
            int itemsAdded = addMaterial(materialToBeChanged, materialCountPair.getCount());
            materialsAdded.addMaterial(materialToBeChanged, itemsAdded);
        }
        return materialsAdded;
    }

    /**
     * @param materialsToRemove
     * @return How many materials were ACTUALLY removed.
     */
    public MaterialCollection removeMaterials(MaterialCollection materialsToRemove) {
        MaterialCollection materialsRemoved = new MaterialCollection();
        for(MaterialCountPair materialCountPair : materialsToRemove) {
            Material materialToBeChanged = materialCountPair.getMaterial();
            int removedItems = removeMaterial(materialToBeChanged, materialCountPair.getCount());
            materialsRemoved.addMaterial(materialToBeChanged, removedItems);
        }
        return materialsRemoved;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder().append("Materials : \n");
        for(MaterialCountPair materialCountPair : this) {
            result.append(materialCountPair.getMaterial())
                    .append("\n\tCount : ").append(materialCountPair.getCount()).append("\n");
        }
        return result.toString();
    }

    @Override
    public Iterator<MaterialCountPair> iterator() {
        return new MaterialIterator();
    }

    private class MaterialIterator implements Iterator<MaterialCountPair> {
        private Iterator<Map.Entry<Material, Integer>> materialIterator;

        public MaterialIterator(){
            materialIterator = materials.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return materialIterator.hasNext();
        }

        @Override
        public MaterialCountPair next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Map.Entry<Material,Integer> entry = materialIterator.next();
            return new MaterialCountPair(entry.getKey(), entry.getValue());
        }
    }
}