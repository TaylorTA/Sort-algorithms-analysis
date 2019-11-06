# Sort-algorithms-analysis
The purpose of this program is to time the execution of five sorting algorithms (insertion sort, selection sort, merge sort, quick sort, and a hybrid quick sort (details below)) and
reports on the run times and whether the algorithms actually sorted the list.

Report:
- Insertion sort was faster than selection sort. Although both insertion sort and selection sort
has same worst case O(n^2), selection sort always takes its worst case while insertion sort is faster
than worst case in general.
-  Quick sort was way faster than insertion sort. Although quick sort and insertion sort have
same worst case O(n^2), quick sort takes O(nlogn) time in average which is way faster than insertion
sort.
- With smart choose of break point, hybrid quick sort is faster than quick sort. The reason is that
it uses insertion sort for arrays with small size. Insertion sort runs faster on small arrays as it
avoid calculation on partition and pivot choose.
- I would recommend quick sort. Although hybrid quick sort can run faster than it, hybrid quick sort
takes long time to choose efficient break point. With a smart choose of pivot, quick sort has time
complexity O(nlogn) in average. Merge sort has time complexity O(nlogn) as well, but quick sort
always runs faster than it.
- I would warn others about selection sort. Although it seems to be the easiest sorting algorithm to
implement, it runs the slowest.
