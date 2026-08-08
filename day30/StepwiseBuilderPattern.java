// =====================================================================
//          STEPWISE BUILDER PATTERN (TYPE-SAFE BUILDER)
// =====================================================================
// Enforces build order at compile-time using interfaces
// Prevents incomplete object construction

// EXAMPLE 1: Pizza Builder with mandatory steps
class Pizza {
    private final String size;        // Required
    private final String crustType;   // Required  
    private final String cheese;      // Required
    private final String sauce;       // Optional
    private final java.util.List<String> toppings; // Optional
    
    private Pizza(Builder builder) {
        this.size = builder.size;
        this.crustType = builder.crustType;
        this.cheese = builder.cheese;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
    }
    
    // Step 1: Choose size
    public interface SizeStep {
        CrustStep size(String size);
    }
    
    // Step 2: Choose crust
    public interface CrustStep {
        CheeseStep crustType(String crustType);
    }
    
    // Step 3: Choose cheese
    public interface CheeseStep {
        OptionalStep cheese(String cheese);
    }
    
    // Step 4: Optional additions
    public interface OptionalStep {
        OptionalStep sauce(String sauce);
        OptionalStep addTopping(String topping);
        Pizza build();
    }
    
    public static class Builder implements SizeStep, CrustStep, CheeseStep, OptionalStep {
        private String size;
        private String crustType;
        private String cheese;
        private String sauce = "Tomato";
        private java.util.List<String> toppings = new java.util.ArrayList<>();
        
        private Builder() {}
        
        @Override
        public CrustStep size(String size) {
            this.size = size;
            return this;
        }
        
        @Override
        public CheeseStep crustType(String crustType) {
            this.crustType = crustType;
            return this;
        }
        
        @Override
        public OptionalStep cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }
        
        @Override
        public OptionalStep sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }
        
        @Override
        public OptionalStep addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }
        
        @Override
        public Pizza build() {
            return new Pizza(this);
        }
    }
    
    public static SizeStep newBuilder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "Pizza{size='" + size + "', crust='" + crustType + 
               "', cheese='" + cheese + "', sauce='" + sauce + 
               "', toppings=" + toppings + "}";
    }
}

// EXAMPLE 2: Email Builder with validation steps
class SecureEmail {
    private final String from;
    private final String to;
    private final String subject;
    private final String body;
    
    private SecureEmail(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.subject = builder.subject;
        this.body = builder.body;
    }
    
    // Step interfaces enforce order
    public interface FromStep {
        ToStep from(String from);
    }
    
    public interface ToStep {
        SubjectStep to(String to);
    }
    
    public interface SubjectStep {
        BodyStep subject(String subject);
    }
    
    public interface BodyStep {
        BuildStep body(String body);
    }
    
    public interface BuildStep {
        SecureEmail build();
        BuildStep validate();
    }
    
    public static class Builder implements FromStep, ToStep, SubjectStep, BodyStep, BuildStep {
        private String from;
        private String to;
        private String subject;
        private String body;
        
        private Builder() {}
        
        @Override
        public ToStep from(String from) {
            if (!isValidEmail(from)) {
                throw new IllegalArgumentException("Invalid from email: " + from);
            }
            this.from = from;
            return this;
        }
        
        @Override
        public SubjectStep to(String to) {
            if (!isValidEmail(to)) {
                throw new IllegalArgumentException("Invalid to email: " + to);
            }
            this.to = to;
            return this;
        }
        
        @Override
        public BodyStep subject(String subject) {
            if (subject == null || subject.trim().isEmpty()) {
                throw new IllegalArgumentException("Subject cannot be empty");
            }
            this.subject = subject;
            return this;
        }
        
        @Override
        public BuildStep body(String body) {
            this.body = body;
            return this;
        }
        
        @Override
        public BuildStep validate() {
            System.out.println("Validating email...");
            return this;
        }
        
        @Override
        public SecureEmail build() {
            return new SecureEmail(this);
        }
        
        private boolean isValidEmail(String email) {
            return email != null && email.contains("@");
        }
    }
    
    public static FromStep newBuilder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "Email{from='" + from + "', to='" + to + 
               "', subject='" + subject + "', body='" + body + "'}";
    }
}


// EXAMPLE 3: Database Connection Builder with mandatory configs
class DatabaseConnection {
    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;
    private final int timeout;
    private final boolean useSSL;
    
