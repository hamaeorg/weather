terraform {
    required_providers {
        azurerm = {
            source = "hashicorp/azurerm"
            version = "3.0.2"
        }
    }
}

provider "azurerm" {
    features{}
    subscription_id = var.subscription_id
    client_id = var.client_id
    client_secret = var.client_secret
    tenant_id = var.tenant_id
}

resource "azurerm_resource_group" "rg" {
    name = "rg-aks-westeu-01"
    location = "westeurope"
    tags = {
        source = "Terraform"
    }

}

resource "azurerm_kubernetes_cluster" "akc" {
  name                = "aks-westeu-01"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = "aks-westeu-01-dns"
  sku_tier            = "Free"
  tags = {
    source = "Terraform"
  }

  default_node_pool {
    name                = "agentpool"
    vm_size             = "Standard_B2s"
    node_count           = 2
  }

  identity {
    type         = "SystemAssigned"
  }

#   network_profile {
#     network_plugin     = "azure"
#     dns_service_ip     = "10.0.0.10"
#     docker_bridge_cidr = "172.17.0.1/16"
#     service_cidr       = "10.0.0.0/16"
#     load_balancer_sku  = "Standard"
#   }
}