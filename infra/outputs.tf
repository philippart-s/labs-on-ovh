output "kubeconfig" {
  description = "Generation of each kubeconfig file"
  count       = var.number_of_clusters
  //content       = [for kube in ovh_cloud_project_kube.my_kube_cluster : kube.kubeconfig]
  sensitive = true
  filename  = "polytech-${count.index}.yml"
}
