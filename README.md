# ossrep-account-api

Run in Dev Mode
```shell script
./mvnw clean quarkus:dev
```
or 
```shell script
quarkus dev --clean
```

Build Locally
```shell script
./mvnw clean package
podman build -t quay.io/ossrep/ossrep-account-api:0.0.1 .
```

Run Locally
```shell script
podman network create ossrep-network
podman run --name ossrep-account-db \
  -d --rm \
  -e POSTGRES_DB=ossrep-account-db \
  -e POSTGRES_USER=testuser \
  -e POSTGRES_PASSWORD=Pass123! \
  -p 5432:5432 \
  --network ossrep-network \
  docker.io/library/postgres:17
  
podman run --name ossrep-account-api \
  -i --rm \
  -e DB_URL="jdbc:postgresql://ossrep-account-db:5432/ossrep-account-db" \
  -e DB_USER=testuser \
  -e DB_PASSWORD="Pass123!" \
  -p 8080:8080 \
  --network ossrep-network \
  quay.io/ossrep/ossrep-account-api:0.0.1
```

Interact with DB
```shell
podman exec -it ossrep-account-db psql -U testuser -d ossrep-account-db
```

## Other Environment Configuration Options

LOG_LEVEL
LOG_LEVEL_OSSREP