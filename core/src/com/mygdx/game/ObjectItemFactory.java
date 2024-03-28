package com.mygdx.game;

import object.Obj_16;
import object.Obj_27;
import object.Obj_3;
import object.Obj_420;
import object.Obj_5;
import object.Obj_56;
import object.Obj_8;
import object.Obj_Boots;
import object.Obj_Chest;
import object.SuperObject;

public class ObjectItemFactory implements ObjectFactory{
	//Concrete factory for creating object items
	 private int index;
	 private GamePanel gp;

	 public ObjectItemFactory(int index, GamePanel gp) {
	     this.index = index;
	     this.gp = gp;
	 }

	 @Override
	 public SuperObject createObject() {
	     switch (index) {
	         case 0:
	             return new Obj_16();
	         case 1:
	             return new Obj_56();
	         case 2:
	             return new Obj_27();
	         case 3:
	             return new Obj_Boots();
	         case 4:
	             return new Obj_Chest();
	         case 5:
	             return new Obj_3();
	         case 6:
	             return new Obj_5();
	         case 7:
	             return new Obj_8();
	         case 8:
	             return new Obj_420();
	         default:
	             return null; // Handle unknown index
	     }
	 }
}

