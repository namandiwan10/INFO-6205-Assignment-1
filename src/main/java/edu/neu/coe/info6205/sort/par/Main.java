package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;


public class Main {

	public static void main(String[] args) {
		processArgs(args);
		System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
		Random random = new Random();
		int[] array;
		HashMap<String, ArrayList<Long>> timeMap = new HashMap<>();
		ArrayList<Long> timeList = new ArrayList<>();

		for (int arraySize = 150000; arraySize <= 600000; arraySize += 150000) {
			System.out.println("Array size ：" + arraySize);
			array = new int[arraySize];
			for (int threadCount = 2; threadCount < 65; threadCount = threadCount * 2) {
				ForkJoinPool myPool = new ForkJoinPool(threadCount);
				System.out.println("Thread count is: " + threadCount);
				for (int j = 50; j < 100; j += 5) {
					ParSort.cutoff = 100 * (j + 1);
					for (int i = 0; i < array.length; i++)
						array[i] = random.nextInt(10000000);
					long time;
					long startTime = System.currentTimeMillis();
					for (int t = 0; t < 10; t++) {
						for (int i = 0; i < array.length; i++)
							array[i] = random.nextInt(10000000);
						ParSort.sort(array, 0, array.length);
					}
					long endTime = System.currentTimeMillis();
					time = (endTime - startTime);
					String key = arraySize + "-" + threadCount + "-" + ParSort.cutoff;
					if (!timeMap.containsKey(key)) {
					    timeMap.put(key, new ArrayList<Long>());
					}
					timeMap.get(key).add(time);
					timeList.add(time);
					System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time + "ms");
				}
			}
		}
		for (int arraySize = 100000; arraySize <= 300000; arraySize += 100000) {
		    for (int j = 50; j < 100; j += 5) {
		        int cutoff = 100 * (j + 1);
		        for (int threadCount = 2; threadCount < 65; threadCount = threadCount * 2) {
		            String key = arraySize + "-" + threadCount + "-" + cutoff;
		            if (timeMap.containsKey(key)) {
		                double averageTime = timeMap.get(key).stream().mapToLong(Long::longValue).average().orElse(0);
		            }
		        }
		    }
		}

		try {
			FileOutputStream fis = new FileOutputStream("./src/result.csv");
			OutputStreamWriter isr = new OutputStreamWriter(fis);
			BufferedWriter bw = new BufferedWriter(isr);
			int j = 0;
			for (long i : timeList) {
				String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
				j++;
				bw.write(content);
				bw.flush();
			}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void processArgs(String[] args) {
		String[] xs = args;
		while (xs.length > 0)
			if (xs[0].startsWith("-"))
				xs = processArg(xs);
	}

	private static String[] processArg(String[] xs) {
		String[] result = new String[0];
		System.arraycopy(xs, 2, result, 0, xs.length - 2);
		processCommand(xs[0], xs[1]);
		return result;
	}

	private static void processCommand(String x, String y) {
		if (x.equalsIgnoreCase("N"))
			setConfig(x, Integer.parseInt(y));
		else
		// TODO sort this out
		if (x.equalsIgnoreCase("P")) // noinspection ResultOfMethodCallIgnored
			ForkJoinPool.getCommonPoolParallelism();
	}

	private static void setConfig(String x, int i) {
		configuration.put(x, i);
	}

	@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
	private static final Map<String, Integer> configuration = new HashMap<>();

}