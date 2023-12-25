package DoubleLinkedSequence;


public class DoubleLinkedSeq implements Cloneable {

    private int manyNodes;
    private DoubleNode head;
    private DoubleNode tail;
    private DoubleNode cursor;
    private DoubleNode precursor;

   /**
   * Initialize an empty sequence.
   * <b>Postcondition:</b>
   *   This sequence is empty.
   **/   
    public DoubleLinkedSeq() {
       manyNodes = 0;
       head = null;
       tail = null;
       cursor = null;
       precursor = null;
   }

   /**
    * Add a new element to this sequence, after the current element.
    * 
    * @param element
    * the new element that is being added
    * <b>Postcondition:</b>
    * A new copy of the element has been added to this sequence. If
    * there was
    * a current element, then the new element is placed after the
    * current
    * element. If there was no current element, then the new element
    * is placed
    * at the end of the sequence. In all cases, the new element
    * becomes the
    * new current element of this sequence.
    * @exception OutOfMemoryError
    *                             Indicates insufficient memory for a new node.
    **/
   public void addAfter(double element) {
       DoubleNode newNode = new DoubleNode(element, null);
       if (manyNodes == 0) {
           head = newNode;
           tail = newNode;
           cursor = newNode;
           precursor = null;
       } else if (cursor == tail) {
           tail.setLink(newNode);
           tail = newNode;
           precursor = cursor;
           cursor = newNode;
       } else {
           newNode.setLink(cursor.getLink());
           cursor.setLink(newNode);
           precursor = cursor;
           cursor = newNode;
       }
       manyNodes++;
   }

   /**
   * Add a new element to this sequence, before the current element. 
   * @param element
   *   the new element that is being added
   * <b>Postcondition:</b>
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed before the current
   *   element. If there was no current element, then the new element is placed
   *   at the start of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for a new node.
   **/
   public void addBefore(double element) {
       DoubleNode newNode = new DoubleNode(element, null);
       if (manyNodes == 0 || cursor == head) {
           newNode.setLink(head);
           head = newNode;
       } else {
           precursor.setLink(newNode);
           newNode.setLink(cursor);
       }
       cursor = newNode;
       manyNodes++;
   }

   /**
   * Place the contents of another sequence at the end of this sequence.
   * @param addend
   *   a sequence whose contents will be placed at the end of this sequence
   * <b>Precondition:</b>
   *   The parameter, <CODE>addend</CODE>, is not null. 
   * <b>Postcondition:</b>
   *   The elements from <CODE>addend</CODE> have been placed at the end of 
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the <CODE>addend</CODE> is also unchanged.
   * @exception NullPointerException
   *   Indicates that <CODE>addend</CODE> is null. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this sequence.
   **/
   public void addAll(DoubleLinkedSeq addend) {
       if (addend == null) {
           throw new NullPointerException("Addend is null");
       }

       // If the current sequence is empty, set it's head to the head of addend.
       if (head == null) {
           head = addend.head;
       } else {
           // Find the last node of the current sequence.
           DoubleNode currentLast = head;
           while (currentLast.getLink() != null) {
               currentLast = currentLast.getLink();
           }
           // Link the last node of the current sequence to the head of addend.
           currentLast.setLink(addend.head);
       }
       manyNodes = manyNodes + addend.manyNodes;
   }

   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * <b>Precondition:</b>
   *   <CODE>isCurrent()</CODE> returns true. 
   * <b>Postcondition:</b>
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   <CODE>advance</CODE> may not be called.
   **/
   public void advance() {
       if (!isCurrent()) {
           throw new IllegalStateException("No current element to advance");
       } else {
           precursor = cursor;
           cursor = cursor.getLink();
       }
   }
   
