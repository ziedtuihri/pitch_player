
package edu.pitchplayer.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Raouf
 */
public class TerrainCount {
     private final SimpleStringProperty ville;
    private final SimpleIntegerProperty nombre;

    public TerrainCount(String ville, int nombre) {
        this.ville = new SimpleStringProperty(ville);
        this.nombre = new SimpleIntegerProperty(nombre);
    }

    public String getVille() {
        return ville.get();
    }

    public SimpleStringProperty villeProperty() {
        return ville;
    }

    public int getNombre() {
        return nombre.get();
    }

    public SimpleIntegerProperty nombreProperty() {
        return nombre;
    }
    
}