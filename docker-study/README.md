
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

## Step2. DockerFile
* Dockerfile �̓R���e�i�[���̊��ŉ����N���邩���`�������

```
# �`�[�g�V�[�g
## �֗�
apt-get update
apt-get install vim

## Docker
sudo service docker start

### �m�F�n
* �����Ă���R���e�i�̊m�F
docker ps

* ��~���Ă���R���e�i�̊m�F
docker ps -a

* �C���[�W�̊m�F
docker image ls

### �쐬�n
* �C���[�W�̍쐬
docker build [ -t �o�C���[�W���p [ :�o�^�O���p ] ] �oDockerfile�̂���f�B���N�g���p

### �擾�n
* �C���[�W�̎擾
docker pull �C���[�W��[:�^�O|@�C���[�W�̃n�b�V���l]
��: docker pull debian:jessie
��(ECR):
$(aws ecr get-login --region ap-northeast-1 --no-include-email)
docker pull 683640743654.dkr.ecr.ap-northeast-1.amazonaws.com/umejima-sample-ecr:latest

### �N���n
* �R���e�i�̋N��
docker run [�C���[�WID]
�� ���݂��Ȃ��ꍇ�ADockerHUB ���� PULL ������ۂ��H

  ��: �}�V���̃|�[�g4000���R���e�i�̌��J�|�[�g80�Ƀ}�b�s���O���āA�A�v�������s
  docker run -p 4000:80 <�C���[�WID>

  ��: �����o�b�N�O���E���h�Ńf�^�b�`���[�h�Ŏ��s
  docker run -d -p 4000:80 <�C���[�WID>

  ��: �N�������܂܂ɂ���
  docker run -itd <�C���[�WID>

  ��: host �� iptable �𗘗p����
  docker run -itd --net=host <�C���[�WID>

### �ċN��
docker restart <�R���e�iID>

### ��~�n
* �R���e�i�̒�~
docker container stop <�R���e�iID>

### �폜�n
* �R���e�i�̍폜
docker rm [�R���e�iID] [�R���e�iID] ...
�� �S�R���e�i�폜 (docker rm `docker ps -a -q`)

* �C���[�W�̍폜
docker rmi [�C���[�WID]

### �R���e�i�ɓ���
docker exec -it <�C���[�WID> bach
�� bash �Ƃ� sh �Ƃ��œ������ۂ��B���̂Ƃ��� bash �ł���Ė��Ȃ��B

## DockerFile
http://docs.docker.jp/engine/reference/builder.html#

* �ȍ~�̖��߂Ŏg�� �x�[�X�E�C���[�W ���w�肵�܂��B
FROM <�C���[�W> | <�C���[�W>:<�^�O> | <�C���[�W>@<digest>
��: FROM python:2.7-slim
�� �ŏ��ɋL�ڂ���K�v������B

# ���ߎ��s���̍�ƃf�B���N�g�����w�肵�܂��B
WORKDIR <path>
��: WORKDIR /app
�� �w�肪�Ȃ��ꍇ�ł��A����ɍ����炵���B

# �t�@�C�����ړ����܂��B
COPY <�\�[�X> <���M��>
��: COPY . /app
�� <���M��> �͐�΃p�X�ł��B���邢�́A�p�X�� WORKDIR ����̑��΃p�X�ł��B

# �V�F���`���ŃR�}���h�����s���܂��B
RUN <�R�}���h>
��: RUN pip install --trusted-host pypi.python.org -r requirements.txt
�� �V�F���`���A�R�}���h�����s����BLinux ��̃f�t�H���g�� /bin/sh -c

# �R���e�i�����s���Ƀ��b�X������|�[�g���w�肵�܂��B
EXPOSE <�|�[�g�ԍ�>
��: EXPOSE 80
�� -p �t���O���w�肵�Ȃ���΁A���ꂾ���ł̓z�X�g����R���e�i�ɃA�N�Z�X�ł���悤�ɂ��܂���Ƃ����L�q�L��B

# ���ϐ���ݒ肵�܂��B
ENV <key> <value>
��: ENV NAME World

# Run app.py when the container launches
CMD ["���s�o�C�i��", "�p�����[�^�P", "�p�����[�^�Q"] �i exec �`���A��������`���j
��: CMD ["python", "app.py"]
�� Dockerfile �� CMD ���߂���x�����w��ł��܂��B������ CMD ������ꍇ�A�ł����� CMD �̂ݗL���B
```

# TODO
## DockerCompose
* �R���e�i���m�̘A�g�֘A