    private DatabaseConnection(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.database = builder.database;
        this.username = builder.username;
        this.password = builder.password;
        this.timeout = builder.timeout;
        this.useSSL = builder.useSSL;
    }
    
    public interface HostStep {
        PortStep host(String host);
    }
    
    public interface PortStep {
        DatabaseStep port(int port);
    }
    
    public interface DatabaseStep {
        UsernameStep database(String database);
    }
    
    public interface UsernameStep {
        PasswordStep username(String username);
    }
    
    public interface PasswordStep {
        OptionalConfigStep password(String password);
    }
    
    public interface OptionalConfigStep {
        OptionalConfigStep timeout(int seconds);
        OptionalConfigStep useSSL(boolean useSSL);
        DatabaseConnection connect();
    }
    
    public static class Builder implements HostStep, PortStep, DatabaseStep, 
                                          UsernameStep, PasswordStep, OptionalConfigStep {
        private String host;
        private int port;
        private String database;
        private String username;
        private String password;
        private int timeout = 30;
        private boolean useSSL = false;
        
        private Builder() {}
        
        @Override
        public PortStep host(String host) {
            this.host = host;
            return this;
        }
        
        @Override
        public DatabaseStep port(int port) {
            if (port <= 0 || port > 65535) {
                throw new IllegalArgumentException("Invalid port: " + port);
            }
            this.port = port;
            return this;
        }
        
        @Override
        public UsernameStep database(String database) {
            this.database = database;
            return this;
        }
        
        @Override
        public PasswordStep username(String username) {
            this.username = username;
            return this;
        }
        
        @Override
        public OptionalConfigStep password(String password) {
            this.password = password;
            return this;
        }
        
        @Override
        public OptionalConfigStep timeout(int seconds) {
            this.timeout = seconds;
            return this;
        }
        
        @Override
        public OptionalConfigStep useSSL(boolean useSSL) {
            this.useSSL = useSSL;
            return this;
        }
        
        @Override
        public DatabaseConnection connect() {
            System.out.println("Connecting to " + host + ":" + port + "/" + database);
            return new DatabaseConnection(this);
        }
    }
    
    public static HostStep builder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "DatabaseConnection{host='" + host + "', port=" + port + 
               ", database='" + database + "', username='" + username + 
               "', timeout=" + timeout + ", useSSL=" + useSSL + "}";
    }
}

// EXAMPLE 4: File Upload Builder with type safety
class FileUpload {
    private final String filename;
    private final byte[] content;
    private final String contentType;
    private final long maxSize;
    
    private FileUpload(Builder builder) {
        this.filename = builder.filename;
        this.content = builder.content;
        this.contentType = builder.contentType;
        this.maxSize = builder.maxSize;
    }
    
    public interface FilenameStep {
        ContentStep filename(String filename);
    }
    
    public interface ContentStep {
        ContentTypeStep content(byte[] content);
    }
    
    public interface ContentTypeStep {
        OptionalSettingsStep contentType(String contentType);
    }
    
    public interface OptionalSettingsStep {
        OptionalSettingsStep maxSize(long bytes);
        FileUpload upload();
    }
    
    public static class Builder implements FilenameStep, ContentStep, 
                                          ContentTypeStep, OptionalSettingsStep {
        private String filename;
        private byte[] content;
        private String contentType;
        private long maxSize = 5_000_000; // 5MB default
        
        private Builder() {}
        
        @Override
        public ContentStep filename(String filename) {
            if (filename == null || filename.isEmpty()) {
                throw new IllegalArgumentException("Filename cannot be empty");
            }
            this.filename = filename;
            return this;
        }
        
        @Override
        public ContentTypeStep content(byte[] content) {
            if (content == null || content.length == 0) {
                throw new IllegalArgumentException("Content cannot be empty");
            }
            this.content = content;
            return this;
        }
        
        @Override
        public OptionalSettingsStep contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }
        
        @Override
        public OptionalSettingsStep maxSize(long bytes) {
            this.maxSize = bytes;
            return this;
        }
        
