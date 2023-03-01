resource "ovh_cloud_project_kube" "my_kube_cluster" {
   count        = var.number_of_clusters 
   service_name = "${var.service_name}"
   name         = "polytech-${count.index + 1}"
   region       = "GRA7"
   version      = "1.25"
}

resource "ovh_cloud_project_kube_nodepool" "node_pool" {
  for_each = {
    for kube_cluster in ovh_cloud_project_kube.my_kube_cluster : kube_cluster.name => kube_cluster
  }
   service_name  = "${var.service_name}"
   kube_id       = each.value.id
   name          = "${each.value.name}-nodepool" 
   flavor_name   = "d2-8"
   desired_nodes = 3
   max_nodes     = 3
   min_nodes     = 3
}