package material;

import material.type.MaterialType;
import java.util.Objects;

public abstract class Material {

    protected String name;
    protected MaterialType materialType;

    public Material(String name, MaterialType materialType) {
        this.name = name;
        this.materialType = materialType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other){
            return true;
        }
        if (!(other instanceof Material otherMaterial)){
            return false;
        }
        return name.equals(otherMaterial.name) && materialType.equals(otherMaterial.materialType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, materialType);
    }

    @Override
    public String toString() {
        return "Material:\n" + name + "\n" + materialType;
    }

    public void setMaterialType(MaterialType materialType){
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}