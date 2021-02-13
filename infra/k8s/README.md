
kubernetes

* k8s �}�X�^�[
  �N���X�^�[���Ǘ�����

* k8s �N���X�^�[
  �m�[�h�E�}�X�^���܂Ƃ߂��N���X�^

* k8s �m�[�h
  �R���e�i��z�u����ꏊ (�}�V��)
  �� �}�X�^�[�����J���Ă���Kubernetes API���g�p���ă}�X�^�[�ƒʐM

* K8s Pod
  1�ȏ�̃A�v���P�[�V�����R���e�i(Docker��rkt�Ȃ�)�̃O���[�v�Ƃ����̃R���e�i�̋��L���\�[�X��\��Kubernetes�̒��ۊT�O
  �� �l�b�g���[�L���O�A�{�����[��

* Deployments
  �A�v���P�[�V�����̃C���X�^���X���쐬���A�X�V������@���w��
  �� �X�P�W���[�����O�A�X�P�[�����O�A�Z���t�q�[�����O

* Ingress
  �N���X�^�[���� Service �ɑ΂���O������̃A�N�Z�X(���HTTP)���Ǘ�����API�I�u�W�F�N�g
  �� (L7)���[�h�o�����T�[

* Service
  Pod�̘_���Z�b�g�ƁA�����ɃA�N�Z�X���邽�߂̃|���V�[���`���钊�ۊT�O
  �� YAML(����)�܂���JSON���g���Ē�`����
  �� (L4)���[�h�o�����T�[�ANAT�ADNS


## kubectl

* Kubernetes�̃R�}���h���C���C���^�[�t�F�C�X
* Kubernetes API ���g�p���ăN���X�^�[�ƑΘb����

### �C���X�g�[��

curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl

### �R�}���h

* kubectl version --client

* kubectl config view
  �� �N���X�^�R���t�B�O��� (�N���X�^�E�F�؁E�R���e�L�X�g�̃R���t�B�O��񂪂���A�ǂ̃N���X�^�𗘗p���邩�̎w�肪�ł���)
  �� ~/.kube/config �ȂǂɃ}�j�t�F�X�g�t�@�C����p�ӂ��邱�ƂŕύX�\
  �� �R���e�L�X�g�ʂɃR�}���h��ł��Ƃ��\ (kubectl --context prd-admin get pod)

�� �R���e�L�X�g (����т���ɕt������N���X�^�E�F�؏��) �����߂���� pod �̍쐬�����s���K�v�����邽�߁A
   �����ňꂩ���炸�A�ȈՃc�[�� (minikube) �𗘗p����B


## minikube

* Minikube�̓��[�J������Kubernetes���ȒP�Ɏ��s���邽�߂̃c�[��
* �V���O���m�[�h�̃N���X�^�̂ݎ��s�\

### �C���X�g�[��

curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/bin/
sudo yum install -y conntrack
sudo minikube start --vm-driver=none

### �R�}���h

* minikube start/status/stop
  �� �J�n/�X�e�[�^�X/��~

* sudo minikube kubectl -- apply -f xx --prune -l system=xxx
  �� ���\�[�X�쐬
     (kind �̒l�ɂ���č쐬����郊�\�[�X���߂�)
     (�����̃��\�[�X����̃}�j�t�F�X�g�t�@�C���Ɋ܂߂邱�Ƃ��\)
  �� create �ł��쐬�\�����A�O��Ƃ̍������m�F�\�Ȃ悤�ɂ��邽�� apply �𗘗p����
     (apply �̏ꍇ�A���p�����}�j�t�F�X�g�t�@�C�������O�Ɏc�邽�ߑO�̃}�j�t�F�X�g�t�@�C�����Ȃ��Ȃ����Ƃ��Ă���r�ł���\��������s�����)
  �� prune ��t���邱�ƂŁA�ύX�����ɂ��폜�������Ď��{�����
  �� label ��t���邱�ƂŁA�Ώۃ��\�[�X�̍i�荞�݂��\

* sudo minikube kubectl get pods
  �� Pod �ꗗ�擾
  �� label ��t���邱�ƂŁA�Ώۃ��\�[�X�̍i�荞�݂��\

* sudo minikube kubectl get service
  �� Service �ꗗ�擾
  �� label ��t���邱�ƂŁA�Ώۃ��\�[�X�̍i�荞�݂��\

* sudo minikube kubectl -- exec -it xx bash
  �� docker exec �Ɠ���
  �� xx �� pod ��������Γ����ɃA�N�Z�X�ł���

* sudo minikube kubectl port-forward xx (localhost�̃|�[�g�ԍ�):(service�̃|�[�g�ԍ�)
  �� xx �� deployment/service/pod �����ꂩ���w��\
  �� service �̏ꍇ�� svc/xx
