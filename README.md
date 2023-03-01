## How to use plan ?

 - set the three environment variable to configure the OVHcloud provider, see https://registry.terraform.io/providers/ovh/ovh/latest/docs
 - set the `TF_VAR_service_name` environment variable to set the service_name (unique OVHcloud project id)
 - apply the plan with the service_name variable setted: `terraform apply -var="service_name=12346789"`