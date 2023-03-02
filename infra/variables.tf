variable "service_name" {
  description = "Openstack tenant Id"
  type        = string
}

variable "number_of_clusters" {
  description = "Desired clusters number"
  type        = number
  default     = 2
}
