## How to use plan ?

 - set the OVHcloud environment variable to configure the OVHcloud provider (see https://registry.terraform.io/providers/ovh/ovh/latest/docs & https://docs.ovh.com/gb/en/api/first-steps-with-ovh-api/):
  - OVH_APPLICATION_KEY
  - OVH_APPLICATION_SECRET
  - OVH_CONSUMER_KEY 
 - list of the variables:
  - `service_name`: Openstack tenant Id, no default value, must me set by environment variable (`TF_VAR_service_name`) or by setting it as argument `-var="service_name=<service name>`
  - `number_of_clusters`: Desired clusters number, default is `1`
  - `deploy_prometheus`: If true deploy a prometheus on each cluster, default is `false`
  - `cluster_name`: Root of the cluster name, default is `k8s`
  - `ovh_region`: OVH region where create the K8s clusters, default is `GRA7`
  - `k8s_version`: The desired version of K8s, default is `1.25`
  - `ovh_k8s_flavor`: Flavor to use for the K8s cluster, default is `d2-8`
  - `ovh_k8s_autoscale`: Set or not the autoscale feature for node pool, default is `false`
  - `ovh_k8s_disired_nodes`: Number of node at the creation of the cluster, default is `3`
  - `ovh_k8s_min_nodes`: Minimal number of nodes at the creation of the cluster, default is `3`
  - `ovh_k8s_max_nodes`: Maximal number of nodes at the creation of the cluster, default is `3`
 - apply the plan with the desired variable setted: `terraform apply -var="<variable name> = <value>"`
