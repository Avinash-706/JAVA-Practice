// =====================================================================
//          FLUENT INTERFACE DESIGN - ADVANCED PATTERNS
// =====================================================================
// Fluent Interface: API design that reads like natural language
// Uses method chaining to create expressive, readable code

// EXAMPLE 1: Query Builder (SQL-like fluent interface)
class QueryBuilder {
    private StringBuilder query;
    private String table;
    private String whereClause;
    private String orderBy;
    private int limit;
    
    public QueryBuilder() {
        this.query = new StringBuilder();
    }
    
    public QueryBuilder select(String... columns) {
        query.append("SELECT ");
        if(columns.length == 0) {
            query.append("*");
        } else {
            query.append(String.join(", ", columns));
        }
        return this;
    }
    
    public QueryBuilder from(String table) {
        this.table = table;
        query.append(" FROM ").append(table);
        return this;
    }
    
    public QueryBuilder where(String condition) {
        this.whereClause = condition;
        query.append(" WHERE ").append(condition);
        return this;
    }
    
    public QueryBuilder and(String condition) {
        query.append(" AND ").append(condition);
        return this;
    }
    
    public QueryBuilder or(String condition) {
        query.append(" OR ").append(condition);
        return this;
    }
    
    public QueryBuilder orderBy(String column) {
        this.orderBy = column;
        query.append(" ORDER BY ").append(column);
        return this;
    }
    
    public QueryBuilder limit(int limit) {
        this.limit = limit;
        query.append(" LIMIT ").append(limit);
        return this;
    }
    
    public String build() {
        return query.toString() + ";";
    }
    
    public void execute() {
        System.out.println("Executing: " + build());
    }
}

// EXAMPLE 2: Email Builder (Fluent API)
class Email {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String cc;
    private String bcc;
    private boolean isHTML;
    
    private Email() {}
    
    public static EmailBuilder builder() {
        return new EmailBuilder();
    }
    
    public static class EmailBuilder {
        private Email email;
        
        public EmailBuilder() {
            email = new Email();
        }
        
        public EmailBuilder from(String from) {
            email.from = from;
            return this;
        }
        
        public EmailBuilder to(String to) {
            email.to = to;
            return this;
        }
        
        public EmailBuilder subject(String subject) {
            email.subject = subject;
            return this;
        }
        
        public EmailBuilder body(String body) {
            email.body = body;
            return this;
        }
        
        public EmailBuilder cc(String cc) {
            email.cc = cc;
            return this;
        }
        
        public EmailBuilder bcc(String bcc) {
            email.bcc = bcc;
            return this;
        }
        
        public EmailBuilder asHTML() {
            email.isHTML = true;
            return this;
        }
        
        public Email build() {
            return email;
        }
        
        public void send() {
            System.out.println("Sending email...");
            System.out.println("From: " + email.from);
            System.out.println("To: " + email.to);
            System.out.println("Subject: " + email.subject);
            System.out.println("Body: " + email.body);
            if(email.cc != null) System.out.println("CC: " + email.cc);
            if(email.bcc != null) System.out.println("BCC: " + email.bcc);
            System.out.println("HTML: " + email.isHTML);
            System.out.println("Email sent successfully!\n");
        }
    }
}

// EXAMPLE 3: HTTP Request Builder
class HttpRequest {
    private String method;
    private String url;
    private String body;
    private java.util.Map<String, String> headers;
    private java.util.Map<String, String> params;
    
    private HttpRequest() {
        headers = new java.util.HashMap<>();
        params = new java.util.HashMap<>();
    }
    
    public static HttpRequestBuilder builder() {
        return new HttpRequestBuilder();
    }
    
    public static class HttpRequestBuilder {
        private HttpRequest request;
        
        public HttpRequestBuilder() {
            request = new HttpRequest();
        }
        
        public HttpRequestBuilder get(String url) {
            request.method = "GET";
            request.url = url;
            return this;
        }
        
        public HttpRequestBuilder post(String url) {
            request.method = "POST";
            request.url = url;
            return this;
        }
        
        public HttpRequestBuilder put(String url) {
            request.method = "PUT";
            request.url = url;
            return this;
        }
        
        public HttpRequestBuilder delete(String url) {
            request.method = "DELETE";
            request.url = url;
            return this;
        }
        
        public HttpRequestBuilder header(String key, String value) {
            request.headers.put(key, value);
            return this;
        }
        
        public HttpRequestBuilder param(String key, String value) {
            request.params.put(key, value);
            return this;
        }
        
