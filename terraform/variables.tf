variable "subscription_id" {
  description = "Azure subscription id"
  type        = string
  sensitive   = true
}
variable "client_id" {
  description = "Azure client id"
  type        = string
  sensitive   = true
}
variable "client_secret" {
  description = "Azure Service Principal Client Secret"
  type        = string
  sensitive   = true
}
variable "tenant_id" {
  description = "Azure tenant id"
  type        = string
  sensitive   = true
}