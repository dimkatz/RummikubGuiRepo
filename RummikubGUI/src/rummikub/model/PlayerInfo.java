/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.Objects;

/**
 *
 * @author DK
 */
public class PlayerInfo
{
    private String name;
    private boolean isHumen;
    private boolean placedFirstSequence;

    public PlayerInfo(String name, boolean isHumen, boolean placedFirstSequence) 
    {
        this.name = name;
        this.isHumen = isHumen;
        this.placedFirstSequence = placedFirstSequence;
    }

    public boolean isPlacedFirstSequence() {
        return placedFirstSequence;
    }

    public void setPlacedFirstSequence(boolean placedFirstSequence) {
        this.placedFirstSequence = placedFirstSequence;
    }

    public String getName() 
    {
        return name;
    }

    public boolean isHumen() 
    {
        return isHumen;
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerInfo other = (PlayerInfo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
