variable "access_key" {}
variable "secret_key" {}

# var. ‚Å•Ï”QÆ‰Â”\

provider "aws" {
    access_key = "${var.access_key}"
    secret_key = "${var.secret_key}"
    region = "ap-northeast-1"
}

