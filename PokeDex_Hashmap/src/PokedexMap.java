/**
*PokeDex project by Devesh Parekh
*
*The purpose of the main method is to initialize the application, load Pokémon data from a CSV file,
*and populate the Pokédex hashmap with key-value pairs
* 
* This map supports various operations, including adding, deleting, finding, and printing the entire Pokédex, 
* as well as displaying the load factor.
* 
* A switch-case structure is used to handle user inputs, and the loop continues until the user types 'exit' to
* terminate the program. The user can type 'help' to display the available functionalities. 
* 
* An audio file will also play after the user types exit*/
    
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

// implements interface
public class PokedexMap implements Pokedex {
	
	// calls hash map class
    private rareHashCandy myPokedex;
    public PokedexMap() {
        this.myPokedex = new rareHashCandy(); }
    
    
public static void main(String[] args) {
    PokedexMap myPokedexApp = new PokedexMap();
    System.out.println("Opening PokeDex...");
        
    HashMap<String, String> rawData = pokeReader.loadPokemonData();
        
       for (String pokemonName : rawData.keySet()) {
            
           String pokemonEntry = rawData.get(pokemonName);
            myPokedexApp.add(pokemonName, pokemonEntry);
        }
        System.out.println(myPokedexApp.count() + " Pokemon loaded into Pokedex.");
        Scanner userInput = new Scanner(System.in);
        System.out.println("Type 'help' to display functionalities ");
        
        
        while (true) {
            System.out.print("\nPokedex options: ");

            String move = userInput.nextLine().toLowerCase();
          
            switch (move) {
                case "find":
                    System.out.print("  Enter Pokemon name: ");
                    String name = userInput.nextLine();
                    String result = myPokedexApp.find(name);
                    if (result != null) {
                        System.out.println("  Found: " + result);
                    } else {
                        System.out.println("  '" + name + "' not found in Pokedex.");
                    }
                    break; 

                case "add":
                    System.out.print("  Enter Pokemon name: ");
                    String addPoke = userInput.nextLine();
                    System.out.print("  Enter Pokedex entry (CSV line): ");
                    String entry = userInput.nextLine();
                    
                    if (myPokedexApp.add(addPoke, entry)) {
                        System.out.println("  '" + addPoke + "' was added successfuly.");
                    } else {
                        System.out.println("  Failed to add '" + addPoke + "'.");
                    }
                    break; 

                case "delete":
                    System.out.print("  Enter Pokemon name: ");
                    String delName = userInput.nextLine();
                    if (myPokedexApp.delete(delName)) {
                        System.out.println("  '" + delName + "' has fainted (deleted).");
                    } else {
                        System.out.println("  '" + delName + "' was not found, so it could not be deleted.");
                    }
                    break;

                case "print":
                    myPokedexApp.printHT();
                    break;

                case "count":
                    System.out.println("  Pokedex contains " + myPokedexApp.count() + " active Pokemon.");
                    break;

                case "load factor":
                    System.out.println("  Current load factor: " + myPokedexApp.getLoadFactor());
                    break;

                case "max load factor":
                    System.out.println("  Max load factor: " + myPokedexApp.getMaxLoadFactor());
                    break;

                case "who":
                    myPokedexApp.who();
                    break;

                case "help":
                    myPokedexApp.help(); 
                    break;

                case "exit":
                    myPokedexApp.exit(); 
                    break;

                default:
                    System.out.println("Unknown input. Type 'help' for a list of commands.");
                    break;          
            }
            if (move.equals("exit")) {
                userInput.close();  // Close the scanner
                break;  
            } 
        }
    }


    @Override
    public String find(String pokemon) {
        return this.myPokedex.find(pokemon);
    }

    @Override
    public boolean add(String pokemon, String entry) {
        return this.myPokedex.add(pokemon, entry);
    }
  
    @Override
    public boolean delete(String pokemon) {
        return this.myPokedex.delete(pokemon);
    }
    
    @Override
    public void printHT() {
        this.myPokedex.printHT();
    }
   
    @Override
    public double getLoadFactor() {
        return this.myPokedex.getLoadFactor();
    }

    @Override
    public double getMaxLoadFactor() {
        return this.myPokedex.getMaxLoadFactor();
    }

    @Override
    public int count() {
        return this.myPokedex.count();
    }

    @Override
    public void who() {
   
        System.out.println("Pokédex Hashmap designed by Devesh Parekh");
    }

    @Override
    public void help() {
        System.out.println("--- Pokédex Help Menu ---");
        System.out.println();

        System.out.println("Commands:");
        System.out.println("  ------------------------------");
        System.out.printf("  %-15s - %s\n", "find", "Search for a Pokémon by name.");
        System.out.printf("  %-15s - %s\n", "add", "Add a new Pokémon entry.");
        System.out.printf("  %-15s - %s\n", "delete", "Delete a Pokémon entry.");
        System.out.println("  ------------------------------");
        
        System.out.println("Status Options:");
        System.out.println("  ------------------------------");
        System.out.printf("  %-15s - %s\n", "print", "Show the entire hash table.");
        System.out.printf("  %-15s - %s\n", "count", "Show how many Pokémon are in the Pokedex.");
        System.out.printf("  %-15s - %s\n", "load factor", "Show the current load factor.");
        System.out.printf("  %-15s - %s\n", "max load factor", "Show the maximum allowed load factor.");
        System.out.println("  ------------------------------");

        System.out.println("Information:");
        System.out.println("  ------------------------------");
        System.out.printf("  %-15s - %s\n", "who", "Show the author's name.");
        System.out.printf("  %-15s - %s\n", "help", "Show this help menu.");
        System.out.printf("  %-15s - %s\n", "exit", "Quit the program.");
        System.out.println("  ------------------------------");
    }

    
    /**method to play a sound when user types exit */
    
