
# tag::variables[]
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

variable "image_path" {
  type        = string
  description = "where is located the qcow2 image"
}

# end::variables[]

# tag::resources[]
provider "libvirt" {
  uri = var.kvm_destination_uri
}

resource "libvirt_volume" "os_image" {
  name   = "os_image"
  pool   = "default"
  source = var.image_path
}

resource "libvirt_domain" "new_vm" {
  name   = var.hostname
  memory = var.memory
  vcpu   = "2"

  disk {
    volume_id = "${libvirt_volume.os_image.id}"
  }

  network_interface {
    hostname = var.hostname
  }
}
# end::resources[]