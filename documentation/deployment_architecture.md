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

HTTP(S) type of load balancer is created by GCP upon the creation of Ingress object in kubernetes cluster and which is external load balancer. An external Application Load Balancer acts as a proxy between clients and application. 

The project have specific configurations for the load balancer. Such as, load balancers make health check requests periodically to maintain the information about the services to which the load balancer will transfer the requests. To configure health checks, backend-config object is being created and will help load balancer to access the required endpoint in the backend service to find the health of the service. 

Project uses spring boot actuator module to test the health of the backend services.

- **Design Decision:** Using DNS I want to route the requests to load balancer IP. This use case is fulfilled by external load balancer which distributes HTTP and HTTPS traffic to backends hosted on a variety of Google Cloud platforms such as GKE. GCP provides full support for GKE applications, which further made the choice strong.

**GCP Ingress Controller:**

We require Ingress controller, to transfer incoming requests to appropriate services based upon the rules defined inside the ingress object. GCP has its own Ingress controller, which integrates well with GKE and other GCP services.

![GKE Ingress Controller](/documentation/images/Ingress_Controller.png)

- **Design Decision:** There are multiple Ingress Controller in the market such as NgINX, Contour, Istio Ingress, etc. GCP also provide support to make use of custom Ingress controller, but the GCP’s own Ingress controller, which is being created on the creation of Ingress object without further configuration ease the process of using it. Additionally, the auto creation of load balancer and all the dashboards are integrated in GCP for better debugging.

**Ingress Object:**

Ingress exposes HTTP and HTTPS routes from outside the cluster to services within the cluster. Traffic routing is controlled by rules defined on the Ingress resource.

Ingress controller is required In-order to make ingress object work. So, for this project, creation of Ingress object tiggers use of GKE Ingress controller. And, it also start load balancers, to handle the incoming requests coming to the system.

- **Design Decision:** Normally, if users need to access multiple services deployed in k8s cluster, we have to expose each one individually to the outside world. This might mean setting up separate IP addresses or load balancers for each service, which can be complex and costly. As a solution to this Ingress acts as a single entry point for all the traffic coming into your Kubernetes cluster. It knows how to route requests to the right service based on the URL path or other rules you define.

**Service (SVC):**

In Kubernetes, a Service is a method for exposing a network application that is running as one or more Pods in your cluster. ShareItinerary employs NodePort service. Which means, it Exposes the Service on each Node's IP at a static port. So, the ingress can transfer requests to these ports. 

**Deployment:**

A "deployment" is like a manager that tells Kubernetes how many pods you need and how they should be updated. For example, if you want your website to be running on 5 different servers at the same time, the deployment makes sure there are always 5 pods running. If one pod fails, the deployment notices and starts a new one to replace it. If you need to update your website with new features, the deployment ensures the old version is replaced with the new version smoothly.

**Pods:**

Think of a "pod" like a box that holds one or more of these little versions of your website (or any application). Inside this pod, there's everything your website needs to run, like the code, settings, and any tools it uses. A pod is the smallest unit in Kubernetes, and it usually contains one container (which you can think of as a small, lightweight virtual machine) but can have more if needed.