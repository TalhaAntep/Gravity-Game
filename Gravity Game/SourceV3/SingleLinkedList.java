public class SingleLinkedList {

	Node head;
	
    public int countLetters() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count += ((String)current.getData()).length();
            current = current.getLink();
        }
        return count;
    }
	public Node getTail() {
	    Node current = head;
	    while (current != null && current.getLink() != null) {
	        current = current.getLink();
	    }
	    return current;
	}

	public void deleteTail() {
	    if (head == null) {
	        return;
	    } else if (head.getLink() == null) {
	        head = null;
	    } else {
	        Node current = head;
	        while (current.getLink().getLink() != null) {
	            current = current.getLink();
	        }
	        current.setLink(null);
	    }
	}

	public void add(Object dataToAdd) {
		Node newNode = new Node(dataToAdd);
		if (head == null) { // at first
			head = newNode;
		}
	    else {
	        Node current = head;
	        while (current.getLink() != null && dataToAdd.toString().compareTo(current.getLink().getData().toString()) > 0) {
	            current = current.getLink();
	        }
	        newNode.setLink(current.getLink());
	        current.setLink(newNode);
	    }
	}

	public int size() {

		if (head == null) {
			return 0;
		} else {

			int count = 0;
			Node temp = head;
			while (temp != null) {
				temp = temp.getLink();
				count++;
			}
			return count;
		}
	}

	public void display() {

		if (head == null) {
			System.out.print(' ');
		}

		else {
			Node temp = head;
			while (temp != null) {
				System.out.print(temp.getData() + " ");
				temp = temp.getLink();
			}
		}
	}
	
	public boolean search(Object item) {
	    Node current = head;
	    while (current != null) {
	        if (current.getData().equals(item)) {
	            return true;
	        }
	        current = current.getLink();
	    }
	    return false;
	}

	public void delete(Object dataToDelete) {
	    // handle the case where the list is empty
	    if (head == null) {
	        System.out.println("Linked list is empty");
	        return;
	    }
	    
	    // handle the case where the node to delete is the head node
	    while (head.getData().toString().equals(dataToDelete.toString())) {
	        head = head.getLink();
	        if (head == null) {
	            return;
	        }
	    }
	    
	    // find the node to delete and its previous node
	    Node current = head;
	    Node previous = null;
	    while (current != null) {
	        if (current.getData().toString().equals(dataToDelete.toString())) {
	            break;
	        }
	        previous = current;
	        current = current.getLink();
	    }
	    
	    // handle the case where the node to delete is not found
	    if (current == null) {
	        return;
	    }
	    
	    // remove the node by updating the link of the previous node
	    previous.setLink(current.getLink());
	}


}
