output "kubeconfig" {
  description = "Generation of each kubeconfig file"
  value       = [for kube in ovh_cloud_project_kube.my_kube_cluster : kube.kubeconfig]
  sensitive = true
}
