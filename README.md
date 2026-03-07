# Pokémap - Custom Java Hash Map Pokédex

## Overview
This project is a custom Hash Map implementation of a Pokédex, created by Devesh Parekh. It was built to demonstrate core data structure concepts by creating a hash table from scratch rather than relying on Java's built-in `java.util.HashMap`.The program reads data for over 800 Pokémon from a CSV file and allows users to interactively search, add, and delete entries via a command-line interface

## Technical Highlights
* **Custom Hash Map (`rareHashCandy.java`)**: Implements an array-based hash table using **open addressing with linear probing** to resolve collisions
* **Hash Function**: Uses a modulo-based hash function to map keys within the array bounds.
* ** Resizing**: The table is initialized with a prime number size (1777) to help distribute keys evenly. It actively monitors the load factor ($\lambda = \frac{n}{N}$) and automatically rehashes the table when capacity exceeds 0.7 (70%) to maintain an average search efficiency of $\Theta(1)$.
* **Tombstone Deletion**: Employs a "fainted" boolean flag as a tombstone placeholder during deletions to ensure linear probing search paths remain intact.

## Features
* **Interactive Command line**: A command-line interface handling various user operations.
* **File I/O**: Parses `pokemon_pokedex_alt.csv` using UTF-8 encoding to correctly handle complex strings and characters.
* **Audio & Visuals**: Plays a healing sound effect (`Poke_heal.wav`) and displays custom ASCII art upon safely exiting the program.

## Commands
Once the program is running, you can use the following commands:

| Command | Action |
| :--- | :--- |
| **find** | Search for a Pokémon by name and return its full CSV entry. |
| **add** | Insert a new Pokémon name and entry into the Pokédex. |
| **delete** | Remove a Pokémon from the table (marks as fainted). |
| **print** | Print all active keys and entries in the Hash Map. |
| **count** | Display the total number of active Pokémon loaded. |
| **load factor** | Display the current load factor of the hash table. |
| **max load factor**| Display the maximum load factor threshold (0.7). |
| **who** | Print the author's name. |
| **help** | Display the help menu with available commands. |
| **exit** | Play the exit audio, display ASCII art, and quit the program. |
