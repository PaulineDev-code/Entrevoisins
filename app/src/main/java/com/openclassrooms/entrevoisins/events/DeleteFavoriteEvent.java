package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavoriteEvent {



    /**
     * Event fired when a user deletes a Neighbour
     */


        /**
         * Neighbour to delete
         */
        public Neighbour favorite;

        /**
         * Constructor.
         * @param favorite
         */
        public DeleteFavoriteEvent(Neighbour favorite) { this.favorite = favorite;
        }

}
