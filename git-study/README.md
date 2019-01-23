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


・ squash to merge
```
1. master ⇒ develop
2. develop ⇒ feature/xxx
   2.1. コミットA
   2.2. コミットB
3. ↑マージ
```
