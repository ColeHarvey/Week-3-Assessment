import java.util.List;
import java.util.Scanner;

import controller.ListComicHelper;
import model.ListComic;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListComicHelper lih = new ListComicHelper();

		private static void addItem() {
			System.out.print("Enter a writer: ");
			String writer = in.nextLine();
			System.out.print("Enter an artist: ");
			String artist = in.nextLine();
			System.out.print("Enter a title: ");
			String title = in.nextLine();
			ListComic toAdd = new ListComic(writer, artist, title);
			lih.insertItem(toAdd);
		}

		private static void deleteItem() {
			System.out.print("Please enter the writer to delete: ");
			String writer = in.nextLine();
			System.out.print("Please enter the artist to delete: ");
			String artist = in.nextLine();
			System.out.print("Please enter the title to delete: ");
			String title = in.nextLine();
			ListComic toDelete = new ListComic(writer, artist, title);
			lih.deleteItem(toDelete);

		}

		private static void editItem() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Writer");
			System.out.println("2 : Search by Artist");
			System.out.println("3 : Search by Title");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListComic> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the writer name: ");
				String writerName = in.nextLine();
				foundItems = lih.searchForItemByWriter(writerName);
				
			} else if (searchBy == 2) {
				System.out.print("Enter the artist name: ");
				String artistName = in.nextLine();
				foundItems = lih.searchForItemByArtist(artistName);
			} else {
			System.out.print("Enter the comic title: ");
			String itemName = in.nextLine();
			foundItems = lih.searchForItemByTitle(itemName);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (ListComic l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListComic toEdit = lih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getTitle() + " from " + toEdit.getWriter());
				System.out.println("1 : Update writer");
				System.out.println("2 : Update artist");
				System.out.println("3 : Update title");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Writer: ");
					String newWriter = in.nextLine();
					toEdit.setWriter(newWriter);
				} else if (update == 2) {
					System.out.print("New Artist: ");
					String newArtist = in.nextLine();
					toEdit.setArtist(newArtist);
				} else if (update == 3) {
					System.out.print("New Title: ");
					String newTitle = in.nextLine();
					toEdit.setTitle(newTitle);
				}

				lih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("// Welcome to your list of comics! \\");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add a comic");
				System.out.println("*  2 -- Edit a comic");
				System.out.println("*  3 -- Delete a comic");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addItem();
				} else if (selection == 2) {
					editItem();
				} else if (selection == 3) {
					deleteItem();
				} else if (selection == 4) {
					viewList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewList() {
			List<ListComic> allItems = lih.showAllItems();
			for(ListComic singleItem : allItems) {
				System.out.println(singleItem.returnComicDetails());
			}
			
		}

	}