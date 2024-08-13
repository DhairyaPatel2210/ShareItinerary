![ShareItinerary_CI_CD.png](/documentation/images/ShareItinerary_CI_CD.png)

As part of automating the deployment process, I have implemented a Continuous Integration and Continuous Deployment (CI/CD) pipeline. This pipeline automates the processes of testing, building JAR files, creating Docker images, pushing them to a Docker registry, deploying code on a Kubernetes Cluster running on Google Cloud’s Google Kubernetes Engine (GKE) service, and spinning up load balancers.

## **Steps:**

1. **Code Pushed to GitHub**
    
    When developing new features, committing changes to the main branch on GitHub triggers the CI/CD pipeline on CircleCI.
    
2. **Triggers CircleCI Pipeline**
    
    A project has been created in CircleCI and connected to GitHub with proper authorization. A new commit to the main branch of the repository triggers the pipeline. This configuration can also be adjusted for different branches.
    
3. **Jar Build with Unit Tests**
    
    The first step in the pipeline is to build the JAR file from the code on GitHub. Before building the JAR, the code undergoes unit testing to ensure each component functions as intended. After successful unit tests, the pipeline creates the JAR file from the latest code.
    
4. **Docker Image Build**
    
    The Docker image is built from the JAR file, ensuring that the application can run on any machine with Docker support. Important points to note:
    
    1. **Multi-Arch Docker Image Build:** For instance, building a Docker image on a machine with an ARM64 architecture CPU will generate an image compatible only with ARM64 machines. Since cloud service providers often use nodes with different architectures, I have provided support for building Docker images for both AMD64 and ARM64 architectures. This covers most scenarios, and additional architectures can be added in the future if needed.
    2. **Jar Build and Docker Image Build:** These steps are combined into a single job within the CircleCI workflow under "Build-and-Push." Ideally, they should be separated, but the Docker image build step requires the JAR file. Separating them would necessitate using CircleCI’s workspaces or caches to share the JAR file between jobs. Given that this is a personal project, combining the steps in a single job is sufficient.
5. **Pushes Images to DockerHub**
    
    This step involves pushing multi-architecture Docker images to DockerHub. Key points include:
    
    1. **Authorization with DockerHub:** CircleCI uses environment variables for credentials, which can be stored securely. To manage credentials across multiple projects, I used CircleCI’s organization context, making the environment variables accessible only to specific jobs.
    2. **Pushing out Docker Images:** The latest Docker images are pushed to DockerHub with the `latest` tag.
6. **Integration Test Run**
    
    While unit tests verify that individual components work independently, integration tests ensure that all components work together within the application. I used TestContainers to create a database image for my Spring Boot application, enabling actual database calls during testing. TestContainers automatically manages the lifecycle of these containers, creating and destroying them as part of the test process. The integration tests run in a separate job called "Tests" on a Linux-based virtual machine with Java and Docker pre-installed. CircleCI’s Docker images facilitate this setup. If all integration tests pass, the pipeline proceeds to the next step.
    
7. **GCP Authorization**
    
    After running tests and pushing images to the Docker registry, the pipeline reaches the deployment stage, which requires manual approval to prevent automatic deployments to production without oversight. I use Google Cloud as my service provider and deploy the application on GKE. Before deployment, I authorize GCP using service account credentials stored in CircleCI’s organization context.
    
8. **GCP Utilities Installation**
    
    Deploying the web application on Kubernetes requires the `kubectl` utility to be installed on the CI runners. This step ensures `kubectl` is available.
    
9. **Making Cluster Connection**
    
    Upon entering the deployment stage, the pipeline uses CircleCI’s GCP image, which includes the necessary GCP utilities. Using the `gcloud` utility, it establishes a connection to the existing Kubernetes cluster in GKE. If the cluster doesn’t exist or is not accessible, an error is thrown.
    
10. **Applying K8s Files To Cloud**
    
    If everything goes well, the pipeline applies Kubernetes configuration files to the Kubernetes server using `kubectl`. This step also spins up the ingress controller and L7 load balancer in GCP, exposing the web application to the public.
    

## **Future Improvements:**

- **Playground(Dev) and stage deployments:** Currently, there is only one deployment environment—the production environment. Ideally, we should have a playground environment for testing and experimenting without concerns about stability and a stage environment that closely mirrors production. The stage environment would serve as the final step before making new code live to users.
- **Adding smoke tests:** While integration tests are in place, smoke tests could be added. Unlike integration tests that run in a separate environment, smoke tests would be executed in the stage environment, which mimics production.

## **Tools Used**

**1.**

**Tool:** GitHub

- Type: Code Management Tool
- GitHub integrates seamlessly with the Git versioning system, allowing me to host code on remote servers and access it from anywhere in the world.

**2.**

**Tool:** CircleCI

- Type: CI/CD Tool
- CircleCI provides customizable workflows, parallel job execution, and reusable configurations through orbs. It supports Docker integration, dependency caching, and secure environment variable management. CircleCI also offers a large library of built-in Docker images, which are useful for running code in CI runners. For instance, CircleCI’s Java image simplifies compiling my Java Spring Boot project and running unit tests. Additionally, CircleCI offers 6,000 free build minutes each month, which is more than sufficient for my needs.

**3.**

**Tool:** Docker

- Type: Container management tool
- Docker is an open-source tool that allows developers to create, run, and share containers for applications. It ensures that applications can run on any machine that supports Docker.

**4.**

**Tool:** DockerHub

- Type: Docker Image Registry
- DockerHub offers a wide range of open-source, official Docker images for popular applications like PostgreSQL and Unix OS. It integrates seamlessly with Docker and provides a free private repository for container images.

**5.**

**Tool:** Google Cloud Platform

- Type: Cloud Services Platform
- The purpose of this project is to learn cloud services, Kubernetes, Docker, and other web technologies. GCP’s extensive cloud services, user-friendly documentation, and $300 in free credits for new users made it an ideal choice for this project.