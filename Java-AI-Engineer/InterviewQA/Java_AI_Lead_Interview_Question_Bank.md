# Java AI Lead Interview Question Bank — 10 YOE

A topic-wise master checklist for Senior/Lead Java AI Engineer interviews.

## Priority Map

| Priority | Area | Target Depth |
|---|---|---|
| P0 | Core Java | Expert |
| P0 | Spring Boot | Expert |
| P0 | Microservices | Expert |
| P0 | HLD System Design | Expert |
| P0 | GenAI / LLM | Strong |
| P0 | RAG | Strong |
| P0 | Agentic AI | Strong |
| P1 | Event-Driven Architecture | Strong |
| P1 | LLD / Design Patterns | Strong |
| P1 | Kubernetes | Strong |
| P1 | Docker | Strong |
| P1 | CI/CD | Strong |
| P2 | Observability / Security / Cloud | Strong |

## 1. Core Java

### Java fundamentals
1. Explain JVM architecture.
2. JDK vs JRE vs JVM.
3. How does Java memory management work?
4. Heap vs Stack.
5. Young generation vs Old generation.
6. How does Garbage Collection work?
7. G1GC vs ZGC.
8. What causes OutOfMemoryError?
9. What causes memory leaks in Java?
10. How would you troubleshoot high JVM memory usage?

### Collections
11. How does HashMap work internally?
12. What happens during a HashMap collision?
13. HashMap vs ConcurrentHashMap.
14. How does ConcurrentHashMap work?
15. ArrayList vs LinkedList.
16. HashSet vs TreeSet.
17. HashMap vs TreeMap.
18. Fail-fast vs fail-safe iterators.
19. Comparable vs Comparator.
20. When would you use CopyOnWriteArrayList?
21. What happens if a mutable object is used as a HashMap key?

### Concurrency
22. Process vs thread.
23. synchronized vs Lock.
24. ReentrantLock vs synchronized.
25. volatile — what problem does it solve?
26. AtomicInteger vs synchronized.
27. What is CAS?
28. What is a race condition?
29. What is deadlock?
30. How do you detect/prevent deadlocks?
31. ExecutorService architecture.
32. FixedThreadPool vs CachedThreadPool.
33. CompletableFuture.
34. Parallel streams — when should you avoid them?
35. How would you design a thread-safe singleton?
36. How would you troubleshoot thread starvation?

### Modern Java
37. Java 8 features.
38. Streams internals.
39. map vs flatMap.
40. Optional best practices.
41. Functional interfaces.
42. Java 17/21 features.
43. Records.
44. Sealed classes.
45. Virtual threads.
46. When should virtual threads NOT be used?

## 2. Spring Boot

### Spring fundamentals
47. Explain Spring IoC/DI.
48. Bean lifecycle.
49. Bean scopes.
50. @Component vs @Service vs @Repository.
51. Constructor injection vs field injection.
52. How does Spring resolve dependencies?
53. What happens during Spring Boot startup?
54. Auto-configuration internals.
55. Spring Boot starters.

### Configuration
56. application.properties vs YAML.
57. Profiles.
58. Externalized configuration.
59. @ConfigurationProperties vs @Value.
60. Spring Cloud Config.
61. Secrets management.
62. How would you manage configuration across 50 microservices?

### REST APIs
63. REST principles.
64. PUT vs PATCH.
65. Idempotency.
66. API versioning.
67. Global exception handling.
68. Validation.
69. Pagination.
70. Rate limiting.
71. API authentication vs authorization.
72. How would you design a production-grade REST API?

### Spring internals
73. How does Spring AOP work?
74. JDK proxy vs CGLIB.
75. @Transactional internals.
76. Why does @Transactional sometimes not work?
77. Transaction propagation.
78. Transaction isolation.
79. Lazy vs eager loading.
80. N+1 problem.
81. Spring Boot Actuator.
82. Custom health checks.

## 3. Spring Data JPA / Database

83. JPA vs Hibernate.
84. First-level vs second-level cache.
85. Entity lifecycle.
86. Lazy vs eager fetching.
87. N+1 problem.
88. How do you detect N+1?
89. How do you fix N+1?
90. JOIN FETCH vs EntityGraph.
91. Optimistic vs pessimistic locking.
92. Database indexing.
93. Composite indexes.
94. Query optimization.
95. Connection pooling.
96. HikariCP internals.
97. What happens when DB connections are exhausted?
98. Transaction boundaries.
99. Distributed transactions.
100. SQL vs NoSQL — when would you choose each?

## 4. Microservices