        @Override
        public FileUpload upload() {
            if (content.length > maxSize) {
                throw new IllegalArgumentException("File exceeds max size: " + maxSize);
            }
            System.out.println("Uploading file: " + filename);
            return new FileUpload(this);
        }
    }
    
    public static FilenameStep builder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "FileUpload{filename='" + filename + "', contentType='" + 
               contentType + "', size=" + content.length + " bytes}";
    }
}

public class StepwiseBuilderPattern {
    
    public static void main(String[] args) {
        
        System.out.println("=== STEPWISE BUILDER PATTERN ===\n");
        System.out.println("Enforces build order at COMPILE TIME\n");
        
        System.out.println("=== EXAMPLE 1: Pizza Builder ===\n");
        
        // Must follow exact order: size -> crust -> cheese -> optional
        Pizza pizza1 = Pizza.newBuilder()
            .size("Large")              // Step 1 - Required
            .crustType("Thin Crust")    // Step 2 - Required
            .cheese("Mozzarella")       // Step 3 - Required
            .sauce("BBQ Sauce")         // Optional
            .addTopping("Pepperoni")    // Optional
            .addTopping("Mushrooms")    // Optional
            .build();
        
        System.out.println(pizza1);
        
        // Minimal pizza (only required fields)
        Pizza pizza2 = Pizza.newBuilder()
            .size("Medium")
            .crustType("Regular")
            .cheese("Cheddar")
            .build();
        
        System.out.println(pizza2);
        
        // Following line would NOT compile (wrong order):
        // Pizza invalid = Pizza.newBuilder().cheese("Mozzarella").size("Large");
        
        System.out.println("\n=== EXAMPLE 2: Secure Email Builder ===\n");
        
        // Must provide: from -> to -> subject -> body
        SecureEmail email = SecureEmail.newBuilder()
            .from("sender@example.com")
            .to("recipient@example.com")
            .subject("Important Meeting")
            .body("Meeting at 3 PM")
            .validate()
            .build();
        
        System.out.println(email);
        
        // This would throw exception (invalid email):
        try {
            SecureEmail invalid = SecureEmail.newBuilder()
                .from("invalid-email")  // Missing @
                .to("test@example.com")
                .subject("Test")
                .body("Test")
                .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
        
        System.out.println("\n=== EXAMPLE 3: Database Connection ===\n");
        
        // Enforced order: host -> port -> database -> username -> password
        DatabaseConnection conn = DatabaseConnection.builder()
            .host("localhost")
            .port(5432)
            .database("mydb")
            .username("admin")
            .password("secret123")
            .timeout(60)
            .useSSL(true)
            .connect();
        
        System.out.println(conn);
        
        // Minimal connection
        DatabaseConnection conn2 = DatabaseConnection.builder()
            .host("192.168.1.100")
            .port(3306)
            .database("testdb")
            .username("user")
            .password("pass")
            .connect();
        
        System.out.println(conn2);
        
        System.out.println("\n=== EXAMPLE 4: File Upload ===\n");
        
        byte[] fileContent = "Hello World".getBytes();
        
        FileUpload upload = FileUpload.builder()
            .filename("document.txt")
            .content(fileContent)
            .contentType("text/plain")
            .maxSize(10_000_000)
            .upload();
        
        System.out.println(upload);
        
        System.out.println("\n=== ADVANTAGES OF STEPWISE BUILDER ===");
        System.out.println("1. Compile-time safety - wrong order won't compile");
        System.out.println("2. Prevents incomplete objects");
        System.out.println("3. Clear step-by-step construction");
        System.out.println("4. Self-documenting API");
        System.out.println("5. IDE guides you through steps");
        
        System.out.println("\n=== DISADVANTAGES ===");
        System.out.println("1. More interfaces and code");
        System.out.println("2. Complex implementation");
        System.out.println("3. Less flexible than regular builder");
        System.out.println("4. Harder to maintain");
        
        System.out.println("\n=== WHEN TO USE ===");
        System.out.println("1. Complex objects with mandatory order");
        System.out.println("2. When validation at each step is needed");
        System.out.println("3. Critical configurations (DB, Security)");
        System.out.println("4. When preventing invalid states is crucial");
        
        System.out.println("\n=== KEY DIFFERENCE FROM REGULAR BUILDER ===");
        System.out.println("Regular Builder: All methods available anytime");
        System.out.println("Stepwise Builder: Next method available only after previous");
        System.out.println("Result: Compile-time guarantee of correct construction order");
    }
}
