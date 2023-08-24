[点击此处切换到中文版本](src/main/doc/README_CN.md)

**UnifiedApiClient SDK Comprehensive Documentation**

---

### Introduction

The Java SDK is a robust interface designed to facilitate seamless interactions between developers and the target platform. This documentation provides an in-depth guide on how to utilize the SDK effectively, ensuring that developers can harness its full potential.

---

### Table of Contents

1. [Prerequisites](#prerequisites)
2. [Initialization](#initialization)
3. [Public Methods](#public-methods)
4. [Error Handling](#error-handling)
5. [Logging](#logging)
6. [Best Practices](#best-practices)
7. [Conclusion](#conclusion)

---

### Prerequisites

- Dependencies:
    - `com.fasterxml.jackson.core:jackson-databind`
    - `org.slf4j:slf4j-api`
    - Java's built-in `java.net.http.HttpClient`

---

### Initialization

To utilize the SDK, initialize the `UnifiedApiClient`:

```java
Logger customLogger = LoggerFactory.getLogger(YourClass.class);
UnifiedApiClient client = new UnifiedApiClient("https://api.targetplatform.com", customLogger);
```

Parameters:
- `host`: The base URL of the target platform.
- `customLogger` (optional): A custom logger instance. If not provided, the SDK will use its default logger.

---

### [Public Methods](src/main/doc/PublicMethods.md)

---

### Error Handling

The SDK throws exceptions for various errors. Handle these exceptions to ensure smooth application operation. For instance, if an API is unavailable, the SDK will throw an exception with the message "API not available: [METHOD] [briefUri]".

Example:

```java
try {
    RegisterDeviceResponse response = client.registerDevice(boxUUID, reqId, boxRegKey);
} catch (Exception e) {
    System.err.println("Error: " + e.getMessage());
}
```

---

### Logging

The SDK uses SLF4J for logging. By default, it logs information about API requests and responses. If you provide a custom logger during initialization, the SDK will use that logger; otherwise, it will use its default logger.

Logs include:
- Timestamp
- Function name
- Request method, path, request ID, request body, and any additional headers
- Response status code and body

---

### Best Practices

1. **Initialization**: Always initialize the `UnifiedApiClient` with the correct host URL.
2. **Error Handling**: Implement comprehensive error handling to manage exceptions thrown by the SDK.
3. **Logging**: Utilize the logging feature to monitor API interactions and diagnose issues.
4. **Unit Testing**: Regularly run unit tests to ensure the SDK's functionality and catch potential issues early.

---

### Conclusion

The Java SDK is a powerful tool for developers to interact with the target platform. By following this comprehensive guide, developers can ensure they're using the SDK to its fullest potential, leading to efficient and effective platform interactions.

---

**Note**: This documentation provides a detailed overview of the SDK. For even more in-depth details, always refer to the SDK's source code and accompanying Javadoc comments.

---

