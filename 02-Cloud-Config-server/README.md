# Spring Cloud Config Server - Remote Git Repository

This learning chapter is part of the micro-services-ws-1 workspace.

## Agenda

- [Problem we will solve](#problem-we-will-solve)
- [What you will learn](#what-you-will-learn)
- [How to run](#how-to-run)
- [Example](#example)
- [Key points or common mistakes](#key-points-or-common-mistakes)
- [Chapter summary and next step](#chapter-summary-and-next-step)
- [Common interview questions](#common-interview-questions-and-short-answers)
- [Existing content preserved](#existing-content-preserved)

## Problem We Will Solve

This chapter solves the problem of serving configuration from a remote GitHub repository instead of only a local folder.

## What You Will Learn

- How to point Config Server to GitHub
- How default labels work
- What changes when config is stored remotely

## How To Run

- Ensure internet access is available
- Run from 02-Cloud-Config-server: mvn spring-boot:run
- Open http://localhost:8888/centralized/default or /dev or /prod

## Example

`	ext
GET http://localhost:8888/centralized/prod
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [03-MS-Cloud-config-client](../03-MS-Cloud-config-client/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What is different from chapter 01?**  
This chapter uses a remote Git repository.

**Q2. When are credentials required?**  
Credentials are needed for a private repository.

**Q3. Why keep config in remote Git?**  
It makes config shareable across machines and teams.

**Q4. What port is used?**  
Port 8888.

**Q5. What is the default branch here?**  
The configured default label is main.

**Q6. What should not be stored plainly in Git?**  
Secrets such as passwords and tokens.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.

# **Reading the properties from the git repository.**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>sp*ring-cloud-config-server</artifactId>
</dependency>
```
---
# **Spring Cloud Config Server with Git Repository**
---
```properties
spring.application.name=Cloud-Config-server
server.port=8888
spring.cloud.config.server.git.uri=https://github.com/amitkvaio/msconfig.git
spring.cloud.config.server.git.default-label=main
```

# **URL**
```
# http://localhost:8888/centralized/default  
# http://localhost:8888/centralized/dev  
# http://localhost:8888/centralized/prod  
```
