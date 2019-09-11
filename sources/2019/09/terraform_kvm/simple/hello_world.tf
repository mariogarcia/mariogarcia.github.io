provider "libvirt" { # <1>
  uri = "qemu:///system"
}

resource "libvirt_volume" "os_image" { # <2>
  name   = "os_image"
  pool   = "default"
  source = "/home/mario/Development/devops/hashicorp-tools/packer/out/packer-buster"
}

resource "libvirt_domain" "new_vm" { # <3>
  name   = "postgres"
  memory = "1024"
  vcpu   = "2"

  disk {
    volume_id = "${libvirt_volume.os_image.id}"
  }

  network_interface {
    hostname = "postgresvm"
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
