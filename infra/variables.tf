variable "service_name" {
  description = "Openstack tenant Id"
  type        = string
}

variable "number_of_clusters" {
  description = "Desired clusters number"
  type        = number
  default     = 1
}

variable "deploy_prometheus" {
  description = "If true deploy a prometheus on each cluster, false is the default"
  type = bool
  default = false
}

variable "cluster_name" {
  description = "Root of the cluster name"
  type = string
  default = "k8s"
}

variable "ovh_region" {
  description = "OVH region where create the K8s clusters"
  type = string
  default = "GRA7"
}

variable "k8s_version" {
  description = "The desired version of K8s"
  type = string
  default = "1.25"
}

variable "ovh_k8s_flavor" {
  description = "Flavor to use for the K8s cluster"
  type = string
  default = "d2-8"
}

variable "ovh_k8s_autoscale" {
  description = "Set or not the autoscale feature for node pool"
  type = bool
  default = false
}

variable "ovh_k8s_disired_nodes" {
  description = "Number of node at the creation of the cluster"
  type = number
  default = 3
}

variable "ovh_k8s_min_nodes" {
  description = "Minimal number of nodes at the creation of the cluster"
  type = number
  default = 3
}

variable "ovh_k8s_max_nodes" {
  description = "Maximal number of nodes at the creation of the cluster"
  type = number
  default = 3
}