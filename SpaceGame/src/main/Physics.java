package main;

import java.util.LinkedList;

import classes.EntityA;
import classes.EntityB;

public class Physics { // this is goin to handle whether we ahve collision or
						// not

	public static boolean Collision(EntityA enta, LinkedList<EntityB> entb) {

		for (int i = 0; i < entb.size(); i++) {// denna loopar genom hela A
												// klassen

			if (enta.getBounds().intersects(entb.get(i).getBounds())) {
				return true;
			}
		}

		return false;

	}
	public static boolean Collision(EntityB entb, LinkedList<EntityA> enta) {

		for (int i = 0; i < enta.size(); i++) {// denna loopar genom hela A
												// klassen

			if (entb.getBounds().intersects(enta.get(i).getBounds())) {
				return true;
			}
		}

		return false;

	}

}
