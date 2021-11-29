
/**
 * Write a description of class ListOps here.
 *
 * @author Dan Kolan
 * @version 1.0
 */
import bridges.base.SLelement;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.ArrayList;

// It is recommended that you work on one method at a time. Uncomment the one you are working on. 
public class ListOps extends SLList<ActorMovieIMDB> {

    private int nodesTraversed = 0;

    /*
    // TODO: JavaDoc notation describing method and parameters
    // search for an actor
     */
    public Boolean findActor(String actor_name) {
        boolean found = false;
        // move current point to beginning of list
        moveToStart();

        // the rest of the code goes here
        while (getValue() != null) {
            if (getValue().getActor().compareTo(actor_name) == 0) {
                found = true;
                break;
            }
            next();
        }
        return found;
    }

    public void findAndInsert(String actor_name, String movie_name) {
        // If actor already exists, add movie_name to list of movies
        if (findActor(actor_name)) {
            next();
            //Use double comma because some movie titles include one comma, leading to inaccurate counting.
            curr.getValue().setMovie(curr.getValue().getMovie() + ",," + movie_name);
            return;
        }

        // If list is empty, create new object from parameters, insert into list
        if (curr.getValue() == null) {
            System.out.println("empty list findAndInsert test " + currPos());
            ActorMovieIMDB newHeadNode = new ActorMovieIMDB();
            newHeadNode.setActor(actor_name);
            newHeadNode.setMovie(movie_name);
            insert(newHeadNode);
            curr.setLabel(actor_name + ":" + movie_name);
            return;
        }

        // check if parameter is smaller than head node
        moveToStart();
        if (curr.getValue() != null && actor_name.compareTo(curr.getValue().getActor()) < 0) {
            // if true, make new node and make it the new head
            ActorMovieIMDB newHeadNode = new ActorMovieIMDB();
            newHeadNode.setActor(actor_name);
            newHeadNode.setMovie(movie_name);
            insert(newHeadNode);
            curr.setLabel(actor_name + ":" + movie_name);
            return;
        }

        // Loop until end of list or until actor_name is greater than node's value
        moveToStart();
        if (curr.getValue() != null && actor_name.compareTo(curr.getValue().getActor()) <= 0) {
            next();
        }
        // back up one node to insert before node that is less than actor_name
        prev();
        ActorMovieIMDB nodeToInsert = new ActorMovieIMDB();
        nodeToInsert.setActor(actor_name);
        nodeToInsert.setMovie(movie_name);
        insert(nodeToInsert);
        curr.setLabel(actor_name + ":" + movie_name);

        moveToEnd();
        if (actor_name.compareTo(curr.getValue().getActor()) > 0) {
            ActorMovieIMDB newTailNode = new ActorMovieIMDB();
            newTailNode.setActor(actor_name);
            newTailNode.setMovie(movie_name);
            append(nodeToInsert);
            curr.setLabel(actor_name + ":" + movie_name);
        }
    }


    public void colorByMovieCount() {
        int movieCount = 0;

        moveToStart();
        while (getValue() != null) {
            String currActor = getValue().getActor();

            while (getValue() != null && getValue().getActor().compareTo(currActor) == 0) {
                String movieTitles = getValue().getMovie();
                for(String title : movieTitles.split("\n")) {
                    movieCount++;
                }
                next();
            }

            if (movieCount < 2) {
                curr.setColor("blue");
                curr.setSize(10);
            } else if (movieCount < 5) {
                curr.setColor("cyan");
                curr.setSize(15);
            } else if (movieCount < 10) {
                curr.setColor("green");
                curr.setSize(20);
            } else if (movieCount < 20) {
                curr.setColor("yellow");
                curr.setSize(30);
            } else if (movieCount < 30) {
                curr.setColor("orange");
                curr.setSize(40);
            } else {
                curr.setColor("brown");
                curr.setSize(50);
            }
            movieCount = 0;
        }
    }

    public void findAndInsert3(String actor_name, String movie_name) {
        if (findActor(actor_name)) {
            getValue().setMovie(getValue().getMovie() + "\n" + movie_name);
        } else {
            ActorMovieIMDB insertActorMovie = new ActorMovieIMDB();
            insertActorMovie.setActor(actor_name);
            insertActorMovie.setMovie(movie_name);
            insert(insertActorMovie);
        }
    }

    public void colorByMovieCount2() {
        int movieCount = 0;

        moveToStart();
        while (getValue() != null) {
            String currActor = getValue().getActor();

//            Print statement to ensure every node is being processed
//            System.out.println("currActor: " + currActor + " currPosition " + currPos());

            while (getValue() != null && getValue().getActor().compareTo(currActor) == 0) {

//                Print statement to ensure each node is being compared
//                System.out.println("Actor Node: " + getValue().getActor() + " currActor: " + currActor + " currPosition " + currPos());

                String movieTitles = getValue().getMovie();
                for(String title : movieTitles.split(" AND ")) {
//                    System.out.println(getValue().getActor() + " " + movieCount);
                    movieCount++;
                }
                next();
            }

            if (movieCount < 2) {
                curr.setColor("blue");
                curr.setSize(10);
            } else if (movieCount < 5) {
                curr.setColor("cyan");
                curr.setSize(15);
            } else if (movieCount < 10) {
                curr.setColor("green");
                curr.setSize(20);
            } else if (movieCount < 20) {
                curr.setColor("yellow");
                curr.setSize(30);
            } else if (movieCount < 30) {
                curr.setColor("orange");
                curr.setSize(40);
            } else {
                curr.setColor("brown");
                curr.setSize(50);
            }

            movieCount = 0;
        }
    }
}

