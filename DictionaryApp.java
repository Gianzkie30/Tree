import java.util.Scanner;

// This is a node class for the Binary Search Tree.
class Node {
    String word;
    String definition;
    Node left, right;

    Node(String word, String definition) {
        this.word = word;
        this.definition = definition;
        this.left = this.right = null;
    }
}

// This is the Binary Search Tree for the Dictionary.
class DictionaryBST {
    private Node root;

    // Add a word
    public void addWord(String word, String definition) {
        root = addRecursive(root, word, definition);
        System.out.println("Word '" + word + "' added successfully!");
    }

    private Node addRecursive(Node current, String word, String definition) {
        if (current == null) {
            return new Node(word, definition);
        }
        if (word.compareToIgnoreCase(current.word) < 0) {
            current.left = addRecursive(current.left, word, definition);
        } else if (word.compareToIgnoreCase(current.word) > 0) {
            current.right = addRecursive(current.right, word, definition);
        } else {
            // Word already exists, update its definition
            current.definition = definition;
            System.out.println("Word already exists. Definition updated.");
        }
        return current;
    }

    // Search for a word
    public String searchWord(String word) {
        Node found = searchRecursive(root, word);
        if (found != null) {
            return found.word + " - " + found.definition;
        }
        return "Word not found!";
    }

    private Node searchRecursive(Node current, String word) {
        if (current == null || current.word.equalsIgnoreCase(word)) {
            return current;
        }
        if (word.compareToIgnoreCase(current.word) < 0) {
            return searchRecursive(current.left, word);
        }
        return searchRecursive(current.right, word);
    }

    // Display all words in alphabetical order (in-order traversal)
    public void displayAllWords() {
        if (root == null) {
            System.out.println("The dictionary is empty.");
        } else {
            System.out.println("Dictionary List:");
            inOrderTraversal(root);
        }
    }

    private void inOrderTraversal(Node current) {
        if (current != null) {
            inOrderTraversal(current.left);
            System.out.println(current.word + ": " + current.definition);
            inOrderTraversal(current.right);
        }
    }
}

// Define main application
public class DictionaryApp {
    public static void main(String[] args) {
        DictionaryBST dictionary = new DictionaryBST();
        Scanner sc = new Scanner(System.in);
        System.out.println("<--- You are free to use my Dictionary Application --->");
        int choice;

        do {
            System.out.println("\n-- Select an Option Below --");
            System.out.println("1. Add a Word");
            System.out.println("2. Search for a Word");
            System.out.println("3. Display All Words");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4 only): ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String word = sc.nextLine();
                    System.out.print("Enter definition: ");
                    String definition = sc.nextLine();
                    dictionary.addWord(word, definition);
                    break;

                case 2:
                    System.out.print("Enter word to search: ");
                    String searchWord = sc.nextLine();
                    System.out.println(dictionary.searchWord(searchWord));
                    break;

                case 3:
                    dictionary.displayAllWords();
                    break;

                case 4:
                    System.out.println("Exiting the application. Balik-balik niya!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
    }
}
