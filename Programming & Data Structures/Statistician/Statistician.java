package Statistician;

public class Statistician {
   /**
   * Initialize a new Statistician.  
   * <b>Postcondition:</b>
   *   This Statistician is newly initialized and has not yet been given any 
   *   numbers.
   **/   

    private int length;
    private double sum;
    private double min;
    private double max;

    public Statistician() {
        length = 0;
    }

   /**
   * Add the numbers of another Statistician to this Statistician.
   * @param addend
   *   a Statistician whose numbers will be added to this Statistician
   * <b>Precondition:</b>
   *   The parameter, <CODE>addend</CODE>, is not null. 
   * <b>Postcondition:</b>
   *   The numbers from <CODE>addend</CODE> have been added to this
   *   Statistician. After the operation, this Statistician acts as if
   *   if was given all of its numbers and also given all of the numbers
   *   from the addend.
   * @exception NullPointerException
   *   Indicates that <CODE>addend</CODE> is null. 
   **/

    public void addAll(Statistician addend) {
        if (addend == null) {
            throw new NullPointerException("addAll: addend is null");
        }
        if (length == 0) {
            length = addend.length;
            sum = addend.sum;
            min = addend.min;
            max = addend.max;
        } else {
            length = length + addend.length;
            sum = sum + addend.sum;
            if (addend.min < min) {
                min = addend.min;
            }
            if (addend.max > max) {
                max = addend.max;
            }
        }
    }

   /**
   * Clear this Statistician. 
   * <b>Postcondition:</b>
   *   This Statistician is reinitialized as if it has never been given any 
   *   numbers.
   **/

    public void clear() {
        length = 0;
        sum = 0.0;
    }

   /**
   * Compare this <CODE>Statistician</CODE> to another object for equality.
   * @param obj
   *   an object with which this <CODE>Statistician</CODE> will be compared
   * @return
   *   A return value of <CODE>true</CODE> indicates that 
   *   <CODE>obj</CODE> refers to a 
   *   <CODE>Statistican</CODE> object with the same length, sum, mean,
   *   minimum and maximum as this 
   *   <CODE>Statistician</CODE>. Otherwise the return value is 
   *   <CODE>false</CODE>.
   * <b>Note:</b>
   *   If <CODE>obj</CODE> is null or does not refer to a 
   *   <CODE>Statistician</CODE> object, then the answer is 
   *   <CODE>false</CODE>.
   **/   

    public boolean equals(Object obj) {
        if (obj instanceof Statistician) {
            Statistician candidate = (Statistician) obj;
            return (candidate.length == length) && (candidate.sum == sum) && (candidate.min == min) && (candidate.max == max);
        } else {
            return false;
        }
    }

   /**
   * Determine how many numbers have been given to this Statistician.
   * @return
   *   the count of how many numbers have been given to this Statistician
   *   since it was initialized or reinitialized.
   * <b>Note:</b>
   *   Giving a Statistician more than
   *   <CODE>Integer.MAX_VALUE</CODE> numbers, will
   *   cause failure with an arithmetic overflow.
   **/ 

    public int length() {
        if (length >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return length;
        }
    }

   /**
   * Determine the largest number that has been given 
   * to this Statistician.
   * @return
   *   the largest number that has been given to this 
   *   Statistician
   *   since it was initialized or reinitialized.
   * <b>Note:</b>
   *   If <CODE>length()</CODE> is zero, then the answer from this method
   *   is <CODE>Double.NaN</CODE>.
   **/ 

    public double maximum() {
        if (length == 0) {
            return Double.NaN;
        } else {
            return max;
        }
    }

   /**
   * Determine the arithmetic average of all the numbers that have been given 
   * to this Statistician.
   * @return
   *   the arithmetic mean of all the number that have been given to this 
   *   Statistician
   *   since it was initialized or reinitialized.
   * <b>Note:</b>
   *   If this Statistician has been given more than
   *   <CODE>Integer.MAX_VALUE</CODE> numbers, then this method fails
   *   because of arithmetic overflow.
   *   If <CODE>length()</CODE> is zero, then the answer from this method
   *   is <CODE>Double.NaN</CODE>.
   *   If <CODE>sum()</CODE> exceeds the bounds of double numbers, then the 
   *   answer from this method may be 
   *   <CODE>Double.POSITIVE_INFINITY</CODE> or
   *   <CODE>Double.NEGATIVE_INFINITY</CODE>.
   **/ 

    public double mean() {
        if (length == 0) {
            return Double.NaN;
        }
        if (sum >= Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        if (sum <= Double.NEGATIVE_INFINITY) {
            return Double.NEGATIVE_INFINITY;
        } else {
            double mean = sum / length;
            return mean;
        }
    }

   /**
   * Determine the smallest number that has been given 
   * to this Statistician.
   * @return
   *   the smallest number that has been given to this 
   *   Statistician
   *   since it was initialized or reinitialized.
   * <b>Note:</b>
   *   If <CODE>length()</CODE> is zero, then the answer from this method
   *   is <CODE>Double.NaN</CODE>.
   **/ 

    public double minimum() {
        if (length == 0) {
            return Double.NaN;
        } else {
            return min;
        }
    }

   /**
   * Give a new number to this Statistician. 
   * @param number
   *   the new number that is being given the this Statistician
   * <b>Postcondition:</b>
   *   The specified number has been given to this Statistician and
   *   it will be included in any subsequent statistics.
   **/

    public void next(double number) {
        if (length == 0) {
            sum = number;
            min = number;
            max = number;
        } else {
            sum = sum + number;
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
        }
        length = length + 1;
    }

   /**
   * Determine the sum of all the numbers that have been given to this 
   * Statistician.
   * @return
   *   the sum of all the number that have been given to this 
   *   Statistician
   *   since it was initialized or reinitialized.
   * <b>Note:</b>
   *   If the sum exceeds the bounds of double numbers, then the answer
   *   from this method may be 
   *   <CODE>Double.POSITIVE_INFINITY</CODE> or
   *   <CODE>Double.NEGATIVE_INFINITY</CODE>.
   **/ 

    public double sum() {
        if (sum == 0) {
            return 0;
        }
        if (sum >= Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        if (sum <= Double.NEGATIVE_INFINITY) {
            return Double.NEGATIVE_INFINITY;
        }
        return sum;
    }

   /**
   * Create a new Statistician that behaves as if it was given all the
   * numbers from two other bags.
   * @param s1
   *   the first of two Statisticians
   * @param s2
   *   the second of two Statisticians
   * <b>Precondition:</b>
   *   Neither s1 nor s2 is null.
   * @return
   *   a new Statistician that acts as if it was given all the numbers from
   *   s1 and s2.
   * @exception NullPointerException
   *   Indicates that one of the arguments is null.
   **/  
   
    public static Statistician union(Statistician s1, Statistician s2) {
        if ((s1 == null) || (s2 == null)) {
            throw new NullPointerException("Union: one of the arguments is null - NullPointerException");
        } else {
            Statistician s3 = new Statistician();
            s3.addAll(s1);
            s3.addAll(s2);
            return s3;
        }
    }
}   