   /**
   * Generate a copy of this sequence.
   * @return
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to a <CODE>DoubleLinkedSeq</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public DoubleLinkedSeq clone() {
       DoubleLinkedSeq answer;
       try {
           answer = (DoubleLinkedSeq) super.clone();
       } catch (CloneNotSupportedException e) {
           throw new RuntimeException("Cloneable not supported");
       }
       // The original sequence has no current element
       if (!isCurrent()) {
           answer.head = DoubleNode.listCopy(head);
           answer.cursor = null;
           answer.precursor = null;
       } else if (precursor == null) {
           // If the current element is the first element
           DoubleNode[] copy = DoubleNode.listCopyWithTail(head);
           answer.head = copy[0];
           answer.cursor = answer.head;
           answer.precursor = null;
       } else {
           // If the current element is after the first element
           DoubleNode[] copy1 = DoubleNode.listPart(head, precursor);
           DoubleNode[] copy2 = DoubleNode.listCopyWithTail(cursor);
           copy1[1].setLink(copy2[0]);

           answer.head = copy1[0];
           answer.cursor = copy2[0];
           answer.precursor = null;
       }
       return answer;
   }

   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * @param s1
   *   the first of two sequences
   * @param s2
   *   the second of two sequences
   * <b>Precondition:</b>
   *   Neither s1 nor s2 is null.
   * @return
   *   a new sequence that has the elements of <CODE>s1</CODE> followed by the
   *   elements of <CODE>s2</CODE> (with no current element)
   * @exception NullPointerException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new sequence.
   **/   
  public static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) {
    if (s1 == null || s2 == null) {
        throw new NullPointerException("One of the sequences in null");
    }
    DoubleLinkedSeq answer = new DoubleLinkedSeq();
    answer.addAll(s1);
    answer.addAll(s2);
    return answer;
}

   /**
   * Accessor method to get the current element of this sequence. 
   * <b>Precondition:</b>
   *   <CODE>isCurrent()</CODE> returns true.
   * @return
   *   the current capacity of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   <CODE>getCurrent</CODE> may not be called.
   **/
   public double getCurrent() {
       if (isCurrent()) {
           return cursor.getData();
       } else {
           throw new IllegalStateException("There is no current element");
       }
   }

   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * <CODE>getCurrent</CODE> method. 
   * @return
   *   true (there is a current element) or false (there is no current element at the moment)
   **/
   public boolean isCurrent() {
       // If cursor does not equal null, then there is a current element
       if (cursor != null) {
           return true;
       } else {
           return false;
       }
   }
              
   /**
   * Remove the current element from this sequence.
   * <b>Precondition:</b>
   *   <CODE>isCurrent()</CODE> returns true.
   * <b>Postcondition:</b>
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   <CODE>removeCurrent</CODE> may not be called. 
   **/
    public void removeCurrent() {
       if (!isCurrent()) {
           throw new IllegalStateException("There is no current element.");
       } else {
           // Only one element
           if (head == tail) {
               head = null;
               tail = null;
               cursor = null;
               manyNodes--;
           }
           // Cursor points to head
           else if (cursor == head && precursor == null) {
               head = head.getLink();
               cursor = head;
               manyNodes--;
           }
           // If cursor does not equal tail then cursor equals next element after
           else if (cursor != tail && precursor != null) {
               cursor = cursor.getLink();
               precursor.setLink(cursor);
               manyNodes--;
           }
           // If cursor equals tail then cursor & precursor equal null after
           else if (cursor == tail && precursor != null) {
               cursor = null;
               precursor.setLink(null);
               precursor = null;
               manyNodes--;
           }
       }
   }
                 
   /**
   * Determine the number of elements in this sequence.
   * @return
   *   the number of elements in this sequence
   **/ 
    public int size() {
       return manyNodes;
   }
   
   /**
   * Set the current element at the front of this sequence.
   * <b>Postcondition:</b>
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/ 
   public void start() {
       if (head == null) {
           cursor = null;
           precursor = null;
       } else {
           cursor = head;
           precursor = null;
       }
   }

   public void print() {
       System.out.println("length = " + manyNodes);

       if (isCurrent()) {
           System.out.println("current element = " + cursor.getData());
       } else {
           System.out.println("there is no current element");
       }

       System.out.print("elements: ");
       DoubleNode current = head;
       while (current != null) {
           System.out.print(current.getData() + " ");
           current = current.getLink();
       }

       System.out.println("");
       System.out.println("");
   }
}