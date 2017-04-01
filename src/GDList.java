
/**
 * GDList is a generic doubly linked list. All elements are distinct
 **/

public class GDList<E> implements Cloneable
{
	/**
	 * Nested class GNode is a generic class representing a node in a list E is
	 * generic type parameter of data Has both previous and next pointers
	 **/
	private static class GNode<E>
	{
		private E data;
		private GNode<E> previous;
		private GNode<E> next;

		// constructor
		public GNode(E e)
		{
			data = e;
			previous = null;
			next = null;
		}

		@SuppressWarnings("unused")
		public E getData()
		{
			return data;
		}

		public GNode<E> getPrevious()
		{
			return previous;
		}

		public GNode<E> getNext()
		{
			return next;
		}

		@SuppressWarnings("unused")
		public void setData(E e)
		{
			data = e;
		}

		public void setPrevious(GNode<E> p)
		{
			previous = p;
		}

		public void setNext(GNode<E> p)
		{
			next = p;
		}
	}

	private GNode<E> head;
	private GNode<E> tail;
	private int size; // number of nodes in a list

	/**
	 * no-arg constructor creates an empty list
	 **/
	public GDList()
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Method to add a new Node to the head of a list.
	 * 
	 * @param e
	 *            - data contained in node.
	 * @return (0) a new Node with data e is added to the head of the list
	 *         (become head), size incremented. (1) a Node with data e already
	 *         exist (do nothing).
	 */
	public int addToHead(E e)
	{
		GNode<E> temp = new GNode<E>(e);

		// empty list
		if (head == null)
		{
			head = temp;
			tail = temp;
		} else
		{
			// nodes already in list
			if (findNode(head, e) == null)
			{
				//
				temp.setNext(head);
				head.setPrevious(temp);
				head = temp;
			} else
				return 1;
		}
		size++;
		return 0;
	}

	/**
	 * Method to add a new Node to the tail of a list.
	 * 
	 * @param e
	 *            - data contained in node.
	 * @return (0) a new Node with data e is added to the tail of the list
	 *         (becomes tail), size incremented. (1) a Node with data e already
	 *         exist (do nothing).
	 */
	public int addToTail(E e)
	{
		GNode<E> temp = new GNode<E>(e);

		if (head == null)
		{
			head = temp;
			tail = temp;
		} else
		{
			if (findNode(head, e) == null)
			{
				temp.setPrevious(tail);
				tail.setNext(temp);
				tail = temp;
			} else
				return 1;
		}
		size++;
		return 0;
	}

	/**
	 * Method to add a new Node to the list, following a Node specified by its
	 * data.
	 * 
	 * @param n
	 *            - Node with attributes specified in generic GNode<E> class
	 * @param e
	 *            - data contained in node.
	 * @return (0) a new Node with data e is added to after the node specified,
	 *         size incremented. (1) a Node specified is null, or a Node with
	 *         data e already exist (do nothing).
	 */
	public int addAfter(GNode<E> n, E e)
	{

		if (n == null)
			throw new IllegalArgumentException("The node n cannot be null");

		if (findNode(head, e) != null)
			return 1;

		if (n == tail)
		{
			addToTail(e);
		} else
		{
			GNode<E> temp = new GNode<E>(e);
			GNode<E> tempNext = n.getNext();
			temp.setNext(tempNext);
			tempNext.setPrevious(temp);
			temp.setPrevious(n);
			n.setNext(temp);
			size++;
		}
		return 0;
	}

	/**
	 * Method to add a new Node to the list, before a Node specified by its
	 * data.
	 * 
	 * @param n
	 *            - Node with attributes specified in generic GNode<E> class
	 * @param e
	 *            - data contained in node.
	 * @return (0) a new Node with data e is added to before the node specified,
	 *         size incremented. (1) a Node specified is null, or a Node with
	 *         data e already exist (do nothing).
	 */
	public int addBefore(GNode<E> n, E e)
	{
		if (n == null)
			throw new IllegalArgumentException("The node n cannot be null");

		if (findNode(head, e) != null)
			return 1;

		if (n == head)
			addToHead(e);

		else
		{
			GNode<E> temp = new GNode<E>(e);
			GNode<E> tempPrevious = n.getPrevious();
			temp.setNext(n);
			n.setPrevious(temp);
			tempPrevious.setNext(temp);
			temp.setPrevious(tempPrevious);
			size++;
		}
		return 0;
	}

