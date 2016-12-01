package spells;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import spells.DndClass;
import com.google.gson.Gson;

/**
 * Parse text list of spells for each class by level
 * and turn it into json 
 * http://media.wizards.com/2015/downloads/dnd/DnD_SpellLists_1.0.pdf
 * @author Alex Kwon
 *
 */
public class BagOfSpells {

	public static void main(String[] args) {
		String className = null;
		Scanner reader = null;
		DndClass currentClass = null;
		String current = null;
		int currentSpellLevel = 0;
		HashMap<String, DndClass> spellBag = new HashMap<>();
		Gson gson = new Gson();
		
		try {
			File spelltext = new File("spells-by-class.txt");
			reader = new Scanner(spelltext);
		} catch (Exception e) {

			e.printStackTrace();
		}
		

		while (reader.hasNextLine()) {
			current = reader.nextLine();
			/*
			 * if the current line contains "spells", that means it's a class
			 * title and a new class should be created based on the name of the
			 * class
			 * 
			 */
			if (current.contains("Spells")) {
				/*
				 * add the class name and the object to the hashmap
				 */
				className = current.substring(0, current.indexOf(" "));
				spellBag.put(className, new DndClass(className));
				
				System.out.println(spellBag.keySet());
				
				currentClass = spellBag.get(className);

				current = reader.nextLine();


			}
			
			/*
			 * N-th level list of spells is found
			 * Special case for Cantrip/Level 0
			 * 
			 */
			if (current.contains("Level")) {
				if (current.startsWith("C")) {
					currentSpellLevel = 0;
				} else {
					currentSpellLevel = Integer.parseInt(current.substring(0,
							1));

				}

			}
			
			/*
			 * build the list of spells
			 */
			Queue<String> spellList = new LinkedList<>();

			boolean hasNextSpell = true;
			while (hasNextSpell) {
				if (reader.hasNextLine()) {
					current = reader.nextLine();
				} else {

					break;
				}

				if (spellAddConditions(current)) {
					spellList.add(current);
				} else {
					hasNextSpell = false;
				}

			}
			
			// add the found spells for the current class/level to the current class
			while (!spellList.isEmpty()) {

				String currentSpell = spellList.poll();

				currentClass.addHashSpell(currentSpell, currentSpellLevel);
	
			}
		}
		
		
		System.out.println("done " + spellBag.toString());
		reader.close();
		
		// convert the hashmap containing the classes to json and write to file
		String json = gson.toJson(spellBag);
		System.out.println(json);
		File jsonFile = new File("spells-by-class.json");
		try {
			FileWriter writer = new FileWriter(jsonFile);
			writer.write(json);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	public static boolean spellAddConditions(String current) {

		if (current.toLowerCase().contains("level")) {
			return false;
		}

		if (current.toLowerCase().contains("spells")) {
			return false;
		}

		if (current.trim().isEmpty()) {
			return false;
		}

		return true;
	}

}
