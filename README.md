# hello_quarkus_kotlin_gcp

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/hello_quarkus_kotlin_gcp-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any
  of the extensions that depend on it.
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin

## Install on Google Cloud Platform (GCP) using Cloud Run

Google Cloud Run allows you to run your Docker containers inside Google Cloud Platform in a managed way. (https://quarkus.io/guides/deploying-to-google-cloud)

- Install `gcloud` CLI: https://cloud.google.com/sdk/docs/install-sdk
- Create a new project during `gcloud init`:  hello-quarkus-kotlin-gcp
- if not logged-in (Check with `gcloud auth list`): `gcloud auth login` 
- `cp src/main/docker/Dockerfile.jvm Dockerfile`
-  `mvn clean package`
- create [.gcloudignore](.gcloudignore);  test it with  `gcloud meta list-files-for-upload`
- Set GCP project: `gcloud config set project hello-quarkus-kotlin-gcp`
- (Make sure you have billing activated in the GCP for the new projects to avoid the "serviceusage.services.use" permission error in the next step)
- Upload files and build the Docker image project `gcloud builds submit --tag gcr.io/hello-quarkus-kotlin-gcp/hello1`
- Check if the image exists in the repository:  https://console.cloud.google.com/artifacts/docker/hello-quarkus-kotlin-gcp/us/gcr.io?project=hello-quarkus-kotlin-gcp
- Lunch it on Cloud Run: `gcloud run deploy --image gcr.io/hello-quarkus-kotlin-gcp/hello1`
- Finally, you get a Service URL and can use the app: https://hello1-r2cbzfff2q-ew.a.run.app
- Running App can be managed in the GCP console: https://console.cloud.google.com/run?project=hello-quarkus-kotlin-gcp

Deploying an update:
- if not logged-in (Check with `gcloud auth list`): `gcloud auth login`
-  `mvn clean package`
-  `gcloud builds submit --tag gcr.io/hello-quarkus-kotlin-gcp/hello1`
-  `gcloud run deploy --image gcr.io/hello-quarkus-kotlin-gcp/hello1`