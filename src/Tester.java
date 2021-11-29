
/**
 * Write a description of class Tester here.
 *
 * @author Dan Kolan
 * @version 1.0
 */
import java.util.ArrayList;
import java.lang.String;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import bridges.base.Element;
import bridges.base.SLelement;
import bridges.base.GraphAdjListSimple;
import bridges.base.Edge;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;

public class Tester {
	public static void main(String[] args) throws Exception {

								// initialize Bridges
		Bridges bridges = new Bridges(12, "kolan_daniel", "442237426692");
		DataSource ds = bridges.getDataSource();

								// set a title for the visualization
		bridges.setTitle("Singly Linked List using IMDB Actor/Movie Data");

								// use BRIDGES to retrieve the actor movie data
		ArrayList<ActorMovieIMDB>  actor_movie_data =
					(ArrayList<ActorMovieIMDB>) ds.getActorMovieIMDBData(1813);

		Collections.sort(actor_movie_data, new Comparator<ActorMovieIMDB>() {
			@Override
			public int compare(ActorMovieIMDB o1, ActorMovieIMDB o2) {
				return o1.getActor().compareTo(o2.getActor());
			}
		});

		bridges.setTitle("Singly Linked List using IMDB Actor/Movie Data");

		System.out.println("Size:" + actor_movie_data.size());
		ListOps actor_list = new ListOps();

		// Read and build linked list of actors/movies
		for (int i = 0; i < actor_movie_data.size(); i++) {
			actor_list.findAndInsert3(actor_movie_data.get(i).getActor(), actor_movie_data.get(i).getMovie());
		}

		// Set labels for nodes
		actor_list.moveToStart();
		while (actor_list.getValue() != null) {
			actor_list.curr.setLabel(actor_list.getValue().getActor() + ":" + actor_list.getValue().getMovie());
			actor_list.next();
		}

		//  Color the list according to movie count
		actor_list.colorByMovieCount();

		// Array of 3 actors to be found, 3 to not be found
		String[] actorsToFind = new String[] {"Ned_Bellamy", "John_Cusack", "Andy_Dick", "James_Gandolfini",
										      "Tony_Sirico", "Steven_Van_Zandt"};

		// Test of finding 3 actors in list, 3 actors not in list
		for(String actor : actorsToFind) {
			if (actor_list.findActor(actor)) {
				actor_list.curr.setSize(50);
				actor_list.curr.setColor("red");
				actor_list.curr.setShape("triangle");
			}
		}

	    // Build visualized list
		bridges.setDataStructure(actor_list.getFront());
		bridges.visualize();
	}
}