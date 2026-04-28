# Grocery Inventory Management System — CI/CD Mini Project

## Tech Stack
| Tool | Purpose |
|------|---------|
| Spring Boot (Java 17) | REST API application |
| Maven | Build tool |
| Git | Version control |
| Jenkins | CI/CD pipeline |
| Docker | Containerisation |
| Kubernetes | Container orchestration |
| Ansible | Configuration management |

---

## Project Structure
```
grocery-cicd/
├── src/                          # Spring Boot application
│   ├── main/java/com/grocery/
│   │   ├── controller/           # REST endpoints
│   │   ├── model/                # JPA entity
│   │   ├── repository/           # Data access
│   │   └── service/              # Business logic
│   └── test/                     # Unit tests
├── k8s/
│   ├── namespace.yaml
│   ├── deployment.yaml           # 2-replica deployment
│   └── service.yaml              # NodePort service
├── ansible/
│   ├── inventory.ini             # Target hosts
│   └── playbook.yml              # Automation tasks
├── Dockerfile                    # Multi-stage build
├── Jenkinsfile                   # 9-stage CI/CD pipeline
└── pom.xml                       # Maven build config
```

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/items` | Get all items |
| GET | `/api/items/{id}` | Get item by ID |
| POST | `/api/items` | Create new item |
| PUT | `/api/items/{id}` | Update item |
| DELETE | `/api/items/{id}` | Delete item |
| GET | `/api/items/low-stock?threshold=10` | Low stock alert |
| GET | `/api/items/category/{category}` | Filter by category |
| GET | `/api/items/health` | Health check |

---

## Quick Start (Local)

```bash
# Build and run
mvn spring-boot:run

# Test the API
curl http://localhost:8080/api/items/health

# Add an item
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{"name":"Tomatoes","category":"Vegetables","quantity":50,"price":2.99,"unit":"kg"}'
```

---

## Docker

```bash
# Build image
docker build -t grocery-inventory:1.0 .

# Run container
docker run -p 8080:8080 grocery-inventory:1.0

# Verify
curl http://localhost:8080/api/items/health
```

---

## Kubernetes

```bash
# Apply all manifests
kubectl apply -f k8s/

# Check status
kubectl get pods -n grocery-app
kubectl get svc -n grocery-app

# Access app (NodePort)
curl http://<node-ip>:30080/api/items/health
```

---

## Ansible

```bash
# Run playbook
ansible-playbook -i ansible/inventory.ini ansible/playbook.yml -v
```

---

## Jenkins Pipeline Stages

1. **Checkout** — Clone from Git
2. **Build** — `mvn compile`
3. **Test** — `mvn test` + JUnit reports
4. **Package** — `mvn package` → JAR
5. **Docker Build** — Build image with tag
6. **Docker Push** — Push to Docker Hub
7. **Ansible** — Configure deployment nodes
8. **Kubernetes Deploy** — Rolling update
9. **Smoke Test** — Health check on deployed service

---

## Team
- Student 1: [Name] — [Roll No]
- Student 2: [Name] — [Roll No]
