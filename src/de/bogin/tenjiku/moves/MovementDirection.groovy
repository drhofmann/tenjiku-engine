package de.bogin.tenjiku.moves;

import de.bogin.tenjiku.pieces.PlayerColour;

public enum MovementDirection {
	FRONT, BACK, FRONT_RIGHT, FRONT_LEFT, BACK_RIGHT, BACK_LEFT, LEFT, RIGHT;

	List<MovementDirection> getOrthogonalDirections(){
		List<MovementDirection> result = new ArrayList<MovementDirection>()
		switch (this){
			case [FRONT, BACK]:
			result << MovementDirection.LEFT
			result << MovementDirection.RIGHT
			break

			case [LEFT, RIGHT]:
			result  << MovementDirection.FRONT
			result  << MovementDirection.BACK
			break

			case [FRONT_LEFT, BACK_RIGHT]:
			result << MovementDirection.FRONT_RIGHT
			result << MovementDirection.BACK_LEFT
			break

			case [FRONT_RIGHT, BACK_LEFT]:
			result << MovementDirection.FRONT_LEFT
			result << MovementDirection.BACK_RIGHT
			break

			default:
			throw new IllegalArgumentException ("Unknown direction $this")
		}
		return result;
	}
}
