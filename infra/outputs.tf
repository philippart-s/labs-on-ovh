output "kubeconfig" {
  description = "Concatened kubeconfig files"
  value       = [for kube in ovh_cloud_project_kube.my_kube_cluster : kube.kubeconfig]
  sensitive   = true
}
