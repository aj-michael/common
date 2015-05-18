package net.ajmichael.common.util;

import java.lang.reflect.Array;

/**
 * Common knowledge in-place sorting algorithms.
 * 
 * @author Adam Michael
 */
public final class ArraySorts {

  private ArraySorts() {}

  /**
   * Runs through the array n times, swapping consecutive elements if they are out of order. If no
   * elements are swapped on a run, the function returns early.
   */
  public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
    for (int i = 0; i < arr.length; i++) {
      boolean atLeastOneSwap = false;
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j].compareTo(arr[j + 1]) > 0) {
          swap(arr, j, j + 1);
          atLeastOneSwap = true;
        }
      }
      if (!atLeastOneSwap) {
        return;
      }
    }
  }

  public static <T extends Comparable<T>> void heapSort(T[] arr) {
    heapify(arr);
    for (int i = arr.length - 1; i > 0; i--) {
      swap(arr, 0, i);
      siftDown(arr, 0, i - 1);
    }
  }

  static <T extends Comparable<T>> void heapify(T[] arr) {
    for (int start = (arr.length - 2) / 2; start >= 0; start--) {
      siftDown(arr, start, arr.length - 1);
    }
  }

  static <T extends Comparable<T>> void siftDown(T[] arr, int start, int end) {
    int left = 2 * start + 1;
    int right = 2 * start + 2;
    if (left == end) {
      if (arr[left].compareTo(arr[start]) > 0) {
        swap(arr, start, left);
        siftDown(arr, left, end);
      }
    } else if (right <= end) {
      if (arr[left].compareTo(arr[start]) > 0 && arr[left].compareTo(arr[right]) >= 0) {
        swap(arr, start, left);
        siftDown(arr, left, end);
      } else if (arr[right].compareTo(arr[start]) > 0 && arr[right].compareTo(arr[left]) >= 0) {
        swap(arr, start, right);
        siftDown(arr, right, end);
      }
    }
  }

  /**
   * Variant of the traditional list insertion sort. Runs through the array, pulling each element
   * back as far towards the start as it can while maintaining order.
   */
  public static <T extends Comparable<T>> void insertionSort(T[] arr) {
    for (int i = 1; i < arr.length; i++) {
      for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
        swap(arr, j, j - 1);
      }
    }
  }

  public static <T extends Comparable<T>> void mergeSort(T[] arr) {
    mergeSort(arr, 0, arr.length - 1);
  }

  private static <T extends Comparable<T>> void merge(T[] arr, int s1, int e1, int s2, int e2) {
    Object[] result = (Object[]) Array.newInstance(arr.getClass().getComponentType(), e2 - s1 + 1);
    int i = s1;
    int j = s2;
    int k = 0;
    while (i <= e1 && j <= e2) {
      if (arr[i].compareTo(arr[j]) < 0) {
        result[k++] = arr[i++];
      } else {
        result[k++] = arr[j++];
      }
    }
    while (i <= e1) {
      result[k++] = arr[i++];
    }
    while (j <= e2) {
      result[k++] = arr[j++];
    }
    System.arraycopy(result, 0, arr, s1, e2 - s1 + 1);
  }

  private static <T extends Comparable<T>> void mergeSort(T[] arr, int start, int end) {
    if (end - start > 0) {
      int mid = (start + end) / 2;
      mergeSort(arr, start, mid);
      mergeSort(arr, mid + 1, end);
      merge(arr, start, mid, mid + 1, end);
    }
  }

  /**
   * Dutch National Flag partition algorithm. Rearranges {@code arr} into three partitions: values
   * less than {@code mid}, values equal to {@code mid} and values greater than {@mid}.
   * 
   * @return Pair of ints (a, b) where a is the index of the start of the mid partition and b is the
   *         index of the start of the last partition.
   */
  private static <T extends Comparable<T>> int[] partition(T[] arr, T mid, int start, int end) {
    int i = start;
    int j = start;
    int k = end;
    while (j < k + 1) {
      if (arr[j].compareTo(mid) < 0) {
        swap(arr, i++, j);
      } else if (arr[j].compareTo(mid) > 0) {
        swap(arr, j, k--);
      } else {
        j++;
      }
    }
    return new int[] {i, j};
  }

  /**
   * Trivially selects a pivot, partitions the array around that pivot and recursively sorts the
   * sub-arrays.
   */
  public static <T extends Comparable<T>> void quickSort(T[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private static <T extends Comparable<T>> void quickSort(T[] arr, int start, int end) {
    if (start < end) {
      T pivot = arr[start];
      int[] splits = partition(arr, pivot, start, end);
      quickSort(arr, start, splits[0] - 1);
      quickSort(arr, splits[1], end);
    }
  }

  /**
   * Builds the sorted array in place, starting with the empty list prefix of the array. One-by-one,
   * the smallest element of the unsorted array is found and moved to the beginning.
   */
  public static <T extends Comparable<T>> void selectionSort(T[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j].compareTo(arr[min]) < 0) {
          min = j;
        }
      }
      swap(arr, i, min);
    }
  }

  private static <T> void swap(T[] arr, int a, int b) {
    T temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }
}