        public HttpRequestBuilder body(String body) {
            request.body = body;
            return this;
        }
        
        public HttpRequestBuilder contentType(String contentType) {
            request.headers.put("Content-Type", contentType);
            return this;
        }
        
        public HttpRequestBuilder authorization(String token) {
            request.headers.put("Authorization", "Bearer " + token);
            return this;
        }
        
        public HttpRequest build() {
            return request;
        }
        
        public void execute() {
            System.out.println("Executing HTTP Request:");
            System.out.println("Method: " + request.method);
            System.out.println("URL: " + request.url);
            if(!request.params.isEmpty()) {
                System.out.println("Params: " + request.params);
            }
            if(!request.headers.isEmpty()) {
                System.out.println("Headers: " + request.headers);
            }
            if(request.body != null) {
                System.out.println("Body: " + request.body);
            }
            System.out.println("Request executed!\n");
        }
    }
}

// EXAMPLE 4: Test Assertion Builder (JUnit-style)
class Assertion {
    private Object actual;
    
    private Assertion(Object actual) {
        this.actual = actual;
    }
    
    public static Assertion assertThat(Object actual) {
        return new Assertion(actual);
    }
    
    public Assertion isEqualTo(Object expected) {
        if(actual.equals(expected)) {
            System.out.println("✓ Assertion passed: " + actual + " equals " + expected);
        } else {
            System.out.println("✗ Assertion failed: " + actual + " does not equal " + expected);
        }
        return this;
    }
    
    public Assertion isNotNull() {
        if(actual != null) {
            System.out.println("✓ Assertion passed: value is not null");
        } else {
            System.out.println("✗ Assertion failed: value is null");
        }
        return this;
    }
    
    public Assertion isInstanceOf(Class<?> clazz) {
        if(clazz.isInstance(actual)) {
            System.out.println("✓ Assertion passed: " + actual + " is instance of " + clazz.getSimpleName());
        } else {
            System.out.println("✗ Assertion failed: " + actual + " is not instance of " + clazz.getSimpleName());
        }
        return this;
    }
}

public class FluentInterfaceDesign {
    
    public static void main(String[] args) {
        
        System.out.println("=== QUERY BUILDER (SQL-like) ===\n");
        
        // Simple query
        QueryBuilder query1 = new QueryBuilder()
            .select("id", "name", "email")
            .from("users")
            .where("age > 18")
            .orderBy("name")
            .limit(10);
        
        System.out.println(query1.build());
        
        // Complex query with multiple conditions
        QueryBuilder query2 = new QueryBuilder()
            .select()
            .from("products")
            .where("category = 'Electronics'")
            .and("price < 1000")
            .or("discount > 20")
            .orderBy("price")
            .limit(5);
        
        query2.execute();
        
        System.out.println("\n=== EMAIL BUILDER ===\n");
        
        // Simple email
        Email.builder()
            .from("sender@example.com")
            .to("recipient@example.com")
            .subject("Hello")
            .body("This is a test email")
            .send();
        
        // Complex email with CC, BCC, HTML
        Email.builder()
            .from("admin@company.com")
            .to("team@company.com")
            .cc("manager@company.com")
            .bcc("hr@company.com")
            .subject("Monthly Report")
            .body("<h1>Report</h1><p>Details...</p>")
            .asHTML()
            .send();
        
        System.out.println("=== HTTP REQUEST BUILDER ===\n");
        
        // GET request
        HttpRequest.builder()
            .get("https://api.example.com/users")
            .param("page", "1")
            .param("limit", "10")
            .header("Accept", "application/json")
            .execute();
        
        // POST request with authentication
        HttpRequest.builder()
            .post("https://api.example.com/users")
            .contentType("application/json")
            .authorization("abc123token")
            .body("{\"name\":\"John\",\"email\":\"john@example.com\"}")
            .execute();
        
        System.out.println("=== TEST ASSERTIONS ===\n");
        
        // Chained assertions
        Assertion.assertThat("Hello")
            .isNotNull()
            .isEqualTo("Hello")
            .isInstanceOf(String.class);
        
        System.out.println("\n=== FLUENT INTERFACE PRINCIPLES ===");
        System.out.println("1. Methods return 'this' or builder object");
        System.out.println("2. Method names read like natural language");
        System.out.println("3. Creates domain-specific language (DSL)");
        System.out.println("4. Improves code readability and maintainability");
        System.out.println("5. Common in modern frameworks (JUnit, Mockito, etc.)");
    }
}
