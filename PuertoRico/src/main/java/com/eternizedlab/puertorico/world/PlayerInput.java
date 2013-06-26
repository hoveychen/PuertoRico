package com.eternizedlab.puertorico.world;

import com.eternizedlab.puertorico.world.role.Mayor.MayorActionable;

public interface PlayerInput extends MayorActionable{
	Role chooseRole();
	
}