101. Monolith vs microservices.
102. When should you NOT use microservices?
103. How do you identify service boundaries?
104. Database-per-service pattern.
105. API Gateway.
106. Service discovery.
107. Config server.
108. Circuit breaker.
109. Retry.
110. Timeout.
111. Bulkhead.
112. Rate limiting.
113. Distributed tracing.
114. Centralized logging.
115. Correlation IDs.
116. Synchronous vs asynchronous communication.
117. REST vs gRPC.
118. Inter-service authentication.
119. Distributed transactions.
120. Saga pattern.
121. Choreography vs orchestration.
122. Idempotency.
123. Duplicate request handling.
124. Eventual consistency.
125. Backward-compatible API evolution.
126. Zero-downtime deployment.
127. Blue-green deployment.
128. Canary deployment.
129. Handling cascading failures.
130. Designing resilient microservices.

### Lead-level scenarios
131. One microservice is taking 10 seconds. How do you troubleshoot it?
132. Service A calls B → C → D. How do you prevent cascading failures?
133. Your service receives the same request twice. How do you handle it?
134. Database is down for 10 minutes. What should your service do?
135. One service suddenly receives 10× traffic. What happens?
136. How would you migrate a monolith to microservices?
137. How would you handle distributed transactions?

## 5. Event-Driven Architecture

### Fundamentals
138. What is Event-Driven Architecture?
139. Event vs command.
140. Event producer vs consumer.
141. Queue vs topic.
142. Kafka architecture.
143. Kafka partition.
144. Consumer group.
145. Offset.
146. Consumer rebalancing.
147. Ordering guarantees.
148. At-least-once vs at-most-once vs exactly-once.
149. Event replay.
150. Event retention.

### Reliability
151. Duplicate events — how do you handle them?
152. Idempotent consumers.
153. Dead Letter Queue.
154. Retry strategy.
155. Poison messages.
156. Backpressure.
157. Consumer lag.
158. Outbox pattern.
159. Transactional outbox.
160. CDC vs Outbox.
161. Saga using events.
162. Event schema evolution.
163. Schema Registry.
164. Event versioning.

### Architecture scenarios
165. Design an Order → Payment → Inventory → Shipping event-driven system.
166. How would you guarantee order processing?
167. What happens if Payment succeeds but Inventory fails?
168. How do you recover failed events?
169. How do you guarantee no duplicate payment?
170. How would you monitor Kafka in production?

## 6. System Design — HLD

### Fundamentals
171. Functional vs non-functional requirements.
172. Scalability.
173. Availability.
174. Reliability.
175. Consistency.
176. CAP theorem.
177. PACELC.
178. Horizontal vs vertical scaling.
179. Load balancing.
180. Caching.
181. CDN.
182. Database sharding.
183. Replication.
184. Partitioning.
185. Read replicas.
186. Message queues.
187. Event streaming.
188. Distributed locking.
189. Rate limiting.
190. Backpressure.

### Must-practice designs
191. Design URL Shortener.
192. Design Uber-like ride booking system.
193. Design Netflix-like video streaming.
194. Design Amazon-like e-commerce platform.
195. Design payment system.
196. Design notification system.
197. Design Twitter/X-like feed.
198. Design WhatsApp-like messaging system.
199. Design food delivery system.
200. Design job scheduler.
201. Design API Gateway.
202. Design distributed rate limiter.
203. Design distributed cache.
204. Design logging platform.
205. Design real-time analytics system.
206. Design file upload/storage system.
207. Design order management system.
208. Design inventory management system.

### AI-focused HLD
209. Design a RAG platform.
210. Design an enterprise AI chatbot.
211. Design an LLM gateway.
212. Design a multi-agent platform.
213. Design an AI document processing pipeline.
214. Design an AI-powered customer support system.
215. Design an AI code-review platform.
216. Design an LLM observability platform.
217. Design an AI recommendation system.
218. Design an agentic incident-management system.

## 7. LLD

### Object-oriented design
219. SOLID principles.
220. DRY vs KISS.
221. Composition vs inheritance.
222. Dependency inversion.
223. Interface segregation.
224. Open/closed principle.

### Design patterns
225. Singleton.
226. Factory.
227. Abstract Factory.
228. Builder.
229. Strategy.
230. Observer.
231. Decorator.
232. Adapter.
233. Facade.
234. Template Method.
235. Command.
236. Chain of Responsibility.
237. State.
238. Proxy.

### LLD problems
239. Design Parking Lot.
240. Design Elevator.
241. Design Vending Machine.
242. Design ATM.
243. Design Library Management.
244. Design Hotel Booking.
245. Design Snake & Ladder.
246. Design Tic-Tac-Toe.
247. Design Rate Limiter.
248. Design Notification Framework.
249. Design Payment Processing Framework.
250. Design a Retry Framework.
251. Design a Circuit Breaker.
252. Design a Job Scheduler.
253. Design an LLM provider abstraction layer.

## 8. Docker