	/**
	 * Method to delete the node with data e. If a node with e does not exist,
	 * return null. If exists, delete the node and return the pointer to the
	 * deleted node and decrement the size.
	 * 
	 * @param e
	 *            - data contained in node.
	 * @return null
	 * @throws IllegalArgumentException
	 *             "(e) data entered not valid".
	 */
	public GNode<E> deleteNode(E e)
	{
		GNode<E> temp = findNode(head, e);

		// data value entered not in list
		if (temp == null)
			return null;

		else
		{
			// delete head node
			if (head == temp)
			{
				head = temp.next;
			}
			// delete middle node
			if (temp.previous != null)
			{
				temp.previous.setNext(temp.next);
			}
			// delete tail node
			if (temp.next != null)
			{
				temp.next.setPrevious(temp.previous);
			}

			size--; // decrement the size
		}
		return null;
	}

	/**
	 * Method to delete the Node immediately following the Node specified by its
	 * data.
	 * 
	 * @param e
	 *            - data contained in node.
	 * @return Pointer returned to the deleted node and the size of the list is
	 *         decremented. If the node specified is the tail, or the if a Node
	 *         with e does not exist return null.
	 */
	public GNode<E> deleteAfter(E e)
	{
		GNode<E> temp = findNode(head, e);

		if (temp == tail || temp == null)
			return null;

		return (deleteNode((E) temp.getNext().data));
	}

	/**
	 * Method to delete the Node immediately following the Node specified by its
	 * data.
	 * 
	 * @param e
	 *            - data contained in node.
	 * @return Pointer returned to the deleted node and the size of the list is
	 *         decremented. If the node specified is the had, or the if a Node
	 *         with e does not exist return null.
	 */
	public GNode<E> deleteBefore(E e)
	{
		GNode<E> temp = findNode(head, e);

		if (temp == head || temp == null)
			return null;

		return (deleteNode((E) temp.getPrevious().data));
	}

	/**
	 * Method to find a Node - searches for the data contained in the Node.
	 * 
	 * @param p
	 *            - first node in the list (head)
	 * @param e
	 *            - data contained in node.
	 * @return Pointer returned to the Node specified. if a Node p, or a Node
	 *         with data e does not exist return null.
	 */
	public GNode<E> findNode(GNode<E> p, E e)
	{
		GNode<E> current = p;
		while (current != null && current.data != e)
			current = current.getNext();
		return current;
	}
	
	/**
	 * Method to find course node using the course name
	 * @param courseName - String (name of course)
	 * @return course Node containing course object record to be removed
	 */
	public Student.Course findCourseNode(String courseName)
	{
        if(head != null)
        {
            GNode<E> current = head;
            Student.Course courses = (Student.Course)current.data;
            while (current != null)
            {
                if(courses.getCourseName().equals(courseName))
                {
                    break;
                }
                else
                {
                    current = current.getNext();
                    courses = (Student.Course)current.data;
                }
            }
            return courses;
        }
        else
        {
            return null;
        }
	}

	/**
	 * Method to exchange Node 1 & Node 2
	 * 
	 * @param n1
	 *            - Node 1, identified by data contained (e)
	 * @param n2
	 *            - Node 2, identified by data contained (e)
	 * @precondition - Node 1 or Node 2 cannot be null
	 * @postcondition - The nodes, included data, and links are swapped.
	 */
	public void exchange(GNode<E> n1, GNode<E> n2)
	{
		GNode<E> n1Next = n1.getNext();
		GNode<E> n1Prev = n1.getPrevious();
		GNode<E> n2Next = n2.getNext();
		GNode<E> n2Prev = n2.getPrevious();

		if (n1Prev != null && n2Next != null)
		{ // n1 & n2 are in the middle of the list
			n1Prev.setNext(n2);
			n2.setPrevious(n1Prev);
			n2.setNext(n1Next);
			n1Next.setPrevious(n2);

			n2Prev.setNext(n1);
			n1.setPrevious(n2Prev);
			n1.setNext(n2Next);
			n2Next.setPrevious(n1);
		} else
			if (n1Prev == null && n2Next != null)
			{ // n1 is the head && n2 is in the middle
				n2.setNext(head);
				head.setPrevious(n2);
				head = n2;

				n2.setNext(n1Next);
				n1Next.setPrevious(n2);

				n2Prev.setNext(n1);
				n1.setPrevious(n2Prev);
				n1.setNext(n2Next);
				n2Next.setPrevious(n1);
			} else
				if (n1Prev != null && n2Next == null)
				{ // n1 is in the middle && n2 is the tail
					n1.setPrevious(tail);
					tail.setNext(n1);
					tail = n1;

					n1Prev.setNext(n2);
					n2.setPrevious(n1Prev);
					n2.setNext(n1Next);
					n1Next.setPrevious(n2);

					n2Prev.setNext(n1);
					n1.setPrevious(n2Prev);
					n1.setNext(n2Next);
				} else
					if (n1Prev == null && n2Next == null)
					{ // n1 is the head && n2 is the tail
						n2.setNext(head);
						head.setPrevious(n2);
						head = n2;

						n1.setPrevious(tail);
						tail.setNext(n1);
						tail = n1;

						n2.setNext(n1Next);
						n1Next.setPrevious(n2);

						n2Prev.setNext(n1);
						n1.setPrevious(n2Prev);

						n1.setNext(null);
						n2.setPrevious(null);
					}
	} // End Method: exchange()

