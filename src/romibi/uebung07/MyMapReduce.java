package romibi.uebung07;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MyMapReduce {

	public static void main(String[] args) {
		MyMapReduce mr = new MyMapReduce();
		String input = "src/romibi/uebung07/data/Input";//"C:/Temp/Input";
		// Work kˆnnte auch tempor‰res Verzeichnis sein...
		String temp = "src/romibi/uebung07/data/Temp";
		String output = "src/romibi/uebung07/data/Output";

		/*
		 * Eingabe: diverse Text Dateien *.txt Ausgabe: diverse Text Dateien
		 * *.txt im Work Verzeichnis
		 */
		mr.map(input, temp);
		/*
		 * Eingabe: diverse Text Dateien *.txt im Work Verzeichnis 
		 * Ausgabe: diverse sortierte Text Dateien *.sorted im Work Verzeichnis
		 */
		mr.shuffle(temp);
		/*
		 * Eingabe: diverse Text Dateien *.txt.sorted im Work Verzeichnis 
		 * Ausgabe: diverse sortierte und reduzierte Text Dateien *.sorted.reduced 
		 *          im Work Verzeichnis
		 */
		mr.reduceBlock(temp);
		/*
		 * Eingabe: diverse Text Dateien im Work Verzeichnis 
		 * Ausgabe: eine sortierte, reduzierte Text Dateien im output Verzeichnis
		 */

		mr.reduce(output);

	}

	public void map(String inDir, String outDir) {
		System.out.println();
		System.out.println(">>> map(" + inDir + " , " + outDir + ")");
		boolean debug = true;
		Path path = Paths.get(inDir);
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
			for (Path file : ds) {
				System.out.println(file.getFileName());
				Charset charset1 = Charset.forName("ISO-8859-1");

				Path file_path;
				if (debug) {
					/*
					 * Dateien / Buffer stehen lassen
					 */
					file_path = Paths
							.get(outDir, file.getFileName().toString());
				} else {
					/*
					 * Buffer existieren nur tempor‰r
					 */
					String temp_prefix = "Buffer_";
					String temp_sufix = ".txt";
					file_path = Files.createTempFile(temp_prefix, temp_sufix);
				}
				Charset charset2 = Charset.forName("UTF-8");
				BufferedWriter writer = Files.newBufferedWriter(file_path,
						charset2, StandardOpenOption.CREATE);
				List<String> lines = Files.readAllLines(file, charset1);
				for (String line : lines) {
					System.out.println(line);
					StringTokenizer tokenizer = new StringTokenizer(line,
							":.; ");
					while (tokenizer.hasMoreTokens()) {
						String token = tokenizer.nextToken();
						writer.write(token + ",1");
						writer.newLine();
						System.out.println(token);
					}
				}
				writer.flush();
				writer.close();

			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void shuffle(String workDir) {
		System.out.println();
		System.out.println(">>> shuffle(" + workDir + ")");
		boolean debug = true;
		Path path = Paths.get(workDir);
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.txt")) {
			for (Path file : ds) {
				System.out.println(file.getFileName());
				Charset charset1 = Charset.forName("UTF-8");// "ISO-8859-1");

				Path file_path;
				if (debug) {
					/*
					 * Dateien / Buffer stehen lassen
					 */
					file_path = Paths.get(workDir, file.getFileName()
							.toString() + ".sorted");
				} else {
					/*
					 * Buffer existieren nur tempor‰r
					 */
					String temp_prefix = "Buffer_";
					String temp_sufix = ".txt";
					file_path = Files.createTempFile(temp_prefix, temp_sufix);
				}
				Charset charset2 = Charset.forName("UTF-8");
				BufferedWriter writer = Files.newBufferedWriter(file_path,
						charset2, StandardOpenOption.CREATE);
				List<String> lines = Files.readAllLines(file, charset1);
				Object[] oArray = lines.toArray();
				Arrays.sort(oArray);
				for (Object token : oArray) {
					writer.write(token.toString());
					writer.newLine();
					System.out.println(token);
				}
				writer.flush();
				writer.close();

			}
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	public void reduceBlock(String inDir) {
		System.out.println();
		System.out.println(">>> reduceBlock(" + inDir + ")");

		Path path = Paths.get(inDir);
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path,
				"*.sorted")) {

			List<Path> pathList = new ArrayList<Path>();
			for (Path file : ds) {
				Path file_path = Paths
						.get(inDir, file.getFileName().toString());
				System.out.println(file.getFileName());
				pathList.add(file_path);
			}

			Charset charset = Charset.forName("UTF-8");
			for (int i = 0; i < pathList.size(); i++) {
				List<String> lines = Files.readAllLines(pathList.get(i),
						charset);
				// System.out.println(lines.toString());
				Path file_path = Paths.get(inDir, pathList.get(i)
						.getFileName().toString()
						+ ".reduced");
				BufferedWriter writer = Files.newBufferedWriter(file_path,
						charset, StandardOpenOption.CREATE);

				String[] items = new String[lines.size()];
				for (int j = 0; j < lines.size(); j++) {
					items[j] = lines.get(j);
				}

				for (int k = 1; k <=lines.size(); k++) {
					int eq = 1;
					while (  (k < lines.size()) && (items[k - 1].equals(items[k]) ))  {
						eq++;
						k++;
					}
					// k-1: for geht bis k
					String[] parts = items[k - 1].split(",");
					String newItem = parts[0] + "," + eq;
//					System.out.println("[NewItem]" + newItem);

					writer.write(newItem);
					writer.newLine();
					// System.out.println("[TOKEN]" + token);
				}
				writer.flush();
				writer.close();
			}

		} catch (IOException e) {
			System.err.println(e);
		}

	}


	public void reduce(String outDir) {
		// TODO
	}

}
