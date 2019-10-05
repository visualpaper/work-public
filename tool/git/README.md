* 過去の履歴消して無理やり push
```
git reset --hard xxxx
git push -f
```

* 過去の履歴を残して戻す
```
git revert xxxx
git push
```

* Git での履歴追跡について
https://stackoverflow.com/questions/2314652/is-it-possible-to-move-rename-files-in-git-and-maintain-their-history
https://git.wiki.kernel.org/index.php/GitFaq#Why_does_Git_not_.22track.22_renames.3F

```
git log --follow `filename` で追えって言ってる
```

色々試したが、無理っぽい。。。？


* 履歴をまとめる
```
git log --oneline

> b1b5563 (HEAD, origin/feature/git-study-6) rename nomi
> 6a35a51 Merge pull request #4 from visualpaper/feature/git-study-4
> d0ed420 (origin/feature/git-study-4, feature/git-study-4) sampole2
> 62db707 rename
> a759e58 (origin/feature/git-study-3, feature/git-study-3) Merge pull request #2 from visualpaper/feature/git-study-2
> 2cde7bb (origin/feature/git-study-2, feature/git-study-2) test2
> 6ad81bb test
> 3f3030f Merge pull request #1 from visualpaper/feature/git-study-1
> e2e2a6d (origin/feature/git-study-1, feature/git-study-1) 実験準備 branch
> 5b75231 (master) iam 作り直し
> 34eba35 sqs 勉強
> adb8b03 kcl 勉強時に使用したものを一応 push
> e85b7a7 Create README.md
> ce706e3 initialize push

6ad81bb 以降のコミットを全部まとめたいとして、

git rebase -i 6ad81bb

> pick 2cde7bb test2
> pick 62db707 rename
> pick d0ed420 sampole2
> pick b1b5563 rename nomi
> pick 6d1e920 modify
> pick ba9557f rename nomi
> pick 03ef642 modify
> pick ebb5fcb readme 追加
> 
> # Rebase 6ad81bb..ebb5fcb onto 6ad81bb (8 commands)
> ～～～～～～

↑の pick を f に変更
※ vim 操作と同じ。i で挿入モード/:wq で保存

(p)pick	コミットをそのまま残す。
(r)reword	コミットメッセージを変更。
(e)edit	コミット自体の内容を編集。
(s)squash	直前のpickを指定したコミットに統合。
メッセージも統合。
(f)fixup	直前のpickを指定したコミットに統合。
メッセージは破棄。

push したい branch に force push

git push -f origin HEAD:feature/git-study-6
```

・ rebase (特定のコミットを省く)
1. rebase -i xxxxx
2. 特定のコミットを除く (https://tkengo.github.io/blog/2013/06/08/git-rebase-reference2/)

```
pick xxxxxx comment-A ← これを省きたい
pick yyyyyy comment-B
↓
r yyyyyy comment-B
```

3. コンフリクトが発生した場合は中断されるため、commit ダイアログにていつものようにマージ解消
4. マージ解消したファイルを add する (https://qiita.com/ryosukehujisawa/items/d5f013e80f4845ec5e92#git-add)
5. git rebase --continue で継続
6. 後は squach to meger と同じで最後に git push -f すれば OK

・ squash to merge
```
1. master ⇒ develop
2. develop ⇒ feature/xxx
   2.1. コミットA
   2.2. コミットB
3. ↑マージ
4. develop ⇒ deature/yyy
   4.1. コミットC
   4.2. コミットD
5. develop ⇒ oneoff/zzz
6. git log --oneline

> 0c55a68 (HEAD -> oneoff/git-study-squash, origin/oneoff/git-study-squash, origin/develop) Merge pull request #10 from visualpaper/feature/git-study-2
> 31fb8a8 (origin/feature/git-study-2, feature/git-study-2) コミットD
> eed74a8 コミットC
> 091b7b1 Merge pull request #9 from visualpaper/feature/git-study-1
> ad180cd (origin/feature/git-study-1, feature/git-study-1) コミットB
> d9e0f20 コミットA
> 28476c6 (origin/master) Merge pull request #8 from visualpaper/feature/1_docker_install
> bd58f05 (origin/feature/1_docker_install, feature/1_docker_install) 1: docker 入門(STEP1まで)
> 7c82ec0 (master) Update README.md

8. git rebase -i 28476c6
※ まとめたいコミットの一つ手前からである点に注意
※ ここから先は i で編集、:wq! で上書き (OK)

> pick d9e0f20 コミットA
> pick ad180cd コミットB
> pick eed74a8 コミットC
> pick 31fb8a8 コミットD

↓

> r d9e0f20 コミットA
> s ad180cd コミットB
> s eed74a8 コミットC
> s 31fb8a8 コミットD

9. :wq! で上書きすると、新しいコミットコメント設定

> コミットA
> 
> # Please enter the commit message for your changes. Lines starting
> # with '#' will be ignored, and an empty message aborts the commit.

↓

> version up
> 
> # Please enter the commit message for your changes. Lines starting
> # with '#' will be ignored, and an empty message aborts the commit.

10. :wq! で上書きすると、最後に確認

> # This is a combination of 4 commits.
> # This is the 1st commit message:
> 
> version up
> 
> # This is the commit message #2:
> 
> コミットB
> 
> # This is the commit message #3:
> 
> コミットC
> 
> # This is the commit message #4:
> 
> コミットD
> 
> # Please enter the commit message for your changes. Lines starting
> # with '#' will be ignored, and an empty message aborts the commit.
> #

11. :wq! しておしまい。

12. git push -f

13. oneoff/xxx to master

14. master to develop
```
