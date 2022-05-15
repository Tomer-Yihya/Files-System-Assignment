
public class tester {
	
	public static void main(String[] args) {
		
		fileSystem system = new fileSystem();
		example_test(system);
		System.out.println("\n\n");
		
		//findFolder_test(system);
		removeFolder_test(system);
		System.out.println("done!");
	}
	
	static void build_example(fileSystem system) {
		Folder myfs = new Folder("myfs");
		Folder java_project = new Folder("java-project");
		Folder src = new Folder("src");
		Folder tree = new Folder("tree");
		Folder map = new Folder("map");
		Folder favorites = new Folder("favorites");
		system.addMainFolder(myfs);
		myfs.addFolder(java_project);
		myfs.addFolder(favorites);
		src.addFolder(tree);
		src.addFolder(map);		
		java_project.addFolder(src);
		java_project.addFileToFolder(new File("readme.md"));
		tree.addFileToFolder(new File("AVLTree.java"));
		tree.addFileToFolder(new File("BinaryTree.java"));
		map.addFileToFolder(new File("HashMap.java"));
		map.addFileToFolder(new File("TreeMap.java"));
		favorites.addLinkToFolder(new Link("java-project-link"));
	}
	
	static void example_test(fileSystem system) {
		build_example(system);
		Folder temp = system.folderMap.get("myfs");
		system.printFolder(temp);
	}
		
	static void findFolder_test(fileSystem system) {
		build_example(system);
		
		System.out.println("\n\n");
		Folder temp = system.findFolder("myfs");
		if(temp ==null)
			System.out.println("folder myfs not fount");
		
		temp = system.findFolder("java-project");
		if(temp ==null)
			System.out.println("folder java-project not fount");
		
		temp = system.findFolder("src");
		if(temp ==null)
			System.out.println("src not found");
		
		temp = system.findFolder("tree");
		if(temp ==null)
			System.out.println("tree not found");
		
		temp = system.findFolder("map");
		if(temp ==null)
			System.out.println("map not found");
		
		temp = system.findFolder("favorites");
		if(temp ==null)
			System.out.println("favorites not found");
	}

	static void removeFolder_test(fileSystem system) {
		System.out.println("\n\n");
		Folder temp = system.findFolder("src");
		system.deleteFolder(temp);
		temp = system.findFolder("myfs");
		system.printFolder(temp);
	}

}
