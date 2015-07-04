package org.pharmgkb.parsers;

import javax.annotation.Nonnull;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Counterpart to {@link LineParser}.
 * @author Douglas Myers-Turnbull
 */
public interface LineWriter<T> extends Function<T, String> {
	default void writeToFile(@Nonnull Stream<T> stream, @Nonnull Path file) throws IOException {
		writeToFile(stream, file.toFile());
	}
	default void writeToFile(@Nonnull Iterable<T> lines, @Nonnull Path file) throws IOException {
		writeToFile(lines, file.toFile());
	}
	default void writeToFile(@Nonnull Iterable<T> lines, @Nonnull File file) throws IOException {
		writeToFile(StreamSupport.stream(lines.spliterator(), false), file);
	}
	default void writeToFile(@Nonnull Stream<T> stream, @Nonnull File file) throws IOException {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)), true)) {
			stream.forEach(pw::println);
		}
	}
}
