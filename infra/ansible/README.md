[Ansible]

�p�����������\�z�c�[��
�� ��������ׂ��A�Ƃ��������e���v���[�g�ɋL�ڂ���B

[���p�菇]

1. �C���X�g�[��
   > sudo yum update
   > sudo yum -y install python-devel openssl-devel gcc git
   > sudo easy_install pip
   > sudo pip install ansible
   > ansible --version
     �� �o�[�W�������\�������� OK

2. hosts �t�@�C���쐬
   �� ini �t�@�C���ŋL�ڂ��Ă��邪�A�ʂ� yaml �ł��ǂ� (https://qiita.com/hiroyuki_onodera/items/e6d0d308eb44e26fa03f)
   �� localhost (���g) �̃}�V���Ɏ��{������������N�ɋL�ڂ���Ă��� (localhost ansible_connection=local)

   Playbook �Ŏw�肷�� hosts �̃��t�@�����X�ƂȂ�B

3. Playbook �쐬
   �� �ʃ��|�W�g���ł�邽�߁A����

4. ���{
   > ansible-playbook -i inventory/hosts playbook.yml
   �� --extra-vars "hosts=all user=root" �� group_vars �ɒ�`���Ă��Ȃ����̂��������ނ��Ƃ��ł���B
