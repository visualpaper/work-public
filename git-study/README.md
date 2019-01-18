* �ߋ��̗��������Ė������ push
```
git reset --hard xxxx
git push -f
```

* �ߋ��̗������c���Ė߂�
```
git revert xxxx
git push
```

* Git �ł̗���ǐՂɂ���
https://stackoverflow.com/questions/2314652/is-it-possible-to-move-rename-files-in-git-and-maintain-their-history
https://git.wiki.kernel.org/index.php/GitFaq#Why_does_Git_not_.22track.22_renames.3F

```
git log --follow `filename` �Œǂ����Č����Ă�
```

�F�X���������A�������ۂ��B�B�B�H


* �������܂Ƃ߂�
```
git log --oneline
```

> b1b5563 (HEAD, origin/feature/git-study-6) rename nomi
> 6a35a51 Merge pull request #4 from visualpaper/feature/git-study-4
> d0ed420 (origin/feature/git-study-4, feature/git-study-4) sampole2
> 62db707 rename
> a759e58 (origin/feature/git-study-3, feature/git-study-3) Merge pull request #2 from visualpaper/feature/git-study-2
> 2cde7bb (origin/feature/git-study-2, feature/git-study-2) test2
> 6ad81bb test
> 3f3030f Merge pull request #1 from visualpaper/feature/git-study-1
> e2e2a6d (origin/feature/git-study-1, feature/git-study-1) �������� branch
> 5b75231 (master) iam ��蒼��
> 34eba35 sqs �׋�
> adb8b03 kcl �׋����Ɏg�p�������̂��ꉞ push
> e85b7a7 Create README.md
> ce706e3 initialize push

6ad81bb �ȍ~�̃R�~�b�g��S���܂Ƃ߂����Ƃ��āA

```
git rebase -i 6ad81bb
```

> pick 2cde7bb test2
> pick 62db707 rename
> pick d0ed420 sampole2
> pick b1b5563 rename nomi
> pick 6d1e920 modify
> pick ba9557f rename nomi
> pick 03ef642 modify
> pick ebb5fcb readme �ǉ�
> 
> # Rebase 6ad81bb..ebb5fcb onto 6ad81bb (8 commands)
> �`�`�`�`�`�`

���� pick �� f �ɕύX
�� vim ����Ɠ����Bi �ő}�����[�h/:wq �ŕۑ�

```
(p)pick	�R�~�b�g�����̂܂܎c���B
(r)reword	�R�~�b�g���b�Z�[�W��ύX�B
(e)edit	�R�~�b�g���̂̓��e��ҏW�B
(s)squash	���O��pick���w�肵���R�~�b�g�ɓ����B
���b�Z�[�W�������B
(f)fixup	���O��pick���w�肵���R�~�b�g�ɓ����B
���b�Z�[�W�͔j���B
```

push ������ branch �� force push

```
git push -f origin HEAD:feature/git-study-6
```
