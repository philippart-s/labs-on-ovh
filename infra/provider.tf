terraform {
  required_version = "~> 1.3.6"
  required_providers {
    ovh = {
      source  = "ovh/ovh"
      version = "~> 0.25.0"
    }
  }
}

provider "ovh" {
  endpoint = "ovh-eu"
}
