variable "access_key" {}
variable "secret_key" {}

# var. �ŕϐ��Q�Ɖ\

provider "aws" {
    access_key = "${var.access_key}"
    secret_key = "${var.secret_key}"
    region = "ap-northeast-1"
}