254. What is Docker?
255. Container vs VM.
256. Docker image vs container.
257. Dockerfile.
258. Docker layers.
259. Multi-stage builds.
260. Docker networking.
261. Docker volumes.
262. Docker Compose.
263. Container health checks.
264. Docker security.
265. How do you reduce image size?
266. How do you troubleshoot a container that keeps restarting?
267. How do you pass secrets/configuration to containers?
268. How would you containerize a Spring Boot application?

## 9. Kubernetes

### Fundamentals
269. Kubernetes architecture.
270. Control plane vs worker nodes.
271. Pod.
272. Deployment.
273. ReplicaSet.
274. StatefulSet.
275. DaemonSet.
276. Job vs CronJob.
277. Service.
278. ConfigMap.
279. Secret.
280. Namespace.
281. Ingress.
282. PersistentVolume.
283. PersistentVolumeClaim.

### Production
284. Liveness vs readiness vs startup probes.
285. Resource requests vs limits.
286. HPA.
287. Cluster autoscaling.
288. Rolling deployments.
289. Blue-green deployment.
290. Canary deployment.
291. Pod disruption budget.
292. Affinity/anti-affinity.
293. Taints and tolerations.
294. Kubernetes networking.
295. Service discovery.
296. Kubernetes security.
297. RBAC.
298. How do you troubleshoot CrashLoopBackOff?
299. How do you troubleshoot OOMKilled?
300. How do you troubleshoot a pod that cannot connect to another service?

## 10. CI/CD

301. CI vs CD.
302. Git branching strategies.
303. GitFlow vs trunk-based development.
304. Build pipeline design.
305. Maven build lifecycle.
306. Unit vs integration tests.
307. Code quality gates.
308. SonarQube.
309. Artifact repositories.
310. Docker image build pipeline.
311. Security scanning.
312. Infrastructure deployment.
313. Kubernetes deployment pipeline.
314. Blue-green deployment.
315. Canary deployment.
316. Rollback strategy.
317. Deployment approvals.
318. Secrets in CI/CD.
319. Pipeline failure troubleshooting.
320. Design an end-to-end Git → Build → Test → Docker → Kubernetes → Production pipeline.

## 11. GenAI / LLM

### LLM fundamentals
321. What is an LLM?
322. Transformer architecture.
323. Attention mechanism.
324. Self-attention.
325. Tokens.
326. Context window.
327. Embeddings.
328. Temperature.
329. Top-p.
330. Prompt engineering.
331. System vs user vs assistant messages.
332. Hallucination.
333. LLM limitations.
334. Fine-tuning vs prompting.
335. RAG vs fine-tuning.

### LLM production
336. How do you reduce hallucinations?
337. How do you control LLM cost?
338. How do you reduce latency?
339. Streaming LLM responses.
340. Token management.
341. Context management.
342. Model selection.
343. Fallback models.
344. LLM caching.
345. Prompt caching.
346. Structured output.
347. Function/tool calling.
348. Guardrails.
349. Prompt injection.
350. Data leakage.
351. PII protection.
352. LLM evaluation.

## 12. RAG

### Core
353. What is RAG?
354. Why RAG instead of fine-tuning?
355. RAG architecture.
356. Document ingestion pipeline.
357. Chunking.
358. Chunk size selection.
359. Chunk overlap.
360. Embedding models.
361. Vector databases.
362. Similarity search.
363. Cosine similarity.
364. Metadata filtering.
365. Top-K.
366. Hybrid search.
367. BM25.
368. Semantic search.
369. Reranking.
370. Query rewriting.
371. HyDE.
372. Multi-query retrieval.
373. Parent-child retrieval.
374. Context compression.
375. Citation/grounding.
376. RAG evaluation.
377. Retrieval precision/recall.
378. Context relevance.
379. Faithfulness.
380. Answer relevance.

### Advanced RAG
381. Naive RAG vs Advanced RAG.
382. Agentic RAG.
383. Graph RAG.
384. Multi-modal RAG.
385. Corrective RAG.
386. Self-RAG.
387. Adaptive RAG.
388. How do you prevent irrelevant chunks?
389. How do you handle very large documents?
390. How do you update embeddings when documents change?
391. How do you handle deleted documents?
392. How do you secure enterprise RAG?

### RAG system design
393. Design an enterprise RAG platform for 1M documents.
394. Design multi-tenant RAG.
395. Design RAG with authorization-aware retrieval.
396. Design RAG with hybrid search.
397. Design RAG evaluation pipeline.

## 13. Agentic AI

