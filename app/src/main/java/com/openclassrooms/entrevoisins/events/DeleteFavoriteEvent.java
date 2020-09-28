package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Favorite
 */
public class DeleteFavoriteEvent {

        /**
         * Favorite to delete
         */
        public Neighbour favorite;

        /**
         * Constructor.
         * @param favorite
         */
        public DeleteFavoriteEvent(Neighbour favorite) { this.favorite = favorite;
        }

}