   public void playSound(String pika) {
        try {
            File audioFile = new File("Poke_heal.wav");        
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            
            clip.open(audioStream);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1200);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    @Override
    public void exit() {
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠤⠒⠊⠉⠀⢀⠴⠊⠉⠋⠙⢿⡖⠦⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠊⠁⠀⠀⠀⠀⡠⠚⠁⠀⠀⠀⠀⠀⠀⠹⡄⠀⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⢀⠔⠋⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⠀⠀⠀⠈⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⡏⠀⠀⠀⠀⠀⢀⣾⠶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⢻⣆⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠠⡇⠀⠀⠀⢀⣴⢿⠁⠀⠈⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡇⠀⠀⠀⠀⢸⣿⠀⢀⡴⠋⢣⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⢾⣧⣘⡀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⠀⠀⠀⠀⢸⣿⠖⠁⠀⠀⠀⢣⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⢀⣇⠀⠀⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⣼⡇⠀⠀⠀⠀⠀⠀⢣⠀⠀⠀");
        System.out.println("⠀⠀⠀⣀⣤⣶⠟⠋⠓⠦⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣦⣶⣿⠤⠖⠚⠉⠙⡆⠀⠀⠀⠀⠀⠀⠀⢣⠀⠀");
        System.out.println("⠀⢠⣾⣟⠁⠃⠀⠀⠀⠀⠀⠈⠉⠉⠓⠒⠒⠒⠒⠒⠒⣶⡶⢿⠉⠉⠀⠈⢆⠀⠀⠀⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⢣⠀");
        System.out.println("⠀⠀⠙⣿⢿⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣴⡖⣻⡟⠀⢸⠀⠀⠀⠀⠈⡆⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⣨⠇");
        System.out.println("⠀⠀⠀⢹⡀⠀⠉⠉⡿⠲⣶⠒⠒⠒⣿⡏⠉⠁⠀⠀⠉⢉⡴⠒⢺⠀⠀⠀⠀⠀⢹⠀⠀⠀⣼⡇⠀⠀⠀⠀⠀⠀⣬⠃⠀");
        System.out.println("⠀⠀⠀⠀⠳⡀⠀⠀⢧⠀⡾⠀⠀⣀⣠⡀⠀⠀⠀⠀⠀⠸⣦⣀⣸⡇⠀⠀⠀⠀⠈⡆⠀⣰⣿⡇⠀⠀⠀⠀⢀⡴⠁⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠱⡀⠀⠈⢯⡀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠉⠛⠛⢳⠀⠀⠀⠀⠀⣷⣴⣿⣿⡇⠀⠀⠀⢀⡟⠁⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣱⡀⠀⠀⠑⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⣿⠛⠿⣿⡇⠀⠀⢠⡟⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⣿⢦⡀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠀⠀⠀⠀⣹⠀⠀⠈⠁⠀⢠⠎⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠉⠁⠀⢙⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡀⠀⠀⠀⣰⠋⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡟⢦⡀⠈⢣⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⣀⡀⠀⠀⣀⠤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢧⣴⣿⣷⠾⠃⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⡼⠟⠳⣀⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠟⠋⢁⣀⡀⢀⣀⡀⠀⠀⠀⠀");
        System.out.println("⡇⠀⡀⠱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠿⠟⠳⡀⠀⠀⠀⠀⠀⠀⢸⣤⡞⠉⠀⠀⠰⡟⠙⠳⣄⠀⠀");
        System.out.println("⢹⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⢳⠀⠀⠀⠀⠀⢀⣼⣿⡿⠀⠀⠀⠀⠹⣄⠀⢹⣇⠀");
        System.out.println("⠘⡆⢰⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⠀⠀⠀⣠⣾⣿⣿⠇⠀⠀⠀⠀⢀⠈⠙⣿⣿⠀");
        System.out.println("⠀⠸⡄⢹⠆⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⢸⠀⠤⠊⢁⠟⣿⢿⣦⣀⣀⣾⣿⠟⣧⣴⠿⡯⠀");
        System.out.println("⠀⠀⠉⠉⠳⢄⡙⠢⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢆⠀⠀⢸⠀⢀⡠⠊⠀⠘⢦⣢⡈⠉⠻⢿⠿⠋⢀⠔⠁⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠈⠙⠒⠒⠶⠒⠒⠒⠈⠉⠉⠉⠉⠉⠉⠒⠒⠪⠷⠒⠛⠊⠉⠀⠠⠄⠀⠤⣌⠛⠳⠦⠤⠴⠒⠁⠀⠀⠀");
        playSound("Poke_heal.wav");
        System.out.println("Pokédex exited. Goodbye!"); // timed it so this wont print until audio file finishes playing
        System.exit(0);
    }
}