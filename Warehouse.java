import material.Material;
import material.collection.MaterialCollection;
import material.collection.MaterialCountPair;

/**
 * Represents a Sandship warehouse.
 */
public class Warehouse {
    private MaterialCollection materials;

    public Warehouse() {
        this(new MaterialCollection());
    }

    public Warehouse(MaterialCollection initialMaterials) {
        materials = new MaterialCollection(initialMaterials);
    }

    /**
     * Check to see if warehouse has particular material.
     * @param material
     * @return
     */
    public boolean hasMaterial(Material material) {
        return materials.exists(material);
    }

    /**
     * @return The number of materials in warehouse.
     */
    public int size() {
        return materials.size();
    }

    /**
     * @param material
     * @param count
     * @return How many items were actually added to the warehouse.
     */
    public int addMaterial(Material material, int count) {
        return materials.addMaterial(material,count);
    }

    /**
     * @param materialsToAdd Represents material-count pairs, which will be added to the warehouse.
     * @return Material-count pairs, which were actually added to the warehouse.
     */
    public MaterialCollection addMaterials(MaterialCollection materialsToAdd) {
        return materials.addMaterials(materialsToAdd);
    }

    /**
     * Moves materials to the other warehouse.
     * Items are moved PARTIALLY if they violate any maximum/minimum capacity conditions.
     * @param otherWarehouse
     * @param materialsToMove An object, which contains pairs indicating which and how many of material to move.
     */
    public void moveMaterials(Warehouse otherWarehouse, MaterialCollection materialsToMove) {
        for(MaterialCountPair materialCountPair : materialsToMove) {
            moveMaterial(otherWarehouse, materialCountPair.getMaterial(), materialCountPair.getCount());
        }
    }

    /**
     * Moves material to other warehouse.
     * Items are moved PARTIALLY if they violate any maximum/minimum capacity conditions.
     * @param otherWarehouse
     * @param material
     * @param count
     */
    public void moveMaterial(Warehouse otherWarehouse, Material material, int count) {
        int receiverCountLimit = material.getMaterialType().getMaxPerWarehouse() -
                otherWarehouse.materials.getCount(material);
        int senderCountLimit = materials.getCount(material);
        int itemsToMove = Math.min(count, Math.min(receiverCountLimit,senderCountLimit));
        removeMaterial(material,itemsToMove);
        otherWarehouse.addMaterial(material,itemsToMove);
    }

    /**
     * @param materialsToRemove Materials to be removed.
     * @return Which materials and how many where removed.
     */
    public MaterialCollection removeMaterials(MaterialCollection materialsToRemove){
        return materials.removeMaterials(materialsToRemove);
    }

    /**
     * @param material Material to be removed
     * @param count Number of material to be removed.
     * @return How many of the material was actually removed.
     */
    public int removeMaterial(Material material, int count) {
        return materials.removeMaterial(material,count);
    }

    @Override
    public String toString() {
        return "Warehouse :\n" + materials;
    }
}