### Fundamentals
398. What is an AI agent?
399. LLM application vs AI agent.
400. Agent vs workflow.
401. Tool calling.
402. Function calling.
403. Planning.
404. Reasoning.
405. Reflection.
406. Memory.
407. Short-term vs long-term memory.
408. Agent state.
409. Agent orchestration.
410. Multi-agent architecture.
411. Supervisor agent.
412. Planner/executor architecture.
413. ReAct pattern.
414. Human-in-the-loop.
415. Agent guardrails.
416. Agent permissions.
417. Agent observability.
418. Agent evaluation.
419. Agent failure handling.
420. Agent loops.
421. How do you prevent infinite tool-calling loops?
422. How do you control agent cost?
423. How do you prevent an agent from taking unsafe actions?

### Agentic architecture scenarios
424. Design an AI Software Engineer Agent.
425. Design an AI incident RCA agent.
426. Design an AI customer support agent.
427. Design an AI research agent.
428. Design a multi-agent travel planner.
429. Design an agent that monitors production incidents and performs RCA.
430. Design an agent that analyzes GitLab failures, logs, and creates an RCA.

## 14. AI + Java Integration

431. How do you integrate an LLM into Spring Boot?
432. How would you build an AI REST API?
433. How would you implement streaming LLM responses?
434. How would you integrate Java with OpenAI/Anthropic/Hugging Face models?
435. How would you build an embedding service?
436. How would you integrate a vector database with Spring Boot?
437. How would you implement RAG using Java?
438. How would you implement tool calling?
439. How would you build an LLM gateway?
440. How would you support multiple LLM providers?
441. How would you implement model fallback?
442. How would you implement token/cost tracking?
443. How would you implement prompt versioning?
444. How would you monitor LLM requests?
445. How would you secure an enterprise AI API?
446. How would you deploy an AI microservice on Kubernetes?

## 15. Observability & Production Engineering

447. Logging vs metrics vs tracing.
448. OpenTelemetry.
449. Distributed tracing.
450. Trace ID vs Span ID.
451. Prometheus.
452. Grafana.
453. Application Insights.
454. ELK.
455. SLI vs SLO vs SLA.
456. Error budgets.
457. Alerting strategy.
458. JVM monitoring.
459. Kubernetes monitoring.
460. Kafka monitoring.
461. API latency monitoring.
462. AI/LLM observability.
463. Token usage monitoring.
464. LLM latency.
465. RAG retrieval monitoring.
466. Agent tool-call monitoring.

## 16. Security

467. OAuth2.
468. JWT.
469. OIDC.
470. Authentication vs authorization.
471. RBAC.
472. API security.
473. mTLS.
474. Secrets management.
475. Encryption at rest/in transit.
476. SQL injection.
477. CORS.
478. CSRF.
479. Rate limiting.
480. Zero-trust architecture.
481. Prompt injection.
482. Indirect prompt injection.
483. RAG data leakage.
484. LLM jailbreaks.
485. AI authorization.

## 17. Cloud / Azure

486. Azure Functions architecture.
487. Azure App Service.
488. Azure Kubernetes Service.
489. Azure Storage.
490. Azure Service Bus.
491. Azure Event Hubs.
492. Azure Key Vault.
493. Azure Application Insights.
494. Azure OpenAI.
495. Azure networking.
496. Azure identity.
497. Managed Identity.
498. Azure scaling.
499. Azure disaster recovery.
500. How would you deploy a Spring Boot + RAG application on Azure?

## Top 30 Must-Master Questions

1. HashMap internals
2. ConcurrentHashMap
3. Java concurrency
4. CompletableFuture
5. JVM/GC troubleshooting
6. Spring Boot internals
7. @Transactional
8. JPA N+1
9. Microservice boundaries
10. Circuit breaker / retry / timeout
11. Saga pattern
12. Idempotency
13. Event-driven architecture
14. Kafka partitions / consumer groups
15. Outbox pattern
16. Event ordering and exactly-once semantics
17. Distributed systems consistency
18. HLD interview framework
19. Design a payment system
20. Design an order management system
21. Kubernetes production troubleshooting
22. CI/CD architecture
23. LLM fundamentals
24. RAG architecture
25. Advanced RAG
26. RAG evaluation
27. LLM security
28. Agentic AI architecture
29. Multi-agent orchestration
30. Design an enterprise AI platform using Java + Spring Boot + Kafka + Kubernetes + RAG + Agents

## Lead-Level Answer Framework

For every major topic, prepare your answer using:

1. What — concise definition
2. Why — problem solved
3. How — architecture / internal working
4. Trade-offs — when not to use it
5. Production example — realistic scenario
6. Failure scenarios — what can go wrong
7. Observability — how you detect it
8. Scale — behavior at 10×/100× traffic
9. Security — how to protect it
10. Design decision — why this approach over alternatives

## Target Interview Positioning

Java 17/21 → Spring Boot → Microservices → Kafka/Event-Driven → Docker → Kubernetes → CI/CD → Cloud/Azure → LLM → RAG → Agentic AI → AI System Design

Positioning goal:

> I can design and lead scalable distributed systems and extend them with production-grade GenAI capabilities.
