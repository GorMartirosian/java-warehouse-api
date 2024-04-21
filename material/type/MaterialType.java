package material.type;

import java.util.Objects;

public class MaterialType {
    private String name;
    private String description;
    private String icon;
    private int maxPerWarehouse;

    public MaterialType(String name, String description, String icon, int maxPerWarehouse) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.maxPerWarehouse = maxPerWarehouse;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(!(other instanceof MaterialType otherMaterialType)){
            return false;
        }
        return name.equals(otherMaterialType.name) &&
                description.equals(otherMaterialType.description) &&
                icon.equals(otherMaterialType.icon) &&
                maxPerWarehouse == otherMaterialType.maxPerWarehouse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, icon, maxPerWarehouse);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMaxPerWarehouse() {
        return maxPerWarehouse;
    }

    public void setMaxPerWarehouse(int maxPerWarehouse) {
        this.maxPerWarehouse = maxPerWarehouse;
    }

    @Override
    public String toString() {
        return "Material Type:" +
                "\n\tName: " + name +
                "\n\tDescription: " + description +
                "\n\tMax Per Warehouse: " + maxPerWarehouse;
    }
}