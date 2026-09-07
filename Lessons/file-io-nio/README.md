# File I/O / NIO

#### Learning Objectives

- Explain the purpose of file I/O and why Java applications need to read from and write to files.
- Read and write files using Java I/O, including basic text file operations.
- Use Java NIO (Path and Files APIs) to perform modern file and directory operations.
- Manage files safely, using proper exception handling and try-with-resources.
- Identify when to use I/O vs NIO based on performance and scalability needs.

#### Introduction (25 minutes)

Most interesting software deals with data that outlives a single program execution. Your application might need to load configuration files at startup, write audit logs throughout the day, process CSV imports from external systems, or store user-generated content. The moment your program needs to interact with the filesystem, you're working with file I/O.

Java's approach to file operations has evolved considerably. The original `java.io` package gave us streams and readers—powerful abstractions that treated files as sequences of bytes or characters. Then Java 7 introduced NIO.2, which brought `Path` and `Files` classes that feel more modern and handle many common tasks more cleanly. Understanding both matters because you'll encounter legacy code using the old approach, and you'll want the new approach for anything you write today.

What makes file I/O particularly interesting from an engineering perspective is that it sits at the boundary between your application and the operating system. File operations involve system calls, filesystem permissions, and all the messy reality of actual computers. This lesson covers both how to write file operations that work and how to write them defensively.

#### Why Applications Need File I/O (15 minutes)

File I/O serves several distinct purposes in real applications. Configuration management is probably the most universal—your application needs to know database connection strings, feature flags, API keys, and behavioral settings that vary between environments. We externalize these values to property files that the application reads at startup.

Data persistence represents another major category. Sometimes you're processing batch files from partners who send CSV or XML exports. Sometimes you're implementing a simple cache. The filesystem provides a straightforward persistence layer with good performance for many use cases.

Logging and auditing demand file I/O for different reasons. When something goes wrong in production, you need a record of what happened. These operations typically involve append-only writes to files that grow over time, with rotation policies to prevent unbounded growth.

#### The Traditional java.io Package (35 minutes)

Java's original I/O system centers on streams—sequential flows of data that you read from or write to. The core abstraction splits into byte streams (`InputStream` and `OutputStream`) for binary data and character streams (`Reader` and `Writer`) for text.

For reading text files, you typically combine a `FileReader` with a `BufferedReader`:

```java
import java.io.*;

public class LegacyFileReaderExample {
    public static void main(String[] args) {
        // First, create sample data
        createSampleFile();
        
        // Then read it
        readConfigFile("src/config.txt");
    }
    
    private static void createSampleFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/config.txt"));
            writer.write("database.url=jdbc:mysql://localhost:3306/mydb");
            writer.newLine();
            writer.write("database.user=admin");
            writer.newLine();
            writer.write("database.password=secret");
            writer.newLine();
            writer.write("max.connections=10");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void readConfigFile(String filename) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Config: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

This code works, but notice the manual resource management. If you forget the `finally` block or mishandle the null check, you leak file handles. Operating systems limit the number of open files a process can have, and leaking handles eventually causes your application to fail.

Writing files follows a similar pattern. The `FileWriter` constructor's second parameter controls whether you append to an existing file or overwrite it:

```java
import java.io.*;
import java.util.*;

public class LegacyFileWriterExample {
    public static void main(String[] args) {
        List<String> logEntries = Arrays.asList(
            "2025-01-15 10:30:00 INFO Application started",
            "2025-01-15 10:30:05 INFO User logged in: john",
            "2025-01-15 10:31:00 ERROR Database connection failed",
            "2025-01-15 10:31:10 INFO Retrying connection"
        );
        
        writeLogFile("src/application.log", logEntries);
        System.out.println("Log file written successfully");
    }
    
    public static void writeLogFile(String filename, List<String> logEntries) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true)); // append mode
            for (String entry : logEntries) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

#### Try-With-Resources and Modern Resource Management (20 minutes)

Java 7's try-with-resources syntax eliminates the boilerplate around closing resources. Any class implementing `AutoCloseable` can be used in this construct:

```java
import java.io.*;
import java.util.*;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        // Create sample file
        createSampleData();
        
        // Read it back
        try {
            List<String> lines = readAllLines("src/data.txt");
            System.out.println("Read " + lines.size() + " lines:");
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void createSampleData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data.txt"))) {
            writer.write("Alice,Engineering,85000");
            writer.newLine();
            writer.write("Bob,Marketing,72000");
            writer.newLine();
            writer.write("Carol,Sales,68000");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<String> readAllLines(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
```

The `reader` automatically closes when the try block exits, whether normally or via exception. This pattern should be automatic whenever you work with files—it's the correct way to handle resources in Java.

#### Java NIO.2: Path and Files (30 minutes)

