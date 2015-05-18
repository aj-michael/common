package net.ajmichael.common.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArraySorts_T {

  private final Integer[] arr1 = {1, 5, 3, 5, 2, 6, 9, 6, 1};
  private final Integer[] expected1 = {1, 1, 2, 3, 5, 5, 6, 6, 9};

  private final String[] arr2 = {};
  private final String[] expected2 = {};

  @Test
  public void testBubbleSort() {
    ArraySorts.bubbleSort(arr1);
    assertArrayEquals(expected1, arr1);

    ArraySorts.bubbleSort(arr2);
    assertArrayEquals(expected2, arr2);
  }

  @Test
  public void testHeapSort() {
    ArraySorts.heapSort(arr1);
    assertArrayEquals(expected1, arr1);

    ArraySorts.heapSort(arr2);
    assertArrayEquals(expected2, arr2);
  }

  @Test
  public void testInsertionSort() {
    ArraySorts.insertionSort(arr1);
    assertArrayEquals(expected1, arr1);

    ArraySorts.insertionSort(arr2);
    assertArrayEquals(expected2, arr2);
  }

  @Test
  public void testMergeSort() {
    ArraySorts.mergeSort(arr1);
    assertArrayEquals(expected1, arr1);

    ArraySorts.mergeSort(arr2);
    assertArrayEquals(expected2, arr2);
  }

  @Test
  public void testQuickSort() {
    ArraySorts.quickSort(arr1);
    assertArrayEquals(expected1, arr1);

    ArraySorts.quickSort(arr2);
    assertArrayEquals(expected2, arr2);
  }

  @Test
  public void testSelectionSort() {
    ArraySorts.selectionSort(arr1);
    assertArrayEquals(expected1, arr1);

    ArraySorts.selectionSort(arr2);
    assertArrayEquals(expected2, arr2);
  }

  @Test
  public void testSiftDown() {
    Integer[] heap = new Integer[] {-8, -3, -6, -5, -9, -4, -4, -4};
    Integer[] expected = new Integer[] {-3, -5, -6, -8, -9, -4, -4, -4};
    ArraySorts.siftDown(heap, 0, 4);
    assertArrayEquals(expected, heap);
  }

  @Test
  public void testHeapify() {
    Integer[] arr = new Integer[] {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
    Integer[] expected = new Integer[] {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
    ArraySorts.heapify(arr);
    assertArrayEquals(expected, arr);
  }
}
