resource "ovh_cloud_project_kube" "my_kube_cluster" {
  count        = var.number_of_clusters
  service_name = var.service_name
  name         = "${var.cluster_name}-${count.index}"
  region       = var.ovh_region
  version      = var.k8s_version
}

resource "ovh_cloud_project_kube_nodepool" "node_pool" {
  for_each = {
    for kube_cluster in ovh_cloud_project_kube.my_kube_cluster : kube_cluster.name => kube_cluster
  }
  service_name  = var.service_name
  kube_id       = each.value.id
  name          = "${each.value.name}-nodepool"
  flavor_name   = var.ovh_k8s_flavor
  autoscale     = var.ovh_k8s_autoscale
  desired_nodes = var.ovh_k8s_disired_nodes
  max_nodes     = var.ovh_k8s_max_nodes
  min_nodes     = var.ovh_k8s_min_nodes
}

/*resource "local_file" "kubeconfig" {
  count    = var.number_of_clusters
  content  = ovh_cloud_project_kube.my_kube_cluster[count.index].kubeconfig
  filename = "${var.cluster_name}-${count.index}.yml"

  // Prometheus operator deployment
  provisioner "local-exec" {
    command = <<EOT
              helm install prometheus-community/kube-prometheus-stack \
                            --kubeconfig ${var.cluster_name}-${count.index}.yml \
                            --create-namespace --namespace prometheus \
                            --generate-name \
                            --set prometheus.service.type=LoadBalancer \
                            --set prometheus.prometheusSpec.serviceMonitorSelectorNilUsesHelmValues=false \
                            --set grafana.enabled=false
              EOT
  }
}
*/