[Click here to switch to English version](README.md)

**Java SDK 综合文档**

---

### 简介

本Java SDK 是一个强大的工具，旨在促进开发者与目标平台之间的无缝交互。本文档为开发者提供了如何有效地使用 SDK 的深入指南，确保开发者能够充分发挥其潜力。

---

### 目录

1. [前置条件](#前置条件)
2. [初始化](#初始化)
3. [公共方法](PublicMethods_CN.md)
4. [错误处理](#错误处理)
5. [日志](#日志)
6. [最佳实践](#最佳实践)
7. [结论](#结论)

---

### 前置条件

- 依赖:
    - `com.fasterxml.jackson.core:jackson-databind`
    - `org.slf4j:slf4j-api`
    - Java 内置的 `java.net.http.HttpClient`

---

### 初始化

要使用 SDK，请初始化 `UnifiedApiClient`：

```
Logger customLogger = LoggerFactory.getLogger(YourClass.class);
UnifiedApiClient client = new UnifiedApiClient("https://api.targetplatform.com", customLogger);
```

参数:
- `host`: 目标平台的基本 URL。
- `customLogger` (可选): 自定义日志记录器实例。如果未提供，SDK 将使用其默认日志记录器。

---

### 错误处理

SDK 会为各种错误抛出异常。处理这些异常以确保应用程序的平稳运行。例如，如果 API 不可用，SDK 将抛出带有消息 "API 不可用: [方法] [briefUri]" 的异常。

示例:

```
try {
RegisterDeviceResponse response = client.registerDevice(boxUUID, reqId, boxRegKey);
} catch (Exception e) {
System.err.println("错误: " + e.getMessage());
}
```

---

### 日志

SDK 使用 SLF4J 进行日志记录。默认情况下，它会记录有关 API 请求和响应的信息。如果在初始化期间提供了自定义日志记录器，SDK 将使用该日志记录器；否则，它将使用其默认日志记录器。

日志包括:
- 时间戳
- 函数名称
- 请求方法、路径、请求 ID、请求体以及任何附加header
- 响应状态码和主体

---

### 最佳实践

1. **初始化**: 始终使用正确的主机 URL 初始化 `UnifiedApiClient`。
2. **错误处理**: 定义全面的错误处理以管理 SDK 在运行中抛出的异常。
3. **日志**: 利用日志功能监视 API 交互并诊断问题。
4. **单元测试**: 定期运行单元测试以确保 SDK 的功能并及早发现潜在问题。

---

### 结论

本Java SDK 是开发者与目标平台互动的强大工具。通过遵循这一全面指南，开发者可以确保他们充分利用了 SDK 的潜力，从而实现高效和有效的平台交互。

---

**注意**: 本文档提供了 SDK 的详细概述。要获得更深入的详细信息，请始终参考 SDK 的源代码和附带的 Javadoc 注释。

---

