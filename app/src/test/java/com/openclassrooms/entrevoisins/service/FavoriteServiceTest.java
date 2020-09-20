package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Favorite service
 */
@RunWith(JUnit4.class)
public class FavoriteServiceTest {

    private NeighbourApiService service;


    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getFavoritesWithSuccess() {
        List<Neighbour> favorites = service.getFavorites();
        favorites.add(service.getNeighbours().get(2));

        List<Neighbour> expectedFavorites = new ArrayList<>();
        expectedFavorites.add(service.getNeighbours().get(2));
        assertThat(favorites, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedFavorites.toArray())));
    }

    @Test
    public void addFavoriteWithSuccess() {
        Neighbour favoriteToAdd = service.getNeighbours().get(5);
        service.addFavorite(favoriteToAdd);
        assertTrue(service.getFavorites().contains(favoriteToAdd));
    }

    @Test
    public void deleteFavoriteWithSuccess() {

        List<Neighbour> favorites = service.getFavorites();
        favorites.add(service.getNeighbours().get(2));
        Neighbour favoriteToDelete = service.getNeighbours().get(2);
//        assertTrue(service.getFavorites().contains(favoriteToDelete));
        service.deleteFavorite(favoriteToDelete);
        assertFalse(service.getFavorites().contains(favoriteToDelete));
    }


    @Test
    public void isFavoriteWithSuccess2() {
        List<Neighbour> favorites = service.getFavorites();
        favorites.add(service.getNeighbours().get(7));

        Boolean isFav = service.isFavorite(service.getNeighbours().get(7));
        assertEquals(isFav , true);

    }

}