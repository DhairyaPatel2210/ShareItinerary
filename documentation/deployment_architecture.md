![ShareItinerary Architecture](/documentation/images/ShareItinerary_Arch.jpg)



**End Users (Mobile Devices and Desktop):** 

People can make requests to the web application using the domain name shareitineary.live. 

**Why I made use of GCP instead of AWS?**

AWS is largely being employed by industry. But, the purpose of this project is to learn cloud services, kubernetes, and docker, and other web technologies. To lower down the cost of this project, GCP’s $300 credits offering to every user is the motivation to make use of GCP. Almost all the configurations can be easily migrated from GCP to AWS, so it serves the purpose.

**DNS Lookup:**

Requests made from the end users will be routed to DNS service, and eventually it will be routed to Load Balancer of google cloud platform.

- Used Platform: Name.com
- Design Decision: This project made with maximizing learning potential in mind. So, lowering down the cost of the system is the top priority. My Student status allows me to make use of Github Student Developer Pack, and which allowed me to make use of [Name.com](http://Name.com). It provided me free Domain Name and SSL certificates for one year.

**GCP Load Balancer:**

An HTTP(S) type load balancer is automatically created by GCP when an Ingress object is defined in the Kubernetes cluster. This external Application Load Balancer acts as a proxy between clients and the application.

The project has specific configurations for the load balancer, such as periodic health checks to monitor the status of backend services. To configure these health checks, a `backend-config` object is created, allowing the load balancer to access the appropriate endpoint in the backend service to determine its health.

The project uses the Spring Boot Actuator module to check the health of the backend services.

- **Design Decision:** I use DNS to route requests to the load balancer's IP. This requirement is met by an external load balancer, which distributes HTTP and HTTPS traffic to backends hosted on various Google Cloud platforms, such as GKE. GCP's full support for GKE applications further solidified this choice.

**GCP Ingress Controller:**

An Ingress controller is necessary to route incoming requests to the appropriate services based on rules defined within the Ingress object. GCP provides its own Ingress controller, which integrates seamlessly with GKE and other GCP services.

![GKE Ingress Controller](/documentation/images/Ingress_Controller.png)

- **Design Decision:** There are multiple Ingress controllers available, such as NGINX, Contour, and Istio Ingress. While GCP also supports custom Ingress controllers, I chose GCP's native Ingress controller because it is automatically created with the Ingress object, simplifying the configuration process. Additionally, the auto-creation of the load balancer and integrated dashboards in GCP enhance debugging capabilities.

**Ingress Object:**

Ingress exposes HTTP and HTTPS routes from outside the cluster to services within the cluster. Traffic routing is managed by rules defined in the Ingress resource.

An Ingress controller is required to make the Ingress object function. In this project, creating an Ingress object triggers the use of the GKE Ingress controller, which also initiates the load balancers to handle incoming requests.

- **Design Decision:** Typically, to access multiple services deployed in a Kubernetes cluster, each service would need to be exposed individually, which could involve setting up separate IP addresses or load balancers. This can be complex and costly. Ingress provides a solution by acting as a single entry point for all traffic coming into the Kubernetes cluster. It routes requests to the correct service based on the URL path or other rules.

**Service (SVC):**

In Kubernetes, a Service is a method for exposing a network application running as one or more Pods in your cluster. ShareItinerary uses a NodePort service, which exposes the Service on each Node's IP at a static port, allowing the Ingress to route requests to these ports.

**Deployment:**

A "Deployment" in Kubernetes is like a manager that ensures the desired number of Pods are running and handles updates. For example, if you want your website to run on five different servers simultaneously, the Deployment ensures there are always five Pods running. If one Pod fails, the Deployment replaces it with a new one. When updating your website, the Deployment facilitates a smooth transition from the old version to the new one.

**Pods:**

A "Pod" is the smallest deployable unit in Kubernetes and usually contains one container (similar to a lightweight virtual machine). Inside this Pod are all the necessary components for your application, such as code, settings, and tools. Although a Pod typically contains one container, it can hold more if needed.