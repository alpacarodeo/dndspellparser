package spells;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Holds the class name, max spell level and the list of spells by level learnable.
 * @author Alex Kwon
 *
 */
public class DndClass {
	
	private String classType;
	private int maxSpellLevel;
	
	private HashMap<String, ArrayList<String>> spellList;
	
	private ArrayList<String> item;
	
	public DndClass(String classType) {
		this.classType = classType;
		maxSpellLevel = 0;
		
		//myList = new ArrayList<>();
		//item = new ArrayList<>();
		//myList.add(item);
		spellList = new HashMap<>();
	}
	
	public void addHashSpell(String spellName, int spellLevel) {
		if(spellList.containsKey("Level " + spellLevel)) {
			spellList.get("Level " + spellLevel).add(spellName);
			
			
		} else {
			spellList.put("Level " + spellLevel, new ArrayList<String>());
			spellList.get("Level " + spellLevel).add(spellName);

		}
	}

//	public void addSpell(String spellName, int spellLevel) {
//		
//		if (myList.size() < spellLevel){
//			while (myList.size() < spellLevel) {
//				ArrayList<String> list = new ArrayList<>();
//				myList.add(list);
//			}
//			System.out.println("Add spell levels to the spellbag first!");
//		}
//		ArrayList<String> list = myList.get(spellLevel);
//		list.add(spellName);
//	}
	
	
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getNumSpellLevels() {
		return maxSpellLevel;
	}

	public void setNumSpellLevels(int numSpellLevels) {
		this.maxSpellLevel = numSpellLevels;
	}

	@Override
	public String toString() {
		return "DndClass [classType=" + classType + ", numSpellLevels="
				+ maxSpellLevel + "]";
	}

}
