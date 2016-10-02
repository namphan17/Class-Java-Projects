/**********************************************************************
 *  readme template                                                   
 *  TSP Heuristics
 **********************************************************************/

Name: Nam Phan

Hours to complete assignment (optional):
2 days I guess. 2-3 hours per day.
/**********************************************************************
 *  Explain how you implemented the nearest insertion heuristic.
 **********************************************************************/
First, I create a new Node, named it "key, to store that Point, that node 
is the node that we will insert into the linked list.I initialize a new point 
to be inserted after the start point. The I get the distance from that point 
to the start point and store it with the "min" variable. After that, I create a 
temp Node, using for loop to make it  go through every node in the linked list. 
I will compare the distance from  the temp Node to the inserted point: if it is 
smaller than the "min" value, the "min" value will be equal to the new distance and
we will assign the "min" to be that new distance; then the key Node's next value 
will be the Node next to the temp Node, which is temp.next. Finally, I use a 
while loop to locate which Node the key Node is linked  to, then I link the 
previous Node to the key Node.

/**********************************************************************
 *  Explain how you implemented the smallest insertion heuristic.
 *  It's sufficient to list only the differences between this
 *  heuristic and the nearest insertion heuristic.
 **********************************************************************/
It's pretty much similar to how I implement the Nearest heuristic. The only 
difference is the "min" value will be the increase of the whole tour after 
we insert that key Node. To calculate this "min" value, we will first get the 
distance from key point to each of those 2 Nodes and take the sum. Then we 
calculate the distance between 2 consecutive Nodes. And then we subtract that 
distance from the sum. In the for loop, we just need to repeat these steps 
to get the increase, then the way we compare and set the key position is still
the same.


/**********************************************************************
 *  Fill in the distances computed by your heuristics.                
 **********************************************************************/

data file          nearest neighbor     smallest increase      extra credit
-----------------------------------------------------------------------------
tsp10.txt              1566.1363             1655.7462
tsp100.txt			   7389.9297			 4887.2190
tsp1000.txt			  27868.7106		 	 17265.6282
usa13509.txt 		  77449.9794 			 45074.7769




/**********************************************************************
 *  Estimate the running time (in seconds) of your two heuristics as
 *  a function of N, using an expression of the form: a * N^b seconds
 *  where b is an integer. DESCRIBE HOW YOU DETERMINED YOUR ANSWER. 
 *  Also list more than 3 data points. You may use TSPTimer.java as 
 *  described in the project description.
 **********************************************************************/
N:			1000	2k	     4k	     8k		       16k
nearest:	0.434s	1.957s	 8.079s	 33.069s       133.122s
smallest:	0.246s  0.975s   3.897s	 16.084	       62.704s	
The ratio over time converges to 4. So we have the power of N is 2.
The function of N for running time has the form f(N) = aN^2.
nearest: a ~ 6.16*10^-7
smallest: a ~ 3.07*10^-7
The order of growth for both heuristics is quadratic.

/**********************************************************************
 *  Explain why it's better to use a circular linked list instead of an 
 *  array.  
 **********************************************************************/
Because we will keep adding new Node without knowing about the final number 
of those Nodes. Therefore it is difficult to initialize the suitable size 
for our array. And for this problem, we want the tour to come back to the 
start point after visit all other points. Therefore the circular linked 
list is the best data structure to use for this problem.


/**********************************************************************
 *  If you did the extra credit, explain your heuristic, and how
 *  you went about implementing it.
 **********************************************************************/



/**********************************************************************
 *  If you did the extra credit, give instructions here for 
 *  running it.
 **********************************************************************/



/**********************************************************************
 *  List whatever help (if any) that you received.  Include any 
 *  external resources and the names of any students that helped you.                          
 **********************************************************************/
You helped me the most Ross!!!


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

To come up with the idea how to write the code for this insertion is not
that hard. However, I need to be very careful in writing my code because 
working with this liked list is prone to NullPoiterException. Sometimes,
it takes lots of time to detect the errors and debug it.


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
It is a very fun project. I really enjoyed working on it.
