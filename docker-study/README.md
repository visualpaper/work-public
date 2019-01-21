
# Docker �֘A�׋�
## �͂��߂�
### ���p��
1. EC2 �쐬
2. [�ڑ�] ���ɕ\������� EC2 �z�X�g��/�L�[�y�A�t�@�C����閧���Ɏw�肵 SSH �ڑ�
�� �f�t�H���g�� 22 �|�[�g�͊J���Ă���

### Docker �����ݒ�
1. �C���X�g�[��
```
sudo yum update -y
sudo yum install -y docker
```

2. �N��
```
sudo service docker start
```

3. Docker �O���[�v�� ec2-user ��ǉ�
```
sudo usermod -a -G docker ec2-user
```

4. ��U���O�A�E�g�˃��O�C��

5. info �\���m�F
```
docker info
```
�� ec2-user �� sudo �Ȃ��ł����邱�Ƃ��m�F

## STEP1: �C���[�W�擾�`�N���`�I��
1. �C���[�W�� Docker HUB ���擾����
> docker pull hello-world

2. �C���[�W�ꗗ�m�F
> docker image ls

3. �C���[�W�����ɃR���e�i���N������
> docker run hello-world

4. �N�����Ă���R�l�e�i�̊m�F
> docker ps
�� hello-world �͋N�������ςȂ��łȂ��炵��

5. ��~���Ă���R���e�i�̊m�F
> docker ps -a

6. �R���e�i�̍폜
> docker rm [�R���e�iID]

7. ��~���Ă���R���e�i�̊m�F
> docker ps -a

8. �C���[�W�̍폜
> docker rmi [�C���[�WID]

9. �C���[�W�̊m�F
> docker image ls

```
# �`�[�g�V�[�g
## �m�F�n
* �����Ă���R���e�i�̊m�F
docker ps

* ��~���Ă���R���e�i�̊m�F
docker ps -a

* �C���[�W�̊m�F
docker image ls

## �擾�n
* �C���[�W�̎擾
docker pull �C���[�W��[:�^�O|@�C���[�W�̃n�b�V���l]
��: docker pull debian:jessie

## �N���n
* �R���e�i�̋N��
docker run [�C���[�WID]
�� ���݂��Ȃ��ꍇ�ADockerHUB ���� PULL ������ۂ��H

## �폜�n
* �R���e�i�̍폜
docker rm [�R���e�iID] [�R���e�iID] ...
�� �S�R���e�i�폜 (docker rm `docker ps -a -q`)

* �C���[�W�̍폜
docker rmi [�C���[�WID]

```
