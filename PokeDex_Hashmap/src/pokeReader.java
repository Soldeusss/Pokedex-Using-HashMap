/** The purpose of this class is to read the pokedex.csv file
 * and store  the pokemon's name as the key and
 * the entry row as its value */
import java.util.*;
import java.io.*;

public class pokeReader {
	
	public static HashMap<String, String> loadPokemonData() {
        
        HashMap<String, String> rawPokeData = new HashMap<>();
	    try (Scanner inputFile = new Scanner(new File("pokemon_pokedex_alt.csv"),"UTF-8"))
	    {
	    	if (inputFile.hasNextLine()) { 
                inputFile.nextLine();
	    	}
	    	while (inputFile.hasNextLine()) { 
	            String line = inputFile.nextLine();
	            String[] data = line.split(",");
	            //System.out.println(line); 
	            
	            String key = data[5];   // The name column
                String value = line;  // The full row
                
                rawPokeData.put(key, value);
	            }
	    } catch (FileNotFoundException e) {
        System.out.println("File Not Found!");
	    }
	    return rawPokeData;
}
}