The NIO.2 API provides a cleaner approach to file operations. Instead of dealing with `File` objects, you work with `Path` objects and the `Files` utility class:

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class NIOBasicExample {
    public static void main(String[] args) {
        try {
            // Create sample properties file
            Path configPath = Paths.get("src/app.properties");
            List<String> properties = Arrays.asList(
                "# Application Configuration",
                "app.name=MyApplication",
                "app.version=1.0.0",
                "app.port=8080",
                "app.debug=true"
            );
            Files.write(configPath, properties);
            
            // Read it back
            Map<String, String> config = loadProperties(configPath);
            System.out.println("Loaded configuration:");
            config.forEach((key, value) -> 
                System.out.println(key + " = " + value));
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Map<String, String> loadProperties(Path configFile) throws IOException {
        Map<String, String> properties = new HashMap<>();
        List<String> lines = Files.readAllLines(configFile);
        
        for (String line : lines) {
            if (line.trim().isEmpty() || line.startsWith("#")) {
                continue;
            }
            String[] parts = line.split("=", 2);
            if (parts.length == 2) {
                properties.put(parts[0].trim(), parts[1].trim());
            }
        }
        return properties;
    }
}
```

For large files where loading everything into memory would be problematic, use `Files.lines()` which returns a `Stream<String>`:

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class StreamingFileExample {
    public static void main(String[] args) {
        try {
            // Create a large log file
            Path logFile = Paths.get("src/large.log");
            createLargeLogFile(logFile);
            
            // Count errors without loading entire file into memory
            long errorCount = countErrorsInLog(logFile);
            System.out.println("Found " + errorCount + " errors in log");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void createLargeLogFile(Path logFile) throws IOException {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            if (i % 10 == 0) {
                content.append("ERROR: Failed operation at line ").append(i).append("\n");
            } else {
                content.append("INFO: Normal operation at line ").append(i).append("\n");
            }
        }
        Files.writeString(logFile, content.toString());
    }
    
    public static long countErrorsInLog(Path logFile) throws IOException {
        try (Stream<String> lines = Files.lines(logFile)) {
            return lines.filter(line -> line.contains("ERROR")).count();
        }
    }
}
```

The stream is lazy—lines are read only as needed. The try-with-resources ensures the underlying file handle closes properly.

#### Directory Operations and File Metadata (25 minutes)

NIO.2 excels at directory manipulation and querying file metadata:

```java
import java.nio.file.*;
import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.stream.*;

public class DirectoryOperationsExample {
    public static void main(String[] args) {
        try {
            // Setup directory structure
            Path baseDir = Paths.get("src/myapp");
            setupApplicationDirectories(baseDir);
            
            // Create some log files
            createSampleLogs(baseDir.resolve("logs"));
            
            // Find recent logs
            List<Path> recentLogs = findRecentLogs(baseDir.resolve("logs"), 30);
            System.out.println("Recent log files:");
            recentLogs.forEach(System.out::println);
            
            // Calculate directory size
            long totalSize = calculateDirectorySize(baseDir);
            System.out.println("\nTotal directory size: " + totalSize + " bytes");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void setupApplicationDirectories(Path baseDir) throws IOException {
        Files.createDirectories(baseDir.resolve("data"));
        Files.createDirectories(baseDir.resolve("logs"));
        Files.createDirectories(baseDir.resolve("temp"));
        System.out.println("Created directory structure at " + baseDir);
    }
    
    private static void createSampleLogs(Path logsDir) throws IOException {
        Files.writeString(logsDir.resolve("app.log"), "Application log content\n");
        Files.writeString(logsDir.resolve("error.log"), "Error log content\n");
        Files.writeString(logsDir.resolve("access.log"), "Access log content\n");
    }
    
    public static List<Path> findRecentLogs(Path logsDir, int daysOld) throws IOException {
        Instant cutoff = Instant.now().minus(daysOld, ChronoUnit.DAYS);
        
        try (Stream<Path> paths = Files.list(logsDir)) {
            return paths
                .filter(path -> path.toString().endsWith(".log"))
                .filter(path -> {
                    try {
                        return Files.getLastModifiedTime(path).toInstant().isAfter(cutoff);
                    } catch (IOException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
        }
    }
    
    public static long calculateDirectorySize(Path directory) throws IOException {
        try (Stream<Path> paths = Files.walk(directory)) {
            return paths
                .filter(Files::isRegularFile)
                .mapToLong(path -> {
                    try {
                        return Files.size(path);
                    } catch (IOException e) {
                        return 0L;
                    }
                })
                .sum();
        }
    }
}
```

Checking file attributes is straightforward:

```java
import java.nio.file.*;
import java.io.IOException;

public class FileAttributesExample {
    public static void main(String[] args) {
        try {
            // Create test file
            Path testFile = Paths.get("src/test.txt");
            Files.writeString(testFile, "Sample content for testing");
            
            // Validate it
            boolean valid = isValidConfigFile(testFile);
            System.out.println("File is valid: " + valid);
            
            // Display attributes
            System.out.println("Size: " + Files.size(testFile) + " bytes");
            System.out.println("Readable: " + Files.isReadable(testFile));
            System.out.println("Writable: " + Files.isWritable(testFile));
            System.out.println("Last modified: " + Files.getLastModifiedTime(testFile));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isValidConfigFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            return false;
        }
        if (!Files.isRegularFile(path)) {
            return false;
        }
        if (!Files.isReadable(path)) {
            return false;
        }
        long size = Files.size(path);
        return size > 0 && size < 1_000_000;
    }
}
```

#### Exception Handling Strategies (25 minutes)

File I/O generates `IOException` frequently. The filesystem is unreliable—files disappear, disks fill up, permissions change. Your code must handle these realities:

```java
import java.nio.file.*;
import java.io.IOException;

public class ExceptionHandlingExample {
    public static void main(String[] args) {
        try {
            // Create test data
            Path targetFile = Paths.get("src/important-data.txt");
            byte[] data = "Critical application data".getBytes();
            
            // Save atomically
            saveDataAtomically(targetFile, data);
            System.out.println("Data saved successfully");
            
            // Verify
            String content = Files.readString(targetFile);
            System.out.println("Verified content: " + content);
            
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void saveDataAtomically(Path targetFile, byte[] data) throws IOException {
        Path tempFile = Files.createTempFile(targetFile.getParent(), ".tmp", ".dat");
        try {
            Files.write(tempFile, data);
            Files.move(tempFile, targetFile, 
                StandardCopyOption.ATOMIC_MOVE, 
                StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Files.deleteIfExists(tempFile);
            throw e;
        }
    }
}
```

This writes to a temporary file first, then atomically renames it to the target. If anything fails, the original file remains untouched.

#### Character Encoding and Charset Handling (20 minutes)

Character encoding is where many file I/O bugs originate. Always specify the encoding explicitly:

```java
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.*;

public class EncodingExample {
    public static void main(String[] args) {
        try {
            Path dataFile = Paths.get("src/utf8-data.txt");
            
            // Write with explicit UTF-8 encoding
            List<String> records = Arrays.asList(
                "Name: José García",
                "City: São Paulo",
                "Note: €100 payment received",
                "Special: 你好世界"
            );
            exportData(dataFile, records);
            
            // Read back with UTF-8
            List<String> readRecords = Files.readAllLines(dataFile, StandardCharsets.UTF_8);
            System.out.println("Read records:");
            readRecords.forEach(System.out::println);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void exportData(Path file, List<String> records) throws IOException {
        Files.write(file, records, StandardCharsets.UTF_8);
    }
}
```

UTF-8 should be your default choice for text files—it handles all Unicode characters and is universally supported.

#### Comparing I/O vs NIO Approaches (20 minutes)

Here's a practical comparison showing both approaches for the same task:

```java
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.stream.Stream;

public class IOvsNIOComparison {
    public static void main(String[] args) {
        try {
            // Create sample log file
            Path logFile = Paths.get("src/server.log");
            createSampleLog(logFile);
            
            // Process with traditional I/O
            System.out.println("Processing with traditional I/O:");
            processLogWithIO(logFile);
            
            System.out.println("\nProcessing with NIO:");
            processLogWithNIO(logFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void createSampleLog(Path logFile) throws IOException {
        StringBuilder log = new StringBuilder();
        log.append("2025-01-15 10:00:00 INFO Server started\n");
        log.append("2025-01-15 10:00:05 DEBUG Connection pool initialized\n");
        log.append("2025-01-15 10:01:00 ERROR Failed to connect to database\n");
        log.append("2025-01-15 10:01:05 INFO Retrying connection\n");
        log.append("2025-01-15 10:01:10 ERROR Connection timeout\n");
        log.append("2025-01-15 10:02:00 INFO Connection established\n");
        Files.writeString(logFile, log.toString());
    }
    
    // Traditional I/O approach
    public static void processLogWithIO(Path logFile) throws IOException {
        int errorCount = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    Files.newInputStream(logFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ERROR")) {
                    errorCount++;
                    System.out.println("Error found: " + line);
                }
            }
        }
        System.out.println("Total errors (I/O): " + errorCount);
    }
    
    // NIO approach with streams
    public static void processLogWithNIO(Path logFile) throws IOException {
        try (Stream<String> lines = Files.lines(logFile, StandardCharsets.UTF_8)) {
            lines.filter(line -> line.contains("ERROR"))
                 .forEach(line -> System.out.println("Error found: " + line));
            
            long count = Files.lines(logFile, StandardCharsets.UTF_8)
                              .filter(line -> line.contains("ERROR"))
                              .count();
            System.out.println("Total errors (NIO): " + count);
        }
    }
}
```

#### Summary (15 minutes)

File I/O sits at the intersection of your application logic and the operating system's filesystem. The evolution from `java.io` to NIO.2 reflects the platform's maturation. The old stream-based approach remains valid for sequential processing, but the `Path` and `Files` APIs provide a cleaner foundation for modern code.

Try-with-resources ensures proper cleanup regardless of which approach you take. Character encoding must always be specified explicitly—UTF-8 is the safe default. Performance comes primarily from buffering, which both APIs handle well. Defensive programming—validating paths, using atomic operations, handling exceptions properly—transforms file operations from fragile code into robust components that survive production.
