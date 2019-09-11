provider "libvirt" {
  uri = var.kvm_destination_uri
}

resource "libvirt_volume" "os_image" {
  name   = "os_image${var.os_image}"
  pool   = "default"
  source = var.image_source_path
}

resource "libvirt_domain" "new_vm" {
  name   = var.hostname
  memory = var.memory
  vcpu   = var.cpu_count

  disk {
    volume_id = "${libvirt_volume.os_image.id}"
  }

  network_interface {
    hostname = var.hostname
    bridge   = var.bridge_name
  }

  graphics {
    listen_type = "address"
  }

  console {
    type        = "pty"
    target_port = "0"
    target_type = "virtio"
  }
}