	/**
	 * Method to add a new Node with data e at in a specified position.
	 * 
	 * @param e
	 *            - The data contained in the new Node being added.
	 * @param pos
	 *            - The location of the new Node, starting at the head, where
	 *            the position of the head is 0. - (pos >= 0 if a node with e
	 *            already exists).
	 * @return (1) - If a node already exist in the list with data specified.
	 *         (0) - The data specified is unique - a new Node containing this
	 *         data is created at the position specified, and the size of the
	 *         list is incremented.
	 */
	public int addPos(E e, int pos)
	{
		// value not found in list --> add new node & data at specified pos
		if (findNode(head, e) == null)
		{

			if (pos < 0)
			{
				throw new IllegalArgumentException("Index cannot be less than zero");
			}
			if (pos > size)
			{
				throw new IllegalArgumentException("pos cannot be greater than size of list");
			}
			if (pos == 0) // add to head
			{
				addToHead(e);

			} else
				if (pos == size) // add to tail
				{
					addToTail(e);

				} else
				{ // add node at pos specified (in the middle)
					GNode<E> temp = head;
					for (int i = 0; i < pos; i++)
					{
						temp = temp.next; // locate existing node at pos
											// specified --> assign to temp.
					}
					GNode<E> newNode = new GNode<E>(e);
					newNode.setNext(temp); // add link from newNode to temp (
											// [newNode] --> [temp] )
					newNode.setPrevious(temp.previous); // add link from
														// previous node to
														// newNode ( [previous]
														// --> [newNode] )
					temp.previous.setNext(newNode); // add link from previous to
													// newNode
					size++; // increment size of list
				}

		} else // Value found in list --> return 1
			return 1;

		// size++;
		return 0;
	} // end method: addPos()

	/**
	 * Method to replace an existing Node with a new Node, containing data (e),
	 * at the position specified.
	 * 
	 * @param e
	 *            - The data contained in the new Node being added.
	 * @param pos
	 *            - The location of the new Node, starting at the head, where
	 *            the position of the head is 0 - (pos >= 0 if a node with e
	 *            already exists).
	 * @return - pointer to the replaced Node. If a node does not exist at the
	 *         specified location return null.
	 */
	public GNode<E> replacePos(E e, int pos)
	{
		if (pos < 0)
			return null;

		else

			if (pos == 0) // replace Node at head
			{
				addPos(e, pos); // add new node in pos specified
				deleteAfter(e); // delete Node previously in specified pos
			}

			else // replace Node at tail
				if (pos == size - 1)
				{
					addPos(e, pos); // add new node in pos specified
					// delete node after new node & decrement size
					GNode<E> temp = findNode(head, e);
					if (e != head)
					{
						temp.setNext(temp.next.next);
						size--;
					}
				} else // replace Node in middle
				{
					addPos(e, pos); // add new node in pos specified
					// delete node after new node & decrement size

					System.out.println("This is middle operation");

					GNode<E> temp = findNode(head, e);
					if (e != head)
					{
						temp.setNext(temp.next.next);
						size--;
					}
				}

		return head;
	} // End method: replacePos()

	
	/**
	 * Method to display data in current list, arrange from head to tail.
	 * @param id - Student object String (unique)
	 * @param student - Student object
	 */
	public void printList(String id, Student student)
    {
        if (head != null)
        {
            GNode<E> current = head;
            Student.Course courses = (Student.Course)current.data;
            
            for (int i = 0; i<size; i++)
            {
                courses.displayCourse();
                current = current.getNext();
                if (current!=null) // only set new course if next value is not null
                {
                	courses = (Student.Course)current.data;
                }
                
                System.out.println();
            }
        }
    }
	
}