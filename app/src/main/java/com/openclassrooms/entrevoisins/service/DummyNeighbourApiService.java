package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<>();



    /**
     * {@inheritDoc}
     */

    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavorite(Neighbour favorite) { favorites.remove(favorite); }

    @Override
    public void addFavorite(Neighbour neighbour) { favorites.add(neighbour); }

    @Override
    public boolean isFavorite(Neighbour neighbour) {
       return favorites.contains(neighbour); }



    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbourbyid(long id) {
        for (Neighbour neighbour : neighbours) {
            if( neighbour.getId()==id){
                return neighbour;
            }
        }
        return null;
    }
}
