variable "kvm_destination_uri" {
  type        = string
  description = "kvm instance uri where the vm is going to be deployed"
}

variable "hostname" {
  type        = string
  description = "hostname of the vm"
}

variable "memory" {
  type        = string
  description = "amount of memory"
}

variable "bridge_name" {
  type        = string
  description = "name of the bridge configured in the host"
}

variable "cpu_count" {
  type        = string
  description = "number of cpus used by vm"
}

variable "os_image" {
  type        = string
  description = "name of the vm volume"
}

variable "image_source_path" {
  type        = string
  description = "qcow2 image path"
}