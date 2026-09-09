# Basic Terminology:
--

## 1. What is Scalability?
Scalability is the ability of a system to handle increased load by adding resources. The key word here is "ability", 
a scalable system can grow to meet demand without requiring a complete architectural overhaul.

## 2. What Availability Measures

Availability measures how often your system is operational and accessible to users. A highly available system continues 
functioning even when individual components fail.

## 3. Relaibility:
Reliability is the probability that a system will perform its intended function correctly over a given period of time, under specified conditions.

## 4. Latency vs Throughput vs Bandwidth
Latency is the time it takes for a single request to travel from source to destination and back. It measures delay.

Throughput is the amount of work completed per unit of time. It measures volume.

Bandwidth is the maximum rate at which data can be transferred. It measures capacity.

## 5. Understanding the CAP Theorem
The CAP theorem, introduced by Eric Brewer in 2000, states that a distributed system can only guarantee two out of three properties:

Consistency (C)— Every read receives the most recent write or an error.
Availability (A) — Every request (read/write) gets a response, but it may not be the latest data.
Partition Tolerance (P) — The system continues to operate even if network failures occur between nodes.

### 5.1 Why Can’t We Have All Three?
Network partitions (failures, latency, infrastructure issues) are inevitable in distributed systems. When a partition happens, the system must choose between Consistency and Availability:

If the system prioritizes Consistency, it may reject reads/writes until the partition is resolved.
If the system prioritizes Availability, it will return stale or inconsistent data to keep functioning.
This is why no distributed system can guarantee all three properties simultaneously.

Financial systems? Prioritize Consistency (CP).
Social media or e-commerce? Prioritize Availability (AP).
Single-node applications? You can have CA, but it won’t scale.
By aligning your system design with business requirements, you can build scalable, reliable, and performant distributed systems.

refer: https://medium.com/@damithns/cap-theorem-in-system-design-a-practical-approach-ef003ade3c53
https://algomaster.io/learn/system-design/scalability
